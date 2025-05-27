package it.univaq.disim.oop.myunivaq.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.myunivaq.business.BusinessException;
import it.univaq.disim.oop.myunivaq.business.InsegnamentoService;
import it.univaq.disim.oop.myunivaq.business.MyUnivaqBusinessFactory;
import it.univaq.disim.oop.myunivaq.domain.Appello;
import it.univaq.disim.oop.myunivaq.domain.TipologiaEsame;
import it.univaq.disim.oop.myunivaq.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AppelloController implements Initializable, DataInitializable<Appello> {

	@FXML
	private TextField descrizione;

	@FXML
	private DatePicker dataAppello;

	@FXML
	private ComboBox<TipologiaEsame> tipologiaEsame;

	@FXML
	private Button salvaButton;

	private ViewDispatcher dispatcher;
	private InsegnamentoService insegnamentoService;

	private Appello appello;

	public AppelloController() {
		dispatcher = ViewDispatcher.getInstance();
		MyUnivaqBusinessFactory factory = MyUnivaqBusinessFactory.getInstance();
		insegnamentoService = factory.getInsegnamentoService();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tipologiaEsame.getItems().addAll(TipologiaEsame.values());
	}

	@Override
	public void initializeData(Appello appello) {
		this.appello = appello;
		this.descrizione.setText(appello.getDescrizione());
		this.tipologiaEsame.setValue(appello.getTipologiaEsame());
		this.dataAppello.setValue(appello.getData());
		this.dataAppello.setEditable(false);
		salvaButton.disableProperty().bind(descrizione.textProperty().isEmpty());

	}

	@FXML
	public void salvaAction(ActionEvent event) {

		try {
			appello.setDescrizione(descrizione.getText());
			appello.setData(dataAppello.getValue());
			appello.setTipologiaEsame(tipologiaEsame.getValue());

			if (appello.getId() == null) {
				insegnamentoService.createAppello(appello);
			} else {
				insegnamentoService.updateAppello(appello);
			}
				
			dispatcher.renderView("appelli", appello.getInsegnamento());
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.renderView("appelli", appello.getInsegnamento());
	}

}
