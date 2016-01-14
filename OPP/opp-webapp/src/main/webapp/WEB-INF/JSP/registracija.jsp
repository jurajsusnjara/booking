<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    
<!DOCTYPE html>
<html>
<head>
<style>

#left{
	float: left;
	width: 50%;
	height: 1000px;
	
}

#headershadow{
    box-shadow: 0px 5px 20px gray;
}

.topmargin{
	margin-top: 100px;
}

#right{
	float: right;
	width: 50%;
	height: 1000px;
    border-left: 1px solid;	
    border-left-color: #a1b3ec;
    }
    
 #logoshaddow{
    text-shadow: 1px 1px 5px gray;
}

#logoshaddow{
    text-shadow: 1px 1px 5px gray;
}

#logoUrl{
	color: gray;
}

#logoUrl:hover {
    color: white;
    text-decoration: none;
}
    

</style>

 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="icon" href="/favicon.ico" type="image/x-icon" />
 <link href="/opp-webapp/css/style.css" rel="stylesheet">
 <link rel='stylesheet' href='/opp-webapp/webjars/bootstrap/3.3.5/css/bootstrap.min.css'>
 <script type='text/javascript' src='/opp-webapp/webjars/jquery/1.11.1/jquery.min.js'></script>
 <script type='text/javascript' src='/opp-webapp/webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>

<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Registracija</title>
</head>
<body>

<nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      	<p class="navbar-brand" id="logoshaddow"><b><a id="logoUrl" href="/opp-webapp/">Kod Nas Je Najljepse</a></b></p> 	
      	 	
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      <ul class="nav navbar-nav navbar-left">
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
				<c:when test="${sessionScope.korisnik != null}">
				<li><a href="/opp-webapp/odjava">Odjava:
						${sessionScope.korisnik.getIme()}</a></li>
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
				<form id="loginform" class="form-horizontal" role="form" method="post">
					
				<label>Email:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="korisnickoIme" placeholder="Unesite svoj email">
				</div>
				<br>
				<label>Lozinka:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="password" class="form-control" name="sifra" placeholder="Unesite Lozinku">
				</div>
				<br>
				<div class="input-group col-xs-12 text-center login-action">
					<label>
					  <button id="login" type="submit" name="method" value="login" class="btn btn-success">Prijavi se</button>
					
					  <!-- <span id="btn-login"><a href="#" class="btn btn-primary" value="login">Prijavi se</a></span> -->
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
				<form id="loginform" class="form-horizontal" role="form" method="post">
				<label>Ime:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span>
					<input id="login-username" type="text" class="form-control" name="Ime" value="" placeholder="Unesite ime"> 
					<!-- <input id="usernameinput" name="korisnickoime" type="text" class="form-control input-md" value="${forma.korisnickoime}" placeholder="Unesite korisničko ime" aria-describedby="errorstatus"> -->
					                                       
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
				<label></label>
				<div class="input-group">
					<span class="input-group"><i class="fa fa-lock"></i></span>
					<h3><b>Podaci o prebivalistu</b></h3>
				<hr>
				</div>
				
				<label>Adresa:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="Adresa" placeholder="Unesite adresu (ulica i postanski broj)">
				</div>
				<br><label>Grad:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="Grad" placeholder="Unesite ime grada">
				</div>
				<br>
				<br><label>Drzava:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="Drzava" placeholder="Unesite ime drzave">
				</div>
				<br>
				<br><label>Postanski broj:</label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input id="login-password" type="text" class="form-control" name="PostanskiBroj" placeholder="Unesite postanski broj">
				</div>
				<br>
				
				<br>
				<br>
				<div class="input-group col-xs-12 text-center login-action">
					<label>
					 <!-- <span id="btn-login"><a href="#" class="btn btn-primary">Registriraj se</a></span> --> 
					  <button id="register" type="submit" name="method" value="register" class="btn btn-success">Registriraj se</button>
					  
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