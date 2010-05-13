/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Galeria {

    public static String devolveGaleriaPorId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_id", request.getParameter("var_id"));
        String qryName = new String("devolve_galeria_por_id");
        ResultSet rs;
        String galeria = null;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                galeria = rs.getString("idGaleria");
                System.out.println("Galeria@@@@@@@: " + galeria);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return galeria;
    }
     public static String devolveIdGaleriaPorIdArtista(String idArtista) throws Exception {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_idArtista", idArtista);
        String qryName = new String("devolve_Galeria_por_idArtista");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                idArtista = rs.getString("idGaleria");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return idArtista;
    }

    public static void insere(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("insere_galeria");
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
        String qryName = new String("apaga_galeria");
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
        String qryName = new String("altera_galeria");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bd.fechaStatement();
        bd.fechaConnection();

    }
}
