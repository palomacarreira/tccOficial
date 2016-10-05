package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CalculosHoraExtra;
import model.ContratoTO;
import model.EmpregadoTO;
import model.EspecialistaContrato;
import model.EspecialistaEmpregado;
import model.EspecialistaHorasExtras;
import model.EspecialistaJornadaTrabalho;
import model.EspecialistaPonto;
import model.HoraExtraTO;
import model.JornadaTrabalhoTO;
import model.PontoTO;

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
		EspecialistaPonto ponto = new EspecialistaPonto();
		EspecialistaHorasExtras horasExtras = new EspecialistaHorasExtras();
		EspecialistaJornadaTrabalho jornadaTrabalho = new EspecialistaJornadaTrabalho();
		RequestDispatcher view = null;
		String codigoEmpregado = (String) request.getParameter("codigoEmpregado");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		
		switch (acao) 
		{
			case "Pesquisar":
				
				EmpregadoTO empregadoTO = empregado.pesquisar(codigoEmpregado);
				ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
				String dataAdmissao = formatador.format(contratoTO.getDataAdmissao());
				ArrayList<PontoTO> listaDePonto = ponto.pesquisar(codigoEmpregado);
				ArrayList<JornadaTrabalhoTO> listaJornadaTrabalho = jornadaTrabalho.pesquisarJornada(contratoTO.getCodigo());
				int totalDias = 0;
				if(request.getParameter("mes") == null)
				{
					int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
					int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
					totalDias = diasNoMes(mesAtual, anoAtual);
				 
					request.setAttribute("anoEscolhido", anoAtual);
					request.setAttribute("mesEscolhido", mesAtual);
				 	request.setAttribute("totalDias", totalDias);
				}
				else{
					int anoEscolhido = Integer.parseInt(request.getParameter("ano"));
					int mesEscolhido = Integer.parseInt(request.getParameter("mes"));
					totalDias = diasNoMes(mesEscolhido, anoEscolhido);
				
					request.setAttribute("anoEscolhido", anoEscolhido);
					request.setAttribute("mesEscolhido", mesEscolhido);
				 	request.setAttribute("totalDias", totalDias);
				}
				
							
				// CALCULAR TOTAL HORA EXTRA
				 CalculosHoraExtra calculos = new CalculosHoraExtra();
				 String qtdHorasSemanais = contratoTO.getDuracaoSemanal();
				 double salario = contratoTO.getSalarioBase();
				 
				 String qtdHorasMensais = calculos.qtdHorasMensais(totalDias, qtdHorasSemanais);
				 
				 HoraExtraTO horaExtraTO = new HoraExtraTO();
				 double valorHoraExtra = 0.0;
				 String acaoSelecionada = "";
				 ArrayList<PontoTO> listaPontos = ponto.pesquisar(codigoEmpregado);
				 for(PontoTO pontoTO : listaPontos)
				 {	 
					 horaExtraTO = horasExtras.pesquisar(pontoTO.getCodigo());
					 String totalHorasExtras = horaExtraTO.getTotalDeHorasExtras();
					 String totalHorasExtrasNoturno =  horaExtraTO.getTotalDeHorasExtrasNoturno();
					 int diaDaSemana = pontoTO.getDataPonto().getDay();
					 acaoSelecionada = pontoTO.getAcao();
					 
					 valorHoraExtra += calculos.calcularValorHoras(salario, qtdHorasMensais, totalHorasExtras, totalHorasExtrasNoturno, diaDaSemana, acaoSelecionada);						
				 }
				
				// CALCULAR INSS EMPREGADO
				double inss = 0.0;
				if(salario <= 1556.94){ inss = salario * 0.08;}
				else if(salario >= 1556.95 && salario <= 2594.92){ inss = salario * 0.09;}
				else if(salario >= 2594.93 && salario <= 5189.82){ inss = salario * 0.11;}
				else if(salario > 5189.82){inss = 570.88;} // teto de contribuição inss 2016
				
				// CALCULAR FGTS
				
				double fgts = salario * 0.08;
				
				// CALCULAR IRRF
				int numeroDependentes = empregadoTO.getQtdDependentes();
				double valorPorDependente = numeroDependentes * 189.59;
				
				double valorBaseIR = (salario - inss);
				valorBaseIR = (valorBaseIR - valorPorDependente);
				double parcelaDeduzir = 0.0;
				double irrf = 0.0;
				
				if(valorBaseIR <= 1903.98){ 
					irrf = valorBaseIR;
					parcelaDeduzir = 0.0;
				}
				else if(valorBaseIR >= 1903.99 && valorBaseIR <= 2826.65){ 
					irrf = valorBaseIR * 0.075;
					parcelaDeduzir = 142.80;
				}
				else if(valorBaseIR >= 2826.66 && valorBaseIR <= 3715.05){ 
					irrf = valorBaseIR * 0.15;
					parcelaDeduzir = 354.80;
				}
				else if(valorBaseIR >= 3751.06 && valorBaseIR <= 4664.68){ 
					irrf = valorBaseIR * 0.225;
					parcelaDeduzir = 636.13;
				}
				else if(valorBaseIR > 4664.68){ 
					irrf = valorBaseIR * 0.275;
					parcelaDeduzir = 869.36;
				}

				irrf = irrf - parcelaDeduzir;
				
				 
				// CALCULAR DESCONTO VALE TRANSPORTE
					
				double valeTransporte = contratoTO.getValeTransporte();
				double parteSalario = salario * 0.06; // 6% DO SALÁRIO
				if(valeTransporte > parteSalario)
				{
					valeTransporte = parteSalario;
				}
					
				// CALCULAR SALÁRIO // SE TIVER HORAS NEGATIVAS DESCONTA
				double salarioLiquido = 0;
				double descontos = contratoTO.getDescontoBeneficios() + valeTransporte + inss + irrf;
				
				if(contratoTO.getCompensacaoDias().equals("salario")){ // se for regime salarial
					salarioLiquido = (salario - descontos) + valorHoraExtra;
				}else{ // se for regime de horas, só entra valorHoraExtra se for para descontar horas devendo
					if(valorHoraExtra < 0){
						salarioLiquido = (salario - descontos) + valorHoraExtra;
					}
				}
				
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
		
		public int diasNoMes(int mes, int ano){
		 	return 32 - new Date(ano, mes, 32).getDate();
		}
}
