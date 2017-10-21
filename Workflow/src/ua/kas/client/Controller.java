package ua.kas.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

public class Controller implements Initializable {

	@FXML
	ComboBox<String> comB_servers;

	private final static int FILE_SIZE = 6022386;

	private ObservableList<String> list_servers = FXCollections.observableArrayList("127.0.0.1");

	private String location;

	public void selectLocation() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("File (*.txt, .doc, .docx, .pdf)",
				"*.txt", "*.doc", "*.docx", "*.pdf");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		location = file.getAbsolutePath();
	}

	public void download() throws UnknownHostException, IOException {
		int bytesRead;
		int current = 0;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		Socket sock = null;
		try {
			sock = new Socket("127.0.0.1", 8888);
			System.out.println("Connecting...");

			// receive file
			byte[] mybytearray = new byte[FILE_SIZE];
			InputStream is = sock.getInputStream();
			fos = new FileOutputStream(location);
			bos = new BufferedOutputStream(fos);
			bytesRead = is.read(mybytearray, 0, mybytearray.length);
			current = bytesRead;

			do {
				bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
				if (bytesRead >= 0)
					current += bytesRead;
			} while (bytesRead > -1);

			bos.write(mybytearray, 0, current);
			bos.flush();
			System.out.println("File " + location + " downloaded (" + current + " bytes read)");
		} finally {
			if (fos != null)
				fos.close();
			if (bos != null)
				bos.close();
			if (sock != null)
				sock.close();
		}
	}

	public void list() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comB_servers.setItems(list_servers);
	}
}
