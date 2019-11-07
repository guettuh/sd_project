import java.io.*;
import java.net.*;
import java.util.*;


public class pedidos{

    //hashtable para guardar os pedidos (Mensagem) e as pedidos
    //mapeia chaves para valores. Qualquer objeto não nulo pode ser usado como uma chave ou como um valor.
    
    private static Hashtable<String,  Mensagem> pedido = new Hashtable<String,  Mensagem>();
    private static Hashtable<String, String> mensagens = new Hashtable<String, String>();
    private static int update = 1;
    private static int idRegisto;
    private static String mensagem;


    public static Hashtable<String, Mensagem> getPedido() {
        return pedido;
    }


    public static Hashtable<String, String> getMensagens() {
        return mensagens;
    }


    public static int getUpdate() {
        return update;
    }

    //metodo para guardar as mensagens
    //syncronized para não ser mais que uma thread a aceder ao código dentro desse bloco.
    
    public void setMensagem( int idRegisto, String nickName, String mensagem, String update){
        synchronized(this){
            if(mensagens.containsKey(nickName)){
               Mensagem novaMessagem = pedido.get(nickName);
               if(update.equals(String.valueOf(this.update))){
                   this.idRegisto = idRegisto;
                   this.mensagem = mensagem;
                   this.update = this.update+1;
               } 
                   novaMessagem.setIdRegisto(idRegisto);
                   novaMessagem.setMensagem(mensagem);
                   novaMessagem.setUpdate(this.update);
               }
            else {
                Mensagem novaMessagem = new Mensagem(nickName);
                if(update.equals(String.valueOf(this.update))){
                   this.idRegisto = idRegisto;
                   this.mensagem = mensagem;
                   this.update = this.update+1;
               }
            
                pedido.put(nickName, novaMessagem);
                   novaMessagem.setIdRegisto(idRegisto);
                   novaMessagem.setMensagem(mensagem);
                   novaMessagem.setUpdate(this.update);
            }    
        }
    }
    
    public Vector<String> getMensagem(String nickName, String update){
        synchronized(this){
            if(mensagens.containsKey(nickName)){
               Mensagem novaMessagem = pedido.get(nickName);
               novaMessagem.setIdRegisto(idRegisto);
               novaMessagem.setMensagem(mensagem);
               }else{
               Mensagem novaMessagem = new Mensagem(nickName);
               pedido.put(nickName, novaMessagem);
               novaMessagem.setIdRegisto(idRegisto);
               novaMessagem.setMensagem(mensagem);
            }
            Vector<String> relatorio = new Vector<String> ();
            relatorio.add(this.mensagem);

            return relatorio;
            
        }
    }
    
    //metodo para guardar as mensagens
    //syncronized para não ser mais que uma thread a aceder ao código dentro desse bloco.
    
    ////////////////////////////////////////
    /// Because synchronization does hurt concurrency, 
    /// you don't want to synchronize any more code than is necessary to protect your data.
    /// So if the scope of a method is more than needed, you can reduce the 
    /// scope of the synchronized part to something less than a full method—to just a block.
    /////////////
    
    
    public void guardarMensagem(String nickName, String mensagem){
		synchronized (this){
			mensagens.put(nickName,mensagem);
		}
    }
    
    //vetor consultaMensagem com dados dos pedidos
    //o porque um vetor: A classe Vector implementa uma variedade crescente de objetos. Como um array (uma matriz), contém componentes que podem ser acedidos
    // usando um índice inteiro, consultamos pela enumeração
    
    public Vector<Mensagem> getPedidos (String update) {
		Vector<Mensagem> result = new Vector<Mensagem>();
		for (Enumeration<Mensagem> e = pedido.elements();e.hasMoreElements(); ){
			Mensagem element = e.nextElement();
			result.add(element);
		}
		return  result;
	}
    
    //metodo para imprimir as pedidos
    //pega no hashtable, retorna uma representação do objeto em forma de String com o metodo toString
    //imprime o conteudo no objeto resultado e retorna o valor 
    public String printMensagens (){
        
		String printMensagem = mensagens.toString();
		printMensagem=mensagem.substring(1,mensagem.length()-1);
		String resultado="";
		if (printMensagem.length()<3){
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
    //o seu id e seu nick, o pedido de pedido que envia, e a ultimo update/registo conhecida
    
    class Mensagem {
 
            private String nickname;
            private int idRegisto;
            private String mensagem;
            private int update;
        
            public Mensagem(int idRegisto, String nickname, String mensagem, int update) {
        
                this.nickname= nickname;
                this.idRegisto= idRegisto;
                this.mensagem=mensagem;
                this.update=update;
            }

            public Mensagem(String nickname) {
		this.nickname = nickname;
		this.update=0;
	}
        public int getUpdate() {
            return update;
        }

        public void setUpdate(int update) {
            this.update = update;
        }
            
            public Mensagem(){
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