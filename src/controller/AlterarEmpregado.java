package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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


/**
 * Servlet implementation class AlterarEmpregado
 */
@WebServlet("/AlterarEmpregado")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
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
		RequestDispatcher view;
		String codigoUsuario = (String) session.getAttribute("codigoUsuario");
		String codEmpregado = (String) session.getAttribute("codEmpregado");
		String acao = request.getParameter("acao");
		switch (acao) 
		{
			case "Salvar":
			
				 
				 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				 Date dataAdmissao = null;
				 Date dataNasc = null;
				 String dataA = request.getParameter("dataNasc");
				 String dataB = request.getParameter("dataAdmissao");
				 
				 try {
					dataNasc = new java.sql.Date( ((java.util.Date)formatter.parse(dataA)).getTime() );
					dataAdmissao = new java.sql.Date( ((java.util.Date)formatter.parse(dataB)).getTime());
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
				 String email = (String) request.getParameter("email");
				 String senha = (String) request.getParameter("senha");
				 Boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
				 int qtdDependentes = Integer.parseInt(request.getParameter("qtdDependentes"));
			
				 String duracaoSemanal = (String) request.getParameter("duracaoSemanal");
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
				 String fotoAntiga = (String) session.getAttribute("fotoAntiga"); 
				 for(Part part: request.getParts()){
					 if(part.getName().equals("fotoEmpregado")) 
					 {
						 foto = this.getFileName(part);				
						 if(foto.equals("")) // SE SELECIONOU ALGUMA FOTO
						 {
							 foto = fotoAntiga;
						 }	else{
							 foto = codEmpregado + "_" + getFileName(part);
						 }
						 break;
					 }
				 }
				 
			try {
				
				espEmpregado.alterar(codEmpregado, nome,  sobrenome, dataNasc, 
				estadoCivil, sexo, cpf,  rg, ufRg, numCarteira, serieCarteira,  ufCarteira, 
						 email,  senha,  ativo, foto, qtdDependentes);
				
				espContato.alterarEmpregado(codEmpregado, tipoContato, numeroTelefone);
		
				espEndereco.alterarEmpregado(codEmpregado, endereco, cidade, estado, 
						numeroEndereco, complemento, cep, bairro);
				
				espContrato.alterarEmpregado(codEmpregado,cargo,diaPagamento, 
						 dataAdmissao.toString(), descontoINSS,  valeTransporte, 
						 salarioBase, compensacaoDias,  regimeDeTrabalho,  
					  conta,  agencia,  banco,  tipoConta, duracaoSemanal, descontoBeneficios);
				
				String codigoContrato = espContrato.pesquisarEmpregado(codEmpregado).getCodigo(); 
				ArrayList<JornadaTrabalhoTO> listaDeJornadas = espJornada.pesquisarJornada(codigoContrato);
				int i = 1;
				for(JornadaTrabalhoTO jornada: listaDeJornadas)
				{
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

					 espJornada.alterarJornada(jornada.getCodigo(),horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, 
								horaSaida, diaSemana, diaFolga, diaMeioPeriodo);
					 
					 i = i+1; // CONTADOR
				 } 
				
				 for(Part part: request.getParts()){
					 if(part.getName().equals("fotoEmpregado")) 
					 {
						 foto = this.getFileName(part);
						 if(!foto.equals("")) // SE SELECIONOU ALGUMA FOTO
						 {
							 File dirX = new File((getServletContext().getRealPath("")+ "/"+ "uploads"));
							 if( !dirX.isDirectory() ){
						            dirX.mkdir();
						     }	
							 File dir = new File((getServletContext().getRealPath("")+ "/"+ "uploads") + "/" + codigoUsuario );// diret�rio de upload
							 //se o diret�rio n�o existe ele cria
						     if( !dir.isDirectory() ){
						         dir.mkdir();
						     }	
						     
						     foto = codEmpregado + "_" + getFileName(part);
						     espEmpregado.alterarFoto(codEmpregado ,foto); // FAZ UPDATE NA TABELA
							 if(fotoAntiga.equals("")){
								 File arquivo = new File( dir + "/" + foto); 
								 part.write( arquivo.getAbsolutePath() );  
							 }
							 else
							 {
								foto = codEmpregado + "_" + getFileName(part);
								// DELETA ANTIGA E COLOCA A NOVA
								File arquivo = new File(dir + "/" + fotoAntiga);
								if(arquivo.delete()){
									arquivo = new File(dir + "/" + foto); 
									part.write( arquivo.getAbsolutePath() );
								}
								else{
											//ERROOO
								}
						     }
						 }
					 }
				 }
				 
		       //view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=alterar");
		       view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos");
		       view.forward(request, response);

			} catch (NumberFormatException e) {
				 view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=erro");
				 view.forward(request, response);
			}
			break;
			
		case "Demitir":
			String codE = (String) request.getParameter("codEmpregado");
			if(codE != null){
				
				EmpregadoTO empregadoTO = espEmpregado.pesquisar(codE);
				ContratoTO contratoTO = espContrato.pesquisarEmpregado(codE);	
				try 
				{
					request.setAttribute("listaEmpregado", empregadoTO);
					request.setAttribute("listaContrato", contratoTO);
					view = request.getRequestDispatcher("TelaRescisao.jsp");
					view.forward(request, response);
					
				} catch (NumberFormatException e) {
					request.setAttribute("msg", "Error " + e.getMessage());
					 view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=erro");
					 view.forward(request, response);
				}
			}
			else{
				 view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos");
				 view.forward(request, response);
			}
		break;
		
		case "Excluir":
			String codX = (String) request.getParameter("codEmpregado");
			try {
				 espEmpregado.excluir(codX);
				 File dirX = new File( getServletContext().getRealPath("uploads"));
				 for(Part part: request.getParts()){
					 if(part.getName().equals("fotoEmpregado")) 
					 {
						 foto = this.getFileName(part);
						 File arquivo = new File(dirX.getAbsolutePath() + "/" + foto);
						 if(arquivo.delete()){
						 }
						 else{//ERRO
						 }
					  }	
				  }	
				view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=excluir");
				view.forward(request, response);
				
			} catch (Exception e) {
				request.setAttribute("msg", "Error " + e.getMessage());
				view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=erro");
	        	view.forward(request, response);
			}	
			break;
			
		case "Alterar":

			String codEmp = (String) request.getParameter("codEmpregado");
			request.getSession().setAttribute("codEmpregado", codEmp);
			if(codEmp != null){
					
					EmpregadoTO empregadoTO = espEmpregado.pesquisar(codEmp);
					ContatoTO contatoTO = espContato.pesquisarEmpregado(codEmp);
					EnderecoTO enderecoTO = espEndereco.pesquisarEmpregado(codEmp );
					ContratoTO contratoTO = espContrato.pesquisarEmpregado(codEmp);
					ArrayList<JornadaTrabalhoTO> listaJornada = espJornada.pesquisarJornada(contratoTO.getCodigo());

					try {

					request.getSession().setAttribute("fotoAntiga", empregadoTO.getFoto());
					String caminhoFoto = "";
					if(!empregadoTO.getFoto().equals("")){
						caminhoFoto = codigoUsuario +"/"+ empregadoTO.getFoto();
					}
					request.setAttribute("Foto",caminhoFoto);
					request.setAttribute("listaEmpregado", empregadoTO);
					request.setAttribute("listaContato", contatoTO);
					request.setAttribute("listaEndereco", enderecoTO);
					request.setAttribute("listaContrato", contratoTO);
					request.setAttribute("listaJornada", listaJornada);
					
					view = request.getRequestDispatcher("TelaAlterarEmpregado.jsp");
					view.forward(request, response);
					
				} catch (NumberFormatException e) {
					request.setAttribute("msg", "Error " + e.getMessage());
					 view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos&tipo=erro");
					 view.forward(request, response);
				}
			}
			else{
				 view = request.getRequestDispatcher("PesquisarEmpregado?acao=PesquisarTodos");
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
