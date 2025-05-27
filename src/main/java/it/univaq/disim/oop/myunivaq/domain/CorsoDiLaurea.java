package it.univaq.disim.oop.myunivaq.domain;

import java.util.HashSet;
import java.util.Set;

public class CorsoDiLaurea {

	private Integer id;
	private String nome;
	private String classe;

	private Set<Studente> iscritti = new HashSet<>();
	private Set<Insegnamento> insegnamenti = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public Set<Studente> getIscritti() {
		return iscritti;
	}

	public void setIscritti(Set<Studente> iscritti) {
		this.iscritti = iscritti;
	}

	public Set<Insegnamento> getInsegnamenti() {
		return insegnamenti;
	}

	public void setInsegnamenti(Set<Insegnamento> insegnamenti) {
		this.insegnamenti = insegnamenti;
	}

}
