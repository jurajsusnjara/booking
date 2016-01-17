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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js">
</head>
<body>

<div class="container">

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