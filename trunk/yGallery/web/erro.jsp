<%-- 
    Document   : loginErrado
    Created on : 20/Abr/2010, 13:22:52
    Author     : Joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<META HTTP-EQUIV="Refresh" CONTENT="3; URL=http:/yGallery/index.jsp">
<H3><%=session.getAttribute("erro") %>, a redirecionar...</H3>
<% session.invalidate(); %>

