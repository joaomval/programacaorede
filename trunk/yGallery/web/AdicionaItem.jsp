<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Your Gallery</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
        <link href="../Users/Joao/Documents/NetBeansProjects/yGallery/web/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@page import="java.util.List"%>
        <%@page import="Sistema.TipoArtigo"%>
        <form id="adicionaItem" method="post" action="Artista?accao=adiciona">
            <label for="tipo_item">Tipo artigo</label>
            <select name="tipo_item" onchange="frmDisplay.submit()">
                <%List<String> vector = TipoArtigo.devolveTiposArtigo();
                            System.out.println("tipos@@@@ " + vector);
                            for (int i = 0; i != vector.size(); i++) {
                %>
                <option selected ><%=vector.get(i)%></option>
                <%}%>
            </select>
            <div><label for="nome">Nome</label>
                <input id="nome" type="text" name="var_nome" size="20"></div>
            <div>
                <style>
                    label { vertical-align: top; width: 100px }
                </style>
                <label for="descricao">Descrição</label>
                <textarea name="descricao" rows="5"  ></textarea></div>
            <div><label for="preco">Preço</label>
                <input id="preco" type="text" name="var_nome" size="5"></div>
            <INPUT TYPE="SUBMIT" class="formbutton" VALUE="adiciona">
        </form>
    </body>
</html>
