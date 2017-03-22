<%@page import="projet.data.Groupe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="groupes" type="java.util.List<projet.data.Groupe>" scope="request"/>
<%@include file="commun/entetedepage.jsp" %>


<div class="container">	

	<h1>Liste des groupes présents en BD</h1>
	
	<!-- tableau de groupes  -->
	<table>
	
		<tr>
			<th>Nom du groupe</th>
			<th>Nombre d'étudiants</th>
		</tr>
	
	<% for (Groupe groupe : groupes) {%>
	
		<tr>
			<td><%=groupe.getNom()%></td>
			<td><%=groupe.getEtudiants().size()%></td>
			<td><form method="POST" action="<%= getServletContext().getContextPath()%>/do/showStdGrps">
					<input type="hidden" value="<%= groupe.getId() %>" name="Id"> 
					<td><input type="submit" value="Voir étudiants" /></td>
				</form>	
			</td>
			<td><form method="POST" action="<%= getServletContext().getContextPath()%>/do/setGrpNotes">
					<input type="hidden" value="<%= groupe.getId() %>" name="Id"> 
					<td><input type="submit" value="Saisir notes" /></td>
				</form>	
			</td>
			
		</tr>
	<% } %>
	</table>
</div>