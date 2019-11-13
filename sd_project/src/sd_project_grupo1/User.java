package sd_project_grupo1;

public class User {
    private String nickName;
    private int idRegisto;

    public User(String nickName) {
        this.nickName = nickName;
        this.idRegisto=idRegisto;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getIdRegisto() {
        return idRegisto;
    }

    public void setIdRegisto(int idRegisto) {
        this.idRegisto = idRegisto;
    }
    
    
}
