package application.controller;

import application.Context;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class RunAlgorithmController {
	
	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox comboChooseAlgorithm;
	
	@FXML
	private Button buttonStart;
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize()
	{
		comboChooseAlgorithm.getItems().add("NaiveBayes");
	}
	
	public RunAlgorithmController() 
	{
		
	}

	@FXML
	private void buttonStartClicked() throws Exception
	{
		int selectedClassifierIndex = comboChooseAlgorithm.getSelectionModel().getSelectedIndex();
		Instances instances = Context.getLoadedInstance();
		Classifier classifier = null;
		
		switch (selectedClassifierIndex)
		{
		case 0: {classifier = (Classifier)new NaiveBayes();} break;
		}

		classifier.buildClassifier(instances);
		
		Evaluation evaluation = new Evaluation(instances);
		evaluation.evaluateModel(classifier, instances);
		
		Context.setEvaluation(evaluation);
	}
	
	
}

