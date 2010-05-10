<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Your Gallery</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div id="container">
            <jsp:include page="menu.jsp" flush="true" />
            <%--<div id="header" title="yourGallery">
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
                    <%@page import="Sistema.Artista" %>
                    <%if (Artista.eArtista((String) session.getAttribute("id"))) {%>
                    <li><a href="/yGallery/MinhaGaleria.jsp">myGallery</a></li>
                    <% }%>
                </ul>
            </div>--%>
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
				Noticias
                    </h3>
                    <p>
                        <img class="imagefloat" src="imagens/arte.jpg" alt="" width="149" height="93" border="1">

                    <h2>Obra de Picasso estabelece recorde mundial em leilões de arte</h2>
                    <p>NOVA YORK (Reuters) - A pintura "Nu, Folhas Verdes e Busto", de Pablo Picasso, foi leiloada por um valor impressionante de mais de 106 milhões de dólares pela Christie's na terça-feira, estabelecendo um recorde para uma obra de arte vendida em leilão.</p>

                    <p>O vibrante retrato em tamanho grande da amada e frequente modelo de Picasso, Marie-Therese Walter, foi o destaque de uma coleção internacional compilada pelos falecidos patronos de arte de Los Angeles Frances e Sidney Brody.</p>

                    <p>A estimativa era de que a pintura fosse vendida por mais de 80 milhões de dólares, mas muitos especialistas de arte previam nas últimas semanas que a obra ganharia confiança no mercado de artes, atualmente se recuperando, e quebraria o recorde anterior de 104,3 milhões de dólares estabelecido em fevereiro por "Walking Man I" (Homem que Caminha I), de Giacometti, estabelecido na Sotheby's de Londres.</p>
                    <ul>
                        <li><a href="http://br.noticias.yahoo.com/s/reuters/100505/entretenimento/cultura_arte_picasso_leilao_recorde" target="_blank" >mais...</a></li>
                    </ul>
                </div>
            </div>
            <div id="footer">
		Copyright © YourGallery 2010
            </div>
        </div>
    </body>
</html>
