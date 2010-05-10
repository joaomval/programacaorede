<html>
    <body>
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