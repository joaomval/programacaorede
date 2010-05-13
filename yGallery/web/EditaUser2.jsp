<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <% if (session.getAttribute("id") == null) {%>
        <%session.setAttribute("erro", "Deve fazer login primeiro");%>
        <META HTTP-EQUIV="Refresh" CONTENT="0; URL=http:/yGallery/erro.jsp">
        <% }%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Your Gallery</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div id="container">
            <jsp:include page="menu.jsp" flush="true" />
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
                                    <font color="#FF0000"> email já em utilização</font>
                                    <% }%>
                                     <% if (session.getAttribute("NaoEmail") != null) {%>
                                    <font color="#FF0000"> email não é válido</font>
                                    <% }%>
                                </td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Nome</th>
                                <td><input type="text" <%if (nome != null) {%>value="<%=nome%>" <%}%> name="var_nome" size="20"><% if (session.getAttribute("NaoNome") != null) {%>
                                    <font color="#FF0000"> nome não é válido</font>
                                    <% }%></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Data de Nascimento</th>
                                <td><input type="text"  <%if (nascimento != null) {%>value="<%=nascimento%>" <%}%> name="var_datadenascimento" size="20"><% if (session.getAttribute("NaoData") != null) {%>
                                    <font color="#FF0000"> data não é válida</font>
                                    <% }%></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Morada</th>
                                <td><input type="text" <%if (morada != null) {%>value="<%=morada%>" <%}%> name="var_morada" size="70"></td>
                            </tr>
                            <tr>
                                <th align="right" valign="middle" scope="row">Código Postal</th>
                                <td><input type="text" <%if (postal != null) {%>value="<%=postal%>" <%}%> name="var_codigopostal" size="15"><% if (session.getAttribute("NaoPostal") != null) {%>
                                    <font color="#FF0000">Código postal não é válido</font>
                                    <% }%></td>
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
		Copyright © YourGallery 2010
            </div>
        </div>
    </body>
</html>
