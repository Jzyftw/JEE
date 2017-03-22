<%@page import="projet.data.Etudiant"%>
<%@page import="projet.data.Note"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@include file="commun/entetedepage.jsp" %>

<div class="container">	

	<form method="POST" action="<%= getServletContext().getContextPath()%>/do/editS">
		<% Etudiant etudiant = (Etudiant) request.getAttribute("etudiant"); %>
		<input type="hidden" value="<%=etudiant.getId()%>" name="Id"/>		
		<h1>Détails de l'étudiant <%=etudiant.getPrenom()%> <%=etudiant.getNom()%></h1>
		<p>Groupe : <%=etudiant.getGroupe().getNom()%></p>
		<p>Nombre d'absences : <%=etudiant.getNbAbsences()%></p>
	
		<td><input type="submit" value="Modifier les absences" /></td>
	</form>
		<table>
			<tr>
				<th> Notes </th>
			</tr>
			<% for (Note note : etudiant.getNotes()) {%>
			<tr>
				<form method="POST" action="<%= getServletContext().getContextPath()%>/do/editMark">
					<input type="hidden" value="<%= note.getId() %>" name="Id"> 
					<td><%= note.getNote() %></td>
					<td><input type="submit" value="éditer note" /></td>
				</form>					
			</tr>
			<% } %>
			<tr>
				<form method="POST" action="<%= getServletContext().getContextPath()%>/do/addMark">
					<input type="hidden" value="<%= etudiant.getId() %>" name="Id"> 
					<td><input type="submit" value="Ajouter note" /></td>
				</form>	
			</tr>
		</table>
	

</div>
