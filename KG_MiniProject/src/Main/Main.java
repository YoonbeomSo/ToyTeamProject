package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginMain.FXML"));
		Parent loginForm = loader.load();
		
		Controller mainController = new Controller();
		
		primaryStage.setTitle("LoginMainPage");
		primaryStage.setScene(new Scene(loginForm));
		primaryStage.show();
	}
	
	
}
