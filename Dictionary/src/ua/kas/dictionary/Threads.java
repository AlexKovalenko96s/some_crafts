package ua.kas.dictionary;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Threads implements Runnable{

	static private Socket connection;
	static private ObjectOutputStream output;
	static private ObjectInputStream input;
	
	@Override
	public void run() {
		
		while(true){
			try {
				connection = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
				output = new ObjectOutputStream(connection.getOutputStream());
				input = new ObjectInputStream(connection.getInputStream());		
				JOptionPane.showMessageDialog(null, (String) input.readObject());
			} catch (IOException e) {e.printStackTrace();
			} catch (HeadlessException e) {e.printStackTrace();
			} catch (ClassNotFoundException e) {e.printStackTrace();}
		}
	}
	
	static void sendDate(Object string) throws IOException{
		output.flush();
		output.writeObject(string);
	}
}
