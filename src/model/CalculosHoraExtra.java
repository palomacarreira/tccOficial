package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class CalculosHoraExtra 
{

	public String qtdHorasMensais(int ultimoDiaDoMes, String qtdHorasSemanais)
	{
		int qtdSemanasMes = obterQuantidadeDeSemanasMesAtual(ultimoDiaDoMes);
		int horasSemanais = Integer.parseInt(qtdHorasSemanais.substring(0,2));
		int minutosSemanais = Integer.parseInt(qtdHorasSemanais.substring(3, 5));
		
		horasSemanais = horasSemanais * 60 + minutosSemanais; 
		int qtdHorasMensais = horasSemanais * qtdSemanasMes;
		int hora = (int) Math.floor(qtdHorasMensais / 60);
		int minuto = hora % 60;
		
		return completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
	}
	
	public int diasNoMes(String mes, String ano){
		
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
	
	// SABER QUAL É O DIA DA SEMANA - SEG, TERÇ, ..
		public int diaDaSemana(String dia, String mes, String ano){
		
	        java.util.Date date = null;
	        String data = dia +"/"+mes+"/"+ano;
            String formato = "dd/MM/yyyy";
	        SimpleDateFormat df = new SimpleDateFormat(formato);
			try {
				date = df.parse(data);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int diaDaSemana = c.get(Calendar.DAY_OF_WEEK);
			int diaSemana = 0;
			
			switch (diaDaSemana)  
		    {  
			    case 1:  
			    {  
			      diaSemana = 0;// "Domingo"  
			      break;  
			    }  
			    case 2:  
			    {  
			      diaSemana = 1;// "Segunda";  
			      break;  
			    }  
			    case 3:  
			    {  
			      diaSemana = 2;//"Terça";  
			      break;  
			    }  
			    case 4:  
			    {  
			      diaSemana = 3;//"Quarta";  
			      break;  
			    }  
			    case 5:  
			    {  
			      diaSemana = 4;//"Quinta";  
			      break;  
			    }  
			    case 6:  
			    {  
			      diaSemana = 5;//"Sexta";  
			      break;  
			    }  
			    case 7:  
			    {  
			      diaSemana = 6;//"Sábado";  
			      break;  
			    }  
		    }  
			
			return diaSemana;   
		}

		public int obterQuantidadeDeSemanasMesAtual(int ultimoDiaDoMes) 
		{
			Calendar dataBase = Calendar.getInstance();
			dataBase.set(Calendar.DAY_OF_MONTH, ultimoDiaDoMes);
			return dataBase.get(Calendar.WEEK_OF_MONTH);
		}
		
	
		
	public String somaHorasExtras(String totalDeHorasExtras, String totalDeHorasExtrasNoturno){

		int horaExtra = 0;
		int minutoExtra = 0;
		int horaNoturna = 0;
		int minutoNoturna = 0;
		int horas = 0;
		int minuto = 0;
		
		String soma = "";

		if(! totalDeHorasExtras.equals("") || ! totalDeHorasExtras.equals(""))
		{
			horaExtra = Integer.parseInt(totalDeHorasExtras.substring(0, 2));
			horaNoturna = Integer.parseInt(totalDeHorasExtrasNoturno.substring(0, 2));
			minutoExtra = Integer.parseInt(totalDeHorasExtras.substring(3, 5));
			minutoNoturna = Integer.parseInt(totalDeHorasExtrasNoturno.substring(3, 5));
			
			horaExtra = horaExtra * 60 + minutoExtra; 
			horaNoturna = horaNoturna * 60 + minutoNoturna; 
			int horaSoma = (horaExtra + horaNoturna);
			
			int hora = (int) Math.floor(horaSoma / 60);
			minuto = horaSoma % 60;
			
			soma = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
		}
		else if(totalDeHorasExtras.equals("")){
			soma = totalDeHorasExtrasNoturno;
		}
		else{
			soma = totalDeHorasExtras;
		}
		
		return soma;
	}
	
	public double calcularValorHoras(double salario, String qtdHorasMensais, String horasExtras, String horasExtrasNoturno, int diaDaSemana, String acaoSelecionada)
	{
		
		int totalDehorasExtras = Integer.parseInt(horasExtras.substring(0, 2));
		int TotalDehorasExtrasNoturno = Integer.parseInt(horasExtrasNoturno.substring(0, 2));
		
		//A remuneração do trabalho noturno deve ter acréscimo de, no mínimo, 20% 
		//(vinte por cento) sobre o valor da hora diurna. 
		//valorHoraDiurna = salário base / horas mensais trabalhadas;
		//As horas extras em domingos e feriados são calculadas com 100% de acréscimo.
		//Horas extras realizadas entre segunda e sábado devem ter acréscimo de no mínimo 50% do salário. 
		// Transforma-se a hora extra noturna: 1h / 52,5 x 60 = 1,1428 horas; já que a hora noturna equivale a 52min e 30 seg.
		
		
		int horas = Integer.parseInt(qtdHorasMensais.substring(0,2));
		double valorHoraDiurna = salario / horas;
		double valorHoraNoturna = valorHoraDiurna + (valorHoraDiurna * 0.2);

		double totalHorasExtras = 0.0;
		double totalHorasExtrasNoturnas = 0.0;
		double valorHorasExtras = 0.0;
		
		int hE = 0;
		int hEN = Integer.parseInt(horasExtrasNoturno.substring(0,2));
		if(horasExtras.substring(0,1).equals("-")) // HORAS EXTRAS NEGATIVAS = DEVENDO HORAS
		{
			if(acaoSelecionada.equals("Atraso Justificado")){
				totalHorasExtras= 0;
			}
			else
			{
				hE = Integer.parseInt(horasExtras.substring(1,3));
				int minutos = Integer.parseInt(horasExtras.substring(4,6));
				//Se o empregado se atrasar,  o empregador está apto a deduzir além dos minutos de atraso, 
				//um dia por semana relativo ao DSR.
				//Se o atraso for inferior a cinco minutos diários, nenhuma implicação terá pois está
				//dentro do período de tolerância previsto expressamente na legislação laboral.

				if(minutos == 5){
					hE = Integer.parseInt(horasExtras.substring(0,2));
					if(diaDaSemana == 7 || acaoSelecionada.equals("Feriado Trabalhado") || acaoSelecionada.equals("Trabalhou na DSR"))		
					{
						totalHorasExtras = valorHoraDiurna  *  hE * 2;
					}
					else
					{
						totalHorasExtras = valorHoraDiurna *  hE * 1.5;
					}
				}
				else{
					totalHorasExtras = -(valorHoraDiurna  *  hE);
				}
			}
		}
		else
		{
			hE = Integer.parseInt(horasExtras.substring(0,2));
			if(diaDaSemana == 7 || acaoSelecionada.equals("Feriado Trabalhado") || acaoSelecionada.equals("Trabalhou na DSR"))		
			{
				totalHorasExtras = valorHoraDiurna  *  hE * 2;
			}
			else
			{
				totalHorasExtras = valorHoraDiurna *  hE * 1.5;
			}
		}
		
		
		totalHorasExtrasNoturnas = (valorHoraNoturna + (valorHoraNoturna * 0.5)) * (hEN  *  1.1428);
		valorHorasExtras = totalHorasExtras + totalHorasExtrasNoturnas;
		return valorHorasExtras;
	}


	public String calculaHorasExtras(String horaEntrada, String horaSaidaAlmoco, String horaVoltaAlmoco, 
	String horaSaida, String cadastroEntrada, String cadastroSaidaAlmoco, String cadastroVoltaAlmoco,
			String cadastroSaida)
	{
		int hora = 0;
		int minuto = 0;
		int tempoTotal = 0;
		String horaFinal = "00:00";
		
		// PEGA O VALOR CADASTRADO DO PONTO
		String horasPonto = horarios(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida); // chama método
		
		if(! horasPonto.equals("00:00"))
		{
			// PEGA O VALOR CADASTRADO NO CONTRADO (DA JORNADA DE TRABALHO)
			String horasCadastradas = horarios(cadastroEntrada, cadastroSaidaAlmoco, cadastroVoltaAlmoco, cadastroSaida); // chama método
		
			// VERIFICA SE AS HORAS DA COLUNA PASSARAM DAS HORAS REFERENTES AO DIA DA SEMANA NA JORNADA DE TRABALHO
			// EX: SEGUNDA TRABALHA 4 HORAS NO CONTRATO , ENTÃO VERIFICA SE O TOTAL CADASTRADO NO PONTO ULTRPASSA ESSE VALOR
			int horaCAD = Integer.parseInt(horasCadastradas.substring(0, 2));
			int horaCAL = Integer.parseInt(horasPonto.substring(0, 2));
			int minutoCAD =  Integer.parseInt(horasCadastradas.substring(3, 5));
			int minutoCAL = Integer.parseInt(horasPonto.substring(3, 5));
		
			int hCadastradas = horaCAD * 60 + minutoCAD; 
			int hPonto = horaCAL * 60 + minutoCAL; 
		
			// E TIVER HORA NOTURNA 
			String valorNoturno = calculaHorasExtrasNoturno(horaEntrada, horaSaida);
			if(!valorNoturno.equals("00:00"))
			{
				int hE = Integer.parseInt(valorNoturno.substring(0, 2));
				int mE = Integer.parseInt(valorNoturno.substring(3, 5));
				double vlhora = (hE / 1.1428) * 60 + mE; 
				int h = (int) Math.floor(vlhora / 60);
				int m = (int)vlhora % 60;
				horaFinal = completaZeroEsquerda(h) + ":" + completaZeroEsquerda(m);
				
				hE = Integer.parseInt(valorNoturno.substring(0, 2));
				mE = Integer.parseInt(valorNoturno.substring(3, 5));
				int hr = hE * 60 + mE; 
				hPonto = hPonto - hr;
			}
			
			// SE NÃO CUMPRIR COM AS HORAS DO DIA
			if (hPonto < hCadastradas)
			{
				int vlhora = (hCadastradas - hPonto);
				hora = (int) Math.floor(vlhora / 60);
				minuto = vlhora % 60;
				horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
				horaFinal = "-"+ horaFinal;
			}
			else if (hPonto == hCadastradas)// SE NÃO ULTRAPASSAR VOLTA 00:00
			{
				horaFinal = "00:00";
			}
			else
			{// SE ULTRAPASSAR
	
				int vlhora = (hPonto - hCadastradas);
				hora = (int) Math.floor(vlhora / 60);
				minuto = vlhora % 60;
				horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
			}
		}
		return horaFinal;
	}

	public String calculaTotalHoras(String horaEntrada, String horaSaidaAlmoco, String horaVoltaAlmoco, 
			String horaSaida)
	{
		int hora = 0;
		int minuto = 0;
		int tempoTotal = 0;
		String horaFinal = "00:00";
		
		horaFinal = horarios(horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida); // chama método
		return horaFinal;
	}

	public String calculaHorasExtrasNoturno(String horaEntrada, String horaSaida)
	{
		String horaFinal = "00:00";
		if(possuiValor(horaEntrada) && possuiValor(horaSaida))
		{
			int horainicio = Integer.parseInt(horaEntrada.substring(0, 2));
			int horafinal = Integer.parseInt(horaSaida.substring(0, 2));
			int minutoinicio = Integer.parseInt(horaEntrada.substring(3, 5));
			int minutofinal = Integer.parseInt(horaSaida.substring(3, 5));
			
			if(horafinal <= horainicio)
			{ 
				if(horafinal == horainicio)
				{
					horainicio = horainicio * 60 + minutoinicio; 
					horafinal = horafinal * 60 + minutofinal; 
					int vlhora = (horafinal - horainicio);
					int hora = (int) Math.floor(vlhora / 60);
					int minuto = vlhora % 60;
					
					horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
				}
				if(horafinal > 5 && horainicio <= 22){
					
					horaFinal = "07:00";
				}
				else if(horafinal > 5 && (horainicio >= 22 && horainicio < 24))
				{
					horainicio = 24 - horainicio;
					horainicio = horainicio * 60 + minutoinicio; 
					horafinal = horafinal * 60 + minutofinal; 
					int vlhora = (horafinal - horainicio);
					int hora = (int) Math.floor(vlhora / 60);
					int minuto = vlhora % 60;
					
					horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
				}
				else if(horafinal <= 5 && horainicio >= 22)
				{
					horainicio = 24 - horainicio;
					horainicio = horainicio * 60 + minutoinicio; 
					horafinal = horafinal * 60 + minutofinal; 
					int vlhora = (horafinal + horainicio);
					int hora = (int) Math.floor(vlhora / 60);
					int minuto = vlhora % 60;
					
					horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
				}
				else if(horafinal <= 5 && horainicio <= 22)
				{
					horafinal = horafinal + 2; //soma 2 do horário das 22 às 00:00
					horafinal = horafinal * 60 + minutofinal; 
					int hora = (int) Math.floor(horafinal / 60);
					int minuto = horafinal % 60;
					horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
				}
				
			}
			else if(horainicio >= 22 && horafinal <= 5){// TRABALHOU DENTRO DO PERÍODO NOTURNO 22:00 ÀS 5:00
				if(horafinal >= 00)
				{
					horafinal = horafinal + 24;
				}
				
				horainicio = horainicio * 60 + minutoinicio; 
				horafinal = horafinal * 60 + minutofinal; 
				int vlhora = (horafinal - horainicio);
				int hora = (int) Math.floor(vlhora / 60);
				int minuto = vlhora % 60;
				
				horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
			}
			else if(horainicio >= 22 && horafinal > 5 && horafinal < 22){ // TRABALHOU ALÉM DA HORA NOTURNA
				horaFinal = "07:00";
			}
			else if(horafinal >= 00 && horafinal <= 5)
			{
				
				horafinal = horafinal + 2 ; // soma + 2 horas (horário das 22:00 às 00:00)
				horainicio = horainicio * 60 + minutoinicio; 
				horafinal = horafinal * 60 + minutofinal; 
				int vlhora = (horainicio - horafinal);
				int hora = (int) Math.floor(vlhora / 60);
				int minuto = vlhora % 60;
				
				horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
			}
			else if(horafinal >= 22 && horainicio <= 23)
			{
				if(horainicio < 22)
				{
					horainicio = 22 * 60; 
					horafinal = horafinal * 60 + minutofinal; 
					int vlhora = (horafinal - horainicio);
					int hora = (int) Math.floor(vlhora / 60);
					int minuto = vlhora % 60;
					horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
				}
				else {
					horainicio = horainicio * 60 + minutoinicio; 
					horafinal = horafinal * 60 + minutofinal; 
					int vlhora = (horafinal - horainicio);
					int hora = (int) Math.floor(vlhora / 60);
					int minuto = vlhora % 60;
					horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
				}
			}
		}	
		
		//É feito levando-se em conta que a hora dura apenas 52 minutos e 30 segundos.
		//A hora trabalhada no período noturno deve ser convertida tomando 1h dividida por 7 (sete) 
		//e multiplicada por (oito) o que equivale a 1,14 hora noturna trabalhada. 
		// 1h / 52,5 x 60 = 1,1428 horas
		if(!horaFinal.equals("00:00"))
		{
			int horainicio = Integer.parseInt(horaFinal.substring(0, 2));
			int minutoinicio = Integer.parseInt(horaFinal.substring(3, 5));
			double vlhora =  (horainicio *  1.1428) * 60 + minutoinicio; 
			int hora = (int) Math.floor(vlhora / 60);
			int minuto = (int)vlhora % 60;
			
			horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
		}
		
		return horaFinal;
	}


	//RETORNA O CALCULO DE UMA COLUNA DE HORARIOS
	public String horarios(String horaEntrada, String horaSaidaAlmoco, String horaVoltaAlmoco, String horaSaida)
	{
		String horaFinal = "00:00";
		if(possuiValor(horaSaidaAlmoco) && possuiValor(horaVoltaAlmoco))
		{
			int horainicio = Integer.parseInt(horaEntrada.substring(0, 2));
			int horapausa = Integer.parseInt(horaSaidaAlmoco.substring(0, 2));
			int horacontinuacao = Integer.parseInt(horaVoltaAlmoco.substring(0, 2));
			int horafinal = Integer.parseInt(horaSaida.substring(0, 2));
			
			int minutoinicio = Integer.parseInt(horaEntrada.substring(3, 5));
			int minutopausa = Integer.parseInt(horaSaidaAlmoco.substring(3, 5));
			int minutocontinuacao = Integer.parseInt(horaVoltaAlmoco.substring(3, 5));
			int minutofinal = Integer.parseInt(horaSaida.substring(3, 5));
		
		
			// HORA NOTURNA 
			if(horainicio >= horafinal && horafinal >= 0)
			{
				horafinal = horafinal + 24;
			}
			
			horainicio = horainicio * 60 + minutoinicio; 
			horapausa = horapausa * 60 + minutopausa; 
			horacontinuacao = horacontinuacao * 60 + minutocontinuacao; 
			horafinal = horafinal * 60 + minutofinal; 
			
			int vlhora = (horapausa - horainicio) + (horafinal - horacontinuacao);
			int hora = (int) Math.floor(vlhora / 60);
			int minuto = vlhora % 60;
			horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
		}
		else if(possuiValor(horaEntrada) && possuiValor(horaSaida))
		{
			int horainicio = Integer.parseInt(horaEntrada.substring(0, 2));
			int horafinal = Integer.parseInt(horaSaida.substring(0, 2));
			int minutoinicio = Integer.parseInt(horaEntrada.substring(3, 5));
			int minutofinal = Integer.parseInt(horaSaida.substring(3, 5));
			
			
			// HORA NOTURNA 
			if(horainicio > horafinal && horafinal >= 0)
			{
				horafinal = horafinal + 24;
			}
			
			horainicio = horainicio * 60 + minutoinicio; 
			horafinal = horafinal * 60 + minutofinal; 
			int vlhora = (horafinal - horainicio);
			int hora = (int) Math.floor(vlhora / 60);
			int minuto = vlhora % 60;
			
			horaFinal = completaZeroEsquerda(hora) + ":" + completaZeroEsquerda(minuto);
		}
		
		return horaFinal;
	}

	//Verifica se um campo está vazio
	public boolean possuiValor( String valor )
	{
		return valor != "undefined" && (!valor.equals(""));
	}

	//Completa um número menor que dez com um zero à esquerda.
	//Usado aqui para formatar as horas... Exemplo: 3:10 -> 03:10 , 10:5 -> 10:05
	public String completaZeroEsquerda( int numero )
	{
		String num = Integer.toString(numero);
		return ( numero < 10 ? "0" + num : num);
	}
}
