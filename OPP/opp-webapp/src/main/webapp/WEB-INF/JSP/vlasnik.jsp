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