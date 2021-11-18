package dao.interfaces;

import java.util.Date;
import java.util.List;

import entity.Owner;
import javafx.util.Pair;

public interface OwnerDAO {

	void insert(Owner owner);
	Owner findByID(String id);
	List<Owner> findByField(String field, String data);
	List<Owner> findByDate(String field, Date dateGte, Date dateLte);
	List<Owner> getAllOwners();
	List<Pair<String, String>> getAllIdAndNames();
	void update(String id, Owner owner);
	void updatePatientList(String ownerId, String patientID);
	void delete(String id);
	void deletePatientId(String ownerId, String patientId);
}
