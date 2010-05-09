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
                <div id ="login">
                    <% if (session.getAttribute("pessoa") == null) {%>
                    <form method="post" action="User?accao=faz_login">
                        <div><label for="email">E-Mail</label>
                            <input id="email" type="text" name="var_email" size="15"></div>
                        <div><label for="password">Password</label>
                            <input id="password" type="password" name="var_password" size="15"></div>
                        <INPUT TYPE="SUBMIT" class="formbutton" VALUE="login">
                    </form>

                    <% } else {%>
                    <p>Bem-vindo </p>
                    <p><%= session.getAttribute("pessoa")%>
                        <a href="/yGallery/logout.jsp">Sair</a></p>
                        <%}%>
                </div>
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
				Alterar Dados:
                    </h3>
                    <form method="post" action="/yGallery/Admin?accao=altera_dados">
                        <table width="100%" border="0" cellpadding="8">
                            <tr>
                                <%@page import="Sistema.Utilidades" %>
                                <% Utilidades.populaAtributosSessionOutro(request, response, (String) session.getAttribute("id_pessoa_editar"));%>
                                <% String email = (String) session.getAttribute("email");
                                            String nome = (String) session.getAttribute("nome");
                                            String nascimento = (String) session.getAttribute("dataNascimento");
                                            String morada = (String) session.getAttribute("morada");
                                            String postal = (String) session.getAttribute("codPostal");
                                            String artista = (String) session.getAttribute("artista");
                                %>
                                <th width="23%" align="right" valign="middle" scope="col">E-Mail</th>
                                <td><input type="text" <% if (email != null) {%>value="<%=email%>" <%}%> name="var_email" size="20">
                                    <% if (session.getAttribute("emailExiste") != null) {%>
                                    <font color="#FF0000"> email j� em utiliza��o</font>
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
                                <th align="right" valign="middle" scope="row">C�digo Postal</th>
                                <td><input type="text" <%if (postal != null) {%>value="<%=postal%>" <%}%> name="var_codigopostal" size="15"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Artista</th>
                                <%System.out.println("ARTISTA@2@@ " + artista);%>
                                <td><input type="checkbox" name="var_artista" value="eartista"<%if (artista != null) {%> checked <%}%></td>
                            </tr>
                            <%session.setAttribute("artista", null);%>
                            <tr>
                                <td><INPUT TYPE="SUBMIT" class="formbutton" VALUE="Submeter"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div id="footer">
		Copyright � YourGallery 2010
            </div>
        </div>
    </body>
</html>
