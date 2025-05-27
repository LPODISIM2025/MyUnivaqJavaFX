package it.univaq.disim.oop.myunivaq.business.impl.ram;

import it.univaq.disim.oop.myunivaq.business.BusinessException;
import it.univaq.disim.oop.myunivaq.business.CorsoDiLaureaService;
import it.univaq.disim.oop.myunivaq.business.UtenteNotFoundException;
import it.univaq.disim.oop.myunivaq.business.UtenteService;
import it.univaq.disim.oop.myunivaq.domain.CorsoDiLaurea;
import it.univaq.disim.oop.myunivaq.domain.Docente;
import it.univaq.disim.oop.myunivaq.domain.Studente;
import it.univaq.disim.oop.myunivaq.domain.Utente;

public class RAMUtenteServiceImpl implements UtenteService {
	
	private CorsoDiLaureaService corsoDiLaureaService;
	
	public RAMUtenteServiceImpl(CorsoDiLaureaService corsoDiLaureaService) {
		this.corsoDiLaureaService = corsoDiLaureaService;
	}

	@Override
	public Utente authenticate(String username, String password) throws BusinessException {
		if ("docente".equalsIgnoreCase(username)) {
			Utente docente = new Docente();
			docente.setUsername(username);
			docente.setPassword(password);
			docente.setNome("Juri");
			docente.setCognome("Di Rocco");
			return docente;
		} 
		if ("studente".equalsIgnoreCase(username)) {
			Studente studente = new Studente();
			studente.setUsername(username);
			studente.setPassword(password);
			studente.setNome("Paolo");
			studente.setCognome("Rossi");
			CorsoDiLaurea corsoDiLaurea = corsoDiLaureaService.findCorsoDiLaureaById(1);
			studente.setIscritto(corsoDiLaurea);
			return studente;
		}
		throw new UtenteNotFoundException();
	}

}
