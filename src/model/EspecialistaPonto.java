package model;

import java.sql.Date;
import java.util.ArrayList;

import mysqldao.PontoDAO;

public class EspecialistaPonto {
	PontoTO pontoTO = new PontoTO();
	PontoDAO pontoDAO = new PontoDAO();	
	
	
	public void adicionar(Date data, String horaEntrada, String horaSaidaAlmoco, String horaVoltaAlmoco, 
         		String horaSaida, String acao, String codigoEmpregado){
				
		pontoTO.setDataPonto(data);
        pontoTO.setHoraEntrada(horaEntrada);
        pontoTO.setHoraSaidaAlmoco(horaSaidaAlmoco);
        pontoTO.setHoraVoltaAlmoco(horaVoltaAlmoco);
        pontoTO.setHoraSaida(horaSaida);
        pontoTO.setAcao(acao);
        pontoTO.setCodigoEmpregado(codigoEmpregado);
    	pontoDAO.cadastrarPonto(pontoTO);
	}
	
	
	public void alterar(Date data, String horaEntrada, String horaSaidaAlmoco, String horaVoltaAlmoco, 
     		String horaSaida, String acao, String codigo){	
		
		pontoTO.setDataPonto(data);
        pontoTO.setHoraEntrada(horaEntrada);
        pontoTO.setHoraSaidaAlmoco(horaSaidaAlmoco);
        pontoTO.setHoraVoltaAlmoco(horaVoltaAlmoco);
        pontoTO.setHoraSaida(horaSaida);
        pontoTO.setAcao(acao);
        pontoTO.setCodigo(codigo);
    	pontoDAO.alterarPonto(pontoTO);
	}
	
	
	public ArrayList<PontoTO> pesquisar(String codigo){
		ArrayList<PontoTO> pontoTO = new ArrayList<PontoTO>();
		pontoTO  = pontoDAO.pesquisarPonto(codigo);
		return pontoTO;
	}
	
	 public String ultimoCodigoPonto(){
		 return pontoDAO.ultimoCodigoPonto();
	 }
	 
	 public PontoTO pesquisaPorData(Date data){
		 
		 return pontoDAO.pesquisaPorData(data);
	 }
}
