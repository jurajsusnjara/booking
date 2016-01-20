<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<head>
<style>
#left {
	float: left;
	width: 50%;
	height: 1000px;
}

.topmargin {
	margin-top: 100px;
}

#right {
	float: right;
	width: 50%;
	height: 1000px;
	border-left: 1px solid;
	border-left-color: #a1b3ec;
}

#headershadow {
	box-shadow: 0px 10px 20px #a5b9ec;
}

#positionCenter {
	margin: 0 auto;
	height: 400px;
	widht: 960px;
}

#apartman {
	height: 200px;
	width: 500px;
	margin: 0 auto;
}

#namebox {
	text-align: center;
}

#logoshaddow {
	text-shadow: 1px 1px 5px gray;
}

#logoshaddow {
	text-shadow: 1px 1px 5px gray;
}

#logoUrl {
	color: gray;
}

#logoUrl:hover {
	color: white;
	text-decoration: none;
}

#pojedinaSlika {
	width: 160px;
	height: 100x;
	margin: 0 auto;
	float: left;
}

.thumbnails img {
	height: 80px;
	border: 4px solid #555;
	padding: 1px;
	margin: 0 10px 10px 0;
}

.thumbnails img:hover {
	border: 4px solid #00ccff;
	cursor: pointer;
}

.preview img {
	border: 4px solid #444;
	padding: 1px;
	width: 800px;
}

#imgContainer {
	height: 100px;
	width: 800px;
	margin: 0 auto;
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

#mj9{
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

<!-- Latest compiled and minified JavaScript -->
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

<title>${apartman.getNazivApartman()}</title>
</head>

<body>



	<nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<p class="navbar-brand">
				<p class="navbar-brand" id="logoshaddow">
					<b><a id="headerUrl" href="/opp-webapp/">Kod Nas Je
							Najljepse</a></b>
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
								</p>
								</a>
							</li>
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/odjava">Odjava
								</p>
								</a>
							</li>

						</c:when>
						<c:when
							test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 2}">
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/vlasnik">Konfiguracija
										sustava
								</p>
								</a>
							</li>
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/statistika">Statistika
								</p>
								</a>
							</li>
							<li>
								<p class="navbar-text">
									Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}
								</p>
								</a>
							</li>
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/odjava">Odjava
								</p>
								</a>
							</li>

						</c:when>
						<c:when
							test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 3}">
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/admin">Promjena
										rezervacija
								</p>
								</a>
							</li>
							<li>
								<p class="navbar-text">
									Moj profil (administrator): <a id="headerUrl"
										href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}
								</p>
								</a>
							</li>
							<li>
								<p class="navbar-text">
									<a id="headerUrl" href="/opp-webapp/odjava">Odjava
								</p>
								</a>
							</li>

						</c:when>
						<c:otherwise>
							<li><a href="/opp-webapp/registracija">Prijava/registrcija</a></li>

						</c:otherwise>
					</c:choose>



				</ul>
			</div>
		</div>
	</nav>

	<div class="container topmargin" id="positionCenter">
		<br> <br>

		<div id="headerSplit">
			<h2 class="topmargin" style="float: left;">${apartman.getNazivApartman()}
			</h2>
			<span id="btn-login" class="topmargin" style="float: right;"><a
				href="/opp-webapp/rezervacija?id=${apartman.getApartmanID()}"
				class="btn btn-primary" value="login">Rezerviraj</a></span>
		</div>

		<br>
		<table class="table table-striped">
			<tbody>

				<tr>
					<td>Pogled</td>
					<td>${apartman.getOpisApartmana().getPogled()}</td>
				</tr>
				<tr>
					<td>Kat</td>
					<td>${apartman.getOpisApartmana().getKat()}</td>
				</tr>
				<tr>
					<td>Minimalan broj osoba</td>
					<td>${apartman.getOpisApartmana().getMinBroj()}</td>

				</tr>

				<tr>
					<td>Maksimalan broj osoba</td>
					<td>${apartman.getOpisApartmana().getMaxBroj()}</td>

				</tr>
			</tbody>

		</table>
		<hr>
		<hr>

	</div>

	<div id="imgContainer">
		<div class="thumbnails" align="center">

			<c:forEach items="${apartman.getOpisApartmana().getFotografije()}"
				var="fotografija">
				<div id="pojedinaSlika">
					<img onmouseover="preview.src=img${fotografija.getFotoID()}.src"
						name="img${fotografija.getFotoID()}"
						src="${fotografija.getFotoDatoteka()}" alt="" />
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="preview" align="center">
		<img name="preview"
			src="${apartman.getOpisApartmana().getFotografije().get(0).getFotoDatoteka()}"
			alt="" />
	</div>


	<br>
	<br>
	<br>
	<br>
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
											<td class="tableTd slobodan"><b><c:out value="${svibanj.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out value="${svibanj.get(i*7+j).getDan()}" /></b></td>
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
											<td class="tableTd slobodan"><b><c:out value="${lipanj.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out value="${lipanj.get(i*7+j).getDan()}" /></b></td>
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
											<td class="tableTd slobodan"><b><c:out value="${srpanj.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out value="${srpanj.get(i*7+j).getDan()}" /></b></td>
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
											<td class="tableTd slobodan"><b><c:out value="${kolovoz.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out value="${kolovoz.get(i*7+j).getDan()}" /></b></td>
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
											<td class="tableTd slobodan"><b><c:out value="${rujan.get(i*7+j).getDan()}" /></b></td>
										</c:when>

										<c:otherwise>
											<td class="tableTd zauzet"><b><c:out value="${rujan.get(i*7+j).getDan()}" /></b></td>
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