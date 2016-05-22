package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class MainWindowController {

	@FXML
	private AnchorPane contentFrame;

	@FXML
	private Button buttonLoadData;

	@FXML
	private Button buttonStartAlgorithm;

	@FXML
	private Button buttonShowResults;

	@FXML
    private Button buttonSelectAttributes;

	@FXML
	private MenuItem menuButtonExit;

	@FXML
	private void initialize()
	{
		loadAsPanel("view/StartWindow.fxml");
	}

	public MainWindowController() {}

	// load window as panel ("view/win.fxml")
	private void loadAsPanel(String resourcePath)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(resourcePath));
			AnchorPane window = (AnchorPane) loader.load();
			contentFrame.getChildren().clear();
			contentFrame.getChildren().add(window);
			AnchorPane.setTopAnchor(window, 5.0);
			AnchorPane.setBottomAnchor(window, 5.0);
			AnchorPane.setLeftAnchor(window, 5.0);
			AnchorPane.setRightAnchor(window, 5.0);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}


	// load as new window ("../view/win.fxml")
	/*
	private void loadAsWindow(String resourcePath, String title)
	{
		try {
			Stage stage;
			Parent root;

			stage = new Stage();

			root = FXMLLoader.load(getClass().getResource(resourcePath));

			stage.setScene(new Scene(root));
			stage.setTitle(title);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/

	@FXML
	private void buttonLoadDataClicked()
	{
		loadAsPanel("view/LoadFileWindow.fxml");
	}

	@FXML
	private void buttonStartAlgorithmClicked()
	{
		loadAsPanel("view/RunAlgorithmWindow.fxml");
	}

	@FXML
	private void buttonShowResultsClicked()
	{
		loadAsPanel("view/DisplayResultsWindow.fxml");
	}

	@FXML
    private void buttonSelectAttributesClicked()
    {
        loadAsPanel("view/SelectAttributesWindow.fxml");
    }

	@FXML
	private void menuButtonExitClicked()
	{
		System.exit(0);
	}
}
