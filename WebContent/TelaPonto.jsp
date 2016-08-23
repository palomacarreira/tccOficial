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
<script type="text/javascript" src="js/scriptFolhaPonto.js"></script>
<script src="jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/sweetalert.min.js"></script> 
<link rel="stylesheet" href="css/font-awesome.min.css">
<script src="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css"></script>

<style type="text/css">
#imagemCadastroPonto:hover {
    background-image: url("imagens/cadastroPontoHover.png");
    background-repeat: no-repeat;
}
</style>

<title>Ponto</title>

</head>
<body>
<c:import url="cabecalhoDeslogado.jsp"/>

<%

	String[] meses = {"","Janeiro", "Fevereiro","Março","Abril","Maio","Junho",
			"Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};	
	ArrayList<PontoTO> listaDePonto = (ArrayList<PontoTO>) request.getAttribute("listaPonto");
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
    
    <h1>Folha de Ponto</h1>

	<form id="dadosFolha" role="form" class="form-inline" >
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
			
		  	<div class= "form-group">
		  		<a href="PesquisarPonto?acao=CadastrarAlterar&codigoEmpregado=<%=codigoEmpregado%>">
		  		<img id="imagemCadastroPonto" src="imagens/cadastroPonto.png">
 				</a>
		    </div>
		</div> 
	</form>
   <script>
		function funcaoData() 
		{
			var valor_mes = document.getElementById("combo_mes").value;
			var valor_ano = document.getElementById("combo_ano").value;
			location.href="PesquisarPonto?acao=Pesquisar&codigoEmpregado=<%=codigoEmpregado%>&mes="+valor_mes+"&ano="+valor_ano+""; 
		}
	</script>
	<br/>
	
	<form id="dadosPonto" role="form" class="form-inline" method="post">
	<div id="itensFolhaPonto">
	   <table id="tableFolhaPonto" class="table table-striped table-bordered" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>Dia</th>
	                <th>Entrada</th>
	                <th>Saida Almoço</th>
	                <th>Retorno Almoço</th>
	                <th>Saída</th>
	                <th>Horas</th>
	                <th>Horas Extras</th>
	                <th>Ação</th>
	            </tr>
	        </thead>
	        <tfoot>
	            <tr>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	                <th>Totais:</th>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	                <th>&nbsp;</th>
	            </tr>
	        </tfoot>
	        <tbody>
	  
	        </tbody>
	    </table>
	</div>
	</form>
</div>



<script type="text/javascript">
window.onload = function(){
  <% 
  		Boolean obtemPontoMesAtual = false;
		 if(listaDePonto.size() != 0)
		 {
				for(PontoTO pontoTO : listaDePonto)
				{
					SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
					String dataPonto = formatador.format(pontoTO.getDataPonto());
					int diaPonto = Integer.parseInt(dataPonto.substring(8,10));
					int mesPonto = Integer.parseInt(dataPonto.substring(5,7));
					int anoPonto = Integer.parseInt(dataPonto.substring(0,4));
					int ano = anoEscolhido;
					int mes = mesEscolhido;

					if((mesPonto == mes) && (anoPonto == ano))
					{ 
						obtemPontoMesAtual = true;
						%>
							var newRow = $("<tr>");
						    var cols = "";
						
						    cols += '<td>'+ <%=diaPonto%> +'</td>';
						    cols += '<td><span id=\"horaEntrada'+<%=diaPonto%>+'\"><%=pontoTO.getHoraEntrada()%></span></td>';
						    cols += '<td><span id=\"horaSaidaAlmoco'+<%=diaPonto%>+'\"><%=pontoTO.getHoraSaidaAlmoco()%></span></td>';
						    cols += '<td><span id=\"horaVoltaAlmoco'+<%=diaPonto%>+'\"><%=pontoTO.getHoraVoltaAlmoco()%></span></td>';
						    cols += '<td><span id=\"horaSaida'+<%=diaPonto%>+'\"><%=pontoTO.getHoraSaida()%></span></td>';
						    cols += '<td><span id=\"horasColuna'+<%=diaPonto%>+'\"></span></td>';
						    cols += '<td>&nbsp;</td>';
						    cols += '<td>';
						    cols += '<select id=\"combo_acao\" name=\"ano\">';
							cols += '<option value=\"Dia Comum\">Dia Comum</option>';
							cols += '<option value=\"Falta\">Falta</option>';
							cols += '<option value=\"Falta Justificada\">Falta Justificada</option>';
							cols += '<option value=\"Feriado\">Feriado</option>';
							cols += '<option value=\"Feriado Trabalhado\">Feriado Trabalhado</option>';
							cols += '</select>';
						    cols += '</td>';
						
						    newRow.append(cols);
						    $("#tableFolhaPonto").append(newRow);
			
						    <%
							if(pontoTO.getAcao().equals("Falta")){
							%>
							    $('#combo_acao option[value="Falta"]').attr({ selected : "selected" });
							<%
							}else{
							%>
							    $('#combo_acao option[value="Dia Comum"]').attr({ selected : "selected" });
							<%
							} 
						    //%>
						    //if(<%=pontoTO.getHoraEntrada()%> != ""){
						   // 	calculaTotal();
						   // }
						   // if(<%=pontoTO.getHoraSaidaAlmoco()%> != ""){
						   // 	calculaTotal();
						   // }
							//if(<%=pontoTO.getHoraVoltaAlmoco()%> != ""){
							//	calculaTotal();				    	
							//}
							//if(<%=pontoTO.getHoraSaida()%> != ""){
							//	calculaTotal();
							//}
						   // <%
					}
				}
				
				if(obtemPontoMesAtual == false)
				{
					for( int x = 1; x <= diasDoMes ; x++)
					{
					%>
						var newRow = $("<tr>");
					    var cols = "";
			
					    cols += '<td>'+ <%=x%> +'</td>';
					    cols += '<td>&nbsp;</td>';
					    cols += '<td>&nbsp;</td>';
					    cols += '<td>&nbsp;</td>';
					    cols += '<td>&nbsp;</td>';
					    cols += '<td>&nbsp;</td>';
					    cols += '<td>&nbsp;</td>';
					    cols += '<td>';
					    cols += '<div class=\"form-group\">';
					    cols += '<select class=\"form-control\" id=\"combo_acao\" name=\"ano\">';
					    cols += '<option value=\"Dia Comum\">Dia Comum</option>';
					    cols += '<option value=\"Falta\">Falta</option>';
					    cols += '<option value=\"Falta Justificada\">Falta Justificada</option>';
					    cols += '<option value=\"Feriado\">Feriado</option>';
					    cols += '<option value=\"Feriado Trabalhado\">Feriado Trabalhado</option>';
					    cols += '</select>';  
					    cols +='</div>';
					    newRow.append(cols);
					    $("#tableFolhaPonto").append(newRow);
					    
					    <%
					}
				} 
			}
		 	else
			{
				for( int x = 1; x <= diasDoMes ; x++)
				{
				%>
					var newRow = $("<tr>");
				    var cols = "";
		
				    cols += '<td>'+ <%=x%> +'</td>';
				    cols += '<td>&nbsp;</td>';
				    cols += '<td>&nbsp;</td>';
				    cols += '<td>&nbsp;</td>';
				    cols += '<td>&nbsp;</td>';
				    cols += '<td>&nbsp;</td>';
				    cols += '<td>&nbsp;</td>';
				    cols += '<td>';
				    cols += '<div class=\"form-group\">';
				    cols += '<select class=\"form-control\" id=\"combo_acao\" name=\"ano\">';
				    cols += '<option value=\"Dia Comum\">Dia Comum</option>';
				    cols += '<option value=\"Falta\">Falta</option>';
				    cols += '<option value=\"Falta Justificada\">Falta Justificada</option>';
				    cols += '<option value=\"Feriado\">Feriado</option>';
				    cols += '<option value=\"Feriado Trabalhado\">Feriado Trabalhado</option>';
				    cols += '</select>';  
				    cols +='</div>';
				    newRow.append(cols);
				    $("#tableFolhaPonto").append(newRow);
				    
				    <%
				}
			} 
		%>
	}(jQuery);
</script>	

<c:import url="rodape.jsp"/>
</body>
</html>