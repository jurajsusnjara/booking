<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false" %>

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

#divPosition{
	width: 50%; 
	height: 50%; 
	float: left;
}

#inside{
	border-right: 4px solid;	
    border-right-color: white; 
    border-bottom: 4px solid;	
    border-bottom-color: white;
    border-left: 4px solid;	
    border-left-color: white; 
    border-top: 4px solid;	
    border-top-color: white;       
	width: 100%;
	height: 100%;
    background-size: 100% 100%;    
}


#objectText{
  width: 100%;
  height: 100%;
  margin-top: 20%;
  color: white;
  text-align: center;
  text-shadow: 3px 3px 8px black;
  font-size: 60px;
}

#sth{
 width: 100%;
 height: 100%;
 margin-top:60px;
}

#headerUrl{
	color: gray;
}

#headerUrl:hover{
	text-decoration: none;
	color: #7041f9;
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
						<c:when test="${sessionScope.korisnik != null}">
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

<div id="sth" >
<c:forEach items="${Objekti}" var="objekt"> 
<div id="divPosition">
        <div id="inside" style="background-image: url('${objekt.getFotografija()}')">
            <div id="objectText">
                <a id="urlColorWhite" href="/opp-webapp/objekt?id=${objekt.getObjektID()}">${objekt.getNazivObjekt()}</a>
            </div>
  </div>
</div>
</c:forEach>
</div>



</body>
</html>