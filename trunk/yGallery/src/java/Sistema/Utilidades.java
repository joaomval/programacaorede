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
    public static void populaAtributos(HttpServletRequest request, HttpServletResponse response){
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
    
}
