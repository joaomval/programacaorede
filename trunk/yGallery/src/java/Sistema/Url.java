/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.util.Hashtable;

/**
 *
 * @author Joao
 */
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
}
