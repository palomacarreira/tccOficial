package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaPonto;
import model.PontoTO;

/**
 * Servlet implementation class CadastrarPontoAndroid
 */
@WebServlet("/CadastrarPontoAndroid")
public class CadastrarPontoAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarPontoAndroid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EspecialistaContrato espContrato = new EspecialistaContrato();
		EspecialistaPonto espPonto = new EspecialistaPonto();
		
		RequestDispatcher view;
		String codigoEmpregado = (String) request.getParameter("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		switch (acao) 
		{
			case "Cadastrar":
				
				try 
				{
					String hora = request.getParameter("hora");
					if(hora == null){hora = "";}
					String dataEscolhida = retornaDataAtual(); // pega dia que está batendo o ponto
					
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date data = null;
					data= new java.sql.Date(((java.util.Date)formatter.parse(dataEscolhida)).getTime());
					String acaoSelecionada = (String) request.getParameter("acaoSelecionada");
					String finalizarDia = request.getParameter("acaoSelecionada");
						
					String  horaEntrada , horaSaidaAlmoco,horaVoltaAlmoco,horaSaida ;
					PontoTO pontoTO = espPonto.pesquisaPorData(data);
					if(pontoTO != null) // altera os dados do "dia" até que este seja finalizado
					{
						horaEntrada = pontoTO.getHoraEntrada();
						horaSaidaAlmoco = pontoTO.getHoraSaidaAlmoco();
						horaVoltaAlmoco = pontoTO.getHoraVoltaAlmoco();
						horaSaida = pontoTO.getHoraSaida();
						
						if(finalizarDia != "")
						{
							if(horaSaida == "")
							{
								horaSaida = hora;
							}
							else 
							{
								if(horaSaidaAlmoco == "")
								{
									horaSaidaAlmoco = horaSaida;
									horaSaida = hora;
								}
								else if(horaVoltaAlmoco == "")
								{
									horaVoltaAlmoco =  horaSaida;
									horaSaida = hora;
								}
								else // caso tenha batido o ponto errado, o horário final vai se alterando
								{
									horaSaida = hora;
								}
								
							}
							 
							espPonto.alterar(data, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida,
									acaoSelecionada, codigoEmpregado);
						}
					}
					else // realiza o primeiro cadastro do dia
					{
						espPonto.adicionar(data, hora, "", "", "",acaoSelecionada, codigoEmpregado);
					}
					
					view = request.getRequestDispatcher("CadastrarHorasExtras?acao=Cadastrar");
					view.forward(request, response);	
				} 
				catch (ParseException e) 
				{
					request.setAttribute("msg", "Error " + e.getMessage());
				}
				break;
		}
	
	}
	
	public int diasNoMes(int mes, int ano){
	 	return 32 - new Date(ano, mes, 32).getDate();
	}
	
	public String retornaDataAtual()
	{
		Calendar calendario = Calendar.getInstance();
        String dia = ((Integer) calendario.get(Calendar.DAY_OF_MONTH)).toString();
        String mes = ((Integer) (calendario.get(Calendar.MONTH) + 1)).toString();
        String ano = ((Integer) calendario.get(Calendar.YEAR)).toString();
        String data = ano+"-"+mes+"-"+dia;;

        return data;
    }
}
