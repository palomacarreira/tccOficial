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
<script type="text/javascript" src="js/scriptEmpregado.js"></script>
<script src="jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"  href="css/estilo.css" />
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/sweetalert.min.js"></script> 

<script type="text/javascript">
function confirma(){
	swal({   
		title: "Cadastro Empregado",   
		text: "Você tem certeza que deseja excluir o funcionário?",   
		type: "warning",   
		showCancelButton: true,   
		confirmButtonColor: "#DD6B55",   
		confirmButtonText: "Confirmar",   
		closeOnConfirm: false 
	});
}
</script>

<title>Rescisão</title>

</head>
<body>
<%
		EmpregadoTO empregado = (EmpregadoTO) request.getAttribute("listaEmpregado");
		ContratoTO contrato = (ContratoTO) request.getAttribute("listaContrato");
		request.setAttribute("codEmpregado", empregado.getCodigoEmpregado());
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

						
							<p style="font-size: 25px;">Rescisão</p>

					

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
<form id="dadosEmpregado" role="form" class="form-inline" method="post" action="AlterarEmpregado">
<nav class="navbar navbar-default" role="navigation">
	<label  for= "dadosEmpregado" > Dados Rescisão</label><br> <br>
    <div class="form-group"> 
    <label  for= "regimeContrato" >Motivo demissão:</label> 
    	<div class="span3">
		<input  type="radio" name="motivoDemissao" id="justaCausa"  value="Justa Causa" CHECKED>
		Justa Causa
		<input type="radio" name="motivoDemissao" id="solicitadoEmpregador"  value="Solicitado pelo empregador"> 
		Solicitado pelo empregador	
		<input type="radio" name="motivoDemissao" id="solicitadoEmpregado"  value="Solicitado pelo empregado"> 
		Solicitado pelo empregado	
		</div>	
    </div>
 
	<br>
	<br>
	<div  class= "form-group" > 
	<label  for= "nome" > Descrição: </label> 
		<div class="span3">
			<textarea  class= "form-control"  name="descricao" id="descricao" rows="4" cols="50" name="comment"></textarea>
		</div>
	</div> 
		
	<div  class= "form-group" > 
	<label  for= "dataAdmissao" > Data de Demissão: </label> 
		<div class="span3">
	    	<input type="date" id="dataDemissao" name="dataDemissao" class= "form-control" size="20"> 
	    </div>
	</div>
   <br>
	<br>	
</nav>
<nav class="navbar navbar-default" role="navigation">
	<div id="dadosFuncionario">
	  <div class="row">
      		<div class="col-md-2">
            	<div class="func-info">
					<p class="text-orange">Salário:</p>
					<p>Contratado desde:</p>	
				</div>
        	</div>
        	<div class="col-md-4"></div>
        	<div class="col-md-4"></div>
        	<div class="col-md-2">
            	<div class="func-info">
					<p class="text-orange"><%=contrato.getSalarioBase()%></p>
					<p><%=contrato.getDataAdmissao()%></p>	
				</div>
        	</div>
		</div>	
	</div>	

	<br>
	<br>
</nav>
     <div id="botoes">
     	<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " onclick="history.go(-1)" > Voltar </button> 
		<button  type= "submit" name="acao" value="Excluir" onclick="return confirma()" class= "btn btn-primário " > Demitir </button> 
		
	
	</div>
	 </form>
</div>

<c:import url="rodape.jsp"/>

</body>
</html>