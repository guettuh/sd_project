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
        
    }

   public ArrayList<User> getUsers() {
    return   listUsers;
   }
   
   public void saveUser(String nickName){
        User user = new User(nickName);
        listUsers.add(user);
    }
 
   public ArrayList<String> getUsersString(){
       ArrayList<String> listUsersString = new ArrayList<String>();
       
       for(int i=0; i<listUsers.size(); i++ ){
            String user = listUsers.get(i).toString();
            listUsersString.add(user);
       }
        return listUsersString;
   }
   

    

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static void setListUsers(ArrayList<User> listUsers) {
        User.listUsers = listUsers;
    }
    
    public String getNickName(){
        return nickName;
    }
}
