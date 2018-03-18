package scorePackUDP;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ServidorUDP{

	//patron de diseño singlenton -> no funciona :(
	
	//Creamos un espacio para guardar todos los datos
	final static ArrayList<ScorePlayer> scoreStr = new ArrayList<>();
	static DatagramSocket socketUDP;
	
	//creamos un espacio para guardar el dato del jugador
	static ScorePlayer scorePlayer;
	static int port = 6789;
	
	public void start() throws SocketException {
		System.out.println("servidor iniciado :)");
	      socketUDP = new DatagramSocket(port);
	}
	public static void main (String args[]) {
		System.out.println("iniciado...");
	    try {

	      socketUDP = new DatagramSocket(port);

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

        //transformamos los datos en texto y le quitamos los espacios inecesarios :)
        String tex = new String(peticion.getData()).trim();
        if (tex.equals("retrive")) {
		    // Construimos el DatagramPacket para enviar la respuesta
		    //primero convertimos el array en un objeto y luego en bytes
		    final ByteArrayOutputStream baos = new ByteArrayOutputStream(2000);
		    final ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(scoreStr);
		    final byte[] dataStr = baos.toByteArray();
		    DatagramPacket respuesta = new DatagramPacket(dataStr, dataStr.length, peticion.getAddress(), peticion.getPort());
		
		    // Enviamos la respuesta con los datos
		    socketUDP.send(respuesta);
		    System.out.println("Enviando: "+scoreStr.size());
		    scoreStr.clear();
		}else {
			//recogemos los datos, y lo transformamos en un objeto de tipo ScorePlayer
			//para luego añadirlo a los datos.
		    final ByteArrayInputStream baos = new ByteArrayInputStream(peticion.getData());
		    final ObjectInputStream oos = new ObjectInputStream(baos);
		    scorePlayer = (ScorePlayer) oos.readObject();
		    scoreStr.add(scorePlayer);

		    //mostramos los datos por pantalla para verificar que los datos llegaron correctamente
		    System.out.print("Score: " +scorePlayer.getScore());
		    System.out.println(" de " + scorePlayer.getName());
		    //formatText(new String(peticion.getData()));
		}
	}
}