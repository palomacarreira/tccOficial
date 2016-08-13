package model;

import java.sql.Date;
import mysqldao.ContratoDAO;

public class EspecialistaContrato {

	ContratoDAO contratoDAO = new ContratoDAO();
	ContratoTO contratoTO = new ContratoTO();
	
	public void adicionarEmpregado(String cargo, String diaPagamento, 
			Date dataAdmissao, Boolean descontoINSS, Double valeTransporte, 
			Double salarioBase, String compensacaoDias,  String regimeDeTrabalho, 
		  String conta, String agencia, String banco, String tipoConta, String duracaoSemanal) 
	{
		
		
		contratoTO.setCargo(cargo); 
		contratoTO.setDiaPagamento(diaPagamento); 
		contratoTO.setDataAdmissao(dataAdmissao);
		contratoTO.setDescontoINSS(descontoINSS);
		contratoTO.setValeTransporte(valeTransporte);
		contratoTO.setSalarioBase(salarioBase);
		contratoTO.setCompensacaoDias(compensacaoDias);
		contratoTO.setRegimeDeTrabalho(regimeDeTrabalho);
		contratoTO.setAgencia(agencia);
		contratoTO.setConta(conta);
		contratoTO.setBanco(banco);
		contratoTO.setTipoConta(tipoConta);
		contratoTO.setDuracaoSemanal(duracaoSemanal);
    	contratoDAO.cadastrar(contratoTO);
		
	}

	public void alterarEmpregado(String codigoEmpregado, String cargo, String diaPagamento, 
			Date dataAdmissao, Boolean descontoINSS, Double valeTransporte, 
			Double salarioBase, String compensacaoDias,  String regimeDeTrabalho,  
		  String conta, String agencia, String banco, String tipoConta, String duracaoSemanal) 
	{
		
		contratoTO.setCodigoEmpregado(codigoEmpregado);
		contratoTO.setCargo(cargo); 
		contratoTO.setDiaPagamento(diaPagamento); 
		contratoTO.setDataAdmissao(dataAdmissao);
		contratoTO.setDescontoINSS(descontoINSS);
		contratoTO.setValeTransporte(valeTransporte);
		contratoTO.setSalarioBase(salarioBase);
		contratoTO.setCompensacaoDias(compensacaoDias);
		contratoTO.setRegimeDeTrabalho(regimeDeTrabalho);
		contratoTO.setAgencia(agencia);
		contratoTO.setConta(conta);
		contratoTO.setBanco(banco);
		contratoTO.setTipoConta(tipoConta);
		contratoTO.setDuracaoSemanal(duracaoSemanal);
    	contratoDAO.alterar(contratoTO);
		
	}
	

	public ContratoTO pesquisarEmpregado(String codg){
		
		contratoTO = contratoDAO.pesquisarEmpregado(codg);
		return contratoTO;
	}
}