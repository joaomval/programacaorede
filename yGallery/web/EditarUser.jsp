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
                    <li><a href="/yGallery/Administracao.jsp">Administração</a></li>
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
				Editar Utilizador
                    </h3>
                    <%@page import="Sistema.Pessoa"%>
                    <%@page import="java.util.List"%>
                    <%List<String> vector = Pessoa.devolveEmailPessoas(request, response);%>
                    <%List<String> vector2 = Pessoa.devolveIdPessoas(request, response);%>
                    <%List<String> vector3 = Pessoa.devolveNomePessoas(request, response);%>
                    <TABLE border="1px">

                        <th align="middle">ID</th>
                        <th align="middle">E-Mail</th>
                        <th align="middle">Nome</th>
                        <th align="middle">Editar</th>

                        <% for (int row = 0; row < vector.size(); row++) {%>
                        <TR> 
                            <%for (int col = 0; col < 4; col++) {%>
                            <TD align="middle">
                                <%if (col == 3) {%> <a href="Admin?accao=<%=vector2.get(row)%>"><img src="imagens/editar.png"></a><%}%>
                                <%if (col == 1) {%> <%=vector.get(row)%><%}%>
                                <%if (col == 0) {%> <%=vector2.get(row)%><%}%>
                                <%if (col == 2) {%> <%=vector3.get(row)%><%}%>
                                <%}%>
                            </TD>
                            <% }%>
                        </TR>
                    </TABLE>
                           
                    <%--<select name="utlizador" onchange="frmDisplay.submit()">
                        <%List<String> vector = Pessoa.devolveEmailPessoas(request, response);
                                    System.out.println("VECTOR@@@@ " + vector);
                                    for (int i = 0; i != vector.size(); i++) {
                        %>
                        <option selected ><%=vector.get(i)%></option>
                        <%}%>
                    </select>
                    --%>
                </div>
            </div>
            <div id="footer">
		Copyright © YourGallery 2010
            </div>
        </div>
    </body>
</html>
