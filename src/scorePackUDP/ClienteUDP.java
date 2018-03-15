package scorePackUDP;

import java.net.*;
import java.io.*;

public class ClienteUDP {

  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) {

	  String texto="13456,rocko";
	  String ip ="127.0.0.1";
	  //String[] item = {"1234","name12"};
    try {
      DatagramSocket socketUDP = new DatagramSocket();
      byte[] item = texto.getBytes();


      //final ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
      //final ObjectOutputStream oos = new ObjectOutputStream(baos);
      //oos.writeObject(item);
      //final byte[] data = baos.toByteArray();
      
      InetAddress hostServidor = InetAddress.getByName(ip);
      int puertoServidor = 6789;

      // Construimos un datagrama para enviar el mensaje al servidor
      DatagramPacket peticion =
        new DatagramPacket(item, item.length, hostServidor,puertoServidor);

      // Enviamos el datagrama
      socketUDP.send(peticion);

      // Construimos el DatagramPacket que contendrá la respuesta
      byte[] bufer = new byte[1000];
      DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
      socketUDP.receive(respuesta);

      // Enviamos la respuesta del servidor a la salida estandar
      System.out.println("Respuesta: " + new String(respuesta.getData()));

      // Cerramos el socket
      socketUDP.close();

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}