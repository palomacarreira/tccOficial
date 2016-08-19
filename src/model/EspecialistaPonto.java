package model;

import java.sql.Date;
import java.util.ArrayList;

import mysqldao.PontoDAO;

public class EspecialistaPonto {
	PontoTO pontoTO = new PontoTO();
	PontoDAO pontoDAO = new PontoDAO();	
	
	
	public void adicionar(Date data, String horaEntrada, String horaSaidaAlmoco, String horaVoltaAlmoco, 
         		String horaSaida, Boolean falta, Boolean faltaJustificada, String codigoEmpregado){
				
		pontoTO.setData(data);
        pontoTO.setHoraEntrada(horaEntrada);
        pontoTO.setHoraSaidaAlmoco(horaSaidaAlmoco);
        pontoTO.setHoraVoltaAlmoco(horaVoltaAlmoco);
        pontoTO.setHoraSaida(horaSaida);
        pontoTO.setFalta(falta);
        pontoTO.setFaltaJustificada(faltaJustificada);
        pontoTO.setCodigoEmpregado(codigoEmpregado);
    	pontoDAO.cadastrarPonto(pontoTO);
	}
	
	
	public void alterar(Date data, String horaEntrada, String horaSaidaAlmoco, String horaVoltaAlmoco, 
     		String horaSaida, Boolean falta, Boolean faltaJustificada, String codigoEmpregado){	
		
		pontoTO.setData(data);
        pontoTO.setHoraEntrada(horaEntrada);
        pontoTO.setHoraSaidaAlmoco(horaSaidaAlmoco);
        pontoTO.setHoraVoltaAlmoco(horaVoltaAlmoco);
        pontoTO.setHoraSaida(horaSaida);
        pontoTO.setFalta(falta);
        pontoTO.setFaltaJustificada(faltaJustificada);
        pontoTO.setCodigoEmpregado(codigoEmpregado);
    	pontoDAO.alterarPonto(pontoTO);
	}
	
	
	public ArrayList<PontoTO> pesquisar(String codigo){
		ArrayList<PontoTO> pontoTO = new ArrayList<PontoTO>();
		pontoTO  = pontoDAO.pesquisarPonto(codigo);
		return pontoTO;
	}
}
