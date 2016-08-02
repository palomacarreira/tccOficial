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
	$("#cnpj").mask("99.999.999/9999-99");
	$("#rg").mask("99.999.999-*");
	$("#cep").mask("99.999-999");
	$("#numeroTelefone").mask("(99)9999-9999");

});
</script>

<title>Cadastro Usuário</title>

</head>
<body>

<% String[] estados = {"AC", "AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PE","PI",
     			"PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};
     	%>
     	
<c:import url="cabecalhoDeslogado.jsp"/>
<div class="container">

<form name="form1" class= "form-inline" method="post" action="CadastrarUsuario">
<nav class="navbar navbar-default" role="navigation">
<div class="container">

	<label  for= "meuPerfil" > Meu Perfil </label></br> </br>
		  <div  class= "form-group" > 
		    <label  for= "nome" > Nome </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="nome" id="nome" size="80"> 
		    </div>
		  </div> 
		  <div  class= "form-group" > 
		    <label  for= "sobrenome" > Sobrenome </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="sobrenome" id= "sobrenome" size="80"> 
		    </div>
		  </div> 

		<div  class= "form-group" > 
		    <label  for= "cpf" > CPF </label> 
		    <div class="span3">
		    <input type="text" id="cpf" name="cpf" class= "form-control" size="28"> 
		    </div>
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "cnpj" > CNPJ </label> 
		    <div class="span3">
		    <input  type= "text" id="cnpj" name="cnpj" class= "form-control" size="28"> 
		    </div>
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "rg" > RG </label> 
		    <div class="span3">
		    <input  type= "text" name="rg" id= "rg" class= "form-control" size="30"> 
		    </div>
		 </div>
		 
		 <div class="col-sm-1 pull-right">
     		 <label  for = "select" >UF RG</label> 
     		 <div class="span3">
 
       		  <%
		out.println("<select class=\"form-control\" name=\"ufRg\" id=\"comboUf_Rg\">");
		
     	for (int i = 0; i < estados.length; i++) {
     		
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
     		
     	}
		out.println("</select>");
    	%>
    	
      		</div>
    	</div> 
    	
    	<div  class= "form-group" > 
		    <label  for= "cep" > CEP </label>
		    <div class="span3"> 
		    <input  type= "text"  class= "form-control"  name="cep" id= "cep" size="25"> 
		    </div>
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "logadouro" > Endereco </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control" name="endereco" id= "endereco" size="25"> 
		    </div>
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "numero" > Numero </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="numeroEndereco" id= "numeroEndereco" size="25"> 
		    </div>
		 </div>
		 <div  class= "form-group" > 
		    <label  for= "complemento" > Complemento </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="complemento" id= "complemento" size="10"> 
		    </div>
		 </div>
		  <div  class= "form-group" > 
		    <label  for= "bairro" > Bairro </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="bairro" id= "bairro" size="10"> 
		    </div>
		 </div>
		     <div  class= "form-group " > 
		    <label  for= "cidade" > Cidade </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="cidade" id= "cidade" size="10"> 
		    </div>
		 
	   </div>
		 
			 <div class="col-sm-1 pull-right">
     		 <label  for = "select" >Estado</label> 
     		 <div class="span3">
       		  
								<%
		out.println("<select class=\"form-control\" name=\"estado\" id=\"estado\">");
		
     	for (int i = 0; i < estados.length; i++) {
 
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
     	}
		out.println("</select>");
    	%>
      	</div>
    	</div> 
 </div>
 </br>
    	</nav>
    	<nav class="navbar navbar-default" role="navigation">
    	<div class="container">

       <label  for= "email" > Login </label></br> </br>
      
       <div  class= "form-group " > 
		    <label  for= "email" > E-mail </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control" name="email" id= "email" size="40"> 
		    </div>
	   </div>
	   <div  class= "form-group" > 
		    <label  for= "senha" > Senha </label> 
		    <div class="span3">
		    <input  type="password"  class= "form-control" name="senha" id= "senha" size="10"> 
		    </div>
		</div>
		

    </div>
     </br>
     
    </nav>
    
     	<nav class="navbar navbar-default" role="navigation">
    <div class="container">
         <label  for= "TipoContato" > Contato </label></br> </br>
       <div  class= "form-group " > 
		    <label  for= "tipoContato" > Tipo Contato </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control" name="tipoContato" id= "tipoContato" size="20"> 
		    </div>
	   </div>
	   <div  class= "form-group" > 
		    <label  for= "numeroTelefone" > Numero </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control" name="numeroTelefone" id= "numeroTelefone" size="20"> 
		    </div>
		</div>


      </br>
    </div>
    </br>
      </nav>
    
     <div id="botoes">
		<button  type= "submit" name="acao" value="Cadastrar" class= "btn btn-primário " > Salvar </button> 
		<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " > Cancelar </button> 
	</div>
	
     
  </form>



</div>
<c:import url="rodape.jsp"/>

</body>
</html>