<%@page import="projet.data.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="etudiants" type="java.util.List<projet.data.Etudiant>" scope="request"/>

<%@include file="commun/entetedepage.jsp" %>


<div class="container">	
	<h1>Ajouter notes de groupe</h1>
	
	<form method="POST" action="<%= getServletContext().getContextPath()%>/do/saveGrpNotes">
		<div>
			<% int i = 0;
			for (Etudiant etudiant : etudiants) {%>
				<div class="etudiants">
					<p>Id : <%=etudiant.getId()%> </p>
					<p><%=etudiant.getPrenom()%> <%=etudiant.getNom()%></p>
					Note : <input class="note" type="number" name="note<%=i%>" min="0" max="20">
					<input type="hidden" value="<%=etudiant.getId()%>" name="IdEtu<%=i%>">
					<% i++; %>
				</div>
			<% } %>
		</div>
		<input type="hidden" value="<%= request.getAttribute("grpId")%>" name="grpId"/>
		<input type="submit" value="Enregistrer les notes" />
	</form>
	
</div>