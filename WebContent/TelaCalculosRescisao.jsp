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

<title>Dados Rescisão</title>

</head>
<body>
	<%
		double salarioLiquido = (double) request.getAttribute("salarioLiquido");
		double salarioProporcional = (double) request.getAttribute("salarioLiquidoProporcional");
		double inss = (double) request.getAttribute("inss");
		double fgts = (double) request.getAttribute("fgts");
		double irrf = (double) request.getAttribute("irrf");
		double valeTransporte = (double) request.getAttribute("valeTransporte");
		double ferias = (double) request.getAttribute("ferias");
		double feriasProporcionais = (double) request.getAttribute("feriasProporcionais");
		double decimoTerceiro = (double) request.getAttribute("decimoTerceiro");
		double multaAvisoPrevio = (double) request.getAttribute("multaAvisoPrevio");
		double folgas = (double) request.getAttribute("folgas");
		double beneficios = (double) request.getAttribute("beneficios");
		double total = (double) request.getAttribute("total");
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
                <div class="col-md-5">
					<p style="font-size: 25px;">Dados Rescisão</p>
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
	<form name="form1" class= "form-inline" method="post" action="">
		<nav class="navbar navbar-default" role="navigation">
			<div id="itensFolhaPonto">
			   <table id="tableFolhaPonto" class="table table-striped table-bordered" cellspacing="0" width="100%">
			        <thead>
			            <tr>
			                <th>Dados</th>
			                <th>Entrada</th>
			                <th>Descontos</th>
			            </tr>
			        </thead>
			        <tfoot>
			            <tr>
			                <th>&nbsp;</th>
			                <th>&nbsp;</th>
			                <th>&nbsp;</th>
			            </tr>
			        </tfoot>
			        <tbody>
			        
			        <tr>
			        <td>Salário Liquido</td>
				    <td><%=salarioLiquido%></td>
				    <td></td>
		    		</tr>
		    		
		    		<tr>
			        <td>Salário Proporcional</td>
				    <td><%=salarioProporcional%></td>
				    <td></td>
		    		</tr>
		    		
		    		<tr>
			        <td>INSS</td>
				    <td></td>
				    <td><%=inss%></td>
		    		</tr>
		    		
		    		<tr>
			        <td>FGTS</td>
				    <td></td>
				    <td><%=fgts%></td>
		    		</tr>
		    		
		    		<tr>
			        <td>IRRF</td>
				    <td></td>
				    <td><%=irrf%></td>
		    		</tr>
		    		
		    		<tr>
			        <td>Vale Transporte</td>
				    <td></td>
				    <td><%=valeTransporte%></td>
		    		</tr>
		    		
		    		<tr>
			        <td>Folgas</td>
				    <td></td>
				    <td><%=folgas%></td>
		    		</tr>
		    		
		    		<tr>
			        <td>Beneficios</td>
				    <td></td>
				    <td><%=beneficios%></td>
		    		</tr>
		    		
		    		<tr>
			        <td>Férias</td>
				    <td><%=ferias%></td>
				    <td></td>
		    		</tr>
		    		
		    		<tr>
			        <td>Férias Proporcionais</td>
				    <td><%=feriasProporcionais%></td>
				    <td></td>
		    		</tr>
		    		
		    		<tr>
			        <td>Decimo Terceiro</td>
				    <td><%=decimoTerceiro%></td>
				    <td></td>
		    		</tr>
		    		
		    		<tr>
			        <td>Multa Aviso Prévio</td>
				    <td></td>
				    <td><%=multaAvisoPrevio%></td>
		    		</tr>
		    		
		    		<tr>
			        <td>TOTAL PAGAR:</td>
				    <td><%=total%></td>
				    <td></td>
		    		</tr>
		    		
			        </tbody>
			    </table>
			</div>
		 </nav>
		 <br>
			<div id="botoes">
				<button  type= "submit" name="acao" value="imprimir" class= "btn btn-primário " > Imprimir </button> 
				<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário "  onclick="history.go(-1)"> Cancelar </button> 
			</div>
	</form>
	</div>

<c:import url="rodape.jsp"/>
</body>
</html>
