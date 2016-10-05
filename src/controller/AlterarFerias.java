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
import javax.servlet.http.HttpSession;

import model.ContratoTO;
import model.EspecialistaContrato;
import model.EspecialistaFerias;
import model.EspecialistaPonto;
import model.FeriasTO;
import model.PontoTO;

/**
 * Servlet implementation class AlterarFerias
 */
@WebServlet("/AlterarFerias")
public class AlterarFerias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarFerias() {
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
		EspecialistaContrato espContrato = new EspecialistaContrato();
		EspecialistaFerias espFerias = new EspecialistaFerias();
		String codigoFerias = (String) request.getParameter("codigoFerias");
		String codigoEmpregado = request.getParameter("codigoEmpregado");
		RequestDispatcher view;
		String acao = request.getParameter("acao");
		switch (acao) 
		{
			case "TelaPagamento":
				
					try 
					{
						FeriasTO feriasTO = espFerias.pesquisarFerias(codigoFerias);
						request.setAttribute("codigoEmpregado", codigoEmpregado);
						request.setAttribute("codigoFerias",codigoFerias);
						request.setAttribute("valor",feriasTO.getValor());
						view = request.getRequestDispatcher("TelaPagamentoFerias.jsp");
						view.forward(request, response);
						
					} catch (NumberFormatException e) {
						request.setAttribute("msg", "Error " + e.getMessage());
					}

				break;
				
			case "Pagamento":
				
					String situacao = "CONCLUIDA";
					String dataPagamento = (String)request.getParameter("dataPagamento");
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date data = null;
					
					try 
					{
						 data = new java.sql.Date(((java.util.Date)formatter.parse(dataPagamento)).getTime());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}		
					
					try 
					{
						espFerias.alterarPagamento(codigoFerias, data, situacao);
						view = request.getRequestDispatcher("PesquisarFerias?acao=Pesquisar");
						view.forward(request, response);
						
					} catch (NumberFormatException e) {
						request.setAttribute("msg", "Error " + e.getMessage());
					}

			break;
				
				
		case "Excluir":
			
			
			try {
				espFerias.excluir(codigoFerias);
				view = request.getRequestDispatcher("PesquisarFerias?acao=Pesquisar");
				view.forward(request, response);
			} catch (Exception e) {
				
				request.setAttribute("msg", "Error " + e.getMessage());
			}	
			break;
		}
	}
	
	public ArrayList<FeriasTO> listaPeriodos(Date dataAdmissao)
	{
		ArrayList<FeriasTO> listaPeriodos = new ArrayList<FeriasTO>();
		FeriasTO feriasTO = null;
		
		GregorianCalendar dtAdmissao = new GregorianCalendar();
		GregorianCalendar dtHoje = new GregorianCalendar();
		GregorianCalendar dtAdmissaoMaisUmAno = new GregorianCalendar();
			
		dtAdmissao.setTime(dataAdmissao);
		dtAdmissaoMaisUmAno.setTime(dataAdmissao);
		dtAdmissaoMaisUmAno.add(Calendar.YEAR, 1);
		int qtdLinhas = 0;
		dtHoje.setTime(dtHoje.getTime());
		
		if(dtAdmissaoMaisUmAno.before(dtHoje))
		{

			do{
				feriasTO = new FeriasTO();
				Date d = new Date(dtAdmissao.getTime().getTime());  
				Date d2 = new Date(dtAdmissaoMaisUmAno.getTime().getTime());
				feriasTO.setPeriodoAquisitivoInicio(d);
				feriasTO.setPeriodoAquisitivoFim(d2);
				listaPeriodos.add(feriasTO);

				dtAdmissao.setTime(dtAdmissaoMaisUmAno.getTime());
				dtAdmissaoMaisUmAno.add(Calendar.YEAR, 1);
	 
			}while(dtAdmissaoMaisUmAno.before(dtHoje));
	
		}
		
		return listaPeriodos;
	}
	
	public double calculaValorFerias(double salario)
	{
		return salario + (salario * 1/3);
	}

}
