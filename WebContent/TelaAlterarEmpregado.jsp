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
<script type="text/javascript" src="js/scriptEmpregado.js"></script>
<script src="jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/sweetalert.min.js"></script>
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"  href="css/estilo.css" />
<!--<script src="https://code.jquery.com/jquery-2.2.4.min.js" ></script>  -->

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



	<%
		String[] estados = {"AC", "AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PE","PI",

		     "PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};

		String[] estadoCivil = {"Solteiro","Casado","Viúvo","Separado","Divorciado"};

		String[] diaPagamento = {"1º dia util","2º dia util","3º dia util","4º dia util","5º dia util"};
	%>



	<div class="header clearfix">



		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
			defer></script>

		<script src="dist/js/bootstrap-submenu.min.js" defer></script>

		<script src="js/sweetalert.min.js"></script>

		<div class="row">

			<div class="col-md-4">

				<img src="imagens/logo.jpg" align="left">

			</div>

			<div class="col-md-8">

				<br> <br>

				<nav>

				<ul class="nav nav-pills">



					<div class="col-md-2">

						<a
							href="AlterarEmpregado?acao=Demitir&codEmpregado=<%=empregado.getCodigoEmpregado()%>">

							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>

						</span> <br> <span class="func-link-txt">Demitir</span>

						</a>

					</div>
					
					<div class="col-md-2">

						<a
							href="PesquisarHolerite?acao=Pesquisar&codEmpregado=<%=empregado.getCodigoEmpregado()%>">

							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-bars fa-stack-1x func-link-i2"></i>

						</span> <br> <span class="func-link-txt">Holerite</span>

						</a>

					</div>

					<div class="col-md-2">

						<a
							href="PesquisarFerias?acao=Pesquisar&codigoEmpregado=<%=empregado.getCodigoEmpregado()%>">

							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>

						</span> <br> <span class="func-link-txt">Férias</span>

						</a>

					</div>

					<div class="col-md-2">

						<a
							href="AtividadeController?acao=PesquisarTodos&codEmpregado=<%=empregado.getCodigoEmpregado()%>">

							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>

						</span> <br> <span class="func-link-txt">Atividades</span>

						</a>

					</div>

					<div class="col-md-2">

						<a
							href="#">

							<span class="fa-stack fa-2x"> <i
								class="fa fa-square-o fa-stack-2x func-link-i1"></i> <i
								class="fa fa-trash-o fa-stack-1x func-link-i2"></i>

						</span> <br> <span class="func-link-txt">Décimo Terceiro</span>

						</a>

					</div>





					<!--<li class="perfil navbar-right"><a tabindex="0"
						data-toggle="dropdown" data-submenu><imgsrc="imagens/ferramenta.png" width="20" height="20" align="right"><span
							class="caret"></span></a>



						<ul class="dropdown-menu">

							<li class="perfil dropdown-submenu"><a
								href="AlterarUsuario?acao=alterar" tabindex="0">Meu Perfil</a> <a
								href="AlterarUsuario?acao=excluir" tabindex="0">Excluir
									Conta</a> <a href="TelaLogin.jsp" tabindex="0">Sair</a></li>

						</ul></li>

  -->
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



	<div class="container">

		<form id="dadosEmpregado" role="form" class="form-inline"
			method="post" action="AlterarEmpregado" enctype="multipart/form-data">

			<nav class="navbar navbar-default" role="navigation">

			<div class="container">

				<label for="dadosEmpregado"> Dados Empregado </label><br> <br>



				<div class="form-group">

					<label for="nome"> Nome </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+empregado.getNome()+" class=\"form-control\"  name=\"nome\" id=\"nome\" size=\"80\">");
						%>

					</div>

				</div>




				<div class="form-group">

					<div id="foto">

						<%
							if(foto.equals("")){
						%><img id="myimage" src="imagens/sem-imagem.png" height="100">
						<%
							}

										else{
						%><img id="myimage" src="uploads/<%=foto%>" height="100">
						<%
							}
						%>

						<label class="btn btn-primary" for="my-file-selector"> <input
							id="my-file-selector" name="fotoEmpregado" type="file"
							style="display: none;" onchange="onFileSelected(event)">

							Pesquisar..

						</label>

					</div>

				</div>


				<div class="form-group">

					<label for="sobrenome"> Sobrenome </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+empregado.getSobrenome()+" class=\"form-control\"  name=\"sobrenome\" id=\"sobrenome\" size=\"80\">");
						%>

					</div>

				</div>




				<div class="form-group">

					<label for="dataNasc"> Data Nascimento </label>

					<div class="span3">

						<input type="date" value="<%=empregado.getDataNasc()%>"
							id="dataNasc" name="dataNasc" class="form-control" size="20"
							required>

					</div>

				</div>



				<div class="form-group">

					<label for="sexo"> Sexo </label>

					<div class="span3">

						<%
							if(empregado.getSexo().equals("F")){

										  out.println("<input type=\"radio\"  name=\"sexo\"  id=\"radioFem\"  value=\"F\" checked>Fem");

										  }else{

										  out.println("<input type=\"radio\"  name=\"sexo\"  id=\"radioFem\"  value=\"F\" >Fem");

										  }

										  	  if(empregado.getSexo().equals("M")){

										  	  out.println("<input type=\"radio\"  name=\"sexo\"  id=\"radioMasc\"  value=\"M\" checked>Masc");

										  	  }else{

										  	  out.println("<input type=\"radio\"  name=\"sexo\"  id=\"radioMasc\"  value=\"M\">Masc");

										  	  }
						%>

					</div>

				</div>


				<div class="form-group">

					<label for="numDependentes"> Número de Dependentes </label>

					<div class="span3">

						<%
							out.println("<input type=\"number\" min=\"0\" value="+empregado.getQtdDependentes()+" id=\"qtdDependentes\" name=\"qtdDependentes\" class=\"form-control\" size=\"28\">");
						%>

					</div>

				</div>



				<div class="form-group">

					<label for="select">Estado Civil</label>

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





				<div class="form-group">

					<label for="cpf"> CPF </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+empregado.getCpf()+" id=\"cpf\" name=\"cpf\" class=\"form-control\" size=\"28\">");
						%>

					</div>

				</div>


				<div class="form-group">

					<label for="rg"> RG </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+empregado.getRg()+" name=\"rg\" id=\"rg\" class=\"form-control\" size=\"30\">");
						%>

					</div>

				</div>



				<div class="form-group">

					<label for="select">UF RG</label>

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



				<div class="form-group">

					<label for="numCarteira"> Nº Carteira de Trabalho </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+empregado.getNumCarteira()+" name=\"numCarteira\" id=\"numCarteira\" class=\"form-control\" size=\"30\">");
						%>

					</div>

				</div>



				<div class="form-group">

					<label for="serieCarteira"> Série </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+empregado.getSerieCarteira()+" name=\"serieCarteira\" id=\"serieCarteira\" class=\"form-control\" size=\"30\">");
						%>

					</div>

				</div>



				<div class="form-group">

					<label for="select">UF Carteira</label>

					<div class="span3">

						<%
							out.println("<select class=\"form-control\" name=\"ufCarteira\" id=\"ufCarteira\">");

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

			</div>

			<br>

			</nav>





			<nav class="navbar navbar-default" role="navigation">

			<div class="container">

				<label for="dadosEndereco"> Dados endereço </label><br> <br>

				<div class="form-group">

					<label for="cep"> CEP </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+endereco.getCep()+" class=\"form-control\"  name=\"cep\" id=\"cep\" size=\"25\">");
						%>

					</div>

				</div>

				<div class="form-group">

					<label for="logadouro"> Endereco </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+endereco.getEndereco()+" class=\"form-control\" name=\"endereco\" id=\"endereco\" size=\"25\">");
						%>

					</div>

				</div>

				<div class="form-group">

					<label for="numero"> Numero </label>

					<div class="span3">

						<input type="number" min="0" value="<%=endereco.getNumero()%>"
							class="form-control" name="numeroEndereco" id="numeroEndereco"
							size="25" required>

					</div>

				</div>

				<div class="form-group">

					<label for="complemento"> Complemento </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+endereco.getComplemento()+" class=\"form-control\"  name=\"complemento\" id=\"complemento\" size=\"10\">");
						%>

					</div>

				</div>

				<div class="form-group">

					<label for="bairro"> Bairro </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+endereco.getBairro()+" class=\"form-control\" name=\"bairro\" id=\"bairro\" size=\"10\">");
						%>

					</div>

				</div>


				<div class="form-group ">

					<label for="cidade"> Cidade </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+endereco.getCidade()+" class=\"form-control\" name=\"cidade\" id=\"cidade\" size=\"10\">");
						%>

					</div>

				</div>



				<div class="form-group">

					<label for="select">Estado</label>

					<div class="span3">

						<%
							out.println("<select class=\"form-control\" name=\"estado\" id=\"estado\">");

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

				<label for="contratoTrabalho"> Contrato de Trabalho </label><br>
				<br>



				<div class="form-group">

					<label for="dataAdmissao"> Data de Admissão </label>

	
				
				<div class="span3">

						<input value="<%=contrato.getDataAdmissao()%>"id="dataAdmissao" name="dataAdmissao" class="form-control" size="20"required>

					</div>

</div>

				<div class="form-group">

					<label for="cargo"> Cargo </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+contrato.getCargo()+" class=\"form-control\" name=\"cargo\" id=\"cargo\" size=\"20\">");
						%>

					</div>

				</div>


				<div class="form-group">

					<label for="salarioBase"> Salário Base </label>

					<div class="span3">

						<div class="input-group">

							<span class="input-group-addon">$</span>

							<%
								out.println("<input name=\"salarioBase\" value="+contrato.getSalarioBase()+" id=\"salarioBase\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\"/>");
							%>

						</div>

					</div>

				</div>


				<div class="form-group">

					<label for="valeTransporte"> Valor vale transporte(MENSAL)
					</label>

					<div class="span3">

						<div class="input-group">

							<span class="input-group-addon">$</span>

							<%
								out.println("<input name=\"valeTransporte\" value="+contrato.getValeTransporte()+" id=\"valeTransporte\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\100\" class=\"form-control currency\"/>");
							%>

						</div>

					</div>

				</div>


				<div class="form-group">

					<label for="descontoBeneficios"> Desconto de Benefícios <a
						href="javascript:void(0)" onclick="informacaoDescontoBeneficios()"><i
							class="fa fa-info-circle" title="Informação"></i></a>

					</label>

					<div class="span3">

						<div class="input-group">

							<span class="input-group-addon">$</span>

							<%
								out.println("<input value="+contrato.getDescontoBeneficios()+" name=\"descontoBeneficios\" id=\"descontoBeneficios\" size=\"20\" type=\"number\" min=\"0\" step=\"0.01\" data-number-to-fixed=\"2\" data-number-stepfactor=\"100\" class=\"form-control currency\"/>");
							%>

						</div>

					</div>

				</div>


				<div class="form-group">

					<label for="dataPagamento"> Dia de Pagamento</label>

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



				<div class="form-group">

					<label for="regimeContrato"> Regime de Trabalho</label>

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



				<div class="form-group">

					<label for="compensacaoDias"> Compensação de Dias de
						Trabalho</label>

					<div class="span3">

						<%
							if(contrato.getCompensacaoDias().equals("salario")){

										  out.println("<input  type=\"radio\" name=\"compensacaoDias\" id=\"salario\"  value=\"salario\" CHECKED>Acréscimo de salário<br>");

										  }else{

										  out.println("<input type=\"radio\" name=\"compensacaoDias\" id=\"salario\"  value=\"salario\"> Acréscimo de salário<br>");

										  }

										  	  if(contrato.getCompensacaoDias().equals("horas")){

										  	  out.println("<input type=\"radio\" name=\"compensacaoDias\" id=\"horas\"  value=\"horas\" CHECKED>Regime de compensação de horas<br>");

										  	  }else{

										  	  out.println("<input type=\"radio\" name=\"compensacaoDias\" id=\"horas\"  value=\"horas\">Regime de compensação de horas<br>");

										  	  }
						%>

						<br>

					</div>

				</div>

				<br>



				<div class="form-group">

					<label for="serieCarteira"> Banco </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+contrato.getBanco()+" name=\"banco\" id=\"banco\" class=\"form-control\" size=\"30\">");
						%>

					</div>

				</div>



				<div class="form-group">

					<label for="agencia"> Agência </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+contrato.getAgencia()+" name=\"agencia\" id=\"agencia\" class=\"form-control\" size=\"30\">");
						%>

					</div>

				</div>



				<div class="form-group">

					<label for="agencia"> Conta </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+contrato.getConta()+" name=\"conta\" id=\"conta\" class=\"form-control\" size=\"30\">");
						%>

					</div>

				</div>



				<div class="form-group">

					<label for="tipoConta">Tipo de Conta</label>

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

				<br> <br>





				<div class="form-group">

					<label for="periodoDeTrabalho"> Confira os horários de
						trabalho <a href="javascript:void(0)"
						onclick="informacaoCamposHora()"><i class="fa fa-info-circle"
							title="Informação"></i></a>

					</label> <br> <br>



					<table class="tableHorarios ">

						<tbody>

							<tr>

								<td>Semana</td>
								<td>Seg</td>
								<td></td>
								<td>Ter</td>
								<td>Qua</td>
								<td>Qui</td>
								<td>Sex</td>
								<td>Sáb</td>
								<td>Dom</td>

							</tr>

							<tr>

								<td>Entrada</td>

								<td><input class="form-control" name="horaEntrada1"
									id="horaEntrada1" size="5"></td>

								<td><img src="imagens/botao_repete.ico" class="imgPreenche"
									id="1" style="cursor: pointer" height="20"></td>

								<td><input type="text" name="horaEntrada2"
									id="horaEntrada2" class="form-control" size="5"></td>

								<td><input type="text" name="horaEntrada3"
									id="horaEntrada3" class="form-control" size="5"></td>

								<td><input type="text" name="horaEntrada4"
									id="horaEntrada4" class="form-control" size="5"></td>

								<td><input type="text" name="horaEntrada5"
									id="horaEntrada5" class="form-control" size="5"></td>

								<td><input type="text" name="horaEntrada6"
									id="horaEntrada6" class="form-control" size="5"></td>

								<td><input type="text" name="horaEntrada7"
									id="horaEntrada7" class="form-control" size="5"></td>

							</tr>



							<tr>

								<td>Saída Almoço</td>

								<td><input type="text" name="horaSaidaAlmoco1"
									class="form-control" name="hora" id="horaSaidaAlmoco1" size="5">

								</td>

								<td><img src="imagens/botao_repete.ico" class="imgPreenche"
									id="2" style="cursor: pointer" height="20"></td>

								<td><input type="text" name="horaSaidaAlmoco2"
									id="horaSaidaAlmoco2" class="form-control" size="5"></td>

								<td><input type="text" name="horaSaidaAlmoco3"
									id="horaSaidaAlmoco3" class="form-control" size="5"></td>

								<td><input type="text" name="horaSaidaAlmoco4"
									id="horaSaidaAlmoco4" class="form-control" size="5"></td>

								<td><input type="text" name="horaSaidaAlmoco5"
									id="horaSaidaAlmoco5" class="form-control" size="5"></td>

								<td><input type="text" name="horaSaidaAlmoco6"
									id="horaSaidaAlmoco6" class="form-control" size="5"></td>

								<td><input type="text" name="horaSaidaAlmoco7"
									id="horaSaidaAlmoco7" class="form-control" size="5"></td>

							</tr>



							<tr>

								<td>Volta Almoço</td>

								<td><input type="text" name="horaVoltaAlmoco1"
									class="form-control" id="horaVoltaAlmoco1" size="5"></td>

								<td><img src="imagens/botao_repete.ico" class="imgPreenche"
									id="3" style="cursor: pointer" height="20"></td>

								<td><input type="text" name="horaVoltaAlmoco2"
									id="horaVoltaAlmoco2" class="form-control" size="5"></td>

								<td><input type="text" name="horaVoltaAlmoco3"
									id="horaVoltaAlmoco3" class="form-control" size="5"></td>

								<td><input type="text" name="horaVoltaAlmoco4"
									id="horaVoltaAlmoco4" class="form-control" size="5"></td>

								<td><input type="text" name="horaVoltaAlmoco5"
									id="horaVoltaAlmoco5" class="form-control" size="5"></td>

								<td><input type="text" name="horaVoltaAlmoco6"
									id="horaVoltaAlmoco6" class="form-control" size="5"></td>

								<td><input type="text" name="horaVoltaAlmoco7"
									id="horaVoltaAlmoco7" class="form-control" size="5"></td>

							</tr>



							<tr>

								<td>Saída</td>

								<td><input type="text" name="horaSaida1"
									class="form-control" name="hora" id="horaSaida1" size="5">

								</td>

								<td><img src="imagens/botao_repete.ico" class="imgPreenche"
									id="4" style="cursor: pointer" height="20"></td>

								<td><input type="text" name="horaSaida2"
									class="form-control" id="horaSaida2" class="form-control"
									size="5"></td>

								<td><input type="text" name="horaSaida3"
									class="form-control" id="horaSaida3" class="form-control"
									size="5"></td>

								<td><input type="text" name="horaSaida4"
									class="form-control" id="horaSaida4" class="form-control"
									size="5"></td>

								<td><input type="text" name="horaSaida5"
									class="form-control" id="horaSaida5" class="form-control"
									size="5"></td>

								<td><input type="text" name="horaSaida6"
									class="form-control" id="horaSaida6" class="form-control"
									size="5"></td>

								<td><input type="text" name="horaSaida7"
									class="form-control" id="horaSaida7" class="form-control"
									size="5"></td>

							</tr>

							<tr>

								<td>Dia de Folga</td>

								<td><input name="diaFolga1" id="diaFolga1" class="checkbox"
									type="checkbox" onchange="alertaDiaFolga(this)" /></td>

								<td></td>

								<td><input name="diaFolga2" id="diaFolga2" class="checkbox"
									type="checkbox" onchange="alertaDiaFolga(this)" /></td>

								<td><input name="diaFolga3" id="diaFolga3" class="checkbox"
									type="checkbox" onchange="alertaDiaFolga(this)" /></td>

								<td><input name="diaFolga4" id="diaFolga4" class="checkbox"
									type="checkbox" onchange="alertaDiaFolga(this)" /></td>

								<td><input name="diaFolga5" id="diaFolga5" class="checkbox"
									type="checkbox" onchange="alertaDiaFolga(this)" /></td>

								<td><input name="diaFolga6" id="diaFolga6" class="checkbox"
									type="checkbox" onchange="alertaDiaFolga(this)" /></td>

								<td><input name="diaFolga7" id="diaFolga7" class="checkbox"
									type="checkbox" onchange="alertaDiaFolga(this)" /></td>

							</tr>

							<tr>
							<tr>

								<td>Meio período</td>

								<td><input name="diaMeioPeriodo1" id="diaMeioPeriodo1"
									type="checkbox" onchange="alertaMeioPeriodo(this)" /></td>

								<td></td>

								<td><input name="diaMeioPeriodo2" id="diaMeioPeriodo2"
									type="checkbox" onchange="alertaMeioPeriodo(this)" /></td>

								<td><input name="diaMeioPeriodo3" id="diaMeioPeriodo3"
									type="checkbox" onchange="alertaMeioPeriodo(this)" /></td>

								<td><input name="diaMeioPeriodo4" id="diaMeioPeriodo4"
									type="checkbox" onchange="alertaMeioPeriodo(this)" /></td>

								<td><input name="diaMeioPeriodo5" id="diaMeioPeriodo5"
									type="checkbox" onchange="alertaMeioPeriodo(this)" /></td>

								<td><input name="diaMeioPeriodo6" id="diaMeioPeriodo6"
									type="checkbox" onchange="alertaMeioPeriodo(this)" /></td>

								<td><input name="diaMeioPeriodo7" id="diaMeioPeriodo7"
									type="checkbox" onchange="alertaMeioPeriodo(this)" /></td>


							</tr>



						</tbody>

					</table>

				</div>



				<br> <br> <br> <br>



				<div class="form-group ">

					<label for="totalHoras">Total de horas na semana: </label>

					<div class="span3">

						<input type="text" name="duracaoSemanal"
							value="<%=contrato.getDuracaoSemanal()%>" class="form-control"
							id="duracaoSemanal" size="5" disabled>

					</div>

					<span style="color: red; font-weight: bold;" id="saldoHoras"></span>

				</div>



			</div>

			</nav>



			<nav class="navbar navbar-default" role="navigation">

			<div class="container">

				<label for="TipoContato"> Contato </label><br> <br>

				<div class="form-group ">

					<label for="tipoContato"> Tipo Contato </label>

					<div class="span3">

						<%
							out.println("<input type=\"text\" value="+contato.getTipoContato()+" class=\"form-control\" name=\"tipoContato\" id=\"tipoContato\" size=\"20\">");
						%>

					</div>

				</div>

				<div class="form-group">

					<label for="numeroTelefone"> Número </label>

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

				<label for="email"> Login </label><br> <br>

				<div class="form-group ">

					<label for="email"> E-mail </label>

					<div class="span3">

						<%
							out.println("<input value="+empregado.getEmail()+" class=\"form-control\" id=\"email\"  placeholder=\"E-mail\" name=\"email\" type=\"email\">");
						%>

					</div>

				</div>

				<div class="form-group">

					<label for="senha"> Senha </label>

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

				<button type="submit" name="acao" value="Salvar"
					onclick="return validar()" class="btn btn-primário ">
					Salvar</button>

				<button type="submit" name="acao" value="Cancelar"
					class="btn btn-primário " onclick="history.go(-1)">
					Cancelar</button>

			</div>


		</form>

	</div>



	<c:import url="rodape.jsp" />



	<script type="text/javascript">

window.onload = function(){

  <%ArrayList<JornadaTrabalhoTO> listaJornada = (ArrayList<JornadaTrabalhoTO>)request.getAttribute("listaJornada");

  for(int x = 0; x < listaJornada.size(); x++){

  JornadaTrabalhoTO lista = listaJornada.get(x);%>

    var id = "<%=lista.getDiaSemana()%>";



document.getElementById("horaSaida" + id).value="<%=lista.getHoraSaida()%>";
document.getElementById("horaEntrada"+ id).value="<%=lista.getHoraEntrada()%>";
document.getElementById("horaSaidaAlmoco"+ id).value="<%=lista.getHoraSaidaAlmoco()%>";
document.getElementById("horaVoltaAlmoco"+ id).value="<%=lista.getHoraVoltaAlmoco()%>";

			if (
	<%=lista.getDiaFolga()%>
		== true) {

				document.getElementById("horaSaida" + id).disabled = true;

				document.getElementById("horaEntrada" + id).disabled = true;

				document.getElementById("horaSaidaAlmoco" + id).disabled = true;

				document.getElementById("horaVoltaAlmoco" + id).disabled = true;

				document.getElementById("diaFolga" + id).checked = true;

				document.getElementById("diaMeioPeriodo" + id).disabled = true;

			}

			else if (
	<%=lista.getDiaMeioPeriodo()%>
		== true)

			{

				document.getElementById("horaSaidaAlmoco" + id).disabled = true;

				document.getElementById("horaVoltaAlmoco" + id).disabled = true;

				document.getElementById("diaMeioPeriodo" + id).checked = true;

				document.getElementById("diaFolga" + id).disabled = true;

			}
	<%}%>
		}
	</script>



</body>

</html>