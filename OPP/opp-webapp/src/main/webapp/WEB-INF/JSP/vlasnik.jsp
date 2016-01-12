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
		</style>
		
	</head>
	<body>
	
		<form action="" method="post">
			<h1>Dodaj objekt:</h1>
			Naziv objekta: <input type="text" name="nazivObjekta" value='<c:out value="${nazivObjekta}"/>' size="30"><br>
			Fotografija: <input type="text" name="fotografija" value='<c:out value="${fotografija}"/>' size="30"><br>
			<c:if test="${error != null}">
				<div class="greska"><c:out value="${error}"/></div>
			</c:if>	
			<input type="submit" name="method" value="dodajObjekt">
		</form>
			
	</body>
	
</html>