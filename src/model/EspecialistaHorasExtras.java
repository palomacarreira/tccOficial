package model;

import java.sql.Date;

import mysqldao.HoraExtraDAO;

public class EspecialistaHorasExtras 
{
	HoraExtraTO horasExtrasTO = new HoraExtraTO();
	HoraExtraDAO horaExtraDAO = new HoraExtraDAO();
	
	public void adicionar(String totalDeHorasExtras, String totalDeHorasExtrasNoturno, int folga, String codigoPonto)
	{		
		horasExtrasTO.setCodigoPonto(codigoPonto);
		horasExtrasTO.setTotalDeHorasExtras(totalDeHorasExtras);
		horasExtrasTO.setTotalDeHorasExtrasNoturno(totalDeHorasExtrasNoturno);

		horaExtraDAO.cadastrar(horasExtrasTO);
	}
	
	
	public void alterar(String totalDeHorasExtras, String totalDeHorasExtrasNoturno, int folga, String codigoPonto)
	{		
		horasExtrasTO.setCodigoPonto(codigoPonto);
		horasExtrasTO.setTotalDeHorasExtras(totalDeHorasExtras);
		horasExtrasTO.setTotalDeHorasExtrasNoturno(totalDeHorasExtrasNoturno);

		horaExtraDAO.alterar(horasExtrasTO);
	}
	
	public void excluir(String codigo)
	{
		 horaExtraDAO.excluir(codigo);
	}
	
	public HoraExtraTO pesquisar(String codigo)
	{
		 return horaExtraDAO.pesquisar(codigo);
	}
	
}
