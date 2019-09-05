package sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FlujoSalida{
	
   public static void main(String[] args){
		
	InetAddress ip;
	String nombreMaquina;
	String direccionMac;
	String direccionIp;
	try {
			
		//Obtiene los datos de la computadora
		ip = InetAddress.getLocalHost();
		
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			
		byte[] mac = network.getHardwareAddress();
			
		//le da el formato al mac address
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
		}
		
		//instancia en variables los datos de la maquina
		nombreMaquina=ip.getHostName();
		direccionIp=ip.getHostAddress();
		direccionMac= (sb.toString());
		
		
		//crea una lista y agrega los datos de la maquina
		List<String> datos = new ArrayList<String>();
		datos.add(nombreMaquina);
		datos.add(direccionIp);
		datos.add(direccionMac);
		
		
		
		
		try {
			//creacion del socket
			//IMPORTANTE CAMBIAR el primer argumento a la ip que recibira la informacion
			Socket socket = new Socket(direccionIp.toString(), 9999);
			//Creacion del stream
			ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());;
			//metodo que envia la informacion
			
			flujoSalida.writeObject(datos);
			
			//Cierre de los proceso
			flujoSalida.close();
			socket.close();
			
			//Excepccion del socket
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		//Excepciones de el metodo que extrae la ip y el mac
			
	} catch (UnknownHostException e) {
		
		e.printStackTrace();
		
	} catch (SocketException e){
			
		e.printStackTrace();
			
	}
	   
   }

}
