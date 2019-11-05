import java.net.*;
import java.io.*;
import java.util.*;

//implementa o run para a execução do serverSocket
//A interface Runnable deve ser implementada por qualquer classe cujas instâncias sejam executadas por um thread. 
//A classe deve definir um método sem argumentos chamado run.

public class httpServer implements Runnable{
    static int DEFAULT_PORT=8081;
    public int port;
    private mensagens mensagem;
    
    public httpServer(int port, mensagens mensagem){
		this.port=port;
                this.mensagem=mensagem;
    }
		
		// Cria um ServerSocket e associa-o a porta especificada na variavel port
	public void run(){
		ServerSocket servidor = null; 
	
		try	{ 
			servidor = new ServerSocket(port);
		} catch (Exception e) { 
			System.err.println("erro ao criar socket servidor...");
			e.printStackTrace();
			System.exit(-1);
		}

		System.out.println("Servidor a' espera de ligacoes no porto " + port);
		
		while(true) {
			try {
				
				Socket ligacao = servidor.accept();
				
				GetMensagensRequestHandler t = new GetMensagensRequestHandler(ligacao, mensagem);
				t.start();
				
			} catch (IOException e) {
				System.out.println("Erro na execucao do servidor: "+e);
				System.exit(1);
			}
		}
}
        
        public static void main(String[] args) {
        //ligação ao servidor
            int port=DEFAULT_PORT;
            mensagens mensagem = new mensagens();
		try {
			ServerSocket serverConnect = new ServerSocket(port);
			System.out.println("Server started.\nListening for connections on port : " + port + " ...\n");
			
			// we listen until user halts server execution
			while (true) {
				httpServer myServer = new httpServer(port, mensagem);
				
				// create dedicated thread to manage the client connection
				Thread thread = new Thread(myServer);
				thread.start();
			}
			
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
                
	}
}
	