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



<title>Perfil Empregador</title>

</head>
<body>

<script type="text/javascript">

$(document).ready(function () {
	
	$('#datetimepicker').data("DateTimePicker").FUNCTION();
		  $(function () {
		      $('#datetimepicker1').datetimepicker();
		  });
  });
$(function() {
    $('#datetimepicker4').datetimepicker({
      pickTime: false
    });
  });

  
</script>
        
<c:import url="cabecalhoLogado.jsp"/>
<div class="container">

<form  class= "form-inline" > 
<nav class="navbar navbar-default" role="navigation">
<div class="container">

	<label  for= "meuPerfil" > Meu Perfil </label><br> <br>
		  <div  class= "form-group" > 
		    <label  for= "nome" > Nome </label> 
		    <input  type= "text"  class= "form-control"  id= "nome" size="100"> 
		  </div> 
		  <div  class= "form-group" > 
		    <label  for= "sobrenome" > Sobrenome </label> 
		    <input  type= "text"  class= "form-control"  id= "sobrenome" size="100"> 
		  </div> 
		 


            <div class="form-group">
            <label  for= "date" > Data </label> 
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
  
       
    </div>



		 
		 <div  class= "radio form-group" > 
  			<label> 
   				 <input  type= "radio"  name= "radioFem"  id= "radioFem"  value= "option1" >
   			 F
  			</label>
  			<label> 
  				<input  type= "radio"  name= "radioMasc"  id= "radioMasc"  value= "option2" >
  			 M 
  			 </label> 
		</div> 
		<div  class= "form-group" > 
		    <label  for= "cpf" > CPF </label> 
		    <input  type= "text"  class= "form-control"  id= "cpf" size="10"> 
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "cnpj" > CNPJ </label> 
		    <input  type= "text"  class= "form-control"  id= "cnpj" size="10"> 
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "rg" > RG </label> 
		    <input  type= "text"  class= "form-control"  id= "rg" size="10"> 
		 </div>
		 
		 <div  class = "form-grupo" > 
     		 <label  for = "select" >UF</label> 
      		 <select  id = "select"  class = "forma-control" > 
       		 <option> selecione seu estado </option> 
      		</select> 
    	</div> 
    	
    	<div  class= "form-group" > 
		    <label  for= "cep" > CEP </label> 
		    <input  type= "text"  class= "form-control"  id= "cep" size="10"> 
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "logadouro" > Logadouro </label> 
		    <input  type= "text"  class= "form-control"  id= "logadouro" size="10"> 
		 </div> 
		 <div  class= "form-group" > 
		    <label  for= "numero" > Numero </label> 
		    <input  type= "text"  class= "form-control"  id= "numero" size="10"> 
		 </div>
		 <div  class= "form-group" > 
		    <label  for= "complemento" > Complemento </label> 
		    <input  type= "text"  class= "form-control"  id= "complemento" size="10"> 
		 </div>
		  <div  class= "form-group" > 
		    <label  for= "bairro" > Bairro </label> 
		    <input  type= "text"  class= "form-control"  id= "bairro" size="10"> 
		 </div>
		 
		 <div  class = "form-grupo" > 
     		 <label  for = "select" >Estado</label> 
       		 <select class="form-control" name="uf" id="uf">
									<option value="AC">AC</option>
									<option value="AL">AL</option>
									<option value="AM">AM</option>
									<option value="AP">AP</option>
									<option value="BA">BA</option>
									<option value="CE">CE</option>
									<option value="DF">DF</option>
									<option value="ES">ES</option>
									<option value="GO">GO</option>
									<option value="MA">MA</option>
									<option value="MG">MG</option>
									<option value="MS">MS</option>
									<option value="MT">MT</option>
									<option value="PA">PA</option>
									<option value="PE">PE</option>
									<option value="PI">PI</option>
									<option value="PR">PR</option>
									<option value="RJ">RJ</option>
									<option value="RN">RN</option>
									<option value="RO">RO</option>
									<option value="RR">RR</option>
									<option value="RS">RS</option>
									<option value="SC">SC</option>
									<option value="SE">SE</option>
									<option value="SP">SP</option>
									<option value="TO">TO</option>
      		</select> 
    	</div>
    	
    	<div  class = "form-grupo" > 
     		 <label  for = "select" >Cidade</label> 
      		 <select  id = "select"  class = "forma-control" > 
       		 <option> selecione a cidade </option> 
      		</select> 
    	</div>
    	</div>
    

    	</nav>
    	<nav class="navbar navbar-default" role="navigation">
    	<div class="container">

       <label  for= "email" > Login </label><br> <br>
       <div  class= "form-group " > 
		    <label  for= "email" > Email </label> 
		    <input  type= "text"  class= "form-control"  id= "numero" size="10"> 
	   </div>
	   <div  class= "form-group" > 
		    <label  for= "senha" > Senha </label> 
		    <input  type= "text"  class= "form-control"  id= "numero" size="10"> 
		</div>
		<br>
       <button  type= "submit"  class= "btn btn-primário " > Alterar</button> 
       <button  type= "submit"  class= "btn btn-primário " > Salvar </button> 
 
    </div>
       </nav>
  </form>



</div>
<c:import url="rodape.jsp"/>

</body>
</html>