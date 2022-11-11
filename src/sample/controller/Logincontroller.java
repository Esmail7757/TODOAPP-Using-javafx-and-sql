package sample.controller;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ResourceBundle;
import java.util.zip.CheckedOutputStream;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animation.Shaker;
import sample.model.User;

import javax.swing.JOptionPane;
public class Logincontroller implements Initializable {
    @FXML
    private TextField loginusername;

    @FXML
    private PasswordField loginpasword;

    @FXML
    private Button loginbutton;

    @FXML
    private Button loginsignupbutton;

    private DatabaseHandler databaseHandler;
    private int userid;

    @FXML
    /*
    this interfdace Called to initialize a controller after its root element has been completely processed.
    */
    public void initialize(URL location, ResourceBundle resources) {


         databaseHandler = new DatabaseHandler();
        loginbutton.setOnAction((ActionEvent Event) -> {
            /*
             trim()is a built-in function that eliminates leading and trailing spaces.
             The Unicode value of space character is ‘\u0020’.
             The trim() method in java checks this Unicode
            value before and after the string, if it exists then
            removes the spaces and returns the omitted string.
             */

            String loginText = loginusername.getText().trim();
            String loginPwd = loginpasword.getText().trim();
            User user = new User();
            user.setUserName(loginText);
            user.setPassword(loginPwd);
            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;

            try {
                while (userRow.next()) {
                    counter++;
                    String name=userRow.getString("firstname");
                    userid= userRow.getInt("userid");
                    System.out.println("Welcom! "+name);
                }
                if (counter == 1) {
                    AddItemONSctreen();

                }else{

                    Shaker UserNameShaker= new Shaker(loginusername);
                    UserNameShaker.Shake();
                    Shaker UserPasswordShaker= new Shaker(loginpasword);
                    UserPasswordShaker.Shake();

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        loginsignupbutton.setOnAction(event ->
        {
            //here will take the user to sign up screen to inter his info
            //and make the log in screen disappear

            loginsignupbutton.getScene().getWindow().hide();

            //FXMLLoader ->Loads an object hierarchy from an XML document.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/SignUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));



            // this method is not called on the primary stage or on the stage that is aready visable
            // so it is wait till it is called because it depend on the input event handler
            stage.showAndWait();

        });

    }
    private void AddItemONSctreen(){
        /// add item screen

        loginsignupbutton.getScene().getWindow().hide();

        //FXMLLoader ->Loads an object hierarchy from an XML document.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/AddItem.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddItemController addItemController=loader.getController();
        addItemController.setUserId(userid);
        // this method is not called on the primary stage or on the stage that is aready visable
        // so it is wait till it is called because it depend on the input event handler
        stage.showAndWait();
    }

}