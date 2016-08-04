package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ContratoTO;
import model.EmpregadoTO;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.ManipulandoImagem;

/**
 * Servlet implementation class PesquisarEmpregado
 */
@WebServlet("/PesquisarEmpregado")
public class PesquisarEmpregado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarEmpregado() {
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

		RequestDispatcher view= null;
		HttpSession session = request.getSession();
		EmpregadoTO empregado = null;
		EspecialistaEmpregado especilista = new EspecialistaEmpregado();
		
		switch (acao) 
		{
		case "Pesquisar":
			try{
				
				empregado = especilista.pesquisar(request.getParameter("codigo"));
				request.setAttribute("listaEmpregado", empregado);
				view = request.getRequestDispatcher("TelaPassagem.jsp");
				view.forward(request, response); 
				
			}
			catch(NumberFormatException e){
				request.setAttribute("msg", "Error " + e.getMessage());
				view = request.getRequestDispatcher("TelaEmpregado.jsp");
				view.forward(request, response);
			} 
		break;
		
		
		case "PesquisarTodos":
	
			try
			{			
				String codigoUsuario = (String) session.getAttribute("codigo");	
				String tipo = request.getParameter("tipo");
				
				if(especilista.pesquisarTodos(codigoUsuario).size() > 0)
				{
					ArrayList<EmpregadoTO> funcionarios = especilista.pesquisarTodos(codigoUsuario);	
					
					if(funcionarios.size() > 0)
					{
						if(tipo!= null && tipo.equals("excluir")){
							request.setAttribute("mge", "Funcionário Excluido!");
						}
						else if (tipo!= null && tipo.equals("cadastrar")){
							request.setAttribute("mge", "Funcionário Cadastrado!");
						}	
						else if (tipo!= null && tipo.equals("alterar")){
							request.setAttribute("mge", "Funcionário Alterado!");
						}
						else if (tipo!= null && tipo.equals("erro")){
							request.setAttribute("mge", "Operação não pode ser realizada!");
						}
						 request.setAttribute("combo", funcionarios);
						 view = request.getRequestDispatcher("TelaEmpregado.jsp");
						 view.forward(request, response);
					}
				}
				else{
					request.setAttribute("combo", null);
					view = request.getRequestDispatcher("TelaEmpregado.jsp");
					view.forward(request, response);
				}
		}
		catch(NumberFormatException e){
			
			request.setAttribute("msg", "Error " + e.getMessage());
			view = request.getRequestDispatcher("TelaPrincipal.jsp");
			view.forward(request, response);
		}
		break;
		}
	}
	
}

