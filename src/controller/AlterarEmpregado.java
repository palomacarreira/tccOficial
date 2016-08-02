package controller;

import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import model.ContatoTO;
import model.ContratoTO;
import model.EmpregadoTO;
import model.EnderecoTO;
import model.EspecialistaContato;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaEndereco;
import model.EspecialistaJornadaTrabalho;
import model.JornadaTrabalhoTO;
import model.ManipulandoImagem;
import model.UsuarioTO;

/**
 * Servlet implementation class AlterarEmpregado
 */
@WebServlet("/AlterarEmpregado")
public class AlterarEmpregado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarEmpregado() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		EspecialistaEmpregado espEmpregado = new EspecialistaEmpregado();
		EspecialistaContato espContato = new EspecialistaContato();
		EspecialistaEndereco espEndereco = new EspecialistaEndereco();
		EspecialistaContrato espContrato = new EspecialistaContrato();
		EspecialistaJornadaTrabalho espJornada = new EspecialistaJornadaTrabalho();
		
		
		String codEmpregado = (String) session.getAttribute("codigo");
		
		RequestDispatcher view;
		String acao = request.getParameter("acao");
		switch (acao) 
		{
			case "Salvar":
				//alterar dados empregado
				
				byte[] foto = null;
				InputStream inputStream = null;
				
				String caminho = request.getParameter("fotoEmpregado");
			    BufferedImage imagem;
			    if(caminho != "")
			    {
			    	try {		
			    	
			 		imagem = ImageIO.read(new File(caminho));
			 		ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
			 		ImageIO.write((BufferedImage)imagem, "jpg", bytesImg);
			 		bytesImg.flush();
			 		byte[] byteArray = bytesImg.toByteArray();
			 		bytesImg.close();
			 		foto = byteArray;
			 		
			    	} 
			    	catch (IOException e) 
			    	{
			    		e.printStackTrace();
			    	}
			     
			    }
			    	
			    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    String data = request.getParameter("dataNasc");
				Date dataNasc = null;
				String dataA = request.getParameter("dataAdmissao");
				Date dataAdmissao = null;
				try {
					dataNasc = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
					dataAdmissao = new java.sql.Date( ((java.util.Date)formatter.parse(dataA)).getTime());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
		        
				 
				 String horaEntrada = request.getParameter("horaEntrada");
				 String horaSaida  = request.getParameter("horaSaida");
				 String horaSaidaAlmoco = request.getParameter("horaSaidaAlmoco");
				 String horaVoltaAlmoco = request.getParameter("horaVoltaAlmoco");
				 
				
				 String nome = (String) request.getParameter("nome");
				 String sobrenome = (String) request.getParameter("sobrenome");
				 String estadoCivil = (String) request.getParameter("estadoCivil");
				 String sexo = (String) request.getParameter("sexo");
				 String cpf = (String) request.getParameter("cpf");
				 String rg = (String) request.getParameter("rg");
				 String ufRg = (String) request.getParameter("ufRg");
				 String numCarteira = (String) request.getParameter("numCarteira");
				 String serieCarteira = (String) request.getParameter("serieCarteira");
				 String ufCarteira = (String) request.getParameter("ufCarteira");
				 String email = (String) request.getParameter("email");
				 String senha = (String) request.getParameter("senha");
				 Boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
				 int qtdDependentes = Integer.parseInt(request.getParameter("qtdDependentes"));
			
				 String cargo = (String) request.getParameter("cargo");
				 String regimeContrato = (String) request.getParameter("regimeContrato");
				 String diaPagamento = request.getParameter("diaPagamento");
				 Boolean descontoINSS = Boolean.parseBoolean(request.getParameter("descontoINSS"));
				 Double valeTransporte = Double.parseDouble(request.getParameter("valeTransporte"));
				 Double salarioBase = Double.parseDouble(request.getParameter("salarioBase"));
				 String compensacaoDias =(String)request.getParameter("compensacaoDias");
				 String regimeDeTrabalho =(String) request.getParameter("regimeTrabalho");
				 String conta = (String) request.getParameter("conta");
				 String agencia = (String) request.getParameter("agencia");
				 String banco =(String) request.getParameter("banco");
				 String tipoConta =(String) request.getParameter("tipoConta");
			
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
				
				request.setAttribute("msg", "Dados Alterados");
				view = request.getRequestDispatcher("TelaPrincipal.jsp");
				view.forward(request, response);
				
		            
				espEmpregado.alterar(codEmpregado, nome, sobrenome, dataNasc, estadoCivil, sexo, 
				cpf, cargo, ufRg, numCarteira, serieCarteira, ufCarteira, email, senha, ativo, 
				foto, qtdDependentes);
				
				espContato.alterarEmpregado(codEmpregado, tipoContato, numeroTelefone);
		
				espEndereco.alterarEmpregado(codEmpregado, endereco, cidade, estado, 
						numeroEndereco, complemento, cep, bairro);
				
				espContrato.alterarEmpregado(codEmpregado, cargo, diaPagamento, dataAdmissao, 
				descontoINSS, valeTransporte, salarioBase, compensacaoDias, regimeDeTrabalho, horaEntrada,
				horaSaidaAlmoco, horaVoltaAlmoco, horaSaidaAlmoco, tipoConta, agencia, banco, tipoContato);
				
				
				response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            RequestDispatcher rd = null;
	            
	          
	       
	            out.println("<b>Cadastro Realizado!</b><br>");
	        	view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos");
	        	view.forward(request, response);
				
	            out.close();
				
			} catch (NumberFormatException e) {
				
				request.setAttribute("msg", "Error " + e.getMessage());
				view = request.getRequestDispatcher("TelaEmpregado.jsp");
				view.forward(request, response);
			}
			break;
			
		case "Excluir":
		
			break;
			
		case "Alterar":

			String codEmp = (String) request.getParameter("codEmpregado");
			if(codEmp != null){
				
					EmpregadoTO empregadoTO = espEmpregado.pesquisar(codEmp);
					ContatoTO contatoTO = espContato.pesquisarEmpregado(codEmp);
					EnderecoTO enderecoTO = espEndereco.pesquisarEmpregado(codEmp );
					ContratoTO contratoTO = espContrato.pesquisarEmpregado(codEmp);
					ArrayList<JornadaTrabalhoTO> listaJornada = espJornada.pesquisarJornada(contratoTO.getCodigo());
		  		
					String fotoEmpregado = ManipulandoImagem.exibiImagemLabel(empregadoTO.getFoto());
					
					
					try {
						
					request.setAttribute("Foto", fotoEmpregado);
					request.setAttribute("listaEmpregado", empregadoTO);
					request.setAttribute("listaContato", contatoTO);
					request.setAttribute("listaEndereco", enderecoTO);
					request.setAttribute("listaContrato", contratoTO);
					request.setAttribute("listaJornada", listaJornada);
					
					view = request.getRequestDispatcher("TelaAlterarEmpregado.jsp");
					view.forward(request, response);
					
				} catch (NumberFormatException e) {
					request.setAttribute("msg", "Error " + e.getMessage());
					view = request.getRequestDispatcher("TelaEmpregado.jsp");
					view.forward(request, response);
				}
			}
			else{
				view = request.getRequestDispatcher("TelaEmpregado.jsp");
				view.forward(request, response);
			}
			break;
			
		case "Cancelar":
			view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos");
        	view.forward(request, response);
			break;
		}
	}

}
