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
import javax.servlet.http.HttpSession;

import util.DataUtil;
import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaFolhaPagamento;
import model.EspecialistaPonto;
import model.FeriasTO;
import model.EspecialistaFerias;
import model.FolhaPagamentoTO;
import model.PontoTO;

/**
 * Servlet implementation class CadastrarFerias
 */
@WebServlet("/CadastrarFerias")
public class CadastrarFerias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarFerias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		
		EspecialistaFerias espFerias = new EspecialistaFerias();
		EspecialistaContrato contrato = new EspecialistaContrato();
		EspecialistaPonto ponto = new EspecialistaPonto();
		
		RequestDispatcher view= null;
		String codigoEmpregado = request.getParameter("codigoEmpregado");
	
		switch (acao)
		{
		
		case "Adicionar":
			
			String periodo = (String) request.getParameter("periodoAquisitivo");
			ContratoTO cont = contrato.pesquisarEmpregado(codigoEmpregado);
			ArrayList<PontoTO> listaDePonto = ponto.pesquisar(cont.getCodigo());
			
			int periodoInicioAno = Integer.parseInt(periodo.substring(0,4));
			int periodoFimAno  = Integer.parseInt(periodo.substring(11,15));
			int periodoInicioMes = Integer.parseInt(periodo.substring(5,7));
			int periodoFimMes  = Integer.parseInt(periodo.substring(16,18));

			
			int qtdFolgas = 0;
			if(listaDePonto != null){
				
				for(PontoTO pontoTO: listaDePonto)
				{
					int pontoDataAno = pontoTO.getDataPonto().getYear();
					int pontoDataMes = pontoTO.getDataPonto().getMonth();
							
					// verificando os pontos dentro do periodo selecionado		
					if( pontoDataAno == periodoInicioAno || pontoDataAno == periodoFimAno)
					{
						if(pontoDataMes >= periodoInicioMes && pontoDataMes <= periodoFimMes)
						{
							if(pontoTO.getAcao().equals("Falta"))
							{
								qtdFolgas += 1;
							}
						}
					}
				}
		
				String regimeTrabalho = cont.getRegimeDeTrabalho();		
				String duracaoSemanal = cont.getDuracaoSemanal();
				
				int diasDeFerias = 0;
				if(regimeTrabalho.equals("Tempo Parcial")){ // CALCULO REGIME DE TEMPO PARCIAL
					int totalSemana = Integer.parseInt((duracaoSemanal.substring(0,2)));
					if(totalSemana > 22)
					{
						if(qtdFolgas > 7){
							diasDeFerias = 9;
						}else{ 
							diasDeFerias = 18;
						}
					}else if(totalSemana > 20 && totalSemana <=22){
						if(qtdFolgas > 7){
							diasDeFerias = 8;
						}else{ 
							diasDeFerias = 16;
						}
					}else if(totalSemana > 15 && totalSemana <=20){
						if(qtdFolgas > 7){
							diasDeFerias = 7;
						}else{ 
							diasDeFerias = 14;
						}
					}else if(totalSemana > 10 && totalSemana <=15){
						if(qtdFolgas > 7){
							diasDeFerias = 6;
						}else{ 
							diasDeFerias = 12;
						}
					}else if(totalSemana > 5 && totalSemana <=10){
						if(qtdFolgas > 7){
							diasDeFerias = 5;
						}else{ 
							diasDeFerias = 10;
						}
					}else if(totalSemana <= 5){
						if(qtdFolgas > 7){
							diasDeFerias = 4;
						}else{ 
							diasDeFerias = 8;
						}
					}
							
				}else{ // CALCULO REGIME DE TEMPO INTEGRAL
					diasDeFerias = 30;
					if(qtdFolgas > 5){
						if(qtdFolgas >= 6 && qtdFolgas <=14){
							diasDeFerias = 24;
						}else if(qtdFolgas >= 15 && qtdFolgas <=23){
							diasDeFerias = 18;
						}else if(qtdFolgas >= 24 && qtdFolgas <=32){
							diasDeFerias = 12;
						}else{
							diasDeFerias = 0;
						}
					}	
				}
				
				// FERIAS DISPONIVEIS A DO ANO QUE ELE ENTROU NO EMPREGO PARA FRENTE ATE O ANO ATUAL
				// contando do mais que ele entrou 12 meses a frente
				// da ultima ferias mais 12 meses
				ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				Date dataAdmissao = contratoTO.getDataAdmissao();
				request.setAttribute("periodo", periodo);
				request.setAttribute("diasDeFerias", diasDeFerias);
				request.setAttribute("valorFerias", calculaValorFerias(contratoTO.getSalarioBase()));
				
				view = request.getRequestDispatcher("TelaCadastrarFerias.jsp");
				view.forward(request, response);
			}
			else
			{
				// NÃO HÁ PONTO REGISTRADO
				view = request.getRequestDispatcher("PesquisarFerias?acao=Pesquisar&codigoEmpregado=" + codigoEmpregado);
	        	view.forward(request, response);
			}
		break;
		
		case "Cadastrar":

		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);	
		
		String periodoAquisitivo = request.getParameter("periodoAquisitivo");
		String periodoInicio = periodoAquisitivo.substring(0,10);
		String periodoFim  = periodoAquisitivo.substring(11,21);
		String dataFeriasInicio = request.getParameter("dataInicio");
		String dataFeriasFim = request.getParameter("dataTermino");
		Boolean vendaFerias = Boolean.parseBoolean(request.getParameter("vendaFerias"));
		Double valor= Double.parseDouble(request.getParameter("valor"));
		int qtdDiasFerias = Integer.parseInt(request.getParameter("qtdDiasFerias"));
		int qtdDiasVendidos = Integer.parseInt(request.getParameter("qtdDiasVendidos"));
		int diasFerias = qtdDiasFerias - qtdDiasVendidos;
		String situacao = "PENDENTE";
		
		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 //PEGANDO PERIODO DE FERIAS
		 Date dataInicioFerias = null;
		 Date dataFinalFerias = null;
		 Date  periodoAquisitivoInicio = null;
		 Date periodoAquisitivoFim = null;
		 try {
			 dataInicioFerias = new java.sql.Date(((java.util.Date)formatter.parse(dataFeriasInicio)).getTime());
			 dataFinalFerias = new java.sql.Date(((java.util.Date)formatter.parse(dataFeriasFim)).getTime());
			 periodoAquisitivoInicio = new java.sql.Date(((java.util.Date)formatter.parse(periodoInicio)).getTime());
			 periodoAquisitivoFim = new java.sql.Date(((java.util.Date)formatter.parse(periodoFim)).getTime());
		 } catch (ParseException e1) {
			e1.printStackTrace();
		 }		  
		
		try 
		{
			espFerias.adicionar(contratoTO.getCodigo(), periodoAquisitivoInicio, periodoAquisitivoFim, situacao, 
					dataInicioFerias, dataFinalFerias, diasFerias, valor, vendaFerias);
				
			view = request.getRequestDispatcher("PesquisarFerias?acao=Pesquisar&codigoEmpregado=" + codigoEmpregado);
        	view.forward(request, response);
			} 
		catch (NumberFormatException e) 
		{
				
				request.setAttribute("msg", "Error " + e.getMessage());
				view = request.getRequestDispatcher("TelaFerias.jsp");
				view.forward(request, response);
		}
		break;
		}
	
	}
	
	public double calculaValorFerias(double salario)
	{
		return salario + (salario * 1/3);
	}

}

