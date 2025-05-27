package it.univaq.disim.oop.myunivaq.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.myunivaq.domain.Docente;
import it.univaq.disim.oop.myunivaq.domain.Studente;
import it.univaq.disim.oop.myunivaq.domain.Utente;
import it.univaq.disim.oop.myunivaq.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class LayoutController implements Initializable, DataInitializable<Utente> {
	private static final MenuElement MENU_HOME = new MenuElement("Home", "home");

	private static final MenuElement[] MENU_DOCENTI = { new MenuElement("Gestione Esami", "insegnamenti"),
			new MenuElement("Approvazione Piani", "piani"), new MenuElement("Sedute Di Laurea", "sedute-laurea"),
			new MenuElement("Laureandi Assegnati", "laureandi"), new MenuElement("Lezioni", "lezioni"),
			new MenuElement("Diario", "diario"), new MenuElement("Conseguimento Titoli", "titoli"),
			new MenuElement("Questionari", "questionari") };

	private static final MenuElement[] MENU_STUDENTI = { new MenuElement("Appelli", "appelli-studente"),
			new MenuElement("Orario", "orario"), new MenuElement("Piano di Studi", "piano-di-studi"),
			new MenuElement("Tasse", "tasse"), new MenuElement("Libretto", "libretto") };

	private Utente utente;

	@FXML
	private VBox menuBar;

	private ViewDispatcher dispatcher;

	public LayoutController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	}

	@Override
	public void initializeData(Utente utente) {
		this.utente = utente;
		menuBar.getChildren().addAll(createButton(MENU_HOME));
		menuBar.getChildren().add(new Separator());

		if (utente instanceof Docente) {
			for (MenuElement menu : MENU_DOCENTI) {
				menuBar.getChildren().add(createButton(menu));
			}
		}
		if (utente instanceof Studente) {
			for (MenuElement menu : MENU_STUDENTI) {
				menuBar.getChildren().add(createButton(menu));
			}
		}
	}

	@FXML
	public void esciAction(MouseEvent event) {
		dispatcher.logout();
	}

	private Button createButton(MenuElement viewItem) {
		Button button = new Button(viewItem.getNome());
		button.setStyle("-fx-background-color: transparent; -fx-font-size: 14;");
		button.setTextFill(Paint.valueOf("white"));
		button.setPrefHeight(10);
		button.setPrefWidth(180);
		button.setOnAction((ActionEvent event) -> {
			dispatcher.renderView(viewItem.getVista(), utente);
		});
		return button;
	}

}