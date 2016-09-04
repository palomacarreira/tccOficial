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

import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaPonto;
import model.PontoTO;

/**
 * Servlet implementation class PesquisarPonto
 */
@WebServlet("/PesquisarPonto")
public class PesquisarPonto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarPonto() {
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
		EspecialistaContrato contrato = new EspecialistaContrato();
		EspecialistaPonto ponto = new EspecialistaPonto();
		
		RequestDispatcher view;
		String codigoEmpregado = (String) request.getParameter("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		switch (acao) 
		{
			case "Pesquisar":
				
				ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
				String dataAdmissao = formatador.format(contratoTO.getDataAdmissao());
				ArrayList<PontoTO> listaDePonto = ponto.pesquisar(codigoEmpregado);
				
				if(request.getParameter("mes") == null)
				{
					int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
					int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
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
				request.setAttribute("listaPonto", listaDePonto);
				request.setAttribute("dataAdmissao", dataAdmissao);
				view = request.getRequestDispatcher("TelaPonto.jsp");
				view.forward(request, response);
			break;
			
			case "CadastrarAlterar":
				
				contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				formatador = new SimpleDateFormat("yyyy-MM-dd");
				dataAdmissao = formatador.format(contratoTO.getDataAdmissao());
				listaDePonto = ponto.pesquisar(codigoEmpregado);
	
				if(request.getParameter("mes") == null)
				{
					int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
					int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
					int totalDias = diasNoMes(mesAtual, anoAtual);

					request.setAttribute("anoEscolhido", anoAtual);
					request.setAttribute("mesEscolhido", mesAtual);
				 	request.setAttribute("totalDias", totalDias);
				 	request.setAttribute("codigoEmpregado", codigoEmpregado);
					request.setAttribute("listaPonto", listaDePonto);
					request.setAttribute("dataAdmissao", dataAdmissao);
					
					view = request.getRequestDispatcher("TelaCadastrarPonto.jsp");
					for(PontoTO lista : listaDePonto)
					{
						String dataPonto = formatador.format(lista.getDataPonto());
						int diaPonto = Integer.parseInt(dataPonto.substring(8,10));
						int mesPonto = Integer.parseInt(dataPonto.substring(5,7));
						int anoPonto = Integer.parseInt(dataPonto.substring(0,4));
						if((mesPonto == mesAtual) && (anoPonto == anoAtual))
						{ 
							view = request.getRequestDispatcher("TelaAlterarPonto.jsp");
						}
					}
					view.forward(request, response);
				}
				else
				{
					int anoEscolhido = Integer.parseInt(request.getParameter("ano"));
					int mesEscolhido = Integer.parseInt(request.getParameter("mes"));
					int totalDias = diasNoMes(mesEscolhido, anoEscolhido);
					
					request.setAttribute("anoEscolhido", anoEscolhido);
					request.setAttribute("mesEscolhido", mesEscolhido);
				 	request.setAttribute("totalDias", totalDias);
				 	request.setAttribute("codigoEmpregado", codigoEmpregado);
					request.setAttribute("listaPonto", listaDePonto);
					request.setAttribute("dataAdmissao", dataAdmissao);
					
					view = request.getRequestDispatcher("TelaCadastrarPonto.jsp");
					for(PontoTO lista : listaDePonto)
					{
						String dataPonto = formatador.format(lista.getDataPonto());
						int diaPonto = Integer.parseInt(dataPonto.substring(8,10));
						int mesPonto = Integer.parseInt(dataPonto.substring(5,7));
						int anoPonto = Integer.parseInt(dataPonto.substring(0,4));
						if((mesPonto == mesEscolhido) && (anoPonto == anoEscolhido))
						{ 
							view = request.getRequestDispatcher("TelaAlterarPonto.jsp");
						}
					}
					view.forward(request, response);
				}
			break;
		}
	}
	
	public int diasNoMes(int mes, int ano){
	 	return 32 - new Date(ano, mes, 32).getDate();
	}
}