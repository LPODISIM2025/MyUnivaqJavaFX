package it.univaq.disim.oop.myunivaq.business;

import java.util.List;

import it.univaq.disim.oop.myunivaq.domain.Appello;
import it.univaq.disim.oop.myunivaq.domain.Docente;
import it.univaq.disim.oop.myunivaq.domain.Insegnamento;

public interface InsegnamentoService {

	List<Insegnamento> findAllInsegnamenti(Docente docente) throws BusinessException;
	
	List<Appello> findAllAppelli(Insegnamento insegnamento) throws BusinessException;

	void createAppello(Appello appello) throws BusinessException;

	void updateAppello(Appello appello) throws BusinessException;
}
