<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>

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

<title>Pocetna</title>
</head>
<body>

	<nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<p class="navbar-brand">
					<b>Kod Nas Je Najljepse</b>
				</p>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-left">
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${sessionScope.korisnik != null}">
							<li>Prijavljen ${sessionScope.korisnik.ime}</li>
						</c:when>
						<c:otherwise>
							<li><a href="/opp-webapp/registracija">Prijava/Registracija</a></li>

						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<ul>
		<c:forEach items="${Objekti}" var="objekt">
				<li>
				<tr>
					<td>${objekt.getNazivObjekt()}</td>
					</tr>
					<tr>
					<td><a
						href="/opp-webapp/{objekt.getNazivObjekt()}?ime=${objekt.getNazivObjekt()}">
							<img src="${objekt.getFotografija()}" alt="Mountain View"
							style="width: 304px; height: 228px;">
					</a>
				</td>
				</tr>
				</li>
			</c:forEach>
	</ul>
</body>
</html>