package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaPonto;
import model.PontoTO;

/**
 * Servlet implementation class CadastrarPonto
 */
@WebServlet("/CadastrarPonto")
   
public class CadastrarPonto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarPonto() {
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

		EspecialistaContrato contrato = new EspecialistaContrato();
		EspecialistaPonto ponto = new EspecialistaPonto();
		
		RequestDispatcher view;
		String codigoEmpregado = (String) request.getParameter("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		switch (acao) 
		{
			
			case "TelaCadastrar":
				
				ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				ArrayList<PontoTO> listaDePonto = ponto.pesquisar(codigoEmpregado);
				SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
				String dataAdmissao = formatador.format(contratoTO.getDataAdmissao());

				if(request.getParameter("mes") == null)
				{
					Calendar calendar = new GregorianCalendar();
					int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
					int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
					int totalDias = diasNoMes(mesAtual, anoAtual);

					request.setAttribute("anoEscolhido", anoAtual);
					request.setAttribute("mesEscolhido", mesAtual);
				 	request.setAttribute("totalDias", totalDias);
				}
				else{
					int anoEscolhido = Integer.parseInt(request.getParameter("ano"));
					int mesEscolhido = Integer.parseInt(request.getParameter("mes"));
					int totalDias = diasNoMes(mesEscolhido, anoEscolhido);
					
					request.setAttribute("anoEscolhido", anoEscolhido);
					request.setAttribute("mesEscolhido", mesEscolhido);
				 	request.setAttribute("totalDias", totalDias);
				}
				
				request.setAttribute("codigoEmpregado", codigoEmpregado);
				request.setAttribute("listaDePonto", listaDePonto);
				request.setAttribute("dataAdmissao", dataAdmissao);
				view = request.getRequestDispatcher("TelaCadastrarPonto.jsp");
				view.forward(request, response);
			break;
			
			case "Cadastrar":
				// CASO QUERIA PREENCHER MANUALMENTE
				// SER� INSERIDO NAS LINHAS DA TABELA INPUTS
				
				try {
					
					int totalDeDias = Integer.parseInt(request.getParameter("totalDeDias"));
					
					for(int i = 1; i <= totalDeDias; i++){
						
						 String horaEntrada = request.getParameter("horaEntrada" + i);
						 if(horaEntrada == null){horaEntrada = "";}
						 String horaSaida  = request.getParameter("horaSaida" + i);
						 if(horaSaida == null){horaSaida = "";}
						 String horaSaidaAlmoco = request.getParameter("horaSaidaAlmoco"+ i);
						 if(horaSaidaAlmoco == null){horaSaidaAlmoco = "";}
						 String horaVoltaAlmoco = request.getParameter("horaVoltaAlmoco"+ i);
						 if(horaVoltaAlmoco == null){horaVoltaAlmoco = "";}
						 
						 String dia = Integer.toString(i);
						 if(i < 10){dia = "0"+i;}
						 String mes = request.getParameter("mes");
						 if(mes.length() == 1){mes = "0"+mes;}
						 String ano = request.getParameter("ano");
						 String dataEscolhida = ano+"-"+mes+"-"+dia;
						 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						 Date data = null;
						 data= new java.sql.Date(((java.util.Date)formatter.parse(dataEscolhida)).getTime());
						 String acaoSelecionada = (String) request.getParameter("acaoSelecionada"+ i);
							
						 ponto.adicionar(data, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida,
									acaoSelecionada, codigoEmpregado);
					}
					
					view = request.getRequestDispatcher("PesquisarPonto?acao=Pesquisar&codigoEmpregado="+ codigoEmpregado);
					view.forward(request, response);	
				} catch (ParseException e) {
					request.setAttribute("msg", "Error " + e.getMessage());
					view = request.getRequestDispatcher("PesquisarPonto?acao=Pesquisar&codigoEmpregado="+ codigoEmpregado);
					view.forward(request, response);
				}
				break;
		}
	
	}
	
	public int diasNoMes(int mes, int ano){
	 	return 32 - new Date(ano, mes, 32).getDate();
	}
	
}
