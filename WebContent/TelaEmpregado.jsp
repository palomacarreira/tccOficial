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
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="dist/css/bootstrap-submenu.min.css">

<link rel="stylesheet" type="text/css"  href="css/estilo.css" />
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" defer></script>
  <script src="dist/js/bootstrap-submenu.min.js" defer></script>
<script src="js/sweetalert.min.js"></script> 

<title>Funcionários</title>
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
				<ul class="nav nav-pills">

                <div class="col-md-2">

						
							<p style="font-size: 25px;">Funcionários</p>

					

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
       
		<div class="adicionar">
            <a href="CadastrarEmpregado?acao=Adicionar" class="link-avancar">
            	<i class="fa fa-plus-square fa-2x"></i>
            	<span class="func-link-txt" style="font-size:30px;">adicionar</span>
        	</a>
        </div>
 		<br>
 		<br>
 		<div id="meusFuncionarios">
 			<%
				if(request.getAttribute("comboFuncionarios") != null){
				ArrayList<EmpregadoTO> listaFunc = (ArrayList<EmpregadoTO>) request.getAttribute("comboFuncionarios");
				ArrayList<ContratoTO> listaCont = (ArrayList<ContratoTO>) request.getAttribute("comboContratos");
					
				for (int i = 0; i < listaFunc.size(); i++) 
				{	
				%>
               
               <div class="row1" ><!--  style="border-radius: 25px; border: 2px solid #DCDCDC;"-->
                <div class="col-md-4" style="border-radius: 25px; border: 2px solid #DCDCDC;">
                	<div class="funcDados">
                			<img href="AlterarEmpregado?acao=Alterar&codEmpregado=<%=listaFunc.get(i).getCodigoEmpregado()%>" id="myimage" src="uploads/1/<%=listaFunc.get(i).getFoto() %>" height="100">
							<a href="AlterarEmpregado?acao=Alterar&codEmpregado=<%=listaFunc.get(i).getCodigoEmpregado()%>"><%=listaFunc.get(i).getNome()%> <%=listaFunc.get(i).getSobrenome()%></a>
							<p class="text-orange" style="font-family: Arial; font-style: oblique; font-size: 15px;text-align: center">Salário <%=listaCont.get(i).getSalarioBase()%></p>
							<p style="font-family: Arial; font-style: oblique; font-size: 15px;text-align: center">Contratado desde: <%=listaCont.get(i).getDataAdmissao()%></p>
					</div>
                </div>
          
            </div>
     	 <% }

		}
		else{
		%> 
			<p>Você não tem funcionários cadastrados ou ativos no momento.</p>
			<p>Clique em adicionar acima para cadastrar um novo funcionário.</p>
		<%}%>
	</div>
</div>


<c:import url="rodape.jsp"/>
<script type="text/javascript">
window.onload = function(){
	 <%
	  String aqui= (String)request.getAttribute("mge");
	  if(aqui != null){
	  %>
	  swal({   title: "CADASTRO EMPREGADO",   
			text: "<span style=\"color:#F8BB86\"><%=aqui%><span>",   
					html: true 
			});

	<%}%>
}
</script>	
</body>
</html>