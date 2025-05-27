package it.univaq.disim.oop.myunivaq.domain;

import java.util.HashSet;
import java.util.Set;

public class Docente extends Utente {

	private Set<Insegnamento> titolare = new HashSet<>();

	public Set<Insegnamento> getTitolare() {
		return titolare;
	}

	public void setTitolare(Set<Insegnamento> titolare) {
		this.titolare = titolare;
	}


}
