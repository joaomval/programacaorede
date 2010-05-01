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

public class TipoEvento {

    public String devolveTipoEventoPorId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_id", request.getParameter("var_id"));
        String qryName = new String("devolve_tipo_evento_por_id");
        ResultSet rs;
        String tipoevento = null;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                tipoevento = rs.getString("idTipoArtigo");
                System.out.println("Tipo Artigo@@@@@@@: " + tipoevento);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return tipoevento;
    }

    public void insere(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("insere_tipo_evento");
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
        String qryName = new String("apaga_tipo_evento");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bd.fechaStatement();
        bd.fechaConnection();
    }
}
