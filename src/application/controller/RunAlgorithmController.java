package application.controller;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import application.Context;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.core.ChebyshevDistance;
import weka.core.EuclideanDistance;
import weka.core.FilteredDistance;
import weka.core.Instances;
import weka.core.ManhattanDistance;
import weka.core.MinkowskiDistance;

public class RunAlgorithmController {
	
	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox comboChooseAlgorithm;
	
	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox comboKNNDistance;
	
	@FXML
	private Button buttonStart;
	
	@FXML
	private TextField KNN;
	
	@FXML
	private TextField textTime;
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize()
	{
		comboChooseAlgorithm.getItems().add("Naive Bayes");
		comboChooseAlgorithm.getItems().add("IBk - KNN");
		
		comboKNNDistance.getItems().add("Chebyshev Distance");
		comboKNNDistance.getItems().add("Euclidean Distance");
		comboKNNDistance.getItems().add("Filtered Distance");
		comboKNNDistance.getItems().add("Manhattan Distance");
		comboKNNDistance.getItems().add("Minkowski Distance");
		
		algorithmChanged();
	}
	
	public RunAlgorithmController() 
	{
		
	}

	@FXML
	private void algorithmChanged()
	{
		int selectedItem = comboChooseAlgorithm.getSelectionModel().getSelectedIndex();
		if (selectedItem != 1)
		{
			comboKNNDistance.visibleProperty().set(false);
			KNN.visibleProperty().set(false);
		}
		else
		{
			comboKNNDistance.visibleProperty().set(true);
			KNN.visibleProperty().set(true);
		}
	}
	
	private IBk AlgorithmIBk(int distance) throws Exception
	{
		IBk ibk = new IBk();
		ibk.setKNN(Context.getKNN());
		if (distance != -1)
		{
			switch (distance)
			{
			case 0: { ibk.getNearestNeighbourSearchAlgorithm().setDistanceFunction(new ChebyshevDistance()); }break;
			case 1: { ibk.getNearestNeighbourSearchAlgorithm().setDistanceFunction(new EuclideanDistance()); }break;
			case 2: { ibk.getNearestNeighbourSearchAlgorithm().setDistanceFunction(new FilteredDistance()); }break;
			case 3: { ibk.getNearestNeighbourSearchAlgorithm().setDistanceFunction(new ManhattanDistance()); }break;
			case 4: { ibk.getNearestNeighbourSearchAlgorithm().setDistanceFunction(new MinkowskiDistance()); }break;
			}
		}
		return ibk;
	}
	
	private NaiveBayes AlgorithmNaiveBayes() throws Exception
	{
		NaiveBayes nb = new NaiveBayes();
		// options
		return nb;
	}
	
	@FXML
	private void buttonStartClicked()
	{
		try
		{
			if (!KNN.getText().equals(""))
				Context.setKNN(Integer.parseInt(KNN.getText()));
			
			int selectedClassifierIndex = comboChooseAlgorithm.getSelectionModel().getSelectedIndex();
			int selectedKNNDistanceIndex = comboKNNDistance.getSelectionModel().getSelectedIndex();			
			
			Instances instances = Context.getLoadedInstance();
			Classifier classifier = null;
			
			switch (selectedClassifierIndex)
			{
			case 0: { classifier = (Classifier) AlgorithmNaiveBayes(); } break;
			case 1: { classifier = (Classifier) AlgorithmIBk(selectedKNNDistanceIndex); } break;
			}
			
			classifier.buildClassifier(instances);
			
			Evaluation evaluation = new Evaluation(instances);
			
			Long end = 0L;
			Long start = System.nanoTime();
			evaluation.crossValidateModel(classifier, instances, 10, new Random(1));
			end = System.nanoTime();

			
			textTime.setText("" + TimeUnit.MILLISECONDS.convert(end-start, TimeUnit.NANOSECONDS) + "ms");
			
			Context.setEvaluation(evaluation);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
}

