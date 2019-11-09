import java.io.*;
import java.net.*;
import java.util.*;

public class pedidos {

    // hashtable para guardar os pedidos (Mensagem) e as pedidos
    // mapeia chaves para valores. Qualquer objeto não nulo pode ser usado como uma
    // chave ou como um valor.

    private static Hashtable<String, Mensagem> mensagems = new Hashtable<String, Mensagem>();
    private static ArrayList<utilizador> listaUtilizador = new ArrayList<utilizador>();
    private static int idMensagem = 0;
    private static int idRegisto;
    private static String mensagem;

    public static Hashtable<String, Mensagem> getMensagems() {
        return mensagems;
    }

    /*
     * public static Hashtable<String, String> getUtilizadores() { return mensagens;
     * }
     */

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

    // o objeto "nova menssagem" é o mensagems de consulta de mensagem. retorna a
    // lista
    public int guardarUtilizador(String nickName) {
        utilizador utilizador = new utilizador(nickName);
        listaUtilizador.add(utilizador);
        return idRegisto;
    }

    // metodo para guardar as mensagens
    // syncronized para não ser mais que uma thread a aceder ao código dentro desse
    // bloco.

    ////////////////////////////////////////
    /// Because synchronization does hurt concurrency,
    /// you don't want to synchronize any more code than is necessary to protect
    //////////////////////////////////////// your data.
    /// So if the scope of a method is more than needed, you can reduce the
    /// scope of the synchronized part to something less than a full method—to just
    //////////////////////////////////////// a block.
    /////////////

    // vetor consultaMensagem com dados dos pedidos
    // o porque um vetor: A classe Vector implementa uma variedade crescente de
    // objetos. Como um array (uma matriz), contém componentes que podem ser
    // acedidos
    // usando um índice inteiro, consultamos pela enumeração

    /*
     * public Vector<String> getUtilizadores () { Vector<String> result = new
     * Vector<String>(); for (Enumeration<Mensagem> e =
     * mensagems.elements();e.hasMoreElements(); ){ Mensagem element =
     * e.nextElement(); result.add(element.getNickname()); } return result; }
     */

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
}