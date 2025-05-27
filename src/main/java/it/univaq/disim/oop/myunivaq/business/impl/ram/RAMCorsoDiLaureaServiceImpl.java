package it.univaq.disim.oop.myunivaq.business.impl.ram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univaq.disim.oop.myunivaq.business.BusinessException;
import it.univaq.disim.oop.myunivaq.business.CorsoDiLaureaService;
import it.univaq.disim.oop.myunivaq.domain.CorsoDiLaurea;

public class RAMCorsoDiLaureaServiceImpl implements CorsoDiLaureaService {

	private Map<Integer, CorsoDiLaurea> corsiDiLaurea = new HashMap<>();

	public RAMCorsoDiLaureaServiceImpl() {
		CorsoDiLaurea informatica = new CorsoDiLaurea();
		informatica.setId(1);
		informatica.setClasse("F3I");
		informatica.setNome("Laurea in Informatica");
		corsiDiLaurea.put(informatica.getId(), informatica);

		CorsoDiLaurea masterMobileWebTechnologies = new CorsoDiLaurea();
		masterMobileWebTechnologies.setId(2);
		masterMobileWebTechnologies.setClasse("M4I");
		masterMobileWebTechnologies.setNome("Master Mobile e Web Technologies");
		corsiDiLaurea.put(masterMobileWebTechnologies.getId(), masterMobileWebTechnologies);
	}

	@Override
	public List<CorsoDiLaurea> findAllCorsiDiLaurea() throws BusinessException {
		return new ArrayList<>(corsiDiLaurea.values());
	}

	@Override
	public CorsoDiLaurea findCorsoDiLaureaById(int id) throws BusinessException {
		return corsiDiLaurea.get(id);
	}

}
