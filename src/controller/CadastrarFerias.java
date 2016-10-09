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
import model.CalculosPagamento;
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

				ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				CalculosPagamento calculos = new CalculosPagamento();
				int diasDeFerias = calculos.calculaDiasFerias(codigoEmpregado, periodoInicioAno,  periodoInicioMes,
						 periodoFimAno, periodoFimMes);
				if(diasDeFerias != -1)
				{
					double valorFerias =  calculos.calculaValorFerias(contratoTO.getSalarioBase());
					
					Date dataAdmissao = contratoTO.getDataAdmissao();
					request.setAttribute("periodo", periodo);
					request.setAttribute("diasDeFerias", diasDeFerias);
					request.setAttribute("valorFerias", valorFerias);
					
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

		contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);	
		
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
}

