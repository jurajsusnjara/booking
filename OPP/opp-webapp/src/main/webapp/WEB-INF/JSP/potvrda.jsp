<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="style.css" type="text/css">
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
<script type='text/javascript'
	src='/opp-webapp/webjars/jquery/1.11.1/jquery.min.js'></script>
<script type='text/javascript'
	src='/opp-webapp/webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Potvrda</title>
</head>
<body>
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
		
		<form action="" class="form-horizontal" method="post" id="password">


			<div class="form-group">
				<label class="col-md-4 control-label">Lozinka</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="password" name="novaLozinka1" class="form-control"
							placeholder="Nova lozinka" size="50"><br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Ponovi lozinku</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="password" name="novaLozinka2" class="form-control"
							placeholder="Nova lozinka" size="50"><br>
					</div>
				</div>
			</div>
		<div class="form-group">
				<label class="col-md-4 control-label" for="signup"></label>
				<div class="col-md-4">
					<button type="submit" name="method" value="promijeniSifru"
						class="btn btn-success" value="promijeniPodatke">Promijeni</button>

					<button id="reset" type="reset" name="reset"
						class="btn btn-warning">Resetiraj</button>

				</div>
			</div>
</div>
</body>
</html>