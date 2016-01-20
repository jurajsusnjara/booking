<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js">

<style type="text/css">
		.greska {
		   font-family: fantasy;
		   font-weight: bold;
		   font-size: 0.9em;
		   color: #FF0000;
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#dodajObjekt").hide();
		$("#dodajApartman").hide();
		$("#dodajAdmina").hide();

		$("#otvoriObjekte").click(function() {
			$("#dodajObjekt").show();
			$("#dodajApartman").hide();
			$("#dodajAdmina").hide();
		});
		$("#zatvori").click(function() {
			$("#dodajObjekt").hide();
			$("#dodajApartman").hide();
			$("#dodajAdmina").hide();
		});
		$("#otvoriApartmane").click(function() {
			$("#dodajApartman").show();
			$("#dodajObjekt").hide();
			$("#dodajAdmina").hide();
		});
		$("#otvoriAdministratore").click(function() {
			$("#dodajAdmina").show();
			$("#dodajApartman").hide();
			$("#dodajObjekt").hide();
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
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/vlasnik">Konfiguracija sustava</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/statistika">Statistika</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 3}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/admin">Promjena rezervacija</p></a></li>
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
	<div class="container">

		<h2>Konfiguracija sustava</h2>

		<hr>



		<button name="button" class="btn btn-default" id="otvoriObjekte">Objekti</button>
		<button name="button" class="btn btn-default" id="otvoriApartmane">Apartmani</button>
		<button name="button" class="btn btn-default"
			id="otvoriAdministratore">Administratori</button>
		<button name="button" class="btn btn-default" id="zatvori">Zatvori</button>

		<p></p>

		<div class="form-horizontal" id="dodajObjekt">

			<h2>Objekti</h2>

			<hr>


			<c:forEach var="o" items="${objekti}">

				
				<form method="post">
					<a href="vlasnik/objekt/${o.objektID}">${o.nazivObjekt} </a> 
					<a class="btn btn-info" href="vlasnik/objekt/uredi/${o.objektID }">Uredi</a>
					<input type="hidden" name="objektID" value="${o.objektID}" >
					<button type="submit" name="method" value="obrisiObjekt"
						class="btn btn-danger">Obriši</button>
				</form>
				

			</c:forEach>

			<p>
				<a href="vlasnik/objekt/dodaj" class="btn btn-success">Dodaj
					novi objekt</a>
			</p>


		</div>

		<div class="form-horizontal" id="dodajApartman">

			<h2>Apartmani</h2>

			<hr>

			<c:forEach var="a" items="${apartmani}">

				<p>
					<a href="vlasnik/apartman/${a.apartmanID}">${a.nazivApartman} </a>
					<a class="btn btn-info"
						href="vlasnik/apartman/uredi/${a.apartmanID }">Uredi</a> <a
						class="btn btn-danger" href="#">Obriši</a>
				</p>

			</c:forEach>

			<p>
				<a href="vlasnik/apartman/dodaj" class="btn btn-success">Dodaj
					novi apartman</a>
			</p>

		</div>

		<div class="form-horizontal" id="dodajAdmina">

			<h2>Administratori</h2>

			<hr>

			<c:forEach var="a" items="${administratori}">

				<p>
					<a href="vlasnik/administrator/${a.korisnikID}">${a.ime}
						${a.prezime}</a>  <a class="btn btn-danger" href="#">Obriši</a>
				</p>

			</c:forEach>

			<p>
				<a href="vlasnik/administrator/dodaj" class="btn btn-success">Dodaj
					novog administratora</a>
			</p>

		</div>

	</div>

</body>
</html>