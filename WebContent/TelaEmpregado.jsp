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
<script src="js/sweetalert.min.js"></script> 

<title>Funcionários</title>
</head>

<body>
<c:import url="cabecalhoLogado.jsp"/>
	
<div class="container">
        <h1>Meus funcionários</h1>
		<div class="adicionar">
            <a href="CadastrarUsuario?acao=Adicionar" class="link-avancar">
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
               
               <div class="row" style="border-radius: 25px; border: 2px solid #DCDCDC;">
                <div class="col-md-4">
                	<div class="funcDados">
							<a href="AlterarEmpregado?acao=Alterar&codEmpregado=<%=listaFunc.get(i).getCodigoEmpregado()%>"><%=listaFunc.get(i).getNome()%></a>
							<p class="text-orange">Salário <%=listaCont.get(i).getSalarioBase()%></p>
							<p>Contratado desde <%=listaCont.get(i).getDataAdmissao()%></p>
					</div>
                </div>
                <div class="col-md-2">
                		<a href="AlterarEmpregado?acao=Demitir&codEmpregado=<%=listaFunc.get(i).getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x">
								<i class="fa fa-square-o fa-stack-2x func-link-i1"></i>
								<i class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
							</span>
							<br>
							<span class="func-link-txt">Demitir</span>
						</a>
                </div>
                <div class="col-md-2">
                   <a href="AlterarEmpregado?acao=Ferias&codEmpregado=<%=listaFunc.get(i).getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x">
								<i class="fa fa-square-o fa-stack-2x func-link-i1"></i>
								<i class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
							</span>
							<br>
							<span class="func-link-txt">Férias</span>
					</a>
                </div>
                <div class="col-md-2">
                   <a href="AlterarEmpregado?acao=DecimoTerceiro&codEmpregado=<%=listaFunc.get(i).getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x">
								<i class="fa fa-square-o fa-stack-2x func-link-i1"></i>
								<i class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
							</span>
							<br>
							<span class="func-link-txt">Décimo Terceiro</span>
					</a>
                </div>
                <div class="col-md-2">
               		<div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Tutorials
    <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">HTML</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">CSS</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">JavaScript</a></li>
      <li role="presentation" class="divider"></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
    </ul>
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