/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_project_grupo1;

import java.util.*;


public class User {
    private int IDRegisto;
    private String nickName;
    private  HashMap<String, User> listUsers = new HashMap<String, User>();
    
   

    public User(String nickName) {
        this.nickName = nickName;
        
    }

   public HashMap<String, User> getUsers() {
    return   listUsers;
   }
   
   public void saveUser(User newUser){
        if(!listUsers.containsValue(newUser)){
            System.out.println("Não existe utilizador");
            User user = newUser;
            listUsers.put(String.valueOf(listUsers.size() + 1), user);
            System.out.println(listUsers.containsValue(user));
        }else{
            System.out.println("Já existe utilizador");
        }
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

    

    public int getIDRegisto(User user) {
        return listUsers.get(user).hashCode();
    }
    
    public String getNickName(){
        return nickName;
    }
}
