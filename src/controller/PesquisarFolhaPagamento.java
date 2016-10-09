package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CalculosPagamento;
import model.ContratoTO;
import model.EmpregadoTO;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaUsuario;
import model.HoleriteRelatorio;
import model.UsuarioTO;
import relatorio.HoleriteRel;

/**
 * Servlet implementation class PesquisarFolhaPagamento
 */
@WebServlet("/PesquisarFolhaPagamento")
public class PesquisarFolhaPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarFolhaPagamento() {
        super();
        // TODO Auto-generated constructor stub
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
		EspecialistaEmpregado empregado = new EspecialistaEmpregado();
		EspecialistaContrato contrato = new EspecialistaContrato();
		RequestDispatcher view = null;
		String codigoEmpregado = (String) request.getParameter("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		
		switch (acao) 
		{
			case "GerarHolerite":
			try{
			 					
			 	EspecialistaEmpregado emp = new EspecialistaEmpregado();
			 	EspecialistaUsuario usuario = new EspecialistaUsuario();
			 	List<HoleriteRelatorio> holerites = new ArrayList<HoleriteRelatorio>();
			 	String codEmpregado = request.getParameter("codEmpregado");
			 	ContratoTO contTO = contrato.pesquisarEmpregado(codEmpregado);
			 	UsuarioTO usuarioTO = usuario.pesquisarUsuario(codEmpregado);
			 	EmpregadoTO emprTO = emp.pesquisar(codEmpregado);	
			 	Date data = new java.sql.Date(new java.util.Date().getTime());
			 						
			 	System.out.println("Gerando PDF do holerite");
			 					
			 	HoleriteRelatorio holerite = new HoleriteRelatorio();
			 						
			 	holerite.setNomeEmpregador(usuarioTO.getNome() +" "+ usuarioTO.getSobrenome());
			 	holerite.setIdEmpregado(emprTO.getCodigoEmpregado());
			 	holerite.setNomeEmpregado(emprTO.getNome()+" "+ emprTO.getSobrenome());
			 	holerite.setCargoEmpregado(contTO.getCargo());
			 	holerite.setSalarioBase(contTO.getSalarioBase());
			 	holerite.setPorcentagemDescontoInss(0.6);//ISSO TEM QUE PEGAR SA TABELA PARAMETRO DO BANCO
			 	holerite.setValorDescontoInss(9.0);//ISSO E A PORCENTAGEM SOBRE O SALARIO
			 	holerite.setSalarioLiquido(9.0); //ISSO E O SALARIO BASE MENOS TODOS OS DESCONTOS
			 	holerite.setDataDoHolerite(data);//MES E ANO DO HOLERITE
			 	holerite.setHoraExtra(9.0);//ISSO E O VALOR ADICIONAL REFERENTE HORA EXTRA DO MES EM QUESTAO
			 	holerite.setAdicionalNoturno(9.0);//ISSO E O VALOR ADICIONAL REFERENTE TRABALHO NOTURNO
			 	holerite.setValorDescontoIr(9.0);//VALOR A SER DESCONTADO DE IR --ISSO VAI DEPENDER DO VALOR DO SALARIO DO EMPREGADO, SE O VALOR FOR BAIXO NAO ENTRA AQUI.
			 	holerites.add(holerite);
			 
			 	HoleriteRel relatorio = new HoleriteRel();
			 	relatorio.imprimir(holerites);
			 	relatorio.download(response,holerites);
			 						
			 	} catch (Exception e) {
			 	// TODO Auto-generated catch block
			 		e.printStackTrace();
			 	}
			 	break;
			 			
			case "Pesquisar":
				
				EmpregadoTO empregadoTO = empregado.pesquisar(codigoEmpregado);
				ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
				String dataAdmissao = formatador.format(contratoTO.getDataAdmissao());
				CalculosPagamento calculos = new CalculosPagamento();
			 	
				int totalDias = 0;
				int anoEscolhido = 0;
				int mesEscolhido = 0;
				if(request.getParameter("mes") == null)
				{
					anoEscolhido  = Calendar.getInstance().get(Calendar.YEAR);
					mesEscolhido = Calendar.getInstance().get(Calendar.MONTH) + 1;
					totalDias = calculos.diasNoMes(mesEscolhido, anoEscolhido);
				}
				else{
					anoEscolhido = Integer.parseInt(request.getParameter("ano"));
					mesEscolhido = Integer.parseInt(request.getParameter("mes"));
					totalDias = calculos.diasNoMes(mesEscolhido, anoEscolhido);
				}
				
			 	double valorHoraExtra = calculos.totalHoraExtra(codigoEmpregado,mesEscolhido, anoEscolhido,totalDias);
				double salario = contratoTO.getSalarioBase();
				double inss = calculos.totalINSS(salario);
				double fgts = calculos.totalFGTS(salario);
				double irrf = calculos.totalIRRF(salario,codigoEmpregado,inss);
				double valeTransporte = calculos.totalValeTransporte(salario, codigoEmpregado);
				double folgas = calculos.totalFolgas(codigoEmpregado);
				double salarioLiquido = calculos.totalSalario(codigoEmpregado, salario, valeTransporte, 
				inss, irrf, valorHoraExtra, folgas);
				
				request.setAttribute("anoEscolhido", anoEscolhido);
				request.setAttribute("mesEscolhido", mesEscolhido);
			 	request.setAttribute("totalDias", totalDias);
				request.setAttribute("inss", inss);
				request.setAttribute("fgts", fgts);
				request.setAttribute("irrf", irrf);
				request.setAttribute("valeTransporte", valeTransporte);
				request.setAttribute("salarioLiquido", salarioLiquido);
				request.setAttribute("valorHoraExtra", valorHoraExtra);
				request.setAttribute("listaEmpregado", empregadoTO);
				request.setAttribute("listaContrato", contratoTO);
				request.setAttribute("codigoEmpregado", codigoEmpregado);
				request.setAttribute("dataAdmissao", dataAdmissao);
				view = request.getRequestDispatcher("TelaFolhaPagamento.jsp");
				view.forward(request, response);
			break;
		}
	}

}
