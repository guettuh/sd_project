import java.net.*;
import java.io.*;

public class httpServer implements Runnable {

    static final int DEFAULT_PORT=8080;
    static final int HTTP_PORT=8081;
    public int port;
    private Mensagens mensagens;
    public httpServer(int port, mensagens mensagens){
        this.pedidos=pedidos;
        this.port=port;
    }
    public void run(){
        ServerSocket servidor = null;
        try	{
            int porto = port;
            servidor = new ServerSocket(porto);
        } catch (Exception e) {
            System.err.println("erro ao criar socket servidor...");
            e.printStackTrace();
            System.exit(-1);
        }
        while(true) {
            try {
                //	Aguarda que seja estabelecida alguma ligacao e quando isso acontecer cria um socket
                // chamado ligacao

                Socket ligacao = servidor.accept();
                if (port==8080){
                    HttpConnectionsRequestHandler t = new HttpConnectionsRequestHandler(ligacao, mensagens);
                    t.start();
                }
                else if (port==8081){
                    GetPedidosRequestHandler v = new GetPedidosRequestHandler(ligacao, mensagens);
                    v.start();
                }


            } catch (IOException e) {
                System.out.println("Erro na execucao do servidor: "+e);
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        int port=DEFAULT_PORT;
        int http_port=HTTP_PORT;
        Mensagens mensagens = new mensagens();

        httpServer servidorHttp = new httpServer(http_port,mensagens);
        new Thread(servidorHttp).start();
        httpServer servidor = new httpServer(port,mensagens);
        new Thread(servidor).start();




        //escrever mensagem a dizer "Servidor a' espera de ligacoes no porto ..."

        System.out.println("Servidor a' espera de ligacoes no porto " + HTTP_PORT+"+"+port);
        System.out.println(mensagens.getNickName()+" "+mensagens.getIdRegisto()+" "+mensagens.getAtualizacoes());


    }}
