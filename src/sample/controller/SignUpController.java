package sample.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.User;

public class SignUpController implements Initializable {
    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUPuserName;

    @FXML
    private TextField signUpLocation;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private PasswordField signUppasword;

    @FXML
    private Button signUpButton;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signUpButton.setOnAction(event-> {
            createUser();
            signUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();

                Parent root=loader.getRoot();
                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });
    }
    public void createUser(){

        String name= signUpFirstName.getText();
        String lastName= signUpLastName.getText();
        String userName=signUPuserName.getText();
        String password=signUppasword.getText();
        String location=signUpLocation.getText();
        String gender="";

        if (signUpCheckBoxFemale.isSelected()){
            gender="Female";
        }else gender="Male";

        User user=new User( name,lastName,userName,password,location,gender);

        DatabaseHandler databasehandler=new DatabaseHandler();
        databasehandler.signUpUser(user);

    }


}
