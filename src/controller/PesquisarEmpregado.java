package controller;


import java.io.IOException;
import java.util.ArrayList;
import model.ContratoTO;
import model.EmpregadoTO;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		EmpregadoTO empregado = null;
		EspecialistaEmpregado especilista = new EspecialistaEmpregado();
		
		RequestDispatcher view;
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		HttpSession session = request.getSession();
		String codigoUsuario = (String) session.getAttribute("codigoUsuario");
		
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
				
				String tipo = request.getParameter("tipo");
				EspecialistaContrato espContrato = new EspecialistaContrato();
				
				if(especilista.pesquisarTodos(codigoUsuario).size() > 0)
				{
					ArrayList<EmpregadoTO> funcionarios = especilista.pesquisarTodos(codigoUsuario);	
					
					if(funcionarios.size() > 0)
					{
						if(tipo!= null && tipo.equals("excluir")){
							request.setAttribute("mge", "Cadastro do empregado excluido com sucesso.");
						}
						else if (tipo!= null && tipo.equals("cadastrar")){
							request.setAttribute("mge", "Cadastro do empregado realizado com sucesso.");
						}	
						else if (tipo!= null && tipo.equals("alterar")){
							request.setAttribute("mge", "Cadastro do empregado atualizado com sucesso.");
						}
						else if (tipo!= null && tipo.equals("erro")){
							request.setAttribute("mge", "Operação não pode ser realizada!");
						}
						
						ArrayList<ContratoTO> contratos = new ArrayList<>();
						for(int i=0; i < funcionarios.size(); i++){
							contratos.add(espContrato.pesquisarEmpregado(funcionarios.get(i).getCodigoEmpregado()));
						}
						
						 request.setAttribute("comboFuncionarios", funcionarios);
						 request.setAttribute("comboContratos", contratos);
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

