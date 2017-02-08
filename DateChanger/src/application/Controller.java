package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

public class Controller {

	@FXML
	Button btn_change;
	@FXML
	Button btn_select;

	private ArrayList<String> path = new ArrayList<>();

	private String pathFolder;

	public void select() {
		DirectoryChooser chooser = new DirectoryChooser();
		File selectedDirectory = chooser.showDialog(null);

		if (selectedDirectory == null) {
			btn_change.setDisable(true);
			JOptionPane.showMessageDialog(null, "No Directory selected!!!");
		} else {
			pathFolder = selectedDirectory.getAbsolutePath();
			btn_change.setDisable(false);
		}
	}

	public void change() {
		path.clear();

		File folder = new File(pathFolder);

		processFilesFromFolder(folder);
		save(path);
	}

	private void processFilesFromFolder(File folder) {
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) {
			if (entry.isDirectory()) {
				processFilesFromFolder(entry);
				continue;
			}
			path.add(entry.getAbsolutePath());
		}
	}

	private void save(ArrayList<String> path) {
		File file;
		long time;
		for (int i = 0; i < path.size(); i++) {
			file = new File(path.get(i));
			time = System.currentTimeMillis();
			FileTime fileTime = FileTime.fromMillis(time);

			try {
				Files.setAttribute(file.toPath(), "basic:lastModifiedTime", fileTime, LinkOption.NOFOLLOW_LINKS);
				Files.setAttribute(file.toPath(), "basic:creationTime", fileTime, LinkOption.NOFOLLOW_LINKS);
				Files.setAttribute(file.toPath(), "basic:lastAccessTime", fileTime, LinkOption.NOFOLLOW_LINKS);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error!");
			}

			Path pathSource = Paths.get(path.get(i));
			Path pathDestination = Paths.get(path.get(i));
			try {
				Files.copy(pathSource, pathDestination);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error!");
			}
		}
		JOptionPane.showMessageDialog(null, "Finish!");
	}

}
