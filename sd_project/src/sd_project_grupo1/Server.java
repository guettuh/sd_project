/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_project_grupo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Implementa o run para a execução do serverSocket
//A interface Runnable deve ser implementada por qualquer classe cujas instâncias sejam executadas por um thread. 
//A classe deve definir um método sem argumentos chamado run.
public class Server implements Runnable {
    
    static final int DEFAULT_PORT = 8081;
    static final String DEFAULT_FILE = "index.html";
    static final String FILE_NOT_FOUND = "404.html";
    public int port;
    private final MessageRequest message;
    

    //Construtor da Classe Server
    public Server(int port, MessageRequest message) {
        this.port = port;
        this.message = message;
    }
    
    //Cria um ServerSocket e associa-o à porta especificada na variavel port
    public void run() {
        ServerSocket server = null;
        
        try{
            server = new ServerSocket(port);
            System.out.println("Servidor Iniciado! Á escuta na porta"+port);
            
            //Aguarda que seja estabelecida uma conexão. QUando isso acontece cria um socket - connection
            while(true){
                try{
                    Socket connection = server.accept();
                    GetHttpRequestHandler thread = new GetHttpRequestHandler(connection, message);
                    thread.start();
                }catch (IOException e){
                    System.err.println("Erro na sua execução do Socket Servidor!"+e);
                    System.exit(1);
                }
            }
            
        }catch (Exception e){
            System.err.println("Não foi possível iniciar o Servidor. Erro ao criar o Socket Servidor!");
            System.exit(-1);
        }
    }
  
    
    public static void main(String[] args) {
        //ligação ao servidor
        int port = DEFAULT_PORT;
        MessageRequest message = new MessageRequest();
        
        //Cria uma conexao para o cliente
        Server server = new Server(port, message);
        Thread thread = new Thread(server);
        thread.start();    
    }

}
    
