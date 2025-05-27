package it.univaq.disim.oop.myunivaq.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.myunivaq.business.BusinessException;
import it.univaq.disim.oop.myunivaq.business.InsegnamentoService;
import it.univaq.disim.oop.myunivaq.business.MyUnivaqBusinessFactory;
import it.univaq.disim.oop.myunivaq.domain.Docente;
import it.univaq.disim.oop.myunivaq.domain.Insegnamento;
import it.univaq.disim.oop.myunivaq.view.ViewDispatcher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InsegnamentiController implements Initializable, DataInitializable<Docente> {

	@FXML
	private TableView<Insegnamento> insegnamentiTable;

	@FXML
	private TableColumn<Insegnamento, String> codiceTableColumn;

	@FXML
	private TableColumn<Insegnamento, String> denominazioneTableColumn;

	@FXML
	private TableColumn<Insegnamento, String> corsoDiStudioTableColumn;

	@FXML
	private TableColumn<Insegnamento, Button> azioniTableColumn;

	private ViewDispatcher dispatcher;

	private InsegnamentoService insegnamentoService;

	public InsegnamentiController() {
		dispatcher = ViewDispatcher.getInstance();
		MyUnivaqBusinessFactory factory = MyUnivaqBusinessFactory.getInstance();
		insegnamentoService = factory.getInsegnamentoService();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		codiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("codice"));
		denominazioneTableColumn.setCellValueFactory(new PropertyValueFactory<>("denominazione"));
		corsoDiStudioTableColumn.setCellValueFactory((CellDataFeatures<Insegnamento, String> param) -> {
			return new SimpleStringProperty(param.getValue().getCorsoDiLaurea().getNome());
		});		
		
		/*
		// ESEMPIO DI COME RENDERE VIOLETTO LA CELLA CORSO DI STUDIO
		corsoDiStudioTableColumn.setCellFactory((TableColumn<Insegnamento, String> param) -> {
			return new TableCell<Insegnamento, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (!isEmpty()) {
						this.setTextFill(Color.RED);
						if (item.contains("Informatica")) {
							this.setTextFill(Color.BLUEVIOLET);
						}
						setText(item);
					}
				}
			};
		});
		*/
		azioniTableColumn.setStyle("-fx-alignment: CENTER;");
		azioniTableColumn.setCellValueFactory((CellDataFeatures<Insegnamento, Button> param) -> {
			final Button appelliButton = new Button("Appelli");
			appelliButton.setOnAction((ActionEvent event) -> {
				dispatcher.renderView("appelli", param.getValue());
			});
			return new SimpleObjectProperty<Button>(appelliButton);
		});

	}

	@Override
	public void initializeData(Docente docente) {
		try {
			List<Insegnamento> insegnamenti = insegnamentoService.findAllInsegnamenti(docente);
			ObservableList<Insegnamento> insegnamentiData = FXCollections.observableArrayList(insegnamenti);
			insegnamentiTable.setItems(insegnamentiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

}
