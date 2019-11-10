/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_project_grupo1;

import java.util.ArrayList;


public class User {
    private int IDRegisto;
    private String nickName;
    private static ArrayList<User> listUsers = new ArrayList<User>();
    
   

    public User(String nickName) {
        this.nickName = nickName;
        this.IDRegisto = listUsers.size() +1;
    }

   public ArrayList<User> getUsers() {
    return   listUsers;
   }

   public ArrayList<String> getUsersString(){
       ArrayList<String> listUsersString = new ArrayList<String>();
       
       for(int i=0; i<listUsers.size(); i++ ){
            String user = listUsers.get(i).toString();
            listUsersString.add(user);
       }
        return listUsersString;
   }
   
    ArrayList<String> getUsers(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getIDRegisto(){
        return IDRegisto;
    }
    
    public String getNickName(){
        return nickName;
    }
}
