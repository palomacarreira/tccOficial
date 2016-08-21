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
	FeriasTO feriasTO = new FeriasTO();
	FeriasDAO feriasDAO = new FeriasDAO();	
	

	public void adicionar(String codigoContrato, Date periodoAqInicio, Date periodoAqFim, String situacao, Date dataInicio, Date dataFinal,
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
	
	
	public void alterar(String codigo, Date periodoAqInicio, Date periodoAqFim, String situacao, Date dataInicio, Date dataFinal,
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
	
	public ArrayList<FeriasTO> ferias(String codigo, Date dataAdmissao){
		ArrayList<FeriasTO> feriasTO = new ArrayList<FeriasTO>();
		feriasTO  = feriasDAO.pesquisarFerias(codigo);
		FeriasTO feriasTabela = new FeriasTO();
		
		GregorianCalendar dtAdmissao = new GregorianCalendar();
		GregorianCalendar dtHoje = new GregorianCalendar();
		GregorianCalendar dtAdmissaoMaisUmAno = new GregorianCalendar();
		
		
			dtAdmissao.setTime(dataAdmissao);
		dtAdmissaoMaisUmAno.setTime(dataAdmissao);
			dtAdmissaoMaisUmAno.add(Calendar.YEAR, 1);
		int qtdLinhas = 0;
			dtHoje.setTime(dtHoje.getTime());
		
		if(dtAdmissaoMaisUmAno.before(dtHoje)){//SE A DATA DE ADMISSAO FOR ANTERIOR A DATA ATUAL

			do{
				//feriasTabela.setPeriodoAquisitivoInicio((java.sql.Date) dataAdmissao);
				FeriasTO ferias = new FeriasTO();
				
				 java.util.Date utilDate = new java.util.Date();
				 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				    
				Date d = new Date(dtAdmissao.getTime().getTime());  
				Date d2 = new Date(dtAdmissaoMaisUmAno.getTime().getTime());
				ferias.setPeriodoAquisitivoInicio(d);
				ferias.setPeriodoAquisitivoFim(d2);
				
				feriasTO.add(ferias);//feriasTO.add(new FeriasTO());//CRIA A LINHA NA TABELA 
	
				qtdLinhas = qtdLinhas + 1;
				dtAdmissao.setTime(dtAdmissaoMaisUmAno.getTime());
				dtAdmissaoMaisUmAno.add(Calendar.YEAR, 1);
			
				    //DateTime start = new DateTime(dtAdmissaoMaisUmAno);
				    //DateTime end = new DateTime(dtHoje);
				    //Days.daysBetween(start, end).getDays();
				    //diferenca =   Days.daysBetween(start, end).getDays();
				
			 //esse código será executado pelo menos uma vez    
			}while(dtAdmissaoMaisUmAno.before(dtHoje));
			//while( dtAdmissaoMaisUmAno.before(dtHoje)||	dtAdmissao.equals(dtHoje));

	
		}
		
		//System.out.println(qtdLinhas);	
		//System.out.println(dtHoje.getTime());
		//System.out.println(dtAdmissao.getTime());
		
		//feriasTO.add(new FeriasTO());
		return feriasTO;
	}
}

