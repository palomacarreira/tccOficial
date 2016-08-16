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
import model.EspecialistaFolhaPagamento;
import model.EspecialistaFerias;
import model.FolhaPagamentoTO;

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
		EspecialistaFolhaPagamento folha = new EspecialistaFolhaPagamento();
		
		RequestDispatcher view;
		String codigoEmpregado = (String) request.getAttribute("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		switch (acao) 
		{
		
		case "Adicionar":
			
			ContratoTO cont = contrato.pesquisarEmpregado(codigoEmpregado);
			FolhaPagamentoTO folhaTO = folha.pesquisar(cont.getCodigo());
			
			Double salario = cont.getSalarioBase();
			String regimeTrabalho = cont.getRegimeDeTrabalho();		
			String duracaoSemanal = cont.getDuracaoSemanal();
			Date dataAdmissao = cont.getDataAdmissao();
			int qtdFolgas = folha.getQtdDiasDeFolga();
			
			int diasDeFerias = 0;
			if(regimeTrabalho.equals("Tempo Parcial")){ // CÁLCULO REGIME DE TEMPO PARCIAL
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
						
			}else{ // CÁLCULO REGIME DE TEMPO INTEGRAL
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
			
			// FÉRIAS DISPONÍVEIS A DO ANO QUE ELE ENTROU NO EMPREGO PARA FRENTE ATÉ O ANO ATUAL
			// contando do mês que ele entrou 12 meses a frente
			// da ultima ferias mais 12 meses
			
			request.setAttribute("qtdFolgas", diasDeferias);
			request.setAttribute("Salário", salario);
			view = request.getRequestDispatcher("TelaFerias.jsp");
			view.forward(request, response);
			
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

