import java.io.*;
import java.net.*;
import java.util.*;


public class mensagens{

    //hashtable para guardar os pedidos e as mensagens
    //vetor infoPedido com dados dos pedidos
    private static Hashtable<String,  consultaMensagens> consulta = new Hashtable<String,  consultaMensagens>();
    private static Hashtable<String, String> mensagens = new Hashtable<String, String>();
    private static int update = 1;

    public static Hashtable<String, consultaMensagens> getConsulta() {
        return consulta;
    }


    public static Hashtable<String, String> getMensagens() {
        return mensagens;
    }


    public static int getUpdate() {
        return update;
    }

    
    
    public void guardarMensagem (String idRegisto, String mensagem){
		synchronized (this){
			mensagens.put (idRegisto,mensagem);
		}
    }
    public Vector<consultaMensagens> getConsultaMensagens(String update) {
		Vector<consultaMensagens> result = new Vector<consultaMensagens>();
		for (Enumeration<consultaMensagens> e = consulta.elements();e.hasMoreElements(); ){
			consultaMensagens element = e.nextElement();
			result.add(element);
		}
		return  result;
	}
    
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
			resultado+=mensagens[x].replace("=",":");
			resultado+="<br>";
		}
		return resultado;
	}}

   

}