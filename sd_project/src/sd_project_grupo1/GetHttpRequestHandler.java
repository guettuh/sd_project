
package sd_project_grupo1;

import java.io.*;
import java.net.*;
import java.util.*;

public class GetHttpRequestHandler extends Thread {
    Socket connection;
    MessageRequest message;
    User user;
    BufferedReader in;
    PrintWriter out;
    String findNickName = null;

    GetHttpRequestHandler(Socket connection, MessageRequest message) {
        this.connection = connection;
        this.message = message;

        try {
            this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.out = new PrintWriter(connection.getOutputStream());
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
            // msg = message from Client
            String msg = in.readLine();
            StringBuilder builder = new StringBuilder();
            String line = msg;
            int length = 0;
            //do while para apanhar o tamanho do content e atribuir ao lenght para ser usado no post.
            do {
                System.out.println(line);
                if (line.equals("")) {
                    break;
                }
                //serve para "apanhar" o tamanho da mensagem. 
                if (line.contains("Content-Length")) {
                    int idx = line.indexOf(":");
                    length = Integer.parseInt(line.substring(idx + 2, line.length()));
                }
                builder.append(line);
                line = in.readLine();
                
            } while (true);

            
            StringTokenizer tokens = new StringTokenizer(msg);
            String metodo = tokens.nextToken();

            if (metodo.equals("GET")) {
                response = "101\n";
                System.out.println("work");
                System.out.println(response);
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                out.println("\r\n");
                out.println("<!doctype html>\n" + "\n" 
                        + "<html lang=\"pt-pt\">\n" 
                        
                        + "<head>\n"
                        + "<meta charset=\"utf-8\">\n" + "<meta http-equiv=\"refresh\" content=\"30\">\n" 
                        + "    <title>Sistemas Distribuidos</title>\n" + "\n"
                        + "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\"/>\n"

                        + "</head>\n" + "\n" 
                        + "<body>");
                out.println(
                        "<div> <center> <br><h1><font face=Arial color= blue>SISTEMA DE RESPOSTA A AUDIENCIAS </font></h1>\n"
                                + "   <div> <h3><font face=Arial> Grupo 1 - Sistemas Distribuidos 2019/2020</font></div><br>"
                                + "      <div class=\"border\"rows=2 cols=\"10\">\n" + "        <div class=\"main\">\n"
                                + "            <div class=\"card mb-3\" style=\"min-height: 20rem;\">\n"
                                + "                 <br><h5> <br><br><br>Registe-se para poder enviar Mensagens!   </h5> </br>   "
                                + "                 <div class=\"card-body\"> "
                                + "                     <form method=\"post\" >\n"
                                + "                         NickName: <br> <input name=\"nickname\" rows=\"1\"></input>\n"
                                + "<br>\n" + "                         <br>\n"
                                + "                         <button type=\"submit\" value=\"mensagem\" class=\"btn btn-primary\">Submeter</button>\n"
                                + "                     </form>\n" + "                </div>\n" + "           </div>\n"
                                + "\n");
                out.println("<div class=\"row\">\n" + "  <div class=\"col-sm-4\">\n"

                        + "      <div class=\"card-body\">\n"
                        + "        <h5 class=\"card-title\">Utilizadores Registados</h5>\n"
                        + "        <div id=\"setTime\" class=\"card-body\">\n" + message.printUsers() + "      </div>\n"
                        + "    </div>\n" + "  </div>\n" + "  <div class=\"col-sm-8\">\n"
                        + "      <div class=\"card-body\">\n" + "        <h5 class=\"card-title\">Mensagens</h5>\n"
                        + "       <div id=\"setTime\" class=\"card-body\">\n" + message.printMessages()
                        + "       </div>\n" + "    </div>\n" + "  </div>\n" + "</div>" + "</div>\n"
                        + "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n"
                        + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\" integrity=\"sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh\" crossorigin=\"anonymous\"></script>\n"
                        + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" integrity=\"sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ\" crossorigin=\"anonymous\"></script>\n"
                        + "</body>\n" + "</html>");

            }

            else if (metodo.equals("POST")) {

                char[] body = new char[length];
                in.read(body, 0, length);
                String submition = new String(body);
                System.out.println(submition);
                int index = submition.indexOf("&");

                findNickName = submition.substring(0, submition.length());
                int idx2 = findNickName.indexOf("=");
                String nickname = findNickName.substring(idx2 + 1, findNickName.length());

                if (submition.contains("nickname=") && submition.contains("mensagem=")) {

                    String findMessage = submition.substring(index, submition.length());
                    int idx3 = findMessage.indexOf("=");
                    String messageUser;
                    messageUser = findMessage.substring(idx3 + 1, findMessage.length());
                    nickname = findNickName.substring(idx2 + 1, index);
                    user = new User(nickname);
                    message.saveMessage(user, messageUser);
                } else if (!submition.contains("messagem=")) {

                    user = new User(nickname);
                    message.saveUser(user);

                }

                response = "101\n";
                System.out.println("work");
                System.out.println(response);
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                out.println("\r\n");
                out.println("<!doctype html>\n" + "\n" + "<html lang=\"pt-pt\">\n" + "<head>\n"
                        + "    <meta charset=\"utf-8\">\n" 
                        + "    <meta http-equiv=\"refresh\" content=\"30\">\n"   
                        //+ "    <meta http-equiv=\"refresh\" content=\"30\">\n" //atualiza a pagina a cada 30segundos
                        + "    <title>Sistemas Distribuidos</title>\n" + "\n"
                        + "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\"/>\n"
                        + "</head>\n" + "<body>\n");
                out.println(
                        "<div> <center> <br><h1><font face=Arial color= blue>SISTEMA DE RESPOSTA A AUDIENCIAS </font></h1>\n"
                                + "   <div> <h3><font face=Arial> Grupo 1 - Sistemas Distribuidos 2019/2020</font></div><br>"
                                + "      <div class=\"border\"rows=2 cols=\"10\">\n" + "        <div class=\"main\">\n"
                                + "            <div class=\"card mb-3\" style=\"min-height: 20rem;\">\n"
                                + "                 <br><h5> <br><br><br>Comece já a falar, comece por uma Mensagem! :D   </h5> </br>   "
                                + "                 <div class=\"card-body\"> "
                                + "                     <form method=\"post\" >\n"
                                + "                         NickName: <br> <input disable name=\"nickname\" value=" + nickname
                                + " rows=\"1\"></input>\n" + "O seu id é: "+user.getIdRegisto()+" <br>\n" + "                         <br>\n"
                                + "                         <label for=\"mensagem\">Envie a sua mensagem:</label>\n"
                                + "<br>\n"
                                + "                         <textarea name=\"mensagem\" rows=\"2\"></textarea>\n"
                                + "<br>\n"
                                + "                         <button type=\"submit\" value=\"mensagem\" class=\"btn btn-primary\">Submeter</button>\n"
                                + "                     </form>\n" + "                </div>\n" + "           </div>\n"
                                + "\n");
                out.println("<div class=\"row\">\n" + "  <div class=\"col-sm-4\">\n"

                        + "      <div class=\"card-body\">\n"
                        + "        <h5 class=\"card-title\">Utilizadores Registados</h5>\n"
                        + "        <div id=\"setTime\" class=\"card-body\">\n" + message.printUsers() + "      </div>\n"
                        + "    </div>\n" + "  </div>\n" + "  <div class=\"col-sm-8\">\n"
                        + "      <div class=\"card-body\">\n" + "        <h5 class=\"card-title\">Mensagens</h5>\n"
                        + "       <div id=\"setTime\" class=\"card-body\">\n" + message.printMessages()
                        + "       </div>\n" + "    </div>\n" + "  </div>\n" + "</div>" + "</div>\n"

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
            System.out.println("Terminou a ligacao!");

        } catch (IOException e) {
            System.err.println("Ocorreu um erro na execução do servidor:" + e);
            System.exit(1);
        }
}
    }


