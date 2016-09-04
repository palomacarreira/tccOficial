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
<link rel="stylesheet" type="text/css"  href="css/estilo.css" />

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
	function Excluir(titulo){
		alert("Atividade" +titulo+ "excluida com sucesso!");
	}

	
</script>

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

						
							<p style="font-size: 25px;">Atividades</p>

					

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
	<br>
	<div class="container">
	<form name="form1" class="form-inline" method="post" action="AtividadeController" enctype="multipart/form-data">
			
			
	<%out.println("<input name=\"codigoEmpregado\" id=\"codigoEmpregado\"type=\"hidden\" value=\""+request.getAttribute("codigoEmpregado")+"\"/>");
	System.out.println(request.getAttribute("codigoEmpregado"));%>
	<nav class="navbar navbar-default" role="navigation">
	
	<div class="container">

		 <div  class= "form-group" > 
		    <label  for= "titulo" > Titulo
 				<a href="javascript:void(0)" onclick="informacaoValor()"><i class="fa fa-info-circle" title="Informação"></i></a>
			</label>
		    <div class="span3">

		    	<input name="titulo" required="required" id="titulo" type="text"
							class="form-control input-md">
		    </div>
		 </div>
    
    </br></br>
    
		

		  <div  class= "form-group" > 
		    <label  for= "descricao" > Descricao
 				<a href="javascript:void(0)" onclick="informacaoValor()"><i class="fa fa-info-circle" title="Informação"></i></a>
			</label>
		    <div class="span3">

		    	<textarea class="form-control" id="descricao" required="required" name="descricao"></textarea>
		    </div>
		 </div>
    </div>
    </br></br>
    
  </nav>
  <div id="botoes">
  		<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " onclick="history.go(-1)" > Voltar </button> 
   	    <!-- button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " onclick="history.go(-1)" > Cancelar </button>  -->
   	    <button  type= "submit" name="acao" value="Cadastrar" class="btn btn-primary">Adicionar Atividade</button>
   	
	</div>
 </form>

<form>
			<table class="table ls-table" id="tabela1">
<%out.println("<input name=\"cpf\" id=\"cpf\"type=\"hidden\" value=\""+request.getAttribute("cpf")+"\"/>");%>
<%out.println("<input name=\"codigoEmpregado\" id=\"codigoEmpregado\"type=\"hidden\" value=\""+request.getAttribute("codigoEmpregado")+"\"/>");%>
					
					
				<%if(request.getAttribute("combo") != null){
				ArrayList<AtividadesTO> lista = (ArrayList<AtividadesTO>) request.getAttribute("combo");
			
				out.println("<thead>");
				out.println("<tr>");
				
				/*out.println("<th>Codigo da Atividade</th>");*/
				out.println("<th>Título</th>");
				/*out.println("<th>Data</th>");*/
				out.println("<th>Enviado</th>");
				out.println("<th>Enviar</th>");
				out.println("<th>Excluir</th>");
				
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				
			
				for (AtividadesTO item : lista) {
					
				out.println("<form>");
				out.println("<tr>");
				
				String realizado = "vazio";
				if(item.getRealizado() == true){
					realizado = "Sim";
				}else{
					realizado = "Nao";
				}
				
				/*out.println("<td>"+item.getId()+"</td>");*/
				out.println("<input name=\"codigo\" id=\"codigo\"type=\"hidden\" value=\""+item.getId()+"\"/>");
				out.println("<td>"+item.getTitulo()+"</td>");
				/*out.println("<td>"+item.getData()+"</td>");*/
				out.println("<td>"+realizado+"</td>");
				out.println("<td><button type=\"submit\" name=\"acao\" value=\"Enviar\" class=\"btn btn-danger\" > Enviar </button></td>");
		        out.println("<td><button type=\"submit\" name=\"acao\" value=\"Excluir\" class=\"btn btn-danger\" onclick=\"Excluir("+item.getTitulo()+")\"> Excluir </button></td>");
		        		
				out.println("</tr>");
			
				out.println("<form>");
				}
				
				/*}else{
					out.println("<tr>");
					out.println("<td width=\"25%\" height=\"20%\">Funcionários</td>");
					out.println("<td width=\"25%\"><center>Férias</center></td>");
					out.println("<td width=\"25%\"><center>13º</center></td>");
					out.println("<td width=\"25%\"><center>Demitir</center></td>");
					out.println("</tr>");*/
				}%>
			
			
				</tbody>
			</table>
	</form>
</div>
		<c:import url="rodape.jsp" />
</body>
</html>
