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
		Copyright � YourGallery 2010
            </div>
        </div>
    </body>
</html>
