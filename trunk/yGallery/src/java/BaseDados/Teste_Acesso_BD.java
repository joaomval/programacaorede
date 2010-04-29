/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDados;

/**
 *
 * @author Joao
 */
import java.sql.*;
import java.util.*;

/**
 * Classe para testar o acesso a uma BD relacional
 * com recurso a um ficheiro de texto do tipo '.properties'
 * que guarda todos os comandos SQL, o que permite separar
 * realmente o código JAVA do código SQL
 */
public class Teste_Acesso_BD {

    Connection connect;
    Statement stmt;
// instância de SQLCache guardará as strings que representam comandos SQL
    SQLCache cache;

    /**
     * Construtor que carrega as intruções SQL do ficheiro de properties
     * para o atributo 'cache' (instância de SQLCache)
     */
    public Teste_Acesso_BD() {
        cache = new SQLCache();
        cache.loadSQLStatements();
    }

    /**
     * Carrega o driver MySQL e abre a Connection em localhost, sem utilizar
    password.
     */
    public void carregaDriverEAbreConnection() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/yourgallery?"
                    + "user=root&password=qwerty");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    /**
     ** Abre um Statement.
     */
    public void abreStatement() {
        try {
            stmt = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fecha um Statement.
     */
    public void fechaStatement() {
        try {
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fecha uma Connection.
     */
    public void fechaConnection() {
        try {
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que executa uma query com recurso às classes
     * auxiliares SQLCache e QueryResult.
     */
    public ResultSet executeSelect(
            String qryName, Hashtable params) throws Exception {
// instância de QueryResult guardará o resultado da query
        ResultSet rs = null;
        try {
            String query = cache.compileQuery(qryName, params);
             rs = stmt.executeQuery(query);
            //result = new QueryResult();
            //result.populateData(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void executaUpdate(String qryName, Hashtable params) throws SQLException, Exception {

	String query = cache.compileQuery(qryName, params);
	stmt.executeUpdate(query);

    }
    /**
     * O método main testa a execução de uma query com parâmetros.
     */
    public static void main(String args[]) {
        Teste_Acesso_BD testeBD = new Teste_Acesso_BD();
        testeBD.carregaDriverEAbreConnection();
        testeBD.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_nome", "Antonio");
        params.put("var_password", "passdoantonio");
        String qryName = new String("query1");
        ResultSet qr;
        try {
            qr = testeBD.executeSelect(qryName, params);
            //System.out.println(qr);
           while(qr.next()){
                String JNome = qr.getString("nome");
                String JPassword = qr.getString("password");
                System.out.println(JNome + " - " + JPassword);
            
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        testeBD.fechaStatement();
        testeBD.fechaConnection();
    }
}
/**
 * O ficheiro sql.properties tem o seguinte conteúdo para teste: *
 *
 * # Sample ResourceBundle properties file
 * query1= SELECT * from pessoa
 * query2= SELECT * from pessoa where nome='Maria'
 * query3= SELECT * from pessoa where nome='varNome'
 *
 * ( Para conhecer os ficheiros de propriedades ver em
 * http://java.sun.com/docs/books/tutorial/i18n/resbundle/propfile.html )
 *
 */
