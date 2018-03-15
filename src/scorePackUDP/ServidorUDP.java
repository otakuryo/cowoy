package scorePackUDP;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.io.*;

public class ServidorUDP{

	static HashMap<String, String> score = new HashMap<>();
	static List<String[]> s = new ArrayList<>();
	public static void main (String args[]) {

		System.out.println("iniciado...");
	    try {

	      DatagramSocket socketUDP = new DatagramSocket(6789);
	      byte[] bufer = new byte[1000];

	      while (true) {
	        // Construimos el DatagramPacket para recibir peticiones
	        DatagramPacket peticion =
	          new DatagramPacket(bufer, bufer.length);

	        // Leemos una petición del DatagramSocket
	        socketUDP.receive(peticion);

	        System.out.print("Datagrama recibido del host: " +
	                           peticion.getAddress());
	        System.out.println(" con texto: " + new String(peticion.getData()));
	        formatText(new String(peticion.getData()));
	        // Construimos el DatagramPacket para enviar la respuesta
	        DatagramPacket respuesta =
	          new DatagramPacket(peticion.getData(), peticion.getLength(),
	                             peticion.getAddress(), peticion.getPort());

	        // Enviamos la respuesta, que es un eco
	        socketUDP.send(respuesta);
	      }

	    } catch (SocketException e) {
	      System.out.println("Socket: " + e.getMessage());
	    } catch (IOException e) {
	      System.out.println("IO: " + e.getMessage());
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

}