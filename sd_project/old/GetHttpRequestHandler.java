 out.println(“<!doctype html>\n”
                        + “\n”
                        + “<html lang=\“en\“>\n”
                        + “<head>\n”
                        + ”    <meta charset=\“utf-8\“>\n” + “\n” + ”    <title>Chat</title>\n” + “\n”
                        + ”    <link rel=\“stylesheet\” href=\“https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\” crossorigin=\“anonymous\“/>\n”
                        + “</head>\n”
                        + “<body>\n”);
                         out.println(“<div> <center> <br><h1><font face=Arial color= blue>SISTEMA DE RESPOSTA A AUDIENCIAS </font></h1>\n”
                        + ”   <div> <h3><font face=Arial> Grupo 1 - Sistemas Distribuidos 2019/2020</font></div><br>”
                        +”      <div class=\“border\“rows=2 cols=\“10\“>\n”
                        + ”        <div class=\“main\“>\n”
                        + ”            <div class=\“card mb-3\” style=\“min-height: 20rem;\“>\n”
                        + ”                 <br><h5> <br><br><br>Registe-se para poder enviar Mensagens!   </h5> </br>   ”
                        + ”                 <div class=\“card-body\“> ”
                        + ”                     <form method=\“post\” >\n”
                        + ”                         NickName: <br> <input name=\“nome\” value=” + nickname +” rows=\“1\“></input>\n” + “<br>\n”
                        + ”                         <br>\n”
                        + ”                         <label for=\“mensagem\“>Envie a sua mensagem:</label>\n” + “<br>\n”
                        + ”                         <textarea name=\“mensagem\” rows=\“2\“></textarea>\n” + “<br>\n”
                        + ”                         <button type=\“submit\” value=\“mensagem\” class=\“btn btn-primary\“>Submeter</button>\n”
                        + ”                     </form>\n”
                        + ”                </div>\n”
                        + ”           </div>\n” + “\n”);
               out.println(“<div class=\“row\“>\n”
                       + ”  <div class=\“col-sm-4\“>\n”
                       +”      <div class=\“card-body\“>\n”
                       +”        <h5 class=\“card-title\“>Utilizadores Registados</h5>\n”
                       +”        <div id=\“setTime\” class=\“card-body\“>\n” + message.printUsers()
                       + ”      </div>\n”
                       +”    </div>\n”
                       +”  </div>\n”
                       +”  <div class=\“col-sm-8\“>\n”
                       +”      <div class=\“card-body\“>\n”
                       +”        <h5 class=\“card-title\“>Mensagens</h5>\n”
                       + ”       <div id=\“setTime\” class=\“card-body\“>\n” + message.printMessages()
                       + ”       </div>\n”
                       +”    </div>\n”
                       +”  </div>\n”
                       +“</div>”
                               +“</div>\n”
                        + “<script src=\“https://code.jquery.com/jquery-3.2.1.slim.min.js\” integrity=\“sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\” crossorigin=\“anonymous\“></script>\n”
                        + “<script src=\“https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\” integrity=\“sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh\” crossorigin=\“anonymous\“></script>\n”
                        + “<script src=\“https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\” integrity=\“sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ\” crossorigin=\“anonymous\“></script>\n”
                        + “</body>\n” + “</html>“);