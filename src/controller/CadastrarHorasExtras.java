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
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CalculosHoraExtra;
import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaHorasExtras;
import model.EspecialistaJornadaTrabalho;
import model.EspecialistaPonto;
import model.JornadaTrabalhoTO;
import model.PontoTO;

/**
 * Servlet implementation class CadastrarHorasExtras
 */
@WebServlet("/CadastrarHorasExtras")
public class CadastrarHorasExtras extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarHorasExtras() {
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
		// TODO Auto-generated method stub
		
		EspecialistaJornadaTrabalho espJornada = new EspecialistaJornadaTrabalho();
		EspecialistaHorasExtras espHorasExtras = new EspecialistaHorasExtras();
		EspecialistaContrato espContrato = new EspecialistaContrato();
		
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		
		switch (acao) 
		{
			case "Cadastrar":
				CalculosHoraExtra calculos = new CalculosHoraExtra();
				try {
					 String codigoPonto = (String) request.getAttribute("codigoPonto");
					 String codigoEmpregado = (String) request.getAttribute("codigoEmpregado");
					 ContratoTO contratoTO = espContrato.pesquisarEmpregado(codigoEmpregado);
					 ArrayList<JornadaTrabalhoTO> listaJornada = espJornada.pesquisarJornada(contratoTO.getCodigo());
					 String qtdHorasSemanais = contratoTO.getDuracaoSemanal();
					 double salario = contratoTO.getSalarioBase();
					 
					 
					 String acaoSelecionada = (String) request.getAttribute("acaoSelecionada");
					 String horaEntrada = (String) request.getAttribute("horaEntrada");
					 String horaSaidaAlmoco = (String)request.getAttribute("horaSaidaAlmoco");
					 String horaVoltaAlmoco =(String) request.getAttribute("horaVoltaAlmoco");
					 String horaSaida  = (String)request.getAttribute("horaSaida");
					 String dia = (String) request.getAttribute("dia");
					 String mes = (String)request.getAttribute("mes");
					 String ano = (String)request.getAttribute("ano");
					 
					 
					 JornadaTrabalhoTO lista = listaJornada.get(6);
					 int diaSemana = calculos.diaDaSemana(dia, mes, ano);
					 switch(diaSemana) {
					    case 0:
					    	lista = listaJornada.get(6); // domingo
					        break;
					    case 1:
					    	lista = listaJornada.get(0); // segunda
					        break;
					    case 2:
					    	lista = listaJornada.get(1); // terça
					        break;
					    case 3:
					    	lista = listaJornada.get(2);	
					        break;
					    case 4:
					    	lista = listaJornada.get(3);	
					        break;
					    case 5:
					    	lista = listaJornada.get(4);	
					        break;
					    case 6:
					    	lista = listaJornada.get(5);	
						}
					 
					String cadastroEntrada = lista.getHoraEntrada();
					String cadastroSaidaAlmoco = lista.getHoraSaidaAlmoco();
					String cadastroVoltaAlmoco = lista.getHoraVoltaAlmoco();
					String cadastroSaida =lista.getHoraSaida();

					//CÁLCULOS
					int ultimoDiaDoMes =  calculos.diasNoMes(mes,ano);
					
					String totalHoras = ""; 
					double valorTotal = 0.0;
					String totalDeHorasExtras = calculos.calculaHorasExtras(horaEntrada, horaSaidaAlmoco, 
							horaVoltaAlmoco, horaSaida, cadastroEntrada, cadastroSaidaAlmoco, 
							cadastroVoltaAlmoco, cadastroSaida);
					String totalDeHorasExtrasNoturno = calculos.calculaHorasExtrasNoturno(horaEntrada, 
							horaSaida);
					boolean folga = false;
					if(diaSemana == 7 || acaoSelecionada.equals("Feriado Trabalhado"))
					{
						folga = true;
					}
					espHorasExtras.adicionar(totalDeHorasExtras, totalDeHorasExtrasNoturno, folga, codigoPonto);
						 
				} 
				catch (Exception e) 
				{
					request.setAttribute("msg", "Error " + e.getMessage());
				}
				break;
		}
	}
	
	
}
