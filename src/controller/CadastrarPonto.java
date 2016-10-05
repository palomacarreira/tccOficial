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

import model.CalculosHoraExtra;
import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaHorasExtras;
import model.EspecialistaJornadaTrabalho;
import model.EspecialistaPonto;
import model.JornadaTrabalhoTO;
import model.PontoTO;

/**
 * Servlet implementation class CadastrarPonto
 */
@WebServlet("/CadastrarPonto")
   
public class CadastrarPonto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarPonto() {
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

		EspecialistaContrato espContrato = new EspecialistaContrato();
		EspecialistaPonto espPonto = new EspecialistaPonto();
		EspecialistaJornadaTrabalho espJornada = new EspecialistaJornadaTrabalho();
		EspecialistaHorasExtras espHorasExtras = new EspecialistaHorasExtras();
		RequestDispatcher view;
		String codigoEmpregado = (String) request.getParameter("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		switch (acao) 
		{
			
			case "TelaCadastrar":
				
				ContratoTO contratoTO = espContrato.pesquisarEmpregado(codigoEmpregado);
				ArrayList<PontoTO> listaDePonto = espPonto.pesquisar(codigoEmpregado);
				SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
				String dataAdmissao = formatador.format(contratoTO.getDataAdmissao());

				if(request.getParameter("mes") == null)
				{
					Calendar calendar = new GregorianCalendar();
					int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
					int mesAtual = Calendar.getInstance().get(Calendar.MONTH) + 1;
					int totalDias = diasNoMes(mesAtual, anoAtual);

					request.setAttribute("anoEscolhido", anoAtual);
					request.setAttribute("mesEscolhido", mesAtual);
				 	request.setAttribute("totalDias", totalDias);
				}
				else{
					int anoEscolhido = Integer.parseInt(request.getParameter("ano"));
					int mesEscolhido = Integer.parseInt(request.getParameter("mes"));
					int totalDias = diasNoMes(mesEscolhido, anoEscolhido);
					
					request.setAttribute("anoEscolhido", anoEscolhido);
					request.setAttribute("mesEscolhido", mesEscolhido);
				 	request.setAttribute("totalDias", totalDias);
				}
				
				request.setAttribute("codigoEmpregado", codigoEmpregado);
				request.setAttribute("listaDePonto", listaDePonto);
				request.setAttribute("dataAdmissao", dataAdmissao);
				view = request.getRequestDispatcher("TelaCadastrarPonto.jsp");
				view.forward(request, response);
			break;
			
			case "Cadastrar":
				
				try {
					int totalDeDias = Integer.parseInt(request.getParameter("totalDeDias"));
					String mes = request.getParameter("mes");
					if(mes.length() == 1){mes = "0"+mes;}
					String ano = request.getParameter("ano");		
					for(int i = 1; i <= totalDeDias; i++)
					{
						 String horaEntrada = request.getParameter("horaEntrada" + i);
						 if(horaEntrada == null){horaEntrada = "";}
						 String horaSaida  = request.getParameter("horaSaida" + i);
						 if(horaSaida == null){horaSaida = "";}
						 String horaSaidaAlmoco = request.getParameter("horaSaidaAlmoco"+ i);
						 if(horaSaidaAlmoco == null){horaSaidaAlmoco = "";}
						 String horaVoltaAlmoco = request.getParameter("horaVoltaAlmoco"+ i);
						 if(horaVoltaAlmoco == null){horaVoltaAlmoco = "";}
						 
						 String dia = Integer.toString(i);
						 if(i < 10){dia = "0"+i;}
						 String dataEscolhida = ano+"-"+mes+"-"+dia;
						 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						 Date data = null;
						 data= new java.sql.Date(((java.util.Date)formatter.parse(dataEscolhida)).getTime());
						 String acaoSelecionada = (String) request.getParameter("acaoSelecionada"+ i);

						 // ADICIONA O PONTO
						 espPonto.adicionar(data, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida,
									acaoSelecionada, codigoEmpregado);
						 ContratoTO contrato = espContrato.pesquisarEmpregado(codigoEmpregado);
						 String qtdHorasSemanais = contrato.getDuracaoSemanal();
						 double salario = contrato.getSalarioBase();
						 ArrayList<JornadaTrabalhoTO> listaJornada = espJornada.pesquisarJornada(contrato.getCodigo());
						 JornadaTrabalhoTO lista = listaJornada.get(6);
						 CalculosHoraExtra calculos = new CalculosHoraExtra();
						 int diaSemana = calculos.diaDaSemana(dia, mes, ano);
						 switch(diaSemana) {
						    case 0:
						    	lista = listaJornada.get(6);
						        break;
						    case 1:
						    	lista = listaJornada.get(0);
						        break;
						    case 2:
						    	lista = listaJornada.get(1);
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
						if(cadastroEntrada.equals("")){cadastroEntrada = "00:00";}
						String cadastroSaidaAlmoco = lista.getHoraSaidaAlmoco();
						if(cadastroSaidaAlmoco.equals("")){cadastroSaidaAlmoco = "00:00";}
						String cadastroVoltaAlmoco = lista.getHoraVoltaAlmoco();
						if(cadastroVoltaAlmoco.equals("")){cadastroVoltaAlmoco = "00:00";}
						String cadastroSaida = lista.getHoraSaida();
						if(cadastroSaida.equals("")){cadastroSaida = "00:00";}

						//CÁLCULOS
						int ultimoDiaDoMes =  calculos.diasNoMes(mes,ano);
						String totalHoras = ""; 
						double valorTotal = 0.0;
					
						// SE TRABALHOU EM UM DIA DE DESCANSO ENTÃO NÃO CONTA COMO HORAS EXTRAS E SIM UM DIA DE FOLGA
						int folga = 0;
						String totalDeHorasExtras = "00:00";
						String totalDeHorasExtrasNoturno = "00:00";
						if(diaSemana == 7 || acaoSelecionada.equals("Feriado Trabalhado") || acaoSelecionada.equals("Trabalhou na DSR"))
						{
							folga = 1;
						} 
						else{
							totalDeHorasExtras = calculos.calculaHorasExtras(horaEntrada, horaSaidaAlmoco, 
									horaVoltaAlmoco, horaSaida, cadastroEntrada, cadastroSaidaAlmoco, 
									cadastroVoltaAlmoco, cadastroSaida);
							totalDeHorasExtrasNoturno = calculos.calculaHorasExtrasNoturno(horaEntrada, 
									horaSaida);
						}
						
						// ADICIONA AS HORAS EXTRAS DO PONTO 
						String codigoPonto = espPonto.ultimoCodigoPonto();
						espHorasExtras.adicionar(totalDeHorasExtras, totalDeHorasExtrasNoturno, folga, codigoPonto);	
										
					}
					
					view = request.getRequestDispatcher("PesquisarPonto?acao=Pesquisar&mes="+mes);
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
		java.util.Date date = null;
        String data = 1 +"/"+mes+"/"+ano;
        String formato = "dd/MM/yyyy";
        SimpleDateFormat df = new SimpleDateFormat(formato);
		try {
			date = df.parse(data);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
	 	int ultimoDia = c.getActualMaximum (Calendar.DAY_OF_MONTH);
	 	
	 	return ultimoDia;
	}
	
}
