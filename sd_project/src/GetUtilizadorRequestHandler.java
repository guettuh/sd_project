import java.net.*;
import java.io.*;
import java.util.*;

public class GetUtilizadorRequestHandler extends Thread {
	Socket ligacao;
	utilizador utilizador;
	BufferedReader in;
    PrintWriter out;
    
    //adaptar caminho para windows ou mac 
    //windows leva duas barras "\\" em susbtiuilçao da normal "/" 
	static final String ficheiro ="/Users/filipemanso/Desktop/Exemplos_códigos_java/paginaHttp.html";

    public GetUtilizadorRequestHandler(Socket ligacao, utilizador utilizador) {
		this.ligacao = ligacao;
		this.utilizador = utilizador;
		try
		{	
		this.in = new BufferedReader (new InputStreamReader(ligacao.getInputStream()));
			
            this.out = new PrintWriter(ligacao.getOutputStream());

            this.fr =new BufferedReader (new FileReader(ficheiro));

		} catch (IOException e) {
			System.out.println("Erro na execucao do servidor: " + e);
			System.exit(1);
		}
    }
    public void run() {                
		try {
			System.out.println("Aceitou ligacao de cliente no endereco " + ligacao.getInetAddress() + " na porta " + ligacao.getPort());
			
			String response;
			String msg = in.readLine();
			System.out.println("Request=" + msg);
			
			StringTokenizer tokens = new StringTokenizer(msg);
			String metodo = tokens.nextToken();
			if (metodo.equals("get")) {
				response = "101\n";
                                String idRegisto = tokens.nextToken();
                                String nickName = tokens.nextToken();
				for (Iterator<String> it = .iterator(); it.hasNext();){
					String next = it.next(); 
                    response += next + ";";
				}
				System.out.println(response);
				out.println(response);
			}
			else 
				out.println("201;method not found");
				
			out.flush();
			in.close();
			out.close();
			ligacao.close();
		} catch (IOException e) {
			System.out.println("Erro na execucao do servidor: " + e);
			System.exit(1);
		}
	}
}