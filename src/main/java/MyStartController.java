import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ListView;
//import javafx.scene.image.Image;

public class MyStartController implements Initializable{
    @FXML
    public BorderPane borderPane;
    @FXML
    public Button StopServer;
    @FXML
    public Button StartServer;
    @FXML
    public TextField textF1;//IP address
    @FXML
    public TextField textF2;//port num

    @FXML
    public ListView ServerListView;//server text

    public Server serverConnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void StartServerMethod(ActionEvent event) throws IOException{
        String IP = textF1.getText();
        String portStr = textF2.getText();
        if(IP.isEmpty() || portStr.isEmpty()){
            System.out.println("NO port or IP");
            return;
        }

        int port;
        try{
            port = Integer.parseInt(portStr);
            if(port < 1 || port > 65535){
                System.out.println("Invalid port");
                return;
            }
        }
        catch (Exception e) {
            System.out.println("Invalid port or IP");
            return;
        }

        serverConnection = new Server(data ->{
            Platform.runLater(()->{
                textF2.getText();//portNum
            });
        }, port
        );

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/serverFXML.fxml"));
        Parent root = loader.load();
//        MyStartController controller = loader.getController();

//        controller.setTitle("This is the Server");
//        controller.textField1.clear();
//        controller.textField2.setText("final string goes here");
//        controller.textField2.clear();
//        controller.but1.setDisable(false);
//        controller.but1.setText("button one")

        borderPane.getScene().setRoot(root);
//        primaryStage.setScene(sceneMap.get("server"));
//        primaryStage.setTitle("This is the Server");
//        serverConnection = new Server(data -> {
//            Platform.runLater(()->{
////                listItems.getItems().add(data.toString());
//            });
//        });
    }

    public void StopServerMethod(ActionEvent event) throws IOException{
//        serverConnection.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/startServer.fxml"));
        Parent root = loader.load();

        borderPane.getScene().setRoot(root);
    }




}
