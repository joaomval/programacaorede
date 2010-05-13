<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link rel="icon" href="imagens/favicon.ico" type="image/ico"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Your Gallery</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div id="container">
            <div id="header" title="yourGallery">
                <h1>
		  YourGallery
                </h1>
                <h2><img src="imagens/yourgallery.png"></h2>
            </div>

            <div id ="login">
                <% if (session.getAttribute("pessoa") == null) {%>
                <form method="post" action="User?accao=faz_login">
                    <div><label for="email">E-Mail</label>
                        <input id="email" type="text" name="var_email" size="15"></div>
                    <div><label for="password">Password</label>
                        <input id="password" type="password" name="var_password" size="15"></div>
                    <tr><td><INPUT TYPE="SUBMIT" class="formbutton" VALUE="login"></td>
                     <td><a href="/yGallery/recuperarPassword.jsp">Recuperar password</a> </td></tr>
                </form>
                
                <% } else {%>
                <p>Bem-vindo </p>
                <p><%= session.getAttribute("pessoa")%>
                    <a href="/yGallery/logout.jsp">Sair</a></p>
                    <%}%>
            </div>

            <div id="mainnav">
                <ul>
                    <li><a href="/yGallery/index.jsp">Inicio</a></li>
                    <li><a href="/yGallery/Galeria.jsp">Galeria</a></li>
                    <li><a href="#">Agenda</a></li>
                    <li><a href="#">Contacto</a></li>
                    <% if (session.getAttribute("pessoa") == null) {%>
                    <li><a href="/yGallery/Registo.jsp">Registar</a></li>
                    <% } else {%>
                    <li><a href="/yGallery/Perfil.jsp">Perfil</a></li>
                    <% }%>
                    <%@page import="Sistema.Administrador" %>
                    <%if (Administrador.eAdmin((String) session.getAttribute("id"))) {%>
                    <li><a href="/yGallery/Administracao.jsp">Administração</a></li>
                    <% }%>
                    <%@page import="Sistema.Artista" %>
                    <%if (Artista.eArtista((String) session.getAttribute("id"))) {%>
                    <li><a href="/yGallery/MinhaGaleria.jsp">myGallery</a></li>
                    <% }%>
                </ul>
            </div>
        </div>
    </body>
</html>
