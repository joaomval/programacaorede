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
                    <li><a href="/yGallery/Administracao.jsp">Administra��o</a></li>
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
				Noticias
                    </h3>
                    <object class="imagefloat" width="240" height="192.5"><param name="movie" value="http://www.youtube.com/v/ab8s3B7CVjo&hl=pt_PT&fs=1&rel=0&color1=0xe1600f&color2=0xfebd01"/><param name="allowFullScreen" value="true"/><param name="allowscriptaccess" value="always"/><embed src="http://www.youtube.com/v/ab8s3B7CVjo&hl=pt_PT&fs=1&rel=0&color1=0x5d1719&color2=0xcd311b" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="240" height="192.5"></embed></object>
                    <h2>Leil�es de Arte Contempor�nea em Londres</h2>
                    <p>A Christie's e a Sotheby's realizaram os seus leil�es de Arte Contempor�nea em Londres, nos dias 30 de Junho e 1 de Julho, totalizando &euro 108.5 milh�es de euros e &euro 119.5 milh�es, respectivamente.</p>
                    <p>Na Christie's, Naked Portrait with Reflection tornou-se no segundo quadro mais bem vendido em leil�o de Lucian Freud por &euro 14.8 milh�es; Three Studies for a Self-Portrait, um tr�ptico de Francis Bacon foi vendido por &euro 21.7 milh�es e Balloon Flower (Magenta) estabeleceu um novo recorde em leil�o para Jeff Koons, ao ser ser arrematado por &euro 16.2 milh�es.</p>
                    <p>Na noite seguinte, o principal destaque da Sotheby's foi Study for Head of George Dyer tamb�m de Francis Bacon, vendido por &euro 17.3 milh�es. Overseas Nurse de Richard Prince foi arrematado por 5.3 milh�es de euros e estabeleceu um novo recorde para o artista em leil�o e o quadro Sem T�tulo (Pecho/Oreja) de Jean Michel Basquiat, que pertencia aos U2 subiu at� aos 6.4 milh�es de euros e foi a segunda pe�a mais bem vendida da noite.</p>
                    <ul>
                        <li><a href="http://aeiou.expresso.pt/leiloes-de-arte-contemporanea-em-londres=f359456" target="_blank" >mais...</a></li>
                    </ul>
                </div>
            </div>
            <div id="footer">
		Copyright � YourGallery 2010
            </div>
        </div>
    </body>
</html>
