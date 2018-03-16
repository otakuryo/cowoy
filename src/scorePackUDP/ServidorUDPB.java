package scorePackUDP;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadFactory;
import java.io.*;

public class ServidorUDPB implements ThreadFactory{

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
	/*
	public void main (String args[]) {
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
	*/
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

	public void capturePack() throws IOException, ClassNotFoundException {
        // Construimos el DatagramPacket para recibir peticiones
        DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

        // Leemos una petición del DatagramSocket
        socketUDP.receive(peticion);

        final ByteArrayInputStream baos = new ByteArrayInputStream(peticion.getData());
        final ObjectInputStream oos = new ObjectInputStream(baos);
        scorePlayer = (ScorePlayer) oos.readObject();
        scoreStr.add(scorePlayer);
        
        System.out.print("Datagrama recibido del host: " +peticion.getAddress());
        System.out.println(" con texto: " + scorePlayer.getName());
        //formatText(new String(peticion.getData()));
        
        // Construimos el DatagramPacket para enviar la respuesta
        DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(), peticion.getPort());

        // Enviamos la respuesta, que es un eco
        socketUDP.send(respuesta);
        System.out.println(scoreStr.size());
	}
	@Override
	public Thread newThread(Runnable r) {

		try {
			System.out.println("servidor iniciado :)");
			socketUDP = new DatagramSocket(6789);
			bufer = new byte[200];
			while (true) {
					capturePack();
			  }	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
}