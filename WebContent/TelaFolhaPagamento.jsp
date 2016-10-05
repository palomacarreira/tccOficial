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
<script>
function informacaoDescontoValeTransporte(){
	
	swal({   title: "Vale Transporte",   
			text:
				"<p>O empregado tem obrigação de contribuir com o valor da sua passagem na proporção equivalente "+
				"a 6% do valor do seu salário, quantia que será descontada em folha. Se o saldo de passagens mensais "+
				"tiver um valor menor do que 6% do salário, então o empregador deverá descontar no máximo o valor "+
				"referente a passagem. Quando as passagens tiverem o valor maior do que o desconto permitido, "+
				"então o empregador deverá complementar o custo. </p>",
				html: true 
			});
}
</script>

<title>Tela Folha de Pagamento</title>

</head>

<body>	
<%
		EmpregadoTO empregado = (EmpregadoTO) request.getAttribute("listaEmpregado");
		ContratoTO contrato = (ContratoTO) request.getAttribute("listaContrato");
		
		String[] meses = {"","Janeiro", "Fevereiro","Março","Abril","Maio","Junho",
				"Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
		String dataAdmissao = (String) request.getAttribute("dataAdmissao");
		int anoAdmissao = Integer.parseInt(dataAdmissao.substring(0,4));
		String codigoEmpregado = (String) request.getAttribute("codigoEmpregado");
		Calendar calendar = new GregorianCalendar();
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
		int anoEscolhido = (int) request.getAttribute("anoEscolhido");
		int mesEscolhido = (int) request.getAttribute("mesEscolhido");
		int diasDoMes = (int)request.getAttribute("totalDias");
		double valorHoraExtra = (double)request.getAttribute("valorHoraExtra");
		double salarioLiquido = (double)request.getAttribute("salarioLiquido");
		double inss = (double)request.getAttribute("inss");
		double fgts = (double)request.getAttribute("fgts");
		double irrf = (double)request.getAttribute("irrf");
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

			<div class="col-md-10">
				<br> <br>
				<nav>
				<ul class="nav nav-pills">
				
				<div class="col-md-1">
						<a href="PesquisarEmpregado?acao=PesquisarTodos">
							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
						</span> <br> <span class="func-link-txt">Funcionários</span>
						</a>
					</div>
					
					<div class="col-md-1">
						<a href="AlterarEmpregado?acao=Demitir&codEmpregado=<%=empregado.getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
						</span> <br> <span class="func-link-txt">Demitir</span>
						</a>
					</div>
					
					<div class="col-md-1">
						<a href="PesquisarFolhaPagamento?acao=Pesquisar&codigoEmpregado=<%=empregado.getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-bars fa-stack-1x func-link-i2"></i>
						</span> <br> <span class="func-link-txt">Holerite</span>
						</a>
					</div>
					
					<div class="col-md-1">
						<a href="PesquisarFerias?acao=Pesquisar&codigoEmpregado=<%=empregado.getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
						</span> <br> <span class="func-link-txt">Férias</span>
						</a>
					</div>

					<div class="col-md-1">
						<a href="AtividadeController?acao=PesquisarTodos&codigoEmpregado=<%=empregado.getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
						</span> <br> <span class="func-link-txt">Atividades</span>
						</a>
					</div>

					<div class="col-md-1">
						<a href="#">
							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
						</span> <br> <span class="func-link-txt">Décimo Terceiro</span>
						</a>
					</div>
					
					<div class="col-md-1">
						<a href="PesquisarPonto?acao=Pesquisar&codigoEmpregado=<%=empregado.getCodigoEmpregado()%>">
							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>
						</span> <br> <span class="func-link-txt">Folha de Ponto</span>
						</a>
					</div>

					<div class="col-md-4">
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
					</div>
				</ul>
			</nav>
		</div>
	</div>
	</div>
	
	<div class="container">
	<form id="dadosEmpregado" role="form" class="form-inline" method="post" action="AlterarEmpregado" enctype="multipart/form-data">
		<div class="container" style="width: 100%;">    
			<div class= "form-group" style="float: right;">
		     	<label  for = "select" >ANO</label> 
		     	<div class="span3">
				<select class="form-control" id="combo_ano" name="ano" onchange="funcaoData()">
			        <%
						for (int i = anoAdmissao; i <= anoAtual; i++) 
						{	
				            if(i == anoEscolhido)
				            {
				            	%>
				                <option value="<%=i%>" selected="'selected'"><%=i%></option>
				                <%
				            }
				            else{
				            	%>
				                <option value="<%=i%>"><%=i%></option>
				                <% 
				            }
					 	}
					%>
	           </select>
		      </div>
		    </div> 
		
			<div class= "form-group" style="float: right; margin-right: 5px;">
		     	<label  for = "select" >MÊS</label> 
		     	<div class="span3">
					<select class="form-control" id="combo_mes" name="mes" onchange="funcaoData()">
		            <%     
		            
		            for (int i = 1; i < meses.length; i++) {
		            	
		            	if(i == mesEscolhido)
		 	            {
		 	            	%>
		 	                <option value="<%=i%>" selected="'selected'"><%=meses[i]%></option>
		 	                <%
		 	            }
		 	            else{
		 	            	%>  
		     		 		<option value="<%=i%>"><%=meses[i]%></option>
		 	                <% 
		 	            }
		     		}
		            %>  
		        	</select>
		      	</div>
		    </div>  	
		</div> 
	<script>
		function funcaoData() 
		{
			var valor_mes = document.getElementById("combo_mes").value;
			var valor_ano = document.getElementById("combo_ano").value;
			location.href="PesquisarFolhaPagamento?acao=Pesquisar&codigoEmpregado=<%=codigoEmpregado%>&mes="+valor_mes+"&ano="+valor_ano+""; 
		}
	</script>

	<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<label  for= "dadosEmpregado" > Dados da Folha </label><br><br>

		  <div  class= "form-group" > 
		    <label  for= "nome" > Funcionário</label> 
		    <div class="span3">
		      <%
		     out.println("<input type=\"text\" value="+empregado.getNome()+" class=\"form-control\"  name=\"nome\" id=\"nome\" size=\"80\" readonly>");
		 	%>
		    </div>
		  </div>
		
		  <div  class= "form-group" > 
		    <label  for= "sobrenome" > Sobrenome </label> 
		    <div class="span3">
		     <%
		     out.println("<input type=\"text\" value="+empregado.getSobrenome()+" class=\"form-control\"  name=\"sobrenome\" id=\"sobrenome\" size=\"80\" readonly>");
		 	%>
		    </div>
		  </div> 
		
		 <div  class= "form-group" > 
		    <label  for= "salario" > Salário Base </label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	       		 	<%
	       		 		out.println("<input value="+contrato.getSalarioBase()+" name=\"salario\" id=\"salario\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\" readonly/>");
			   		%>
	   			 </div>	  	 
	   		</div>  		   
		</div>
		
	 	<div  class= "form-group" > 
		    <label  for= "valeTransporte" > Desconto Vale Transporte
		    <a href="javascript:void(0)" onclick="informacaoDescontoValeTransporte()"><i class="fa fa-info-circle" title="Informação"></i></a>
	 		</label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	       		 	<%
	       		 		out.println("<input value="+contrato.getValeTransporte()+" name=\"valeTransporte\" id=\"valeTransporte\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\" readonly/>");
			   		%>
	   			 </div>	  	 
	   		</div>  		   
		</div> 
		
		 <div  class= "form-group" > 
		    <label  for= "totalHoraExtra" > Total Hora Extra </label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	       		 	<%
	       		 		out.println("<input value="+valorHoraExtra+" name=\"totalHoraExtra\" id=\"totalHoraExtra\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\" readonly/>");
			   		%>
	   			 </div>	  	 
	   		</div>  		   
		</div> 
		
		<div  class= "form-group" > 
		    <label  for= "descontoBeneficios" > Desconto de Benefícios </label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	       		 	<%
	       		 		out.println("<input value="+contrato.getDescontoBeneficios()+" name=\"descontoBeneficios\" id=\"descontoBeneficios\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\" readonly/>");
			   		%>
	   			 </div>	  	 
	   		</div>  		   
		</div>
		
		<div  class= "form-group" > 
		    <label  for= "inss" > Desconto INSS </label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	       		 	<%
	       		 		out.println("<input value=\""+inss+"\" name=\"inss\" id=\"inss\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\" readonly/>");
			   		%>
	   			 </div>	  	 
	   		</div>  		   
		</div>
		 
		 <div  class= "form-group" > 
		    <label  for= "irrf" > IRRF </label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	       		 	<%
	       		 		out.println("<input value=\""+irrf+"\" name=\"irrf\" id=\"irrf\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\" readonly/>");
			   		%>
	   			 </div>	  	 
	   		</div>  		   
		</div>
		
		<div  class= "form-group" > 
		    <label  for= "inss" > FGTS </label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	       		 	<%
	       		 		out.println("<input value=\""+fgts+"\" name=\"fgts\" id=\"fgts\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\" readonly/>");
			   		%>
	   			 </div>	  	 
	   		</div>  		   
		</div>
		 
		 <div  class= "form-group" > 
		    <label  for= "salario" > Salário Líquido </label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	       		 	<%
	       		 		out.println("<input value=\""+salarioLiquido+"\" name=\"salario\" id=\"salario\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\" readonly/>");
			   		%>
	   			 </div>	  	 
	   		</div>  		   
		</div>
		
	</div>
    </nav>
    
    <div id="botoes">
		<button  type= "submit" name="acao" value="Pagar" onclick="return validar()" class= "btn btn-primário " > Pagar </button> 
		<button  type= "submit" name="acao" value="Imprimir" onclick="return validar()" class= "btn btn-primário " > Imprimir </button> 
		<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " onclick=""> Cancelar </button> 
	</div>
	
 </form>
</div>

<c:import url="rodape.jsp"/>
</body>
</html>