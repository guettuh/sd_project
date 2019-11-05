import java.net.*;
import java.io.*;

//acesso via linha de comandos, recebe um argumento "nickName" e liga-se ao servidor
//não faz mais nada
//tem que devolver um id do servidor e terminar ligação. FALTA FAZER
//cópia e adaptação da ficha anterior

public class utilizadorClient{
    static int porta=8081;
    static final String host="127.0.0.1";

    
    public static void main(String[] args) {
	String servidor=host;
	int porto=porta;
        String contador = "0";

        if (args.length != 1) {
            System.out.println("Insira o seu nickName...");
            System.exit(-1);}
        
        //Cria um objecto da classe InetAddress com base no nome do servidor
		InetAddress endereco = null;
		try {
			endereco = InetAddress.getByName(servidor);
		} catch (UnknownHostException e) {
			System.out.println("Endereco desconhecido: "+e);
			System.exit(-1);
		}

		System.out.println("Vai ligar ao porto "+porto+" da maquina "+endereco.getHostName());

		//Cria um socket chamada ligacao e estabelece a ligacao indicada
		Socket ligacao = null;

		try {
			ligacao = new Socket(endereco, porto);
		} catch (Exception e) {
			System.err.println("erro ao criar socket...");
			e.printStackTrace();
			System.exit(-1);
		}

		// Escreve mensagem a dizer "ligado ao porto ... no endereco .. "
		System.out.println("ligado ao porto " + porto + " no endereco " + endereco.getHostName());

		try {
			//cria uma stream para ler os dados que chegam do socket
			BufferedReader in = new BufferedReader (new InputStreamReader(ligacao.getInputStream()));

			//cria uma PrintStream para escrever para o socket
			PrintWriter out = new PrintWriter(ligacao.getOutputStream());

			// constroi o pedido para enviar ao servidor
			String request = "get" + " " + args[0];
			//teste
			System.out.println(request);

			//envia o pedido ao servidor
			out.println(request);
			out.flush();

			//espera pela resposta
			String msg;
			while ((msg = in.readLine())!= null)
				System.out.println("Resposta do servidor: " + msg);

			//termina a ligacao
			ligacao.close();
			System.out.println("Terminou a ligacao!");
		} catch (IOException e) {
			System.out.println("Erro ao comunicar com o servidor: "+e);
			System.exit(1);
		}
    }
}