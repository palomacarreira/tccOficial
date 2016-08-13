<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="dist/css/bootstrap-submenu.min.css">

  <script src="https://code.jquery.com/jquery-2.2.4.min.js" defer></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" defer></script>
  <script src="dist/js/bootstrap-submenu.min.js" defer></script>
  
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
				    <ul class="nav nav-pills menu-principal" align="center">
				      <li><a href="PesquisarEmpregado?acao=PesquisarTodos">Cadastro de Funcionarios</a></li>
	 				  <li class="atividades"><a href="#">Atividades</a></li>
				      <li><a href="#">Folha de Ponto</a></li>
	  				  <li class="guias">
	  				  <a tabindex="0" data-toggle="dropdown" data-submenu>Guias<span class="caret"></span></a>   
	  				  
		  				  <ul class="dropdown-menu">
			  				  <li class="guias dropdown-submenu">
								      <a tabindex="0">INSS</a>
								      <a tabindex="0">FGTS</a>
								      <a tabindex="0">E_SOCIAL</a>
							  </li>
						  </ul>
					  </li>
					  
	                  
	                  <li class="holerite"><a href="#">Holerite</a></li>
	                  <li class="qrcode"><a href="#">QR Code</a></li>
	                  
	                  <li class="perfil navbar-right">
		  				  <a tabindex="0" data-toggle="dropdown" data-submenu><img src="imagens/ferramenta.png" width="20" height="20" align="right"><span class="caret"></span></a>   
		  				  
			  				  <ul class="dropdown-menu">
				  				  <li class="perfil dropdown-submenu">
									      <a href="AlterarUsuario?acao=alterar" tabindex="0">Meu Perfil</a>
									      <a href="AlterarUsuario?acao=excluir" tabindex="0">Excluir Conta</a>
									      <a href="TelaLogin.jsp" tabindex="0">Sair</a>
								  </li>
							  </ul>
						  </li>
				    </ul>
				</nav>

			</div>
	  	</div>
	</div>
	<hr />
</body>
</html>