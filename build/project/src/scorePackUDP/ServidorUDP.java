package scorePackUDP;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class ServidorUDP{

	//patron de dise�o singlenton -> 
	static HashMap<Integer, String> score;
	final static ArrayList<ScorePlayer> scoreStr = new ArrayList<>();
	static DatagramSocket socketUDP;
	static byte[] bufer;
	static ScorePlayer scorePlayer;
	
	public void start() throws SocketException {
		System.out.println("servidor iniciado :)");
	      socketUDP = new DatagramSocket(6789);
	      bufer = new byte[200];
	}
	public static void main (String args[]) {
		System.out.println("iniciado...");
		 score = new HashMap<>();
	    try {

	      socketUDP = new DatagramSocket(6789);
	      bufer = new byte[200];

	      while (true) {
	    	  capturePack();
	      }

	    } catch (SocketException e) {
	      System.out.println("Socket: " + e.getMessage());
	    } catch (IOException e) {
	      System.out.println("IO: " + e.getMessage());
	    } catch (ClassNotFoundException e) {
	    	System.out.println("Output: "+e.getMessage());
		}
	}
	static void formatText(String text) {
		String[] token = text.split(",");
		score.put(Integer.parseInt(token[0]),token[1]);
		showMap();
	}
	public static void showMap() {
		score.forEach((k,v) -> System.out.println("Key: "+k+" Value: "+v));
	}
	public static ArrayList<ScorePlayer> getScoreStr() {
		return scoreStr;
	}

	public static void capturePack() throws IOException, ClassNotFoundException {
        // Construimos el DatagramPacket para recibir peticiones
        DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

        // Leemos una petici�n del DatagramSocket
        socketUDP.receive(peticion);

        if (new String(peticion.getData()).equals("retrive")) {

		    final ByteArrayInputStream baos = new ByteArrayInputStream(peticion.getData());
		    final ObjectInputStream oos = new ObjectInputStream(baos);
		    scorePlayer = (ScorePlayer) oos.readObject();
		    scoreStr.add(scorePlayer);
		    
		    System.out.print("Usuario: " +String.format("%05d$",score));
		    System.out.println(" con puntaje: " + scorePlayer.getScore());
		    //formatText(new String(peticion.getData()));
		    
		    // Construimos el DatagramPacket para enviar la respuesta
		    DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(), peticion.getPort());
		
		    // Enviamos la respuesta, que es un eco
		    socketUDP.send(respuesta);
		    System.out.println(scoreStr.size());
		    
		}else {
		    final ByteArrayInputStream baos = new ByteArrayInputStream(peticion.getData());
		    final ObjectInputStream oos = new ObjectInputStream(baos);
		    scorePlayer = (ScorePlayer) oos.readObject();
		    scoreStr.add(scorePlayer);
		    
		    System.out.print("Datagrama:" +scorePlayer.getScore());
		    System.out.println(" con texto: " + scorePlayer.getName());
		    //formatText(new String(peticion.getData()));
		}
	}
}