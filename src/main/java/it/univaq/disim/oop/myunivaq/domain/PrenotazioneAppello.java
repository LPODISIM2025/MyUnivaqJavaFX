package it.univaq.disim.oop.myunivaq.domain;

import java.time.LocalDate;

public class PrenotazioneAppello {

	private Integer id;
	private LocalDate dataPrenotazione;

	private Studente studente;
	private Appello appello;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Studente getStudente() {
		return studente;
	}

	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	public Appello getAppello() {
		return appello;
	}

	public void setAppello(Appello appello) {
		this.appello = appello;
	}

	public LocalDate getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(LocalDate dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

}
