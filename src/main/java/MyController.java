import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
//import javafx.scene.image.Image;

public class MyController implements Initializable{

    @FXML
    BorderPane borderPane;

    @FXML
    Button StopServer;

    Server serverConnection;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void StartServerMethod() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/game.fxml"));
        Parent root = loader.load();
        MyController controller = loader.getController();

//        controller.setTitle("This is the Server");
//        controller.textField1.clear();
//        controller.textField2.setText("final string goes here");
//        controller.textField2.clear();
//        controller.but1.setDisable(false);
//        controller.but1.setText("button one");

        borderPane.getScene().setRoot(root);
//        primaryStage.setScene(sceneMap.get("server"));
//        primaryStage.setTitle("This is the Server");
//        serverConnection = new Server(data -> {
//            Platform.runLater(()->{
////                listItems.getItems().add(data.toString());
//            });
//        });
    }

    public void StopServerMethod(){
    }




}
