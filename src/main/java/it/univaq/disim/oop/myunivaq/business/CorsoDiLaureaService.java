package it.univaq.disim.oop.myunivaq.business;

import java.util.List;

import it.univaq.disim.oop.myunivaq.domain.CorsoDiLaurea;

public interface CorsoDiLaureaService {

	List<CorsoDiLaurea> findAllCorsiDiLaurea() throws BusinessException;

	CorsoDiLaurea findCorsoDiLaureaById(int id) throws BusinessException;

}
