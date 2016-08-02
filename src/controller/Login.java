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
	    HttpSession session = request.getSession();
	    RequestDispatcher view = null;
		EspecialistaLogin especialista = new EspecialistaLogin();
		EspecialistaUsuario espUsuario = new EspecialistaUsuario();
		
		switch (acao) 
		{
			
			case "Entrar":
				
			String email = (String) request.getParameter("email");
			String senha = (String) request.getParameter("senha");
		
			UsuarioTO usuario = (UsuarioTO) especialista.verificaLogin(email, senha);
			
			if(usuario != null)
			{
				if(usuario.getAtivo() == true)
				{
					session.setAttribute("codigo", usuario.getCodigo());
					view = request.getRequestDispatcher("TelaAtivandoConta.jsp");
					view.forward(request, response);
				}
				else{
					session.setAttribute("codigo", usuario.getCodigo());
					view = request.getRequestDispatcher("TelaPrincipal.jsp");
					view.forward(request, response);
				}
			}
			else{
		    out.print("<h2>Login não encontrado</h2>");  
	        RequestDispatcher rd=request.getRequestDispatcher("TelaLogin.jsp");  
	        rd.include(request,response); 
	    	out.close();  
			}
	    	break;
	    	
			case "Ativa":
				String cod = (String) session.getAttribute("codigo");
				Boolean ok = espUsuario.ativarUsuario(cod);
				if(ok== true){
				view = request.getRequestDispatcher("TelaPrincipal.jsp");
				view.forward(request, response);
				}
				else
				{
					out.print("<h2>Conta não pode ser ativada!</h2>");  
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
