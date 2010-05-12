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
                <form action="enviaremail.jsp" method="post">
                    <table border="0" align="center" bgcolor="gray">
                        <tr>
                        <tr>
                            <td>
                                <font color="orange"><b>Nome..:</b></font></td>
                            <td><input name="nome" type="Text"><font color="orange"></td>
                        </tr>
                        <td>
                            <font color="orange"><b>E-mail..:</b> </font></td>
                        <td><input type="Text" name="de"><font color="orange"></td>
                        </tr>
                        <td>
                            <font color="orange"><b>Fone..:</b> </font></td>
                        <td><input type="Text" name="fone"><font color="orange"></td>
                        </tr>
                        <tr>
                            <td><font color="orange"><b>Assunto..:</b></font></td>
                            <td><select name="assunto">
                                    <option>Chamados</option>
                                    <option>Contato</option>
                                    <option>Informações</option>
                                </select><font color="orange"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <textarea name="obs" rows=10 cols=45>
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
				Administração

                    </h3>

                    <table width="100%" border="0" cellpadding="8">
                        <tr>
                            <th width="23%" align="right" valign="middle" scope="col"><a href="/yGallery/EditarUser.jsp" <img src="imagens/edit_user.png"></a>
                            </th>
                            <td><a href="/yGallery/EditarUser.jsp"> editar Utilizador</a> </td>
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
		Copyright © YourGallery 2010
            </div>
        </div>
    </body>
</html>
