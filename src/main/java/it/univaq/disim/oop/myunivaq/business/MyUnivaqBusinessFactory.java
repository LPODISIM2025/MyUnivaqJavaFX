package it.univaq.disim.oop.myunivaq.business;

import it.univaq.disim.oop.myunivaq.business.impl.file.FileMyUnivaqBusinessFactoryImpl;

public abstract class MyUnivaqBusinessFactory {
	
	//private static MyUnivaqBusinessFactory factory = new RAMMyUnivaqBusinessFactoryImpl();
	private static MyUnivaqBusinessFactory factory = new FileMyUnivaqBusinessFactoryImpl();
	
	public static MyUnivaqBusinessFactory getInstance() {
		return factory;
	}
	
	public abstract UtenteService getUtenteService();
	public abstract InsegnamentoService getInsegnamentoService();
	public abstract CorsoDiLaureaService getCorsoDiLaureaService();
}
