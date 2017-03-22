<%@page import="projet.data.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@include file="commun/entetedepage.jsp" %>


<div class="container">	
	<h1>Ajout de note</h1>
	
	<%  int Id = (int) request.getAttribute("Id"); %>
	<form method="POST" action="<%= getServletContext().getContextPath()%>/do/saveNewMark">
		<input type="hidden" value="<%=Id%>" name="Id">
		<input type="number" min="0" max="20" name="Note">
		<input type="submit" value="Ajouter" />
	</form>
</div>

