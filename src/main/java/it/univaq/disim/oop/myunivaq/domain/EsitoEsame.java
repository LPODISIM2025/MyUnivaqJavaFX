package it.univaq.disim.oop.myunivaq.domain;

import java.time.LocalDate;

public class EsitoEsame {

	private Integer id;
	private int voto;
	private LocalDate dataVerbalizzazione;
	
	private Studente studente;
	private Appello appello;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getDataVerbalizzazione() {
		return dataVerbalizzazione;
	}

	public void setDataVerbalizzazione(LocalDate dataVerbalizzazione) {
		this.dataVerbalizzazione = dataVerbalizzazione;
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

}
