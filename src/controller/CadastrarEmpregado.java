package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.EspecialistaContato;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaEndereco;
import model.EspecialistaJornadaTrabalho;



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
		
		HttpSession session = request.getSession();
		String codigoUsuario = (String) session.getAttribute("codigoUsuario");
		
		switch (acao) 
		{
		 
		case "Adicionar":
			view = request.getRequestDispatcher("TelaCadastroEmpregado.jsp");
        	view.forward(request, response);
			
		break;
		
		case "Cadastrar":

			 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 Date dataAdmissao = null;
			 Date dataNasc = null;
			 String dataA = request.getParameter("dataNasc");
			 String dataB = request.getParameter("dataAdmissao");
			 try {
				dataNasc = new java.sql.Date(((java.util.Date)formatter.parse(dataA)).getTime());
				dataAdmissao = new java.sql.Date(((java.util.Date)formatter.parse(dataB)).getTime());
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
			 String duracaoSemanal = (String) request.getParameter("duracaoSemanal");
             Double descontoBeneficios = Double.parseDouble(request.getParameter("descontoBeneficios"));
             
			 String endereco = (String) request.getParameter("endereco");
			 String cidade = (String) request.getParameter("cidade");
			 String estado = (String) request.getParameter("estado");
			 int numeroEndereco = Integer.parseInt(request.getParameter("numeroEndereco"));
			 String complemento = (String) request.getParameter("complemento");
			 String cep =(String) request.getParameter("cep");
			 String bairro = (String) request.getParameter("bairro");
			 
			 String numeroTelefone = (String) request.getParameter("numeroTelefone");
			 String tipoContato = (String) request.getParameter("tipoContato");
			
				
			 String foto = "";
		      for(Part part: request.getParts()){
		        if(part.getName().equals("fotoEmpregado")) {
		        	foto = this.getFileName(part);  
		        	break;
		        }
		     }

		try {
			
			espEmpregado.adicionar(nome, sobrenome, dataNasc, estadoCivil, sexo, 
					cpf, rg,  ufRg,  numCarteira, serieCarteira, ufCarteira, 
				 login,  senha, ativo,  foto, qtdDependentes, codigoUsuario);

			
			espContato.adicionarEmpregado(tipoContato, numeroTelefone);
			espEndereco.adicionarEmpregado( endereco,  cidade,  estado, numeroEndereco,
					complemento, cep, bairro);
			
			espContrato.adicionarEmpregado(cargo, diaPagamento, 
			dataAdmissao, descontoINSS, valeTransporte, 
			salarioBase, compensacaoDias,regimeDeTrabalho,
			conta,  agencia,  banco,  tipoConta, duracaoSemanal, descontoBeneficios);
					
			
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
				 Boolean diaNaoTrabalhado = false;
				 String diaF = request.getParameter("diaFolga"+ i);
				 String diaM = request.getParameter("diaMeioPeriodo"+ i);
				 
			     if(diaF == null){
					 diaFolga = false;
				 }else{diaFolga = true;}
				 
				 if(diaM == null){
					 diaMeioPeriodo = false;
				 }else{diaMeioPeriodo = true;}
					
				 if(horaEntrada.equals("") && horaSaida.equals("") && horaSaidaAlmoco.equals("") && horaVoltaAlmoco.equals(""))
				 {
					 if(diaFolga == false){
						 diaNaoTrabalhado = true;
					 }
				 }
				 
				 espJornada.adicionarJornada(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, 
							horaSaida, diaSemana, diaFolga, diaMeioPeriodo, diaNaoTrabalhado);
			 } 

			
			 // CRIA NO DIRETORIO DO PROJETO (PASTA UPLOADS) E UMA PASTA DENTRO COM O ID DO USUARIO
			 //PARA EVITAR CONFLITO DE NOMES DE ARQUIVO ATUALIZADA O CAMPO NA TABELA 
			 // A FOTO CADASTRADA TERÁ O NOME: idEmpregado_nomeFoto
			 //PEGA O ÚLTIMO CODIGO CADASTRADO
			 String codEmpr = espEmpregado.getUltimoCodigo();
			 File dirX = new File((getServletContext().getRealPath("")+ "/"+ "uploads"));
			 if( !dirX.isDirectory() ){
		            dirX.mkdir();
		     }	
			 File dir = new File((getServletContext().getRealPath("")+ "/"+ "uploads")+ "/" + codigoUsuario );// diretório de upload
			 //se o diretório não existe ele cria
		        if( !dir.isDirectory() ){
		            dir.mkdir();
		        }	
		    
		      //grava o arquivo no disco com o id do empregado
		      for(Part part: request.getParts()){
		        if(part.getName().equals("fotoEmpregado")) {
		        	foto = this.getFileName(part);  
					if(!foto.equals("")) // SE SELECIONOU ALGUMA FOTO
					{
						 foto = codEmpr + "_" + getFileName(part);
						 espEmpregado.alterarFoto(codEmpr ,foto); // FAZ UPDATE NA TABELA
						 File arquivo = new File(dir + "/" + foto); 
						 part.write( arquivo.getAbsolutePath() );
						 break;
					}
		        }
		     }
		        
		      
            String codigoEmpregado = espEmpregado.getUltimoCodigo();
            request.setAttribute("codigo", codigoEmpregado);
        	view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=cadastrar");
        	view.forward(request, response);
			
		} catch (NumberFormatException e) {
			view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=erro");
        	view.forward(request, response);
		}
		break;
		}
	
	}

	public String getFileName(Part part){
	    String header = part.getHeader( "content-disposition" );
	    for( String tmp : header.split(";") ){
	        if( tmp.trim().startsWith("filename") ){
	            return tmp.substring( tmp.indexOf("=")+2 , tmp.length()-1 );
	        }
    	}
    	return null;
	}

}


