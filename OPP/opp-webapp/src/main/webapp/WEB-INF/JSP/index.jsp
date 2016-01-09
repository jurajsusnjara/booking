<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    
<!DOCTYPE html>
<html>
<head>

 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="icon" href="/favicon.ico" type="image/x-icon" />
 <link href="/opp-webapp/css/style.css" rel="stylesheet">
 <link rel='stylesheet' href='/opp-webapp/webjars/bootstrap/3.3.5/css/bootstrap.min.css'>
 <script type='text/javascript' src='/opp-webapp/webjars/jquery/1.11.1/jquery.min.js'></script>
 <script type='text/javascript' src='/opp-webapp/webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Pocetna</title>
</head>
<body>

<nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <p class="navbar-brand"><b>Kod Nas Je Najljepse</b></p>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
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

</body>
</html>