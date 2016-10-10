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
<script src="jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/sweetalert.min.js"></script> 
<link rel="stylesheet" href="css/font-awesome.min.css">
<script type="text/javascript" src="js/scriptFolhaPonto.js"></script>

<title>Ponto</title>

</head>
<body>

<%
	String[] meses = {"","Janeiro", "Fevereiro","Março","Abril","Maio","Junho",
			"Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	ArrayList<PontoTO> listaDePonto = (ArrayList<PontoTO>) request.getAttribute("listaPonto");
	ArrayList<JornadaTrabalhoTO> listaJornada = (ArrayList<JornadaTrabalhoTO>) request.getAttribute("listaJornadaTrabalho");
	String codigoEmpregado = (String) request.getAttribute("codigoEmpregado");
	String dataAdmissao = (String) request.getAttribute("dataAdmissao");
	int anoAdmissao = Integer.parseInt(dataAdmissao.substring(0,4));
	Calendar calendar = new GregorianCalendar();
	int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
	int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
	int anoEscolhido = (int) request.getAttribute("anoEscolhido");
	int mesEscolhido = (int) request.getAttribute("mesEscolhido");
	int diasDoMes = (int)request.getAttribute("totalDias");
%>  
    <div class="container">
    
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
        <div class="col-md-5">	
			<p style="font-size: 25px;">Folha de Ponto</p>
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
	<br>
	
	<label>
	A hora de saída é aceita contando 24horas, ou seja, o empregado pode ser cadastrado com ponto de entrada
	das 9 horas da manhã e saída as 9 horas da manhã.
	Sendo que, o horário noturno é aquele que é exercido das 22:00 de um dia às 05:00 do dia seguinte, demais
	horas são consideradas horas do mesmo dia, ultrapassando a jornada do dia é considerado hora extra. 
	* DIA DE DSR = Dia de Descanso Semanal Remunerado
	</label>
	
	<form id="dadosPonto" role="form" class="form-inline" method="post" action ="CadastrarPonto?codigoEmpregado=<%=codigoEmpregado %>&totalDeDias=<%=diasDoMes%>">
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
		
	<div id="itensFolhaPonto">
	   <table id="tableFolhaPonto" class="table table-striped table-bordered" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>Dia</th>
	                <th>Entrada</th>
	                <th>Saida Almoço</th>
	                <th>Retorno Almoço</th>
	                <th>Saída</th>
	                <th>Ação</th>
	            </tr>
	        </thead>
	        <tfoot>
	            <tr>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	            </tr>
	        </tfoot>
	        <tbody>
	  
	        </tbody>
	    </table>
	</div>
	   
    <div id="botoes">
		<button  type= "submit" name="acao" value="Cadastrar" onclick="return validar()" class= "btn btn-primário" > Salvar </button> 
		<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário" onclick="history.go(-1)"> Cancelar </button> 
	</div>
 </form>
 
</div>
<script type="text/javascript">
window.onload = function(){
  <% 
	 for( int x = 1; x <= diasDoMes ; x++)
	 {
			%>
			var diaSem ;
			var diaSemana ;
			diaSem = diaDaSemana(<%=x%>,<%=mesEscolhido%>, <%=anoEscolhido%>);
			if(diaSem == 0){diaSemana="DOM";}
			else if(diaSem == 1){diaSemana="SEG";}
			else if(diaSem == 2){diaSemana="TER";}
			else if(diaSem == 3){diaSemana="QUA";}
			else if(diaSem == 4){diaSemana="QUI";}
			else if(diaSem == 5){diaSemana="SEX";}
			else if(diaSem == 6){diaSemana = "SAB";}	
		    
			
			var newRow = $('<tr id=\"linha'+<%=x%>+'\">');
		    var cols = "";

		    cols += '<td><p id=\"dia'+ <%=x%> +'\" name=\"'+diaSemana+'\">'+ <%=x%> + '<br>' +diaSemana+'</p></td>';
		    cols += '<td><input type= "text" class="hora form-control" name="horaEntrada'+ <%=x%> +'" id="horaEntrada'+ <%=x%> +'" size="5"></td>';
		    cols += '<td><input type= "text" class="hora form-control" name="horaSaidaAlmoco'+ <%=x%> +'" id="horaSaidaAlmoco'+ <%=x%> +'"size="5"></td>';
		    cols += '<td><input type= "text" class="hora form-control" name="horaVoltaAlmoco'+ <%=x%> +'" id="horaVoltaAlmoco'+ <%=x%> +'" size="5"></td>';
		    cols += '<td><input type= "text" class="hora form-control" name="horaSaida'+ <%=x%> +'" id="horaSaida'+ <%=x%> +'" size="5"></td>';
		    cols += '<td>';
		    cols += '</td>';
		    newRow.append(cols);
		    $("#tableFolhaPonto").append(newRow);
		   
			var na = document.getElementById("dia"+<%=x%>).innerHTML;
			var tamanho = na.length;
			if(tamanho == 8){
				nameDia = na.substring(5,8);
			}
			else{
				nameDia = na.substring(6,9);
			}
				
			if(nameDia == "SEG")
			{
				<%boolean diaNaoTrabalhado = listaJornada.get(0).getDiaSemTrabalho();%> 
				var verifica = desabilitaCampos(<%=x%>, <%=diaNaoTrabalhado%>);
				if(verifica == true){
					adicionaColunaEspecial(<%=x%>);
				}else{
					if(<%=listaJornada.get(0).getDiaFolga()%> == true){
					adicionaColuna(<%=x%>);}
					else{ adicionaColunaNormal(<%=x%>);}
				}
			}
			else if(nameDia == "TER")
			{
				<%diaNaoTrabalhado = listaJornada.get(1).getDiaSemTrabalho();%>
				var verifica = desabilitaCampos(<%=x%>, <%=diaNaoTrabalhado%>);
				if(verifica == true){
					adicionaColunaEspecial(<%=x%>);
				}else{
					if(<%=listaJornada.get(1).getDiaFolga()%> == true){
					adicionaColuna(<%=x%>);}
					else{ adicionaColunaNormal(<%=x%>);}
				}
			}
			else if(nameDia == "QUA")
			{
				<%diaNaoTrabalhado = listaJornada.get(2).getDiaSemTrabalho();%>
				var verifica = desabilitaCampos(<%=x%>, <%=diaNaoTrabalhado%>);
				if(verifica == true){
					adicionaColunaEspecial(<%=x%>);
				}else{
					if(<%=listaJornada.get(2).getDiaFolga()%> == true){
					adicionaColuna(<%=x%>);}
					else{ adicionaColunaNormal(<%=x%>);}
				}
			}
			else if(nameDia == "QUI")
			{
				<%diaNaoTrabalhado = listaJornada.get(3).getDiaSemTrabalho();%>
				var verifica = desabilitaCampos(<%=x%>, <%=diaNaoTrabalhado%>);
				if(verifica == true){
					adicionaColunaEspecial(<%=x%>);
				}else{
					if(<%=listaJornada.get(3).getDiaFolga()%> == true){
					adicionaColuna(<%=x%>);}
					else{ adicionaColunaNormal(<%=x%>);}
				}
			}
			else if(nameDia == "SEX")
			{
				<%diaNaoTrabalhado = listaJornada.get(4).getDiaSemTrabalho();%>
				var verifica = desabilitaCampos(<%=x%>, <%=diaNaoTrabalhado%>);
				if(verifica == true){
					adicionaColunaEspecial(<%=x%>);
				}else{
					if(<%=listaJornada.get(4).getDiaFolga()%> == true){
					adicionaColuna(<%=x%>);}
					else{ adicionaColunaNormal(<%=x%>);}
				}
			}
			else if(nameDia == "SAB")
			{
				<%diaNaoTrabalhado = listaJornada.get(5).getDiaSemTrabalho();%>
				var verifica = desabilitaCampos(<%=x%>, <%=diaNaoTrabalhado%>);
				if(verifica == true){
					adicionaColunaEspecial(<%=x%>);
				}else{
					if(<%=listaJornada.get(5).getDiaFolga()%> == true){
					adicionaColuna(<%=x%>);}
					else{ adicionaColunaNormal(<%=x%>);}
				}
			}	
			else if(nameDia == "DOM")
			{
				<%diaNaoTrabalhado = listaJornada.get(6).getDiaSemTrabalho();%>
				var verifica = desabilitaCampos(<%=x%>, <%=diaNaoTrabalhado%>);
				if(verifica == true){
					adicionaColunaEspecial(<%=x%>);
				}else{
					if(<%=listaJornada.get(6).getDiaFolga()%> == true){
					adicionaColuna(<%=x%>);}
					else{ adicionaColunaNormal(<%=x%>);}
				}
			}
		   <%
		}
	%>
}(jQuery);

function desabilitaCampos(x,diaNaoTrabalhado)
{
	if(diaNaoTrabalhado){
		document.getElementById("horaEntrada"+x).disabled = true;
		document.getElementById("horaSaidaAlmoco"+x).disabled = true;
		document.getElementById("horaVoltaAlmoco"+x).disabled = true;
		document.getElementById("horaSaida"+x).disabled = true;
		return true;
	}	
	return false;
}

function adicionaColunaEspecial(dia)
{
	$('#linha'+dia).children('td:last').append($(
			'<div class=\"form-group\">'+
			'<select class=\"form-control\" id=\"combo_sem_trabalho'+ dia +'\" name=\"acaoSelecionada'+ dia +'\" Readonly>'+
			'<option value=\"Sem Jornada\">Sem Jornada</option>'+
			'</select>'  +
			'</div>'
	));
}

function adicionaColuna(dia)
{
		$('#linha'+dia).children('td:last').append($(
			'<div class=\"form-group\">'+
			'<select class=\"form-control\" id=\"combo_acao'+ dia +'\" name=\"acaoSelecionada'+ dia +'\">'+
			'<option value=\"Dia de DSR\">Dia de DSR</option>'+
			'<option value=\"Trabalhou na DSR\">Trabalhou na DSR</option>'+
			'</select>'  +
			'</div>'
		));
}

function adicionaColunaNormal(dia)
{
	   	$('#linha'+dia).children('td:last').append($(
		'<div class=\"form-group\">'+
	    '<select class=\"form-control\" id=\"combo_acao\" name=\"acaoSelecionada'+ dia +'\">'+
	    '<option value=\"Dia Comum\">Dia Comum</option>'+
	    '<option value=\"Falta\">Falta</option>'+
		'<option value=\"Falta Justificada\">Falta Justificada</option>'+
	    '<option value=\"Feriado\">Feriado</option>'+
	    '<option value=\"Feriado Trabalhado\">Feriado Trabalhado</option>'+
	    '<option value=\"Atraso Justificado\">Atraso Justificado</option>'+
	    '</select>'+
	    '</div>'
		));
}
</script>	

<script>
	function funcaoData() 
	{
		var valor_mes = document.getElementById("combo_mes").value;
		var valor_ano = document.getElementById("combo_ano").value;
		location.href="PesquisarPonto?acao=CadastrarAlterar&codigoEmpregado=<%=codigoEmpregado%>&mes="+valor_mes+"&ano="+valor_ano+""; 
	}
</script>

<c:import url="rodape.jsp"/>
</body>
</html>