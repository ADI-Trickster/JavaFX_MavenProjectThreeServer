import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Server{
    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;
    private Consumer<Serializable> callback;
    int port;
    String IP;
    boolean serverRunning = true;

    Server(Consumer<Serializable> call, int portNum, String IPstr){
        callback = call;
        port = portNum;
        this.IP = IPstr;
        server = new TheServer();
        server.start();
    }

    public class TheServer extends Thread{
        public void run() {
            try(ServerSocket mysocket = new ServerSocket(port);){
                System.out.println("Server is waiting for a client!");
                System.out.println("portNum: "+ port);

                while(serverRunning) {
                    ClientThread c = new ClientThread(mysocket.accept(), count, callback);
                    callback.accept("client has connected to server: " + "client #" + count);
                    System.out.println("client has connected to server: " + "client #" + count);
                    clients.add(c);
                    c.start();
                    count++;
                }
            }//end of try
            catch(Exception e) {
                callback.accept("Server socket did not launch");
            }
        }//end of while
    }

    public void stopServer() throws IOException{
        serverRunning = false;

        for(ClientThread c : clients){
            c.stopClient();
        }
        clients.clear();
        callback.accept("Server stopped");
    }

    class ClientThread extends Thread{
        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;
        private Consumer<Serializable> callback;
        Deck clientDeck;
        PokerInfo pokerInfo;

        ClientThread(Socket s, int count , Consumer<Serializable> callback){
            this.connection = s;
            this.count = count;
            this.callback = callback;
            clientDeck = new Deck();
            pokerInfo = new PokerInfo();
        }

        public void updateClients(String message) {
            for(int i = 0; i < clients.size(); i++) {
                ClientThread t = clients.get(i);
                try {
                    t.out.writeObject(message);
                }
                catch(Exception e) {
                }
            }
        }

        public void updateClients(PokerInfo pokerInfo){
            try {
                out.writeObject(pokerInfo);
            }
            catch(Exception e){
                callback.accept("Server socket did not launch");
            }
        }

        void stopClient() throws IOException {
            try{
//                for(ClientThread c : clients){
                    in.close();
                    out.close();
                    connection.close();
//                }
            }
            catch(Exception e){
                callback.accept("Did not close connection");
            }
        }

        public void run(){
            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);

                callback.accept("Client # " + count);

                while(true){
                    try{






                    }
                    catch(Exception e){
                        callback.accept("Server socket did not launch");
                    }
                }


            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

//            updateClients("new client on server: client #"+count);
//
//            while(true) {
//                try {
//                    Object data = in.readObject();
//                    if(data instanceof PokerInfo){
//                        PokerInfo clientPokerInfo = (PokerInfo) data;
//
//                        handlePokerMove(clientPokerInfo);
//                        updateClients(clientPokerInfo);
//                    }
//                }
//                catch(Exception e) {
//                    callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
//                    updateClients("Client #"+count+" has left the server!");
//                    clients.remove(this);
////                    count--;
//                    break;
//                }
//            }
        }//end of run

        public void handlePokerMove(PokerInfo clientPokerInfo){

        }

        public void dealCards(){
            //new game setup
            //clear current cards if any

            //set folded to false

            //shuffle deck again if we want

            //deal cards to player
            //deal cards to dealer

            //check pairPlus thing


            //update the client
        }

        public void evalHandsOnServer(){
            //check isFolded

            //get the player and dealers hands
            //check who won using the eval in gameLogic

            int winnings = 0;
            String messageToSend = "";


            //if we want to add the deal quailfy check

            //update client balance
            //give them their winnings
            //set new bet to zero
            //set messageToSend
            //send to client
        }


    }//end of client thread
}