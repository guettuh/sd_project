import java.net.*;
import java.io.*;

public class utilizadorServer {
    static int DEFAULT_PORT=8081;

    public static void main(String[] args) {
		int port=DEFAULT_PORT;
		Utilizador utilizador = new Utilizador();
		
		// Cria um ServerSocket e associa-o a porta especificada na variavel port
	
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
				
				GetUtilizadorRequestHandler t = new GetUtilizadorRequestHandler(ligacao, utilizador);
				t.start();
				
			} catch (IOException e) {
				System.out.println("Erro na execucao do servidor: "+e);
				System.exit(1);
			}
		}
}
}
	