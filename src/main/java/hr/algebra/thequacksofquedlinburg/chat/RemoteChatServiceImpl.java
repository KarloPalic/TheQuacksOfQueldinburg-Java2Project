package hr.algebra.thequacksofquedlinburg.chat;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteChatServiceImpl implements RemoteChatService{
    List<String> chatMessagesList;

    public RemoteChatServiceImpl(){
        chatMessagesList = new ArrayList<>();
    }
    @Override
    public void sendChatMessage(String message) throws RemoteException {
        chatMessagesList.add(message);
    }

    @Override
    public List<String> getAllMessages() throws RemoteException {
        return chatMessagesList;
    }
}
