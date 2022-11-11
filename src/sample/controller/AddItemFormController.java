package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField taskField;

    @FXML
    private TextField descriptionFiled;

    @FXML
    private Button saveTaskButton;

    @FXML
    private Button todosButton;

    @FXML
    private Label successfulLable;

    private DatabaseHandler databaseHandler;

    private int UserId;

    public AddItemFormController() {
    }

    @FXML
    void initialize() {

        databaseHandler=new DatabaseHandler();

        saveTaskButton.setOnAction(event->{

            Task task= new Task();
            Calendar calendar= Calendar.getInstance();
            java.sql.Timestamp timestamp= new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText= taskField.getText().trim();
            String taskDescription= descriptionFiled.getText().trim();

            if (!taskText.equals("") || !taskDescription.equals("")){

                task.setUserId(AddItemController.UserId);
                task.setDatecreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);
                databaseHandler.insertTask(task);
                successfulLable.setVisible(true);
                todosButton.setVisible(true);
                DatabaseHandler databaseHandler=new DatabaseHandler();
               // System.out.println(" Current taks counts: "+databaseHandler.getAllTasks(10));
                int taskNumber=0;
                try {
                    taskNumber = databaseHandler.getAllTasks(AddItemController.UserId);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                todosButton.setText("My 2Do's: " + "("+taskNumber+")");

                taskField.setText("");
                descriptionFiled.setText("");

                todosButton.setOnAction(event1 -> {
                    // take the user to tha taskes screen
                    FXMLLoader fxmlLoader= new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource
                        ("/sample/view/list.fxml"));
                    try {
                        fxmlLoader.load();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Parent root=fxmlLoader.getRoot();
                    Stage stage=new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                });
                //System.out.println("Task Added Successfully!!");
            }else {
                System.out.println("Nothings Added!!");

            }

        });

    }

    // this userid will be setted from AddItemController
    public int getUserId() {
        return this.UserId;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
        System.out.println(" AddItemFormController "+this.UserId);
    }
}
