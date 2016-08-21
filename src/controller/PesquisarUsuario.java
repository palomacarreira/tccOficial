package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContatoTO;
import model.EnderecoTO;
import model.EspecialistaContato;
import model.EspecialistaEndereco;
import model.EspecialistaUsuario;
import model.UsuarioTO;

/**
 * Servlet implementation class PesquisarUsuario
 */
@WebServlet("/PesquisarUsuario")
public class PesquisarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarUsuario() {
        super();
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
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		EspecialistaUsuario espUsuario = new EspecialistaUsuario();
		EspecialistaEndereco espEndereco = new EspecialistaEndereco();
		EspecialistaContato espContato = new EspecialistaContato();
		
		UsuarioTO usuario = null;
		EnderecoTO endereco = null;
		ContatoTO contato = null;
		RequestDispatcher view= null;
		
		String codigo = request.getParameter("codigo");
		switch (acao) 
		{
			case "Pesquisar":
				try{
						usuario = espUsuario.pesquisarUsuario(codigo);
						endereco = espEndereco.pesquisarUsuario(codigo);
						contato = espContato.pesquisarUsuario(codigo);
		
						request.setAttribute("listaUsuario", usuario);
						request.setAttribute("listaContato", contato);
						request.setAttribute("listaEndereco", endereco);
						view = request.getRequestDispatcher("TelaPerfilEmpregado.jsp");
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
