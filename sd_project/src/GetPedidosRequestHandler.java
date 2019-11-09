import java.net.*;
import java.io.*;
import java.util.*;

public class GetPedidosRequestHandler extends Thread {
	Socket ligacao;
	pedidos mensagem;
	BufferedReader in;
	PrintWriter out;
	BufferedReader fr;

	// adaptar caminho para windows ou mac
	// windows leva duas barras "\\" em susbtiuiçao da normal "/"
	//static final String ficheiro = "paginaHttp.html";

	public GetPedidosRequestHandler(Socket ligacao, pedidos mensagem) {
		this.ligacao = ligacao;
		this.mensagem = mensagem;
		try {

			this.in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

			this.out = new PrintWriter(ligacao.getOutputStream());

			//this.fr = new BufferedReader(new FileReader(ficheiro));

		} catch (IOException e) {
			System.out.println("Erro na execucao do servidor: " + e);
			System.exit(1);
		}
	}
	
	 public void run() {
        try {


            //verifica os resultados enviados pelo cliente e escolhe que função desempenhar de acordo com o método enviado pelo cliente
            String response;
            //msg = mensagem do Cliente
            String msg = in.readLine();
            StringBuilder builder = new StringBuilder();
            String line = msg;
            int contLength=0;
            do {
                System.out.println(line);
                if (line.equals("")){ break;}
                if (line.contains("Content-Length")){
                    int idx = line.indexOf(":");
                    contLength = Integer.parseInt(line.substring(idx+2,line.length()));
                }
                builder.append(line);
                line = in.readLine();
            }
            while (true);


            String mensa = builder.toString();
            StringTokenizer tokens = new StringTokenizer(msg);
            String metodo = tokens.nextToken();


            if (msg.equals("GET / HTTP/1.1")) {
                
            } else if ((msg.equals("POST / HTTP/1.1"))) {
                
            }else{
                //caso nao seja nenhum dos métodos referidos acima
                out.println("201;method not found");
            }
            //out.println("<p> Hello world </p>");/*
            //Verificar resultado se 'get'
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