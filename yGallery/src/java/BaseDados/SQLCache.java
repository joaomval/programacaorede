/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDados;

/**
 *
 * @author Joao
 */
import java.util.*;
import java.io.*;

/**
 * Classe que obtêm instruções SQL guardadas num ficheiro de propriedades
 * e que as manipula, por exemplo para verificar a correcção de uma query.
 */
public class SQLCache {

    /**
     * As instruções SQL obtidas a partir do ficheiro ficarão guardadas
     * num objecto do tipo Properties
     * (ver http://java.sun.com/j2se/1.4.2/docs/api/java/util/Properties.html
     * e
    http://java.sun.com/docs/books/tutorial/essential/attributes/properties.html
    )
     */
    private Properties props = new Properties();
    /**
     * Nome do ficheiro de propriedades
     */
    private static final String SQL_FILE = "C:/Users/Joao/Documents/NetBeansProjects/yGallery/sql.properties";
// várias hipóteses de acesso ao ficheiro sql.properties:
// quando se usa uma classe normal:
//private static final String SQL_FILE = "..\\..\\..\\..\\sql.properties";
// quando se acede à base a partir de um servlet:
// 1ª hipótese:
//private static final String SQL_FILE = "..\\..\\sql.properties";
// e copiar à mão o ficheiro sql.properties para "C:\ProgramFiles\netbeans-5.5\enterprise3\apache-tomcat-5.5.17"
// 2ª hipótese:
// posicionar na raiz de C: desde "C:\ProgramFiles\netbeans-5.5\enterprise3\apache-tomcat-5.5.17\webapps"
// e depois percorrer o Path até ao ficheiro de propriedades
// private static final String SQL_FILE ="..\\..\\..\\..\\..\\..\\Users\\Jorge\\Desktop\\ISCTE\\PRede\\PRede_2007-08\\Testes\\TesteMySQL\\sql.properties";

    /**
     * Carrega instruções SQL a partir do ficheiro de propriedades e
    guarda-as num
    objecto Properties
     */
//    public void loadSQLStatements() {
//        try {
//// carrega as instruções SQL a partir de um ficheiro de propriedades
//            InputStream in =
//                    getClass().getClassLoader().getResourceAsStream(SQL_FILE);
//            System.out.println("in: " + in);
//            props.load(in);
//            System.out.println("props" + props);
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    	public void loadSQLStatements() {
		try {
			// carrega as instrucoes SQL a partir de um ficheiro de propriedades
			FileInputStream in = new FileInputStream(SQL_FILE);
			props.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Método chamado antes de executar uma query, para substituir
     * as variáveis na query pelos parâmetros devidos.
     * Os parâmetros estão guardados numa Hastable
     * (ver http://java.sun.com/j2se/1.4.2/docs/api/java/util/Hashtable.html)
     */
    public String compileQuery(String qryName, Hashtable params) throws
            Exception {
        String qry = getSQLStatement(qryName);
        for (Enumeration enu = params.keys(); enu.hasMoreElements();) {
            String key = (String) enu.nextElement();
            String value = (String) params.get(key);
            System.out.println(key);
            qry = qry.replaceAll(key, value);
        }
        return qry;
    }

    /**
     * Método que verifica se uma instrução SQL existe
     */
    public String getSQLStatement(String sqlName) throws Exception {
        String sqlStm = (String) props.get(sqlName);
        if (sqlStm == null) {
            throw new Exception("A instrução SQL não foi encontrada.");
        } else {
            return sqlStm;
        }
    }
}
