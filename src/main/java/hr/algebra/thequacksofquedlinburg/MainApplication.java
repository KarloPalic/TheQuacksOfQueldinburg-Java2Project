package hr.algebra.thequacksofquedlinburg;

import hr.algebra.thequacksofquedlinburg.chat.RemoteChatService;
import hr.algebra.thequacksofquedlinburg.chat.RemoteChatServiceImpl;
import hr.algebra.thequacksofquedlinburg.controllers.BoardController;
import hr.algebra.thequacksofquedlinburg.gameBoard.GameBoardUtils;
import hr.algebra.thequacksofquedlinburg.gameBoard.GameState;
import hr.algebra.thequacksofquedlinburg.jndi.ConfigurationReader;
import hr.algebra.thequacksofquedlinburg.networking.ConfigurationKey;
import hr.algebra.thequacksofquedlinburg.networking.PlayerType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class MainApplication extends Application {

    private static Stage mainStage;
    public static PlayerType playerLoggedIn;
    private static Thread serverThread;


    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("PlayerView.fxml"));
        Parent root = fxmlLoader.load();

        BoardController boardController = fxmlLoader.getController();
        GridPane mainGridPane = boardController.getMainGridPane();

        Scene scene = new Scene(root, 1000, 650);
        boardController.setStage(stage);
        mainStage.setTitle(playerLoggedIn.name());
        mainStage.setScene(scene);
        mainStage.show();

        if (playerLoggedIn == PlayerType.SERVER){
            serverThread = new Thread(() -> startServer(mainGridPane, mainStage));
            serverThread.start();
        }else if (playerLoggedIn == PlayerType.CLIENT){
            serverThread = new Thread(() -> startClient(mainGridPane, mainStage));
            serverThread.start();
        }

    }
    public static void main(String[] args) {
        String playerName = args[0];
        if (PlayerType.SERVER.name().equals(playerName)){
            playerLoggedIn = PlayerType.SERVER;
            startRmiServer();
        } else if (PlayerType.CLIENT.name().equals(playerName)){
            playerLoggedIn = PlayerType.CLIENT;
        } else if (PlayerType.SINGLE_PLAYER.name().equals(playerName)) {
            playerLoggedIn = PlayerType.SINGLE_PLAYER;
        }
        System.out.println("Player name is: " + playerLoggedIn);
        launch();
    }

    public static void startServer(GridPane mainGridPane, Stage mainStage){
        acceptRequestsOnPort(ConfigurationReader.getIntergerValue(
                ConfigurationKey.SERVER_PORT.getKeyName()).get(),mainGridPane, mainStage);
    }

    public static void startClient(GridPane mainGridPane, Stage mainStage){
        acceptRequestsOnPort(ConfigurationReader.getIntergerValue(
                ConfigurationKey.CLIENT_PORT.getKeyName()).get(),mainGridPane, mainStage);
    }

    public static void startRmiServer(){
        try {
            Registry registry = LocateRegistry.createRegistry(
                    ConfigurationReader.getIntergerValue(ConfigurationKey.RMI_PORT.getKeyName()).get());
            RemoteChatService remoteChatService = new RemoteChatServiceImpl();
            RemoteChatService skeleton = (RemoteChatService)
                    UnicastRemoteObject.exportObject(remoteChatService,
                            ConfigurationReader.getIntergerValue(ConfigurationKey.RMI_PORT.getKeyName()).get());
            registry.rebind(RemoteChatService.REMOTE_CHAT_OBJECT_NAME, skeleton);
            System.err.println("Object registered in RMI registry");
        }
        catch(RemoteException ex) {
            ex.printStackTrace();
        }

    }

    private static void acceptRequestsOnPort(Integer port, GridPane mainGridPane, Stage mainStage) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.err.println("Server listening on port: " + serverSocket.getLocalPort());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connected from port: " + clientSocket.getPort());
                new Thread(() ->  processSerializableClient(clientSocket, mainGridPane,mainStage)).start();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void processSerializableClient(Socket clientSocket, GridPane mainGridPane, Stage mainStage) {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());){

            GameState gameState = (GameState) ois.readObject();

            Platform.runLater(() -> {
                BoardController boardController = new BoardController();
                boardController.restoreGameBoard(gameState, mainGridPane);

                gameState.setClickable(mainStage);

                String winner = gameState.getWinner(gameState.getPlayer1Points(), gameState.getPlayer2Points(), gameState.getTurnCount());
                if (!winner.isEmpty()) {
                    GameBoardUtils.showMessage(winner);
                    mainStage.close();
                }
            });

            System.out.println("Game state received from client");
            oos.writeObject("Confirmed that the game board has been received");
            oos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}