<%@page import="hr.fer.opp.model.Rezervacija"%>
<%@page import="java.util.List"%>
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

    <h2>Popis ponuđenih rezervacija</h2>
	<p>Radi optimalnosti ne možete rezervirati apartman. Predložili smo Vam popis rezervacija sa istim uslugama!</p>
    <form  action="" class="form-horizontal" method="post" id="rezervacija">

        <table class="table table-bordered">
            <tr>
                <th> Apartman</th>
                <th> Rezervirajte od</th>
                <th> Rezervirajte do</th>
            </tr>

            <c:forEach var="r" items="${rezervacije}" varStatus="i">

                <tr>
                    <td> ${r.apartman.nazivApartman }</td>
                    <td> ${r.rezerviranoOd }</td>
                    <td> ${r.rezerviranoDo }</td>
                    <td style="border: 0; border-right: hidden; border-bottom: hidden"><a class="btn btn-danger" href="rezervacija/nova?id=${i.index}">Rezerviraj</a></td>
                </tr>

            </c:forEach>

        </table>

    </form>
</div>

</body>
</html>