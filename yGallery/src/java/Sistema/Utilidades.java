/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BaseDados.Teste_Acesso_BD;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Utilidades {

    public static void populaAtributosSessionOutro(HttpServletRequest request, HttpServletResponse response, String ID) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        HttpSession session = request.getSession();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_idPessoa", ID);
        String qryName = new String("query1");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                session.setAttribute("email", rs.getString("eMail"));
                session.setAttribute("nome", rs.getString("nome"));
                session.setAttribute("dataNascimento", rs.getString("dataNascimento"));
                session.setAttribute("morada", rs.getString("morada"));
                session.setAttribute("codPostal", rs.getString("codPostal"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String qryName2 = new String("devolve_artista_por_idPessoa");
        ResultSet rs2;
        try {
            rs2 = bd.executeSelect(qryName2, params);
            while (rs2.next()) {
                if (rs2 != null) {
                    session.setAttribute("artista", "artista");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }


    }

    public static void populaAtributosSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_idPessoa", session.getAttribute("id"));
        String qryName = new String("query1");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                session.setAttribute("email", rs.getString("eMail"));
                session.setAttribute("nome", rs.getString("nome"));
                session.setAttribute("dataNascimento", rs.getString("dataNascimento"));
                session.setAttribute("morada", rs.getString("morada"));
                session.setAttribute("codPostal", rs.getString("codPostal"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bd.fechaStatement();
            bd.fechaConnection();
        }
    }

    public static void populaAtributosForm(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (request.getParameter("var_email") != null) {
            session.setAttribute("email", request.getParameter("var_email"));
            session.setAttribute("nome", request.getParameter("var_nome"));
            session.setAttribute("dataNascimento", request.getParameter("var_datadenascimento"));
            session.setAttribute("morada", request.getParameter("var_morada"));
            session.setAttribute("codPostal", request.getParameter("var_codigopostal"));
        }
    }

    public static boolean eUmEmail(final String string) {
        String email_regex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
        Pattern pattern = Pattern.compile(email_regex);

        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }

    public static boolean eValida(final String data) {
        System.out.println(data.length()+"<----------");
        Calendar calendar = Calendar.getInstance();
        int ano = Integer.parseInt(data.substring(0, 4));
        int mes = Integer.parseInt(data.substring(5, 7));
        int dia = Integer.parseInt(data.substring(8, 10));
    


        int ano_actual = calendar.get(Calendar.YEAR);


        int mes_actual = calendar.get(Calendar.MONTH);


        int dia_actual = calendar.get(Calendar.DAY_OF_MONTH);


        GregorianCalendar calendario_gregoriano = new GregorianCalendar(ano, mes - 1, dia);
        Date data_nascimento = new Date(calendario_gregoriano.getTimeInMillis());

        GregorianCalendar calendario_gregoriano2 = new GregorianCalendar(ano_actual, mes_actual - 1, dia_actual);
        Date data_actual = new Date(calendario_gregoriano2.getTimeInMillis());





        if (ano < 1850 || ano > ano_actual) // se o gajo for um velhadas dá erro
        {
            return false;
        }

        //VERIFICA SE A DATA DE ADMISSAO É MAIOR QUE A DATA DE NASCIMENTO
        if (!eMaiorDoQueDataNascimento(data_actual, data_nascimento)) //Se a data de admissão for superior à de nascimento dá erro
        {
            return false;
        }


        if (mes <= 0 || mes > 12) // se o mês estiver incorrecto
        {
            return false;
        }

        if (ano == ano_actual) { //se o ano for o actual
            if (mes - 1 == mes_actual) { // se o mês for o actual
                if (dia > dia_actual) { //se o dia for maior que o actual dá erro
                    return false;
                } else { // confirma o dia

                    return validaDiaDoMes(ano, mes, dia);

                }
            } else if (mes > mes_actual) { // caso seja um mês
                return false;

                /*}else  if (dia > dia_actual){ //se o dia for maior que o actual dá erro
                return false;*/
            } else { // confirma o dia

                return validaDiaDoMes(ano, mes, dia);

            }
        } else { //caso o ano seja inferior ao ano actual

            return validaDiaDoMes(ano, mes, dia);

        }

    }

    public static boolean eMaiorDoQueDataNascimento(final Date maior, final Date menor) {
        return maior.compareTo(menor) >= 0;
    }

    public static boolean validaDiaDoMes(final int ano, final int mes, final int dia) {

        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (dia < 1 || dia > 31) {
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dia < 1 || dia > 30) {
                    return false;
                }
                break;
            case 2:
                if ((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0))) {
                    if (dia < 1 || dia > 29) {
                        return false;
                    }
                    break;
                } else {
                    if (dia < 1 || dia > 28) {
                        return false;
                    }
                    break;
                }
        }

        return true;

    }
}

    


