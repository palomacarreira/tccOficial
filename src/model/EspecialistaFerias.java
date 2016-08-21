package model;

import java.util.ArrayList;

import mysqldao.FeriasDAO;

public class EspecialistaFerias {
	FeriasTO feriasTO = new FeriasTO();
	FeriasDAO feriasDAO = new FeriasDAO();	
	

	public void adicionar(String codigoContrato, String periodoAqInicio, String periodoAqFim, String situacao, String dataInicio, String dataFinal,
			int qtdDias, Double valor, Boolean vendaFerias){		
		
		feriasTO.setPeriodoAquisitivoInicio(periodoAqInicio);
		feriasTO.setPeriodoAquisitivoFim(periodoAqFim);
        feriasTO.setSituacao(situacao);
        feriasTO.setDataInicio(dataInicio);
        feriasTO.setDataFinal(dataFinal);
        feriasTO.setQtdDiasFerias(qtdDias);
        feriasTO.setValor(valor);
        feriasTO.setVendaFerias(vendaFerias);
        feriasTO.setCodigoContrato(codigoContrato);
	
    	feriasDAO.cadastrarFerias(feriasTO);
	}
	
	
	public void alterar(String codigo, String periodoAqInicio, String periodoAqFim, String situacao, String dataInicio, String dataFinal,
			int qtdDias, Double valor, Boolean vendaFerias){	
		
		feriasTO.setPeriodoAquisitivoInicio(periodoAqInicio);
		feriasTO.setPeriodoAquisitivoFim(periodoAqFim);
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
	
	public void alterarPagamento(String codigo, String diaPagamento)
	{
		 feriasTO.setDiaPagamento(diaPagamento);
         feriasTO.setCodigo(codigo);
         
         feriasDAO.alterarPagamento(feriasTO);
	}
	
	public ArrayList<FeriasTO> pesquisarTodos(String codigo){
		ArrayList<FeriasTO> feriasTO = new ArrayList<FeriasTO>();
		feriasTO  = feriasDAO.pesquisarFerias(codigo);
		return feriasTO;
	}
}

