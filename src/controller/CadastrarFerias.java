package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaFerias;

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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		EspecialistaFerias espFerias = new EspecialistaFerias();
		EspecialistaContrato contrato = new EspecialistaContrato();
		RequestDispatcher view;
		String codigoEmpregado = (String) request.getAttribute("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		switch (acao) 
		{
		
		case "Adicionar":
			
			ContratoTO cont = contrato.pesquisarEmpregado(codigoEmpregado);
			Double salário = cont.getSalarioBase();
			String regimeTrabalho = cont.getRegimeDeTrabalho();		
			
		break;
		
		case "Cadastrar":
		String periodo = (String) request.getParameter("periodoAquisitivo");
		String situacao = (String) request.getParameter("situacao");
		int qtdDias = Integer.parseInt(request.getParameter("qtdDiasFerias"));
		Double valor= Double.parseDouble(request.getParameter("valor"));
		Boolean vendaFerias = Boolean.parseBoolean(request.getParameter("vendaFerias"));
		
		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 Date dataInicio = null;
		 Date dataFinal = null;
		 String dataA = request.getParameter("dataInicio");
		 String dataB = request.getParameter("dataTermino");
		 try {
			dataInicio = new java.sql.Date(((java.util.Date)formatter.parse(dataA)).getTime());
			dataFinal = new java.sql.Date(((java.util.Date)formatter.parse(dataB)).getTime());
		 } catch (ParseException e1) {
			e1.printStackTrace();
		 }		
		 
		try {
			
			ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
			espFerias.adicionar(contratoTO.getCodigo(), periodo, situacao, 
					dataInicio, dataFinal, qtdDias, valor, vendaFerias);
				
        	view = request.getRequestDispatcher("TelaFerias.jsp");
        	view.forward(request, response);
	
			
		} catch (NumberFormatException e) {
			
			request.setAttribute("msg", "Error " + e.getMessage());
			view = request.getRequestDispatcher("TelaFerias.jsp");
			view.forward(request, response);
		}
		break;
		}
	
	}
}

