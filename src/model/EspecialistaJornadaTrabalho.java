package model;

import java.util.ArrayList;

import mysqldao.JornadaTrabalhoDAO;

public class EspecialistaJornadaTrabalho {

	
	JornadaTrabalhoTO jornadaTrabalhoTO = new JornadaTrabalhoTO();
	JornadaTrabalhoDAO jornadaTrabalhoDAO = new JornadaTrabalhoDAO();
	
	public void adicionarJornada(String horaEntrada,String horaSaidaAlmoco, String horaVoltaAlmoco, 
	String horaSaida, int diaSemana, Boolean diaFolga, Boolean diaMeioPeriodo, Boolean diaSemTrabalho) 
	{
	
		jornadaTrabalhoTO.setHoraEntrada(horaEntrada);
		jornadaTrabalhoTO.setHoraSaida(horaSaida);
		jornadaTrabalhoTO.setHoraSaidaAlmoco(horaSaidaAlmoco);
		jornadaTrabalhoTO.setHoraVoltaAlmoco(horaVoltaAlmoco);
		jornadaTrabalhoTO.setDiaSemana(diaSemana);
		jornadaTrabalhoTO.setDiaMeioPeriodo(diaMeioPeriodo);
		jornadaTrabalhoTO.setDiaFolga(diaFolga);
		jornadaTrabalhoTO.setDiaSemTrabalho(diaSemTrabalho);
		jornadaTrabalhoDAO.cadastrar(jornadaTrabalhoTO);
	}
	
	public void alterarJornada(String codigo, String horaEntrada,String horaSaidaAlmoco, String horaVoltaAlmoco, 
			String horaSaida, int diaSemana, Boolean diaFolga, Boolean diaMeioPeriodo, Boolean diaSemTrabalho) 
	{
		jornadaTrabalhoTO.setCodigo(codigo);
		jornadaTrabalhoTO.setHoraEntrada(horaEntrada);
		jornadaTrabalhoTO.setHoraSaida(horaSaida);
		jornadaTrabalhoTO.setHoraSaidaAlmoco(horaSaidaAlmoco);
		jornadaTrabalhoTO.setHoraVoltaAlmoco(horaVoltaAlmoco);
		jornadaTrabalhoTO.setDiaSemana(diaSemana);
		jornadaTrabalhoTO.setDiaMeioPeriodo(diaMeioPeriodo);
		jornadaTrabalhoTO.setDiaFolga(diaFolga);
		jornadaTrabalhoTO.setDiaSemTrabalho(diaSemTrabalho);
		jornadaTrabalhoDAO.alterar(jornadaTrabalhoTO);
	}
	
	public ArrayList<JornadaTrabalhoTO> pesquisarJornada(String codg){
		
		ArrayList<JornadaTrabalhoTO> lista = jornadaTrabalhoDAO.pesquisarJornada(codg);
		return lista;
	}
	
}
