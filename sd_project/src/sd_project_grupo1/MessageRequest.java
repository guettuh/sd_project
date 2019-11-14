package sd_project_grupo1;
// hashtable para guardar os pedidos (Mensagem) e as pedidos

// mapeia chaves para valores. Qualquer objeto não nulo pode ser usado como uma

import java.io.IOException;
import java.util.*;
import javax.swing.*;


// chave ou como um valor.

public class MessageRequest extends JFrame {
    private static final HashMap<String, Message> messageshash = new HashMap<String, Message>();
    private static final HashMap<String, User> listUsers = new HashMap<String, User>();
    private static final int iDMessage = 1;
    private User users;
    private static String message;

    public static HashMap<String, Message> getMessageshash() {
        return messageshash;
    }

    public static HashMap<String, User> getListUsers() {
        return listUsers;
    }

    public static int getiDMessage() {
        return iDMessage;
    }

    public User getUsers() {
        return users;
    }

    static String getMessage() {
        return message;
    }

    public int getIDMensagem() {
        return IDMensagem;
    }

    public static int getIDMessage() {
        return iDMessage;
    }

    private int IDMensagem;

    public String printUsers() {
        String result = new String("");
        for (String name : listUsers.keySet()) {
            int key = listUsers.get(name).getIdRegisto();
            String value = listUsers.get(name).getNickName().toString();
            result += "ID: " + key + " :: user: " + value;
            result += "<br>";
        }

        return result;
    }

    // método para guardar as mensagens
    // syncronized para não ser mais que uma thread a aceder ao código dentro desse
    // bloco
    public void saveUser(User newUser) {
        if (!listUsers.containsValue(newUser)) {
            System.out.println("Não existe utilizador");
            newUser.setIdRegisto(listUsers.size()+1);
            listUsers.put(newUser.getNickName(), newUser );
            System.out.println(" user " + newUser.getIdRegisto() + " Criado");
            System.out.println(listUsers.size() + " utilizadores registados");
        } else {
            System.out.println("Já existe utilizador");
        }
    }

    public void saveMessage(User user, String message) {
        String userNick = user.getNickName();
        synchronized (this) {
            if (listUsers.containsKey(userNick)) {
                Message newMessage = new Message(userNick);
                newMessage.setMessage(message);
                newMessage.setIDMessage(this.IDMensagem);
                messageshash.put((String.valueOf(this.IDMensagem)), newMessage);
                System.out.println("O hashMap contém " + messageshash.size() + " registos.");
                IDMensagem += 1;
            } else {
                System.out.println("Registe-se Primeiro");
            }
        }
    }
        

    public String printMessages() {
        String result = "";

        for (String idMessage : messageshash.keySet()) {
            String key = idMessage.toString();
            String user = messageshash.get(key).getNickName().toString();
            String value = messageshash.get(key).getMessage().toString().replace("+", " ");
            result += user + " : " + value;
            result += "<br>";
        }

        return result;
    }

    class Message {
        private String nickName;
        private String message;
        private int IDMessage;

        public Message(String nickName, String message, int IDMessage) {
            this.nickName = nickName;
            this.message = message;
            this.IDMessage = iDMessage;
        }

        public Message(String nickName) {
            this.nickName = nickName;
            this.IDMessage = 0;
        }

        public Message() {
            this.IDMessage = 0;
        }

        public int getIDMessage() {
            return IDMessage;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setIDMessage(int IDMessage) {
            this.IDMessage = IDMessage;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickame(String nickName) {
            this.nickName = nickName;
        }

        public String getMessage() {
            return message;
        }

    }
}
