package it.univaq.disim.oop.myunivaq.business.impl.ram;

import it.univaq.disim.oop.myunivaq.business.CorsoDiLaureaService;
import it.univaq.disim.oop.myunivaq.business.InsegnamentoService;
import it.univaq.disim.oop.myunivaq.business.MyUnivaqBusinessFactory;
import it.univaq.disim.oop.myunivaq.business.UtenteService;

public class RAMMyUnivaqBusinessFactoryImpl extends MyUnivaqBusinessFactory {

	private UtenteService utenteService;
	private InsegnamentoService insegnamentoService;
	private CorsoDiLaureaService corsoDiLaureaService;

	public RAMMyUnivaqBusinessFactoryImpl() {
		corsoDiLaureaService = new RAMCorsoDiLaureaServiceImpl();
		utenteService = new RAMUtenteServiceImpl(corsoDiLaureaService);
		insegnamentoService = new RAMInsegnamentoServiceImpl(corsoDiLaureaService);
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
