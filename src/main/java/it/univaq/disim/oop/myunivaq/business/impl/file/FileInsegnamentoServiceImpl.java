package it.univaq.disim.oop.myunivaq.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import it.univaq.disim.oop.myunivaq.domain.Lingua;
import it.univaq.disim.oop.myunivaq.domain.TipologiaCredito;
import it.univaq.disim.oop.myunivaq.domain.TipologiaEsame;

public class FileInsegnamentoServiceImpl implements InsegnamentoService {

	private String insegnamentiFilename;
	private String appelliFilename;
	private CorsoDiLaureaService corsoDiLaureaService;

	public FileInsegnamentoServiceImpl(String insegnamentiFilename, String appelliFilename, CorsoDiLaureaService corsoDiLaureaService ) {
		this.insegnamentiFilename = insegnamentiFilename;
		this.appelliFilename = appelliFilename;
		this.corsoDiLaureaService = corsoDiLaureaService;
	}
	
	@Override
	public List<Insegnamento> findAllInsegnamenti(Docente docente) throws BusinessException {
		List<Insegnamento> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(insegnamentiFilename);
			for (String[] colonne : fileData.getRighe()) {
				//1,DT0539,Laboratorio di Programmazione ad Oggetti,ITA,6,b,2,1,1
				if (colonne[8].equals(docente.getId().toString())) {
					Insegnamento insegnamento = new Insegnamento();
					insegnamento.setId(Integer.parseInt(colonne[0]));
					insegnamento.setCodice(colonne[1]);
					insegnamento.setDenominazione(colonne[2]);
					insegnamento.setLingua(Lingua.valueOf(colonne[3]));
					insegnamento.setCfu(Integer.parseInt(colonne[4]));
					insegnamento.setTipologiaCredito(TipologiaCredito.valueOf(colonne[5]));
					insegnamento.setPeriodoInsegnamento(Integer.parseInt(colonne[6]));
					
					CorsoDiLaurea corsoDiLaurea = corsoDiLaureaService.findCorsoDiLaureaById(Integer.parseInt(colonne[7]));
					insegnamento.setCorsoDiLaurea(corsoDiLaurea);
					insegnamento.setDocente(docente);
					result.add(insegnamento);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}
	
	@Override
	public List<Appello> findAllAppelli(Insegnamento insegnamento) throws BusinessException {
		List<Appello> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(appelliFilename);
			for (String[] colonne : fileData.getRighe()) {
				// 1,2020-06-10,Primo Appello sessione estiva,ORALE,1
				if (colonne[4].equals(insegnamento.getId().toString())) {
					Appello appello = new Appello();
					appello.setId(Integer.parseInt(colonne[0]));
					appello.setData(LocalDate.parse(colonne[1]));
					appello.setDescrizione(colonne[2]);
					appello.setTipologiaEsame(TipologiaEsame.valueOf(colonne[3]));
					appello.setInsegnamento(insegnamento);
					result.add(appello);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public void createAppello(Appello appello) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(appelliFilename);
			try (PrintWriter writer = new PrintWriter(new File(appelliFilename))) {
				long contatore = fileData.getContatore();
				writer.println((contatore + 1));
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(appello.getData().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(appello.getDescrizione());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(appello.getTipologiaEsame().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(appello.getInsegnamento().getId());
				writer.println(row.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateAppello(Appello appello) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(appelliFilename);
			try (PrintWriter writer = new PrintWriter(new File(appelliFilename))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == appello.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(appello.getId());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(appello.getData().toString());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(appello.getDescrizione());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(appello.getTipologiaEsame().toString());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(appello.getInsegnamento().getId());
						writer.println(row.toString());
					} else {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

}
