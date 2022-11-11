package sample.controller;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Task;

public class ListController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Task> listTask;

    @FXML
    private TextField listtaskField;

    @FXML
    private TextField listdescriptionFiled;

    @FXML
    private Button listsaveTaskButton;

    // this observable to show the item list to the screen
   private ObservableList<Task> tasks;
   private ListView<Task> listTasks;
   private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {
///*observableArrayList. Creates a new observable array list and adds a content of collection col to it.
// Search Results
//Featured snippet from the web
//FXCollections class to create and return the ObservableList and ObservableMap objects.

        tasks= FXCollections.observableArrayList();

        databaseHandler= new DatabaseHandler();
        ResultSet resultSet= databaseHandler.getTasksByUser(AddItemController.UserId);

        while (resultSet.next()){

            Task task= new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            tasks.addAll(task);
        }
        listTask.setItems(tasks);
        listTask.setCellFactory(cellController-> new cellController());
        listsaveTaskButton.setOnAction(event -> {
            AddNewTsak();;
        });

    }

    public void AddNewTsak(){

            if (!listdescriptionFiled.getText().equals("") || !listtaskField.getText().equals("")){
                Calendar calendar= Calendar.getInstance();
                java.sql.Timestamp timestamp=
                        new java.sql.Timestamp(calendar.getTimeInMillis());

                Task myNewTask= new Task();
                myNewTask.setUserId(AddItemController.UserId);
                myNewTask.setTask(listtaskField.getText().trim());
                myNewTask.setDescription(listdescriptionFiled.getText().trim());
                myNewTask.setDatecreated(timestamp);
                databaseHandler.insertTask(myNewTask);
                // to make the lable empty after clicking on the saveButton
                listtaskField.setText("");
                listdescriptionFiled.setText("");
                // if we make the below instruction iot well work but one's we need to delete an item
                // from the listview it will deleted only from listvielw but will not deleted from database
                //tasks.add(myNewTask);
                try {
                    initialize();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }

    }

}