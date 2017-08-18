package ua.kas.networking;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends JFrame implements Runnable
{

	private static Socket connections;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	public static void main(String[] args)
	{
		new Thread(new Main("Test")).start();
		new Thread(new Server()).start();
	}
	
	public Main(String name)
	{
		super(name);
		setLayout(new FlowLayout());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		JButton b1 = new JButton("Send");
		JTextField t1 = new JTextField(15);
		add(t1);
		add(b1);
		
		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource()==b1)
				{
					sendData(t1.getText());
				}
			}
		});
		
	}
	
	public void run() {
		try 
		{
			while(true)
			{
				connections = new Socket(InetAddress.getByName("127.0.0.1"),5678);
				output = new ObjectOutputStream(connections.getOutputStream());
				input = new ObjectInputStream(connections.getInputStream());
				try {
					JOptionPane.showMessageDialog(null, input.readObject());
				} catch (HeadlessException e) {
				} catch (ClassNotFoundException e) {}
			}
		} catch (UnknownHostException e) {
		} catch (IOException e) {}
	}
	
	private static void sendData(Object obj)
	{
		try {
			output.flush();
			output.writeObject(obj);
		} catch (IOException e) {}
	}
}