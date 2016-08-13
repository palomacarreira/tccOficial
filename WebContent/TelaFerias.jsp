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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Férias</title>

</head>
<body>

<c:import url="cabecalhoLogado.jsp"/>

<div class="container">
  <div id="meusFuncionarios">	
  <h2>Histórico de Férias</h2>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Período Aquisitivo</th>
        <th>Início</th>
        <th>Término</th>
        <th>Dias de Férias</th>
        <th>Situação</th>
      </tr>
    </thead>
    <tbody>
    <%
		if(request.getAttribute("comboFerias") != null)
		{
			ArrayList<FeriasTO> listaFerias = (ArrayList<FeriasTO>) request.getAttribute("comboFerias");
			for (int i = 0; i < listaFerias.size(); i++) 
			{	
			%>
		      <tr>
		        <td><%=listaFerias.get(i).getPeriodoAquisitivo()%></td>
		        <td><%=listaFerias.get(i).getDataInicio()%></td>
		        <td><%=listaFerias.get(i).getDataFinal()%></td>
		        <td><%=listaFerias.get(i).getQtdDiasFerias()%></td>
		      	<td><%=listaFerias.get(i).getSituacao()%></td>
		      </tr>
		 	<% 
		 	}
		}
		else
		{
			%>
			<tr>
				<td>Nenhum registro encontrado</td>
			</tr>
			<%
		}
		%>
	</tbody>
  </table>

	<div class="adicionar">
            <a href="CadastrarFerias?acao=Adicionar&codigoEmpregado=<%=request.getAttribute("codigoEmpregado")%>" class="link-avancar">
            	<i class="fa fa-plus-square fa-2x"></i>
            	<span class="func-link-txt" style="font-size:30px;">Adicionar Férias</span>
        	</a>
        </div>
 		<br>
 		<br>
</div>

<c:import url="rodape.jsp"/>

</body>
</html>