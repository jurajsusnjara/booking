<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>


<head>

<style>
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

#left {
	float: left;
	width: 50%;
	height: 1000px;
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

#leftTablica{
	float: left;
}

#rightTablica{
	float: right;
}

#imgbox{
	width: 304px; 
	height: 228px;
	margin: 0 auto;
}

#right {
	float: right;
	width: 50%;
	height: 1000px;
	border-left: 1px solid;
	border-left-color: #a1b3ec;
}

#namebox{
    text-align: center;
}

#apartman{
	height: 400px;
	width: 500px;
	margin: 0 auto;
}


#apartmanLink{
	color:black;
	
}

#apartmanLink:hover{
	color: grey;
    text-decoration: none;
}

#filtriranje{
	display: none;
}
</style>

<title>Objekt</title>


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
		$("#zatvoriFiltriranje").click(function() {
			$("#filtriranje").hide();
		});
		$("#otvoriFiltriranje").click(function() {
			$("#filtriranje").show();
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
		<button name="button" class="btn btn-primary" id="otvoriFiltriranje">Filtriranje pretrage</button>
		<button name="button" class="btn btn-danger" id="zatvoriFiltriranje">Zatvori
			filtriranje</button>
		<form class="form-horizontal" method="post" id="filtriranje">

			<fieldset>
				<!-- Form Name -->
				<div class="col-md-9">
					<h2>Filtriranje pretrage</h2>
					<hr>
					<br>
				</div>



				<div class="form-group">
					<label class="col-md-4 control-label">Pogled</label>

					<div class="col-md-4">
						<select class="form-control" name="pogled">
							<option value=""></option>
							<option value="Suma">Šuma</option>
							<option value="More">More</option>

						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<div class="input-group input-append date">
							<b>Datum boravka</b>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-md-4 control-label">Od</label>
					<div class="col-md-4">
						<div class="input-group input-append date"
							id="dateRangePickerFrom">
							<input type="date" class="form-control" placeholder="mm/dd/gggg"
								name="datumOd" value=""/> <span class="input-group-addon add-on"></span>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-md-4 control-label">Do</label>
					<div class="col-md-4">
						<div class="input-group input-append date" id="dateRangePickerTo">
							<input type="date" class="form-control" placeholder="mm/dd/gggg"
								name="datumDo" value="" /> <span class="input-group-addon add-on"></span>
						</div>
					</div>
				</div>

				<!-- Buttons -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="signup"></label>
					<div class="col-md-4">
						<button id="register" type="submit" name="register"
							onclick="location.href='/opp-webapp/objekt?id=${Objekt.getObjektID()}'"
							class="btn btn-success">Filtriraj</button>
						<button id="reset" type="reset" name="reset"
							class="btn btn-warning">Resetiraj filtriranje</button>

					</div>
				</div>
			</fieldset>
		</form>
	</div>
	
	
	
	
	
	
<div class="container">
 <h2>Apartmani</h2>
 <hr>
 <br>    
</div>

  <c:forEach items="${Apartmani}" var="apartman"> 
<div id="apartman">
		 <!-- Tu ide foreach, ali sam morao obrisat zbog compileanja. Nemoj zaboravit dodat dole kraj od petlje  -->
<hr><hr>
		
		<div id="namebox">
			<a id="apartmanLink"href="/opp-webapp/apartman?id=${apartman.getApartmanID()}"><h2><b>${apartman.getNazivApartman()}</b></h2></a>
		</div>
		<br>
		<div id="imgbox">
			
								
			<img src="${apartman.getOpisApartmana().getFotografije().get(0).getFotoDatoteka()}" alt="Mountain View" style="width: 304px; height: 228px; box-shadow: 10px 10px 20px #a5b9ec;">
							
			
		</div>
		<br><br>
		</c:forEach>
		<!-- tu zavrsava foreach  -->	 
</div>	

	
</body>
</html>