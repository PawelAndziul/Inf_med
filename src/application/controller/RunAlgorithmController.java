package application.controller;

import application.Context;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import weka.classifiers.Classifier;
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
		Classifier classifier = null;
		
		switch (selectedClassifierIndex)
		{
		case 0: {classifier = (Classifier)new NaiveBayes();} break;
		}
		
		Instances instances = Context.getLoadedInstance();
		
		classifier.buildClassifier(instances);
		
	}
	
	
}

