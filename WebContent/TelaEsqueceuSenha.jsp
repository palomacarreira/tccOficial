<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recuperacao de senha</title>
<script src="js/jquery-1.2.6.pack 2.js"></script>
<script src="js/jquery.maskedinput-1.1.4.pack 2.js"></script>
<script type="text/javascript">

$(document).ready(function(){

	$('#inputCnpj').hide();
	$('#inputCpf').hide();


	$("#cpf").click(function(){
		
		 $('#inputCnpj').hide();
		 $('#inputCpf').show();
		 $("#inputCpf").mask("999.999.999-99");

	})

		$("#cnpj").click(function(){
		
			$('#inputCpf').hide();
			$('#inputCnpj').show();
		    $("#inputCnpj").mask("99.999.999/9999-99");

	})
	
	
});
</script>
</head>
<body>

<form name="form1" class= "form-inline" method="post" action="CadastrarUsuario">
</br></br>
 <label  for= "mensagem" >Por questoes de seguranca, informe seu e-mail e CPF(ou CNPJ) e enviaremos uma senha provisoria para seu e-mail. 
No proximo acesso voce sera orientado a trocar essa senha por outra de sua preferencia.</label> 

  <div  class= "form-group " > 
		    <label  for= "email" > Email </label> 
		    <div class="span3">
		    <input  type= "text"  class= "form-control" name="email" id= "email" size="75"> 
		    </div>
	   </div>
	   </br>
	   </br> </br>
	   <div  class= "radio form-group" > 
  			<div class="span3">
  			    <input  type= "radio"  name= "opcao"  id= "cpf"  value= "option1" >
   			 CPF
  				<input  type= "radio"  name= "opcao"  id= "cnpj"  value= "option2" >
  			 CNPJ
  			</div>
		</div> 
		</br> </br>
	   <div  class= "form-group"> 
		    <!--  <label  for= "cpf" > CPF </label> -->
		    <div class="span3">
		    <input type="text" id="inputCpf" name="inputCpf" class= "form-control" size="28"> 
		    </div>
		 </div> 
		 </br>

		  <div  class= "form-group"> 
		    <!--  <label  for= "cnpj" > CNPJ </label> -->
		    <div class="span3">
		    <input  type="text" id="inputCnpj" name="inputCnpj" class= "form-control" size="28"> 
		    </div>
		 </div>  
    </br></br></br>
    <div id="botoes">
		<button  type= "submit" name="acao" value="Cadastrar" class= "btn btn-primário " > Salvar </button> 
		<button  type= "submit" name="acao" value="Cancelar" class= "btn btn-primário " > Cancelar </button> 
	</div>
	
     
  </form>
</body>
</html>