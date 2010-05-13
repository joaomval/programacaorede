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

public class Artigo {

    public static List<String> devolveArtigos() {
        List<String> artigos = new ArrayList<String>();
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("devolve_artigos");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, null);
            while (rs.next()) {
                artigos.add(rs.getString("idArtigo"));
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoArtigo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return artigos;
    }

    public static String devolveIdArtigoPorNome(String nome) throws Exception {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_nome", nome);
        String qryName = new String("devolve_idartigo_por_nome");
        ResultSet rs;
        String idArtigo = null;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                idArtigo = rs.getString("idArtigo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return idArtigo;
    }

    public static void insere(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("insere_artigo");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bd.fechaStatement();
        bd.fechaConnection();
    }

    public void apaga(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("apaga_artigo");
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
        String qryName = new String("altera_artigo");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bd.fechaStatement();
        bd.fechaConnection();

    }
}
