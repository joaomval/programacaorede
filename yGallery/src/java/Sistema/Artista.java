package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Artista {

    public static String devolveArtistaPorId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_idPessoa", session.getAttribute("id_pessoa_editar"));
        String qryName = new String("devolve_artista_por_idPessoa");
        ResultSet rs;
        String artista = null;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                artista = rs.getString("idArtista");
                System.out.println("ARTISTA@@@@@@@: " + artista);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
        return artista;
    }

    public static void insere(final Hashtable<String, Object> params) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        String qryName = new String("insere_artista");
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
        String qryName = new String("apaga_artista");
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
        String qryName = new String("altera_artista");
        try {
            bd.executaUpdate(qryName, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bd.fechaStatement();
        bd.fechaConnection();

    }
}
