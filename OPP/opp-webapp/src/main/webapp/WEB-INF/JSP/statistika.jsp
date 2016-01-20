<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.greska {
    font-family: fantasy;
    font-weight: bold;
    font-size: 0.9em;
    color: #FF0000;
}
     
#korisnikDetails{
    margin-top: 50px;
    width: 50%;
}
th, td {
    padding: 5px;
    text-align: left;
}
table, th, td {
    
    border-collapse: collapse;
}

.topmargin{
	margin-top: 60px;
}

#urlColorWhite{
	color: white;
}
#urlColorWhite:hover {
    color: gray;
    text-decoration: none;
}

#headershadow{
    box-shadow: 0px 5px 20px gray;
}

#logoshaddow{
    text-shadow: 1px 1px 5px gray;
}

#headerUrl{
	color: gray;
}

#headerUrl:hover{
	text-decoration: none;
	color: #7041f9;
}
</style> 

       
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="style.css" type="text/css">
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
<script type='text/javascript' src='/opp-webapp/webjars/jquery/1.11.1/jquery.min.js'></script>
<script type='text/javascript' src='/opp-webapp/webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<!-- Latest compiled and minified JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
    integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
    crossorigin="anonymous"></script>
 
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>
    $(document).ready(function() {
    	$("#mjesta").hide();
        $("#jedinice").hide();
        $("#usluge").hide();
        
    	$("#otvoriMjesta").click(function() {
            $("#mjesta").show();
            $("#jedinice").hide();
            $("#usluge").hide();
        });
        $("#zatvori").click(function() {
            $("#mjesta").hide();
            $("#jedinice").hide();
            $("#usluge").hide();
        });
        $("#otvoriJedinice").click(function() {
            $("#mjesta").hide();
            $("#jedinice").show();
            $("#usluge").hide();
        });
        $("#otvoriUsluge").click(function() {
            $("#mjesta").hide();
            $("#jedinice").hide();
            $("#usluge").show();
        });
        
    });
</script>
</head>

<body>
	 <nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<p class="navbar-brand">
      				<p class="navbar-brand" id="logoshaddow"><b><a id="headerUrl" href="/opp-webapp/">Kod Nas Je Najljepse</a></b></p> 	
				</p>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-left">
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 1}">
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 2}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/vlasnik">Dodavanje objekata</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/promjenaRezervacije">Promjena rezervacije</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 3}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/vlasnik">Dodavanje objekata</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/statistika">Statistika</p></a></li>
							<li> <p class="navbar-text">Moj profil (administrator): <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:otherwise>
							<li><a href="/opp-webapp/registracija">Prijava/registrcija</a></li>

						</c:otherwise>
					</c:choose>
					
					
					
				</ul>
			</div>
		</div>
	</nav>
	<div class="container topmargin">
	
		<button name="button" class="btn btn-default" id="otvoriMjesta">Otvori mjesta</button>
        <button name="button" class="btn btn-default" id="otvoriUsluge">Otvori usluge</button>
        <button name="button" class="btn btn-default" id="otvoriJedinice">Otvori jedinice</button>
        <button name="button" class="btn btn-default" id="zatvori">Zatvori</button>
		<p></p>
		
		<form action="" class="form-horizontal" method="post" id="mjesta">
		<p>mjesta</p>
		<img src="drzave">
		<img src="gradovi">
		</form>
		
		<form action="" class="form-horizontal" method="post" id="jedinice">
		<p>jedinice</p>
		</form>
		
		<form action="" class="form-horizontal" method="post" id="usluge">
		<p>usluge</p>
		<c:forEach var="item" items="${usluge}">
			<li>${item.zahtjev}<br>
				${item.n}<br>
				${item.topCountry}<br>
			</li>
			<br>
		</c:forEach>
		</form>
		
	</div>
	
</body>
</html>