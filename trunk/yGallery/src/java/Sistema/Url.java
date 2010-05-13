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
import java.util.logging.Level;
import java.util.logging.Logger;


public class Url {

    public static void insere(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("insere_url");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
    }

    public static List<String> devolveURLsArtigo(String idArtigo) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        List<String> lista = new ArrayList<String>();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("devolve_urls_por_id_artigo");
        Hashtable params = new Hashtable();
        params.put("var_idArtigo", idArtigo);
        ResultSet rs = null;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                lista.add(rs.getString("URL"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Url.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Url.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return lista;
    }
}
