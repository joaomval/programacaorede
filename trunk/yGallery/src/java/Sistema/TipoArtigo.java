/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TipoArtigo {

    public String devolveTipoArtigoPorId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_id", request.getParameter("var_id"));
        String qryName = new String("devolve_tipo_artigo_por_id");
        ResultSet rs;
        String tipoartigo = null;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                tipoartigo = rs.getString("idTipoArtigo");
                System.out.println("Tipo Artigo@@@@@@@: " + tipoartigo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return tipoartigo;
    }

    public void insere(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("insere_tipo_artigo");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
    }

    public void apaga(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("apaga_tipo_artigo");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
    }

    public static List<String> devolveTiposArtigo() {
        List<String> artigos = new ArrayList<String>();
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("devolve_tipos_artigo");
        ResultSet rs;
        try {
            rs=bd.executeSelect(qryName);
            while(rs.next()){
                artigos.add(rs.getString("tipo"));
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoArtigo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return artigos;
    }

     public static String devolveId(String tipo) {
       String id_artigo=null;
       Hashtable params = new Hashtable();
       params.put("var_tipo_artigo", tipo);
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("devolve_tipos_artigo");
        ResultSet rs;
        try {
            rs=bd.executeSelect(qryName);
            while(rs.next()){
                id_artigo = rs.getString("idTipoArtigo");
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoArtigo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return id_artigo;
    }
}
