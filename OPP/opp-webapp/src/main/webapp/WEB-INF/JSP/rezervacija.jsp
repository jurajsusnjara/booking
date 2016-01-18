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

<script>
	$(document).ready(function() {
		$('#datePicker').val("${rezervacija.rezerviranoOd}");

	});
</script>

</head>
<body>
	<div class="container">
		<h1>Apartman "${apartman.nazivApartman }" - rezervacija</h1>
		<hr>

		<form action="" class="form-horizontal" method="post" id="filtriranje">

			<br>

			<div class="form-group">
				<label class="col-md-4 control-label">Datum prijave</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="date" 
							id="datePicker"
							name="rezerviranoOd" class="form-control" size="50"><br>
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
						 <input type="checkbox" name="parking" value="0"> <br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">Internet</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="checkbox" name="internet" value="0"> <br>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label">SatelitskaTV</label>
				<div class="col-md-4">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input type="checkbox" name="satelitskaTV" value="0"> <br>
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

			<c:if test="${error != null}">
				<div class="greska">
					<c:out value="${error}" />
				</div>
			</c:if>

			<div class="form-group">
				<label class="col-md-4 control-label" for="signup"></label>
				<div class="col-md-4">
					<button type="submit" name="method" value="rezervirajApartman"
						class="btn btn-success">Rezerviraj</button>

					<button id="reset" type="reset" name="reset"
						class="btn btn-warning">Resetiraj unesene promjene</button>

				</div>
			</div>


		</form>
	</div>
</body>

</html>