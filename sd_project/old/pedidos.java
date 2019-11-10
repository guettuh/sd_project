import java.io.*;
import java.net.*;
import java.util.*;

public class pedidos {

    // hashtable para guardar os pedidos (Mensagem) e as pedidos
    // mapeia chaves para valores. Qualquer objeto não nulo pode ser usado como uma
    // chave ou como um valor.

    private static Hashtable<String, Mensagem> mensagems = new Hashtable<String, Mensagem>();
    private static Hashtable<String, utilizador> listaUtilizador = new Hashtable<String, utilizador>();
    private static int idMensagem = 0;
    private static int idRegisto;
    private static String mensagem;

    public static Hashtable<String, Mensagem> getMensagems() {
        return mensagems;
    }

    
    public static Hashtable<String, utilizador> getUtilizadores() { 
        return listaUtilizador;
    }
     

    public static int getIdMensagem() {
        return idMensagem;
    }

    // metodo para guardar as mensagens
    // syncronized para não ser mais que uma thread a aceder ao código dentro desse
    // bloco.

    public void guardarMensagem(String nickName, String mensagem) {
        synchronized (this) {
            // solução avançada com esta verificação if
            if (listaUtilizador.contains(nickName)) {
                Mensagem novaMessagem = mensagems.get(nickName);
                novaMessagem.setMensagem(mensagem);
                novaMessagem.setIdMensagem(this.idMensagem + 1);
            } else {
                System.out.println("Registe-se primeiro!");
            }

        }
    }

  
    public void guardarUtilizador(String nickName) {
         synchronized (this) {
        utilizador utilizador = new utilizador(nickName);
        listaUtilizador.put(String.valueOf(utilizador.getIdRegisto()), utilizador);
    }
    }

    ////////////////////////////////////////
    /// Because synchronization does hurt concurrency,
    /// you don't want to synchronize any more code than is necessary to protect
    /// your data.
    /// So if the scope of a method is more than needed, you can reduce the
    /// scope of the synchronized part to something less than a full method—to just
    /// a block.
    /////////////


    public Vector<Mensagem> getMensagens(String idMensagem) {
        Vector<Mensagem> result = new Vector<Mensagem>();
        for (Enumeration<Mensagem> e = mensagems.elements(); e.hasMoreElements();) {
            Mensagem element = e.nextElement();
            result.add(element);
        }
        return result;
    }

    public Vector<utilizador> getUtilizador(String idRegisto) {
        Vector<utilizador> result = new Vector<utilizador>();
        for (Enumeration<utilizador> e = listaUtilizador.elements(); e.hasMoreElements();) {
            utilizador element = e.nextElement();
            result.add(element);
        }
        return result;
    }

    // metodo para imprimir as pedidos
    // pega no hashtable, retorna uma representação do objeto em forma de String com
    // o metodo toString
    // imprime o conteudo no objeto resultado e retorna o valor
    /*
     * public String printMensagens (){
     * 
     * 
     * 
     * ArrayList<String> lMensagem = new ArrayList();
     * 
     * 
     * for (int x = 0; x<mensagems.length();x++){ String mensagem=
     * mensagems[x].toString(); lMensagem.add(mensagem);
     * System.out.println(mensagem.length()); resultado+=mensagem.replace("=",":");
     * resultado+="<br>"; } return resultado; }
     */

    // Classe que guarda as informações de cada client que connecta com o servidor,
    // nomeadamente
    // o seu id e seu nick, o mensagems de mensagems que envia, e a ultimo
    // idMensagem/registo conhecida

    class Mensagem {

        private String nickname;
        private String mensagem;
        private int idMensagem;

        public Mensagem(String nickName, String mensagem, int idMensagem) {

            this.nickname = nickName;
            this.mensagem = mensagem;
            this.idMensagem = idMensagem;
        }

        public Mensagem(String nickName) {
            this.nickname = nickName;
            this.idMensagem = 0;
        }

        public int getIdMensagem() {
            return idMensagem;
        }

        public void setIdMensagem(int idMensagem) {
            this.idMensagem = idMensagem;
        }

        public Mensagem() {
            this.idMensagem = 0;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setNickname(String nickName) {
            this.nickname = nickName;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }

    class utilizador {

        private int idRegisto;
        private String nickName;

        public utilizador() {
        }

        public utilizador(String nickName) {
            this.idRegisto = listaUtilizador.size() + 1;
            this.nickName = nickName;
        }

        public int getIdRegisto() {
            return idRegisto;
        }

        public String getNickName() {
            return nickName;
        }

        // por incremental
        public void setIdRegisto(int idRegisto) {
            this.idRegisto = listaUtilizador.size() + 1;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

    }
}