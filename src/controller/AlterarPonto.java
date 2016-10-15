package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

/**
 * Servlet implementation class AlterarPonto
 */
@WebServlet("/AlterarPonto")
public class AlterarPonto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarPonto() {
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
			case "Salvar":
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
	
					 if(horaEntrada.equals("") && horaSaida.equals("") && horaSaidaAlmoco.equals("") && horaVoltaAlmoco.equals(""))
					 {
						 if(!acaoSelecionada.equals("Dia de DSR") && !acaoSelecionada.equals("Sem Jornada")
						&& !acaoSelecionada.equals("Falta Justificada") && !acaoSelecionada.equals("Feriado")){
							 acaoSelecionada = "Falta";
						 }
					 }
					 else if(!horaEntrada.equals("") && !horaSaida.equals("") && !horaSaidaAlmoco.equals("") && !horaVoltaAlmoco.equals(""))
					 {
						 if(acaoSelecionada.equals("Dia de DSR")){
							 acaoSelecionada = "Trabalhou na DSR";
						 }
					 } 
					 else if(!horaEntrada.equals("")){
						 if(acaoSelecionada.equals("Falta")){
							 acaoSelecionada = "Dia Comum";
						 }
					 }
					 
					 // ADICIONA O PONTO
					 ContratoTO contrato = espContrato.pesquisarEmpregado(codigoEmpregado);
					 String codigoPonto = espPonto.pesquisaPorData(data).getCodigo();
					 espPonto.alterar(data, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida,
								acaoSelecionada, codigoPonto);
				
	 
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
					String cadastroSaidaAlmoco = lista.getHoraSaidaAlmoco();
					String cadastroVoltaAlmoco = lista.getHoraVoltaAlmoco();
					String cadastroSaida =lista.getHoraSaida();
	
					
					// ADICIONA AS HORAS EXTRAS DO PONTO 
					boolean folga = false;
					String totalDeHorasExtras = "00:00";
					String totalDeHorasExtrasNoturno = "00:00";
					if(diaSemana == 7 || acaoSelecionada.equals("Feriado Trabalhado") || acaoSelecionada.equals("Trabalhou na DSR"))
					{
						folga = true;
					} 
					else{ //C�LCULOS
						totalDeHorasExtras = calculos.calculaHorasExtras(horaEntrada, horaSaidaAlmoco, 
								horaVoltaAlmoco, horaSaida, cadastroEntrada, cadastroSaidaAlmoco, 
								cadastroVoltaAlmoco, cadastroSaida);
						totalDeHorasExtrasNoturno = calculos.calculaHorasExtrasNoturno(horaEntrada, 
								horaSaida);
					}
					
					espHorasExtras.alterar(totalDeHorasExtras, totalDeHorasExtrasNoturno, folga, codigoPonto);	
									
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

}
