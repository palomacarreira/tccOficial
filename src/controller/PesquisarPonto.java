package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import model.PontoRelatorio;
import model.PontoTO;
import relatorio.PontoRel;

/**
 * Servlet implementation class PesquisarPonto
 */
@WebServlet("/PesquisarPonto")
public class PesquisarPonto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarPonto() {
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
		EspecialistaContrato contrato = new EspecialistaContrato();
		EspecialistaPonto ponto = new EspecialistaPonto();
		EspecialistaEmpregado empregado = new EspecialistaEmpregado();
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
				int anoEscolhido = 0;
				int mesEscolhido = 0;
				int totalDias = 0;
				if(request.getParameter("mes") == null)
				{
					anoEscolhido = Calendar.getInstance().get(Calendar.YEAR);
					mesEscolhido = Calendar.getInstance().get(Calendar.MONTH) + 1;
					totalDias = diasNoMes(mesEscolhido, anoEscolhido);

					request.setAttribute("anoEscolhido", anoEscolhido);
					request.setAttribute("mesEscolhido", mesEscolhido);
				 	request.setAttribute("totalDias", totalDias);
				}
				else{
					anoEscolhido = Integer.parseInt(request.getParameter("ano"));
					mesEscolhido = Integer.parseInt(request.getParameter("mes"));
					totalDias = diasNoMes(mesEscolhido, anoEscolhido);
					
					request.setAttribute("anoEscolhido", anoEscolhido);
					request.setAttribute("mesEscolhido", mesEscolhido);
				 	request.setAttribute("totalDias", totalDias);
				}
				
				int totalFaltas = 0;
				int totalFolgas = 0;
				String totalHorasExtras = "";
				String totalHoraExtraNoturna = "";
				String totalHorasTrabalhadas = "";
				HoraExtraTO horaExtraTO = new HoraExtraTO();
				EspecialistaHorasExtras horasExtras = new EspecialistaHorasExtras();
				ArrayList<HoraExtraTO> arrayHoras = new ArrayList<HoraExtraTO>();
				ArrayList<PontoTO> listaPontos = ponto.pesquisar(codigoEmpregado);
				

				if(listaPontos != null)
				{
					 for(PontoTO pontoTO : listaPontos)
					 {	 
						String dataPonto = formatador.format(pontoTO.getDataPonto());
						int pontoDataMes = Integer.parseInt(dataPonto.substring(5,7));
						int pontoDataAno = Integer.parseInt(dataPonto.substring(0,4));
						
						if( pontoDataAno == anoEscolhido && pontoDataMes == mesEscolhido)
						{
							 horaExtraTO = horasExtras.pesquisarPorPonto(pontoTO.getCodigo());
							 arrayHoras.add(horaExtraTO);
							 totalFaltas+= somaFaltas(pontoTO.getDataPonto(), pontoTO.getAcao(), mesEscolhido, anoEscolhido);
							 totalHorasExtras = somaHorasExtras(totalHorasExtras,horaExtraTO.getTotalDeHorasExtras());
							 totalHoraExtraNoturna= somaHorasExtras(totalHoraExtraNoturna, horaExtraTO.getTotalDeHorasExtrasNoturno());
							 totalHorasTrabalhadas = somaHorasTrabalhadas(totalHorasTrabalhadas, pontoTO);
							 totalFolgas += somaFeriadosDSR(pontoTO);
						}
					 }
				}
				
				request.setAttribute("totalFolgas", totalFolgas);
				request.setAttribute("totalHorasTrabalhadas", totalHorasTrabalhadas);
				request.setAttribute("totalHorasExtras", totalHorasExtras);
				request.setAttribute("totalHoraExtraNoturna", totalHoraExtraNoturna);
				request.setAttribute("totalFaltas", totalFaltas);
				request.setAttribute("arrayHoras", arrayHoras);
				request.setAttribute("listaJornadaTrabalho", listaJornadaTrabalho);
				request.setAttribute("codigoEmpregado", codigoEmpregado);
				request.setAttribute("listaEmpregado", empregadoTO);
				request.setAttribute("listaPonto", listaDePonto);
				request.setAttribute("dataAdmissao", dataAdmissao);
				view = request.getRequestDispatcher("TelaPonto.jsp");
				view.forward(request, response);
			break;
			
			case "GerarFolhaPonto":
				
			
				System.out.println("GERANDO RELATORIO DA FOLHA DE PONTO");

				List<PontoRelatorio> pontoList = new ArrayList<PontoRelatorio>();
				
				PontoRelatorio folhaPonto = new PontoRelatorio();
				
				empregadoTO = empregado.pesquisar(codigoEmpregado);
				contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				formatador = new SimpleDateFormat("yyyy-MM-dd");
				dataAdmissao = formatador.format(contratoTO.getDataAdmissao());
				listaDePonto = ponto.pesquisar(codigoEmpregado);
				//
				folhaPonto.setDataDaFolhaDePonto("");
				folhaPonto.setMes("");
				folhaPonto.setAno("");
				folhaPonto.setIdEmpregado("");
				folhaPonto.setNomeEmpregador("");
				folhaPonto.setNomeEmpregado("");
				folhaPonto.setCargoEmpregado("");
				
				//TabelaPonto tabela = new TabelaPonto();
				
				for (int j = 0; j < listaDePonto.size(); j++) {
					
					
					folhaPonto.getDia().add(listaDePonto.get(j).getDataPonto().toString());
					folhaPonto.getEntrada().add(listaDePonto.get(j).getHoraEntrada());
					folhaPonto.getSaidaAlmoco().add(listaDePonto.get(j).getHoraSaidaAlmoco());
					folhaPonto.getRetornoAlmoco().add(listaDePonto.get(j).getHoraVoltaAlmoco());
					folhaPonto.getSaida().add(listaDePonto.get(j).getHoraSaidaAlmoco());
					folhaPonto.getHoraExtra().add(listaDePonto.get(j).getCodigo());
					folhaPonto.getHorasNoturnas().add(listaDePonto.get(j).getCodigo());
					folhaPonto.getAcao().add(listaDePonto.get(j).getAcao());
				}

				folhaPonto.setTotais("");
				folhaPonto.setDiasFaltas("");
				folhaPonto.setDiasDeDomDsrFer("");
				folhaPonto.setHorasTrabalhadas("");
				folhaPonto.setHorasExtras("");
				folhaPonto.setHorasExtrasNoturnas("");
				
				//

				pontoList.add(folhaPonto);

				PontoRel relatorio = new PontoRel();
				
				relatorio.imprimir(pontoList);
				
				relatorio.download(response,pontoList);

			break;
			
			case "CadastrarAlterar":
				
				contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
				formatador = new SimpleDateFormat("yyyy-MM-dd");
				dataAdmissao = formatador.format(contratoTO.getDataAdmissao());
				listaDePonto = ponto.pesquisar(codigoEmpregado);
				listaJornadaTrabalho = jornadaTrabalho.pesquisarJornada(contratoTO.getCodigo());
				ArrayList<PontoTO> listaPonto = ponto.pesquisar(codigoEmpregado);
	
				if(request.getParameter("mes") == null) // CASO N�O TENHA ESCOLHIDO O M�S AINDA, MOSTRA PONTO DO M�S ATUAL
				{
					int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
					int mesAtual = Calendar.getInstance().get(Calendar.MONTH) + 1;
					totalDias = diasNoMes(mesAtual, anoAtual);

					request.setAttribute("anoEscolhido", anoAtual);
					request.setAttribute("mesEscolhido", mesAtual);
				 	request.setAttribute("totalDias", totalDias);
				 	request.setAttribute("codigoEmpregado", codigoEmpregado);
					request.setAttribute("listaPonto", listaDePonto);
					request.setAttribute("dataAdmissao", dataAdmissao);
					request.setAttribute("listaPonto", listaPonto);
					request.setAttribute("listaJornadaTrabalho", listaJornadaTrabalho);
					view = request.getRequestDispatcher("TelaCadastrarPonto.jsp");
					
					if(listaDePonto != null)
					{
						for(PontoTO lista : listaDePonto)
						{
							String dataPonto = formatador.format(lista.getDataPonto());
							int diaPonto = Integer.parseInt(dataPonto.substring(8,10));
							int mesPonto = Integer.parseInt(dataPonto.substring(5,7));
							int anoPonto = Integer.parseInt(dataPonto.substring(0,4));
							if((mesPonto == mesAtual) && (anoPonto == anoAtual))
							{ 
								view = request.getRequestDispatcher("TelaAlterarPonto.jsp");
							}
						}
					}

					view.forward(request, response);
				}
				else
				{
					anoEscolhido = Integer.parseInt(request.getParameter("ano"));
					mesEscolhido = Integer.parseInt(request.getParameter("mes"));
					totalDias = diasNoMes(mesEscolhido, anoEscolhido);
					
					request.setAttribute("anoEscolhido", anoEscolhido);
					request.setAttribute("mesEscolhido", mesEscolhido);
				 	request.setAttribute("totalDias", totalDias);
				 	request.setAttribute("codigoEmpregado", codigoEmpregado);
					request.setAttribute("listaPonto", listaDePonto);
					request.setAttribute("dataAdmissao", dataAdmissao);
					request.setAttribute("listaPonto", listaPonto);
					request.setAttribute("listaJornadaTrabalho", listaJornadaTrabalho);
					view = request.getRequestDispatcher("TelaCadastrarPonto.jsp");
					
					if(listaDePonto != null)
					{
						for(PontoTO lista : listaDePonto)
						{
							String dataPonto = formatador.format(lista.getDataPonto());
							int diaPonto = Integer.parseInt(dataPonto.substring(8,10));
							int mesPonto = Integer.parseInt(dataPonto.substring(5,7));
							int anoPonto = Integer.parseInt(dataPonto.substring(0,4));
							if((mesPonto == mesEscolhido) && (anoPonto == anoEscolhido))
							{ 
								view = request.getRequestDispatcher("TelaAlterarPonto.jsp");
							}
						}
					}
					view.forward(request, response);
				}
			break;
		}
	}
	
	public int diasNoMes(int mes, int ano){
		java.util.Date date = null;
        String data = 1 +"/"+mes+"/"+ano;
        String formato = "dd/MM/yyyy";
        SimpleDateFormat df = new SimpleDateFormat(formato);
		try {
			date = df.parse(data);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
	 	int ultimoDia = c.getActualMaximum (Calendar.DAY_OF_MONTH);
	 	
	 	return ultimoDia;
	}
	
	public int somaFaltas(Date ponto, String acaoPonto, int mes, int ano)
	{
		int qtdFaltas = 0;
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		String dataPonto = formatador.format(ponto);
		int pontoDataMes = Integer.parseInt(dataPonto.substring(5,7));
		int pontoDataAno = Integer.parseInt(dataPonto.substring(0,4));

		// verificando os pontos dentro do periodo selecionado		
		if( pontoDataAno == ano && pontoDataMes == mes)
		{
			if(acaoPonto.equals("Falta"))
			{
				qtdFaltas += 1;
			}
		}
		
		return qtdFaltas;
	}
	
	public String somaHorasTrabalhadas(String horaAnterior, PontoTO ponto)
	{
		CalculosHoraExtra calculos = new CalculosHoraExtra();
		String horaSomar =calculos.calculaTotalHoras(ponto.getHoraEntrada(), ponto.getHoraSaidaAlmoco(), 
				ponto.getHoraVoltaAlmoco(), ponto.getHoraSaida());
		String horas = calculos.somaHoras(horaAnterior, horaSomar);
		
		return horas;
	}
	
	public String somaHorasExtras(String horaAnterior, String horaSomar)
	{
		CalculosHoraExtra calculos = new CalculosHoraExtra();
		String horaExtra = calculos.somaHoras(horaAnterior,horaSomar);
		return horaExtra;
	}
	
	public int somaFeriadosDSR(PontoTO pontoTO)
	{
		Date ponto = pontoTO.getDataPonto();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		String dataPonto = formatador.format(ponto);
		String dia = dataPonto.substring(8,10);
		String mes = dataPonto.substring(5,7);
		String ano = dataPonto.substring(0,4);
		CalculosHoraExtra calculos = new CalculosHoraExtra();

		int diaSemana = calculos.diaDaSemana(dia, mes, ano);
		int folga = 0;
		
		if(diaSemana == 7 || pontoTO.getAcao().equals("Feriado Trabalhado") || pontoTO.getAcao().equals("Trabalhou na DSR"))
		{
			folga = 1;
		} 
		return folga;
	}
}
