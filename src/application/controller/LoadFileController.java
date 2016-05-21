package application.controller;

import java.io.File;

import javax.swing.JOptionPane;

import application.Context;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class LoadFileController {

	private File loadedFile;

	@FXML
	private Button buttonChooseFile;

	@FXML
	private TextField textFilePath;

	@FXML
	private Button buttonLoadFile;

	@FXML
	private void initialize() {}

	public LoadFileController() {}

	private File loadFile()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Wybierz plik z danymi");

		return fileChooser.showOpenDialog(null);
	}

	private Instances loadCSVFile(File file) throws Exception
	{
		CSVLoader loader = new CSVLoader();
	    loader.setSource(file);
	    loader.setFieldSeparator(";");

	    Instances data = loader.getDataSet();
	    data.setClassIndex(data.numAttributes() - 1);

	    return data;
	}

	@FXML
	private void buttonChooseFileClicked()
	{
		File file = loadFile();

		if (file != null)
		{
			loadedFile = file;
			textFilePath.setText(file.getAbsolutePath());
		}
	}

	@FXML
	private void buttonLoadFileClicked()
	{
		try
		{
			Instances instances = loadCSVFile(loadedFile);
			if (instances != null)
			{
				// Zaladowano plik
				Context.setLoadedInstance(instances);
				JOptionPane.showMessageDialog(null, "Plik zaladowany.");
			}
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
}
