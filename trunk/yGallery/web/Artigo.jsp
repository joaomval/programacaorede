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
                <h3>
				Artigo:
                </h3>
                <%@page import="Sistema.Artigo" %>
                <style>
                    img{
                        max-width:500px;
                        max-height:100%;
                        max-width:100%;
                    }
                </style>
                <img src="<%=session.getAttribute("url_artigo_detalhe")%>" alt=""/>
                <table>
                    <tr>
                        <td>
                            Nome:
                        </td>
                        <td>
                            <%=session.getAttribute("artigo_detalhe")%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Descrição:
                        </td>
                        <td>
                            <%=Artigo.devolveDadosArtigoPorId(Artigo.devolveIdArtigoPorNome((String) session.getAttribute("artigo_detalhe"))).get("descricao")%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Preço:
                        </td>
                        <td>
                            <%=Artigo.devolveDadosArtigoPorId(Artigo.devolveIdArtigoPorNome((String) session.getAttribute("artigo_detalhe"))).get("preco")%> &euro
                        </td>
                    </tr>
                    <tr><p></p></tr>
                    <tr>
                        <td>
                            <%session.setAttribute("erro", "Pagamento não implementado");%>
                            <a href="/yGallery/erro.jsp">Comprar</a>
                        </td>
                    </tr>
                </table>


            </div>
            <div id="footer">
                Copyright © YourGallery 2010
            </div>
        </div>
    </body>
</html>
