import java.net.*;
import java.io.*;
import java.util.*;

public class GetPedidosRequestHandler extends Thread {
	Socket ligacao;
	Pedidos pedidos;
	BufferedReader in;
	PrintWriter out;
	BufferedReader fr;
	static final String ficheiro ="C:\\Users\\Rui_P\\Desktop\\SDTrabalho\\FicheiroHttp.html";

	public GetPedidosRequestHandler(Socket ligacao, Pedidos pedidos) {
		this.ligacao = ligacao;
		this.pedidos = pedidos;
		try
		{	// cria uma stream para ler os dados que chegam do socket
			this.in = new BufferedReader (new InputStreamReader(ligacao.getInputStream()));
			
			//cria uma PrintStream para escrever para o socket
			this.out = new PrintWriter(ligacao.getOutputStream());

			this.fr =new BufferedReader (new FileReader(ficheiro));

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
			String line=msg;
			String mensa = builder.toString();
			StringTokenizer tokens = new StringTokenizer(msg);
			String metodo = tokens.nextToken();
			System.out.println(msg);



			//out.println("<p> Hello world </p>");/*
			//Verificar resultado se 'get'
			if (metodo.equals("get")) {
				response="";
				//response = "101\n";
				String nome  = tokens.nextToken();
				String atualizacao = tokens.nextToken();
				Vector<String> resultado = pedidos.getResultado(nome,atualizacao);
				String atual = String.valueOf(pedidos.getAtualizacoes());
				//response += resultado.size() + "\n";
				for (Iterator<String> it = resultado.iterator(); it.hasNext();){
					String next = it.next(); 
					response += next + " ";
				}
				response+=atual;
				//System.out.println(response);
				out.println(response);
			}
			//atualizar resultado se 'set'
			else if(metodo.equals("set")) {
				response = "";
				String nome = tokens.nextToken();
				String golosEquipa1 = tokens.nextToken();
				String golosEquipa2 = tokens.nextToken();

				String atualizacao = tokens.nextToken();
				pedidos.setResultado(golosEquipa1,golosEquipa2,nome,atualizacao);
				Vector<String> resultado = pedidos.getResultado(nome,atualizacao);
				//response += resultado.size() + "\n";
				for (Iterator<String> it = resultado.iterator(); it.hasNext();){
					String next = it.next(); 
					response += next + " ";
				}
				response+= String.valueOf(pedidos.getAtualizacoes());
				System.out.println(response);
				out.println(response);
			}
				else{
				//caso nao seja nenhum dos métodos referidos acima
				out.println("201;method not found");
			}
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