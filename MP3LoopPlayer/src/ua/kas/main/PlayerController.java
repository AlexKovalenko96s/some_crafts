package ua.kas.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerController implements Initializable {

	@FXML
	Label l_name;
	@FXML
	Label l_timerStart;

	@FXML
	TextField tf_timeout;

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

	private Timeline timeline;

	private ObservableList<String> musicList = FXCollections.observableArrayList();
	private ObservableList<String> clipList = FXCollections.observableArrayList();

	private ArrayList<String> musicPath = new ArrayList<>();
	private ArrayList<String> clipPath = new ArrayList<>();

	private FileInputStream FIS;
	private BufferedInputStream BIS;
	private FileInputStream FIS_Clip;
	private BufferedInputStream BIS_Clip;

	private Player player;
	private Player playerForClip;

	private long pauseLocation = 0;
	private long pauseLocationClip = 0;
	private long songTotalLength = 0;
	private long songTotalLengthClip = 0;

	private String fileLocation;
	private String clipLocation;

	private int countClip = 0;

	private boolean pause = false;
	private boolean pauseOnMusic = false;
	private boolean pauseOnClip = false;
	private boolean nowClip = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lv_music.setEditable(true);
		lv_clip.setEditable(true);
		lv_music.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lv_clip.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void stop() {
		if (player != null || playerForClip != null) {
			try {
				player.close();
			} catch (Exception e) {
			}
			try {
				playerForClip.close();
			} catch (Exception e) {

			}

			pauseLocation = 0;
			songTotalLength = 0;
			countClip = 0;

			pause = false;
			pauseOnMusic = false;
			pauseOnClip = false;
			nowClip = false;
			timeline.stop();

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						l_name.setText("stop");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public void pause() {
		if (!pause) {
			if (player != null && !nowClip) {
				try {
					timeline.pause();
					pauseLocation = FIS.available();
					player.close();
					pause = true;
					pauseOnMusic = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (nowClip && pauseOnMusic && !pauseOnClip) {
			try {
				pauseLocationClip = FIS_Clip.available();
				playerForClip.close();
				pause = true;
				pauseOnClip = true;
				pauseOnMusic = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void resumeMusic() {

		if (pause) {
			if (pauseOnMusic && !nowClip) {
				pause = false;
				try {
					FIS = new FileInputStream(fileLocation);
					BIS = new BufferedInputStream(FIS);
					player = new Player(BIS);
					FIS.skip(songTotalLength - pauseLocation);
					timeline.play();
				} catch (JavaLayerException | IOException e) {
					e.printStackTrace();
				}
				play(fileLocation);
			} else if (pauseOnClip) {
				try {
					pauseOnClip = false;
					FIS_Clip = new FileInputStream(clipLocation);
					BIS_Clip = new BufferedInputStream(FIS_Clip);
					playerForClip = new Player(BIS_Clip);
					FIS_Clip.skip(songTotalLengthClip - pauseLocationClip);
				} catch (JavaLayerException | IOException e) {
					e.printStackTrace();
				}

				playClip(clipLocation);
			}
		}
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
		ArrayList<Integer> index = new ArrayList<>();
		if (event.getSource() == bnt_delMusic) {
			select = lv_music.getSelectionModel().getSelectedItems();
			for (int i = 0; i < musicList.size(); i++) {
				for (int j = 0; j < select.size(); j++) {
					if (musicList.get(i).equals(select.get(j))) {
						index.add(i);
					}
				}
			}

			for (int i = index.size() - 1; i >= 0; i--) {
				musicList.remove((int) index.get(i));
				musicPath.remove((int) index.get(i));
			}
			lv_music.setItems(musicList);
		} else {
			select = lv_clip.getSelectionModel().getSelectedItems();
			for (int i = 0; i < clipList.size(); i++) {
				for (int j = 0; j < select.size(); j++) {
					if (clipList.get(i).equals(select.get(j))) {
						index.add(i);
					}
				}
			}

			for (int i = index.size() - 1; i >= 0; i--) {
				clipList.remove((int) index.get(i));
				clipPath.remove((int) index.get(i));
			}
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
					clipPath.add(file.getAbsolutePath());
				}
				lv_clip.setItems(clipList);
			}
		}
	}

	public void start() {
		int timeout = Integer.parseInt(tf_timeout.getText());
		if (musicList.size() != 0 && clipList.size() != 0 && timeout != 0) {
			play(musicPath.get(0));

			timeline = new Timeline(new KeyFrame(Duration.seconds(timeout), ae -> playClips()));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();

		} else
			JOptionPane.showMessageDialog(null, "не все выбранно!");
	}

	public void playClips() {
		timeline.pause();
		pause();
		countClip = 0;
		nowClip = true;
		playClip(clipPath.get(0));
	}

	public void playClip(String path) {
		try {
			FIS_Clip = new FileInputStream(path);
			BIS_Clip = new BufferedInputStream(FIS_Clip);
			playerForClip = new Player(BIS_Clip);
			if (pauseLocationClip != 0) {
				FIS_Clip.skip(songTotalLengthClip - pauseLocationClip);
			} else {
				songTotalLengthClip = FIS_Clip.available();
			}
			pauseLocationClip = 0;
			clipLocation = path + "";
		} catch (JavaLayerException | IOException e) {
			e.printStackTrace();
		}

		new Thread() {
			@Override
			public void run() {
				try {
					playerForClip.play();
					countClip++;
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}

				if (playerForClip.isComplete()) {
					if (clipList.size() <= 1) {
						playerForClip.close();
						nowClip = false;
						pause = false;
						play(fileLocation);
						timeline.play();
					} else if (clipList.size() > countClip) {
						playClip(clipPath.get(countClip));
					} else {
						nowClip = false;
						pause = false;
						play(fileLocation);
						timeline.play();
					}
				}
			}
		}.start();
	}

	public void play(String path) {
		try {
			FIS = new FileInputStream(path);
			BIS = new BufferedInputStream(FIS);
			player = new Player(BIS);
			if (pauseLocation != 0) {
				FIS.skip(songTotalLength - pauseLocation);
			} else {
				songTotalLength = FIS.available();
			}
			pauseLocation = 0;
			fileLocation = path + "";
		} catch (JavaLayerException | IOException e) {
			e.printStackTrace();
		}

		new Thread() {
			@Override
			public void run() {
				try {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								l_name.setText(musicList.get(0));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

					player.play();

					if (player.isComplete()) {
						if (musicList.size() > 1) {
							play(musicPath.get(1));

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									musicList.remove(0);
									musicPath.remove(0);
									try {
										l_name.setText(musicList.get(1));
									} catch (Exception e) {
										l_name.setText(musicList.get(0));
									}
								}
							});
						} else {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									l_name.setText("null");
									musicList.clear();
									musicPath.clear();
									player.close();
									timeline.stop();
								}
							});
						}
					}
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}