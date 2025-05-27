package it.univaq.disim.oop.myunivaq.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.myunivaq.domain.Studente;
import it.univaq.disim.oop.myunivaq.domain.Utente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HomeController implements Initializable, DataInitializable<Utente> {

	@FXML
	private Label benvenutoLabel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	@Override
	public void initializeData(Utente utente) {
		StringBuilder testo = new StringBuilder();
		testo.append("Benvenuto ");
		testo.append(utente.getNome());
		testo.append(" ");
		testo.append(utente.getCognome());

		if (utente instanceof Studente) {
			Studente studente = (Studente) utente;
			testo.append(" iscritto al corso di ");
			testo.append(studente.getIscritto().getNome());
		}
		benvenutoLabel.setText(testo.toString());
	}

}
