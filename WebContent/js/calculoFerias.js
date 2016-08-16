function getQuantidadeDiasByMes(mes){
	Calendar datas = new GregorianCalendar();
    datas.set(Calendar.MONTH, mes-1);//2
    int quantidadeDias = datas.getActualMaximum (Calendar.DAY_OF_MONTH);  
    return quantidadeDias ;
        
     // SALÁRIO FÉRIAS
     // System.out.println(getQuantidadeDiasByMes(1));
     // VERIFICA O MÊS DAS FÉRIAS ESCOLHIDO TEM QUANTOS DIAS 
}

function calculaSalarioFerias(){
	
}