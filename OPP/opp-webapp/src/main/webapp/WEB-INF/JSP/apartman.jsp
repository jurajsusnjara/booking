<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<head><style>
#left{
	float: left;
	width: 50%;
	height: 1000px;
}

.topmargin{
	margin-top: 100px;
}

#right{
	float: right;
	width: 50%;
	height: 1000px;
    border-left: 1px solid;	
    border-left-color: #a1b3ec;
    }
    
#headershadow{
    box-shadow: 0px 10px 20px #a5b9ec;
}

#positionCenter{
	margin: 0 auto;
	height: 500px;
	widht: 960px;
}

#apartman{
	height: 400px;
	width: 500px;
	margin: 0 auto;
}

#namebox{
    text-align: center;
}

 #logoshaddow{
    text-shadow: 1px 1px 5px gray;
}

#logoshaddow{
    text-shadow: 1px 1px 5px gray;
}

#logoUrl{
	color: gray;
}

#logoUrl:hover {
    color: white;
    text-decoration: none;
}

.topmargin{
	margin-top: 100px;
}

.topmarginBtn{
	margin-top: 100px;
}

#pojedinaSlika{
	width: 420px;
	height: 340x;
	margin: 0 auto;
	margin-left: 30%;
}

#mj6{
}

#mj7{
display: none;
}

#mj8{
display: none;
}

.tableTd{
text-align: center;
}

.slobodan{
background-color: #ccffcc;
}

.zauzet{
background-color: #ffcccc;
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

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>


<title>${Apartman.getNazivApartman()}</title>

<script>
	$(document).ready(function() {
		$("#otvori6mj").click(function() {
			$("#mj6").show();
			$("#mj7").hide();
			$("#mj8").hide();
		});

		$("#otvori7mj").click(function() {
			$("#mj7").show();
			$("#mj6").hide();
			$("#mj8").hide();
		});

		$("#otvori8mj").click(function() {
			$("#mj8").show();
			$("#mj6").hide();
			$("#mj7").hide();
		});

	});
</script>

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



<div class="container topmargin" id="positionCenter">

<button name="button" class="btn btn-primary topmarginBtn" id="otvori6mj">Lipanj</button>

<button name="button" class="btn btn-primary topmarginBtn" id="otvori7mj">Srpanj</button>

<button name="button" class="btn btn-primary topmarginBtn" id="otvori8mj">Kolovoz</button>
	
	
<div id="mj6">	
<br>
<h1>Lipanj</h1>
<hr>
	<table class="table table-bordered">
		<c:forEach var="i" begin="0" end="4">
			<tr>
				<c:forEach var="j" begin="1" end="7">
			  		<c:choose>
				        <c:when test="${i*7+j <= 30}">
				        	<c:choose>
					        	<c:when test="${i*7+j == 30 || i*7+j == 10 || i*7+j == 3 || i*7+j == 17 || i*7+j == 9}">
					        		<td class="tableTd slobodan"><c:out value="${i*7+j}"/></td>
					        	</c:when>
					        	
					        	<c:otherwise>
					        		<td class="tableTd zauzet"><c:out value="${i*7+j}"/></td>
					        	</c:otherwise>
				        	</c:choose>
				        </c:when>
				        <c:otherwise>
				        </c:otherwise>
			        </c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="mj7">
<br>
<h1>Srpanj</h1>
<hr>
	<table class="table table-bordered">
		<c:forEach var="i" begin="0" end="4">
			<tr>
				<c:forEach var="j" begin="1" end="7">
			  		<c:choose>
				        <c:when test="${i*7+j <= 31}">
				        	<c:choose>
					        	<c:when test="${i*7+j == 26 || i*7+j == 2 || i*7+j == 3 || i*7+j == 12 || i*7+j == 22}">
					        		<td class="tableTd slobodan"><c:out value="${i*7+j}"/></td>
					        	</c:when>
					        	
					        	<c:otherwise>
					        		<td class="tableTd zauzet"><c:out value="${i*7+j}"/></td>
					        	</c:otherwise>
				        	</c:choose>
				        </c:when>
				        <c:otherwise>
				        </c:otherwise>
			        </c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>


<div id="mj8">
<br>
<h1>Kolovoz</h1>
<hr>
	<table class="table table-bordered">
		<c:forEach var="i" begin="0" end="4">
			<tr>
				<c:forEach var="j" begin="1" end="7">
				  		<c:choose>
					        <c:when test="${i*7+j <= 31}">
					        	<c:choose>
					        		<c:when test="${i*7+j == 27 || i*7+j == 15 || i*7+j == 1 || i*7+j == 7 || i*7+j == 25}">
					        			<td class="tableTd slobodan"><c:out value="${i*7+j}"/></td>
					        		</c:when>
					        		
					        		<c:otherwise>
					        			<td class="tableTd zauzet"><c:out value="${i*7+j}"/></td>
					        		</c:otherwise>
					        	</c:choose>
					        </c:when>
					        <c:otherwise>
					        </c:otherwise>
				        </c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>

</div>

<div class="container" id="positionCenter">
	<br>
	<br>
 <h2>${Apartman.getNazivApartman()} Ime apartmana</h2>
 <hr>
 <br>    
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Naslov</th>
        <th><b>${apartman.getOpisApartmana().getNaslov()} Naslov</b></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Pogled</td>
        <td>${apartman.getOpisApartmana().getPogled()} More</td>
      </tr>
      <tr>
        <td>Kat</td>
        <td>${apartman.getOpisApartmana().getKat()} 5</td>
      </tr>
      <tr>
        <td>Minimalan broj osoba</td>
        <td>${apartman.getOpisApartmana().getMinBroj()} 1</td>
        
      </tr>
      
      <tr>
        <td>Maksimalan broj osoba</td>
        <td>${apartman.getOpisApartmana().getMaxBroj()} 5</td>
        
      </tr>
      
      <tr>
        <td>Opis</td>
        <td>${apartman.getOpisApartmana().getOpis()} wdaawddwawdadwawddwadawdwawddkajhbvruwhakbauebfwhalbvc<br>
										             usekhbfvkuhrzdjbvuhkrdzbguydhrkfbvuekhdfknvbuadkhfjbvu<br>
										             wdaawddwawdadwawddwadawdwawddkajhbvruwhakbauebfwhalbvc<br>
										             usekhbfvkuhrzdjbvuhkrdzbguydhrkfbvuekhdfknvbuadkhfjbvu<br></td>
        
      </tr>
      
      
    </tbody>

  </table>
  
</div>

<!-- 
<c:forEach items="${apartman.getOpisApartmana().getFotografije()}" var="fotografija">
	<div id="pojedinaSlika">
		<img src="${fotografija.getFotoDatoteka()}" alt="Mountain View" style="width: 304px; height: 228px;">
	</div>
</c:forEach>
-->

<div id="pojedinaSlika">
	<img src="http://www.wallpapereast.com/static/images/Free-Wallpaper-Nature-Scenes_Gg92QQ8.jpg" alt="Mountain View" style="width: 400px; height: 300px; box-shadow: 10px 10px 20px #a5b9ec;">	
</div>

<div id="pojedinaSlika">
<img src="http://www.wallpapereast.com/static/images/Free-Wallpaper-Nature-Scenes_Gg92QQ8.jpg" alt="Mountain View" style="width: 400px; height: 300px; box-shadow: 10px 10px 20px #a5b9ec;">	
</div>

<div id="pojedinaSlika">
<img src="http://www.wallpapereast.com/static/images/Free-Wallpaper-Nature-Scenes_Gg92QQ8.jpg" alt="Mountain View" style="width: 400px; height: 300px; box-shadow: 10px 10px 20px #a5b9ec;">	
</div>

<div id="pojedinaSlika">
<img src="http://www.wallpapereast.com/static/images/Free-Wallpaper-Nature-Scenes_Gg92QQ8.jpg" alt="Mountain View" style="width: 400px; height: 300px; box-shadow: 10px 10px 20px #a5b9ec;">	
</div>

<div id="pojedinaSlika">
<img src="http://www.wallpapereast.com/static/images/Free-Wallpaper-Nature-Scenes_Gg92QQ8.jpg" alt="Mountain View" style="width: 400px; height: 300px; box-shadow: 10px 10px 20px #a5b9ec;">	
</div>
</body>
</html>