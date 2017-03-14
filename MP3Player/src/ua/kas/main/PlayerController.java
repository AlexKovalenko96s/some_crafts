package ua.kas.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerController {

	@FXML
	Label l_name;

	private FileInputStream FIS;
	private BufferedInputStream BIS;

	private Player player;

	private long pauseLocation;
	private long songTotalLength;

	private String fileLocation;

	public void stop() {
		if (player != null) {
			player.close();
			pauseLocation = 0;
			songTotalLength = 0;
		}
	}

	public void pause() {
		if (player != null) {
			try {
				pauseLocation = FIS.available();
				player.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void resume() {
		try {
			FIS = new FileInputStream(fileLocation);
			BIS = new BufferedInputStream(FIS);
			player = new Player(BIS);
			FIS.skip(songTotalLength - pauseLocation);
		} catch (JavaLayerException | IOException e) {
			e.printStackTrace();
		}

		new Thread() {
			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void selector() throws IOException {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 Files", "*.mp3", "*.mpeg3");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(null);

		stop();

		String name = file.getName();
		l_name.setText(name);

		play(file.getAbsolutePath());
	}

	public void play(String path) {
		try {
			FIS = new FileInputStream(path);
			BIS = new BufferedInputStream(FIS);
			player = new Player(BIS);
			songTotalLength = FIS.available();
			fileLocation = path + "";
		} catch (JavaLayerException | IOException e) {
			e.printStackTrace();
		}

		new Thread() {
			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}