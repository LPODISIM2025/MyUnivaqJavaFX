package it.univaq.disim.oop.myunivaq.business.impl.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.myunivaq.business.BusinessException;
import it.univaq.disim.oop.myunivaq.business.CorsoDiLaureaService;
import it.univaq.disim.oop.myunivaq.domain.CorsoDiLaurea;

public class FileCorsoDiLaureaServiceImpl implements CorsoDiLaureaService {

	private String corsiDiLaureaFilename;

	public FileCorsoDiLaureaServiceImpl(String corsiDiLaureaFilename) {
		this.corsiDiLaureaFilename = corsiDiLaureaFilename;
	}

	@Override
	public List<CorsoDiLaurea> findAllCorsiDiLaurea() throws BusinessException {
		List<CorsoDiLaurea> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(corsiDiLaureaFilename);
			for (String[] colonne : fileData.getRighe()) {
				// 1,F3I,Laurea in Informatica
				CorsoDiLaurea corsoDiLaurea = new CorsoDiLaurea();
				corsoDiLaurea.setId(Integer.parseInt(colonne[0]));
				corsoDiLaurea.setClasse(colonne[1]);
				corsoDiLaurea.setNome(colonne[2]);
				result.add(corsoDiLaurea);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public CorsoDiLaurea findCorsoDiLaureaById(int id) throws BusinessException {
		CorsoDiLaurea result = new CorsoDiLaurea();
		try {
			FileData fileData = Utility.readAllRows(corsiDiLaureaFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					// 1,F3I,Laurea in Informatica
					result.setId(id);
					result.setClasse(colonne[1]);
					result.setNome(colonne[2]);
					return result;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

}
