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
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/sweetalert.min.js"></script> 
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"  href="css/estilo.css" />

<title>Cadastro Empregado</title>

</head>
<body>
   

<% String[] estados = {"AC", "AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PE","PI",
     			"PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};	
%>
	
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

						
							<p style="font-size: 25px;">Cadastro</p>

					

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
		<hr />
<div class="container">

<form id="dadosEmpregado" role="form" class="form-inline" method="post" action="CadastrarEmpregado" enctype="multipart/form-data">
<nav class="navbar navbar-default" role="navigation">
<div class="container">
	<label  for= "dadosEmpregado" > Dados Empregado </label><br><br>
	
		  <div  class= "form-group" > 
		    <label  for= "nome" > Nome </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="nome" id="nome" size="80" required> 
		    </div>
		  </div> 
		 
		<div class= "form-group" >  
			<div id="foto">
			<img id="myimage" src="imagens/sem-imagem.png" style="width:100px;">
			<label class="btn btn-primary" for="my-file-selector">
    		<input id="my-file-selector" name="fotoEmpregado" type="file" style="display:none;" onchange="onFileSelected(event)">
    			Pesquisar..
			</label>
			</div>
		</div>

		  <div  class= "form-group" > 
		    <label  for= "sobrenome" > Sobrenome </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="sobrenome" id= "sobrenome" size="80" required> 
		    </div>
		  </div> 

	 	<div class="form-group" > 
	 	<label for="sexo" > Sexo </label> 
  		<div class="span3">
   				 <input  type= "radio"  name= "sexo"  id= "radioFem"  value= "F" checked>
   			 Fem
  				<input  type= "radio"  name= "sexo"  id= "radioMasc"  value= "M" >
  			 Masc
  			</div>
		</div> 
		
		  <div  class= "form-group" > 
		    <label  for= "dataNasc" > Data Nascimento </label> 
		    <div class="span3">
		    <input type="date" id="dataNasc" name="dataNasc" class="form-control" size="20" required> 
		    </div>
		 </div>
		 
		<div  class= "form-group" > 
		    <label  for= "numDependentes" > Número de Dependentes </label> 
		    <div class="span3">
		    <input type="number" min="0" value="0" id="qtdDependentes" name="qtdDependentes" class= "form-control" size="28" required>      
		    </div>
		 </div> 

		  <div class= "form-group">
     		 <label  for = "select" >Estado Civil</label> 
     		 <div class="span3">
 			<select class="form-control" name="estadoCivil" id="estadoCivil">
			<option value="Solteiro" style ="width: 100px; height: 100px">Solteiro</option>	
     		<option value="Casado" style ="width: 100px; height: 100px">Casado</option>	
     		<option value="Viúvo" style ="width: 100px; height: 100px">Viúvo</option>	
     		<option value="Separado" style ="width: 100px; height: 100px">Separado</option>	
     		<option value="Divorciado" style ="width: 100px; height: 100px">Divorciado</option>	
		</select>
      		</div>
    	</div> 
		 
		<div  class= "form-group" > 
		    <label  for= "cpf" > CPF </label> 
		    <div class="span3">
		    <input type="text" id="cpf" name="cpf" class= "form-control" size="28"> 
		    </div>
		 </div> 
		
		 <div  class= "form-group" > 
		    <label  for= "rg" > RG </label> 
		    <div class="span3">
		    <input  type= "text" name="rg" id= "rg" class= "form-control" size="30"> 
		    </div>
		 </div>
		 
		 <div class= "form-group" >
     		 <label  for = "select" >UF RG</label> 
     		 <div class="span3">
 
       		  <%
		out.println("<select class=\"form-control\" name=\"ufRg\" id=\"comboUf_Rg\">");
		
     	for (int i = 0; i < estados.length; i++) {
     		
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
     		
     	}
		out.println("</select>");
    	%>
    	
      		</div>
    	</div> 
    	
    	<div  class= "form-group" > 
		    <label  for= "numCarteira" > Nº Carteira de Trabalho </label> 
		    <div class="span3">
		    <input  type= "text" name="numCarteira" id= "numCarteira" class= "form-control" size="30"> 
		    </div>
		 </div>
		 
		 <div  class= "form-group" > 
		    <label  for= "serieCarteira" > Série </label> 
		    <div class="span3">
		    <input  type= "text" name="serieCarteira" id= "serieCarteira" class= "form-control" size="30"> 
		    </div>
		 </div>
		 
		  <div class= "form-group">
     		 <label  for = "select" >UF Carteira</label> 
     		 <div class="span3">
       		  
								<%
		out.println("<select class=\"form-control\" name=\"ufCarteira\" id=\"ufCarteira\">");
		
     	for (int i = 0; i < estados.length; i++) {
 
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
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
    	<label  for= "dadosEndereco" > Dados endereço </label><br> <br>
    	<div  class= "form-group" > 
		    <label  for= "cep" > CEP </label>
		    <div class="span3"> 
		    <input  type= "text"  class= "form-control"  name="cep" id= "cep" size="25"> 
		    </div>
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "logadouro" > Endereco </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control" name="endereco" id= "endereco" size="25"> 
		    </div>
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "numero" > Numero </label> 
		    <div class="span3">
		    <input type="number" min="0" class= "form-control"  name="numeroEndereco" id= "numeroEndereco" size="25" required> 
		    </div>
		 </div>
		 <div  class= "form-group" > 
		    <label  for= "complemento" > Complemento </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="complemento" id= "complemento" size="10"> 
		    </div>
		 </div>
		  <div  class= "form-group" > 
		    <label  for= "bairro" > Bairro </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="bairro" id= "bairro" size="10"> 
		    </div>
		 </div>
		     <div  class= "form-group " > 
		    <label  for= "cidade" > Cidade </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control"  name="cidade" id= "cidade" size="10"> 
		    </div>
		 
	   </div>
		 
			 <div class= "form-group">
     		 <label  for = "select" >Estado</label> 
     		 <div class="span3">
       		  
								<%
		out.println("<select class=\"form-control\" name=\"estado\" id=\"estado\">");
		
     	for (int i = 0; i < estados.length; i++) {
 
     			out.println("<option value="+ estados[i] +" style =\"width: 100px; height: 100px\">"+ estados[i] +"</option>");	
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
         <label  for= "contratoTrabalho" > Contrato de Trabalho </label><br> <br>
         
        <div  class= "form-group" > 
		    <label  for= "dataAdmissao" > Data de Admissão </label> 
		    <div class="span3">
		    <input type="date" id="dataAdmissao" name="dataAdmissao" class= "form-control" size="20" required> 
		    </div>
		 </div>
		 
	   <div  class= "form-group" > 
		    <label  for= "cargo" > Cargo </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control" name="cargo" id="cargo" size="20"> 
		    </div>
		</div>

		<div  class= "form-group" > 
		    <label  for= "salarioBase" > Salário Base </label> 
		    <div class="span3">
	 			<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	        		<input name="salarioBase" id= "salarioBase" size="20" type="number" value="0" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency"/>
	   			 </div>	   
   			 </div> 
		</div>
		
		<div  class= "form-group" > 
		    <label  for= "valeTransporte" > Valor vale transporte(MENSAL) 
		    <a href="javascript:void(0)" onclick="informacaoDescontoValeTransporte()"><i class="fa fa-info-circle" title="Informação"></i></a>
		    </label> 
		    <div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	        		<input name="valeTransporte" id="valeTransporte" size="20" type="number" value="0" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency"/>
	   			 </div>	 
   			 </div>   		   
		</div>
		
		<div  class= "form-group" > 
		    <label  for= "descontoBeneficios" > Desconto de Benefícios
	 		<a href="javascript:void(0)" onclick="informacaoDescontoBeneficios()"><i class="fa fa-info-circle" title="Informação"></i></a>
	 		</label>
	 		<div class="span3">
				<div class="input-group"> 
	       		 	<span class="input-group-addon">$</span>
	        		<input name="descontoBeneficios" id="descontoBeneficios" size="20" type="number" value="0" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency"/>
	   			 </div>	    
	   		</div>		   
		</div>
	
		 <div class= "form-group">
     		 <label  for= "dataPagamento" > Dia de Pagamento</label> 
     		 <div class="span3">
 				<select class="form-control" name="diaPagamento" id="diaPagamento">
					<option value="1º dia util" style ="width: 100px; height: 100px">1º dia util</option>	
     				<option value="2º dia util" style ="width: 100px; height: 100px">2º dia util</option>
     				<option value="3º dia util" style ="width: 100px; height: 100px">3º dia util</option>
     				<option value="4º dia util" style ="width: 100px; height: 100px">4º dia util</option>
     				<option value="5º dia util" style ="width: 100px; height: 100px">5º dia util</option>	
				</select>
      		</div>
    	</div> 
	
		<br>
		
     	<div class= "form-group">
     		<label  for= "regimeContrato" > Regime de Trabalho</label> 
     		<div class="span3">
		    <input  type="radio" name="regimeTrabalho" onchange="doalert(this)" id="regime44"  value="Tempo Integral" CHECKED>
		    Tempo Integral(44hrs semanais) 
		    <br>
			<input type="radio" name="regimeTrabalho" onchange="doalert(this)" id="regime25"  value="Tempo Parcial"> 
			Tempo Parcial(25hrs semanais)		
  			 <br>
      		</div>
    		</div> 
    		
    		
    		<div class="form-group">
     		<label  for="compensacaoDias" > Compensação de Dias de Trabalho
     		<a href="javascript:void(0)" onclick="informacaoBancoDeHoras()"><i class="fa fa-info-circle" title="Informação"></i></a>
	 		</label>
     		<div class="span3">
		    <input  type="radio" name="compensacaoDias" id="salario"  value="salario" CHECKED>
		    Acréscimo de salário
		    <br>
			<input type="radio" name="compensacaoDias" id="horas"  value="horas"> 
			Regime de compensação de horas	
		    <br>
      		</div>
    		</div> 
    	<br>
    	
		 <div  class= "form-group" > 
		    <label  for= "serieCarteira" > Banco </label> 
		    <div class="span3">
		    <input  type= "text" name="banco" id="banco" class= "form-control" size="30"> 
		    </div>
		 </div>
		 
		 <div  class= "form-group" > 
		    <label  for= "agencia" > Agência </label> 
		    <div class="span3">
		    <input  type= "text" name="agencia" id= "agencia" class= "form-control" size="30"> 
		    </div>
		 </div>
		  
		 <div  class= "form-group" > 
		    <label  for= "agencia" > Conta </label> 
		    <div class="span3">
		    <input  type= "text" name="conta" id="conta" class="form-control" size="30"> 
		    </div>
		 </div>
		 
		   <div >
     		 <label  for = "tipoConta" >Tipo de Conta</label> 
     		 <div class="span3">
 				<select class="form-control" name="tipoConta" id="tipoConta">
					<option value="Poupança" style ="width: 100px; height: 100px">Poupança</option>	
     				<option value="Corrente" style ="width: 100px; height: 100px">Conta Corrente</option>	
				</select>
      		</div>
    		</div> 
    	
		  <br>
     <br>
     
     
  
	 <div class="container">
	 <label  for= "periodoDeTrabalho" > Confira os horários de trabalho 
	 <a href="javascript:void(0)" onclick="informacaoCamposHora()"><i class="fa fa-info-circle" title="Informação"></i></a>
	 </label>
 
		 <br> 
		 <br>
		  <table class="tableHorarios ">
		  <tbody>
		  <tr>
		  <td>Semana</td><td>Seg</td><td> </td><td>Ter</td><td>Qua</td><td>Qui</td><td>Sex</td><td>Sáb</td><td>Dom</td>
		  </tr> 
		  <tr>
		  <td>Entrada</td>
		  <td>
		  <input type="text" class="form-control" name="horaEntrada1" id="horaEntrada1" size="5">
		  </td>
		  <td>
		  <img src="imagens/botao_repete.ico" class="imgPreenche" id="1" style="cursor: pointer" height="20">
		  </td>
		  <td>
		  <input type="text" class="form-control" name="horaEntrada2" id="horaEntrada2" size="5" >
		  </td>
		  <td>
		  <input type="text" class="form-control"  name="horaEntrada3" id="horaEntrada3" size="5" >
		  </td> 
		  <td>
		 	<input type="text" class="form-control" name="horaEntrada4" id="horaEntrada4" size="5" >
		  </td>
		    <td>
		 	<input type="text" class="form-control" name="horaEntrada5" id="horaEntrada5" size="5" >
		  </td>
		    <td>
		 	<input type="text" class="form-control" name="horaEntrada6" id="horaEntrada6" size="5" >
		 </td>
		    <td>
		 	<input type="text" class="form-control" name="horaEntrada7" id="horaEntrada7" size="5" disabled>
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
		 	<input type="text" name="horaSaidaAlmoco7" id="horaSaidaAlmoco7" class="form-control" size="5" disabled>
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
		 	<input type="text"  name="horaVoltaAlmoco7" id="horaVoltaAlmoco7" class="form-control" size="5" disabled>
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
		 	<input type="text" name="horaSaida7" class="form-control" id="horaSaida7" class="form-control" size="5" disabled>
		  </td>
		  </tr>
		  
		  <tr>
		  <td> DSR </td>
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
			<input name="diaFolga7" id="diaFolga7" class="checkbox" type="checkbox" onchange="alertaDiaFolga(this)" checked/>
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
			<input name="diaMeioPeriodo7" id="diaMeioPeriodo7" type="checkbox" onchange="alertaMeioPeriodo(this)" disabled/>
		  </td>
		
		  </tr>
		  
		 </tbody>
		  </table>
		 </div>
		  
		 <br> 
		 <br>
		 
	    <div  class= "form-group " > 
		    <label for="totalHoras">Total de horas na semana: </label>
		    <div class="span3">
		    <input type="text" name="duracaoSemanal" class="form-control" id="duracaoSemanal" size="5" value="00:00" Readonly>
		    </div>
	   		<span style="color:red; font-weight:bold;" id="saldoHoras"></span>
	   </div>
	   
    </div>
    <br>
 	</nav>
    
    <nav class="navbar navbar-default" role="navigation">
    <div class="container">
         <label  for= "TipoContato" > Contato </label><br> <br>
       <div  class= "form-group " > 
		    <label  for= "tipoContato" > Tipo Contato </label> 
		    <div class="span3">
		    <input type="text" class= "form-control" name="tipoContato" id= "tipoContato" size="20"> 
		    </div>
	   </div>
	   <div  class= "form-group" > 
		    <label  for= "numeroTelefone" > Número </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control" name="numeroTelefone" id= "numeroTelefone" size="20"> 
		    </div>
		</div>
      <br>
    </div>
    <br>
    
      </nav>
    
    <nav class="navbar navbar-default" role="navigation">
    	<div class="container">

       <label  for= "email" > Login </label><br> <br>
      
       <div  class= "form-group " > 
		    <label  for= "email" > E-mail </label> 
		    <div class="span3">
		    <input class="form-control" id= "email"  placeholder="E-mail" name="email" type="email">
		    </div>
	   </div>

	   <div  class= "form-group" > 
		    <label  for= "senha" > Senha </label> 
		    <div class="span3">
		    <input  type="password"  class= "form-control" name="senha" id= "senha" size="10"> 
		    </div>
		</div>
  
    </div>
     <br>
     
    </nav>
    
    <div id="botoes">
		<button  type= "submit" name="acao" value="Cadastrar" onclick="return validar()" class= "btn btn-primário " > Salvar </button> 
		<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário "  onclick="history.go(-1)"> Cancelar </button> 
	</div>
 </form>

</div>
<c:import url="rodape.jsp"/>

</body>
</html>