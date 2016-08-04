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
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prettify/r298/run_prettify.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>
<link rel="stylesheet" href="css/font-awesome.min.css">
<style type="text/css">
    	#content {
			margin: 30px;
    	}
</style>

<title>Funcionários</title>
</head>

<body>
<c:import url="cabecalhoLogado.jsp"/>
	
<div id="content">
        <div class="row">
            <div class="col-md-8">
                <h1>Meus funcionários</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="adicionar">
                    <a href="CadastrarUsuario?acao=Adicionar" class="link-avancar">
                    <i class="fa fa-plus-square fa-2x" aria-hidden="true"></i>
                   		<span class="func-link-txt" style="font-size:30px;">adicionar</span>
                    </a>
                </div>
              </div>
         </div>
 				<br><br>
 			<div id="meusFuncionarios">
 			<%
				if(request.getAttribute("combo") != null){
				
				ArrayList<EmpregadoTO> lista = (ArrayList<EmpregadoTO>) request.getAttribute("combo");
				
				for (EmpregadoTO item : lista) {
					EspecialistaContrato espContrato = new EspecialistaContrato();
					ContratoTO contratoTO = espContrato.pesquisarEmpregado(item.codigoEmpregado);
					String fotoEmpregado = (String) ManipulandoImagem.exibiImagemLabel(item.getFoto());	
				%>
               <div class="col-md-12">
               <div class="row" style="border-radius: 25px; border: 2px solid #DCDCDC;">
                <div class="col-md-2">
                	<img class="img-circle" height="50" src="data:image/png;base64,<%=fotoEmpregado%>" alt="">	
                </div>
                <div class="col-md-4">
                	<div class="func-info">
							<a href="AlterarEmpregado?acao=Alterar&codEmpregado=<%=item.getCodigoEmpregado()%>"><%=item.getNome()%></a>
							<p class="text-orange">Salário <%=contratoTO.getSalarioBase()%></p>
							<p>Contratado desde <%=contratoTO.getDataAdmissao()%></p>
						</div>
                </div>
                <div class="col-md-2">
                		<a href="AlterarEmpregado?acao=Excluir&codEmpregado=<%=item.getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x">
								<i class="fa fa-square-o fa-stack-2x func-link-i1"></i>
								<i class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
							</span>
							<br>
							<span class="func-link-txt">Demitir</span>
						</a>
                </div>
                <div class="col-md-2">
                   <a href="AlterarEmpregado?acao=Ferias&codEmpregado=<%=item.getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x">
								<i class="fa fa-square-o fa-stack-2x func-link-i1"></i>
								<i class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
							</span>
							<br>
							<span class="func-link-txt">Férias</span>
						</a>
                </div>
                <div class="col-md-2">
                   <a href="AlterarEmpregado?acao=DecimoTerceiro&codEmpregado=<%=item.getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x">
								<i class="fa fa-square-o fa-stack-2x func-link-i1"></i>
								<i class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
							</span>
							<br>
							<span class="func-link-txt">Décimo Terceiro</span>
						</a>
                </div>
            </div>
        </div>
	<%}}%>
</div>
</div>

<c:import url="rodape.jsp"/>
<script type="text/javascript">
window.onload = function(){
	 <%
	  String aqui= (String)request.getAttribute("mge");
	  if(aqui != null){
	  %>
		  BootstrapDialog.alert("<%=aqui%>");

	<%}%>
}
</script>	
</body>
</html>