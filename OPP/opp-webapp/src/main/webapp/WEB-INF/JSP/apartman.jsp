<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>
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

#pojedinaSlika{
	width: 420px;
	height: 340x;
	margin: 0 auto;
	
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


<title>${apartman.getNazivApartman()}</title>
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

<div class="container" id="positionCenter">
	<br>
	<br>
 <h2 class="topmargin">${apartman.getNazivApartman()} </h2>
 <hr>
 <br>    
  <table class="table table-striped">
    <tbody>
      <tr>
        <td>Pogled</td>
        <td>${apartman.getOpisApartmana().getPogled()}</td>
      </tr>
      <tr>
        <td>Kat</td>
        <td>${apartman.getOpisApartmana().getKat()}</td>
      </tr>
      <tr>
        <td>Minimalan broj osoba</td>
        <td>${apartman.getOpisApartmana().getMinBroj()}</td>
        
      </tr>
      
      <tr>
        <td>Maksimalan broj osoba</td>
        <td>${apartman.getOpisApartmana().getMaxBroj()}</td>
        
      </tr>
      
      
      
      
    </tbody>

  </table>
  
</div>


<c:forEach items="${apartman.getOpisApartmana().getFotografije()}" var="fotografija">
	<div id="pojedinaSlika">
		<img src="${fotografija.getFotoDatoteka()}" alt="Mountain View" style="width: 400px; height: 250px;">
	
	</div>
</c:forEach>



</body>
</html>