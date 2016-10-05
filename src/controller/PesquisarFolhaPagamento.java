package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CalculosFolhaDePagamento;
import model.CalculosHoraExtra;
import model.ContratoTO;
import model.EmpregadoTO;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaHorasExtras;
import model.EspecialistaJornadaTrabalho;
import model.EspecialistaPonto;
import model.HoraExtraTO;
import model.JornadaTrabalhoTO;
import model.PontoTO;

/**
 * Servlet implementation class PesquisarFolhaPagamento
 */
@WebServlet("/PesquisarFolhaPagamento")
public class PesquisarFolhaPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarFolhaPagamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EspecialistaEmpregado empregado = new EspecialistaEmpregado();
		EspecialistaContrato contrato = new EspecialistaContrato();
		RequestDispatcher view = null;
		String codigoEmpregado = (String) request.getParameter("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		
		switch (acao) 
		{
			case "Pesquisar":
				
				EmpregadoTO empregadoTO = empregado.pesquisar(codigoEmpregado);
				ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
				String dataAdmissao = formatador.format(contratoTO.getDataAdmissao());
				CalculosFolhaDePagamento calculos = new CalculosFolhaDePagamento();
			 	
				int totalDias = 0;
				int anoEscolhido = 0;
				int mesEscolhido = 0;
				if(request.getParameter("mes") == null)
				{
					anoEscolhido  = Calendar.getInstance().get(Calendar.YEAR);
					mesEscolhido = Calendar.getInstance().get(Calendar.MONTH) + 1;
					totalDias = calculos.diasNoMes(mesEscolhido, anoEscolhido);
				}
				else{
					anoEscolhido = Integer.parseInt(request.getParameter("ano"));
					mesEscolhido = Integer.parseInt(request.getParameter("mes"));
					totalDias = calculos.diasNoMes(mesEscolhido, anoEscolhido);
				}
				
			 	double valorHoraExtra = calculos.totalHoraExtra(codigoEmpregado,mesEscolhido, anoEscolhido,totalDias);
				double salario = contratoTO.getSalarioBase();
				double inss = calculos.totalINSS(salario);
				double fgts = calculos.totalFGTS(salario);
				double irrf = calculos.totalIRRF(salario,codigoEmpregado,inss);
				double valeTransporte = calculos.totalValeTransporte(salario, codigoEmpregado);
				double salarioLiquido = calculos.totalSalario(codigoEmpregado, salario, valeTransporte, 
				inss, irrf, valorHoraExtra);
				
				request.setAttribute("anoEscolhido", anoEscolhido);
				request.setAttribute("mesEscolhido", mesEscolhido);
			 	request.setAttribute("totalDias", totalDias);
				request.setAttribute("inss", inss);
				request.setAttribute("fgts", fgts);
				request.setAttribute("irrf", irrf);
				request.setAttribute("valeTransporte", valeTransporte);
				request.setAttribute("salarioLiquido", salarioLiquido);
				request.setAttribute("valorHoraExtra", valorHoraExtra);
				request.setAttribute("listaEmpregado", empregadoTO);
				request.setAttribute("listaContrato", contratoTO);
				request.setAttribute("codigoEmpregado", codigoEmpregado);
				request.setAttribute("dataAdmissao", dataAdmissao);
				view = request.getRequestDispatcher("TelaFolhaPagamento.jsp");
				view.forward(request, response);
			break;
		}
	}

}
