
package sd_project_grupo1;

import java.io.*;
import java.net.*;
import java.util.*;

public class GetHttpRequestHandler extends Thread {
    Socket connection;
    MessageRequest message;
    User users;
    BufferedReader in;
    PrintWriter out;
    BufferedReader fr;
    static final String ficheiro = "/Users/filipemanso/Desktop/Exemplos_códigos_java/paginaHttp.html";


    GetHttpRequestHandler(Socket connection, MessageRequest message) {
        this.connection = connection;
        this.message = message;

        try {
            this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.out = new PrintWriter(connection.getOutputStream());
            this.fr =new BufferedReader (new FileReader(ficheiro));
        } catch (IOException e) {
            System.err.println("Ocorreu um erro na execução do Servidor: " + e);
            System.exit(1);
        }

    }

    public void run() {
        try {
            System.out.println("A conexão foi aceite no endereço " + connection.getInetAddress() + " na porta "
                    + connection.getPort());

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
            
            StringTokenizer tokens = new StringTokenizer(msg);
            String metodo = tokens.nextToken();
            
                if (msg.equals("GET / HTTP/1.1")) {
                    response = "101\n";
                    System.out.println("work");
                    System.out.println(response);
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("\r\n");
                    out.println("<!doctype html>\n" + "\n" + "<html lang=\"en\">\n" + "<head>\n"
                            + "    <meta charset=\"utf-8\">\n" + "\n" + "    <title>Chat</title>\n" + "\n"
                            + "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\"/>\n"
                            + "</head>\n" + "\n" 
                            + "<body>");
                    out.println("        </div>\n" 
                            + "    </div>\n" 
                            + "    <div class=\"border\"rows=4 cols=\"10\">\n"
                            + "        <div class=\"\">\n"
                            + "            <div class=\"card mb-3\" style=\"min-height: 20rem;\">\n"
                            + "                <div class=\"card-body\">\n" + message.printUsers()
                            + "                </div>\n"
                            + "            </div>\n" 
                            + "\n");
                    out.println("             <br>\n" 
                            + "        </div>\n" 
                            
                            + "        <div class=\"form\">\n"
                            + "           <div class=\"form-group\" >\n" 
                            + "            <form method=\"post\" >\n"
                            + "                NickName: <br> <input name=\"nome\" rows=\"1\"></input>\n" 
                            + "<br>\n"
                            + "<br>\n" 
                            + "                <button type=\"submit\" value=\"mensagem\" class=\"btn btn-primary\">Submeter</button>\n"
                            + "            </form>\n" 
                            + "        </div>\n" 
                            + "        </div>\n" 
                            + "    </div>\n"
                            + "</div>\n" 
                            + "\n"
                            + "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n"
                            + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\" integrity=\"sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh\" crossorigin=\"anonymous\"></script>\n"
                            + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" integrity=\"sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ\" crossorigin=\"anonymous\"></script>\n"
                            + "</body>\n" 
                            + "</html>");
                
                } 
                
                else if (msg.equals("POST / HTTP/1.1")) {
                    
                    char[] corpo = new char[contLength];
                in.read(corpo,0,contLength);
                String corpoS = new String(corpo);
                System.out.println(corpoS);
                int idz = corpoS.indexOf("&");


                if(corpoS.contains("&")){
                    String nomeX = corpoS.substring(0,idz);
                    int idn = nomeX.indexOf("=");
                    String nome = nomeX.substring(idn+1,nomeX.length());

                    String mensagemX = corpoS.substring(idz+1,corpoS.length());
                    int idm = mensagemX.indexOf("=");
                    String mensagem = mensagemX.substring(idm+1,mensagemX.length());
                    
                    message.saveMessage(nome, mensagem);
                } else {
                    String nomeX = corpoS.substring(0,corpoS.length());
                    int idn = nomeX.indexOf("=");
                    String nome = nomeX.substring(idn+1,nomeX.length());
                    
                    message.saveUser(nome);
                }   
                
                
                    
                  
                    
                    response = "101\n";
                    System.out.println("work");
                    System.out.println(response);
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("\r\n");
                    out.println("<!doctype html>\n" 
                            + "\n" 
                            + "<html lang=\"en\">\n" 
                            + "<head>\n"
                            + "    <meta charset=\"utf-8\">\n" 
                            + "\n" 
                            + "    <title>Chat</title>\n" 
                            + "\n"
                            + "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\"/>\n"
                            + "</head>\n" 
                            + "\n" 
                            + "<body>");
                    out.println("        </div>\n" 
                            + "    </div>\n" 
                            + "    <div class=\"border\"rows=4 cols=\"10\">\n"
                            + "        <div class=\"\">\n"
                            + "            <div class=\"card mb-3\" style=\"min-height: 20rem;\">\n"
                            + "                <div class=\"card-body\">\n" + message.printUsers()+
                            "                </div>\n"
                            + "            </div>\n" 
                                    + "\n");
                    out.println("             <br>\n" 
                            + "        </div>\n" 
                            + "        <div class=\"\">\n"
                            + "            <div class=\"card mb-3\" style=\"min-height: 20rem;\">\n"
                            + "                <div class=\"card-body\">\n" + message.printMessages()
                            + "                </div>\n"
                            + "            </div>\n" 
                            + "\n");
                    out.println("             <br>\n" 
                            + "        </div>\n"
                            + "        <div class=\"form\">\n"
                            + "           <div class=\"form-group\" >\n" 
                            + "            <form method=\"post\" >\n"
                            + "                NickName: <br> <input name=\"nome\" rows=\"1\"></input>\n" 
                            + "<br>\n"
                            + "<br>\n" 
                            + "<label for=\"mensagem\">Tell us your story:</label>\n" 
                            + "<br>\n"
                            + "                <textarea name=\"mensagem\" rows=\"2\"></textarea>\n" + "<br>\n"
                            + "                <button type=\"submit\" value=\"mensagem\" class=\"btn btn-primary\">Submeter</button>\n"
                            + "            </form>\n" + "        </div>\n" + "        </div>\n" + "    </div>\n"
                            + "</div>\n" + "\n"
                            + "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n"
                            + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\" integrity=\"sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh\" crossorigin=\"anonymous\"></script>\n"
                            + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" integrity=\"sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ\" crossorigin=\"anonymous\"></script>\n"
                            + "</body>\n" + "</html>");
                
                
                } else {
                    out.println("201:method not found");

                }

                System.out.println("from client: " + builder);
                // Write a sentence after reading.
                out.flush();
                in.close();
                out.close();
                connection.close();
            
        } catch (IOException e) {
            System.err.println("Ocorreu um erro na execução do servidor:" + e);
            System.exit(1);
        }
    }

}
