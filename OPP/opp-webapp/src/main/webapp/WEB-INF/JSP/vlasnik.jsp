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

.topmargin {
	margin-top: 60px;
}

#urlColorWhite {
	color: white;
}

#urlColorWhite:hover {
	color: gray;
	text-decoration: none;
}

#headershadow {
	box-shadow: 0px 5px 20px gray;
}

#logoshaddow {
	text-shadow: 1px 1px 5px gray;
}

#headerUrl {
	color: gray;
}

#headerUrl:hover {
	text-decoration: none;
	color: #7041f9;
}
table, th, td {
    border: 1px solid white;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	function otvoriApartmane() {
		$("#dodajApartman").show();
		$("#dodajObjekt").hide();
		$("#dodajOpis").hide();
		$("#dodajAdmina").hide();
		$("#dodajFotografiju").hide();
	}
	$(document).ready(function() {
		$("#dodajObjekt").show();
		$("#dodajApartman").hide();
		$("#dodajOpis").hide();
		$("#dodajAdmina").hide();
		$("#dodajFotografiju").hide();
		$("#dodajObjektForma").hide();
		$("#dodajApartmanForma").hide();
		$("#dodajOpisForma").hide();
		$("#dodajAdminaForma").hide();
		$("#dodajSlikuForma").hide();
		$("#otvoriObjekte").click(function() {
			$("#dodajObjekt").show();
			$("#dodajApartman").hide();
			$("#dodajOpis").hide();
			$("#dodajAdmina").hide();
			$("#dodajFotografiju").hide();
		});
		$("#otvoriApartmane").click(function() {
			$("#dodajApartman").show();
			$("#dodajObjekt").hide();
			$("#dodajOpis").hide();
			$("#dodajAdmina").hide();
			$("#dodajFotografiju").hide();
		});
		$("#otvoriOpis").click(function() {
			$("#dodajAdmina").hide();
			$("#dodajApartman").hide();
			$("#dodajOpis").show();
			$("#dodajObjekt").hide();
			$("#dodajFotografiju").hide();
		});
		$("#otvoriAdministratore").click(function() {
			$("#dodajAdmina").show();
			$("#dodajApartman").hide();
			$("#dodajOpis").hide();
			$("#dodajObjekt").hide();
			$("#dodajFotografiju").hide();
		});
		$("#otvoriSlike").click(function() {
			$("#dodajAdmina").hide();
			$("#dodajApartman").hide();
			$("#dodajOpis").hide();
			$("#dodajObjekt").hide();
			$("#dodajFotografiju").show();
		});
		$("#dodajNoviObjekt").click(function() {
			$("#dodajObjektForma").show();
		});
		$("#zatvoriNoviObjekt").click(function() {
			$("#dodajObjektForma").hide();
		});
		$("#dodajNoviApartman").click(function() {
			$("#dodajApartmanForma").show();
		});
		$("#zatvoriNoviApartman").click(function() {
			$("#dodajApartmanForma").hide();
		});
		$("#dodajNoviOpis").click(function() {
			$("#dodajOpisForma").show();
		});
		$("#zatvoriNoviOpis").click(function() {
			$("#dodajOpisForma").hide();
		});
		$("#dodajNovogAdmina").click(function() {
			$("#dodajAdminaForma").show();
		});
		$("#zatvoriNoviAdmin").click(function() {
			$("#dodajAdminaForma").hide();
		});
		$("#dodajNovuSliku").click(function() {
			$("#dodajSlikuForma").show();
		});
		$("#zatvoriNovuSliku").click(function() {
			$("#dodajSlikuForma").hide();
		});
	});
</script>
</head>
<body>

	<% String pogled = (String) request.getAttribute("pogled");
	if (pogled != null && pogled.equals("apartman")) {
		out.print("otvoriApartmane();");
	}
		%>
	
	<nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<p class="navbar-brand">
      				<p class="navbar-brand" id="logoshaddow"><b><a id="headerUrl" href="/opp-webapp/">Kod Nas Je Najljepše</a></b></p> 	
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
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 3}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/vlasnik">Konfiguracija sustava</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/statistika">Statistika</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/ulogirani">Ulogirani korisnici</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 2}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/admin">Promjena rezervacija</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/ulogirani">Ulogirani korisnici</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:otherwise>
							<li><a href="/opp-webapp/registracija">Prijava/registracija</a></li>

						</c:otherwise>
					</c:choose>
					
					
					
				</ul>
			</div>
		</div>
	</nav>
		
	<div class="container topmargin">
		<h2>Konfiguracija sustava</h2>
		<hr>
		<button name="button" class="btn btn-default" id="otvoriObjekte">Objekti</button>
		<button name="button" class="btn btn-default" id="otvoriApartmane">Apartmani</button>
		<button name="button" class="btn btn-default" id="otvoriOpis">Opis
			apartmana</button>
		<button name="button" class="btn btn-default" id="otvoriSlike">Slike
			apartmana</button>
		<button name="button" class="btn btn-default"
			id="otvoriAdministratore">Administratori</button>
		
		<br>
		<c:if test="${error != null}">
				<div class="col-md-4 col-md-offset-2" id="greska" style="color: red">
					<c:out value="${error}" />
				</div>
		</c:if>
		<p></p>	
		
		<div id="dodajObjekt">
			<h2>Objekti</h2>
			<div style="width:30%">
				<table class="table">
				<tbody>
					<c:forEach var="o" items="${objekti}">
						<form method="post">
							<tr>
								<td><a href="objekt?id=${o.objektID}">${o.nazivObjekt}
								</a></td>
								<td><a class="btn btn-info"
									href="vlasnik/objekt/uredi?id=${o.objektID }">Uredi</a> 
									<input type="hidden" name="objektID" value="${o.objektID}">
									<button type="submit" name="method" value="obrisiObjekt"
										class="btn btn-danger">Obriši</button></td>
								
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<hr>
			<br>
			<p>
				<button id="dodajNoviObjekt" class="btn btn-default">Dodaj
					novi objekt</button>
				<button id="zatvoriNoviObjekt" class="btn btn-default">Zatvori</button>
			</p>
			<form action="" class="form-horizontal" method="post"
				id="dodajObjektForma">
				<div class="form-group">
					<div class="col-md-4">
						<label>Naziv objekta</label> <input type="text" name="nazivObjekt"
							class="form-control" placeholder="Upiši naziv objekta" required> <br>
						<label>URL slike</label> <input type="text" name="fotografija"
							class="form-control" placeholder="Upiši URL slike"> <br>
						<button type="submit" id="submit" name="method" value="dodajObjekt"
							class="btn btn-success">Dodaj objekt</button>
					</div>
				</div>
			</form>
		</div>
		<div class="form-horizontal" id="dodajApartman">
			<h2>Apartmani</h2>

			<table class="table" style="width:30%">
				<tbody>
					<c:forEach var="a" items="${apartmani}">
						<form method="post">
							<tr>
								<td><a href="apartman?id=${a.apartmanID}">${a.nazivApartman}
								</a></td>
								<td><a class="btn btn-info"
									href="vlasnik/apartman/uredi?id=${a.apartmanID }">Uredi</a> 
									<input type="hidden" name="apartmanID" value="${a.apartmanID}">
									<button type="submit" name="method" value="obrisiApartman"
										class="btn btn-danger">Obriši</button></td>
							
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
			<hr>
			<br>
			<p>
				<button id="dodajNoviApartman" class="btn btn-default">Dodaj
					novi apartman</button>
				<button id="zatvoriNoviApartman" class="btn btn-default">Zatvori</button>
			</p>
			<form class="form-horizontal" method="post"
				id="dodajApartmanForma">
				<div class="form-group">
					<div class="col-md-4">
						<label>Naziv apartmana</label> <input type="text" name="nazivApartman"
							class="form-control" placeholder="Upiši naziv apartmana">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<label>Objekt</label> <select class="form-control" name="objektID">
							<c:forEach items="${objekti}" var="o">
								<option value="${o.objektID}">${o.nazivObjekt}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<label>Opis</label> <select class="form-control" name="opisID">
							<c:forEach items="${opisi}" var="o">
								<option value="${o.opisID}">${o.naslov}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<button type="submit" id="submitApartman" name="method" value="dodajApartman"
							class="btn btn-success">Dodaj apartman</button>
					</div>
				</div>
			</form>
		</div>
		<div id="dodajOpis">
			<h2>Opis apartmana</h2>

			<table class="table" style="width:30%">
				<tbody>
					<c:forEach var="o" items="${opisi}">
						<form method="post">

							<tr>
								<td>${o.naslov}</td>
								<td><a class="btn btn-info"
									href="vlasnik/opis/uredi?id=${o.opisID }">Uredi</a> <input type="hidden" name="opisID" value="${o.opisID}">
									<button type="submit" name="method" value="obrisiOpis"
										class="btn btn-danger">Obriši</button></td>
			
							</tr>
							
						</form>
					</c:forEach>
				</tbody>
			</table>
			<hr>
			<br>
			<p>
				<button id="dodajNoviOpis" class="btn btn-default">Dodaj
					novi opis</button>
				<button id="zatvoriNoviOpis" class="btn btn-default">Zatvori</button>
			</p>
			<form action="" class="form-horizontal" method="post"
				id="dodajOpisForma">
				
				<div class="form-group">
					<div class="col-md-4">
						<label>Naslov opisa</label> <input type="text" name="naslov"
							class="form-control" placeholder="Upiši naslov opisa">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-4">
						<label>Kat</label> <input type="number" name="kat"
							class="form-control" placeholder="Upiši kat">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<label>Pogled</label> <select class="form-control" name="pogled">
							<option value="Suma">Šuma</option>
							<option value="More">More</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<label>Minimalan broj osoba</label> <input type="number"
							name="minBroj" class="form-control"
							placeholder="Minimalan broj osoba">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<label>Maksimalan broj osoba</label> <input type="number"
							name="maxBroj" class="form-control"
							placeholder="Maksimalan broj osoba">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<label>Opis</label>
						<textarea rows="4" cols="5" name="opis" class="form-control"
							placeholder="Upiši opis"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-4">
						<button type="submit" id="submitOpis" name="method" value="dodajOpis"
							class="btn btn-success">Dodaj opis</button>
					</div>
				</div>
			</form>
		</div>
		<div id="dodajFotografiju">
			<h2>Slike</h2>
			<hr>
			<c:forEach var="s" items="${slike}">
				<form method="post">
					<img src="${s.fotoDatoteka}"  width="100" height="100"> <input
						type="hidden" name="fotoID" value="${s.fotoID}">
					<button type="submit" name="method" value="obrisiFotografiju"
						class="btn btn-danger">Obriši</button>
				</form>
				<br>
			</c:forEach>
			<hr>
			<br>
			<p>
				<button id="dodajNovuSliku" class="btn btn-default">Dodaj
					sliku</button>
				<button id="zatvoriNovuSliku" class="btn btn-default">Zatvori</button>
			</p>
			<form action="" class="form-horizontal" method="post"
				id="dodajSlikuForma">
				<div class="form-group">
					<div class="col-md-4">
						<label>URL slike</label> <input type="text" name="fotoDatoteka"
							class="form-control" placeholder="Upiši URL slike">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<label>Opis apartmana</label> <select class="form-control" name="opisID">
							<c:forEach items="${opisi}" var="o">
								<option value="${o.opisID}">${o.naslov}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<button type="submit" id="submitSlika" name="method" value="dodajFotografiju"
							class="btn btn-success">Dodaj sliku</button>
					</div>
				</div>
			</form>
		</div>
		<div id="dodajAdmina">
			<h2>Administratori</h2>
			<table class="table" style="width:30%">
				<tbody>
					<c:forEach var="a" items="${administratori}">
						<form method="post">
							<tr>
								<td>${a.ime} ${a.prezime}</a></td>
								<td><input type="hidden" name="adminID"
									value="${a.korisnikID}"></td>
								<td>
									<button type="submit" name="method" value="obrisiAdmina"
										class="btn btn-danger">Obriši</button>
								</td>
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
			<hr>
			<br>
			<c:if test="${brojac <= 3}">
				<p>
					<button id="dodajNovogAdmina" class="btn btn-default">Dodaj
						administratora</button>
					<button id="zatvoriNoviAdmin" class="btn btn-default">Zatvori</button>
				</p>
			</c:if>
			<form action="" class="form-horizontal" method="post"
				id="dodajAdminaForma">
				<div class="form-group">
					<div class="col-md-4">
						<label>Korisnici</label> 
						<select class="form-control"
							name="adminID">
							<c:forEach items="${korisnici}" var="k">
								<option value="${k.korisnikID}">${k.ime}
									${k.prezime}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<button type="submit" id="submitAdmin" name="method" value="dodajAdmina"
							class="btn btn-success">Dodaj administratora</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>