package it.univaq.disim.oop.myunivaq;

import it.univaq.disim.oop.myunivaq.view.ViewDispatcher;
import it.univaq.disim.oop.myunivaq.view.ViewException;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyUnivaqApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			ViewDispatcher viewDispatcher = ViewDispatcher.getInstance();
			viewDispatcher.loginView(stage);
		} catch (ViewException e) {
			e.printStackTrace();
		}
	}

}
