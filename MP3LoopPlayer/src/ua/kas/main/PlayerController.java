package ua.kas.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.swing.JOptionPane;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

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
import javafx.scene.control.CheckBox;
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
	Label l_musicTime;
	@FXML
	Label l_clipTime;
	@FXML
	Label l_clipSecondTime;

	@FXML
	TextField tf_timeout;
	@FXML
	TextField tf_timeoutSecond;
	@FXML
	TextField tf_on;
	@FXML
	TextField tf_off;

	@FXML
	CheckBox cb_autoOnOff;

	@FXML
	Button bnt_loadMusic;
	@FXML
	Button bnt_loadClip;
	@FXML
	Button bnt_loadClipSecond;
	@FXML
	Button bnt_saveMusic;
	@FXML
	Button bnt_saveClip;
	@FXML
	Button bnt_saveClipSecond;
	@FXML
	Button bnt_addMusic;
	@FXML
	Button bnt_addClip;
	@FXML
	Button bnt_addClipSecond;
	@FXML
	Button bnt_delMusic;
	@FXML
	Button bnt_delClip;
	@FXML
	Button bnt_delClipSecond;
	@FXML
	Button bnt_clearMusic;
	@FXML
	Button bnt_clearClips;
	@FXML
	Button bnt_clearClipsSecond;

	@FXML
	ListView<String> lv_music = new ListView<String>();
	@FXML
	ListView<String> lv_clip = new ListView<String>();
	@FXML
	ListView<String> lv_clipSecond = new ListView<String>();

	// private Timeline timeline;
	private Timeline timeLineLow;
	private Timeline timeLineLowSecond;

	private ObservableList<String> musicList = FXCollections.observableArrayList();
	private ObservableList<String> clipList = FXCollections.observableArrayList();
	private ObservableList<String> clipListSecond = FXCollections.observableArrayList();

	private ArrayList<String> musicPath = new ArrayList<>();
	private ArrayList<String> clipPath = new ArrayList<>();
	private ArrayList<String> clipPathSecond = new ArrayList<>();

	private FileInputStream FIS;
	private BufferedInputStream BIS;
	private FileInputStream FIS_Clip;
	private BufferedInputStream BIS_Clip;
	private FileInputStream FIS_ClipSecond;
	private BufferedInputStream BIS_ClipSecond;

	private Player player;
	private Player playerForClip;
	private Player playerForClipSecond;

	private long pauseLocation = 0;
	private long pauseLocationClip = 0;
	private long songTotalLength = 0;
	private long songTotalLengthClip = 0;
	private long pauseLocationClipSecond = 0;
	private long songTotalLengthClipSecond = 0;
	private long musicLenght = 0;
	private long clipLenght = 0;
	private long clipSecondLenght = 0;

	private String fileLocation;
	private String clipLocation;
	private String clipSecondLocation;

	private int countClip = 0;
	private int countMusic = 0;
	private int countClipSecond = 0;
	private int timeoutSecond = 0;

	private boolean pause = false;
	private boolean pauseOnMusic = false;
	private boolean pauseOnClip = false;
	private boolean pauseOnClipSecond = false;
	private boolean nowClip = false;
	private boolean nowClipSecond = false;

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
			countMusic = 0;

			pause = false;
			pauseOnMusic = false;
			pauseOnClip = false;
			nowClip = false;
			// timeline.stop();
			timeLineLow.stop();

			try {
				timeLineLowSecond.stop();
			} catch (Exception e) {
			}

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
					// timeline.pause();
					timeLineLow.pause();
					try {
						timeLineLowSecond.pause();
					} catch (Exception e) {
					}
					pauseLocation = FIS.available();
					player.close();
					pause = true;
					pauseOnMusic = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (nowClip && pauseOnMusic && !pauseOnClip && !nowClipSecond) {
			try {
				pauseLocationClip = FIS_Clip.available();
				playerForClip.close();
				pause = true;
				pauseOnClip = true;
				pauseOnClipSecond = false;
				pauseOnMusic = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (nowClipSecond && pauseOnMusic && !pauseOnClip && !nowClip) {
			try {
				pauseLocationClipSecond = FIS_ClipSecond.available();
				playerForClipSecond.close();
				pause = true;
				pauseOnClip = false;
				pauseOnClipSecond = true;
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

				Info source = Port.Info.SPEAKER;
				if (AudioSystem.isLineSupported(source)) {
					try {
						Port outline = (Port) AudioSystem.getLine(source);
						outline.open();
						FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
						float v = 0.00005F;
						volumeControl.setValue(v);
						new Thread() {
							@Override
							public void run() {
								float v = 0.00005F;
								while (v <= 1.0f) {
									v += 0.00005f;
									volumeControl.setValue(v);
								}
							}
						}.start();
					} catch (LineUnavailableException ex) {
						System.err.println("source not supported");
						ex.printStackTrace();
					}
				}

				try {
					FIS = new FileInputStream(fileLocation);
					BIS = new BufferedInputStream(FIS);
					player = new Player(BIS);
					FIS.skip(songTotalLength - pauseLocation);
					// timeline.play();
					timeLineLow.play();
					try {
						timeLineLowSecond.play();
					} catch (Exception e) {
					}
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
			musicPath.clear();
			musicList.clear();
			musicLenght = 0;
			lv_music.setItems(musicList);
		} else if (event.getSource() == bnt_clearClips) {
			clipPath.clear();
			clipList.clear();
			clipLenght = 0;
			lv_clip.setItems(clipList);
		} else if (event.getSource() == bnt_clearClipsSecond) {
			clipListSecond.clear();
			clipListSecond.clear();
			clipSecondLenght = 0;
			lv_clipSecond.setItems(clipListSecond);
		}

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				l_musicTime.setText(Long.toString(musicLenght / 3600) + ":" + Long.toString((musicLenght / 60) % 60)
						+ ":" + Long.toString(musicLenght % 60));
				l_clipTime.setText(Long.toString(clipLenght / 3600) + ":" + Long.toString((clipLenght / 60) % 60) + ":"
						+ Long.toString(clipLenght % 60));
				l_clipSecondTime.setText(Long.toString(clipSecondLenght / 3600) + ":"
						+ Long.toString((clipSecondLenght / 60) % 60) + ":" + Long.toString(clipSecondLenght % 60));
			}
		});
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
		} else if (event.getSource() == bnt_delClip) {
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
		} else if (event.getSource() == bnt_delClipSecond) {
			select = lv_clipSecond.getSelectionModel().getSelectedItems();
			for (int i = 0; i < clipListSecond.size(); i++) {
				for (int j = 0; j < select.size(); j++) {
					if (clipListSecond.get(i).equals(select.get(j))) {
						index.add(i);
					}
				}
			}

			for (int i = index.size() - 1; i >= 0; i--) {
				clipListSecond.remove((int) index.get(i));
				clipPathSecond.remove((int) index.get(i));
			}
			lv_clipSecond.setItems(clipListSecond);
		}

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				l_musicTime.setText(Long.toString(musicLenght / 3600) + ":" + Long.toString((musicLenght / 60) % 60)
						+ ":" + Long.toString(musicLenght % 60));
				l_clipTime.setText(Long.toString(clipLenght / 3600) + ":" + Long.toString((clipLenght / 60) % 60) + ":"
						+ Long.toString(clipLenght % 60));
				l_clipSecondTime.setText(Long.toString(clipSecondLenght / 3600) + ":"
						+ Long.toString((clipSecondLenght / 60) % 60) + ":" + Long.toString(clipSecondLenght % 60));
			}
		});
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

					int duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(file);
						duration = audioFile.getAudioHeader().getTrackLength();
						musicLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				lv_music.setItems(musicList);
			} else if (event.getSource() == bnt_addClip) {
				for (File file : list) {
					clipList.add(file.getName());
					clipPath.add(file.getAbsolutePath());

					int duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(file);
						duration = audioFile.getAudioHeader().getTrackLength();
						clipLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				lv_clip.setItems(clipList);
			} else if (event.getSource() == bnt_addClipSecond) {
				for (File file : list) {
					clipListSecond.add(file.getName());
					clipPathSecond.add(file.getAbsolutePath());

					int duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(file);
						duration = audioFile.getAudioHeader().getTrackLength();
						clipSecondLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				lv_clipSecond.setItems(clipListSecond);
			}

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					l_musicTime.setText(Long.toString(musicLenght / 3600) + ":" + Long.toString((musicLenght / 60) % 60)
							+ ":" + Long.toString(musicLenght % 60));
					l_clipTime.setText(Long.toString(clipLenght / 3600) + ":" + Long.toString((clipLenght / 60) % 60)
							+ ":" + Long.toString(clipLenght % 60));
					l_clipSecondTime.setText(Long.toString(clipSecondLenght / 3600) + ":"
							+ Long.toString((clipSecondLenght / 60) % 60) + ":" + Long.toString(clipSecondLenght % 60));
				}
			});
		}
	}

	public void start() {
		Info source = Port.Info.SPEAKER;
		if (AudioSystem.isLineSupported(source)) {
			try {
				Port outline = (Port) AudioSystem.getLine(source);
				outline.open();
				FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
				float v = 1F;
				volumeControl.setValue(v);
			} catch (LineUnavailableException ex) {
				System.err.println("source not supported");
				ex.printStackTrace();
			}
		}

		long dif = 0;
		long difOn = 0;

		if (cb_autoOnOff.isSelected()) {
			String curStringDate = new SimpleDateFormat("HH.mm.ss").format(System.currentTimeMillis());
			long h = Integer.parseInt(curStringDate.substring(0, curStringDate.indexOf(".")));
			long m = Integer
					.parseInt(curStringDate.substring(curStringDate.indexOf(".") + 1, curStringDate.lastIndexOf(".")));
			long s = Integer.parseInt(curStringDate.substring(curStringDate.lastIndexOf(".") + 1));

			String on = tf_on.getText();
			long hOn = Integer.parseInt(on.substring(0, on.indexOf(".")));
			long mOn = Integer.parseInt(on.substring(on.indexOf(".") + 1));

			String off = tf_off.getText();
			long hOff = Integer.parseInt(off.substring(0, off.indexOf(".")));
			long mOff = Integer.parseInt(off.substring(off.indexOf(".") + 1));

			if ((h * 3600) + (m * 60) >= (hOn * 3600) + (mOn * 60)) {
				dif = (hOff * 3600 - h * 3600) + (mOff * 60 - m * 60) - s;
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(dif), ae -> stop()));
				timeline.play();
			} else {
				difOn = (hOn * 3600 - h * 3600) + (mOn * 60 - m * 60) - s;
				Timeline timelineOn = new Timeline(new KeyFrame(Duration.seconds(difOn), ae -> run()));
				timelineOn.play();

				dif = (hOff * 3600 - hOn * 3600) + (mOff * 60 - mOn * 60) + difOn;
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(dif), ae -> stop()));
				timeline.play();
			}
		}

		if (difOn == 0) {
			run();
		}
	}

	public void run() {
		int timeout = Integer.parseInt(tf_timeout.getText());
		if (musicList.size() != 0 && clipList.size() != 0 && timeout != 0) {
			play(musicPath.get(0));

			timeLineLow = new Timeline(new KeyFrame(Duration.seconds(timeout - 5), ae -> soundLow(true)));
			timeLineLow.setCycleCount(Animation.INDEFINITE);
			timeLineLow.play();

			// timeline = new Timeline(new KeyFrame(Duration.seconds(timeout),
			// ae -> playClips()));
			// timeline.setCycleCount(Animation.INDEFINITE);
			// timeline.play();

			try {
				timeoutSecond = Integer.parseInt(tf_timeoutSecond.getText());

				if (timeoutSecond != 0 && clipPathSecond.size() != 0) {
					timeLineLowSecond = new Timeline(
							new KeyFrame(Duration.seconds(timeoutSecond - 5), ae -> soundLow(false)));
					timeLineLowSecond.play();
				} else {
					JOptionPane.showMessageDialog(null, "Выберите аудио-дорожки для 'Вставки №2'!");
				}
			} catch (Exception e) {
			}

		} else
			JOptionPane.showMessageDialog(null, "не все выбранно!");
	}

	public void soundLow(boolean clip) {
		Info source = Port.Info.SPEAKER;
		if (AudioSystem.isLineSupported(source)) {
			try {
				Port outline = (Port) AudioSystem.getLine(source);
				outline.open();
				FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
				float v = 1.0f;
				volumeControl.setValue(v);
				new Thread() {
					@Override
					public void run() {
						float v = 1.0F;
						while (v > 0.001F) {
							v -= 0.000036f;
							volumeControl.setValue(v);
						}
						if (clip)
							playClips();
						else
							playClipsSecond();
					}
				}.start();
			} catch (LineUnavailableException ex) {
				System.err.println("source not supported");
				ex.printStackTrace();
			}
		}
	}

	public void playClips() {
		// timeline.pause();
		timeLineLow.pause();
		pause();
		countClip = 0;
		nowClip = true;
		playClip(clipPath.get(0));

		Info source = Port.Info.SPEAKER;
		if (AudioSystem.isLineSupported(source)) {
			try {
				Port outline = (Port) AudioSystem.getLine(source);
				outline.open();
				FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
				float v = 1.0F;
				volumeControl.setValue(v);
			} catch (LineUnavailableException ex) {
				System.err.println("source not supported");
				ex.printStackTrace();
			}
		}
	}

	public void playClipsSecond() {
		// timeline.pause();
		timeLineLow.pause();
		pause();
		countClipSecond = 0;
		nowClipSecond = true;
		playClipSecond(clipPathSecond.get(0));

		Info source = Port.Info.SPEAKER;
		if (AudioSystem.isLineSupported(source)) {
			try {
				Port outline = (Port) AudioSystem.getLine(source);
				outline.open();
				FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
				float v = 1.0F;
				volumeControl.setValue(v);
			} catch (LineUnavailableException ex) {
				System.err.println("source not supported");
				ex.printStackTrace();
			}
		}
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

						Info source = Port.Info.SPEAKER;
						if (AudioSystem.isLineSupported(source)) {
							try {
								Port outline = (Port) AudioSystem.getLine(source);
								outline.open();
								FloatControl volumeControl = (FloatControl) outline
										.getControl(FloatControl.Type.VOLUME);
								float v = 0.00005F;
								volumeControl.setValue(v);
								new Thread() {
									@Override
									public void run() {
										float v = 0.00005F;
										while (v <= 1) {
											v += 0.00005f;
											volumeControl.setValue(v);
										}
									}
								}.start();
							} catch (LineUnavailableException ex) {
								System.err.println("source not supported");
								ex.printStackTrace();
							}
						}

						// timeline.play();
						timeLineLow.play();
					} else if (clipList.size() > countClip) {
						playClip(clipPath.get(countClip));
					} else {
						nowClip = false;
						pause = false;
						play(fileLocation);

						Info source = Port.Info.SPEAKER;
						if (AudioSystem.isLineSupported(source)) {
							try {
								Port outline = (Port) AudioSystem.getLine(source);
								outline.open();
								FloatControl volumeControl = (FloatControl) outline
										.getControl(FloatControl.Type.VOLUME);
								float v = 0.00005F;
								volumeControl.setValue(v);
								new Thread() {
									@Override
									public void run() {
										float v = 0.00005F;
										while (v <= 1.0f) {
											v += 0.00005f;
											volumeControl.setValue(v);
										}
									}
								}.start();
							} catch (

							LineUnavailableException ex) {
								System.err.println("source not supported");
								ex.printStackTrace();
							}
						}

						// timeline.play();
						timeLineLow.play();
					}
				}
			}
		}.start();
	}

	public void playClipSecond(String path) {
		try {
			FIS_ClipSecond = new FileInputStream(path);
			BIS_ClipSecond = new BufferedInputStream(FIS_ClipSecond);
			playerForClipSecond = new Player(BIS_ClipSecond);
			if (pauseLocationClipSecond != 0) {
				FIS_ClipSecond.skip(songTotalLengthClipSecond - pauseLocationClipSecond);
			} else {
				songTotalLengthClipSecond = FIS_ClipSecond.available();
			}
			pauseLocationClipSecond = 0;
			clipSecondLocation = path + "";
		} catch (JavaLayerException | IOException e) {
			e.printStackTrace();
		}

		new Thread() {
			@Override
			public void run() {
				try {
					playerForClipSecond.play();
					countClipSecond++;
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}

				if (playerForClipSecond.isComplete()) {
					if (clipListSecond.size() <= 1) {
						playerForClipSecond.close();
						nowClipSecond = false;
						pause = false;
						timeLineLowSecond.stop();
						play(fileLocation);

						Info source = Port.Info.SPEAKER;
						if (AudioSystem.isLineSupported(source)) {
							try {
								Port outline = (Port) AudioSystem.getLine(source);
								outline.open();
								FloatControl volumeControl = (FloatControl) outline
										.getControl(FloatControl.Type.VOLUME);
								float v = 0.00005F;
								volumeControl.setValue(v);
								new Thread() {
									@Override
									public void run() {
										float v = 0.00005F;
										while (v <= 1) {
											v += 0.00005f;
											volumeControl.setValue(v);
										}
									}
								}.start();
							} catch (LineUnavailableException ex) {
								System.err.println("source not supported");
								ex.printStackTrace();
							}
						}

						// timeline.play();
						timeLineLow.play();
					} else if (clipListSecond.size() > countClipSecond) {
						playClip(clipPathSecond.get(countClipSecond));
					} else {
						nowClipSecond = false;
						pause = false;
						timeLineLowSecond.stop();
						play(fileLocation);

						Info source = Port.Info.SPEAKER;
						if (AudioSystem.isLineSupported(source)) {
							try {
								Port outline = (Port) AudioSystem.getLine(source);
								outline.open();
								FloatControl volumeControl = (FloatControl) outline
										.getControl(FloatControl.Type.VOLUME);
								float v = 0.00005F;
								volumeControl.setValue(v);
								new Thread() {
									@Override
									public void run() {
										float v = 0.00005F;
										while (v <= 1.0f) {
											v += 0.00005f;
											volumeControl.setValue(v);
										}
									}
								}.start();
							} catch (

							LineUnavailableException ex) {
								System.err.println("source not supported");
								ex.printStackTrace();
							}
						}

						// timeline.play();
						timeLineLow.play();
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
								l_name.setText(musicList.get(countMusic));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

					player.play();
					try {
						if (timeoutSecond != 0 && clipPathSecond.size() != 0) {
							timeLineLowSecond = new Timeline(
									new KeyFrame(Duration.seconds(timeoutSecond - 5), ae -> soundLow(false)));
							timeLineLowSecond.play();
							System.out.println("ddd");
						}
					} catch (Exception e) {
					}

					if (player.isComplete()) {
						countMusic++;
						if (musicList.size() > countMusic) {
							play(musicPath.get(countMusic));
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									l_name.setText(musicList.get(countMusic));
								}
							});
						} else {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									countMusic = 0;
									l_name.setText("null");
									player.close();
									// timeline.stop();
									timeLineLow.stop();
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

	public void save(ActionEvent e) {

		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(null);
		try {
			FileWriter fileWriter;
			fileWriter = new FileWriter(new File(file.getAbsolutePath()));

			if (e.getSource() == bnt_saveMusic) {
				for (int i = 0; i < musicPath.size(); i++) {
					fileWriter.write(musicPath.get(i));
					fileWriter.append(System.lineSeparator());
				}
			} else if (e.getSource() == bnt_saveClip) {
				for (int i = 0; i < clipPath.size(); i++) {
					fileWriter.write(clipPath.get(i));
					fileWriter.append(System.lineSeparator());
				}
			} else if (e.getSource() == bnt_saveClipSecond) {
				for (int i = 0; i < clipPathSecond.size(); i++) {
					fileWriter.write(clipPathSecond.get(i));
					fileWriter.append(System.lineSeparator());
				}
			}
			fileWriter.flush();
			fileWriter.close();

		} catch (Exception ex) {
		}
	}

	public void load(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(null);
		musicLenght = 0;
		Scanner scn = null;
		try {
			if (e.getSource() == bnt_loadMusic) {
				musicList.clear();
				musicPath.clear();

				scn = new Scanner(new File(file.getAbsolutePath()));

				while (scn.hasNextLine())
					musicPath.add(scn.nextLine());

				for (int i = 0; i < musicPath.size(); i++) {
					musicList.add(new File(musicPath.get(i)).getName());

					int duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(new File(musicPath.get(i)));
						duration = audioFile.getAudioHeader().getTrackLength();
						musicLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				lv_music.setItems(musicList);
			} else if (e.getSource() == bnt_loadClip) {
				clipList.clear();
				clipPath.clear();

				scn = new Scanner(new File(file.getAbsolutePath()));

				while (scn.hasNextLine())
					clipPath.add(scn.nextLine());

				for (int i = 0; i < clipPath.size(); i++) {
					clipList.add(new File(clipPath.get(i)).getName());

					int duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(new File(clipPath.get(i)));
						duration = audioFile.getAudioHeader().getTrackLength();
						clipLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				lv_clip.setItems(clipList);
			} else if (e.getSource() == bnt_loadClipSecond) {
				clipListSecond.clear();
				clipPathSecond.clear();

				scn = new Scanner(new File(file.getAbsolutePath()));
				while (scn.hasNextLine())
					clipPathSecond.add(scn.nextLine());

				for (int i = 0; i < clipPathSecond.size(); i++) {
					clipListSecond.add(new File(clipPathSecond.get(i)).getName());

					int duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(new File(clipPathSecond.get(i)));
						duration = audioFile.getAudioHeader().getTrackLength();
						clipSecondLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				lv_clipSecond.setItems(clipListSecond);
			}

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					l_musicTime.setText(Long.toString(musicLenght / 3600) + ":" + Long.toString((musicLenght / 60) % 60)
							+ ":" + Long.toString(musicLenght % 60));
					l_clipTime.setText(Long.toString(clipLenght / 3600) + ":" + Long.toString((clipLenght / 60) % 60)
							+ ":" + Long.toString(clipLenght % 60));
					l_clipSecondTime.setText(Long.toString(clipSecondLenght / 3600) + ":"
							+ Long.toString((clipSecondLenght / 60) % 60) + ":" + Long.toString(clipSecondLenght % 60));
				}
			});
		} catch (Exception ex) {
		} finally {
			scn.close();
		}
	}
}