<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>

<style>

html, body{
	height: 100%; 
	padding: 0; 
	margin: 0; 
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
 
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js">
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
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/vlasnik">Konfiguracija sustava</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/statistika">Statistika</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 3}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/admin">Promjena rezervacija</p></a></li>
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

    <h2>Popis rezervacija</h2>

    <form  action="" class="form-horizontal" method="post" id="rezervacija">

        <table class="table table-bordered">
            <tr>
                <th> Apartman</th>
                <th> Podnositelj zahtjeva</th>
                <th> Rezervirano od</th>
                <th> Rezervirano do</th>
                <th> Parking</th>
                <th> Internet</th>
                <th> Satelitska TV</th>
            </tr>

            <c:forEach var="r" items="${rezervacije}">

                <tr>
                    <td> ${r.apartman.nazivApartman }</td>
                    <td> ${r.korisnik.ime} ${r.korisnik.prezime}</td>
                    <td> ${r.rezerviranoOd }</td>
                    <td> ${r.rezerviranoDo }</td>
                    <c:choose>
                        <c:when test="${r.parking == true}">
                            <td>DA</td>
                        </c:when>
                        <c:otherwise>
                            <td>NE</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${r.internet == true}">
                            <td>DA</td>
                        </c:when>
                        <c:otherwise>
                            <td>NE</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${r.satelitskaTV == true}">
                            <td>DA</td>
                        </c:when>
                        <c:otherwise>
                            <td>NE</td>
                        </c:otherwise>
                    </c:choose>
                    <td style="border: 0; border-right: hidden; border-bottom: hidden"><a class="btn btn-danger" href="admin?apartmanID=${r.apartman.apartmanID}&korisnikID=${r.korisnik.korisnikID}">Promijeni</a></td>
                </tr>

            </c:forEach>

        </table>

    </form>
</div>

</body>
</html>