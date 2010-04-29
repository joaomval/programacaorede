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
		  Sitename
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
                    <li><a href="/yGallery/index.jsp">Inicio</a></li>
                    <li><a href="#">Galeria</a></li>
                    <li><a href="#">Agenda</a></li>
                    <li><a href="#">Contacto</a></li>
                </ul>
            </div>
            <div id="menu">
                <h3>
			Archives
                </h3>
                <ul>
                    <li><a href="#">December 2003</a></li>
                    <li><a href="#">November 2003</a></li>
                    <li><a href="#">October 2003</a></li>
                    <li><a href="#">September 2003</a></li>
                    <li><a href="#">August 2003</a></li>
                </ul>
                <h3>
			Last 10 Entries
                </h3>
                <ul>
                    <li><a href="#">Entry 120 (4)</a></li>
                    <li><a href="#">Entry 119 (0)</a></li>
                    <li><a href="#">Entry 118 (9)</a></li>
                    <li><a href="#">Entry 117 (3)</a></li>
                </ul>
            </div>
            <div id="contents">
                <div class="blogentry">
                    <h3>
				Registo
                    </h3>
                    <form method="post" action="/yGallery/User?accao=inserir_pessoa">
                        <table width="100%" border="0" cellpadding="8">
                            <tr>
                                <th width="23%" align="right" valign="middle" scope="col">E-Mail</th>
                                <td><input type="text" name="var_email" size="20">
                                    <% if(session.getAttribute("emailExiste")!=null){ %>
                                    <font color="#FF0000"> passwords não são iguais</font>
                                    <% } %>
                                    
                                    </td>

                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Nome</th>
                                <td><input type="text" name="var_nome" size="20"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Data de Nascimento</th>
                                <td><input type="text" name="var_datadenascimento" size="20"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Morada</th>
                                <td><input type="text" name="var_morada" size="70"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Código Postal</th>
                                <td><input type="text" name="var_codigopostal" size="15"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Password</th>
                                <td><input type="password" name="var_password" size="20"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Repetir Password</th>
                                <td><input type="password" name="var_repassword" size="20">
                                    <% if(session.getAttribute("passwordDif")!=null){ %>
                                    <font color="#FF0000"> passwords não são iguais</font>
                                    <% } %>
                                </td>
                            </tr>
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
