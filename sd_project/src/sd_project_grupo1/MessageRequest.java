package sd_project_grupo1;


import java.util.*;
import javax.swing.*;


public class MessageRequest extends JFrame {
    
    // hashtable para guardar os pedidos (Mensagem) e as pedidos
    // mapeia chaves para valores. Qualquer objeto não nulo pode ser usado como uma
    // chave ou como um valor.
    private static final HashMap<String, Message> messagesHash = new HashMap<String, Message>();
    private static final HashMap<String, User> listUsers = new HashMap<String, User>();
    private static int iDMessage = 1;
    private User users;
    private static String message;

    public static HashMap<String, Message> getMessagesHash() {
        return messagesHash;
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

    public static int getIDMessage() {
        return iDMessage;
    }

    

    // método para guardar os utilizadores, e atribuir o id de registo.
    // syncronized para não ser mais que uma thread a aceder ao código dentro desse
    // bloco
    public void saveUser(User newUser) {
        String userNick = newUser.getNickName();
        synchronized (this) {
        if (!listUsers.containsKey(userNick)) {
            System.out.println("Não existe utilizador");
            newUser.setIdRegisto(listUsers.size()+1);
            listUsers.put(newUser.getNickName(), newUser );
            System.out.println(" user " + newUser.getIdRegisto() + " chamado " + newUser.getNickName() + " Criado");
            System.out.println(listUsers.size() + " utilizadores registados");
        } else {
            System.out.println("Já existe utilizador");
        }
        }
    }
    
    // método para guardar as mensagens com um user e a mensagem.
    // é guardada com id atribuido à hashmap, de modo a ser impressa posteriormente ordenado
    // syncronized para não ser mais que uma thread a aceder ao código dentro desse
    // bloco
    public void saveMessage(User user, String message) {
        String userNick = user.getNickName();
        synchronized (this) {
            if (listUsers.containsKey(userNick)) {
                Message newMessage = new Message(user.getNickName());
                newMessage.setMessage(message);
                messagesHash.put((String.valueOf(this.iDMessage)), newMessage);
                System.out.println("o hashMap tem " + messagesHash.size() + " mensagens enviadas");
                iDMessage += 1;
            } else {
                System.out.println("Registe-se Primeiro");
            }
        }
    }
       
    //método para imprimir os users e o seu id de registo, ciclo for do hashMap,
    //a imprimir ordenado
    public String printUsers() {
        String result = new String("");
        for (String name : listUsers.keySet()) {
            int key = listUsers.get(name).getIdRegisto();
            String value = listUsers.get(name).getNickName().toString();
            result += "ID:     " + key + " :: user: " + value;
            result += "<br>";
        }

        return result;
    }
    
    
    //método para impressão das mensagens, ciclo for do hashMap, a imprimir por ordem de entrada
    public String printMessages() {
        String result = "";

        for (String nickName : messagesHash.keySet()) {
            String key = nickName.toString();
            String user = messagesHash.get(key).getNickName().toString().replace("+", " ");
            String value = messagesHash.get(key).getMessage().toString().replace("+", " ");
            result += user + " : " + value;
            result += "<br>";
        }

        return result;
    }

    //Class mensagem com atributos do nickName do user e a mensagem
    class Message {
        private String nickName;
        private String message;

        public Message(String nickName, String message) {
            this.nickName = nickName;
            this.message = message;
        }

        public Message(String nickName) {
            this.nickName = nickName;
        }

        public void setMessage(String message) {
            this.message = message;
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
