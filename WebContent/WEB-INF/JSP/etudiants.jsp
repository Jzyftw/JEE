<%@page import="projet.data.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="etudiants" type="java.util.List<projet.data.Etudiant>" scope="request"/>

<%@include file="commun/entetedepage.jsp" %>


<div class="container">	
	<h1>Liste des étudiants présents en BD</h1>
	
	<!-- tableau d'étudiants  -->
	
		<div class="tabEtu">
			
		<% for (Etudiant etudiant : etudiants) {%>
			<div class="etudiants">
				<p>Id : <%=etudiant.getId()%> </p>
				<p><%=etudiant.getPrenom()%> <%=etudiant.getNom()%></p>
				<p><%=etudiant.getGroupe().getNom()%></p>
				
				<form method="POST" action="<%= getServletContext().getContextPath()%>/do/details">
					<input type="hidden" value="<%=etudiant.getId()%>" name="IdEtu">
					<p><input type="submit" value="voir détails" /></p>
				</form>
				
			</div>
			
		<% } %>
			<a href="<%= getServletContext().getContextPath()%>/do/newEtu">Ajouter un étudiant</a>
		</div>
	
</div>