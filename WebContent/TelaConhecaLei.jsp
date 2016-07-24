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
	                    <h3 class="panel-title"> Conheca a lei </h3>
	                    
	                </div>
	           
	                
	                    <form role="form">
	    
	    <h1 style="font-size:100%;" >&nbsp;Salário Mínimo </h1>                    
	<p>&nbsp;O valor pode variar de estado para estado, mas não deve ser inferior ao mínimo nacional (R$ 788). No Paraná, vale o piso regional que atualmente é de R$ 1.070,33. Direito mantido e sem alterações. 
13º salário 
Equivale a um salário mensal e deve ser pago em duas parcelas até o dia 20 de dezembro. Direito mantido e sem alterações.</p>
	    <h1 style="font-size:100%;">&nbsp;INSS</h1>                    
	<p>&nbsp;O empregador recolhe 8% sobre o salário bruto e o empregado tem 8% descontado do seu salário. O imposto incide também sobre o 13° salário, férias e adicional de férias.</p>
	<h1 style="font-size:100%;">&nbsp;FGTS e seguros</h1>   
	<p>&nbsp;Como era: O recolhimento do Fundo de Garantia do Tempo de Serviço (FGTS) era opcional para o empregador doméstico.
	</br>&nbsp;Com a nova lei:A nova lei tornou obrigatório o recolhimento do FGTS por parte do empregador doméstico. A alíquota é de 8% sobre o salário bruto. Também é preciso recolher 0,8% por seguro contra acidente e 3,2% relativos à rescisão contratual. </p>
	<h1 style="font-size:100%;">&nbsp;Horários</h1>   
	<p>&nbsp;Antes da lei: O horário de trabalho era acordado diretamente entre empregador e empregado.
</br>&nbsp;Com a nova lei: A jornada de trabalho deverá ser de 8 horas diárias e 44 horas semanais, com 4 horas de trabalho aos sábados. A lei permite a compensação das horas do sábado durante a semana.</p>
	<h1 style="font-size:100%;">&nbsp;Intervalos</h1>   
	<p>&nbsp;Antes da lei: Não havia definição sobre a obrigatoriedade do intervalo antes da nova lei, ficando a critério do empregador e do empregado.
</br>&nbsp;Com a lei: Quem trabalha 8 horas por dia deve fazer um intervalo de no mínimo de 1 hora e no máximo 2. Para jornadas de até 6 horas a pausa deve ser de 15 minutos.
</br>&nbsp;Pendências: Intervalos menores do que os estipulados pela nova lei devem ser aprovados por convenções ou acordos coletivos de trabalho. Atualmente, o Ministério do Trabalho aconselha que o empregador conceda o intervalo regulamentado, mesmo que o funcionário queira dispensá-lo.</p>

	
	                    </form>
	               
	            </div>
		
	</div>
</div>
<c:import url="rodape.jsp"/>
</body>
</html>