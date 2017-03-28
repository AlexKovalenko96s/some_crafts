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

public class Controller implements Initializable {

	@FXML
	Label l_name;
	@FXML
	Label l_musicTime;
	@FXML
	Label l_clipTime;
	@FXML
	Label l_clipName;

	@FXML
	TextField tf_timeout;
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
	Button bnt_saveMusic;
	@FXML
	Button bnt_saveClip;
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
	Button bnt_upMusic;
	@FXML
	Button bnt_upClip;
	@FXML
	Button bnt_downMusic;
	@FXML
	Button bnt_downClip;

	@FXML
	ListView<String> lv_music = new ListView<String>();
	@FXML
	ListView<String> lv_clip = new ListView<String>();

	private Timeline timeLineLow;

	private ObservableList<String> musicList = FXCollections.observableArrayList();
	private ObservableList<String> clipList = FXCollections.observableArrayList();

	private ArrayList<String> musicPath = new ArrayList<>();
	private ArrayList<String> clipPath = new ArrayList<>();
	private ArrayList<String> clipLenghtList = new ArrayList<>();

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
	private long musicLenght = 0;
	private long clipLenght = 0;

	private String fileLocation;
	private String clipLocation;

	private int countClip = 0;
	private int countMusic = 0;

	private boolean pause = false;
	private boolean pauseOnMusic = false;
	private boolean pauseOnClip = false;
	private boolean nowClip = false;
	private boolean autoOn = false;

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

			timeLineLow.stop();

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						l_name.setText("Stop");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		if (autoOn) {
			start();
			autoOn = false;
		}
	}

	public void pause() {
		if (!pause) {
			if (player != null && !nowClip) {
				try {
					timeLineLow.pause();
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
			musicPath.clear();
			musicLenght = 0;
			lv_music.setItems(musicList);
		} else if (event.getSource() == bnt_clearClips) {
			clipList.clear();
			clipPath.clear();
			clipLenghtList.clear();
			clipLenght = 0;
			lv_clip.setItems(clipList);
		}

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				String h = Long.toString(musicLenght / 3600);
				String m = Long.toString((musicLenght / 60) % 60);
				String s = Long.toString(musicLenght % 60);

				if (h.length() != 2)
					h = "0" + h;
				if (m.length() != 2)
					m = "0" + m;
				if (s.length() != 2)
					s = "0" + s;

				l_musicTime.setText(h + ":" + m + ":" + s);

				h = Long.toString(clipLenght / 3600);
				m = Long.toString((clipLenght / 60) % 60);
				s = Long.toString(clipLenght % 60);

				if (h.length() != 2)
					h = "0" + h;
				if (m.length() != 2)
					m = "0" + m;
				if (s.length() != 2)
					s = "0" + s;

				l_clipTime.setText(h + ":" + m + ":" + s);
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
				long duration = 0;
				try {
					AudioFile audioFile = AudioFileIO.read(new File(musicPath.get((int) index.get(i))));
					duration = audioFile.getAudioHeader().getTrackLength();
					musicLenght -= duration;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
				long duration = 0;
				try {
					AudioFile audioFile = AudioFileIO.read(new File(clipPath.get((int) index.get(i))));
					duration = audioFile.getAudioHeader().getTrackLength();
					clipLenght -= duration;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				clipList.remove((int) index.get(i));
				clipPath.remove((int) index.get(i));
				clipLenghtList.remove((int) index.get(i));
			}

			lv_clip.setItems(clipList);
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				String h = Long.toString(musicLenght / 3600);
				String m = Long.toString((musicLenght / 60) % 60);
				String s = Long.toString(musicLenght % 60);

				if (h.length() != 2)
					h = "0" + h;
				if (m.length() != 2)
					m = "0" + m;
				if (s.length() != 2)
					s = "0" + s;

				l_musicTime.setText(h + ":" + m + ":" + s);

				h = Long.toString(clipLenght / 3600);
				m = Long.toString((clipLenght / 60) % 60);
				s = Long.toString(clipLenght % 60);

				if (h.length() != 2)
					h = "0" + h;
				if (m.length() != 2)
					m = "0" + m;
				if (s.length() != 2)
					s = "0" + s;

				l_clipTime.setText(h + ":" + m + ":" + s);
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
					long duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(file);
						duration = audioFile.getAudioHeader().getTrackLength();
						musicLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					String h = Long.toString(duration / 3600);
					String m = Long.toString((duration / 60) % 60);
					String s = Long.toString(duration % 60);

					if (h.length() != 2)
						h = "0" + h;
					if (m.length() != 2)
						m = "0" + m;
					if (s.length() != 2)
						s = "0" + s;

					musicList.add(h + ":" + m + ":" + s + " " + file.getName());
					musicPath.add(file.getAbsolutePath());
				}

				lv_music.setItems(musicList);
			} else if (event.getSource() == bnt_addClip) {
				for (File file : list) {
					long duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(file);
						duration = audioFile.getAudioHeader().getTrackLength();
						clipLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					String h = Long.toString(duration / 3600);
					String m = Long.toString((duration / 60) % 60);
					String s = Long.toString(duration % 60);

					if (h.length() != 2)
						h = "0" + h;
					if (m.length() != 2)
						m = "0" + m;
					if (s.length() != 2)
						s = "0" + s;

					clipList.add(h + ":" + m + ":" + s + " " + file.getName());
					clipPath.add(file.getAbsolutePath());
					clipLenghtList.add("");
				}

				lv_clip.setItems(clipList);
			}

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					String h = Long.toString(musicLenght / 3600);
					String m = Long.toString((musicLenght / 60) % 60);
					String s = Long.toString(musicLenght % 60);

					if (h.length() != 2)
						h = "0" + h;
					if (m.length() != 2)
						m = "0" + m;
					if (s.length() != 2)
						s = "0" + s;

					l_musicTime.setText(h + ":" + m + ":" + s);

					h = Long.toString(clipLenght / 3600);
					m = Long.toString((clipLenght / 60) % 60);
					s = Long.toString(clipLenght % 60);

					if (h.length() != 2)
						h = "0" + h;
					if (m.length() != 2)
						m = "0" + m;
					if (s.length() != 2)
						s = "0" + s;

					l_clipTime.setText(h + ":" + m + ":" + s);
				}
			});
		}
	}

	public void start() {
		if (musicList.size() != 0 && clipList.size() != 0) {
			if (!clipLenghtList.contains("")) {
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
					long m = Integer.parseInt(
							curStringDate.substring(curStringDate.indexOf(".") + 1, curStringDate.lastIndexOf(".")));
					long s = Integer.parseInt(curStringDate.substring(curStringDate.lastIndexOf(".") + 1));

					String on = tf_on.getText();
					long hOn = Integer.parseInt(on.substring(0, on.indexOf(".")));
					long mOn = Integer.parseInt(on.substring(on.indexOf(".") + 1));

					String off = tf_off.getText();
					long hOff = Integer.parseInt(off.substring(0, off.indexOf(".")));
					long mOff = Integer.parseInt(off.substring(off.indexOf(".") + 1));

					if ((h * 3600) + (m * 60) >= (hOn * 3600) + (mOn * 60) && !autoOn) {
						autoOn = true;
						dif = (hOff * 3600 - h * 3600) + (mOff * 60 - m * 60) - s;
						Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(dif), ae -> stop()));
						timeline.play();
					} else {
						autoOn = true;
						difOn = (hOn * 3600 - h * 3600) + (mOn * 60 - m * 60) - s;
						if (difOn < 0) {
							difOn = (24 * 3600) - difOn;
						}
						Timeline timelineOn = new Timeline(new KeyFrame(Duration.seconds(difOn), ae -> run()));
						timelineOn.play();

						dif = (hOff * 3600 - hOn * 3600) + (mOff * 60 - mOn * 60) + difOn;
						Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(dif), ae -> stop()));
						timeline.play();
					}
				}

				System.out.println(difOn + " " + dif);

				if (difOn == 0 && !cb_autoOnOff.isSelected()) {
					run();
				}
			} else {
				ArrayList<Integer> index = new ArrayList<>();
				for (int i = 0; i < clipLenghtList.size(); i++) {
					if (clipLenghtList.get(i).equals("")) {
						index.add(i + 1);
					}
				}
				JOptionPane.showMessageDialog(null, "Timeout для клипов " + index + " не заполнены!");
			}
		}
	}

	public void run() {
		if (musicList.size() != 0 && clipList.size() != 0) {
			play(musicPath.get(countMusic));

			timeLineLow = new Timeline(new KeyFrame(
					Duration.seconds(Integer.parseInt(clipLenghtList.get(countClip)) - 5), ae -> soundLow()));
			timeLineLow.play();
		} else
			JOptionPane.showMessageDialog(null, "Hе все выбранно!");
	}

	public void soundLow() {
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
						playClips();
					}
				}.start();
			} catch (LineUnavailableException ex) {
				System.err.println("source not supported");
				ex.printStackTrace();
			}
		}
	}

	public void playClips() {
		timeLineLow.pause();
		pause();
		nowClip = true;
		playClip(clipPath.get(countClip));

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
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}

				if (playerForClip.isComplete()) {
					countClip++;
					if (clipList.size() <= countClip) {
						countClip = 0;
					}

					nowClip = false;
					pause = false;
					play(fileLocation);

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
						} catch (

						LineUnavailableException ex) {
							System.err.println("source not supported");
							ex.printStackTrace();
						}
					}
					timeLineLow = new Timeline(new KeyFrame(
							Duration.seconds(Integer.parseInt(clipLenghtList.get(countClip)) - 5), ae -> soundLow()));
					timeLineLow.play();
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
							countMusic = 0;
							play(musicPath.get(countMusic));
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									l_name.setText(musicList.get(countMusic));
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
		Scanner scn = new Scanner(System.in);
		try {
			if (e.getSource() == bnt_loadMusic) {
				musicLenght = 0;
				musicList.clear();
				musicPath.clear();

				scn = new Scanner(new File(file.getAbsolutePath()));

				while (scn.hasNextLine())
					musicPath.add(scn.nextLine());

				for (int i = 0; i < musicPath.size(); i++) {
					long duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(new File(musicPath.get(i)));
						duration = audioFile.getAudioHeader().getTrackLength();
						musicLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					String h = Long.toString(duration / 3600);
					String m = Long.toString((duration / 60) % 60);
					String s = Long.toString(duration % 60);

					if (h.length() != 2)
						h = "0" + h;
					if (m.length() != 2)
						m = "0" + m;
					if (s.length() != 2)
						s = "0" + s;

					musicList.add(h + ":" + m + ":" + s + " " + new File(musicPath.get(i)).getName());
				}

				lv_music.setItems(musicList);
			} else if (e.getSource() == bnt_loadClip) {
				clipLenght = 0;
				clipList.clear();
				clipPath.clear();
				clipLenghtList.clear();

				scn = new Scanner(new File(file.getAbsolutePath()));

				while (scn.hasNextLine())
					clipPath.add(scn.nextLine());

				for (int i = 0; i < clipPath.size(); i++) {
					long duration = 0;
					try {
						AudioFile audioFile = AudioFileIO.read(new File(clipPath.get(i)));
						duration = audioFile.getAudioHeader().getTrackLength();
						clipLenght += duration;
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					String h = Long.toString(duration / 3600);
					String m = Long.toString((duration / 60) % 60);
					String s = Long.toString(duration % 60);

					if (h.length() != 2)
						h = "0" + h;
					if (m.length() != 2)
						m = "0" + m;
					if (s.length() != 2)
						s = "0" + s;

					clipList.add(h + ":" + m + ":" + s + " " + new File(clipPath.get(i)).getName());
					clipLenghtList.add("");
				}

				lv_clip.setItems(clipList);
			}

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					String h = Long.toString(musicLenght / 3600);
					String m = Long.toString((musicLenght / 60) % 60);
					String s = Long.toString(musicLenght % 60);

					if (h.length() != 2)
						h = "0" + h;
					if (m.length() != 2)
						m = "0" + m;
					if (s.length() != 2)
						s = "0" + s;

					l_musicTime.setText(h + ":" + m + ":" + s);

					h = Long.toString(clipLenght / 3600);
					m = Long.toString((clipLenght / 60) % 60);
					s = Long.toString(clipLenght % 60);

					if (h.length() != 2)
						h = "0" + h;
					if (m.length() != 2)
						m = "0" + m;
					if (s.length() != 2)
						s = "0" + s;

					l_clipTime.setText(h + ":" + m + ":" + s);
				}
			});
		} catch (Exception ex) {
		} finally {
			scn.close();
		}
	}

	public void setTimeout() {
		ObservableList<String> select;
		ArrayList<Integer> index = new ArrayList<>();
		select = lv_clip.getSelectionModel().getSelectedItems();

		String timeout = tf_timeout.getText();
		int min = 0, sec = 0;

		if (timeout.contains(".")) {
			min = Integer.parseInt(timeout.substring(0, timeout.indexOf(".")));
			sec = Integer.parseInt(timeout.substring(timeout.indexOf(".") + 1));
		} else {
			min = Integer.parseInt(timeout);
		}

		if (select.size() != 0 && (min != 0 || sec != 00)) {
			for (int i = 0; i < clipList.size(); i++) {
				for (int j = 0; j < select.size(); j++) {
					if (clipList.get(i).equals(select.get(j))) {
						index.add(i);
					}
				}
			}

			for (int i = index.size() - 1; i >= 0; i--) {
				clipLenghtList.set(index.get(i), Integer.toString((min * 60) + sec));
			}
			tf_timeout.setText("");
		}
	}

	public void up(ActionEvent e) {
		ObservableList<String> select;
		ArrayList<Integer> index = new ArrayList<>();

		if (e.getSource() == bnt_upMusic) {
			select = lv_music.getSelectionModel().getSelectedItems();
			if (select.size() != 0) {
				for (int i = 0; i < musicList.size(); i++) {
					for (int j = 0; j < select.size(); j++) {
						if (musicList.get(i).equals(select.get(j))) {
							index.add(i);
						}
					}
				}
			}

			String temp = "";
			String tempPath = "";

			lv_music.getSelectionModel().select(null);

			for (int i = 0; i < index.size(); i++) {
				try {
					temp = musicList.get(index.get(i) - 1);
					tempPath = musicPath.get(index.get(i) - 1);

					musicList.set(index.get(i) - 1, musicList.get(index.get(i)));
					musicList.set(index.get(i), temp);
					musicPath.set(index.get(i) - 1, musicPath.get(index.get(i)));
					musicPath.set(index.get(i), tempPath);

					lv_music.getSelectionModel().select(index.get(i) - 1);
				} catch (Exception ex) {
				}
			}

			lv_music.setItems(musicList);

		} else if (e.getSource() == bnt_upClip) {
			select = lv_clip.getSelectionModel().getSelectedItems();
			if (select.size() != 0) {
				for (int i = 0; i < clipList.size(); i++) {
					for (int j = 0; j < select.size(); j++) {
						if (clipList.get(i).equals(select.get(j))) {
							index.add(i);
						}
					}
				}
			}

			String temp = "";
			String tempPath = "";
			String tempLenght = "";

			lv_clip.getSelectionModel().select(null);

			for (int i = 0; i < index.size(); i++) {
				try {
					temp = clipList.get(index.get(i) - 1);
					tempPath = clipPath.get(index.get(i) - 1);
					tempLenght = clipLenghtList.get(index.get(i) - 1);

					clipList.set(index.get(i) - 1, clipList.get(index.get(i)));
					clipList.set(index.get(i), temp);
					clipPath.set(index.get(i) - 1, clipPath.get(index.get(i)));
					clipPath.set(index.get(i), tempPath);
					clipLenghtList.set(index.get(i) - 1, clipLenghtList.get(index.get(i)));
					clipLenghtList.set(index.get(i), tempLenght);

					lv_clip.getSelectionModel().select(index.get(i) - 1);
				} catch (Exception ex) {
				}
			}

			lv_clip.setItems(clipList);

		}
	}

	public void down(ActionEvent e) {
		ObservableList<String> select;
		ArrayList<Integer> index = new ArrayList<>();

		if (e.getSource() == bnt_downMusic) {
			select = lv_music.getSelectionModel().getSelectedItems();
			if (select.size() != 0) {
				for (int i = 0; i < musicList.size(); i++) {
					for (int j = 0; j < select.size(); j++) {
						if (musicList.get(i).equals(select.get(j))) {
							index.add(i);
						}
					}
				}
			}

			String temp = "";
			String tempPath = "";

			lv_music.getSelectionModel().select(null);

			for (int i = index.size() - 1; i >= 0; i--) {
				try {
					temp = musicList.get(index.get(i) + 1);
					tempPath = musicPath.get(index.get(i) + 1);

					musicList.set(index.get(i) + 1, musicList.get(index.get(i)));
					musicList.set(index.get(i), temp);
					musicPath.set(index.get(i) + 1, musicPath.get(index.get(i)));
					musicPath.set(index.get(i), tempPath);

					lv_music.getSelectionModel().select(index.get(i) + 1);
				} catch (Exception ex) {
				}
			}

			lv_music.setItems(musicList);
		} else if (e.getSource() == bnt_downClip) {
			select = lv_clip.getSelectionModel().getSelectedItems();
			if (select.size() != 0) {
				for (int i = 0; i < clipList.size(); i++) {
					for (int j = 0; j < select.size(); j++) {
						if (clipList.get(i).equals(select.get(j))) {
							index.add(i);
						}
					}
				}
			}

			String temp = "";
			String tempPath = "";
			String tempLenght = "";

			lv_clip.getSelectionModel().select(null);

			for (int i = index.size() - 1; i >= 0; i--) {
				try {
					temp = clipList.get(index.get(i) + 1);
					tempPath = clipPath.get(index.get(i) + 1);
					tempLenght = clipLenghtList.get(index.get(i) + 1);

					clipList.set(index.get(i) + 1, clipList.get(index.get(i)));
					clipList.set(index.get(i), temp);
					clipPath.set(index.get(i) + 1, clipPath.get(index.get(i)));
					clipPath.set(index.get(i), tempPath);
					clipLenghtList.set(index.get(i) + 1, clipLenghtList.get(index.get(i)));
					clipLenghtList.set(index.get(i), tempLenght);

					lv_clip.getSelectionModel().select(index.get(i) + 1);
				} catch (Exception ex) {
				}
			}

			lv_clip.setItems(clipList);
		}
	}
}
