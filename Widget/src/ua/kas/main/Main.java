package ua.kas.main;

import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		Group root = new Group();
		Scene scene = new Scene(root, 400, 400, Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		root.setEffect(new DropShadow(15, Color.GRAY));
		primaryStage.setX(Screen.getPrimary().getBounds().getWidth() - 400);
		primaryStage.setY(0);
		launchClock(root);
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void launchClock(Group root) {
		Line line[] = new Line[60];

		Arc cHoures = new Arc(203, 150, 50, 50, 90, 360), cMin = new Arc(203, 150, 100, 100, 90, 360),
				cSec = new Arc(203, 150, 130, 130, 90, 360);
		root.getChildren().addAll(cSec, cMin, cHoures);
		cSec.setFill(Color.TRANSPARENT);
		cMin.setFill(Color.TRANSPARENT);
		cHoures.setFill(Color.TRANSPARENT);
		cSec.setStroke(Color.ORANGE);
		cMin.setStroke(Color.CORAL);
		cHoures.setStroke(Color.DARKCYAN);
		cHoures.setStrokeWidth(12);
		cMin.setStrokeWidth(12);
		cSec.setStrokeWidth(4);
		cHoures.setStrokeLineCap(StrokeLineCap.ROUND);
		cMin.setStrokeLineCap(StrokeLineCap.ROUND);
		cSec.setStrokeLineCap(StrokeLineCap.ROUND);
		Group group = new Group();
		for (double s = 1.0; s <= 60; s = s + 1.0) {
			if (s % 5 == 0) {
				line[(int) s - 1] = new Line(203.0 + 124.0 * Math.cos(s * Math.PI / 30.0),
						150.0 + 124.0 * Math.sin(s * Math.PI / 30.0), 203.0 + 110.0 * Math.cos(s * Math.PI / 30.0),
						150.0 + 110.0 * Math.sin(s * Math.PI / 30.0));
			} else {
				line[(int) s - 1] = new Line(203.0 + 124.0 * Math.cos(s * Math.PI / 30.0),
						150.0 + 124.0 * Math.sin(s * Math.PI / 30.0), 203.0 + 120.0 * Math.cos(s * Math.PI / 30.0),
						150.0 + 120.0 * Math.sin(s * Math.PI / 30.0));
			}

			line[(int) s - 1].setStroke(Color.MEDIUMSLATEBLUE);
			line[(int) s - 1].setStrokeWidth(4);
			line[(int) s - 1].setStrokeLineCap(StrokeLineCap.ROUND);
			group.getChildren().add(line[(int) s - 1]);

		}
		root.getChildren().add(group);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			Calendar c = Calendar.getInstance();
			cSec.setLength(-(c.get(Calendar.SECOND)) / 60.0 * 360.0);
			cMin.setLength(-c.get(Calendar.MINUTE) / 60.0 * 360.0);
			cHoures.setLength(-c.get(Calendar.HOUR) / 12.0 * 360.0);
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}