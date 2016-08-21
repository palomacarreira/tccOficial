package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaFerias;
import model.FeriasTO;

/**
 * Servlet implementation class PesquisarFerias
 */
@WebServlet("/PesquisarFerias")
public class PesquisarFerias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarFerias() {
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
		EspecialistaContrato espContrato = new EspecialistaContrato();
		RequestDispatcher view= null;
		
		String codigoEmpregado = request.getParameter("codigoEmpregado");

		
		switch (acao){
			case "Pesquisar":
				try{
					ContratoTO contratoTO = espContrato.pesquisarEmpregado(codigoEmpregado);
					Date dataAdmissao = contratoTO.dataAdmissao;
					ArrayList<FeriasTO> listaFerias = espFerias.ferias(contratoTO.getCodigo(), dataAdmissao);
					if(listaFerias.size() > 0)
					{
						request.setAttribute("comboFerias", listaFerias);
					}else{
						request.setAttribute("comboFerias", null);
					}
					request.setAttribute("codigoEmpregado",codigoEmpregado);
					request.setAttribute("dataAdmissao",dataAdmissao);
					view = request.getRequestDispatcher("TelaFerias.jsp");
					view.forward(request, response);
						
				}catch(NumberFormatException e){
					
					request.setAttribute("msg", "Error " + e.getMessage());
					view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=cadastrar");
					view.forward(request, response);
				}
			break;

			
		}
			
	}
}


