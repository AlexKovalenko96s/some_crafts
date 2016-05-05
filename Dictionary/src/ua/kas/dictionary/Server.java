package ua.kas.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Server implements Runnable{

	static String line = "";
	File file = new File("dictionary.txt");
	private static Socket connection;
	private static ServerSocket server;
	static private ObjectOutputStream output;
	static private ObjectInputStream input;
	
	@Override
	public void run() {
		
		try {
			server = new ServerSocket(5678 , 10);
		} catch (IOException e) {e.printStackTrace();}
		
		while(true){
			
			try {
				connection = server.accept();
				output = new ObjectOutputStream(connection.getOutputStream());
				input = new ObjectInputStream(connection.getInputStream());
				String string = (String) input.readObject();
				
				try {
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					
					while((line = br.readLine()) != null){
						if(line.contains(string)){
							System.out.println(line);
							output.writeUTF(line);
						}
					}
					System.out.println(output);
					fr.close();
					br.close();
					
				} catch (IOException e1) {JOptionPane.showMessageDialog(null, "File not found");}		
			} catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
			
		}
	}
}