<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
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

.topmargin {
	margin-top: 90px;
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

.tableTd {
	text-align: center;
}

.slobodan {
	background-color: #ccffcc;
}

.zauzet {
	background-color: #ffcccc;
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
</head>
<body>
	<nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<p class="navbar-brand">
				<p class="navbar-brand" id="logoshaddow">
					<b><a id="headerUrl" href="/opp-webapp/">Kod Nas Je
							Najljepše</a></b>
				</p>
				</p>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-left">
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when
							test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 1}">
							<li>
								<p class="navbar-text">
									Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}
								</p> </a>
							</li>
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/odjava">Odjava 
								</p> </a>
							</li>

						</c:when>
						<c:when
							test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 3}">
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/vlasnik">Konfiguracija
										sustava 
								</p> </a>
							</li>
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/statistika">Statistika

									
								</p> </a>
							</li>
							<li>
								<p class="navbar-text">
									Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}
								</p> </a>
							</li>
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/odjava">Odjava 
								</p> </a>
							</li>

						</c:when>
						<c:when
							test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 2}">
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/admin">Promjena
										rezervacija 
								</p> </a>
							</li>
							<li>
								<p class="navbar-text">
									Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}
								</p> </a>
							</li>
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/odjava">Odjava 
								</p> </a>
							</li>

						</c:when>
						<c:otherwise>
							<li><a href="/opp-webapp/registracija">Prijava/registracija</a></li>

						</c:otherwise>
					</c:choose>



				</ul>
			</div>
		</div>
	</nav>
	<h3>Trenutno aktivni korisnici:</h3>
	<hr>
	<div class="container">
	<table class="table table-striped">
		<tbody>
		<thead>
			<tr>
				<th>E-Mail</th>
				<th>Ime</th>
				<th>Prezime</th>
			</tr>
		</thead>

		<c:forEach var="item" items="${logirani}">

			<tr>
				<td>${item.email}</td>
				<td>${item.ime}</td>
				<td>${item.prezime}</td>
			</tr>

		</c:forEach>
		</tbody>
	</table>
</div>
</body>

</html>