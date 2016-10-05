package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import util.DataUtil;
import mysqldao.FeriasDAO;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class EspecialistaFerias {
	FeriasTO feriasTO = new FeriasTO();;
	FeriasDAO feriasDAO = new FeriasDAO();	
	

	public void adicionar(String codigoContrato, Date periodoAquisitivoInicio, Date periodoAquisitivoFim, String situacao, Date dataInicio, Date dataFinal,
			int qtdDias, Double valor, Boolean vendaFerias){		
		
        feriasTO.setSituacao(situacao);
        feriasTO.setDataInicio(dataInicio);
        feriasTO.setDataFinal(dataFinal);
        feriasTO.setQtdDiasFerias(qtdDias);
        feriasTO.setValor(valor);
        feriasTO.setVendaFerias(vendaFerias);
        feriasTO.setCodigoContrato(codigoContrato);
        feriasTO.setPeriodoAquisitivoInicio(periodoAquisitivoInicio);
        feriasTO.setPeriodoAquisitivoFim(periodoAquisitivoFim);
    	feriasDAO.cadastrarFerias(feriasTO);
	}
	
	
	public void alterar(String codigo, Date periodoAquisitivoInicio, Date periodoAquisitivoFim, Date periodoAqFim, String situacao, Date dataInicio, Date dataFinal,
			int qtdDias, Double valor, Boolean vendaFerias){	
		
		feriasTO.setPeriodoAquisitivoInicio(periodoAquisitivoInicio);
        feriasTO.setPeriodoAquisitivoFim(periodoAquisitivoFim);
        feriasTO.setSituacao(situacao);
        feriasTO.setDataInicio(dataInicio);
        feriasTO.setDataFinal(dataFinal);
        feriasTO.setQtdDiasFerias(qtdDias);
        feriasTO.setValor(valor);
        feriasTO.setVendaFerias(vendaFerias);
        feriasTO.setCodigoContrato(codigo);
		
		feriasDAO.alterarFerias(feriasTO);
  
	}
	
	 public void excluir(String cod){
		
		feriasDAO.excluirFerias(cod);

	}
	
	public void alterarPagamento(String codigo, Date diaPagamento, String situacao)
	{
		 feriasTO.setDiaPagamento(diaPagamento);
		 feriasTO.setSituacao(situacao);
         feriasTO.setCodigo(codigo);
         
         feriasDAO.alterarPagamento(feriasTO);
	}
	
	public ArrayList<FeriasTO> pesquisarTodos(String codigo){
		ArrayList<FeriasTO> feriasTO = new ArrayList<FeriasTO>();
		feriasTO  = feriasDAO.pesquisarTodos(codigo);
		return feriasTO;
	}
	
	public FeriasTO pesquisarFerias(String codigo)
	{
		feriasTO  = feriasDAO.pesquisarFerias(codigo);
		return feriasTO;
	}
	
}

