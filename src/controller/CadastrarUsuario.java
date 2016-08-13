package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EspecialistaContato;
import model.EspecialistaEndereco;
import model.EspecialistaUsuario;


/**
 * Servlet implementation class CadastrarUsuario
 */
@WebServlet("/CadastrarUsuario")
public class CadastrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EspecialistaUsuario espUsuario = new EspecialistaUsuario();
		EspecialistaContato espContato = new EspecialistaContato();
		EspecialistaEndereco espEndereco = new EspecialistaEndereco();
		HttpSession session = request.getSession();
		RequestDispatcher view;
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		switch (acao) 
		{
	
		case "Cadastrar":
			
		String nome = (String) request.getParameter("nome");
		String sobrenome = (String) request.getParameter("sobrenome");
		String cpf = (String) request.getParameter("cpf");
		String cnpj = (String) request.getParameter("cnpj");
		String rg = (String) request.getParameter("rg");
		
		String ufRg = (String) request.getParameter("ufRg");
		String email = (String) request.getParameter("email");
		String senha = (String) request.getParameter("senha");
		Boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
		
		String endereco = (String) request.getParameter("endereco");
		String cidade = (String) request.getParameter("cidade");
		String estado = (String) request.getParameter("estado");
		int numeroEndereco = Integer.parseInt(request.getParameter("numeroEndereco"));
		String complemento = (String) request.getParameter("complemento");
		String cep =(String) request.getParameter("cep");
		String bairro = (String) request.getParameter("bairro");
		
		String numeroTelefone = (String) request.getParameter("numeroTelefone");
		String tipoContato = (String) request.getParameter("tipoContato");
		 
		try {
			
			espUsuario.adicionar(nome, sobrenome, cpf, cnpj, rg, ufRg, email, senha, ativo);
			espContato.adicionarUsuario(tipoContato, numeroTelefone);
			espEndereco.adicionarUsuario( endereco,  cidade,  estado, numeroEndereco,
					complemento, cep, bairro);
					
			
            String codigo = espUsuario.getUltimoCodigo();
            session.setAttribute("codigoUsuario", codigo);
        	view = request.getRequestDispatcher("TelaPrincipal.jsp");
        	view.forward(request, response);
	
			
		} catch (NumberFormatException e) {
			
			request.setAttribute("msg", "Error " + e.getMessage());
			view = request.getRequestDispatcher("TelaLogin.jsp");
			view.forward(request, response);
		}
		break;
		}
	
	}

}
