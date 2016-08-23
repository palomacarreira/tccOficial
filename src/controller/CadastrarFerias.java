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
		String codigoEmpregado = "1";//request.getParameter("codigoEmpregado");
		//String codigoEmpregado = (String) request.getAttribute("codigoEmpregado");
		//String codigoEmpregado = (String) session.getAttribute("codigoEmpregado");

		switch (acao){
		
		case "Adicionar":
			
			ContratoTO cont = contrato.pesquisarEmpregado(codigoEmpregado);
			ArrayList<PontoTO> listaDePonto = ponto.pesquisar(cont.getCodigo());
			//ArrayList<PontoTO> listaDePonto = ponto.pesquisar(cont.getCodigo());
			
			int qtdFolgas = 0;
			if(listaDePonto != null){
				
				for(PontoTO pontoTO: listaDePonto)
				{
					if(pontoTO.getAcao().equals("Falta"))
					{
							qtdFolgas += 1;
					}
				}
				Double salario = cont.getSalarioBase();
				String regimeTrabalho = cont.getRegimeDeTrabalho();		
				String duracaoSemanal = cont.getDuracaoSemanal();
				Date dataAdmissao = cont.getDataAdmissao();
				
				int diasDeFerias = 0;
				if(regimeTrabalho.equals("Tempo Parcial")){ // C�LCULO REGIME DE TEMPO PARCIAL
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
							
				}else{ // C�LCULO REGIME DE TEMPO INTEGRAL
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
				
				// F�RIAS DISPON�VEIS A DO ANO QUE ELE ENTROU NO EMPREGO PARA FRENTE AT� O ANO ATUAL
				// contando do m�s que ele entrou 12 meses a frente
				// da ultima ferias mais 12 meses
				
				request.setAttribute("diasDeFerias", diasDeFerias);
				request.setAttribute("Salario", salario);
				//view = request.getRequestDispatcher("TelaFerias.jsp");
				view = request.getRequestDispatcher("TelaCadastrarFerias.jsp");
				view.forward(request, response);
		}
		else{
		
			// SE N�O TIVER DADOS NO BANCO REFERENTE AO ANO DE F�RIAS
			//MOSTRAR MENSAGEM FALANDO QUE ESTAS F�RIAS S�O ANTIGAS E QUE N�O H� DADOS NO BANCO
			//REFERENTES AO ANO , POR FAVOR CLIQUE NO BOT�O F�RIAS ANTIGAS PARA DESBLOQUEAR
			// AS PR�XIMAS F�RIAS
			
		}
			
		break;
		
		case "Cadastrar":
			
		String periodoAquisitivoInicio = request.getParameter("periodoAquisitivoInicio");
		String periodoAquisitivoFim = request.getParameter("periodoAquisitivoFim");
		String dataFeriasInicio = request.getParameter("dataInicio");
		String dataFeriasFim = request.getParameter("dataTermino");
		Boolean vendaFerias = Boolean.parseBoolean(request.getParameter("vendaFerias"));
		Double valor= Double.parseDouble(request.getParameter("valor"));
		String situacao = (String) request.getParameter("situacao");
		//int qtdDias = Integer.parseInt(request.getParameter("qtdDiasFerias"));

		 
		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 //SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");

		 GregorianCalendar gcIncioFerias =new GregorianCalendar();
		 GregorianCalendar gcFimFerias =new GregorianCalendar();
		 
//PEGANDO PERIODO AQUISITIVO
		 Date periodoAquisitivoI = null;
		 Date periodoAquisitivoF = null;
		 
		 try {
			 periodoAquisitivoI = new java.sql.Date(((java.util.Date)formatter.parse(periodoAquisitivoInicio)).getTime());
			 periodoAquisitivoF = new java.sql.Date(((java.util.Date)formatter.parse(periodoAquisitivoFim)).getTime());
			 
		 } catch (ParseException e1) {
			e1.printStackTrace();
		 }	
		 
		   
//PEGANDO PERIODO DE FERIAS
		 Date dataInicioFerias = null;
		 Date dataFinalFerias = null;
		
		 try {
			 dataInicioFerias = new java.sql.Date(((java.util.Date)formatter.parse(dataFeriasInicio)).getTime());
			 dataFinalFerias = new java.sql.Date(((java.util.Date)formatter.parse(dataFeriasFim)).getTime());
		
		 } catch (ParseException e1) {
			e1.printStackTrace();
		 }		  
		    
		  DataUtil dtUtil = new DataUtil();

		  Calendar cal1 = Calendar.getInstance();
		  cal1.setTime(dataInicioFerias);
		  Calendar cal2 = Calendar.getInstance();
		  cal2.setTime(dataFinalFerias);
		  
		 
		  int qtdDias = dtUtil.retornaDiferencaEmDias2(dataInicioFerias, dataFinalFerias);
		 
		
		 try {
			
			ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
			
			espFerias.adicionar(contratoTO.getCodigo(), periodoAquisitivoI, periodoAquisitivoF, situacao, 
					dataInicioFerias, dataFinalFerias, qtdDias, valor, vendaFerias);
				
			view = request.getRequestDispatcher("PesquisarFerias?acao=Pesquisar&codigoEmpregado=" + codigoEmpregado);
			
        	//view = request.getRequestDispatcher("TelaFerias.jsp");
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

