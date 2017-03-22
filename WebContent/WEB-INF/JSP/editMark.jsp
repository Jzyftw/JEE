<%@page import="projet.data.Etudiant"%>
<%@page import="projet.data.Note"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="commun/entetedepage.jsp" %>


<div class="container">	

	<h1> Modification d'une note </h1>
	
	<% Note note = (Note) request.getAttribute("Note"); %>
	<form method="POST" action="<%= getServletContext().getContextPath()%>/do/saveMark">
		<input type="hidden" value="<%=note.getId()%>" name="Id">
		<input type="number" value="<%=note.getNote()%>" name="Note" min="0" max="20">
		<input type="submit" value="Modifier" />
	</form>
</div>