import java.net.*;
import java.io.*;
import java.util.*;

//implementa o run para a execução do serverSocket
//A interface Runnable deve ser implementada por qualquer classe cujas instâncias sejam executadas por um thread. 
//A classe deve definir um método sem argumentos chamado run.

public class httpServer implements Runnable{
    static int DEFAULT_PORT=8081;
    public int porta;
    private pedidos mensagem;
    
    public httpServer(int port, pedidos mensagem){
		this.porta=port;
                this.mensagem=mensagem;
    }
		
		// Cria um ServerSocket e associa-o a porta especificada na variavel port
	public void run(){
		ServerSocket servidor = null; 
	
		try	{ 
			servidor = new ServerSocket(porta);
		} catch (Exception e) { 
			System.err.println("erro ao criar socket servidor...");
			e.printStackTrace();
			System.exit(-1);
		}

		System.out.println("Servidor a' espera de ligacoes no porto " + porta);
		
		while(true) {
			try {
				
				Socket ligacao = servidor.accept();
				
				GetPedidosRequestHandler t = new GetPedidosRequestHandler(ligacao, mensagem);
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
            pedidos mensagem = new pedidos();
		
			httpServer server = new httpServer(port, mensagem);
                        new Thread(server).start(); 
			}
		
                
	}

	