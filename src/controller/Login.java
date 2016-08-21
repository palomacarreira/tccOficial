package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EspecialistaLogin;
import model.EspecialistaUsuario;
import model.UsuarioTO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		PrintWriter out = response.getWriter();  
	    RequestDispatcher view = null;
		EspecialistaLogin especialista = new EspecialistaLogin();
		EspecialistaUsuario espUsuario = new EspecialistaUsuario();
		HttpSession session = request.getSession();
		
		switch (acao) 
		{
			case "Entrar":
					
			String email = (String) request.getParameter("email");
			String senha = (String) request.getParameter("senha");
		
			UsuarioTO usuario = (UsuarioTO) especialista.verificaLogin(email, senha);
		
		    
			if(usuario != null){
				if(usuario.getAtivo() == true)
				{
					session.setAttribute("codigoUsuario", usuario.getCodigo());
					view = request.getRequestDispatcher("TelaAtivandoConta.jsp");
					view.forward(request, response);
				}
				else{
					session.setAttribute("codigoUsuario", usuario.getCodigo());
					view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=cadastrar");
					view.forward(request, response);
				}
			}
			else{
				request.setAttribute("mge", "O email inserido n�o corresponde a nenhuma conta. Cadastre-se para abrir uma conta"); 
				view = request.getRequestDispatcher("TelaLogin.jsp");
				view.forward(request, response);  
			}
	    	break;
	    	
			case "Ativa":	
				String cod = (String) session.getAttribute("codigoUsuario");
				Boolean ok = espUsuario.ativarUsuario(cod);
				if(ok== true){
					view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=cadastrar");
				view.forward(request, response);
				}
				else
				{
					out.print("<h2>Conta n�o pode ser ativada!</h2>");  
			        RequestDispatcher rd=request.getRequestDispatcher("TelaLogin.jsp");  
			        rd.include(request,response); 
			    	out.close();  
				}
				
			break;
			
			case "Sair":
				view = request.getRequestDispatcher("TelaLogin.jsp");
				view.forward(request, response);
			break;
		}
		
	}

}
