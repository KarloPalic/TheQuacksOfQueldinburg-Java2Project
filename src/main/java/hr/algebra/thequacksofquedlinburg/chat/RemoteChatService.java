package hr.algebra.thequacksofquedlinburg.chat;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteChatService extends Remote {
    String REMOTE_CHAT_OBJECT_NAME = "hr.algebra.rmi.chat.service";
    void sendChatMessage(String message) throws RemoteException;
    List<String> getAllMessages() throws RemoteException;
}
