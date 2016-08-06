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
</head>
<body>
<c:import url="cabecalhoDeslogado.jsp"/>
<div class="container">
	<div  class="row">
		<div  class="col-md-8">
			<img src="imagens/domestica01.jpg" class="img-responsive" align="left">
		</div>
		<div  class="col-md-4">
			<div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title"></h3>
	                    
	                </div>
	                <div class="panel-body">
	                
	                    <form role="form">
	                        <fieldset>
	                            <div class="form-group">
	                                <input class="form-control" placeholder="E-mail" name="email" type="email" >
	                            </div>
	                            <div class="form-group">
	                                <input class="form-control" placeholder="Password" name="password" type="password" value="">
	                            </div>
	                            <!-- Change this to a button or input when using this as a form -->
	                            <button type="button" class="btn btn-success btn-block">Login</button>
	                            <p>Esqueceu sua senha? <a href="signUp.html" class="">Clique aqui!</a></p>
	                        </fieldset>
	                    </form>
	                </div>
	            </div>
			</div>
	</div>
</div>
<c:import url="rodape.jsp"/>
</body>
</html>