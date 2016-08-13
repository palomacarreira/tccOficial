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
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/sweetalert.min.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	$("#periodoAquisitivo").mask("9999");
});

function informacaoValor(){
	
	swal({   title: "Cadastro Empregado",   
		text:
"<p>o valor de pagamento das férias é equivalente a um terço do valor do salário regular do empregado e é somado ao salário do mês de competência.</p><br/>"+
"<p>O pagamento ao empregado deve ser efetuado até dois dias antes do início do período do afastamento. </p>",
			html: true 
		});
}
</script>
<title>Cadastro Férias</title>

</head>
<body>

<c:import url="cabecalhoLogado.jsp"/>

	<div class="container">
	<form name="form1" class= "form-inline" method="post" action="CadastrarFerias">
	<nav class="navbar navbar-default" role="navigation">
	
	<div class="container">
	<label  for= "meuPerfil" > Registro de Férias </label><br><br>
		  
		  <div  class= "form-group" > 
		    <label  for= "nome" > Período Aquisitivo(Ano) </label> 
		    <div class="span3">
		    <input type= "text"  class= "form-control"  name="periodoAquisitivo" id="periodoAquisitivo" size="4"> 
		    </div>
		  </div> 
		  
		   <div  class= "form-group" > 
		    <label  for= "qtdDiasFerias" > Quantidade de dias de férias disponíveis </label> 
		    <div class="span3">
		    	<input  type= "text" id="qtdDiasFerias" name="qtdDiasFerias" class="form-control" size="2" disabled> 
		    </div>
		 </div> 

		<div class="radio form-group" > 
	 	<label for="vendaFerias" > Abono Pecuniário (Venda de 1/3 das Férias)</label> 
  		<div class="span3">
   				 <input  type= "radio"  name="vendaFerias"  id="sim"  value="true" checked>
   			 Sim
  				<input  type= "radio"  name="vendaFerias"  id="não"  value="false" >
  			 Não
  			</div>
		</div> 
		
		<div  class= "form-group" > 
		    <label  for= "qtdDiasVendidos" > Quantidade de dias Vendidos </label> 
		    <div class="span3">
		   		<input type="text" id="qtdDiasVendidos" name="qtdDiasVendidos" class= "form-control" size="2" disabled> 
		    </div>
		 </div> 
		 
		 <div  class= "form-group" > 
		    <label  for= "dataInicio" > Data Início </label> 
		    <div class="span3">
		    <input type="date" id="dataInicio" name="dataInicio" class="form-control" size="20" required> 
		    </div>
		 </div>
		 
		 <div  class= "form-group" > 
		    <label  for= "dataTermino" > Data Término </label> 
		    <div class="span3">
		    <input type="date" id="dataTermino" name="dataTermino" class="form-control" size="20" disabled> 
		    </div>
		 </div>
		 

		  <div  class= "form-group" > 
		    <label  for= "valor" > Valor a pagar pelas Férias 
 				<a href="javascript:void(0)" onclick="informacaoValor()"><i class="fa fa-info-circle" title="Informação"></i></a>
			</label>
		    <div class="span3">
		    	<input type="text" id="valor" name="valor" class="form-control" size="20" disabled> 
		    </div>
		 </div>
		 
    </div>
    
    <div id="botoes">
		<button  type= "submit" name="acao" value="Cadastrar" class= "btn btn-primário " > Registrar </button> 
		<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " onclick="history.go(-1)" > Cancelar </button> 
	</div>
	
  </nav>
  </form>


</div>
<c:import url="rodape.jsp"/>

</body>
</html>