<%@page import="projet.data.Etudiant"%>
<%@page import="projet.data.Note"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="commun/entetedepage.jsp" %>


<div class="container">	
	<div class="conteneur">
		<h1>Absences étudiant</h1>
	
		<form method="POST" action="<%= getServletContext().getContextPath()%>/do/saveS">
			<% Etudiant etudiant = (Etudiant) request.getAttribute("etudiant"); %>
			<input type="hidden" value="<%=etudiant.getId()%>" name="Id">
			Nombre d'absences : <input type="number" value="<%=etudiant.getNbAbsences()%>" name="nbAbs" min="0">
			<p><input type="submit" value="Sauvegarder" /></p>
		</form>
	</div>
</div>
