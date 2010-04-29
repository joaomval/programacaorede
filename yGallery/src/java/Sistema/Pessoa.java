/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joao
 */
public class Pessoa {

    protected String email;
    protected Date dataNascimento;
    protected String morada;
    protected int codPostal;
    protected String password;

    public Pessoa() {
    }

    public Pessoa(String email, Date dataNascimento, String morada, int codPostal, String password) {

        this.email = email;
        this.dataNascimento = dataNascimento;
        this.morada = morada;
        this.codPostal = codPostal;
        this.password = password;
    }

    public String devolvePessoaPorEmail(HttpServletRequest request, HttpServletResponse response, String email) throws Exception {
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

    public void insere(final Hashtable<String, Object> params) {
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

    public String getEMail() {
        return email;
    }
}
