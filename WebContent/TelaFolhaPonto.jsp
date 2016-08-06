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
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" media="all">
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script src="js/jquery-1.2.6.pack 2.js"></script>
<script src="js/jquery.maskedinput-1.1.4.pack 2.js"></script>

<script type="text/javascript">
	$(document).ready(
			
		function() {

		$("#cpf").mask("999.999.999-99");
		$("#data").mask("99/99/9999");
		$("#cnpj").mask("99.999.999/9999-99");
		$("#rg").mask("99.999.999-*");
		$("#cep").mask("99.999-999");
		$("#numeroTelefone").mask("(99)9999-9999");
		$("#horaEntrada").mask("99:99");
		$("#horaSaida").mask("99:99");
		$("#horaSaidaAlmoco").mask("99:99");
		$("#horaVoltaAlmoco").mask("99:99");
		
	});

	function validarCPF( cpf ){
		var filtro = /^\d{3}.\d{3}.\d{3}-\d{2}$/i;
		
		if(!filtro.test(cpf))
		{
			window.alert("CPF inválido. Tente novamente.");
			return false;
		}
	   
		cpf = remove(cpf, ".");
		cpf = remove(cpf, "-");
		
		if(cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" ||
			cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" ||
			cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" ||
			cpf == "88888888888" || cpf == "99999999999")
		{
			window.alert("CPF inválido. Tente novamente.");
			return false;
	   }

		soma = 0;
		for(i = 0; i < 9; i++)
		{
			soma += parseInt(cpf.charAt(i)) * (10 - i);
		}
		
		resto = 11 - (soma % 11);
		if(resto == 10 || resto == 11)
		{
			resto = 0;
		}
		if(resto != parseInt(cpf.charAt(9))){
			window.alert("CPF inválido. Tente novamente.");
			return false;
		}
		
		soma = 0;
		for(i = 0; i < 10; i ++)
		{
			soma += parseInt(cpf.charAt(i)) * (11 - i);
		}
		resto = 11 - (soma % 11);
		if(resto == 10 || resto == 11)
		{
			resto = 0;
		}
		
		if(resto != parseInt(cpf.charAt(10))){
			window.alert("CPF inválido. Tente novamente.");
			return false;
		}
		
		return true;
	 }
	 
	function remove(str, sub) {
		i = str.indexOf(sub);
		r = "";
		if (i == -1) return str;
		{
			r += str.substring(0,i) + remove(str.substring(i + sub.length), sub);
		}
		
		return r;
	}

	/**
	   * MASCARA ( mascara(o,f) e execmascara() ) CRIADAS POR ELCIO LUIZ
	   * elcio.com.br
	   */
	function mascara(o,f){
		v_obj=o
		v_fun=f
		setTimeout("execmascara()",1)
	}

	function execmascara(){
		v_obj.value=v_fun(v_obj.value)
	}

	function cpf_mask(v){
		v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
		v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o terceiro e o quarto dígitos
		v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o setimo e o oitava dígitos
		v=v.replace(/(\d{3})(\d)/,"$1-$2")   //Coloca ponto entre o decimoprimeiro e o decimosegundo dígitos
		return v
	}
	
	function verificarBusca(){
		
		var nomeEmp = document.getElementById('nomeEmpregado').value;
		
		alert("verificarBusca");
		alert(nomeEmp);
		if(nomeEmp == null || nomeEmp == ''){
			alert("E necessario efetuar a busca pelo empregado primeiro.");
		}
	}
	/*ESTA EXIBINDO ESTA MENSAGEM MESMO O CAMPO SEMTA PREENCHIDO! VERIFICAR!!!
	function Enviar(){
		alert("Atividade salva com sucesso!");
	}*/


	
</script>

<body>
	<c:import url="cabecalhoLogado.jsp" />
		<form class="form-horizontal" >
			<fieldset>
				<legend>Folha de ponto</legend>
				
				<%if(request.getAttribute("nome") != null && request.getAttribute("cpf") != null){
					out.println("<input name=\"nomeEmpregado\" id=\"nomeEmpregado\"type=\"hidden\" value=\""+request.getAttribute("nome")+"\"/>");
					out.println("<h5> Nome: " + request.getAttribute("nome")+"</h5>");
					out.println("<h5> CPF: " + request.getAttribute("cpf")+"</h5>");
				}
				if(request.getAttribute("msg") != null){
					out.println("<h5>" + request.getAttribute("msg")+"</h5>");
				}
				
				if(request.getAttribute("naoTemAtividade") != null && request.getAttribute("nome") != null){
					out.println("<h5>" + request.getAttribute("naoTemAtividade")+"</h5>");
				}
				
				if(request.getAttribute("naoEstaCadastrado") != null){
					out.println("<h5>" + request.getAttribute("naoEstaCadastrado")+"</h5>");
				}%>
				
				<!-- RETIREI A OPCAO DE BUSCA PELO NOME POIS NAO CONSEGUI VERIFICAR SE 1 DOS 2 CAMPOS ESTA PREENCHIDO(VALIDACAO) PARA FAZER A BUSCA, 
				
				<div class="form-group">
					<label class="col-md-4 control-label" for="textinput">Nome</label>
					<div class="col-md-4">
						<input name="nome" id="nome" type="text" 
							class="form-control input-md">

					</div>
				</div>
				<div class="form-group">
					<label class="col-md-6 control-label" for="textinput">OU</label>
				</div>-->

				<div class="form-group">
					<label class="col-md-4 control-label" for="textinput">CPF</label>
					<div class="col-md-4">
						<input class="form-control input-md" type="text" name="cpf" id="cpf" onblur="javascript: validarCPF(this.value);" onkeypress="javascript: mascara(this, cpf_mask);"  maxlength="14" />

					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="singlebutton"></label>
					<div class="col-md-4">
					<button type="submit" name="acao" value="Pesquisar"
							class="btn btn-primary">Buscar</button>
					</div>
				</div>
	</fieldset>
</form>

		<c:import url="rodape.jsp" />
</body>
</html>
