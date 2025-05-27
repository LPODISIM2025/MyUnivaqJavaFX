package it.univaq.disim.oop.myunivaq.view;

import java.io.IOException;

import it.univaq.disim.oop.myunivaq.controller.DataInitializable;
import it.univaq.disim.oop.myunivaq.domain.Utente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewDispatcher {

	private static final String RESOURCE_BASE = "/viste/";
	private static final String FXML_SUFFIX = ".fxml";

	private static ViewDispatcher instance = new ViewDispatcher();

	private Stage stage;
	private BorderPane layout;

	private ViewDispatcher() {
	}

	public void loginView(Stage stage) throws ViewException {
		this.stage = stage;
		Parent loginView = loadView("login").getView();
		Scene scene = new Scene(loginView);
		stage.setScene(scene);
		stage.show();
	}

	public void loggedIn(Utente utente) {
		try {
			View<Utente> layoutView = loadView("layout");
			// Inizializza l'utente nel controllore.
			// Utilizzato per la visualizzazione dei menu a seconda se e' docente o studente
			DataInitializable<Utente> layoutController = layoutView.getController();
			layoutController.initializeData(utente);

			layout = (BorderPane) layoutView.getView();
			//Anche in questo caso viene passato l'utente perche' nella vista di 
			//benvenuto il testo varia a seconda se e' docente od utente
			renderView("home", utente);
			Scene scene = new Scene(layout);
			// Utilizzato per non rendere selezionabile una riga in una tabella.
			// Chiaramente in questo modo e' possibile configurare/modificare degli stili
			// comuni a tutta la scena
			scene.getStylesheets().add(getClass().getResource(RESOURCE_BASE + "styles.css").toExternalForm());
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
	}

	public void logout() {
		try {
			Parent loginView = loadView("login").getView();
			Scene scene = new Scene(loginView);
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
	}

	public <T> void renderView(String viewName, T data) {
		try {
			View<T> view = loadView(viewName);
			DataInitializable<T> controller = view.getController();
			controller.initializeData(data);
			layout.setCenter(view.getView());
		} catch (ViewException e) {
			renderError(e);
		}
	}

	public void renderError(Exception e) {
		e.printStackTrace();
		System.exit(1);
	}

	public static ViewDispatcher getInstance() {
		return instance;
	}

	private <T> View<T> loadView(String viewName) throws ViewException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(RESOURCE_BASE + viewName + FXML_SUFFIX));
			Parent parent = (Parent) loader.load();
			return new View<>(parent, loader.getController());

		} catch (IOException ex) {
			throw new ViewException(ex);
		}
	}
}
