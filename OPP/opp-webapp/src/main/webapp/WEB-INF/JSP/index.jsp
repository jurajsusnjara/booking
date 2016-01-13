<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    
<!DOCTYPE html>
<html>
<head>
<style>

#left{
	float: left;
	width: 50%;
	height: 1000px;
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

#right{
	float: right;
	width: 50%;
	height: 1000px;
    border-left: 1px solid;	
    border-left-color: #a1b3ec;
}
    
#headershadow{
    box-shadow: 0px 5px 20px gray;
}

#logoshaddow{
    text-shadow: 1px 1px 5px gray;
}

html, body{
	height: 100%; 
	padding: 0; 
	margin: 0; 
}

div{ 
	width: 50%; 
	height: 50%; 
	float: left;
}

#inside1{
	border-right: 4px solid;	
    border-right-color: white; 
    border-bottom: 4px solid;	
    border-bottom-color: white;     
	width: 100%;
	height: 100%;
    background-image: url(${Objekti.get(0).getFotografija()});
    background-size: 100% 100%;    
}

#inside2{
	border-left: 4px solid;	
    border-left-color: white; 
    border-bottom: 4px solid;	
    border-bottom-color: white; 
	width: 100%;
	height: 100%;
    background-image: url("${Objekti.get(1).getFotografija()}");
    background-size: 100% 100%;    
}

#inside3{
	border-right: 4px solid;	
    border-right-color: white; 
    border-top: 4px solid;	
    border-top-color: white; 
	width: 100%;
	height: 100%;
   background-image: url("${Objekti.get(2).getFotografija()}");
    background-size: 100% 100%;
}

#inside4{
	border-left: 4px solid;	
    border-left-color: white; 
    border-top: 4px solid;	
    border-top-color: white; 
	width: 100%;
	height: 100%;
    background-image: url("${Objekti.get(3).getFotografija()}");
    background-size: 100% 100%;       
}

#objectText1{
  width: 100%;
  height: 100%;
  margin-top: 25%;
  display: table-cell;
  vertical-align: middle;
  color: white;
  text-align: center;
  backgorund-color: blue;
  text-shadow: 3px 3px 8px black;
  font-size: 60px;
}


</style>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="icon" href="/favicon.ico" type="image/x-icon" />
 <link href="/opp-webapp/css/style.css" rel="stylesheet">
 <link rel='stylesheet' href='/opp-webapp/webjars/bootstrap/3.3.5/css/bootstrap.min.css'>
 <script type='text/javascript' src='/opp-webapp/webjars/jquery/1.11.1/jquery.min.js'></script>
 <script type='text/javascript' src='/opp-webapp/webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Pocetna</title>
</head>
<body>

<nav id="headershadow" class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      	<p class="navbar-brand" id="logoshaddow"><b>Kod Nas Je Najljepse</b></p>
      	 	
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      <ul class="nav navbar-nav navbar-left">
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
        <c:when test="${sessionScope.korisnik != null}">
        <li>Prijavljen ${sessionScope.korisnik.getIme()}</li>
        </c:when>
        <c:otherwise>
          <li><a href="/opp-webapp/registracija">Prijava/Registracija</a></li>

        </c:otherwise>
        </c:choose>
       </ul>
    </div>
  </div>
</nav>

<div id="div1" class="topmargin">
	<div id="inside1">
		<div id="objectText1">
			<a id="urlColorWhite" href="/opp-webapp/objekt?ime=${Objekti.get(0).getNazivObjekt()}">${Objekti.get(0).getNazivObjekt()}</a>
		</div>
	</div>
</div>

<div id="div2" class="topmargin">
	<div id="inside2">
		<div id="objectText1">
			<a id="urlColorWhite" href="/opp-webapp/objekt=${Objekti.get(1).getNazivObjekt()}">${Objekti.get(1).getNazivObjekt()}</a>
		</div>
	</div>
</div>

<div id="div3">
	<div id="inside3">
		<div id="objectText1">
			<a id="urlColorWhite" href="/opp-webapp/objekt=?${Objekti.get(2).getNazivObjekt()}">${Objekti.get(2).getNazivObjekt()}</a>
		</div>
	</div>
</div>

<div id="div4">
	<div id="inside4">
		<div id="objectText1">
			<a id="urlColorWhite" href="/opp-webapp/objekt=?${Objekti.get(3).getNazivObjekt()}">${Objekti.get(3).getNazivObjekt()}</a>
		</div>
	</div>
</div>

</body>
</html>