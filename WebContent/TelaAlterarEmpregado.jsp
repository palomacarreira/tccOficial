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
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script src="js/jquery-1.2.6.pack 2.js"></script>
<script src="js/jquery.maskedinput-1.1.4.pack 2.js"></script>
<script type="text/javascript" src="js/scriptEmpregado.js"></script>
<script src="jquery.min.js" type="text/javascript"></script>

<title>Alterar Empregado</title>

</head>

<body>			
		
<%
		EmpregadoTO empregado = (EmpregadoTO) request.getAttribute("listaEmpregado");
		EnderecoTO endereco = (EnderecoTO) request.getAttribute("listaEndereco");
		ContatoTO contato = (ContatoTO) request.getAttribute("listaContato");
		ContratoTO contrato = (ContratoTO) request.getAttribute("listaContrato");
		String foto = (String) request.getAttribute("Foto");
  %>  
  	
<% String[] estados = {"AC", "AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PE","PI",
     			"PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};
	String[] estadoCivil = {"Solteiro","Casado","Viúvo","Separado","Divorciado"};
	String[] diaPagamento = {"1º dia util","2º dia util","3º dia util","4º dia util","5º dia util"};
%>
     	
<c:import url="cabecalhoDeslogado.jsp"/>
<div class="container">

<form name="form1" class= "form-inline" method="post" action="CadastrarEmpregado">
<nav class="navbar navbar-default" role="navigation">
<div class="container">

	<label  for= "dadosEmpregado" > Dados Empregado </label></br> </br>
		  <div  class= "form-group" > 
		    <label  for= "nome" > Nome </label> 
		    <div class="span3">
		      <%
		     out.println("<input type=\"text\" value="+empregado.getNome()+" class=\"form-control\"  name=\"nome\" id=\"nome\" size=\"80\">");
		 	%>
		    </div>
		    
		    <div id="foto">
	<%
	out.println("<img id=\"myimage\" height=\"200\" class='imagem_artigo'src=\"data:image/png;base64,"+foto+"\" alt=\"IMG DESC\">");	
	out.println("<input type=\"file\" id=\"fotoEmpregado\" name=\"fotoEmpregado\" size=\"24\" onchange=\"onFileSelected(event)\" />");

	%>
	</div>
      
      
		  </div> 
		  <div  class= "form-group" > 
		    <label  for= "sobrenome" > Sobrenome </label> 
		    <div class="span3">
		     <%
		     out.println("<input type=\"text\" value="+empregado.getSobrenome()+" class=\"form-control\"  name=\"sobrenome\" id=\"sobrenome\" size=\"80\">");
		 	%>
		    </div>
		  </div> 
		 <div  class= "form-group" > 
		    <label  for= "dataNasc" > Data Nascimento </label> 
		    <div class="span3">
		     <%
		     out.println("<input type=\"date\" value="+empregado.getDataNasc()+" class=\"form-control\"  id=\"dataNasc\" name=\"dataNasc\" size=\"20\">");
		 	%>
		    </div>
		 </div>
		 
	 <div  class= "radio form-group" > 
  		<div class="span3">
		   <%
		   if(empregado.getSexo().equals("F")){
			   out.println("<input type=\"radio\"  name=\"sexo\"  id=\"radioFem\"  value=\"F\" checked>Fem");
		   }else{
			   out.println("<input type=\"radio\"  name=\"sexo\"  id=\"radioFem\"  value=\"F\" >Fem");
		   }
  		   if(empregado.getSexo().equals("F")){
  			   out.println("<input type=\"radio\"  name=\"sexo\"  id=\"radioMasc\"  value=\"M\" checked>Masc");
  		   }else{
  			 out.println("<input type=\"radio\"  name=\"sexo\"  id=\"radioMasc\"  value=\"M\">Masc");
  		   }
		   %>
  		   </div>
		</div> 
		
		<div  class= "form-group" > 
		    <label  for= "numDependentes" > Número de Dependentes </label> 
		    <div class="span3">
		    <%
		     out.println("<input type=\"text\" value="+empregado.getDataNasc()+" id=\"qtdDependentes\" name=\"qtdDependentes\" class=\"form-control\" size=\"28\">");
		 	%>
		    </div>
		 </div> 

		 
		  <div>
     		 <label  for = "select" >Estado Civil</label> 
     		 <div class="span3">
     		
			<%
		out.println("<select class=\"form-control\" name=\"estadoCivil\" id=\"estadoCivil\">");
     	for (int i = 0; i < estadoCivil.length; i++) {
     		if(estadoCivil[i].equals(empregado.getEstadoCivil())){
     			out.println("<option value="+ estadoCivil[i]+" style =\"width: 100px; height: 100px\" selected>"+ estadoCivil[i] +"</option>");	
     		}
     		else{
     			out.println("<option value="+ estadoCivil[i] +" style =\"width: 100px; height: 100px\">"+ estadoCivil[i] +"</option>");	
     		}
     	}
		out.println("</select>");
    	%>
      		</div>
    	</div> 
		 
		 
		<div  class= "form-group" > 
		    <label  for= "cpf" > CPF </label> 
		    <div class="span3">
		    <%
		      out.println("<input type=\"text\" value="+empregado.getCpf()+" id=\"cpf\" name=\"cpf\" class=\"form-control\" size=\"28\">");
		 	%>
		    </div>
		 </div> 
		
		 <div  class= "form-group" > 
		    <label  for= "rg" > RG </label> 
		    <div class="span3">
		     <%
		      out.println("<input type=\"text\" value="+empregado.getRg()+" name=\"rg\" id=\"rg\" class=\"form-control\" size=\"30\">");
		 	%>
		    </div>
		 </div>
		 
		 <div>
     		 <label  for = "select" >UF RG</label> 
     		 <div class="span3">
			<%
		out.println("<select class=\"form-control\" name=\"ufRg\" id=\"ufRg\">");
     	for (int i = 0; i < estados.length; i++) {
     		if(estados[i].equals(empregado.getUfRg())){
     			out.println("<option value="+ estados[i]+" style =\"width: 100px; height: 100px\" selected>"+ estados[i] +"</option>");	
     		}
     		else{
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
     		}
     	}
		out.println("</select>");
    	%>
      		</div>
    	</div> 
    	
    	<div  class= "form-group" > 
		    <label  for= "numCarteira" > Nº Carteira de Trabalho </label> 
		    <div class="span3">
		    <%
		      out.println("<input type=\"text\" value="+empregado.getNumCarteira()+" name=\"numCarteira\" id=\"numCarteira\" class=\"form-control\" size=\"30\">");
		 	%>
		    </div>
		 </div>
		 
		 <div  class= "form-group" > 
		    <label  for= "serieCarteira" > Série </label> 
		    <div class="span3">
		    <%
		      out.println("<input type=\"text\" value="+empregado.getSerieCarteira()+" name=\"serieCarteira\" id=\"serieCarteira\" class=\"form-control\" size=\"30\">");
		 	%>
		    </div>
		 </div>
		 
		  <div>
     		 <label  for = "select" >UF Carteira</label> 
     		 <div class="span3">
		<%
		out.println("<select class=\"form-control\" name=\"ufRg\" id=\"ufRg\">");
     	for (int i = 0; i < estados.length; i++) {
     		if(estados[i].equals(empregado.getUfCarteira())){
     			out.println("<option value="+ estados[i]+" style =\"width: 100px; height: 100px\" selected>"+ estados[i] +"</option>");	
     		}
     		else{
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
     		}
     	}
		out.println("</select>");
    	%>
      	</div>
    	</div> 
<br>
</nav>
		 
		 
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
    	<label  for= "dadosEndereco" > Dados endereço </label></br> </br>
    	<div  class= "form-group" > 
			<label  for= "cep" > CEP </label>
			<div class="span3"> 
			<%
				out.println("<input type=\"text\" value="+endereco.getCep()+" class=\"form-control\"  name=\"cep\" id=\"cep\" size=\"25\">");
			%>
			</div>
    	</div> 
		<div  class= "form-group" > 
			<label  for= "logadouro" > Endereco </label> 
			<div class="span3">
				<%
					out.println("<input type=\"text\" value="+endereco.getEndereco()+" class=\"form-control\" name=\"endereco\" id=\"endereco\" size=\"25\">");
				%>
			</div>
		</div> 
		<div  class= "form-group" > 
		<label  for= "numero" > Numero </label> 
		    <div class="span3">
		    	<%
		      		out.println("<input type=\"text\" value="+endereco.getNumero()+" class=\"form-control\"  name=\"numeroEndereco\" id=\"numeroEndereco\" size=\"25\">");
		 		%>
		    </div>
		</div>
		<div  class= "form-group" > 
		    <label  for= "complemento" > Complemento </label> 
		    <div class="span3">
		    	<%
		      		out.println("<input type=\"text\" value="+endereco.getComplemento()+" class=\"form-control\"  name=\"complemento\" id=\"complemento\" size=\"10\">");
		 		%>
		    </div>
		</div>
		<div  class= "form-group" > 
		    <label  for= "bairro" > Bairro </label> 
		    <div class="span3">
		    	<%
		      		out.println("<input type=\"text\" value="+endereco.getBairro()+" class=\"form-control\" name=\"bairro\" id=\"bairro\" size=\"10\">");
		 		%>
		    </div>
		</div>
		<div  class= "form-group " > 
			<label  for= "cidade" > Cidade </label> 
			<div class="span3">
		    	<%
		      		out.println("<input type=\"text\" value="+endereco.getCidade()+" class=\"form-control\" name=\"cidade\" id=\"cidade\" size=\"10\">");
		 		%> 
			</div>
	   	</div>
		<div>
        	<label  for = "select" >Estado</label> 
     		<div class="span3">
				<%
					out.println("<select class=\"form-control\" name=\"ufRg\" id=\"ufRg\">");
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
    	</div> 
 	</div>
 <br>
 </nav>
      
 <nav class="navbar navbar-default" role="navigation">
    	<div class="container">
        <label  for= "contratoTrabalho" > Contrato de Trabalho </label></br> </br>
        <div  class= "form-group" > 
		    <label  for= "dataAdmissao" > Data de Admissão </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"date\" value="+contrato.getDataAdmissao()+" id=\"dataAdmissao\" name=\"dataAdmissao\" class=\"form-control\" size=\"20\">");
		   	%>
		    </div>
		 </div>
		 
		 
	   <div  class= "form-group" > 
		    <label  for= "cargo" > Cargo </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"text\" value="+contrato.getCargo()+" class=\"form-control\" name=\"cargo\" id=\"cargo\" size=\"20\">");
		   	%>
		    </div>
		</div>

		<div  class= "form-group" > 
		    <label  for= "salarioBase" > Salário Base </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"text\" value="+contrato.getSalarioBase()+" class=\"form-control\" name=\"salarioBase\" id=\"salarioBase\" size=\"20\">");
		   	%>
		    </div>
		</div>
		<br>
	
		<div  class= "form-group" > 
		    <label  for= "cargo" > Valor vale transporte </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"text\" value="+contrato.getValeTransporte()+" class=\"form-control\" name=\"valeTransporte\" id=\"valeTransporte\" size=\"20\">");
		   	%>
		    </div>
		</div>
		
		 <div>
     		 <label  for= "dataPagamento" > Dia de Pagamento</label> 
     		 <div class="span3">
			<%
		out.println("<select class=\"form-control\" name=\"diaPagamento\" id=\"diaPagamento\">");
     	for (int i = 0; i < diaPagamento.length; i++) {
     		if(diaPagamento[i].equals(contrato.getDiaPagamento())){
     			out.println("<option value="+ diaPagamento[i]+" style =\"width: 100px; height: 100px\" selected>"+ diaPagamento[i] +"</option>");	
     		}
     		else{
     			out.println("<option value="+ diaPagamento[i] +" style =\"width: 100px; height: 100px\">"+ diaPagamento[i] +"</option>");	
     		}
     	}
		out.println("</select>");
    	%>
      		</div>
    		</div> 
	
		 <br>
    	
    	
		  <div>
     		 <label  for= "regimeContrato" > Regime de Trabalho</label> 
     		 <div class="span3">
		    <%
		   if(contrato.getRegimeDeTrabalho().equals("Tempo Integral")){
			   out.println("<input  type=\"radio\" onchange=\"doalert(this)\"  name=\"regimeTrabalho\"  id=\"regime44\"  value=\"Tempo Integral\" checked >Tempo Integral(44hrs semanais)");
		   }else{
			   out.println("<input  type=\"radio\"  onchange=\"doalert(this)\" name=\"regimeTrabalho\"  id=\"regime44\"  value=\"Tempo Integral\">Tempo Integral(44hrs semanais)");
			}
  			out.println("</br>");
  		   if(contrato.getRegimeDeTrabalho().equals("Tempo Parcial")){
  			   out.println("<input type=\"radio\" onchange=\"doalert(this)\" name=\"regimeTrabalho\"  id=\"regime25\"  value=\"Tempo Parcial\" checked> Tempo Parcial(25hrs semanais)");
  		   }else{
  			 out.println("<input type=\"radio\" onchange=\"doalert(this)\" name=\"regimeTrabalho\"  id=\"regime25\"  value=\"Tempo Parcial\"> Tempo Parcial(25hrs semanais)");
  		   }
  		 	out.println("</br>");
		   %>	
      		</div>
    		</div> 
    <br>
    
     <div  class= "form-group" > 
		    <label  for= "compensacaoDias" > Compensação de Dias de Trabalho </label> 
  		<div class="span3">
  		<select class="form-control" name="compensacaoDias" id="compensacaoDias">
  			  <%
		   if(contrato.getCompensacaoDias().equals("salario")){
			   out.println("<option value=\"salario\" style =\"width: 100px; height: 100px\" selected> Acréscimo de salário </option>");
		   }else{
			   out.println("<option value=\"salario\" style =\"width: 100px; height: 100px\" > Acréscimo de salário </option>");
		   }
  		   if(contrato.getCompensacaoDias().equals("horas")){
  			   out.println("<option value=\"horas\" style =\"width: 100px; height: 100px\" selected>Regime de compensação de horas</option>");
  		   }else{
  			   out.println("<option value=\"horas\" style =\"width: 100px; height: 100px\">Regime de compensação de horas</option>");
  		   }
		   %>	
		   </select>
  			</div>
		</div> 
		
		
		 <div  class= "form-group" > 
		    <label  for= "serieCarteira" > Banco </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"text\" value="+contrato.getBanco()+" name=\"banco\" id=\"banco\" class=\"form-control\" size=\"30\">");
		   	%>
		    </div>
		 </div>
		 
		 <div  class= "form-group" > 
		    <label  for= "agencia" > Agência </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"text\" value="+contrato.getAgencia()+" name=\"agencia\" id=\"agencia\" class=\"form-control\" size=\"30\">");
		   	%>
		    </div>
		 </div>
		  
		 <div  class= "form-group" > 
		    <label  for= "agencia" > Conta </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"text\" value="+contrato.getConta()+" name=\"conta\" id=\"conta\" class=\"form-control\" size=\"30\">");
		   	%>
		    </div>
		 </div>
		 
		   <div >
     		 <label  for = "tipoConta" >Tipo de Conta</label> 
     		 <div class="span3">
 				<select class="form-control" name="tipoConta" id="tipoConta">
 				<%
		   if(contrato.getTipoConta().equals("Poupança")){
			   out.println("<option value=\"Poupança\" style=\"width: 100px; height: 100px\" selected>Poupança</option>");
		   }else{
			   out.println("<option value=\"Poupança\" style=\"width: 100px; height: 100px\">Poupança</option>");
			}
  			out.println("</br>");
  		   if(contrato.getTipoConta().equals("Corrente")){
  			   out.println("<option value=\"Corrente\" style=\"width: 100px; height: 100px\" selected>Conta Corrente</option>");
  		   }else{
  			   out.println("<option value=\"Corrente\" style=\"width: 100px; height: 100px\">Conta Corrente</option>");
		   }
  		 	out.println("</br>");
		   %>	
				</select>
      		</div>
    		</div> 
    		</div>
		  </br>
     </br>
     
     
 <div class="container">
 <label  for= "periodoDeTrabalho" > Confira os horários de trabalho </label></br> </br>
 <span> Ajuste o horário desde que não passe de 44 horas na semana ou 25 horas em regime de tempo parcial </span>
 </br> 
 <span> *HORÁRIO EM FORMATO 24Hrs </span>
 </br> 
 </br>
  
  <table class="tableHorarios ">
  <tbody>
  <tr>
  <td>Semana</td><td>Seg</td><td> </td><td>Ter</td><td>Qua</td><td>Qui</td><td>Sex</td><td>Sáb</td><td>Dom</td>
  </tr> 
  <tr>
  <td>Entrada</td>
  <td>
  <input class="form-control" name="horaEntrada1" id="horaEntrada1" size="5">
  </td>
  <td>
  <img src="imagens/botao_repete.ico" class="imgPreenche" id="1" style="cursor: pointer" height="20">
  </td>
  <td>
  <input type="text" name="horaEntrada2" id="horaEntrada2" class="form-control" size="5" >
  </td>
  <td>
  <input type="text" name="horaEntrada3" id="horaEntrada3" class="form-control" size="5" >
  </td> 
  <td>
 	<input type="text" name="horaEntrada4" id="horaEntrada4" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaEntrada5" id="horaEntrada5" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text"  name="horaEntrada6" id="horaEntrada6" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaEntrada7" id="horaEntrada7" class="form-control" size="5" >
  </td>
  </tr>
  
  <tr>
  <td>Saída Almoço</td>
  <td>
  <input  type="text"  name="horaSaidaAlmoco1" class="form-control" name="hora" id="horaSaidaAlmoco1" size="5">
  </td>
  <td>
  <img src="imagens/botao_repete.ico" class="imgPreenche" id="2" style="cursor: pointer" height="20">
  </td>
  <td>
  <input type="text" name="horaSaidaAlmoco2" id="horaSaidaAlmoco2" class="form-control" size="5" >
  </td>
  <td>
  <input type="text" name="horaSaidaAlmoco3" id="horaSaidaAlmoco3" class="form-control" size="5" >
  </td> 
  <td>
 	<input type="text"  name="horaSaidaAlmoco4" id="horaSaidaAlmoco4"  class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaSaidaAlmoco5"  id="horaSaidaAlmoco5" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaSaidaAlmoco6" id="horaSaidaAlmoco6" class="form-control" size="5">
  </td>
    <td>
 	<input type="text" name="horaSaidaAlmoco7" id="horaSaidaAlmoco7" class="form-control" size="5">
  </td>
  </tr>
  
  <tr>
  <td>Volta Almoço</td>
  <td>
  <input type="text"  name="horaVoltaAlmoco1" class="form-control" id="horaVoltaAlmoco1" size="5">
  </td>
  <td>
  <img src="imagens/botao_repete.ico" class="imgPreenche" id="3" style="cursor: pointer" height="20">
  </td>
  <td>
  <input type="text"  name="horaVoltaAlmoco2" id="horaVoltaAlmoco2" class="form-control" size="5" >
  </td>
  <td>
  <input type="text" name="horaVoltaAlmoco3" id="horaVoltaAlmoco3"  class="form-control" size="5" >
  </td> 
  <td>
 	<input type="text" name="horaVoltaAlmoco4" id="horaVoltaAlmoco4" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaVoltaAlmoco5" id="horaVoltaAlmoco5" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaVoltaAlmoco6"  id="horaVoltaAlmoco6" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text"  name="horaVoltaAlmoco7" id="horaVoltaAlmoco7" class="form-control" size="5">
  </td>
  </tr>
  
  <tr>
  <td>Saída</td>   
  <td>
  <input type="text" name="horaSaida1" class="form-control" name="hora" id="horaSaida1" size="5">
  </td>
  <td>
  <img src="imagens/botao_repete.ico" class="imgPreenche" id="4" style="cursor: pointer" height="20">
  </td>
  <td>
  <input type="text" name="horaSaida2" class="form-control" id="horaSaida2" class="form-control" size="5" >
  </td>
  <td>
  <input type="text"  name="horaSaida3" class="form-control" id="horaSaida3" class="form-control" size="5" >
  </td> 
  <td>
 	<input type="text" name="horaSaida4" class="form-control" id="horaSaida4" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaSaida5" class="form-control" id="horaSaida5" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaSaida6" class="form-control" id="horaSaida6" class="form-control" size="5" >
  </td>
    <td>
 	<input type="text" name="horaSaida7" class="form-control" id="horaSaida7" class="form-control" size="5" >
  </td>
  </tr>
  <tr>
  <td> Dia de Folga </td>
  <td>
	<input name="diaFolga1" id="diaFolga1" class="checkbox" type="checkbox" onchange="alertaDiaFolga(this)" />
  </td>
  <td> </td>
  <td>
	<input name="diaFolga2" id="diaFolga2" class="checkbox" type="checkbox" onchange="alertaDiaFolga(this)" />
  </td>
  <td>
 	<input name="diaFolga3" id="diaFolga3" class="checkbox" type="checkbox" onchange="alertaDiaFolga(this)" />
  </td>
  <td>
 <input name="diaFolga4" id="diaFolga4" class="checkbox" type="checkbox" onchange="alertaDiaFolga(this)" />
  </td>
  <td>
  <input name="diaFolga5" id="diaFolga5" class="checkbox" type="checkbox" onchange="alertaDiaFolga(this)" />
  </td>
  <td>
  <input name="diaFolga6" id="diaFolga6" class="checkbox" type="checkbox" onchange="alertaDiaFolga(this)" />
  </td>
  <td>
	<input name="diaFolga7" id="diaFolga7" class="checkbox" type="checkbox" onchange="alertaDiaFolga(this)" />
  </td>
  </tr>
  <tr>
  
  
  <tr>
  <td> Meio período </td>
  <td>
	<input name="diaMeioPeriodo1" id="diaMeioPeriodo1" type="checkbox" onchange="alertaMeioPeriodo(this)" />
  </td>
  <td> </td>
  <td>
	<input name="diaMeioPeriodo2" id="diaMeioPeriodo2" type="checkbox" onchange="alertaMeioPeriodo(this)" />
  </td>
  <td>
 	<input name="diaMeioPeriodo3" id="diaMeioPeriodo3" type="checkbox" onchange="alertaMeioPeriodo(this)" />
  </td>
  <td>
 <input name="diaMeioPeriodo4" id="diaMeioPeriodo4" type="checkbox" onchange="alertaMeioPeriodo(this)" />
  </td>
  <td>
  <input name="diaMeioPeriodo5" id="diaMeioPeriodo5" type="checkbox" onchange="alertaMeioPeriodo(this)" />
  </td>
  <td>
  <input name="diaMeioPeriodo6" id="diaMeioPeriodo6" type="checkbox" onchange="alertaMeioPeriodo(this)" />
  </td>
  <td>
	<input name="diaMeioPeriodo7" id="diaMeioPeriodo7" type="checkbox" onchange="alertaMeioPeriodo(this)"/>
  </td>

  </tr>
  
 </tbody>
  </table>
  </div>
  
 <br/> 
 <br/>
  
 <br/> 
 <br/>
 	<label for="totalHoras">Total de horas na semana: </label><span id="totalHoras">--</span>
 	 <br>
	<span style="color:red; font-weight:bold;" id="saldoHoras"></span> 

    </div>
     <br/> 
 </nav>
 
    	<nav class="navbar navbar-default" role="navigation">
    <div class="container">
         <label  for= "TipoContato" > Contato </label></br> </br>
       <div  class= "form-group " > 
		    <label  for= "tipoContato" > Tipo Contato </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"text\" value="+contato.getTipoContato()+" class=\"form-control\" name=\"tipoContato\" id=\"tipoContato\" size=\"20\">");
		   	%>
		    </div>
	   </div>
	   <div  class= "form-group" > 
		    <label  for= "numeroTelefone" > Número </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"text\" value="+contato.getNumero()+" class=\"form-control\" name=\"numeroTelefone\" id=\"numeroTelefone\" size=\"20\">");
		   	%>
		    </div>
		</div>
      <br>
    </div>
    <br>
    
      </nav>
    
    <nav class="navbar navbar-default" role="navigation">
    	<div class="container">

       <label  for= "email" > Login </label></br> </br>
      
       <div  class= "form-group " > 
		    <label  for= "email" > E-mail </label> 
		    <div class="span3">
		    <%
		    out.println("<input value="+empregado.getEmail()+" class=\"form-control\" id=\"email\"  placeholder=\"E-mail\" name=\"email\" type=\"email\">");
		   	%>
		    </div>
	   </div>
	   <div  class= "form-group" > 
		    <label  for= "senha" > Senha </label> 
		    <div class="span3">
		    <%
		    	out.println("<input type=\"password\" value="+empregado.getSenha()+" class=\"form-control\" name=\"senha\" id=\"senha\" size=\"10\">");
		   	%>
		    </div>
		</div>
		

    </div>
     <br>
     
    </nav>
    
     <div id="botoes">
		<button  type= "submit" name="acao" value="Cadastrar" class= "btn btn-primário " > Salvar </button> 
		<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " > Cancelar </button> 
	</div>
  </form>

</div>
<c:import url="rodape.jsp"/>

<script type="text/javascript">
window.onload = function(){
	  <%
	   ArrayList<JornadaTrabalhoTO> listaJornada = (ArrayList<JornadaTrabalhoTO>)request.getAttribute("listaJornada");
	   for(int x = 0; x < listaJornada.size(); x++){
		   JornadaTrabalhoTO lista = listaJornada.get(x);
		   %>
		    var id = "<%=lista.getDiaSemana()%>";

		    document.getElementById("horaSaida" + id).value="<%=lista.getHoraSaida()%>";
			document.getElementById("horaEntrada"+ id).value="<%=lista.getHoraEntrada()%>";
			document.getElementById("horaSaidaAlmoco"+ id).value="<%=lista.getHoraSaidaAlmoco()%>";
			document.getElementById("horaVoltaAlmoco"+ id).value="<%=lista.getHoraVoltaAlmoco()%>";
			
			if(<%=lista.getDiaFolga()%> == true){
				document.getElementById("horaSaida" + id).disabled=true;
				document.getElementById("horaEntrada"+ id).disabled=true;
		  		document.getElementById("horaSaidaAlmoco"+ id).disabled=true;
		  		document.getElementById("horaVoltaAlmoco"+ id).disabled=true;
		  		document.getElementById("diaFolga" + id).checked=true;
		  		document.getElementById("diaMeioPeriodo" + id).disabled=true; 
			   }
			else if(<%=lista.getDiaMeioPeriodo()%> == true)
			   {
				   document.getElementById("horaSaidaAlmoco"+ id).disabled=true;
				   document.getElementById("horaVoltaAlmoco"+ id).disabled=true; 	
			   	document.getElementById("diaMeioPeriodo" + id).checked=true;
			   	document.getElementById("diaFolga" + id).disabled=true; 
			   	
			   }
	<% } %>        
}
</script>

</body>
</html>