package sd_project_grupo1;

import java.io.IOException;
import java.net.*;
import java.util.*;

//Implementa o run para a execução do serverSocket
//A interface Runnable deve ser implementada por qualquer classe cujas instâncias sejam executadas por um thread. 
//A classe deve definir um método sem argumentos chamado run.
public class Server implements Runnable {

    static final int DEFAULT_PORT = 8081;
    public int port;
    private final MessageRequest message;

    // Construtor da Classe Server
    public Server(int port, MessageRequest message) {
        this.port = port;
        this.message = message;
    }

    // Cria um ServerSocket e associa-o à porta especificada na variavel port
    @Override
    public void run() {
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
            System.err.println("erro ao criar socket servidor...");
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Servidor Iniciado! Á escuta na porta " + port);

        // Aguarda que seja estabelecida uma conexão. QUando isso acontece cria um
        // socket - connection
        while (true) {
            try {
                Socket connection = server.accept();
                GetHttpRequestHandler thread = new GetHttpRequestHandler(connection, message);
                thread.start();
            } catch (IOException e) {
                System.err.println("Erro na sua execução do Socket Servidor!" + e);
                System.exit(1);
            }
        }

    }

    public static void main(String[] args) {
        // ligação ao servidor
        int port = DEFAULT_PORT;
        MessageRequest message = new MessageRequest();

        // Cria uma conexao para o cliente
        Server server = new Server(port, message);
        Thread thread = new Thread(server);
        thread.start();
    }

}
