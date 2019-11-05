import java.io.*;
import java.net.*;
import java.util.*;


public class mensagens{

    //hashtable para guardar os pedidos (consultaMensagens) e as mensagens
    
    private static Hashtable<String,  consultaMensagens> mensagem = new Hashtable<String,  consultaMensagens>();
    private static Hashtable<String, String> mensagens = new Hashtable<String, String>();
    private static int update = 1;

    public static Hashtable<String, consultaMensagens> getMensagem() {
        return mensagem;
    }


    public static Hashtable<String, String> getMensagens() {
        return mensagens;
    }


    public static int getUpdate() {
        return update;
    }

    
    //metodo para guardar as mensagens
    public void guardarMensagem (String idRegisto, String mensagem){
		synchronized (this){
			mensagens.put (idRegisto,mensagem);
		}
    }
    
    //vetor consultaMensagem com dados dos pedidos
    //o porque um vetor The Vector class implements a growable array of objects. Like an array, it contains components that can be accessed 
    //using an integer index
    public Vector<consultaMensagens> getConsultaMensagens(String update) {
		Vector<consultaMensagens> result = new Vector<consultaMensagens>();
		for (Enumeration<consultaMensagens> e = mensagem.elements();e.hasMoreElements(); ){
			consultaMensagens element = e.nextElement();
			result.add(element);
		}
		return  result;
	}
    
    //metodo para imprimir as mensagens
    //pega no hashtable, converte para strng com o metodo toString
    //imprime o conteudo no objeto resultado e retorna o valor 
    public String printMensagens (){
        
		String mensagem = mensagens.toString();
		mensagem=mensagem.substring(1,mensagem.length()-1);
		String resultado="";
		if (mensagem.length()<3){
			return resultado;
		}
		else {

		String[] mensagens= mensagem.split(",");
			System.out.println(mensagens.length);

		for (int x = 0; x<20 && x<mensagens.length;x++){
			resultado+=mensagens[x];
			resultado+="<br>";
		}
		return resultado;
	}}

   //Classe que guarda as informações de cada client que connecta com o servidor, nomeadamente
        //o seu id e seu nick, o pedido de mensagem que envia, e a ultima atualização conhecida
    class consultaMensagens {
 
            private String nickname;
            private int idRegisto;
            private String mensagem;
            private int update;
        
            public consultaMensagens(int idRegisto, String nickname, String mensagem, int update) {
        
                this.nickname= nickname;
                this.idRegisto= idRegisto;
                this.mensagem=mensagem;
                this.update=update;
            }
            
            public consultaMensagens(){
                this.update=0;
            }
            public String getNickname(){
                return this.nickname;
            }
            public void setNickname(String nickname){
                this.nickname=nickname;
            }
            public int getIdRegisto(int idRegisto){
                return this.idRegisto;
            }
            public void setIdRegisto(int idRegisto){
                this.idRegisto=idRegisto;
            }
            public void addIdRegisto(){
                this.idRegisto++;
            }
            public String getMensagem() {
                return mensagem;
            }
            public void setMensagem(String mensagem) {
                this.mensagem = mensagem;
            }  
    }
}