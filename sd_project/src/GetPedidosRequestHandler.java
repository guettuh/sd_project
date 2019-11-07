import java.net.*;
import java.io.*;
import java.util.*;

public class GetPedidosRequestHandler extends Thread {
	Socket ligacao;
	pedidos mensagem;
	BufferedReader in;
        PrintWriter out;
        BufferedReader fr;
        
        //adaptar caminho para windows ou mac 
        //windows leva duas barras "\\" em susbtiuiçao da normal "/" 
	static final String ficheiro ="/src/paginaHttp.html";
  

    public GetPedidosRequestHandler(Socket ligacao, pedidos mensagem) {
		this.ligacao = ligacao;
		this.mensagem = mensagem;
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
			System.out.println("Aceitou ligacao de cliente no endereco ");
			
			String response;
			String msg = in.readLine();
			System.out.println("Request=" + msg);
			
                        StringTokenizer tokens = new StringTokenizer(msg);
			String metodo = tokens.nextToken();
			
                        
                        if (metodo.equals("get")) {
				response = "101\n";
                                String nickName =tokens.nextToken();
                                String update = tokens.nextToken();
                                Vector<String> result = pedidos.getMensagens(nickName, update);
				for (Iterator<String> it = result.iterator(); it.hasNext();){
					String next = it.next(); 
                    response += next + ";";
				}
				System.out.println(response);
				out.println(response);
			}
			else //if (metodo.equals("set")
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