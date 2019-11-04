import java.io.*;
import java.net.*;
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

    private static Hashtable<String, IPInfo> presentIPs = new Hashtable<String, IPInfo>();
	private static int cont = 0;

    ////////////////////////////////////////////
    // do 1 projeto para registo das presenças//
    ////////////////////////////////////////////
    public Vector<String> getPresences(String IPAddress) {
		
		long actualTime = new Date().getTime();
		cont = cont+1;
		
		System.out.println("cont = "+ cont);
		
		//Assume-se que o IP e valido!!!!!
		synchronized(this) {
			if (presentIPs.containsKey(IPAddress)) {
				IPInfo newIp = presentIPs.get(IPAddress);
				newIp.setLastSeen(actualTime);
			}
			else {
				IPInfo newIP = new IPInfo(IPAddress, actualTime);
				presentIPs.put(IPAddress,newIP);
			}
		}
		return getIPList();
	}
	
	private Vector<String> getIPList(){
		Vector<String> result = new Vector<String>();
		for (Enumeration<IPInfo> e = presentIPs.elements(); e.hasMoreElements(); ) {
			IPInfo element = e.nextElement();
			if (!element.timeOutPassed(180*1000)) {
				result.add(element.getIP());
			}
		}
		return result;
	}
}

class IPInfo {
	
	private String ip;
	private long lastSeen;

	public IPInfo(String ip, long lastSeen) {
		this.ip = ip;
		this.lastSeen = lastSeen;
	}

	public String getIP () {
		return this.ip;
	}

	public void setLastSeen(long time){
		this.lastSeen = time;
	}

	public boolean timeOutPassed(int timeout){
		boolean result = false;
		long timePassedSinceLastSeen = new Date().getTime() - this.lastSeen;
		if (timePassedSinceLastSeen >= timeout)
			result = true;
		return result;
	}

    
}