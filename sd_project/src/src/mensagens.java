import java.io.*;
import java.net.*;
import java.util.*;


public class mensagens{

    //hashtable para guardar os pedidos e as mensagens
    //vetor infoPedido com dados dos pedidos
    private static Hashtable<String, InfoPedido> pedidos = new Hashtable<String, InfoPedido>();
	private static Hashtable<String, String> mensagens = new Hashtable<String, String>();
    private static int atualizacoes = 1;
    
    public static Hashtable<String, String> getMensagens() {
		return mensagens;
	}

	public static Hashtable<String, InfoPedido> getPedidos() {
		return pedidos;
    }
    
    public void guardarMensagem (String idRegisto, String mensagem){
		synchronized (this){
			mensagens.put (idRegisto,mensagem);
		}
    }
    public Vector<InfoPedido> getPedidos(String atualizacao) {
		Vector<InfoPedido> result = new Vector<InfoPedido>();
		for (Enumeration<InfoPedido> e = pedidos.elements();e.hasMoreElements(); ){
			InfoPedido element = e.nextElement();
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
    class InfoPedido {
        //Classe que guarda as informações de cada client que connecta com o servidor, nomeadamente
        //o seu id e seu nome, a ultima atualização conhecida
            private String nickname;
            private int atualizacoes;
            private int idRegisto;
        
            public InfoPedido(int idRegisto, String nickname) {
        
                this.nickname= nickname;
                this.idRegisto= idRegisto;
            }
            
            public InfoPedido(){
                this.atualizacoes=0;
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
    
}

}