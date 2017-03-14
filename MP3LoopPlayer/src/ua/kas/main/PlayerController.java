package ua.kas.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.FileChooser;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerController implements Initializable {

	@FXML
	Label l_name;
	@FXML
	Label l_time;

	@FXML
	Button bnt_addMusic;
	@FXML
	Button bnt_addClip;
	@FXML
	Button bnt_delMusic;
	@FXML
	Button bnt_delClip;
	@FXML
	Button bnt_clearMusic;
	@FXML
	Button bnt_clearClips;

	@FXML
	ListView<String> lv_music = new ListView<String>();
	@FXML
	ListView<String> lv_clip = new ListView<String>();

	private ObservableList<String> musicList = FXCollections.observableArrayList();
	private ObservableList<String> clipList = FXCollections.observableArrayList();

	private ArrayList<String> musicPath = new ArrayList<>();
	private ArrayList<String> clipPath = new ArrayList<>();

	private FileInputStream FIS;
	private BufferedInputStream BIS;

	private Player player;

	private long pauseLocation;
	private long songTotalLength;

	private String fileLocation;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		l_time.setText(new SimpleDateFormat("HH.mm").format(System.currentTimeMillis()));

		lv_music.setEditable(true);
		lv_clip.setEditable(true);
		lv_music.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lv_clip.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

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

	public void clear(ActionEvent event) {
		if (event.getSource() == bnt_clearMusic) {
			musicList.clear();
			lv_music.setItems(musicList);
		} else {
			clipList.clear();
			lv_clip.setItems(clipList);
		}
	}

	public void remove(ActionEvent event) {
		ObservableList<String> select;
		if (event.getSource() == bnt_delMusic) {
			select = lv_music.getSelectionModel().getSelectedItems();
			ObservableList<String> temp = musicList;
			ArrayList<String> tempPath = musicPath;
			for (int i = 0; i < musicList.size(); i++) {
				for (int j = 0; j < select.size(); j++) {
					if (temp.contains(select.get(i))) {
						System.out.println(temp);
						System.out.println(temp.indexOf(select.get(i)));
						temp.remove(select.get(i));
						System.out.println(temp);
					}
				}
			}
			musicList = temp;
			lv_music.setItems(musicList);
		} else {
			select = lv_clip.getSelectionModel().getSelectedItems();
			ObservableList<String> temp = clipList;
			ArrayList<String> tempPath = clipPath;
			for (int i = 0; i < clipList.size(); i++) {
				for (int j = 0; j < select.size(); j++) {
					if (temp.contains(select.get(i))) {
						temp.remove(select.get(i));
					}
				}
			}
			clipList = temp;
			lv_clip.setItems(clipList);
		}
	}

	public void selector(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 Files", "*.mp3", "*.mpeg3");
		fileChooser.getExtensionFilters().add(extFilter);

		List<File> list = fileChooser.showOpenMultipleDialog(null);

		if (list != null) {
			if (event.getSource() == bnt_addMusic) {
				for (File file : list) {
					musicList.add(file.getName());
					musicPath.add(file.getAbsolutePath());
				}
				lv_music.setItems(musicList);
			} else {
				for (File file : list) {
					clipList.add(file.getName());
					musicPath.add(file.getAbsolutePath());
				}
				lv_clip.setItems(clipList);
			}
		}
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