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
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script type="text/javascript">
<link rel="stylesheet" type="text/css"  href="css/estilo.css" />

$(document).ready(function(){
	
	$("#cpf").mask("999.999.999-99");
	$("#cnpj").mask("99.999.999/9999-99");
	$("#rg").mask("99.999.999-*");
	$("#cep").mask("99.999-999");
	$("#numeroTelefone").mask("(99)9999-9999");

});
</script>
<title>Meu Perfil</title>

</head>

<%
		UsuarioTO usuario = (UsuarioTO) request.getAttribute("listaUsuario");
		EnderecoTO endereco = (EnderecoTO) request.getAttribute("listaEndereco");
		ContatoTO contato = (ContatoTO) request.getAttribute("listaContato");

%>

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

<% String[] estados = {"AC", "AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PE","PI",
     			"PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};
     	%>
     	
<div class="container">   	
<form  class= "form-inline" method="post" action="AlterarUsuario">
	<nav class="navbar navbar-default" role="navigation">
	<div class="container">  
	<!-- <label  for= "meuPerfil" > Meu Perfil </label><br> <br> -->
		  <div  class= "form-group" > 
		    <label  for= "nome" > Nome </label> 
		     <%
		    out.println("<input type=\"text\" class= \"form-control\" value="+usuario.getNome()+" name=\"nome\" id=\"nome\" size=\"100\">");
		    %> 
		  </div> 
		  <div  class= "form-group" > 
		    <label  for= "sobrenome" > Sobrenome </label> 
		     <%
		     out.println("<input type=\"text\" value="+usuario.getSobrenome()+" class=\"form-control\"  name=\"sobrenome\" id=\"sobrenome\" size=\"100\">"); 
			 %> 
		     </div> 
 
		<div  class= "form-group" > 
		    <label  for= "cpf" > CPF </label> 
		     <%
		     out.println("<input type=\"text\" value="+usuario.getCpf()+"  class=\"form-control\"  name=\"cpf\" id=\"cpf\" size=\"10\">");
			%> 
		   </div> 
		 
		 <div  class= "form-group" > 
		    <label  for= "cnpj" > CNPJ </label> 
		    <%
		     out.println("<input type=\"text\"  class=\"form-control\" value="+usuario.getCnpj()+"  name=\"cnpj\" id=\"cnpj\" size=\"10\">");
			%> 
		 </div> 
		 
		 <div  class= "form-group" > 
		    <label  for= "rg" > RG </label> 
		   <%
		     out.println("<input type=\"text\" value="+usuario.getRg()+"  class=\"form-control\" name=\"rg\" id=\"rg\" size=\"10\">");
			%> 
		 </div>
		 
		 <div  class = "form-grupo" > 
     		 <label  for = "select" id="comboUf_RG" >UF RG</label> 

			<%
		out.println("<select class=\"form-control\" name=\"ufRg\" id=\"ufRg\">");
		
  
     	for (int i = 0; i < estados.length; i++) {
     		
     		if(estados[i].equals(usuario.getUfRg())){
     			out.println("<option value="+ estados[i]+" style =\"width: 100px; height: 100px\" selected>"+ estados[i] +"</option>");	
     		}
     		else{
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
     		}
     	}
		out.println("</select>");
    	%>
      		
    	</div> 
    	
    	<div  class= "form-group" > 
		    <label  for= "cep" > CEP </label> 
		 <%
		     out.println("<input type=\"text\" value="+endereco.getCep()+" class=\"form-control\"  name=\"cep\" id=\"cep\" size=\"10\">");
		 %> 
		 </div> 
		 
		 <div  class= "form-group" > 
		    <label  for= "logadouro" > Endereco </label>  
		    <%
		     out.println("<input  type=\"text\"  value="+endereco.getEndereco()+" class=\"form-control\" name=\"endereco\" id=\"endereco\" size=\"10\">");
		 	%> 
		 </div> 
		 
		 <div  class= "form-group" > 
		    <label  for= "numero" > Numero </label> 
		     <%
		     out.println("<input type=\"text\" value="+endereco.getNumero()+"  class=\"form-control\"  name=\"numeroEndereco\" id=\"numeroEndereco\" size=\"10\">");
		 	%>
		    </div>
		 
		 <div  class= "form-group" > 
		    <label  for= "complemento" > Complemento </label> 
		    <%
		     out.println("<input type=\"text\" value="+endereco.getComplemento()+" class=\"form-control\"  name=\"complemento\" id=\"complemento\" size=\"10\">");
		 	%>
		 </div>
		 
		  <div  class= "form-group" > 
		    <label  for= "bairro" > Bairro </label> 
		    <%
		     out.println("<input type=\"text\" value="+endereco.getBairro()+" class=\"form-control\"  name=\"bairro\" id=\"bairro\" size=\"10\">");
		 	%>
		 </div>
		 
			 <div  class = "form-grupo" > 
     		 <label  for = "select" >Estado</label> 
    	<%
		out.println("<select class=\"form-control\" name=\"estado\" id=\"comboUf_Estado\">");
		
     	for (int i = 0; i < estados.length; i++) {
     		
     		if(estados[i].equals(endereco.getEstado())){
     			out.println("<option value="+ estados[i]+" style =\"width: 100px; height: 100px\" selected>"+ estados[i] +"</option>");	
     		}
     		else{
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
     		}
     	}
		out.println("</select>");
    	%>
    	</div>
    	
      <div  class= "form-group " > 
		    <label  for= "cidade" > Cidade </label> 
		     <%
		     out.println("<input type=\"text\"  value="+endereco.getCidade()+" class=\"form-control\"  name=\"cidade\" id=\"cidade\" size=\"10\">");
		 	%>
	   </div>
	  </div>
    </nav>
    	
    <nav class="navbar navbar-default" role="navigation">
    	<div class="container">
    	
       <label  for= "email" > Login </label><br><br>
       
       <div  class= "form-group " > 
		    <label  for= "email" > Email </label> 
		     <%
		     out.println("<input type=\"text\" value="+usuario.getEmail()+" class=\"form-control\" name=\"email\" id=\"email\" size=\"10\">");
		 	%>
	   </div>
	   
	   <div  class= "form-group" > 
		    <label  for= "senha" > Senha </label> 
		    <%
		     out.println("<input type=\"text\"  value="+usuario.getSenha()+" class=\"form-control\" name=\"senha\" id=\"senha\" size=\"10\">");
		 	%>
		</div>
	
    </div>
    </nav>
    
    <nav class="navbar navbar-default" role="navigation">
    <div class="container">
       <label  for= "TipoContato" > Contato </label><br> <br>
       <div  class= "form-group " > 
		    <label  for= "tipoContato" > Tipo Contato </label> 
		     <%
		     out.println("<input type=\"text\" value="+ contato.getTipoContato()+" class=\"form-control\" name=\"tipoContato\" id=\"tipoContato\" size=\"20\">");
		 	%>
	   </div>
	   <div  class= "form-group" > 
		    <label  for="numeroTelefone" > Numero </label> 
		    <%
		     out.println("<input type=\"text\" value="+contato.getNumero()+" class=\"form-control\" name=\"numeroTelefone\" id=\"numeroTelefone\" size=\"20\">");
		 	%>
		</div>
    </div>
     </nav>
     
 		<nav class="navbar navbar-default" role="navigation">
     	<div id="botoes">
			<button  type= "submit" name="acao" value="Salvar" class= "btn btn-primário " > Alterar </button> 
			<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " onclick="history.go(-1)"> Cancelar </button> 
		</div>
       </nav>
  </form>
</div>
<c:import url="rodape.jsp"/>
</body>

</html>