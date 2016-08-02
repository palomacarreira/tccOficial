package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;

import model.EspecialistaContato;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaEndereco;
import model.EspecialistaJornadaTrabalho;
import model.ManipulandoImagem;


/**
 * Servlet implementation class CadastrarEmpregado
 */
@WebServlet("/CadastrarEmpregado")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class CadastrarEmpregado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarEmpregado() {
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
		EspecialistaEmpregado espEmpregado = new EspecialistaEmpregado();
		EspecialistaContato espContato = new EspecialistaContato();
		EspecialistaEndereco espEndereco = new EspecialistaEndereco();
		EspecialistaContrato espContrato = new EspecialistaContrato();
		EspecialistaJornadaTrabalho espJornada = new EspecialistaJornadaTrabalho();
		
		RequestDispatcher view;
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
	
		
		switch (acao) 
		{
		        
		case "Cadastrar":
		
			
			byte[] foto = ManipulandoImagem.getImgBytes(request.getParameter("fotoEmpregado"));
			
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
			 String login = (String) request.getParameter("email");
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
			
			espEmpregado.adicionar(nome, sobrenome, dataNasc, estadoCivil, sexo, 
					cpf, rg,  ufRg,  numCarteira, serieCarteira, ufCarteira, 
				 login,  senha, ativo,  foto, qtdDependentes);
			
			espContato.adicionarEmpregado(tipoContato, numeroTelefone);
			espEndereco.adicionarEmpregado( endereco,  cidade,  estado, numeroEndereco,
					complemento, cep, bairro);
			
			espContrato.adicionarEmpregado(cargo, diaPagamento, 
			dataAdmissao, descontoINSS, valeTransporte, 
			salarioBase, compensacaoDias,regimeDeTrabalho,
			conta,  agencia,  banco,  tipoConta);
			
			
			for(int i = 1; i < 8; i++){
				
				 String horaEntrada = request.getParameter("horaEntrada" + i);
				 if(horaEntrada == null){horaEntrada = "";}
				 String horaSaida  = request.getParameter("horaSaida" + i);
				 if(horaSaida == null){horaSaida = "";}
				 String horaSaidaAlmoco = request.getParameter("horaSaidaAlmoco"+ i);
				 if(horaSaidaAlmoco == null){horaSaidaAlmoco = "";}
				 String horaVoltaAlmoco = request.getParameter("horaVoltaAlmoco"+ i);
				 if(horaVoltaAlmoco == null){horaVoltaAlmoco = "";}
				 int diaSemana = i;
				 
				 Boolean diaFolga = null;
				 Boolean diaMeioPeriodo = null;
				 String diaF = request.getParameter("diaFolga"+ i);
				 String diaM = request.getParameter("diaMeioPeriodo"+ i);
				 
				 if(diaF == null){
					 diaFolga = false;
				 }else{diaFolga = true;}
				 
				 if(diaM == null){
					 diaMeioPeriodo = false;
				 }else{diaMeioPeriodo = true;}
				 
					
				 espJornada.adicionarJornada(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, 
							horaSaida, diaSemana, diaFolga, diaMeioPeriodo);
			 } 
			
			
            String codigoEmpregado = espEmpregado.getUltimoCodigo();
            request.setAttribute("codigo", codigoEmpregado);
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<b>Cadastro Realizado!</b><br>");
        	view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos");
        	view.forward(request, response);
			
            out.close();
			
		} catch (NumberFormatException e) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<b>Cadastro não pode ser realizado!</b><br>");
			request.setAttribute("msg", "Error " + e.getMessage());
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


