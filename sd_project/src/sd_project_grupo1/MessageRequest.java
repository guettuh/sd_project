package sd_project_grupo1; 
// hashtable para guardar os pedidos (Mensagem) e as pedidos
// mapeia chaves para valores. Qualquer objeto não nulo pode ser usado como uma

import java.util.ArrayList;
import java.util.Hashtable;

// chave ou como um valor.

public class MessageRequest {
    private static final Hashtable<String, Message> messages = new Hashtable<String, Message>(); 
    private static final ArrayList<User> listUsers = new ArrayList<User>();
    private static final int iDMessage = 0;
    private User users;
    private static int iDRegister;
    private static String message;
    
    public static Hashtable<String, Message> getMessage(){
        return messages;
    }
    
    public static int getIDMessage(){
        return iDMessage;
    }
    private int IDMensagem;
    
    //método para guardar as mensagens
    //syncronized para não ser mais que uma thread a aceder ao código dentro desse bloco
    
    public void saveMessage(String nickName, String message){
        synchronized(this){
            if(listUsers.contains(nickName)){
                Message newMessage = messages.get(nickName);
                newMessage.setMessage(message);
                newMessage.setIDMessage(this.IDMensagem + 1);
            }else{
                System.out.println("Registe-se Primeiro");
                //Colocar pop-up
            }
        }
        
    }
    
    public String printUsers (){
		String result=new String();
		for (int i = 0; i<listUsers.size();i++){
			result=listUsers.get(i).getNickName();
			result+="<br>";
		}
		return result;
	}
    
    public ArrayList<String> getUsersString(){
       ArrayList<String> listUsersString = new ArrayList<String>();
       
       for(int i=0; i<listUsers.size(); i++ ){
            String user = listUsers.get(i).toString();
            listUsersString.add(user);
       }
        return listUsersString;
   }
    public void saveUser(String nickName){
        User user = new User(nickName);
        listUsers.add(user);
        
    }
    public String printMessages (){
		String mensagens =new String();
		for (int i = 0; i<messages.size();i++){
			mensagens=messages.get(i).getNickName();
			mensagens+="<br>";
		}
		return mensagens;
	}
    
    public class Message{
        private String nickName;
        private String message;
        private int IDMessage;
        
        
        public Message(String nickName, String message, int IDMessage){
            this.nickName = nickName;
            this.message = message;
            this.IDMessage = iDMessage;
        }
        
        public Message(String nickName){
            this.nickName = nickName;
            this.IDMessage = 0;
            System.err.println("Tem que colocar uma mensagem");
        }
        
        public Message(){
            this.IDMessage = 0;
            System.err.println("Tem que colocar um nickname e uma mensagem");
        }
        
        public int getIDMessage(){
            return IDMessage;
        }
    

        public void setMessage(String message) {
            this.message= message;
        }

        public void setIDMessage(int IDMessage) {
            this.IDMessage = IDMessage;
        }
        
        public String getNickName(){
            return this.nickName;
        }
        
        public void setNickame(String nickName){
            this.nickName = nickName;
        }
        
        public String getMessage(){
            return message;
        }
    
    }
}
