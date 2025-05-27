package it.univaq.disim.oop.myunivaq.business;

import it.univaq.disim.oop.myunivaq.domain.Utente;

public interface UtenteService {

	Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException;
	
}
