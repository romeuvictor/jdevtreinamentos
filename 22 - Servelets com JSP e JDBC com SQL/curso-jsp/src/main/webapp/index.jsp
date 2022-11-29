<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
	<title>"Bem vindo ao curso de JSP!"</title>
	
<style>
	form{
	position: absolute;
	top: 40%;
	left: 33%;
	rigth: 33%
	
	}
	h5{
	position: absolute;
	top:30%;
	left:33%;
	}
	.msg{
	position: absolute;
	top:120%;
	left:25%;
	font-size: 15px;
	color: #664d03;
	bg: #fff3cd;
	border-color: #ffecb5;
	}
	
</style>
</head>
<body>
	<h5>Bem vindo ao Curso JSP</h5>
	<input type="hidden" value= <%= request.getParameter("url")%> name="url">
	
	<form action="ServletLogin" method="POST" class="row g-3 needs-validation"  novalidate>

	 <div class="mb-3">	
		<label class="form-label" for="login">Login :</label>
		<input class="form-control" id="login" name="login" type="text" required>
			<div class="invalid-feedback">
    	 		Obrigatório!
    		</div>
    			<div class="valid-feedback">
    	 		OK!
    		</div>
		
	</div>
	
	 <div class="mb-3">	
		<label class="form-label" for="senha">Senha :</label>
		<input class="form-control"  id="senha" name="senha" type="password" required>
		    <div class="invalid-feedback">
    	 		Obrigatório!
    		</div>
    		<div class="valid-feedback">
    	 		OK!
    		</div>
	</div>
	
		<input type="submit" value="Acessar"  class="btn btn-primary">


<h5 class="msg">${msg}</h5>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  <script>
//Example starter JavaScript for disabling form submissions if there are invalid fields
  (() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
      form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
  })()
  
  </script>

</body>
</html>