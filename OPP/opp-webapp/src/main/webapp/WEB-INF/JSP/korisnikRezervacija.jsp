<%@page import="java.util.Date"%>
<%@page import="hr.fer.opp.model.Rezervacija"%>
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
    font-size: 0.9em;
    color: #FF0000;
}
     
#korisnikDetails{
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

       
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="style.css" type="text/css">
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
<script type='text/javascript' src='/opp-webapp/webjars/jquery/1.11.1/jquery.min.js'></script>
<script type='text/javascript' src='/opp-webapp/webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
 <script>
    function setDate(date) {
    	
    	document.getElementById('datePicker').valueAsDate = date;
    }
</script>

</head>
	<% Date d = ((Rezervacija) request.getAttribute("rezervacija")).getRezerviranoDo();%>
   <body onload="setDate(${d})">


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
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 3}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/vlasnik">Konfiguracija sustava</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/statistika">Statistika</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/odjava">Odjava</p></a></li>
							
						</c:when>
						<c:when test="${sessionScope.korisnik != null && sessionScope.korisnik.getUloga() == 2}">
							<li> <p class="navbar-text"><a id="headerUrl" href="/opp-webapp/admin">Promjena rezervacija</p></a></li>
							<li> <p class="navbar-text">Moj profil: <a id="headerUrl" href="/opp-webapp/korisnik">${sessionScope.korisnik.getIme()}</p></a></li>
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
    <h1>Rezervacija</h1>
    <hr>
   
        <form action="" class="form-horizontal" method="post" id="filtriranje">
           
           <br>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Rezervirano od</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="date" id="datePicker" name="rezerviranoOd" class="form-control" size="50"><br>
                    </div>
                </div>
            </div>
 
            <div class="form-group">
                <label class="col-md-4 control-label">Rezervirano do</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="date" name="rezerviranoDo" class="form-control" value='<c:out value="${rezervacija.rezerviranoDo}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Parking</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <c:choose>
                                <c:when test="${rezervacija.parking == true}">
                                    <input type="checkbox" name="parking" value="1" checked><br>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" name="parking" value="0"><br>
                                </c:otherwise>
                            </c:choose>
                           
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Internet</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <c:choose>
                                <c:when test="${rezervacija.internet == true}">
                                    <input type="checkbox" name="internet" value="1" checked><br>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" name="internet" value="0"><br>
                                </c:otherwise>
                            </c:choose>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">SatelitskaTV</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                           <c:choose>
                                <c:when test="${rezervacija.satelitskaTV == true}">
                                    <input type="checkbox" name="satelitskaTV" value="1" checked><br>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" name="satelitskaTV" value="0"><br>
                                </c:otherwise>
                            </c:choose>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Broj odraslih</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="odrasli"class="form-control" required placeholder="Upisi broj" value='<c:out value="${rezervacija.gost.odrasli}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Broj djeca(8-14 godina)</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="godina8_14"class="form-control" required placeholder="Upisi broj" value='<c:out value="${rezervacija.gost.godina8_14}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Broj djeca(2-7 godina)</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="godina2_7"class="form-control" required placeholder="Upisi broj" value='<c:out value="${rezervacija.gost.godina2_7}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
             <div class="form-group">
                <label class="col-md-4 control-label">Broj djeca(0-1 godina)</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="godina0_1"class="form-control" required placeholder="Upisi broj" value='<c:out value="${rezervacija.gost.godina0_1}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <c:if test="${error != null}">
                <div class="greska"><c:out value="${error}"/></div>
            </c:if>
            <!-- Buttons -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="signup"></label>
                    <div class="col-md-4">
                        <button  type="submit" name="method" value="posaljiMolbuZaPromijenu" class="btn btn-success" >Promijeni</button>
                       
                        <button id="reset" type="reset" name="reset" class="btn btn-warning">Resetiraj unesene promjene</button>
 
                    </div>
                </div>
       
           
        </form>
        </div>
    </body>
    
</html>
    