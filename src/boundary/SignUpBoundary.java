package boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpBoundary extends Application {

	private TextField tfFullName = new TextField();
	private TextField tfUsername = new TextField();
	private TextField tfEmail = new TextField();
    private PasswordField pfPassword = new PasswordField();
	private TextField tfPassword = new TextField();
	private Button btnSignUp = new Button("Cadastrar");
	private Button btnLogin = new Button("J� � cadastrado? Login");
	private Label lblTitle = new Label("PetsAnatomy");

	@Override
	public void start(Stage stage) throws Exception {
		HBox mainPane = new HBox(); 
		AnchorPane formPane = new AnchorPane();
		AnchorPane designPane = new AnchorPane(); 

		Font fontTextField = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);
		Font fontTitle = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 96);
		Font fontBtn = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);
		Font fontSmallText = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);

		formPane.prefHeight(768.0);
		formPane.prefWidth(498.0);
		formPane.setStyle("-fx-background-color: #ffffff;");

		FileInputStream inputLogo = new FileInputStream("@../../../PetsAnatomy/src/assets/logo.png");
		Image imageLogo = new Image(inputLogo);
		ImageView imageViewlogo = new ImageView(imageLogo);
		imageViewlogo.setLayoutX(110.0);
		imageViewlogo.setLayoutY(52.0);
		imageViewlogo.setFitHeight(221.0);
		imageViewlogo.setFitWidth(241.0);
		imageViewlogo.setPreserveRatio(true);

		tfFullName.setLayoutX(100.0);
		tfFullName.setLayoutY(300.0);
		tfFullName.setMinSize(301.0, 43.0);
		tfFullName.setStyle("-fx-border-color: #000E44;");
		tfFullName.setFont(fontTextField);

		FileInputStream inputFullName = new FileInputStream("@../../../PetsAnatomy/src/assets/user.png");
		Image imageFullName = new Image(inputFullName);
		ImageView imageViewFullName = new ImageView(imageFullName);
		imageViewFullName.setLayoutX(61.0);
		imageViewFullName.setLayoutY(311.0);
		imageViewFullName.setFitHeight(43.0);
		imageViewFullName.setFitWidth(32.0);
		imageViewFullName.setPreserveRatio(true);

		tfUsername.setLayoutX(100.0);
		tfUsername.setLayoutY(363.0);
		tfUsername.setMinSize(301.0, 43.0);
		tfUsername.setStyle("-fx-border-color: #000E44;");
		tfUsername.setFont(fontTextField);

		FileInputStream inputUsername = new FileInputStream("@../../../PetsAnatomy/src/assets/user.png");
		Image imageUsername = new Image(inputUsername);
		ImageView imageViewUsername = new ImageView(imageUsername);
		imageViewUsername.setLayoutX(61.0);
		imageViewUsername.setLayoutY(369.0);
		imageViewUsername.setFitHeight(43.0);
		imageViewUsername.setFitWidth(32.0);
		imageViewUsername.setPreserveRatio(true);

		tfEmail.setLayoutX(100.0);
		tfEmail.setLayoutY(426.0);
		tfEmail.setMinSize(301.0, 43.0);
		tfEmail.setStyle("-fx-border-color: #000E44;");
		tfEmail.setFont(fontTextField);

		FileInputStream inputEmail = new FileInputStream("@../../../PetsAnatomy/src/assets/email.png");
		Image imageEmail = new Image(inputEmail);
		ImageView imageViewEmail = new ImageView(imageEmail);
		imageViewEmail.setLayoutX(61.0);
		imageViewEmail.setLayoutY(432.0);
		imageViewEmail.setFitHeight(43.0);
		imageViewEmail.setFitWidth(32.0);
		imageViewEmail.setPreserveRatio(true);

		pfPassword.setLayoutX(100.0);
		pfPassword.setLayoutY(493.0);
		pfPassword.setMinSize(301.0, 43.0);
		pfPassword.setStyle("-fx-border-color: #000E44;");

		FileInputStream inputPassword = new FileInputStream("@../../../PetsAnatomy/src/assets/password.png");
		Image imagePassword = new Image(inputPassword);
		ImageView imageViewPassword = new ImageView(imagePassword);
		imageViewPassword.setLayoutX(61.0);
		imageViewPassword.setLayoutY(499.0);
		imageViewPassword.setFitHeight(43.0);
		imageViewPassword.setFitWidth(32.0);
		imageViewPassword.setPreserveRatio(true);
		
        CheckBox cbPassword = new CheckBox("Mostrar/Esconder Senha");
        cbPassword.setFont(fontSmallText);
        cbPassword.setLayoutX(80.0);
        cbPassword.setLayoutY(537.0);
        
        tfPassword.setLayoutX(100.0);
        tfPassword.setLayoutY(493.0);
        tfPassword.setMinSize(301.0, 43.0);
        tfPassword.setFont(fontTextField);
        tfPassword.managedProperty().bind(cbPassword.selectedProperty());
        tfPassword.visibleProperty().bind(cbPassword.selectedProperty());

        pfPassword.managedProperty().bind(cbPassword.selectedProperty().not());
        pfPassword.visibleProperty().bind(cbPassword.selectedProperty().not());

        tfPassword.textProperty().bindBidirectional(pfPassword.textProperty());

		btnSignUp.setLayoutX(55.0);
		btnSignUp.setLayoutY(606.0);
		btnSignUp.setMinSize(330.0, 43.0);
		btnSignUp.setFont(fontBtn);
		btnSignUp.setStyle("-fx-background-color: #000E44; -fx-text-fill: white;");

		btnLogin.setLayoutX(137.0);
		btnLogin.setLayoutY(661.0);
		btnLogin.setMinSize(173.0, 30.0);
		btnLogin.setUnderline(true);
		btnLogin.setFont(fontSmallText);
		btnLogin.setStyle("-fx-background-color: none; -fx-border-color: none;");

		formPane.getChildren().addAll(imageViewlogo, tfFullName, imageViewFullName, tfUsername, imageViewUsername, tfEmail, imageViewEmail, tfPassword, pfPassword, cbPassword, imageViewPassword, btnLogin, btnSignUp);

		designPane.setMinSize(883.0, 768.0);
		designPane.setStyle("-fx-background-color: #000E44;");	

		FileInputStream inputEstetoscopio = new FileInputStream("@../../../PetsAnatomy/src/assets/estetoscopio.png");
		Image imageEstetoscopio = new Image(inputEstetoscopio);
		ImageView imageViewEstetoscopio = new ImageView(imageEstetoscopio);
		imageViewEstetoscopio.setLayoutX(287.0);
		imageViewEstetoscopio.setFitHeight(284.0);
		imageViewEstetoscopio.setFitWidth(333.0);
		imageViewEstetoscopio.setPreserveRatio(true);

		FileInputStream inputPata = new FileInputStream("@../../../PetsAnatomy/src/assets/pata.png");
		Image imagePata = new Image(inputPata);
		ImageView imageViewPata = new ImageView(imagePata);
		imageViewPata.setLayoutX(344.0);
		imageViewPata.setLayoutY(547.0);
		imageViewPata.setFitHeight(221.0);
		imageViewPata.setFitWidth(247.0);
		imageViewPata.setPreserveRatio(true);

		lblTitle.setLayoutX(126.0);
		lblTitle.setLayoutY(337.0);
		lblTitle.setMinSize(624.0, 119.0);
		lblTitle.setFont(fontTitle);
		lblTitle.setStyle("-fx-text-fill: white;");

		designPane.getChildren().addAll(imageViewEstetoscopio, imageViewPata, lblTitle);

		HBox.setHgrow(formPane, Priority.ALWAYS);
		HBox.setHgrow(designPane, Priority.ALWAYS);
		mainPane.getChildren().addAll(designPane, formPane);

		Scene scene = new Scene(mainPane, 1366, 768);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Cl�nica Veterin�ria PetsAnatomy");     	
	}

	public static void main(String[] args) {
		launch(args);
	}

}