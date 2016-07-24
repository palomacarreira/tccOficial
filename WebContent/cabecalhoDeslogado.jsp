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
<title>Cabecalho</title>    <!--Esta tela vai ser chamada por todas que tem que ter o cabecalho deslogado-->
</head>
<body>
	<div class="header clearfix">
		<div class="row">
			<div class="col-md-4">
				<img src="imagens/logo.jpg" align="left">
			</div>
			<div class="col-md-8">
				<br>
				<br>
				<nav>
				    <ul class="nav nav-pills " align="center">
				      <li><a href="TelaLogin.jsp">Home</a></li>
				      <li><a href="TelaDocumentacaoNecessaria.jsp">Documentacao Necessaria</a></li>
	  				  <li><a href="TelaConhecaLei.jsp">Conheca a lei</a></li>
	                  <li><a href="TelaComoFunciona.jsp">Como Funciona</a></li>
	                  <li><a href="TelaFormularioContato.jsp">Contatos</a></li>
				    </ul>
				</nav>
			</div>
	  	</div>
	</div>
	<hr />
</body>
</html>