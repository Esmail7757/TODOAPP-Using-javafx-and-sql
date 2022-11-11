package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.model.Task;
                                        /// inharite from Task Class
public class cellController extends ListCell<Task> {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView imageVeiw;

    @FXML
    private Label taskLable;

    @FXML
    private Label descriptionLable;

    @FXML
    private ImageView deleteButton;

    private FXMLLoader fxmlLoader;
    @FXML
    private Label dateLable;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

    }
    @Override
    public void updateItem(Task mytask, boolean empty){

        super.updateItem(mytask,empty);

        if (mytask != null || !empty){

            if (fxmlLoader == null){
                fxmlLoader=new FXMLLoader(getClass().getResource("/sample/view/cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            taskLable.setText(mytask.getTask());
            descriptionLable.setText(mytask.getDescription());
            dateLable.setText(mytask.getDatecreated().toString());
            //System.out.println("userid for cell controller: " +AddItemController.UserId);

            int taskId= mytask.getTaskId();
            deleteButton.setOnMouseClicked(event -> {
                databaseHandler=new DatabaseHandler();

                try {
                    databaseHandler.deletTask(AddItemController.UserId,taskId);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                getListView().getItems().remove(getItem());
            });

            setText(null);
            setGraphic(rootAnchorPane);
        }else{

            setText(null);
            setGraphic(null);
        }
    }

    }

