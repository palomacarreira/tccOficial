package model;

import java.util.ArrayList;

import mysqldao.JornadaTrabalhoDAO;

public class EspecialistaJornadaTrabalho {

	
	JornadaTrabalhoTO jornadaTrabalhoTO = new JornadaTrabalhoTO();
	JornadaTrabalhoDAO jornadaTrabalhoDAO = new JornadaTrabalhoDAO();
	
	public void adicionarJornada(String horaEntrada,String horaSaidaAlmoco, String horaVoltaAlmoco, 
	String horaSaida, int diaSemana, Boolean diaFolga, Boolean diaMeioPeriodo) 
	{
	
		jornadaTrabalhoTO.setHoraEntrada(horaEntrada);
		jornadaTrabalhoTO.setHoraSaida(horaSaida);
		jornadaTrabalhoTO.setHoraSaidaAlmoco(horaSaidaAlmoco);
		jornadaTrabalhoTO.setHoraVoltaAlmoco(horaVoltaAlmoco);
		jornadaTrabalhoTO.setDiaSemana(diaSemana);
		jornadaTrabalhoTO.setDiaMeioPeriodo(diaMeioPeriodo);
		jornadaTrabalhoTO.setDiaFolga(diaFolga);
		jornadaTrabalhoDAO.cadastrar(jornadaTrabalhoTO);
	}
	
	public void alterarJornada(String codigoContrato, String horaEntrada,String horaSaidaAlmoco, String horaVoltaAlmoco, 
			String horaSaida, int diaSemana, Boolean diaFolga, Boolean diaMeioPeriodo) 
	{
		jornadaTrabalhoTO.setCodigoContrato(codigoContrato);
		jornadaTrabalhoTO.setHoraEntrada(horaEntrada);
		jornadaTrabalhoTO.setHoraSaida(horaSaida);
		jornadaTrabalhoTO.setHoraSaidaAlmoco(horaSaidaAlmoco);
		jornadaTrabalhoTO.setHoraVoltaAlmoco(horaVoltaAlmoco);
		jornadaTrabalhoTO.setDiaSemana(diaSemana);
		jornadaTrabalhoTO.setDiaMeioPeriodo(diaMeioPeriodo);
		jornadaTrabalhoTO.setDiaFolga(diaFolga);
		jornadaTrabalhoDAO.alterar(jornadaTrabalhoTO);
	}
	
	public ArrayList<JornadaTrabalhoTO> pesquisarJornada(String codg){
		
		ArrayList<JornadaTrabalhoTO> lista = jornadaTrabalhoDAO.pesquisarJornada(codg);
		return lista;
	}
	
}
