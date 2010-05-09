/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joao
 */
public class Utilidades {

    public static void populaAtributosSessionOutro(HttpServletRequest request, HttpServletResponse response, String ID) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        HttpSession session = request.getSession();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_idPessoa", ID);
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
        } 
        String qryName2 = new String("devolve_artista_por_idPessoa");
        ResultSet rs2;
        try {
            rs2 = bd.executeSelect(qryName2, params);
            while (rs2.next()){
                if(rs2!=null){
                    session.setAttribute("artista", "artista");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }


    }

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
        if (request.getParameter("var_email") != null) {
            session.setAttribute("email", request.getParameter("var_email"));
            session.setAttribute("nome", request.getParameter("var_nome"));
            session.setAttribute("dataNascimento", request.getParameter("var_datadenascimento"));
            session.setAttribute("morada", request.getParameter("var_morada"));
            session.setAttribute("codPostal", request.getParameter("var_codigopostal"));
        }
    }
}
