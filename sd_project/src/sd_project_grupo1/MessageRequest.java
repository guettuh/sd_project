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
    
    public String imprimirUsers (){
		String mensagensX = listUsers.toString();
		mensagensX=mensagensX.substring(1,mensagensX.length()-1);
		String resultado="";
		if (mensagensX.length()<3){
			return resultado;
		}
		else {

		String[] mensagens= mensagensX.split(",");
			System.out.println(mensagens.length);

		for (int x = 0; x<20 && x<mensagens.length;x++){
			resultado+=mensagens[x].replace("=",":");
			resultado+="<br>";
		}
		return resultado;
	}}
    
    public void saveUser(String nickName){
        User user = new User(nickName);
        listUsers.add(user);
        
    }
    public String imprimirMensagens (){
		String mensagensX = messages.toString();
		mensagensX=mensagensX.substring(1,mensagensX.length()-1);
		String resultado="";
		if (mensagensX.length()<3){
			return resultado;
		}
		else {

		String[] mensagens= mensagensX.split(",");
			System.out.println(mensagens.length);

		for (int x = 0; x<20 && x<mensagens.length;x++){
			resultado+=mensagens[x].replace("=",":");
			resultado+="<br>";
		}
		return resultado;
	}}
    
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
