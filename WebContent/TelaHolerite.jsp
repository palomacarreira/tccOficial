<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script src="js/jquery-1.2.6.pack 2.js"></script>
<script src="js/jquery.maskedinput-1.1.4.pack 2.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#cpf").mask("999.999.999-99");
	$("#campoData").mask("99/99/9999");
	$("#cnpj").mask("99.999.999/9999-99");
	$("#rg").mask("99.999.999-*");
	$("#cep").mask("99.999-999");
	$("#numero").mask("(99) 9999-9999");

});
</script>

<title>Holerite</title>

</head>
<body>

<% String[] meses = {"Janeiro", "Fevereiro","Marco","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};%>
<% String[] ano = {"2000", "2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016"};%>
     	
<c:import url="cabecalhoLogado.jsp"/>
<div class="container">
<form name="form1" class= "form-inline" method="post" action="PesquisarHolerite">
<legend>Relatorio de Holerite</legend>
<nav class="navbar navbar-default" role="navigation">

<div class="container">

	 </br>
		  <div  class= "form-group" > 
		    <label  for= "nome" > Nome </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="nome" id="nome" size="50"> 
		    </div>
		  </div> 

		<div  class= "form-group" > 
		    <label  for= "cpf" > CPF </label> 
		    <div class="span3">
		    <input type="text" id="cpf" name="cpf" class= "form-control" size="28"> 
		    </div>
		 </div> 
		 
		 <div class="col-sm-6 pull-left">
     		 <label  for = "select" >Mes</label> 
     		 <div class="span3">
       		  
								<%
		out.println("<select class=\"form-control\" name=\"meses\" id=\"meses\">");
		
     	for (int i = 0; i < meses.length; i++) {
 
     			out.println("<option value="+ meses[i] +" style =\"width: 100px; height: 100px\">"+ meses[i] +"</option>");	
     	}
		out.println("</select>");
    	%>
      	</div>
    	</div> 
    	
		 <div class="col-sm-6 pull-right">
     		 <label  for = "select" >Ano</label> 
     		 <div class="span3">
 
       		  <%
		out.println("<select class=\"form-control\" name=\"ufRg\" id=\"comboUf_Rg\">");
		
     	for (int i = 0; i < ano.length; i++) {
     		
     			out.println("<option value="+ ano[i] +" style =\"width: 100px; height: 100px\">"+ ano[i] +"</option>");	
     		
     	}
		out.println("</select>");
    	%>
    	
      		</div>
    	</div> 
    	</br>
    	<div class="col-sm-8 pull-right">
    	</br>
    		<button  type= "submit" name="acao" value="GerarHolerite" class= "btn btn-primário " > Gerar Holerite </button> 
		
		 </div>
		
	   </div>   

  </br>
 </div>
 </br>
    	</nav>
    	<div class="container">
    	
    		<nav class="navbar navbar-default" role="navigation">
    	
    	<!--Colocar aqui itens da pesquisa holerite  -->
     </br>
     
    </nav>
     </div>
  </form>

</div>
<c:import url="rodape.jsp"/>

</body>
</html>