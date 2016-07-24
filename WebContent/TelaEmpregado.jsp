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

<title>Funcionários</title>
</head>

<body>

<c:import url="cabecalhoLogado.jsp"/>
	
	<div class="container">
	<label for="funcionarios" > Funcionários </label></br></br>
	<form name="form1" class= "form-inline" method="post" action="CadastrarUsuario">
		<div id="botoes">
			<button  type= "submit" name="acao" value="Adicionar" class="btn btn-primário " > Adicionar </button> 
		</div>
    </form>
	</div>

	</br>
	

<form method="post" action="PesquisarEmpregado">
	<fieldset id="f1">
			<div>
 				<table border="1px">
 			<%
				if(request.getAttribute("combo") != null){
				ArrayList<EmpregadoTO> lista = (ArrayList<EmpregadoTO>) request.getAttribute("combo");
				out.println("<tr>");
				out.println("<td width=\"25%\" height=\"20%\">Funcionários</td>");
				out.println("<td width=\"25%\"><center>Férias</center></td>");
				out.println("<td width=\"25%\"><center>13º</center></td>");
				out.println("<td width=\"25%\"><center>Demitir</center></td>");
				out.println("</tr>");
			
				for (EmpregadoTO item : lista) {
				out.println("<tr>");
				out.println("<td height=\"20%\">"+item.getNome()+"</td>");
				out.println("<td><center>");
				out.println("<button type=\"submit\" name=\"acao\" value=\"Ferias\" class=\"btn btn-primário\" > F </button>"); 
				out.println("</center></td>");
				out.println("<td><center>");
				out.println("<button type=\"submit\" name=\"acao\" value=\"DecimoTerceiro\" class=\"btn btn-primário\" > 13º </button>");
				out.println("</center></td>");
				out.println("<td><center>");
				out.println("<button type=\"submit\" name=\"acao\" value=\"Demitir\" class=\"btn btn-primário\" > D </button>");
				out.println("</center></td>");
				out.println("</tr>");
				}
				}
				else{
					out.println("<tr>");
					out.println("<td width=\"25%\" height=\"20%\">Funcionários</td>");
					out.println("<td width=\"25%\"><center>Férias</center></td>");
					out.println("<td width=\"25%\"><center>13º</center></td>");
					out.println("<td width=\"25%\"><center>Demitir</center></td>");
					out.println("</tr>");
				}
			%>
		</table>
 			</div>
	</fieldset>
	</form>
<c:import url="rodape.jsp"/>
</body>

</html>