<%@page import="projet.data.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@include file="commun/entetedepage.jsp" %>


<div class="container">	
	<div class="conteneur">
		<h1> Nouvel étudiant </h1>
		
		<form method="POST" action="<%= getServletContext().getContextPath()%>/do/addEtu">
			Prénom : <input type="text" name="prenom">
			Nom : <input type="text" name="nom">
			Id du groupe : <input type="number" min="1" max="3" name="grpId"> (1 : MIAM, 2 : SIMO, 3 : MESSI)
			<p><input type="submit" value="Créer" /></p>
		</form>
	</div>
</div>