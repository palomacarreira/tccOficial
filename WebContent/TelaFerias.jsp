<%@page import="java.util.*, java.text.*"%>
<%@page import="model.*, mysqldao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script src="js/jquery-1.2.6.pack 2.js"></script>
<script src="js/jquery.maskedinput-1.1.4.pack 2.js"></script>
<script src="jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/sweetalert.min.js"></script>
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"  href="css/estilo.css" />

<script type="text/javascript">

	function pagamento(idFerias, idEmpregado)
	{
		 location.href = "AlterarFerias?acao=TelaPagamento&codigoEmpregado="+idEmpregado+"&codigoFerias="+idFerias;
	}
	function relatorio(idFerias, idEmpregado)
	{
		 location.href = "PesquisarFerias?acao=GerarFerias&codEmpregado="+idEmpregado+"&codigoFerias="+idFerias;
	
	}

		function confirma(idFerias, idEmpregado) 
		{
			
				 swal({
				        title: "Cadastro Férias",
				        text: "Você tem certeza que deseja excluir?",
				        type: "warning",
				        showCancelButton: true,
				        confirmButtonColor: '#DD6B55',
				        confirmButtonText: 'Sim, Tenho Certeza!',
				        cancelButtonText: "Não, Cancelar!",
				        closeOnConfirm: false,
				        closeOnCancel: false
				    },
				    function(isConfirm) {
				        if (isConfirm) {
					            swal({
					                title: 'Confirmado!',
					                text: 'Férias excluida!',
					                type: 'success'
				            }, function() {
				            	 location.href = "AlterarFerias?acao=Excluir&codigoEmpregado="+idEmpregado+"&codigoFerias="+idFerias;
				            });

				        } else {
				            swal("Cancelado", "Exclusão Cancelada!", "error");
				        }
				    });
		}	
</script>

<title>Férias</title>

</head>

<body>
<% 
	ArrayList<FeriasTO> listaPeriodos  = (ArrayList<FeriasTO>) request.getAttribute("listaPeriodos");
	EmpregadoTO empregado = (EmpregadoTO) request.getAttribute("listaEmpregado");
%>

<div class="header clearfix">
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
			defer></script>

		<script src="dist/js/bootstrap-submenu.min.js" defer></script>
		<script src="js/sweetalert.min.js"></script>
		<div class="row">
			<div class="col-md-2">
				<img src="imagens/logo.jpg" align="left">
			</div>
		</div>
	</div>
	
	<div class="container">
	 
	 				<nav>
				<ul class="nav nav-pills">

                <div class="col-md-2">

						
							<!--<p style="font-size: 25px;">Férias</p>  -->

					

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
				
	 <h2>Histórico de Férias</h2>
	  
	  <form role="form" class="form-inline" method="post" action="CadastrarFerias?acao=Adicionar&codigoEmpregado=<%=request.getAttribute("codigoEmpregado")%>">
		  
		  <div class="form-group">
		  	  <label  for= "nome" > Período Aquisitivo </label> 
		  	  <div class="span3">	
			  <select class="form-control" id="periodoAquisitivo" name="periodoAquisitivo">
				  <%
				  for (int i = 0; i < listaPeriodos.size(); i++) 
				  {
				  		String dado= listaPeriodos.get(i).getPeriodoAquisitivoInicio()+ "/" + listaPeriodos.get(i).getPeriodoAquisitivoFim();
			     		out.println("<option value="+dado+">"+ dado +"</option>");		
			      }
				  %>
			  </select>
			  </div>
		  </div>
		  
		   <div class="form-group">
		  	<div class="span3">	
		    <%
		    	if(listaPeriodos.size() == 0){
		    		out.println("<button type=\"submit\" disabled>Adicionar Férias</button>");
		    		out.println("<label>NÃO HÁ FÉRIAS DISPONÍVEIS</label>");
		    	}
		    	else{
		    		out.println("<button type=\"submit\">Adicionar Férias</button>");
		    	}
			%>
			 </div>
		 </div>
		 <br>
		 <br>
 	</form>
 	
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th>Período Aquisitivo</th>
	        <th>Início</th>
	        <th>Término</th>
	        <th>Dias de Férias</th>
	        <th>Situação</th>
	        <th></th>
	        <th></th>
	      </tr>
	    </thead>
	    <tbody>
	    <%	
	    	String codigoEmpregado = (String) request.getAttribute("codigoEmpregado");
			if(request.getAttribute("comboFerias") != null)
			{
				ArrayList<FeriasTO> listaFerias = (ArrayList<FeriasTO>) request.getAttribute("comboFerias");
				for (int i = 0; i < listaFerias.size(); i++) 
				{	
				String dado= listaFerias.get(i).getPeriodoAquisitivoInicio()+ "/" + listaFerias.get(i).getPeriodoAquisitivoFim();
						
				%>
			      <tr>
			        <td><%=dado%></td>
			        <td><%=listaFerias.get(i).getDataInicio()%></td>
			        <td><%=listaFerias.get(i).getDataFinal()%></td>
			        <td><%=listaFerias.get(i).getQtdDiasFerias()%></td>
			      	<td><%=listaFerias.get(i).getSituacao()%></td>
			      	<%
					 	if(listaFerias.get(i).getSituacao().equals("PENDENTE")){
					 		%>
	<td><button onclick="pagamento(<%=listaFerias.get(i).getCodigo()%>, <%= codigoEmpregado %>)" name="botaoPagar" type="button" class="btn btn-success">Pagar</button></td>
						<%
						}
					 	else{
					 		%>
	<td><button onclick="pagamento(<%=listaFerias.get(i).getCodigo()%>, <%= codigoEmpregado %>)" name="botaoPagar" type="button" class="btn btn-success" disabled>Pagar</button></td>
					 		<%
					 	}
					%>
					 <td><button onclick="confirma(<%=listaFerias.get(i).getCodigo()%>, <%= codigoEmpregado %>)" type="submit" class="btn btn-danger">Excluir</button></td>
					<td><button onclick="relatorio(<%=listaFerias.get(i).getCodigo()%>, <%= codigoEmpregado %>)" type="submit" class="btn btn-success">Exportar Férias</button></td>
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
  	   
<c:import url="rodape.jsp"/>
</body>
</html>








