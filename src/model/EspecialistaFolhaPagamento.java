package model;

import java.sql.ResultSet;

import mysqldao.FolhaPagamentoDAO;


public class EspecialistaFolhaPagamento {

	FolhaPagamentoTO folhaPagamentoTO = new FolhaPagamentoTO();
	FolhaPagamentoDAO folhaPagamentoDAO = new FolhaPagamentoDAO();
	
	public void adicionarFolhaPagamento(Double decTercPrimeiro, Double DecTercSegunda, Double salarioLiquido,
			Double fgts, Double inss, Double irRetido, Double valeTransporte, Double beneficios,
			String mesReferencia, String anoReferencia, Double horaExtra) 
	{
			folhaPagamentoTO = new FolhaPagamentoTO();
	    	folhaPagamentoTO.setDecTercPrimeiro(decTercPrimeiro);
			folhaPagamentoTO.setDecTercSegunda(DecTercSegunda);
			folhaPagamentoTO.setSalarioLiquido(salarioLiquido);
			folhaPagamentoTO.setFgts(fgts);
			folhaPagamentoTO.setInss(inss);
			folhaPagamentoTO.setIrRetido(irRetido);
			folhaPagamentoTO.setValeTransporte(valeTransporte);
			folhaPagamentoTO.setBeneficios(beneficios);
			folhaPagamentoTO.setMesReferencia(mesReferencia);
			folhaPagamentoTO.setAnoReferencia(anoReferencia);
			folhaPagamentoTO.setHoraExtra(horaExtra);
			
			folhaPagamentoDAO.cadastrarFolhaPagamento(folhaPagamentoTO);
	}
	
	public void alterarJornada(String codigoContrato,Double decTercPrimeiro, Double DecTercSegunda, Double salarioLiquido,
			Double fgts, Double inss, Double irRetido, Double valeTransporte, Double beneficios,
			String mesReferencia, String anoReferencia, Double horaExtra) 
	{
		folhaPagamentoTO.setCodigoContrato(codigoContrato);
		folhaPagamentoTO.setDecTercPrimeiro(decTercPrimeiro);
		folhaPagamentoTO.setDecTercSegunda(DecTercSegunda);
		folhaPagamentoTO.setSalarioLiquido(salarioLiquido);
		folhaPagamentoTO.setFgts(fgts);
		folhaPagamentoTO.setInss(inss);
		folhaPagamentoTO.setIrRetido(irRetido);
		folhaPagamentoTO.setValeTransporte(valeTransporte);
		folhaPagamentoTO.setBeneficios(beneficios);
		folhaPagamentoTO.setMesReferencia(mesReferencia);
		folhaPagamentoTO.setAnoReferencia(anoReferencia);
		folhaPagamentoTO.setHoraExtra(horaExtra);
		
		folhaPagamentoDAO.alterarFolhaPagamento(folhaPagamentoTO);
	}
	 
	 
	public FolhaPagamentoTO pesquisar(String codigo){
		
		folhaPagamentoTO = folhaPagamentoDAO.pesquisarFolhaPagamento(codigo);
		return folhaPagamentoTO;
	}

}
