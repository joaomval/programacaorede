<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
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
                <table width="100%" border="0">
                    <tr>
                    <form method="post" action="User?accao=faz_login">
                        <p><input type="text" value="email" name="var_email" size="20"> <input type="password" value="password" name="var_password" size="20">
                            <INPUT TYPE="SUBMIT" VALUE="login"></p>
                    </form>
                    </tr>
                </table>
                <% } else {%>
                <p>Bem-vindo </p>
                <p><%= session.getAttribute("pessoa")%></p>
                <p><a href="/yGallery/logout.jsp">Sair</a></p>
                <%}%>
            </div>

            <div id="mainnav">
                <ul>
                    <li><a href="#">Inicio</a></li>
                    <li><a href="#">Galeria</a></li>
                    <li><a href="#">Agenda</a></li>
                    <li><a href="#">Contacto</a></li>
                    <% if (session.getAttribute("pessoa") == null) {%>
                    <li><a href="/yGallery/Registo.jsp">Registar</a></li>
                    <% } else {%>
                    <li><a href="/yGallery/Perfil.jsp">Perfil</a></li>
                    <% }%>
                    <%@page import="Sistema.Administrador" %>
                    <%if (Administrador.eAdmin((String) session.getAttribute("id"))) {%>
                    <li><a href="/yGallery/Administracao.jsp">Administra��o</a></li>
                    <% }%>
                </ul>
            </div>
            <div id="menu">
                <h3>
			Arquivos
                </h3>
                <ul>
                    <li><a href="#">Mar�o 2010</a></li>
                    <li><a href="#">Fevereiro 2010</a></li>
                    <li><a href="#">Janeiro 2010</a></li>
                    <li><a href="#">Dezembro 2009</a></li>
                    <li><a href="#">Novembro 2009</a></li>
                </ul>
                <h3>
			Ultimas noticias
                </h3>
                <ul>
                    <li><a href="#">Novo museu</a></li>
                    <li><a href="#">Pr�mio internacional</a></li>
                </ul>
            </div>
            <div id="contents">
                <div class="blogentry">
                    <h3>
				Administra��o
                    </h3>

                    <table width="100%" border="0" cellpadding="8">
                        <tr>
                            <th width="23%" align="right" valign="middle" scope="col"> <img src="imagens/edit_user.png">
                            </th>
                            <td>editar Utilizador </td>
                        </tr>
                        <tr>
                            <th width="23%" align="right" valign="middle" scope="col"> <img src="imagens/edit_agenda.png">
                            </th>
                            <td>editar Agenda </td>
                        </tr>
                    </table>


                </div>
            </div>
            <div id="footer">
		Copyright � YourGallery 2010
            </div>
        </div>
    </body>
</html>
