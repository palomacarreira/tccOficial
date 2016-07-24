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
<title>Gestao de empregados</title>
<style>
	#border{
		border: 1px solid;
	}
</style>
<link rel="stylesheet" href="css/jquery.superbox.css" type="text/css" media="all" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script src="js/jquery.superbox-min.js"></script>


<script type="text/javascript">

</script>
</head>
<body>
<c:import url="cabecalhoDeslogado.jsp"/>
<div class="container">
	<div  class="row">
		
	
			<div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title"> Documentacao Necessaria </h3>
	                    
	                </div>
	           
	                
	                    <form role="form">
	                        
							<ul></br>
								<li> Numero do CPF e RG</li></br>
								<li> Data de nascimento</li></br>
								<li> Numero da carteira de trabalho</li></br>
								<li> Endereco residencial</li></br>
								<li> Valor do salario contratado</li></br>
								<li> Numero do telefone</li></br>
								<li> E-mail para contato</li></br>
								<li> Dados bancarios</li></br>
								<li> Data de admissao</li>
							</ul>
	                       
	                    </form>
	               
	            </div>
		
	</div>
</div>
<c:import url="rodape.jsp"/>
</body>
</html>