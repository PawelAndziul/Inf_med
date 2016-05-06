package application.controller;

import application.Context;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import weka.classifiers.Evaluation;

public class DisplayResultsController {
	
	@FXML
	private TextArea textAreaResults;
	
	@FXML
	private void initialize() throws Exception
	{
		loadResults();
	}
	
	public DisplayResultsController() 
	{
		
	}
	
	private void loadResults() throws Exception
	{
		Evaluation evaluation = Context.getEvaluation();
		
		String text = "=== Stratified cross-validation ===\n\n";
		String summary = evaluation.toSummaryString();
		text += summary + "\n\n";
		
		String accuracy = evaluation.toClassDetailsString();
		text += accuracy + "\n\n";

		text += evaluation.toMatrixString();

		
		textAreaResults.setText(text);
	}
	
}

