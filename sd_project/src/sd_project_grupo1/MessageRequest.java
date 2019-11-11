package sd_project_grupo1; 
// hashtable para guardar os pedidos (Mensagem) e as pedidos
// mapeia chaves para valores. Qualquer objeto não nulo pode ser usado como uma

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// chave ou como um valor.

public class MessageRequest {
    private static final Hashtable<String, Message> messageshash = new Hashtable<String, Message>(); 
    private static final ArrayList<User> listUsers = new ArrayList<User>();
    private static final int iDMessage = 0;
    private User users;
    private static int iDRegister;
    private static String message;
    
    public static Hashtable<String, Message> getMessage(){
        return messageshash;
    }
    
    
    public static int getIDMessage(){
        return iDMessage;
    }
    private int IDMensagem;
    
    //método para guardar as mensagens
    //syncronized para não ser mais que uma thread a aceder ao código dentro desse bloco
    
    public void saveMessage(String nickName, String message){
        synchronized(this){
            if(!listUsers.contains(nickName)){
                User newUser = new User(nickName);
                listUsers.add(newUser);
                
                Message newMessage = new Message(nickName);
                newMessage.setMessage(message);
                newMessage.setIDMessage(this.IDMensagem + 1);
                messageshash.put(nickName, newMessage);
            }else if(listUsers.contains(nickName)){
                Message newMessage = new Message(nickName);
                newMessage.setMessage(message);
                newMessage.setIDMessage(this.IDMensagem + 1);
                messageshash.put(nickName, newMessage);
                
            }else{
                System.out.println("Registe-se Primeiro");
                //Colocar pop-up
            }
        }
        
    }
    
    public String printUsers (){
		String result=new String("");
		for (int i = 0; i<listUsers.size();i++){
			result+=listUsers.get(i).getNickName();
			result+="<br>";
		}
		return result;
	}
    
    
    public void saveUser(String nickName){
        if(!listUsers.contains(nickName)){
            User user = new User(nickName);
            listUsers.add(user);
        }else{
            System.out.println("Já existe utilizador");
        }
    }
    public String printMessages( ){
       
		/*int mensagens = 0;
                String texto = " ";
		for (int i = 0; i<messageshash.size();i++){
			texto = messageshash.elements().nextElement().getNickName().toUpperCase() + ": ";
                        texto += messageshash.elements().nextElement().getMessage().toLowerCase()  +  ".";
			texto += "<br>";
                        
		}
		return texto;*/
    String result ="";
    for(Map.Entry<String, Message> entry :messageshash.entrySet()){  
       
    
    result += "<br>"+ entry.getKey().toString()+"<br>"+entry.getValue().getMessage()+"<br>";
  }  

               /* String mensagensString = messageshash.toString();
		mensagensString = mensagensString.substring(1,mensagensString.length()-1);
		String result="";
                String[] mensagens= mensagensString.split(",");
			System.out.println(mensagens.length);

		for (int x = 0; x<20 && x<mensagens.length;x++){
			result =mensagens[x].replace("=",":");
			mensagensString+="<br>";
		}*/
                return result;
  
	}
    
    
    class Message{
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
        }
       
        public Message(){
            this.IDMessage = 0;
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
