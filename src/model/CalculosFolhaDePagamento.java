package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalculosFolhaDePagamento {
	
	EspecialistaContrato contrato = new EspecialistaContrato();
	EspecialistaPonto ponto = new EspecialistaPonto();
	EspecialistaHorasExtras horasExtras = new EspecialistaHorasExtras();
	EspecialistaJornadaTrabalho jornadaTrabalho = new EspecialistaJornadaTrabalho();
	EspecialistaEmpregado empregado = new EspecialistaEmpregado();

	public double totalHoraExtra(String codigoEmpregado,int mesEscolhido, int anoEscolhido, int totalDias)
	{
		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<PontoTO> listaDePonto = ponto.pesquisar(codigoEmpregado);
			
		// CALCULAR TOTAL HORA EXTRA
		 CalculosHoraExtra calculos = new CalculosHoraExtra();
		 String qtdHorasSemanais = contratoTO.getDuracaoSemanal();
		 double salario = contratoTO.getSalarioBase();
		 
		 String qtdHorasMensais = calculos.qtdHorasMensais(totalDias, qtdHorasSemanais);
		 
		 HoraExtraTO horaExtraTO = new HoraExtraTO();
		 double valorHoraExtra = 0.0;
		 String acaoSelecionada = "";
		 ArrayList<PontoTO> listaPontos = ponto.pesquisar(codigoEmpregado);
		 if(listaDePonto != null)
		 {
			 for(PontoTO pontoTO : listaPontos)
			 {	 
				String dataPonto = formatador.format(pontoTO.getDataPonto());
				int diaPonto = Integer.parseInt(dataPonto.substring(8,10));
				int mesPonto = Integer.parseInt(dataPonto.substring(5,7));
				int anoPonto = Integer.parseInt(dataPonto.substring(0,4));
				if((mesPonto == mesEscolhido) && (anoPonto == anoEscolhido))
				{
					 horaExtraTO = horasExtras.pesquisar(pontoTO.getCodigo());
					 String totalHorasExtras = horaExtraTO.getTotalDeHorasExtras();
					 String totalHorasExtrasNoturno =  horaExtraTO.getTotalDeHorasExtrasNoturno();
					 int diaDaSemana = diaPonto;
					 acaoSelecionada = pontoTO.getAcao();
					 valorHoraExtra += calculos.calcularValorHoras(salario, qtdHorasMensais, totalHorasExtras, totalHorasExtrasNoturno, diaDaSemana, acaoSelecionada);						
				}
			 }
		 }
		 return valorHoraExtra;
	}
	
	public double totalINSS(double salario){
		// CALCULAR INSS EMPREGADO
		double inss = 0.0;
		if(salario <= 1556.94){ inss = salario * 0.08;}
		else if(salario >= 1556.95 && salario <= 2594.92){ inss = salario * 0.09;}
		else if(salario >= 2594.93 && salario <= 5189.82){ inss = salario * 0.11;}
		else if(salario > 5189.82){inss = 570.88;} // teto de contribuição inss 2016
		
		return inss;
	}
	
	public double totalFGTS(double salario){
		// CALCULAR FGTS
		double fgts = salario * 0.08;
		return fgts;
	}
	
	public double totalIRRF(double salario, String codigoEmpregado, double inss){
		// CALCULAR IRRF
		EmpregadoTO empregadoTO = empregado.pesquisar(codigoEmpregado);
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
		
		return irrf;
	}
	
	public double totalValeTransporte(double salario, String codigoEmpregado)
	{
		// CALCULAR DESCONTO VALE TRANSPORTE
		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
		double valeTransporte = contratoTO.getValeTransporte();
		double parteSalario = salario * 0.06; // 6% DO SALÁRIO
		if(valeTransporte > parteSalario)
		{
			valeTransporte = parteSalario;
		}
		
		return valeTransporte;
	}
	
	public double totalSalario(String codigoEmpregado, double salario, double valeTransporte, 
			double inss, double irrf, double valorHoraExtra)
	{
		// CALCULAR SALÁRIO // SE TIVER HORAS NEGATIVAS DESCONTA
		ContratoTO contratoTO = contrato.pesquisarEmpregado(codigoEmpregado);
		double salarioLiquido = 0;
		double descontos = contratoTO.getDescontoBeneficios() + valeTransporte + inss + irrf;
		
		if(contratoTO.getCompensacaoDias().equals("salario")){ // se for regime salarial
			salarioLiquido = (salario - descontos) + valorHoraExtra;
		}else{ // se for regime de horas, só entra valorHoraExtra se for para descontar horas devendo
			if(valorHoraExtra < 0){
				salarioLiquido = (salario - descontos) + valorHoraExtra;
			}
		}
		
		return salarioLiquido;
	}
	
	public int diasNoMes(int mesEscolhido, int anoEscolhido){
		
	 	java.util.Date date = null;
        String data = 1 +"/"+mesEscolhido+"/"+anoEscolhido;
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
}
