package ua.kas.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.fannj.ActivationFunction;
import com.googlecode.fannj.Fann;
import com.googlecode.fannj.Layer;
import com.googlecode.fannj.Trainer;
import com.googlecode.fannj.TrainingAlgorithm;

public class Main {
	public static void main(String[] args) {
		System.setProperty("jna.library.path", "C:/Program Files/Java/FANN-2.2.0-Source/bin");
		training();
		check();
	}

	private static void training() {
		// Для сборки новой ИНС необходимо создасть список слоев
		List<Layer> layerList = new ArrayList<Layer>();
		layerList.add(Layer.create(3, ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
		layerList.add(Layer.create(16, ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
		layerList.add(Layer.create(4, ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
		Fann fann = new Fann(layerList);
		// Создаем тренера и определяем алгоритм обучения
		Trainer trainer = new Trainer(fann);
		trainer.setTrainingAlgorithm(TrainingAlgorithm.FANN_TRAIN_RPROP);
		/*
		 * Проведем обучение взяв уроки из файла, с максимальным колличеством
		 * циклов 100000, показывая отчет каждую 100ю итерацию и добиваемся
		 * ошибки меньше 0.0001
		 */
		trainer.train(new File("xor.data").getAbsolutePath(), 500000, 100, 0.0001f);
		// System.out.println(new File("xor.data").getAbsolutePath());
		fann.save("ann");
	}

	private static void check() {
		Fann fann = new Fann("ann");
		float[][] tests = { { 1.0f, 0, 1 }, { 0.9f, 1, 3 }, { 0.3f, 0, 8 }, { 1, 1, 8 }, { 0.1f, 0, 0 }, };
		for (float[] test : tests) {

			System.out.println(getAction(fann.run(test)));
		}
	}

	private static String getAction(float[] out) {

		int i = 0;
		for (int j = 1; j < 4; j++) {
			System.out.println(out[i] + "  =  " + out[j]);
			if (out[i] < out[j]) {
				i = j;
			}
		}

		switch (i) {
		case 0:
			return "атаковать";
		case 1:
			return "прятаться";
		case 2:
			return "бежать";
		case 3:
			return "ничего не делать";
		}
		return "";
	}

}

// private static void training() {
// // Для сборки новой ИНС необходимо создасть список слоев
// List<Layer> layerList = new ArrayList<Layer>();
// layerList.add(Layer.create(2, ActivationFunction.FANN_SIGMOID_SYMMETRIC,
// 0.01f));
// layerList.add(Layer.create(9, ActivationFunction.FANN_SIGMOID_SYMMETRIC,
// 0.01f));
// layerList.add(Layer.create(3, ActivationFunction.FANN_SIGMOID_SYMMETRIC,
// 0.01f));
// Fann fann = new Fann(layerList);
// // Создаем тренера и определяем алгоритм обучения
// Trainer trainer = new Trainer(fann);
// trainer.setTrainingAlgorithm(TrainingAlgorithm.FANN_TRAIN_RPROP);
// /*
// * Проведем обучение взяв уроки из файла, с максимальным колличеством
// * циклов 100000, показывая отчет каждую 100ю итерацию и добиваемся
// * ошибки меньше 0.0001
// */
// trainer.train(new File("test.data").getAbsolutePath(), 500000, 100, 0.0001f);
// // System.out.println(new File("xor.data").getAbsolutePath());
// fann.save("ann");
// }
//
// private static void check() {
// Fann fann = new Fann("ann");
// float[][] tests = { { 1, 3 }, { 2, 1 }, { 3, 2 } };
// for (float[] test : tests) {
//
// System.out.println(getAction(fann.run(test)));
// }
// }
//
// private static String getAction(float[] out) {
//
// int i = 0;
// for (int j = 1; j < 3; j++) {
// System.out.println(out[i] + " = " + out[j]);
// if (out[i] < out[j]) {
// i = j;
// }
// }
//
// switch (i) {
// case 0:
// return "много";
// case 1:
// return "немного";
// case 2:
// return "сиди дома";
// case 3:
// return "ничего не делать";
// }
// return "";
// }
