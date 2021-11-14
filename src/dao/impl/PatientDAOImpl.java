package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import dao.interfaces.PatientDAO;
import entity.Patient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import org.bson.Document;
import org.bson.types.ObjectId;
import utils.MongoConnect;

public class PatientDAOImpl implements PatientDAO {

  MongoCollection<Document> patients;
  MongoConnect mc = new MongoConnect();

  public void getCollection() {
	mc.connection();
    patients = mc.database.getCollection("patients");
  }

  Document newDoc(Patient patient) {
    Document pat = new Document("ownerId", patient.getOwnerId())
      .append("name", patient.getName())
      .append("species", patient.getSpecies())
      .append("family", patient.getFamily())
      .append("bloodtype", patient.getBloodtype())
      .append("obs", patient.getObs())
      .append("birthdate", patient.getBirthdate())
      .append("lastVisit", patient.getLastVisit())
      .append("treatment", patient.getTreatment());
    return pat;
  }

  Patient newPet(Document doc) {
    String name = doc.getString("name");
    ObjectId ownerId = doc.getObjectId("ownerId");
    String species = doc.getString("species");
    String family = doc.getString("family");

    Patient p = new Patient(name, ownerId, species, family);

    p.setBloodtype(doc.getString("bloodtype"));
    p.setObs(doc.getString("obs"));
    p.setTreatment(doc.getBoolean("treatment"));
    p.setBirthdate(doc.getDate("birthdate"));
    p.setLastVisit(doc.getDate("lastVisit"));
    p.setCreated(doc.getDate("created"));
    p.setUpdated(doc.getDate("updated"));

    return p;
  }

  public void insert(Patient patient, String ownerId) {
    Document pat = newDoc(patient);

    pat.put("_id", new ObjectId());
    pat.put("created", new Date());
    pat.replace("ownerId", new ObjectId(ownerId));
    getCollection();
    patients.insertOne(pat);
  }

  public Patient findByID(String field, String id) {
    Document query = new Document();
    getCollection();
    try {
      query = patients.find(new Document("_id", new ObjectId(id))).first();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newPet(query);
  }

  public List<Patient> findByField(String field, String data) {
    List<Patient> pList = new ArrayList<Patient>();
    getCollection();
    MongoCursor<Document> cursor = patients
      .find(new Document(field, data))
      .iterator();

    try {
      while (cursor.hasNext()) {
        pList.add(newPet(cursor.next()));
      }
    } finally {
      cursor.close();
    }

    return pList;
  }

  public List<Patient> getAllPatients() {
    List<Patient> pList = new ArrayList<Patient>();
    getCollection();
    MongoCursor<Document> cursor = patients.find().iterator();

    try {
      while (cursor.hasNext()) {
        pList.add(newPet(cursor.next()));
      }
    } finally {
      cursor.close();
    }
    return pList;
  }

  public List<Patient> findByDate(String field, Date dateGte, Date dateLte) {
    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", dateGte).append("$lte", dateLte)
    );

    List<Patient> pList = new ArrayList<Patient>();
    getCollection();
    MongoCursor<Document> cursor = patients.find(betweenDates).iterator();

    try {
      while (cursor.hasNext()) {
        pList.add(newPet(cursor.next()));
      }
    } finally {
      cursor.close();
    }

    return pList;
  }

  public void update(String id, Patient patient) {
    Document pat = newDoc(patient);
    pat.put("updated", new Date());

    BasicDBObject update = new BasicDBObject("$set", pat);
    getCollection();
    patients.updateOne(new BasicDBObject("_id", new ObjectId(id)), update);
  }

  public void delete(String id) {
    getCollection();
    patients.deleteOne(Filters.eq("_id", new ObjectId(id)));
  }

  public List<Pair<String, String>> getAllIdAndNames() {
    List<Pair<String, String>> cbList = new ArrayList<Pair<String, String>>();
    getCollection();
    MongoCursor<Document> cursor = patients.find().iterator();

    try {
      while (cursor.hasNext()) {
        Document temp = cursor.next();
        cbList.add(
          new Pair<String, String>(
            temp.get("_id").toString(),
            temp.get("name").toString()
          )
        );
      }
    } finally {
      cursor.close();
    }

    return cbList;
  }

  public List<String> petsByOwner(String ownerId) {
    List<String> cbPets = new ArrayList<String>();
    getCollection();
    MongoCursor<Document> cursor = patients
      .find(new Document("ownerId", new ObjectId(ownerId)))
      .iterator();

    try {
      while (cursor.hasNext()) {
        Document temp = cursor.next();
        cbPets.add(temp.get("name").toString());
      }
    } finally {
      cursor.close();
    }

    return cbPets;
  }
}
