<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    
<!DOCTYPE html>
<html>
<head>

 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="icon" href="/favicon.ico" type="image/x-icon" />
 <link href="/opp-webapp/css/style.css" rel="stylesheet">
 <link rel='stylesheet' href='/opp-webapp/webjars/bootstrap/3.3.5/css/bootstrap.min.css'>
 <script type='text/javascript' src='/opp-webapp/webjars/jquery/1.11.1/jquery.min.js'></script>
 <script type='text/javascript' src='/opp-webapp/webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Registracija</title>
</head>
<body>

<nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <p class="navbar-brand"><b>Kod Nas Je Najljepse</b></p>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      <ul class="nav navbar-nav navbar-left">
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
        <c:when test="${sessionScope.korisnik != null}">
        <li>Prijavljen ${sessionScope.korisnik.ime}</li>
        </c:when>
        <c:otherwise>
          <li><a href="/opp-webapp/">Pocetna</a></li>

        </c:otherwise>
        </c:choose>
       </ul>
    </div>
  </div>
</nav>

<div id="left">

<div id="loginbox" class="topmargin mainbox col-md-8  col-sm-8 col-sm-offset-2"> 
	<div class="panel white-alpha-90">
		<div class="panel-heading">
			<div class="panel-title text-center"><h2>Prijava</h2></div>
			<hr>
		</div>     
		<div class="panel-body">
			<div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
				<form id="loginform" class="form-horizontal" role="form">
					
				<label>Korisnicko ime:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="KorisnickoIme" placeholder="Unesite svoje korisnicko ime">
				</div>
				<br>
				<label>Lozinka:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="Lozinka" placeholder="Unesite Lozinku">
				</div>
				<br>
				<div class="input-group col-xs-12 text-center login-action">
					<label>
					  <span id="btn-login"><a href="#" class="btn btn-primary">Prijavi se</a></span>
					</label>
				  </div>
				</div>
				<div style="margin-top:10px" class="form-group">
					<div class="col-sm-12 controls">
					  
					</div>
				</div>
			</form>     
		</div>                     
	</div>  
</div>

</div>

<div id="right">



 
<div id="loginbox" class="topmargin mainbox col-md-9 col-sm-8 col-sm-offset-2"> 
	<div class="panel white-alpha-90">
		<div class="panel-heading">
			<div class="panel-title text-center"><h2>Registracija</h2></div>
			<hr>
		</div>     
		<div class="panel-body">
			<div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
				<form id="loginform" class="form-horizontal" role="form">
				<label>Ime:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span>
					<input id="login-username" type="text" class="form-control" name="Ime" value="" placeholder="Unesite ime">                                        
				</div>
				<br>
				<label>Prezime:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="Prezime" placeholder="Unesite prezime">
				</div>
				<br>
				<label>Telefonski broj:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="Telefon" placeholder="Unesite broj telefona">
				</div>
				<br>
				<label>Korisnicko ime:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="KorisnickoIme" placeholder="Unesite zeljeno korisnicko ime">
				</div>
				<br>
				<label>Email adresa:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="Email" placeholder="Unesite email">
				</div>
				<br>
				<label>Lozinka:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="Lozinka" placeholder="Unesite Lozinku">
				</div>
				<br>
				<label>Potvrda lozinke:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="LonzikaPotvrda" placeholder="Potvrdite lozinku">
				</div>
				<br>
				<div class="input-group col-xs-12 text-center login-action">
					<label>
					  <span id="btn-login"><a href="#" class="btn btn-primary">Registriraj se</a></span>
					</label>
				  </div>
				</div>
				<div style="margin-top:10px" class="form-group">
					<div class="col-sm-12 controls">
					  
					</div>
				</div>
			</form>     
		</div>                     
	</div>  
</div>


</div>

</body>
</html>