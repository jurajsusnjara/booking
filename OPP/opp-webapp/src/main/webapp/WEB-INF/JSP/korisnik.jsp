<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>

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

#korisnikDetails {
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


<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="style.css" type="text/css">
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
<script type='text/javascript'
	src='/opp-webapp/webjars/jquery/1.11.1/jquery.min.js'></script>
<script type='text/javascript'
	src='/opp-webapp/webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>
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
		$("#filtriranje").hide();
		$("#password").hide();
		$("#rezervacija").hide();

		$("#otvoriFiltriranje").click(function() {
			$("#filtriranje").show();
			$("#password").hide();
			$("#rezervacija").hide();
		});
		$("#zatvori").click(function() {
			$("#filtriranje").hide();
			$("#password").hide();
			$("#rezervacija").hide();
		});
		$("#otvoriPassword").click(function() {
			$("#password").show();
			$("#filtriranje").hide();
			$("#rezervacija").hide();
		});
		$("#otvoriRezervacije").click(function() {
			$("#rezervacija").show();
			$("#password").hide();
			$("#filtriranje").hide();
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
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 3}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/vlasnik">Konfiguracija sustava</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/statistika">Statistika</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 2}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/admin">Promjena rezervacija</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
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
 
	<div class="container topmargin" id="korisnikDetails">
		<h2>Podaci o korisniku</h2>
		<hr>
		<br>
		<table class="table table-striped" id="t">
			<thead>
				<tr>
					<th>Korisnicko ime</th>
					<th><b>${sessionScope.korisnik.ime}</b></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Ime</td>
					<td>${sessionScope.korisnik.ime}</td>
				</tr>
				<tr>
					<td>Prezime</td>
					<td>${sessionScope.korisnik.prezime}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${sessionScope.korisnik.email}</td>

				</tr>
				<tr>
					<td>Uloga</td>
					<td><c:choose>
							<c:when test="${sessionScope.korisnik.uloga == 3}">
                        Vlasnik sustava
                </c:when>
							<c:when test="${sessionScope.korisnik.uloga == 2}">
                        Administrator sustava
                </c:when>
							<c:otherwise>
                        Korisnik
                </c:otherwise>
						</c:choose></td>
				</tr>
			</tbody>
		</table>
		<div class="container topmargin">
			<button name="button" class="btn btn-default" id="otvoriFiltriranje">Promjenite
				korisnicke postavke</button>
			<button name="button" class="btn btn-default" id="otvoriPassword">Promjeni
				lozinku</button>
			<button name="button" class="btn btn-default" id="otvoriRezervacije">Pogledaj
				rezervacije</button>
			<button name="button" class="btn btn-default" id="zatvori">Zatvori</button>
		</div>

		<p></p>

		<form action="" class="form-horizontal" method="post" id="filtriranje">


			<div class="form-group">
				<label class="col-md-4 control-label">Ime</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="text" name="ime" class="form-control"
							placeholder="Ime" value='<c:out value="${korisnik.ime}"/>'
							size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Prezime</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="text" name="prezime" class="form-control"
							placeholder="Prezime"
							value='<c:out value="${korisnik.prezime}"/>' size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Email</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="text" name="email" class="form-control"
							placeholder="Email" value='<c:out value="${korisnik.email}"/>'
							size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Telefon</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="text" name="telefon" class="form-control"
							placeholder="Telefon"
							value='<c:out value="${korisnik.telefon}"/>' size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Adresa</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="text" name="adresa" class="form-control"
							placeholder="Adresa"
							value='<c:out value="${korisnik.adresa.adresa}"/>' size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Grad</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="text" name="grad" class="form-control"
							placeholder="Grad"
							value='<c:out value="${korisnik.adresa.grad}"/>' size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Drzava</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="text" name="drzava" class="form-control"
							placeholder="Drzava"
							value='<c:out value="${korisnik.adresa.drzava}"/>' size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Postanski broj</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="text" name="postanskiBroj" class="form-control"
							placeholder="Postanski broj"
							value='<c:out value="${korisnik.adresa.postanskiBroj}"/>'
							size="50"><br>
					</div>
				</div>
			</div>

			<c:if test="${error != null}">
				<div class="greska">
					<c:out value="${error}" />
				</div>
			</c:if>
			<!-- Buttons -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="signup"></label>
				<div class="col-md-4">
					<button type="submit" name="method" value="promijeniPodatke"
						class="btn btn-success">Promijeni</button>

					<button id="reset" type="reset" name="reset"
						class="btn btn-warning">Resetiraj unesene promjene</button>

				</div>
			</div>


		</form>

		<form action="" class="form-horizontal" method="post" id="password">


			<div class="form-group">
				<label class="col-md-4 control-label">Stara lozinka</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="password" name="staraLozinka" class="form-control"
							placeholder="Stara lozinka" size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Nova lozinka</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="password" name="novaLozinka1" class="form-control"
							placeholder="Nova lozinka" size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Ponovi novu lozinku</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="password" name="novaLozinka2" class="form-control"
							placeholder="Nova lozinka" size="50"><br>
					</div>
				</div>
			</div>

			<c:if test="${error != null}">
				<div class="greska">
					<c:out value="${error}" />
				</div>
			</c:if>
			<!-- Buttons -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="signup"></label>
				<div class="col-md-4">
					<button type="submit" name="method" value="promijeniSifru"
						class="btn btn-success" value="promijeniPodatke">Promijeni</button>

					<button id="reset" type="reset" name="reset"
						class="btn btn-warning">Resetiraj unesene promjene</button>

				</div>
			</div>


		</form>

		<form action="" class="form-horizontal" method="post" id="rezervacija">

			<table class="table table-bordered">
                <tr>
                    <th> Apartman</th>
                    <th> Rezervirano od</th>
                    <th> Rezervirano do</th>       
                </tr>
               
                <c:forEach var="r" items="${rezervacije}">
           
                    <tr>
                        <td> ${r.apartman.nazivApartman }</td>
                        <td> ${r.rezerviranoOd }</td>      
                        <td> ${r.rezerviranoDo }</td>
                        <td style="border: 0; border-right: hidden; border-bottom: hidden"><a class="btn btn-danger" href="korisnik/rezervacija/${r.apartman.apartmanID }">Promijeni</a></td>
                    </tr>
           
                </c:forEach>
                       
            </table>

		</form>

	</div>

</body>

</html>