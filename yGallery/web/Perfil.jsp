<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Your Gallery</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
        <link href="../Users/Joao/Documents/NetBeansProjects/yGallery/web/style.css" rel="stylesheet" type="text/css" />
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
                    <form method="post" action="/yGallery/User?accao=faz_login">
                        <p><input type="text" value="email" name="var_email" size="20"> <input type="text" value="password" name="var_password" size="20">
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
                    <li><a href="/Perfil.jsp">Perfil</a></li>
                    <% }%>
                </ul>
            </div>
            <div id="menu">
                <h3>
			Arquivos
                </h3>
                <ul>
                    <li><a href="#">Março 2010</a></li>
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
                    <li><a href="#">Prémio internacional</a></li>
                </ul>
            </div>
            <div id="contents">
                <div class="blogentry">
                    <h3>
				Alterar Dados:
                    </h3>
                    <form method="post" action="/yGallery/User?accao=altera_dados">
                        <table width="100%" border="0" cellpadding="8">
                            <tr>
                                <%@page import="Sistema.Utilidades" %>
                                <% Utilidades.populaAtributosSession(request, response);%>
                                <% String email = (String) session.getAttribute("email");
                                            String nome = (String) session.getAttribute("nome");
                                            String nascimento = (String) session.getAttribute("dataNascimento");
                                            String morada = (String) session.getAttribute("morada");
                                            String postal = (String) session.getAttribute("codPostal");
                                %>
                                <th width="23%" align="right" valign="middle" scope="col">E-Mail</th>
                                <td><input type="text" <% if (email != null) {%>value="<%=email%>" <%}%> name="var_email" size="20">
                                    <% if (session.getAttribute("emailExiste") != null) {%>
                                    <font color="#FF0000"> email já em utilização</font>
                                    <% }%>
                                </td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Nome</th>
                                <td><input type="text" <%if (nome != null) {%>value="<%=nome%>" <%}%> name="var_nome" size="20"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Data de Nascimento</th>
                                <td><input type="text" <%if (nascimento != null) {%>value="<%=nascimento%>" <%}%> name="var_datadenascimento" size="20"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Morada</th>
                                <td><input type="text" <%if (morada != null) {%>value="<%=morada%>" <%}%> name="var_morada" size="70"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Código Postal</th>
                                <td><input type="text" <%if (postal != null) {%>value="<%=postal%>" <%}%> name="var_codigopostal" size="15"></td>
                            <tr>
                                <td><INPUT TYPE="SUBMIT" VALUE="Submeter"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div id="footer">
		Copyright © YourGallery 2010
            </div>
        </div>
    </body>
</html>
