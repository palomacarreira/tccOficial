package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import relatorio.FeriasRel;
import relatorio.HoleriteRel;
import model.ContratoTO;
import model.EmpregadoTO;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaFerias;
import model.FeriasRelatorio;
import model.FeriasTO;
import model.HoleriteRelatorio;

/**
 * Servlet implementation class PesquisarFerias
 */
@WebServlet("/PesquisarFerias")
public class PesquisarFerias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarFerias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 
		String acao = request.getParameter("acao");
		EspecialistaFerias espFerias = new EspecialistaFerias();
		EspecialistaEmpregado empregado = new EspecialistaEmpregado();
		EspecialistaContrato espContrato = new EspecialistaContrato();
		RequestDispatcher view= null;
		
		String codigoEmpregado = request.getParameter("codigoEmpregado");
		switch (acao) 
		{
			case "Pesquisar":
				try{
					EmpregadoTO empregadoTO = empregado.pesquisar(codigoEmpregado);
					ContratoTO contratoTO = espContrato.pesquisarEmpregado(codigoEmpregado);
					ArrayList<FeriasTO> listaFerias = espFerias.pesquisarTodos(contratoTO.getCodigo());
					if(listaFerias.size() > 0)
					{
						request.setAttribute("comboFerias", listaFerias);
					}else{
						request.setAttribute("comboFerias", null);
					}
					
					
					Date dataAdmissao = contratoTO.getDataAdmissao();
					ArrayList<FeriasTO> listaPeriodos = listaPeriodos(dataAdmissao);
					request.setAttribute("listaPeriodos", listaPeriodos);
					request.setAttribute("codigoEmpregado",codigoEmpregado);
					request.setAttribute("listaEmpregado", empregadoTO);
					view = request.getRequestDispatcher("TelaFerias.jsp");
					view.forward(request, response);
						
				}catch(NumberFormatException e){
					
					request.setAttribute("msg", "Error " + e.getMessage());
					view = request.getRequestDispatcher("TelaPrincipal.jsp");
					view.forward(request, response);
				}
			break;
			
			case "GerarFerias":
			System.out.println("GERANDO RELATORIO DE FERIAS");
			EmpregadoTO empregadoTO = empregado.pesquisar(codigoEmpregado);
			ContratoTO contratoTO = espContrato.pesquisarEmpregado(codigoEmpregado);
			List<FeriasRelatorio> feriasList = new ArrayList<FeriasRelatorio>();
			FeriasRelatorio ferias = new FeriasRelatorio();
		
			//ferias.setNomeEmpregador(contratoTO.get);
			//ferias.setNomeEmpregado(empregadoTO.getNome());
			/*ferias.setCargoEmpregado(cargoEmpregado);
			ferias.setPeriodoaquisitivo(periodoaquisitivo);
			ferias.setSituacao(situacao);
			ferias.setInicio(inicio);
			ferias.setTermino(termino);
			ferias.setQtdDias(qtdDias);
			ferias.setValor(valor);
			ferias.setDataPagamento(dataPagamento);*/
			
			feriasList.add(ferias);

			FeriasRel relatorio = new FeriasRel();
			
			relatorio.imprimir(feriasList);
			
			relatorio.download(response,feriasList);
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


