package scorePackUDP;

import java.net.*;
import java.io.*;

public class ClienteUDP {

	static ScorePlayer scorePlayer;
    static int puertoServidor = 6789;
	static String ip ="127.0.0.1";
	public ClienteUDP() {}
	public ClienteUDP(int score, String name, int pilot) {
		scorePlayer = new ScorePlayer(score, name, pilot);
	}  

	public void sendData() {
	    try {
	      DatagramSocket socketUDP = new DatagramSocket();

	      final ByteArrayOutputStream baos = new ByteArrayOutputStream(200);
	      final ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(scorePlayer);
	      final byte[] data = baos.toByteArray();
	      
	      InetAddress hostServidor = InetAddress.getByName(ip);

	      // Construimos un datagrama para enviar el mensaje al servidor
	      DatagramPacket peticion = new DatagramPacket(data, data.length, hostServidor,puertoServidor);
	      
	      //enviamos el datagrama
	      socketUDP.send(peticion);
	      
	      // Cerramos el socket
	      socketUDP.close();

	    } catch (SocketException e) {
	      System.out.println("Socket: " + e.getMessage());
	    } catch (IOException e) {
	      System.out.println("IO: " + e);
	    }
	}
  // Los argumentos proporcionan el mensaje y el nombre del servidor
  
}