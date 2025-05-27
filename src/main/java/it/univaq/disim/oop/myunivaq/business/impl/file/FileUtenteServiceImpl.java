package it.univaq.disim.oop.myunivaq.business.impl.file;

import java.io.IOException;

import it.univaq.disim.oop.myunivaq.business.BusinessException;
import it.univaq.disim.oop.myunivaq.business.CorsoDiLaureaService;
import it.univaq.disim.oop.myunivaq.business.UtenteNotFoundException;
import it.univaq.disim.oop.myunivaq.business.UtenteService;
import it.univaq.disim.oop.myunivaq.domain.CorsoDiLaurea;
import it.univaq.disim.oop.myunivaq.domain.Docente;
import it.univaq.disim.oop.myunivaq.domain.Studente;
import it.univaq.disim.oop.myunivaq.domain.Utente;

public class FileUtenteServiceImpl implements UtenteService {

	private String utentiFilename;
	private CorsoDiLaureaService corsoDiLaureaService;

	public FileUtenteServiceImpl(String utentiFilename, CorsoDiLaureaService corsoDiLaureaService) {
		this.utentiFilename = utentiFilename;
		this.corsoDiLaureaService = corsoDiLaureaService;
	}

	@Override
	public Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException {
		try {
			FileData fileData = Utility.readAllRows(utentiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(username) && colonne[2].equals(password)) {
					Utente utente = null;
					// colonna[3] identifica il ruolo
					switch (colonne[3]) {
						case "docente":
							utente = new Docente();
							break;
						case "studente":
							utente = new Studente();
							break;
						default:
							break;
					}
					if (utente != null) {
						utente.setId(Integer.parseInt(colonne[0]));
						utente.setUsername(username);
						utente.setPassword(password);
						utente.setNome(colonne[4]);
						utente.setCognome(colonne[5]);
					} else {
						throw new BusinessException("errore nella lettura del file");
					}
					if (utente instanceof Studente) {
						CorsoDiLaurea corsoDiLaurea = corsoDiLaureaService.findCorsoDiLaureaById(Integer.parseInt(colonne[6]));
						((Studente)utente).setIscritto(corsoDiLaurea);
					}
					return utente;
				}
			}
			throw new UtenteNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

}
