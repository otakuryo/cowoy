package config;

public class Pref {
	static int WITH = 1024;
	static int HEIGHT = 768;
	static String user= "User";
	static String iP = "127.0.0.1";
	static int port = 6789;
	
	public static int getHEIGHT() {return HEIGHT;}
	public static int getWITH() {return WITH;}
	
	public static String getUser() {return user;}
	public static void setUser(String user) {Pref.user = user;}
	
	public static String getIP() {return iP;}
	public static void setIP(String iPExt) {iP = iPExt;}
	
	public static int getPort() {return port;}
}
