/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author filipemanso
 */
public class consultaMensagens {
 

        
    
   
        //Classe que guarda as informações de cada client que connecta com o servidor, nomeadamente
        //o seu id e seu nome, a ultima atualização conhecida
            private String nickname;
            private int idRegisto;
            private String mensagem;
        
            public consultaMensagens(int idRegisto, String nickname, String mensagem) {
        
                this.nickname= nickname;
                this.idRegisto= idRegisto;
                this.mensagem=mensagem;
            }
            
            public consultaMensagens(){
                this.idRegisto=0;
            }
            public String getNickname(){
                return this.nickname;
            }
            public void setNickname(String nickname){
                this.nickname=nickname;
            }
            public int getIdRegisto(int idRegisto){
                return this.idRegisto;
            }
            public void setIdRegisto(int idRegisto){
                this.idRegisto=idRegisto;
            }
            public void addIdRegisto(){
                this.idRegisto++;
            }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

        
    
}
  
