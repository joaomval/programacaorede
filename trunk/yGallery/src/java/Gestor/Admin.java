
package Gestor;

import Sistema.Artista;
import Sistema.Galeria;
import Sistema.Pessoa;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Admin extends HttpServlet {

    public static final String ALTERA_DADOS = "altera_dados";

   
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
        if (accao.equals(User.ALTERA_DADOS)) {
            try {
                alteraDados(request, response);
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                editaPessoa(request, response, accao);
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

    private void editaPessoa(HttpServletRequest request, HttpServletResponse response, String id) {
        System.out.println("ID_EDITA_PESSOA@@@@ " + id);
        HttpSession session = request.getSession();
        session.setAttribute("id_pessoa_editar", id);
        try {
            response.sendRedirect("/yGallery/EditaUser2.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alteraDados(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        HttpSession session = request.getSession();
        Hashtable params = new Hashtable();
        session.removeAttribute("emailExiste");
        params.put("var_idPessoa", session.getAttribute("id_pessoa_editar"));
        params.put("var_email", request.getParameter("var_email"));
        params.put("var_nome", request.getParameter("var_nome"));
        params.put("var_datadenascimento", request.getParameter("var_datadenascimento"));
        params.put("var_morada", request.getParameter("var_morada"));
        params.put("var_codigopostal", request.getParameter("var_codigopostal"));

        if (User.verificaSeMailEstaLivre(request, response) || session.getAttribute("email").equals(request.getParameter("var_email"))) {
            Pessoa.altera(params);
            session.removeAttribute("emailExiste");
        } else {
            session.setAttribute("emailExiste", "emailExiste");
            response.sendRedirect("/yGallery/EditaUser2.jsp");
        }
        if (request.getParameter("var_artista") != null) {
            if (Artista.devolveArtistaPorId(request, response) != null) {
                response.sendRedirect("/yGallery/EditaUser2.jsp");
            } else {
                Hashtable params2 = new Hashtable();
                params2.put("var_idPessoa", session.getAttribute("id_pessoa_editar"));
                params2.put("var_homepage", "yGallery.site");
                Artista.insere(params2);
                Hashtable params3 = new Hashtable();
                params3.put("var_idArtista", Artista.devolveArtistaPorId(request, response));
                Galeria.insere(params3);
                response.sendRedirect("/yGallery/EditaUser2.jsp");
            }
        } else {
            if (Artista.devolveArtistaPorId(request, response) != null) {
                Hashtable params3 = new Hashtable();
                params3.put("var_idPessoa", session.getAttribute("id_pessoa_editar"));
                Artista.apaga(params3);
                response.sendRedirect("/yGallery/EditaUser2.jsp");
            } else {
                response.sendRedirect("/yGallery/EditaUser2.jsp");
            }
        }


    }
}
