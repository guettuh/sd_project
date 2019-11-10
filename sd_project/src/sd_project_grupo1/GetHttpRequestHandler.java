/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_project_grupo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author melodymonteiro
 */
public class GetHttpRequestHandler extends Thread{
    Socket connection;
    MessageRequest message;
    User users;
    BufferedReader in;
    PrintWriter out;
    
    GetHttpRequestHandler(Socket connection, MessageRequest message) {
        this.connection = connection;
        this.message = message;
        
        try{
            this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.out = new PrintWriter(connection.getOutputStream());
        }catch(IOException e){
            System.err.println("Ocorreu um erro na execução do Servidor: "+e);
            System.exit(1);
        }
       
    }
    
    public void run(){
        try{
            System.out.println("A conexão foi aceite no endereço "+connection.getInetAddress()+" na porta "+ connection.getPort());
            
            String response;
            String msg = in.readLine();
            System.out.println("Request= "+msg);
            
            StringTokenizer tokens = new StringTokenizer(msg);
            String method = tokens.nextToken();
            
            if(method.equals("get")){
                response = "101\n";
                String  user = tokens.nextToken();
                ArrayList<String> listUsers = users.getUsers(user);
                response+= listUsers.size() + "\n";
                
                System.out.println(response);
                out.println(response);
            }else{
                out.println("201:method not found");
                out.flush();
                in.close();
                connection.close();
                
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro na execução do servidor:"+e);
            System.exit(1);
        }
    }
    
    
    
}
