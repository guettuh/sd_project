import java.io.*;
import java.net.*;
import java.util.*;

public class GetHttpRequestHandler extends Thread {
    Socket ligacao;
    pedidos mensagem;
    BufferedReader in;
    PrintWriter out;
    // BufferedReader fr;

    public GetHttpRequestHandler(Socket ligacao, pedidos mensagem) {
        this.ligacao = ligacao;
        this.mensagem = mensagem;
        try {

            this.in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            this.out = new PrintWriter(ligacao.getOutputStream());

            // this.fr =new BufferedReader (new FileReader(ficheiro));

        } catch (IOException e) {
            System.out.println("Erro na execucao do servidor: " + e);
            System.exit(1);
        }
    }

    /*
     * public void run() { try{ if(){
     * 
     * }else{ //caso nao seja nenhum dos métodos referidos acima
     * out.println("201;method not found"); }
     * //out.println("<p> Hello world </p>");/* //Verificar resultado se 'get'
     * out.flush(); in.close(); out.close(); ligacao.close(); }
     * 
     * catch(IOException e) { System.out.println("Erro na execucao do servidor: " +
     * e); System.exit(1); }}
     */

}
