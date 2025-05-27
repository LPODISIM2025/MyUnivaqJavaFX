package it.univaq.disim.oop.myunivaq.business.impl.ram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.myunivaq.business.BusinessException;
import it.univaq.disim.oop.myunivaq.business.CorsoDiLaureaService;
import it.univaq.disim.oop.myunivaq.business.InsegnamentoService;
import it.univaq.disim.oop.myunivaq.domain.Appello;
import it.univaq.disim.oop.myunivaq.domain.CorsoDiLaurea;
import it.univaq.disim.oop.myunivaq.domain.Docente;
import it.univaq.disim.oop.myunivaq.domain.Insegnamento;
import it.univaq.disim.oop.myunivaq.domain.TipologiaEsame;

public class RAMInsegnamentoServiceImpl implements InsegnamentoService {

	private static List<Appello> appelliAggiunti = new ArrayList<>();
	private static int idCounter = 1;
	private CorsoDiLaureaService corsoDiLaureaService;
	
	public RAMInsegnamentoServiceImpl(CorsoDiLaureaService corsoDiLaureaService) {
		this.corsoDiLaureaService = corsoDiLaureaService;
	}

	@Override
	public List<Insegnamento> findAllInsegnamenti(Docente docente) throws BusinessException {
		List<Insegnamento> result = new ArrayList<>();
		int id = 1;
		
		CorsoDiLaurea informatica = corsoDiLaureaService.findCorsoDiLaureaById(1);
		Insegnamento oop = new Insegnamento();
		oop.setId(id++);
		oop.setCodice("DT0539");
		oop.setDenominazione("Laboratorio di Programmazione ad Oggetti");
		oop.setCorsoDiLaurea(informatica);
		result.add(oop);

		Insegnamento webavanzato = new Insegnamento();
		webavanzato.setId(id++);
		webavanzato.setCodice("DT0209");
		webavanzato.setDenominazione("Sviluppo web Avanzato");
		webavanzato.setCorsoDiLaurea(informatica);
		result.add(webavanzato);

		Insegnamento mobile = new Insegnamento();
		mobile.setId(id++);
		mobile.setCodice("F1081");
		mobile.setDenominazione("Applicazioni per dispositivi mobili");
		mobile.setCorsoDiLaurea(informatica);
		result.add(mobile);
		
		CorsoDiLaurea masterMWT = corsoDiLaureaService.findCorsoDiLaureaById(2);
		Insegnamento jakartaJEE = new Insegnamento();
		jakartaJEE.setId(id++);
		jakartaJEE.setCodice("F2035");
		jakartaJEE.setDenominazione("Piattaforma JEE");
		jakartaJEE.setCorsoDiLaurea(masterMWT);

		result.add(jakartaJEE);

		return result;
	}

	@Override
	public List<Appello> findAllAppelli(Insegnamento insegnamento) throws BusinessException {
		List<Appello> result = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			Appello appello = new Appello();
			appello.setId(i);
			appello.setDescrizione("Appello numero " + i);
			appello.setData(LocalDate.of(2020, i, i));
			appello.setTipologiaEsame(TipologiaEsame.ORALE);
			appello.setInsegnamento(insegnamento);
			result.add(appello);
		}
		result.addAll(appelliAggiunti);
		return result;
	}

	@Override
	public void createAppello(Appello appello) throws BusinessException {
		appello.setId(idCounter++);
		appelliAggiunti.add(appello);
	}

	@Override
	public void updateAppello(Appello appello) throws BusinessException {
		for (Appello app : appelliAggiunti) {
			if (appello.getId() == app.getId()) {
				app.setDescrizione(appello.getDescrizione());
				app.setData(app.getData());
			}
		}
	}
	
}
