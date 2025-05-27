package it.univaq.disim.oop.myunivaq.business.impl.file;

import java.io.File;

import it.univaq.disim.oop.myunivaq.business.CorsoDiLaureaService;
import it.univaq.disim.oop.myunivaq.business.InsegnamentoService;
import it.univaq.disim.oop.myunivaq.business.MyUnivaqBusinessFactory;
import it.univaq.disim.oop.myunivaq.business.UtenteService;

public class FileMyUnivaqBusinessFactoryImpl extends MyUnivaqBusinessFactory {

	private UtenteService utenteService;
	private InsegnamentoService insegnamentoService;
	private CorsoDiLaureaService corsoDiLaureaService;

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String UTENTI_FILE_NAME = REPOSITORY_BASE + File.separator + "utenti.txt";
	private static final String INSEGNAMENTI_FILE_NAME = REPOSITORY_BASE + File.separator + "insegnamenti.txt";
	private static final String APPELLI_FILE_NAME = REPOSITORY_BASE + File.separator + "appelli.txt";
	private static final String CORSI_DI_LAUREA_FILE_NAME = REPOSITORY_BASE + File.separator + "corsidilaurea.txt";

	public FileMyUnivaqBusinessFactoryImpl() {
		corsoDiLaureaService = new FileCorsoDiLaureaServiceImpl(CORSI_DI_LAUREA_FILE_NAME);
		utenteService = new FileUtenteServiceImpl(UTENTI_FILE_NAME, corsoDiLaureaService);
		insegnamentoService = new FileInsegnamentoServiceImpl(INSEGNAMENTI_FILE_NAME, APPELLI_FILE_NAME, corsoDiLaureaService);		
	}

	@Override
	public UtenteService getUtenteService() {
		return utenteService;
	}

	@Override
	public InsegnamentoService getInsegnamentoService() {
		return insegnamentoService;
	}

	@Override
	public CorsoDiLaureaService getCorsoDiLaureaService() {
		return corsoDiLaureaService;
	}

}