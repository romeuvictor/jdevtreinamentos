<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
                                            <h1>Conteúdo da página do sistema Página base conteúdo</h1>
                                		</div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>
	</body>

</html>
    