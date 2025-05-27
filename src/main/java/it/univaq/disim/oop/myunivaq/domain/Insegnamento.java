package it.univaq.disim.oop.myunivaq.domain;

public class Insegnamento {

	private Integer id;
	private String codice;
	private String denominazione;
	private Lingua lingua;
	private int cfu;
	private TipologiaCredito tipologiaCredito;
	private int periodoInsegnamento;

	private Docente docente;
	private CorsoDiLaurea corsoDiLaurea;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Lingua getLingua() {
		return lingua;
	}

	public void setLingua(Lingua lingua) {
		this.lingua = lingua;
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public TipologiaCredito getTipologiaCredito() {
		return tipologiaCredito;
	}

	public void setTipologiaCredito(TipologiaCredito tipologiaCredito) {
		this.tipologiaCredito = tipologiaCredito;
	}

	public int getPeriodoInsegnamento() {
		return periodoInsegnamento;
	}

	public void setPeriodoInsegnamento(int periodoInsegnamento) {
		this.periodoInsegnamento = periodoInsegnamento;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public CorsoDiLaurea getCorsoDiLaurea() {
		return corsoDiLaurea;
	}

	public void setCorsoDiLaurea(CorsoDiLaurea corsoDiLaurea) {
		this.corsoDiLaurea = corsoDiLaurea;
	}

}
