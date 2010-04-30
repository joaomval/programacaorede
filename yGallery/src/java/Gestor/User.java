/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestor;

import BaseDados.Teste_Acesso_BD;
import Sistema.Pessoa;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Joao
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class User extends HttpServlet {

    public static final String INSERIR_PESSOA = "inserir_pessoa";
    public static final String FAZ_LOGIN = "faz_login";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void fazLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        if (verificaLogin(request, response)) {
            Pessoa p = new Pessoa();
            String pessoa = p.devolvePessoaPorEmail(request, response);
            session.setAttribute("pessoa", pessoa);
            response.sendRedirect("/yGallery/index.jsp");
        } else {

            session.setAttribute("erro", "login errado");
            response.sendRedirect("/yGallery/erro.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accao = (String) request.getParameter("accao");
        if (accao.equals(User.INSERIR_PESSOA)) {
            try {
                inserirPessoa(request, response);
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accao.equals(User.FAZ_LOGIN)) {
            try {
                fazLogin(request, response);
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean verificaLogin(HttpServletRequest request, HttpServletResponse response) {
        Teste_Acesso_BD bd = new Teste_Acesso_BD();
        bd.carregaDriverEAbreConnection();
        bd.abreStatement();
        Hashtable params = new Hashtable();
        params.put("var_email", request.getParameter("var_email"));
        String qryName = new String("devolve_pessoa_por_email");
        ResultSet rs;
        try {
            rs = bd.executeSelect(qryName, params);
            while (rs.next()) {
                String email = rs.getString("eMail");
                String password = rs.getString("password");
                if (password.equals(request.getParameter("var_password"))) {
                    return true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.fechaStatement();
        bd.fechaConnection();
        return false;

    }

    private void retornaDadosRegisto(HttpServletRequest request, HttpServletResponse response, Hashtable params) {
        request.setAttribute("email", params.get("var_email"));
        request.setAttribute("nome", params.get("var_nome"));
        request.setAttribute("nascimento", params.get("var_datadenascimento"));
        request.setAttribute("morada", params.get("var_morada"));
        request.setAttribute("postal", params.get("var_codigopostal"));
    }

    private void inserirPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        HttpSession session = request.getSession();
        Hashtable params = new Hashtable();
        params.put("var_email", request.getParameter("var_email"));
        params.put("var_nome", request.getParameter("var_nome"));
        params.put("var_datadenascimento", request.getParameter("var_datadenascimento"));
        params.put("var_morada", request.getParameter("var_morada"));
        params.put("var_codigopostal", request.getParameter("var_codigopostal"));
        String password = request.getParameter("var_password");
        String repassword = request.getParameter("var_repassword");
        if (!password.equals(repassword) /*|| !verificaSeMailExiste(request, response, request.getParameter("var_email"))*/) {
            session.setAttribute("passwordDif", "diferente");
            response.sendRedirect("/yGallery/Registo.jsp");
        } else {
            if (verificaSeMailExiste(request, response)) {
                params.put("var_password", password);

                new Pessoa().insere(params);
                String nome = request.getParameter("var_nome");
                session.setAttribute("pessoa", nome);
                response.sendRedirect("/yGallery/index.jsp");

            } else {
                session.setAttribute("emailExiste", "emailExiste");
                retornaDadosRegisto(request, response, params);
                response.sendRedirect("/yGallery/Registo.jsp");
            }

        }
    }

    private void apagaPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        HttpSession session = request.getSession();
        Hashtable params = new Hashtable();
        params.put("var_email", request.getParameter("var_email"));


        if (verificaSeMailExiste(request, response)) {

            params.put("var_email", request.getParameter("var_email"));


        } else {
            session.setAttribute("erro", "e-mail não existe");
            response.sendRedirect("/yGallery/erro.jsp");
        }


    }

    private boolean verificaSeMailExiste(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (new Pessoa().devolvePessoaPorEmail(request, response) == null) {
            return true;
        }
        return false;

    }
}
