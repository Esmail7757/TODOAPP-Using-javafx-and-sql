package sample.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane addbutton;

    @FXML
    private Button AddItemButton;

    @FXML
    private Label noTaskAddedLable;

    @FXML
    private AnchorPane rootAnchorPane;

    public static int UserId;

    @FXML

    void initialize() {
        AddItemButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            System.out.println("added clicked");
            AddItemButton.relocate(0,0);// relocate the addItemButton and taskItemLable & make
            noTaskAddedLable.relocate(0,46);
            AddItemButton.setOpacity(0);
            noTaskAddedLable.setOpacity(0);

            try {
                AnchorPane ForomPane= FXMLLoader.load(getClass().getResource("/sample/view/AddItemForm.fxml"));
                rootAnchorPane.getChildren().setAll(ForomPane);

                AddItemController.UserId=getUserId();
               // AddItemFormController addItemFormController= new AddItemFormController();
                //addItemFormController.setUserId(getUserId());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void setUserId(int UserId ){

        this.UserId=UserId;
        System.out.println("userid from AddItemContrller is "+this.UserId);
    }

    public int getUserId(){
        return this.UserId;
    }


}
