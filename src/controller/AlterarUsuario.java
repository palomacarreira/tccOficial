package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ContatoTO;
import model.EnderecoTO;
import model.EspecialistaContato;
import model.EspecialistaEndereco;
import model.EspecialistaUsuario;
import model.UsuarioTO;


/**
 * Servlet implementation class AlterarUsuario
 */
@WebServlet("/AlterarUsuario")
public class AlterarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarUsuario() {
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		EspecialistaUsuario espUsuario = new EspecialistaUsuario();
		EspecialistaContato espContato = new EspecialistaContato();
		EspecialistaEndereco espEndereco = new EspecialistaEndereco();
		
		RequestDispatcher view;
		String acao = request.getParameter("acao");
		switch (acao) 
		{
			case "Cadastrar":
				
				view = request.getRequestDispatcher("CadastroUsuario.jsp");
				view.forward(request, response);
				break;
				
			
			case "Salvar":
				//alteração dado 
					
				String codigo = (String) session.getAttribute("codigoUsuario");
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
					
					espUsuario.alterar(codigo, nome, sobrenome, cpf, cnpj, rg, ufRg, email, senha, ativo);
					espContato.alterarUsuario(codigo, tipoContato, numeroTelefone);
					espEndereco.alterarUsuario( codigo, endereco,  cidade,  estado, numeroEndereco,
							complemento, cep, bairro);
					
					request.setAttribute("msg", "Dados Alterados");
					view = request.getRequestDispatcher("TelaPrincipal.jsp");
					view.forward(request, response);
					
				} catch (NumberFormatException e) {
					
					request.setAttribute("msg", "Error " + e.getMessage());
					view = request.getRequestDispatcher("TelaPrincipal.jsp");
					view.forward(request, response);
				}
			break;
			
			case "alterar":
				String codg = (String) session.getAttribute("codigoUsuario");
				
				if(codg != null){
					try {
						
						UsuarioTO usuarioTO = espUsuario.pesquisarUsuario(codg);
						ContatoTO contatoTO = espContato.pesquisarUsuario(codg);
						EnderecoTO enderecoTO = espEndereco.pesquisarUsuario(codg);
						
						request.setAttribute("listaUsuario", usuarioTO);
						request.setAttribute("listaContato", contatoTO);
						request.setAttribute("listaEndereco", enderecoTO);
						
						view = request.getRequestDispatcher("TelaPerfilEmpregador.jsp");
						view.forward(request, response);
						
					} catch (NumberFormatException e) {
						request.setAttribute("msg", "Error " + e.getMessage());
						view = request.getRequestDispatcher("TelaPrincipal.jsp");
						view.forward(request, response);
					}
				}
				else{
					view = request.getRequestDispatcher("TelaPrincipal.jsp");
					view.forward(request, response);
				}
				break;
				
		case "excluir":
			String cod = (String) session.getAttribute("codigo");
			try {
				espUsuario.excluir(cod);
				request.setAttribute("msg", "Cadastro Excluído");
				view = request.getRequestDispatcher("TelaLogin.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				
				request.setAttribute("msg", "Error " + e.getMessage());
				view = request.getRequestDispatcher("TelaPerfilEmpregador.jsp");
				view.forward(request, response);
			}	
			break;
		}
	}

}
