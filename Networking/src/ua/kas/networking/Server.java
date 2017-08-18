package ua.kas.networking;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Server implements Runnable
{
	
	private static ServerSocket server;
	private static Socket connections;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	public void run() {
		try 
		{
			server = new ServerSocket(5678, 10);
			while(true)
			{
				connections = server.accept();
				output = new ObjectOutputStream(connections.getOutputStream());
				input = new ObjectInputStream(connections.getInputStream());
				try {
					output.writeObject("You send:" + (String)input.readObject());
				} catch (HeadlessException e) {
				} catch (ClassNotFoundException e) {}
			}
		} catch (UnknownHostException e) {
		} catch (IOException e) {}
	}

}
