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
 
<!-- Latest compiled and minified JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
    integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
    crossorigin="anonymous"></script>
 
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>
    $(document).ready(function() {
        $("#zatvoriFiltriranje").click(function() {
            $("#filtriranje").hide();
        });
        $("#otvoriFiltriranje").click(function() {
            $("#filtriranje").show();
        });
    });
</script>
   
    </head>
    <body>
   
<div class="container" id="korisnikDetails">
 <h2>Podatci o korisniku</h2>
 <hr>
 <br>    
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Korisnicko ime</th>
        <th><b>${sessionScope.korisnik.ime}</b></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Ime</td>
        <td>${sessionScope.korisnik.ime}</td>
      </tr>
      <tr>
        <td>Prezime</td>
        <td>${sessionScope.korisnik.prezime}</td>
      </tr>
      <tr>
        <td>Email</td>
        <td>${sessionScope.korisnik.email}</td>
       
      </tr>
      <tr>
        <td>Uloga</td>
        <td>
            <c:choose>
                <c:when test="${sessionScope.korisnik.uloga == 0}">
                        Administrator sustava
                </c:when>
                <c:when test="${sessionScope.korisnik.uloga == 1}">
                        Narucitelj ankete
                </c:when>
                <c:otherwise>
                        Anketar
                </c:otherwise>
            </c:choose>
        </td>
      </tr>
    </tbody>
  </table>
</div>
 
    <div class="container topmargin">
        <button name="button" class="btn btn-default" id="otvoriFiltriranje">Promijenite korisnicke postavke</button>
        <button name="button" class="btn btn-default" id="zatvoriFiltriranje">Zatvori</button>
        <form  action="" class="form-horizontal" method="post" id="filtriranje">
           
           
            <div class="form-group">
                <label class="col-md-4 control-label">Ime</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="ime"class="form-control" placeholder="Ime" value='<c:out value="${korisnik.ime}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Prezime</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="prezime"class="form-control" placeholder="Prezime" value='<c:out value="${korisnik.prezime}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Email</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="email"class="form-control" placeholder="Email" value='<c:out value="${korisnik.email}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Telefon</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="telefon"class="form-control" placeholder="Telefon" value='<c:out value="${korisnik.telefon}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Adresa</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="adresa"class="form-control" placeholder="Adresa" value='<c:out value="${korisnik.adresa.adresa}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Grad</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="grad"class="form-control" placeholder="Grad" value='<c:out value="${korisnik.adresa.grad}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Drzava</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="drzava"class="form-control" placeholder="Drzava" value='<c:out value="${korisnik.adresa.drzava}"/>' size="50"><br>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-4 control-label">Postanski broj</label>
                    <div class="col-md-4">
                        <div class="input-group input-append date" id="dateRangePickerFrom">
                            <input type="text" name="postanskiBroj"class="form-control" placeholder="Postanski broj" value='<c:out value="${korisnik.adresa.postanskiBroj}"/>' size="50"><br>
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
                        <button id="register" type="submit" name="method" onclick="location.href='/opp-webapp/objekt/'" class="btn btn-success" value="promijeniPodatke">Filtriraj</button>
                        <button id="reset" type="reset" name="reset" class="btn btn-warning">Resetiraj unesene promjene</button>
 
                    </div>
                </div>
       
 
           
        </form>
    </div>
     
   
       
 
    </body>
   
</html>