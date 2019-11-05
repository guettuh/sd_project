import java.net.*;
import java.io.*;
import java.util.*;

public class utilizador{

    private String idRegisto;
    private String nickName;
    
        public utilizador(){
        }

        public utilizador(String idRegisto, String nickName){
            this.idRegisto=idRegisto;
            this.nickName=nickName;
        }

    public String getIdRegisto() {
        return idRegisto;
    }

    public String getNickName() {
        return nickName;
    }

    //por incremental
    public void setIdRegisto(String idRegisto) {
        this.idRegisto = idRegisto;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

	private static Hashtable<String, utilizador> listaUtilizadores = new Hashtable<String, utilizador>();

    public static void setListaUtilizadores(Hashtable<String, utilizador> listaUtilizadores) {
        utilizador.listaUtilizadores = listaUtilizadores;
    }

    public static Hashtable<String, utilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }
	
	

    
}