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
        <%@page import="java.util.List"%>
        <%@page import="Sistema.TipoArtigo"%>
        <div style="width: 335px">
            <fieldset style="border-color: #f2a36c;">
                <legend><h3>Inserir Artigo:</h3></legend>
                <form name="form_insere" action="upload" method="post" ENCTYPE="multipart/form-data">
                    <style>
                        label { vertical-align: top; width: 75px }
                    </style>
                    <div><input type="file" name="file" id="file"></div>
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
                    <div><label for="descricao">Descrição</label>
                        <textarea name="var_descricao" rows="5"  ></textarea></div>
                    <div><label for="preco">Preço</label>
                        <input id="preco" type="text" name="var_preco" size="5"></div>
                    <input type="submit" value="Enviar">
                    <%if(session.getAttribute("sucesso_artigo")!=null){%>
                    <p style="color: green">Artigo adicionado com sucesso!</p>
                        <%}%>
                </form>
            </fieldset>
        </div>
    </body>
</html>
