/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Pessoa {

    public static String devolveIdPorEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_email", request.getParameter("var_email"));
        String qryName = new String("devolve_pessoa_por_email");
        ResultSet rs;
        String pessoa = null;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                pessoa = rs.getString("idPessoa");
                System.out.println("PESSOA@@@@@@@: " + pessoa);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return pessoa;
    }

    public static List<String> devolveIdPessoas(HttpServletRequest request, HttpServletResponse response) {
        List<String> vector = new ArrayList<String>();
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("devolve_todas_pessoas");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, null);
            while (rs.next()) {
                vector.add(rs.getString("idPessoa"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return vector;
    }

    public static List<String> devolveNomePessoas(HttpServletRequest request, HttpServletResponse response) {
        List<String> vector = new ArrayList<String>();
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("devolve_todas_pessoas");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, null);
            while (rs.next()) {
                vector.add(rs.getString("nome"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return vector;
    }

    public static List<String> devolveEmailPessoas(HttpServletRequest request, HttpServletResponse response) {
        List<String> vector = new ArrayList<String>();
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("devolve_todas_pessoas");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, null);
            while (rs.next()) {
                vector.add(rs.getString("eMail"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return vector;
    }

    public static String devolvePessoaPorEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_email", request.getParameter("var_email"));
        String qryName = new String("devolve_pessoa_por_email");
        ResultSet rs;
        String pessoa = null;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                pessoa = rs.getString("nome");
                System.out.println("PESSOA@@@@@@@: " + pessoa);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return pessoa;
    }

    public static void insere(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("query2");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bd.fechaStatement();
        bd.fechaConnection();
    }

    public static void apaga(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("apaga_pessoa");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bd.fechaStatement();
        bd.fechaConnection();
    }

    public static void altera(final Hashtable<String, Object> params)
            throws SQLException {

        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("altera_pessoa");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bd.fechaStatement();
        bd.fechaConnection();

    }
}
