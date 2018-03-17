package scorePackUDP;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class ServidorUDP{

	//patron de diseño singlenton -> 
	final static ArrayList<ScorePlayer> scoreStr = new ArrayList<>();
	static DatagramSocket socketUDP;
	static ScorePlayer scorePlayer;
	
	public void start() throws SocketException {
		System.out.println("servidor iniciado :)");
	      socketUDP = new DatagramSocket(6789);
	}
	public static void main (String args[]) {
		System.out.println("iniciado...");
	    try {

	      socketUDP = new DatagramSocket(6789);
	      byte[] bufer = new byte[200];

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
	public static ArrayList<ScorePlayer> getScoreStr() {
		return scoreStr;
	}

	public static void capturePack() throws IOException, ClassNotFoundException {
	      byte[] buffer = new byte[200];
		// Construimos el DatagramPacket para recibir peticiones
        DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

        // Leemos una petición del DatagramSocket
        socketUDP.receive(peticion);

        String tex = new String(peticion.getData()).trim();
        if (tex.equals("retrive")) {
		    // Construimos el DatagramPacket para enviar la respuesta
		    //primero combertimos el array en un objeto y luego en bytes
		    final ByteArrayOutputStream baos = new ByteArrayOutputStream(2000);
		    final ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(scoreStr);
		    final byte[] dataStr = baos.toByteArray();
		    System.out.println("-> "+dataStr.length);
		    DatagramPacket respuesta = new DatagramPacket(dataStr, dataStr.length, peticion.getAddress(), peticion.getPort());
		
		    // Enviamos la respuesta, que es un eco
		    socketUDP.send(respuesta);
		    System.out.println("Borrando: "+scoreStr.size());
		    scoreStr.clear();

		    System.out.println("Compleado: "+scoreStr.size());
		}else {
		    final ByteArrayInputStream baos = new ByteArrayInputStream(peticion.getData());
		    final ObjectInputStream oos = new ObjectInputStream(baos);
		    scorePlayer = (ScorePlayer) oos.readObject();
		    scoreStr.add(scorePlayer);

		    System.out.print("Score: " +scorePlayer.getScore());
		    System.out.println(" de " + scorePlayer.getName());
		    //formatText(new String(peticion.getData()));
		}
	}
}