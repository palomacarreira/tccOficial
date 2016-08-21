package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DataUtil {

	private Calendar calendar;
	private SimpleDateFormat df;
	private Date date;
	
	public int retornaDiferencaEmDias(Calendar dataAnterior, Calendar dataPosterior){
		long diferenca = dataPosterior.getTimeInMillis() - dataAnterior.getTimeInMillis();
		int tempoDia = 1000 * 60 * 60 * 24;
		int diasDiferenca = (int)diferenca / tempoDia;
		
		return diasDiferenca;
	}
	
	public int retornaDiferencaEmDias2(Date dataAnterior, Date dataPosterior){//SITE QUE FAZ O CALCULO: http://www.calendario-365.com.br/calcular/01-08-2016_30-08-2016.html 
        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
        df.setLenient(false);
     
        long dt = (dataAnterior.getTime() - dataPosterior.getTime()); //+ 3600000; // 1 hora para compensar horário de verão
      
        int diasDiferenca = (int) (dt / 86400000L)*-1; 
        
		return diasDiferenca;
	}
	
	public String retornaDataFormatada(Calendar calendar){
		df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(calendar.getTime());
	}
	
	public String retornaDataFormatadaTexto_en(Calendar calendar){
		df = new SimpleDateFormat("dd/MMM/yyyy", new Locale("en_US"));
		return df.format(calendar.getTime());
	}
	
	public String retornaDataFormatadaJavascript(Calendar calendar){
		df = new SimpleDateFormat("d/M/yyyy");
		return df.format(calendar.getTime());
	}
	
	public String retornaDataFormatadaMesAno(Calendar calendar){
		df = new SimpleDateFormat("MM/yyyy");
		return df.format(calendar.getTime());
	}
	
	public String retornaDataFormatadaAno(Calendar calendar){
		df = new SimpleDateFormat("yyyy");
		return df.format(calendar.getTime());
	}
	
	public String retornaDataFormatadaMesAnoTextoAbrev(Calendar calendar){
		df = new SimpleDateFormat("MMM/yyyy");
		return df.format(calendar.getTime());
	}
	
	public String retornaDataFormatadaMesAnoTexto(Calendar calendar){
		df = new SimpleDateFormat("MMMM/yyyy");
		return df.format(calendar.getTime());
	}
	
	public String retornaDataFormatadaCompleta(Calendar calendar){
		df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(calendar.getTime());
	}
	
	public String retornaHoraFormatada(Calendar calendar){
		df = new SimpleDateFormat("HH:mm");
		return df.format(calendar.getTime());
	}
	
	public String retornaDataHoraFormatada(Calendar calendar){
		df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return df.format(calendar.getTime());
	}
	
	public String retornaDataHoraFormatadaMesTexto_en(Calendar calendar){
		df = new SimpleDateFormat("dd/MMM/yyyy HH:mm", new Locale("en_US"));
		return df.format(calendar.getTime());
	}
	
	public String retornaDataFormatadaCompleta(Date date){
		df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(date);
	}
	
	public String retornaDataFormatadaNameFile(Date date){
		df = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		return df.format(date);
	}
	
	public String retornaDataFormatadaNameFileMesTexto_en(Date date){
		df = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss", new Locale("en_US"));
		return df.format(date);
	}
	
	public Calendar parseStringToCalendar(String data){
		calendar = new GregorianCalendar();
		df = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			calendar.setTime(df.parse(data));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}
	
	public Calendar parseStringToCalendarHour(String data){
		calendar = new GregorianCalendar();
		df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try {
			calendar.setTime(df.parse(data));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}
	
	public String retornaDataFormatadaCompletaMySQL(Date date){
		df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(date);
	}
	
	public Calendar dataAtual() {
		calendar = new GregorianCalendar();
		date = new Date();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Calendar subtrairDataAtual(Calendar data, int dias) {
		data.add(Calendar.DATE, -dias);
		return data;
	}

}
