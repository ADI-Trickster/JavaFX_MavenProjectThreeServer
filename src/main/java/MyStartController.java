import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
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
    public ListView<String> ServerListView = new ListView<>();//server text

    public Server serverConnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void StartServerMethod(ActionEvent event) throws IOException{
        String IP = textF1.getText();
        String portStr = textF2.getText();
//        ServerListView = new ListView<>();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/serverFXML.fxml"));
        Parent root = loader.load();
        MyStartController controller = loader.getController();

        serverConnection = new Server(data ->{
            Platform.runLater(()->{
                controller.ServerListView.getItems().add(data.toString());
//                textF2.getText();
            });
        }, port
        );
        controller.serverConnection = serverConnection;
        controller.ServerListView.getItems().add("Starting server on port " + port + "...");

//        controller.ServerListView.getItems().add(ServerListView.getItems().toString());
        borderPane.getScene().setRoot(root);
    }

    public void StopServerMethod(ActionEvent event) throws IOException{
//        serverConnection.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/startServer.fxml"));
        Parent root = loader.load();
        borderPane.getScene().setRoot(root);
    }

}
