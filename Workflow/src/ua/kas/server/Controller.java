package ua.kas.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import javafx.stage.DirectoryChooser;

public class Controller {

	private String pathFolder = "";
	File[] folderEntries;
	File[] folderFilePath;

	public void selectLocation() {
		DirectoryChooser chooser = new DirectoryChooser();
		File selectedDirectory = chooser.showDialog(null);

		if (selectedDirectory == null) {
			JOptionPane.showMessageDialog(null, "No Directory selected!!!");
		} else {
			pathFolder = selectedDirectory.getAbsolutePath();
		}

		File folder = new File(pathFolder);
		folderEntries = folder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".docx") || name.toLowerCase().endsWith(".txt")
						|| name.toLowerCase().endsWith(".doc") || name.toLowerCase().endsWith(".pdf");
			}
		});

		for (int i = 0; i < folderEntries.length; i++) {
			folderFilePath[i] = folderEntries[i].getAbsoluteFile();
		}
	}

	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				OutputStream os = null;
				ServerSocket servsock = null;
				Socket sock = null;
				try {
					servsock = new ServerSocket(8888);
					while (true) {
						System.out.println("Waiting...");
						try {
							sock = servsock.accept();
							System.out.println("Accepted connection : " + sock);
							// send file
							File myFile = new File(
									"C:/Users/KLUBN_000/Desktop/New Microsoft Word Document - Copy.docx");
							byte[] mybytearray = new byte[(int) myFile.length()];
							fis = new FileInputStream(myFile);
							bis = new BufferedInputStream(fis);
							bis.read(mybytearray, 0, mybytearray.length);
							os = sock.getOutputStream();
							System.out.println(
									"Sending " + "C:/Users/KLUBN_000/Desktop/New Microsoft Word Document - Copy.docx"
											+ "(" + mybytearray.length + " bytes)");
							os.write(mybytearray, 0, mybytearray.length);
							os.flush();
							System.out.println("Done.");
						} finally {
							if (bis != null)
								bis.close();
							if (os != null)
								os.close();
							if (sock != null)
								sock.close();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (servsock != null)
						try {
							servsock.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		});
		thread.start();
	}
}
