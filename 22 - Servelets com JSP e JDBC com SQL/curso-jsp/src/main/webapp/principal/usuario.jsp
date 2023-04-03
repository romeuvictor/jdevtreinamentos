<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->

<jsp:include page="theme-loader.jsp"></jsp:include>

<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
	<div class="pcoded-overlay-box"></div>
	<div class="pcoded-container navbar-wrapper">
		<jsp:include page="navbar.jsp"></jsp:include>

<div class="pcoded-main-container">
	<div class="pcoded-wrapper">

		<jsp:include page="navamainmenu.jsp"></jsp:include>

<div class="pcoded-content">
	<!-- Page-header start -->

	<jsp:include page="page-header.jsp"></jsp:include>
	<!-- Page-header end -->
	<div class="pcoded-inner-content">
		<!-- Main-body start -->
		<div class="main-body">
			<div class="page-wrapper">
				<!-- Page-body start -->
				<div class="page-body">

					<div class="row">
						<div class="col-sm-12">
							<!-- Basic Form Inputs card start -->
							<div class="card">

								<div class="card-block">
									<h4 class="sub-title">Cad. Usuário</h4>

									<form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath()%>/ServletUsuarioController" method="POST" id="formUser">
									
										<input type="hidden" name="acao" id="acao" value="">
									
										<div class="form-group form-default form-static-label">
											<input type="text" name="id" id="id" readonly="readonly" class="form-control" value="${modolLogin.id}"> 		
											<span class="form-bar"></span>															
											<label class="float-label">ID:</label>
										</div>
										
										<div class="form-group form-default input-group mb-4">
										  <div class="input-group-prepend">
										  	
										  	<c:if test="${modolLogin.fotoUser != '' && modolLogin.fotoUser != null}">
										  		<a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=downloadFoto&id=${modolLogin.id}">
										  	 	<img alt="Imagem User" id="fotobase64" src="${modolLogin.fotoUser}" width="70px">
										  	 	</a>
										  	</c:if>
										  	
										  	<c:if test= "${modolLogin.fotoUser == '' || modolLogin.fotoUser == null }">
										  		 <img alt="Imagem User" id="fotobase64" src="assets/images/user.jpeg" width="70px">
										  	</c:if>
										   
										  </div>
										  
										  <input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotobase64', 'fileFoto')" class="form-control-file" style="margin-top: 20px; margin-left: 5px">
							
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="nome" id="nome" class="form-control  form-static-label" required="required" value="${modolLogin.nome}"> 																	
											<span class="form-bar"></span>					
											<label class="float-label">Nome:</label>
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="email" name="email" id="email" autocomplete="off" class="form-control" required="required" value="${modolLogin.email}">
											<span class="form-bar"></span>		
											<label class="float-label">Email:</label>							
										</div>
										
										<div class="form-group form-default form-static-label">
										<select class="form-control" 
										aria-label="Default select example" name="perfil">
											  <option disabled="disabled">[Selecione o perfil]</option>
											 
											  <option value="ADMIN" <% 															
											  
											   ModelLogin modelLogin = (ModelLogin) request.getAttribute("modolLogin");
											 
											  if(modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){
												  out.println(" ");
												   out.print("selected=\"selected\"");
												  out.println(" ");
									          } %>>Admin</option>
											  
											  <option value="SECRETARIA" <%
											  
											 modelLogin = (ModelLogin) request.getAttribute("modolLogin");
												 
											  if(modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")){
												  out.println(" ");
												   out.print("selected=\"selected\"");
												  out.println(" ");
									          } %>>Secretária</option>
											  
											  <option value="AUXILIAR"<%
													  modelLogin = (ModelLogin) request.getAttribute("modolLogin");
												 
											  if(modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")){
												  out.println(" ");
												   out.print("selected=\"selected\"");
												  out.println(" ");
									          } %>>Auxiliar</option>
											</select>
											<span class="form-bar"></span>		
											<label class="float-label">Perfil:</label>
										</div>
										
										<div class="form-group form-default form-static-label">
											<input onblur="pesquisaCep()" type="text" name="cep" id="cep" autocomplete="off" class="form-control" required="required" value="${modolLogin.cep}">
											<span class="form-bar"></span>		
											<label class="float-label">CEP:</label>							
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="logadouro" id="logadouro" autocomplete="off" class="form-control" required="required" value="${modolLogin.logadouro}">
											<span class="form-bar"></span>		
											<label class="float-label">Logadouro:</label>							
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="bairro" id="bairro" autocomplete="off" class="form-control" required="required" value="${modolLogin.bairro}">
											<span class="form-bar"></span>		
											<label class="float-label">Bairro:</label>							
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="localidade" id="localidade" autocomplete="off" class="form-control" required="required" value="${modolLogin.localidade}">
											<span class="form-bar"></span>		
											<label class="float-label">Localidade:</label>							
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="uf" id="uf" autocomplete="off" class="form-control" required="required" value="${modolLogin.uf}">
											<span class="form-bar"></span>		
											<label class="float-label">UF:</label>							
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="numero" id="numero" autocomplete="off" class="form-control" required="required" value="${modolLogin.numero}">
											<span class="form-bar"></span>		
											<label class="float-label">Numero:</label>							
										</div>
										
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="login" id="login" autocomplete="off" class="form-control" required="required" value="${modolLogin.login}">
											<span class="form-bar"></span>		
											<label class="float-label">Login:</label>							
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="password" name="senha" id="senha" autocomplete="off" class="form-control" required="required" value="${modolLogin.senha}"> 																	
											<span class="form-bar"></span>		
											<label class="float-label">Senha:</label>
										</div>
										
										
										
										<div class="form-group form-default form-static-label">
											<input type="radio" name="sexo" checked="checked" value="MASCULINO"<% 															
											  
											  modelLogin = (ModelLogin) request.getAttribute("modolLogin");
											 
											  if(modelLogin != null && modelLogin.getSexo().equals("MASCULINO")){
												  out.println(" ");
												   out.print("checked=\"checked\"");
												  out.println(" ");
									          } %>
											>Masculino </>
											<input type="radio" name="sexo" value="FEMININO" <% 															
											  
											  modelLogin = (ModelLogin) request.getAttribute("modolLogin");
											 
											  if(modelLogin != null && modelLogin.getSexo().equals("FEMININO")){
												  out.println(" ");
												   out.print("checked=\"checked\"");
												  out.println(" ");
									          } %> >Feminino </>
										</div>
										
										 <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm()">Novo</button>
										 <button type="submit" class="btn btn-primary waves-effect waves-light">Salvar</button>
										 <button type="button" class="btn btn-primary waves-effect waves-light" onclick="criaDeleteComAjax()">Excluír</button>
										 <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
									</form>
									</div>
								</div>
							</div>
						</div>
							<span id="msg">${msg}</span>
							
						<div style="height: 300px; overflow: scroll;">
							<table class="table" id="tabelaResuldatoView">
							  <thead>
							    <tr>
							      <th scope="col">ID</th>
							      <th scope="col">Nome</th>
							      <th scope="col">Ver</th>
							    </tr>
							  </thead>
							  <tbody>
							  	<c:forEach items='${modelLogins}' var='ml'>
							  		<tr>
							  			<td><c:out value="${ml.id}"></c:out></td>
							  			<td><c:out value="${ml.nome}"></c:out></td>
							  			<td><a class="btn btn-success" href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}"> Ver</a></td>
							  		</tr>
							  	</c:forEach>
					
									  </tbody>
									</table>
							</div>
						</div>
					</div>
					<div id="styleSelector"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="javascriptfile.jsp"></jsp:include>
<!-- Modal -->
<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
			<div class="input-group mb-3">
			  <input type="text" class="form-control" id="nomeBusca" placeholder="Nome" aria-label="Nome" aria-describedby="basic-addon2">
			  <div class="input-group-append">
			    <button class="btn btn-success" type="button" onClick="buscarUsuario()">Buscar</button>
  </div>
</div>

<div style="height: 300px;overflow: scroll;">
				<table class="table" id="tabelaResuldatos">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Nome</th>
				      <th scope="col">Ver</th>
				    </tr>
				  </thead>
				  <tbody>
				   
				  </tbody>
				</table>
				
			 </div>
			 <span id="totalResultados"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">

function pesquisaCep() {
	
	var cep = $("#cep").val();
	
    $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
    	
    	 if (!("erro" in dados)) {
    			
    		    $("#cep").val(dados.cep);
    		    $("#logadouro").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#localidade").val(dados.localidade);
                $("#uf").val(dados.uf);
    		 
    	 }
		
	});
	
}

function visualizarImg(fotobase64, fileFoto){
	
	var preview = document.getElementById(fotobase64);
	var fileUser = document.getElementById(fileFoto).files[0];
	var reader = new FileReader();
	
	reader.onloadend = function(){
		preview.src = reader.result; //carrega a image
	}
	
	if(fileUser){
		reader.readAsDataURL(fileUser);//preview da imagem
	}else{
		preview.src= '';
		
	}
	
}

function verEditar(id){
	
	var urlAction = document.getElementById('formUser').action;
	window.location.href = urlAction + '?acao=buscarEditar&id=' +id;
	
}

function buscarUsuario(){
	
	var nomeBusca = document.getElementById('nomeBusca').value;
	
	if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){
		
		var urlAction = document.getElementById('formUser').action;
		
		$.ajax({
			
			method : "get",
			url    : urlAction,
			data   : "nomeBusca=" + nomeBusca + "&acao=buscarUserAjax",
			success: function(response){
								
				var json = JSON.parse(response);
				
				$('#tabelaResuldatos > tbody > tr').remove();
				
				for(var p =0; p < json.length; p ++){
					$('#tabelaResuldatos > tbody').append('<tr><td>' +json[p].id+'</td><td>'+json[p].nome+'</td><td><button type="button" class="btn btn-info" onclick="verEditar('+json[p].id+')">Ver</button></td></tr>');					
				}
				document.getElementById('totalResultados').textContent = "Resultados :"+json.length;
					
			}
			
		}).fail(function(xhr, status, errorThrown){
			alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
		});
		
	}
}

function criaDeleteComAjax(){
	
	if(confirm("Deseja realmente excluír os dados?")){
			
	var urlAction = document.getElementById('formUser').action;
	var idUser = 	document.getElementById('id').value;
	
		$.ajax({
			
			method : "get",
			url    : urlAction,
			data   : "id=" + idUser + "&acao=deletarajax",
			success: function(response){
				limparForm();
				document.getElementById('msg').textContent = response;
				
			}
			
		}).fail(function(xhr, status, errorThrown){
			alert('Erro ao deleter usuario por id: ' + xhr.responseText);
		});
		
	}
	
	
}

function criarDelete(){
	
	if(confirm('Deseja realmente excluír os dados?')){
	
		document.getElementById("formUser").method = 'GET';
		document.getElementById("acao").value = 'deletar';
		document.getElementById("formUser").submit();
	}	
}
	
function limparForm(){
		
		var elementos = document.getElementById("formUser").elements;//Retorna os elementos dentro do formulario
		
		for(p = 0; p < elementos.length; p++){
			elementos[p].value = '';
			
		}
	}
 
</script>
</body>

</html>
