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
	font-size: 1.5em;
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

#mj5 {
	
}

#mj6 {
	display: none;
}

#mj7 {
	display: none;
}

#mj8 {
	display: none;
}

#mj9 {
	display: none;
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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>


<script>
$(document).ready(function() {
	$("#otvori5mj").click(function() {
        $("#mj5").show();
        $("#mj6").hide();
        $("#mj9").hide();
        $("#mj7").hide();
        $("#mj8").hide();
    });
    $("#otvori6mj").click(function() {
        $("#mj6").show();
        $("#mj7").hide();
        $("#mj8").hide();
        $("#mj5").hide();
        $("#mj9").hide();
    });

    $("#otvori7mj").click(function() {
        $("#mj7").show();
        $("#mj6").hide();
        $("#mj8").hide();
        $("#mj5").hide();
        $("#mj9").hide();
    });

    $("#otvori8mj").click(function() {
        $("#mj8").show();
        $("#mj6").hide();
        $("#mj7").hide();
        $("#mj5").hide();
        $("#mj9").hide();
    });
    $("#otvori9mj").click(function() {
        $("#mj9").show();
        $("#mj7").hide();
        $("#mj8").hide();
        $("#mj6").hide();
        $("#mj5").hide();
    });

});
</script>

</head>
<body>

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
		<h1>Apartman "${apartman.nazivApartman }" - rezervacija</h1>
		<hr>

		<form action="" class="form-horizontal" method="post" id="filtriranje">

			<br>

			<div class="form-group">
				<label class="col-md-4 control-label">Datum prijave</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="date" id="datePicker" name="rezerviranoOd"
							class="form-control" size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Datum odjave</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="date" name="rezerviranoDo" class="form-control"
							size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Parking</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="checkbox" name="parking"> <br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Internet</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="checkbox" name="internet"> <br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">SatelitskaTV</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="checkbox" name="satelitskaTV"> <br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Broj odraslih osoba</label>
				<div class="col-md-5">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="number" name="odrasli" class="form-control"
							placeholder="Broj odraslih osoba" size="50" required><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Broj djece(8-14
					godina)</label>
				<div class="col-md-5">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="number" name="godina8_14" class="form-control"
							placeholder="Broj djece(8-14 godina)" size="50" required><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Broj djece(2-7 godina)</label>
				<div class="col-md-5">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="number" name="godina2_7" class="form-control"
							placeholder="Broj djece(2-7 godina)" size="50" required><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Broj djece(0-1 godina)</label>
				<div class="col-md-5">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="number" name="godina0_1" class="form-control"
							placeholder="Broj djece(0-1 godina)" size="50" required><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="signup"></label>
				<div class="col-md-4">
					<button type="submit" name="method" value="rezervirajApartman"
						class="btn btn-success" id="spremiPromijene">Rezerviraj</button>

					<button id="reset" type="reset" name="reset"
						class="btn btn-warning">Resetiraj unesene promjene</button>

				</div>
			</div>

			<c:if test="${error != null}">
				<div class="col-md-4 col-md-offset-2" id="greska" style="color: red">
					<c:out value="${error}" />
				</div>
			</c:if>

		</form>
	</div>
	
	<div class="container topmargin" id="positionCenter">
		<button name="button" class="btn btn-primary topmarginBtn"
			id="otvori5mj">Svibanj</button>

		<button name="button" class="btn btn-primary topmarginBtn"
			id="otvori6mj">Lipanj</button>

		<button name="button" class="btn btn-primary topmarginBtn"
			id="otvori7mj">Srpanj</button>

		<button name="button" class="btn btn-primary topmarginBtn"
			id="otvori8mj">Kolovoz</button>

		<button name="button" class="btn btn-primary topmarginBtn"
			id="otvori9mj">Rujan</button>

		<div id="mj5">
			<br>
			<h1>Svibanj</h1>
			<hr>
			<table class="table table-bordered">
				<c:forEach var="i" begin="0" end="4">
					<tr>
						<c:forEach var="j" begin="0" end="6">
							<c:choose>
								<c:when test="${i*7+j < 31}">
									<c:choose>

										<c:when test="${!svibanj.get(i*7+j).getZauzet()}">
											<td class="tableTd slobodan"><b><c:out
														value="${svibanj.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out
														value="${svibanj.get(i*7+j).getDan()}" /></b></td>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>



		<div id="mj6">
			<br>
			<h1>Lipanj</h1>
			<hr>
			<table class="table table-bordered">
				<c:forEach var="i" begin="0" end="4">
					<tr>
						<c:forEach var="j" begin="0" end="6">
							<c:choose>
								<c:when test="${i*7+j < 30}">
									<c:choose>

										<c:when test="${!lipanj.get(i*7+j).getZauzet()}">
											<td class="tableTd slobodan"><b><c:out
														value="${lipanj.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out
														value="${lipanj.get(i*7+j).getDan()}" /></b></td>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div id="mj7">
			<br>
			<h1>Srpanj</h1>
			<hr>
			<table class="table table-bordered">
				<c:forEach var="i" begin="0" end="4">
					<tr>
						<c:forEach var="j" begin="0" end="6">
							<c:choose>
								<c:when test="${i*7+j < 31}">
									<c:choose>

										<c:when test="${!srpanj.get(i*7+j).getZauzet()}">
											<td class="tableTd slobodan"><b><c:out
														value="${srpanj.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out
														value="${srpanj.get(i*7+j).getDan()}" /></b></td>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>



		<div id="mj8">
			<br>
			<h1>Kolovoz</h1>
			<hr>
			<table class="table table-bordered">
				<c:forEach var="i" begin="0" end="4">
					<tr>
						<c:forEach var="j" begin="0" end="6">
							<c:choose>
								<c:when test="${i*7+j < 31}">
									<c:choose>

										<c:when test="${!kolovoz.get(i*7+j).getZauzet()}">
											<td class="tableTd slobodan"><b><c:out
														value="${kolovoz.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out
														value="${kolovoz.get(i*7+j).getDan()}" /></b></td>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>


		<div id="mj9">
			<br>
			<h1>Rujan</h1>
			<hr>
			<table class="table table-bordered">
				<c:forEach var="i" begin="0" end="4">
					<tr>
						<c:forEach var="j" begin="0" end="6">
							<c:choose>
								<c:when test="${i*7+j < 30}">
									<c:choose>

										<c:when test="${!rujan.get(i*7+j).getZauzet()}">
											<td class="tableTd slobodan"><b><c:out
														value="${rujan.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out
														value="${rujan.get(i*7+j).getDan()}" /></b></td>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>


	</div>
</body>

</html>