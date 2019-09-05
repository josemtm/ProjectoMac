package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class FlujoEntrada {

	//Prueba para recibir los datos del flujo de salida.
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			//crear el servidor para escuchar en el puerto
			System.out.println("Servidor esta escuchando!");
			ServerSocket servidor = new ServerSocket(9999);
			Socket miSocket = servidor.accept();
			
			
			
			//recibe los datos del flujo de salida 
			ObjectInputStream flujoEntrada = new ObjectInputStream(miSocket.getInputStream());
			
			try {
				//obtiene e imprime la lista de datos del flujo de entrada
				System.out.println("Los datos de navegacion son:"+" "+ flujoEntrada.readObject());
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			miSocket.close();
			servidor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
