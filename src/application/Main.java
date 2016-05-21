package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane mainFrame;

	public static void main(String[] args)
	{
		launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Analiza stopnia zlosliwosci komorek nowotworowych");

		try
		{
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/MainWindow.fxml"));
	        mainFrame = (BorderPane) loader.load();

			Scene scene = new Scene(mainFrame);

			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
