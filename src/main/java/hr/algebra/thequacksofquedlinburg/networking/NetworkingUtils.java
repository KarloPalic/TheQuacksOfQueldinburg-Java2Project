package hr.algebra.thequacksofquedlinburg.networking;

import hr.algebra.thequacksofquedlinburg.controllers.BoardController;
import hr.algebra.thequacksofquedlinburg.gameBoard.GameState;
import hr.algebra.thequacksofquedlinburg.jndi.ConfigurationReader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkingUtils {

    public static void sendGameBoardToServer(GameState gameState) {
            try (Socket clientSocket = new Socket(ConfigurationReader.getStringValue(ConfigurationKey.HOSTNAME.getKeyName()).get(), ConfigurationReader.getIntergerValue(ConfigurationKey.SERVER_PORT.getKeyName()).get())) {
                System.err.println("Client is connecting to " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                sendSerializableRequest(clientSocket, gameState);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public static void sendGameBoardToClient(GameState gameState) {
            try (Socket clientSocket = new Socket(ConfigurationReader.getStringValue(ConfigurationKey.HOSTNAME.getKeyName()).get(), ConfigurationReader.getIntergerValue(ConfigurationKey.CLIENT_PORT.getKeyName()).get())) {
                System.err.println("Server is connecting to " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                sendSerializableRequest(clientSocket, gameState);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }


    public static void sendSerializableRequest(Socket client, GameState gameState) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        oos.writeObject(gameState);
        System.out.println("Game board sent to the server");
    }
}
