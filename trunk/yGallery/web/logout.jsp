<%-- 
    Document   : logout
    Created on : 19/Abr/2010, 15:47:59
    Author     : Joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% session.invalidate(); %>

<jsp:forward page="index.jsp"/>
