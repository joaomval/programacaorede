/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.sql.ResultSet;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joao
 */
public class Utilidades {

    public static void populaAtributosSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_idPessoa", session.getAttribute("id"));
        String qryName = new String("query1");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                session.setAttribute("email", rs.getString("eMail"));
                session.setAttribute("nome", rs.getString("nome"));
                session.setAttribute("dataNascimento", rs.getString("dataNascimento"));
                session.setAttribute("morada", rs.getString("morada"));
                session.setAttribute("codPostal", rs.getString("codPostal"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
    }

    public static void populaAtributosForm(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("email", request.getAttribute("var_mail"));
        session.setAttribute("nome", request.getAttribute("var_nome"));
        session.setAttribute("dataNascimento", request.getAttribute("var_datadenascimento"));
        session.setAttribute("morada", request.getAttribute("var_morada"));
        session.setAttribute("codPostal", request.getAttribute("var_codigopostal"));
    }
}
