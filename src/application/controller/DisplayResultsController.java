package application.controller;

import javax.swing.JOptionPane;

import application.Context;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import weka.classifiers.Evaluation;

public class DisplayResultsController {

	@FXML
	private TextArea textAreaResults;

	@FXML
	private void initialize() throws Exception
	{
	    textAreaResults.setEditable(false);
	    textAreaResults.setFont(new Font("Courier New", 12));

		loadResults();
	}

	public DisplayResultsController() {}

	private void loadResults()
	{
	    try
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
	    catch (Exception e)
	    {
	        JOptionPane.showMessageDialog(null, "Najpierw uruchom algorytm.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
