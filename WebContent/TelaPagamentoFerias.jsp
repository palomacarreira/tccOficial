<%@page import="java.util.*, java.text.*"%>
<%@page import="model.*, mysqldao.*"%>
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
<script type="text/javascript" src="js/scriptFerias.js"></script>

<title>Pagamento Férias</title>

</head>
<body>
	<%
		double valorFerias = (double) request.getAttribute("valor");
		String codigoFerias = (String) request.getAttribute("codigoFerias");
		String codigoEmpregado = (String) request.getAttribute("codigoEmpregado");
	%>  
	
		<div class="header clearfix">
		<div class="row">
			<div class="col-md-4">
				<img src="imagens/logo.jpg" align="left">
			</div>
			<div class="col-md-8">
				<br>
				<br>
				<nav>
				<ul class="nav nav-pills">
                <div class="col-md-2">
					<p style="font-size: 25px;">Pagamento Férias</p>
				</div>
	            <nav >
		  		<ul class="menu perfil navbar-right" style="position: relative; left: -120px;">
		            <li><a href="#" ><img src="imagens/ferramenta.png" width="20" height="20" align="right"></a>
		                <ul>
		                      <li><a href="AlterarUsuario?acao=alterar">Meu Perfil</a></li>
		                      <li><a href="AlterarUsuario?acao=excluir">Excluir</a></li>
		                      <li><a href="TelaLogin.jsp">Sair</a></li>                   
		                </ul>
		            </li>
		                   
				</ul>
				</nav>	
				</ul>
				</nav>
			</div>
		</div>
		</div>
	<br>
	<hr />
	
	
	<div class="container">
	<form name="form1" class= "form-inline" method="post" action="AlterarFerias?codigoEmpregado=<%= codigoEmpregado %>&codigoFerias=<%= codigoFerias %>">
	<nav class="navbar navbar-default" role="navigation">
		 
		 <label  for= "dadosEmpregado" > Dados Pagamento </label><br><br>
	
			<nav>
		  <div  class= "form-group" > 
		    <label  for= "nome" > Valor </label> 
		    <div class="span3">
		    <input  type= "text"  value="<%= valorFerias %>" class= "form-control"  name="nome" id="nome" size="80" required> 
		    </div>
		  </div> 
		  
		  <div  class= "form-group" > 
		    <label  for= "dataNasc" > Data Pagamento </label> 
		    <div class="span3">
		    <input type="date" id="dataPagamento" name="dataPagamento" class="form-control" size="20" required> 
		    </div>
		 </div>
		 </nav>
		 <br>
		 <nav>
		 	 <div id="botoes">
				<button  type= "submit" name="acao" value="Pagamento" class= "btn btn-primário " > Registrar Pagamento </button> 
				<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário "  onclick="history.go(-1)"> Cancelar </button> 
			</div>
		 </nav>
		 <br>
	</nav>
	</form>
	</div>

<c:import url="rodape.jsp"/>
</body>
</html>
