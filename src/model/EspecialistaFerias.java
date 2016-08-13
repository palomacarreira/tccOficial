package model;

import java.sql.Date;
import java.util.ArrayList;

import mysqldao.FeriasDAO;

public class EspecialistaFerias {
	FeriasTO feriasTO = new FeriasTO();
	FeriasDAO feriasDAO = new FeriasDAO();	
	

	public void adicionar(String codigoContrato, String periodo, String situacao, Date dataInicio, Date dataFinal,
			int qtdDias, Double valor, Boolean vendaFerias){		
		
		feriasTO.setPeriodoAquisitivo(periodo);
        feriasTO.setSituacao(situacao);
        feriasTO.setDataInicio(dataInicio);
        feriasTO.setDataFinal(dataFinal);
        feriasTO.setQtdDiasFerias(qtdDias);
        feriasTO.setValor(valor);
        feriasTO.setVendaFerias(vendaFerias);
        feriasTO.setCodigoContrato(codigoContrato);
	
    	feriasDAO.cadastrarFerias(feriasTO);
	}
	
	
	public void alterar(String codigo, String periodo, String situacao, Date dataInicio, Date dataFinal,
			int qtdDias, Double valor, Boolean vendaFerias){	
		
		feriasTO.setPeriodoAquisitivo(periodo);
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
	
	public void alterarPagamento(String codigo, Date diaPagamento)
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

