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
<link rel="stylesheet" type="text/css"  href="css/estilo.css" />
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

div class="header clearfix">
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

						
							<p style="font-size: 25px;">Meu Perfil</p>

					

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
	<hr />

	<div class="container">
	<form name="form1" class= "form-inline" method="post" action="CadastrarFerias"> 
	<nav class="navbar navbar-default" role="navigation">
	
	<div class="container">
	<label  for= "meuPerfil" > Cadastro de Férias </label><br><br>
		  
		  <div  class= "form-group" > 
		    <label  for= "nome" > Período Aquisitivo(Inicio) </label> 
		    <div class="span3">
		    <input type="date" id="periodoAquisitivoInicio" name="periodoAquisitivoInicio" class="form-control" size="20" required> 
		    </div>
		  </div> 
		  <div  class= "form-group" > 
		    <label  for= "nome" > Período Aquisitivo(Fim) </label> 
		    <div class="span3">
		    <input type="date" id="periodoAquisitivoFim" name="periodoAquisitivoFim" class="form-control" size="20" required> 
		    </div>
		  </div> 
		  </br></br>
		   <div  class= "form-group" > 
		    <label  for= "dataInicio" > Data Início </label> 
		    <div class="span3">
		    <input type="date" id="dataInicio" name="dataInicio" class="form-control" size="20" required> 
		    </div>
		 </div>
		 
		 <div  class= "form-group" > 
		    <label  for= "dataTermino" > Data Término </label> 
		    <div class="span3">
		    <input type="date" id="dataTermino" name="dataTermino" class="form-control" size="20" > 
		    </div>
		 </div>
		 </br></br>
		   <%-- A GENTE JA TEM A QUANTIDADE DE DIAS DE FERIAS PEGANDO A DATA DE INICIO E DATA DE FIM. 
		   <div  class= "form-group" > 
		    <label  for= "qtdDiasFerias" > Quantidade de dias de férias</label> 
		    <div class="span3">
		    	<input  type= "text" id="qtdDiasFerias" name="qtdDiasFerias" class="form-control" size="2" > 
		    </div>
		 </div> --%>

		<div class="form-group radio" > 
	 	<label for="vendaFerias" > Abono Pecuniário (Venda de 1/3 das Férias)</label> 
  		<div class="span3">
   				 <input  type= "radio"  name="vendaFerias"  id="sim"  value="true" >
   			 Sim
  				<input  type= "radio"  name="vendaFerias"  id="não"  value="false" checked>
  			 Não
  			</div>
		</div> 
		
		<%-- Abono Pecuniário EH A VENDA DE 1/3 DAS FERIAS - OU VENDE 1/3 CONFORME A LEI OU NAO VENDE NADA
		<div  class= "form-group" > 
		    <label  for= "qtdDiasVendidos" > Quantidade de dias Vendidos </label> 
		    <div class="span3">
		   		<input type="text" id="qtdDiasVendidos" name="qtdDiasVendidos" class= "form-control" size="2" > 
		    </div>
		 </div> --%>
		 
		
		 </br></br>

		  <div  class= "form-group" > 
		    <label  for= "valor" > Valor a pagar pelas Férias 
 				<a href="javascript:void(0)" onclick="informacaoValor()"><i class="fa fa-info-circle" title="Informação"></i></a>
			</label>
		    <div class="span3">
		    	<input type="text" id="valor" name="valor" class="form-control" size="20" > 
		    </div>
		 </div>
    </div>
    </br></br>
    <div id="botoes">
   	    <button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " onclick="history.go(-1)" > Cancelar </button> 
		<button  type= "submit" name="acao" value="Cadastrar" class= "btn btn-primário " > Registrar </button> 
	</div>
	
  </nav>
  </form>


</div>
<c:import url="rodape.jsp"/>

</body>
</html>