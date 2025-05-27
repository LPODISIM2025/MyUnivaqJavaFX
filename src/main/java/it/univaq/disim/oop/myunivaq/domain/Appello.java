package it.univaq.disim.oop.myunivaq.domain;

import java.time.LocalDate;

public class Appello {

	private Integer id;
	private LocalDate data;
	private String descrizione;
	private TipologiaEsame tipologiaEsame = TipologiaEsame.ORALE;

	private Insegnamento insegnamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TipologiaEsame getTipologiaEsame() {
		return tipologiaEsame;
	}

	public void setTipologiaEsame(TipologiaEsame tipologiaEsame) {
		this.tipologiaEsame = tipologiaEsame;
	}

	public Insegnamento getInsegnamento() {
		return insegnamento;
	}

	public void setInsegnamento(Insegnamento insegnamento) {
		this.insegnamento = insegnamento;
	}


}
