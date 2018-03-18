package scorePackUDP;

import java.net.*;
import java.util.ArrayList;

import config.Pref;

import java.io.*;

public class ClienteUDP {

	static ArrayList<ScorePlayer> scoreStr = new ArrayList<>();
	static ScorePlayer scorePlayer;
    static int puertoServidor = Pref.getPort();
	static String ip =Pref.getIP();
	public ClienteUDP() {}
	public ClienteUDP(int score, String name, int pilot) {
		scorePlayer = new ScorePlayer(score, name, pilot);
	} 
	public static void main(String[] args) {
		System.out.println("lol?");
		ClienteUDP clienteUDP = new ClienteUDP(321, "rio", 0);
		clienteUDP.sendData();
		clienteUDP = new ClienteUDP(123, "ff", 0);
		clienteUDP.sendData();
		clienteUDP = new ClienteUDP(432, "ds", 0);
		clienteUDP.sendData();
		clienteUDP = new ClienteUDP(345, "sd", 0);
		clienteUDP.sendData();
		clienteUDP = new ClienteUDP(765, "hgf", 0);
		clienteUDP.sendData();
		clienteUDP = new ClienteUDP(678, "jhjj", 0);
		clienteUDP.sendData();
	}

	public void sendData() {
	    try {
	      //transaformamos la clase en bytes 
	      DatagramSocket socketUDP = new DatagramSocket();

	      final ByteArrayOutputStream baos = new ByteArrayOutputStream(200);
	      final ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(scorePlayer);
	      final byte[] data = baos.toByteArray();
	      
	      //transformamos la ip dada, en una ip que el datagram pueda manejar
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
	
	//este metodo enviara un retrive al servidor, que este lo reconocera y respondera con la lista de scores :)
	public ArrayList<ScorePlayer> retriveData() throws ClassNotFoundException {
	    try {
	    	String texto="retrive";
	    	DatagramSocket socketUDP = new DatagramSocket();

	    	byte[] item = texto.getBytes();
	    	InetAddress hostServidor = InetAddress.getByName(ip);

	    	// Construimos un datagrama para enviar el mensaje al servidor
	    	DatagramPacket peticion2 = new DatagramPacket(item, item.length, hostServidor,puertoServidor);
	      
	    	//enviamos el datagrama
	    	socketUDP.send(peticion2);
	      
	    	// Construimos el DatagramPacket que contendra la respuesta
	    	byte[] bufer = new byte[2000];
	    	DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
	      
	    	socketUDP.receive(respuesta);

	    	// Recibimos la respuesta del servidor
	    	final ByteArrayInputStream baos = new ByteArrayInputStream(respuesta.getData());
	    	final ObjectInputStream oos = new ObjectInputStream(baos);
	    	scoreStr = (ArrayList<ScorePlayer>) oos.readObject();
	    	System.out.println("en total ahora"+scoreStr.size());
		    
	    	// Cerramos el socket
	    	socketUDP.close();

	    } catch (SocketException e) {
	      System.out.println("Socket: " + e.getMessage());
	    } catch (IOException e) {
	      System.out.println("IO: " + e);
	    }
		return scoreStr;
	}  
}