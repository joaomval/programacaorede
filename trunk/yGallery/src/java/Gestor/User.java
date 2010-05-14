package Gestor;

import BaseDados.Teste_Acesso_BD;
import Sistema.Pessoa;
import Sistema.Utilidades;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class User extends HttpServlet {

    public static final String INSERIR_PESSOA = "inserir_pessoa";
    public static final String FAZ_LOGIN = "faz_login";
    public static final String ALTERA_DADOS = "altera_dados";
    public static final String RECUPERAR_PASSWORD = "recuperar_password";

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
            String pessoa = Pessoa.devolvePessoaPorEmail(request, response);
            session.setAttribute("pessoa", pessoa);
            session.setAttribute("id", Pessoa.devolveIdPorEmail(request, response));
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
        if (accao.equals(INSERIR_PESSOA)) {
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
        } else if (accao.equals(User.ALTERA_DADOS)) {
            try {
                alteraDados(request, response);
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accao.equals(User.RECUPERAR_PASSWORD)) {
            try {
                recuperarPassword(request, response);
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

    private void inserirPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("NaoEmail");
        session.removeAttribute("NaoData");
        session.removeAttribute("NaoNome");
        session.removeAttribute("NaoPostal");
        session.removeAttribute("emailExiste");
        session.removeAttribute("passwordDif");

        Hashtable params = new Hashtable();
        params.put("var_email", request.getParameter("var_email"));
        params.put("var_nome", request.getParameter("var_nome"));
        params.put("var_datadenascimento", request.getParameter("var_datadenascimento"));
        params.put("var_morada", request.getParameter("var_morada"));
        params.put("var_codigopostal", request.getParameter("var_codigopostal"));
        String password = request.getParameter("var_password");
        String repassword = request.getParameter("var_repassword");
        String email = request.getParameter("var_email");
        String data = request.getParameter("var_datadenascimento");
        String nome1 = request.getParameter("var_nome");
        String codPostal = request.getParameter("var_codigopostal");
        if (!password.equals(repassword)) {
            session.setAttribute("passwordDif", "diferente");
            Utilidades.populaAtributosForm(request, response);
            response.sendRedirect("/yGallery/Registo.jsp");
        } else {
            if (verificaSeMailEstaLivre(request, response)) {
                params.put("var_password", password);
                if (!(Utilidades.eUmEmail(email))) {
                    session.setAttribute("NaoEmail", "NaoEmail");
                    Utilidades.populaAtributosForm(request, response);
                    response.sendRedirect("/yGallery/Registo.jsp");
                } else if (Utilidades.naoData(data) || !(Utilidades.eValida(data))) {
                    session.setAttribute("NaoData", "NaoData");
                    Utilidades.populaAtributosForm(request, response);
                    response.sendRedirect("/yGallery/Registo.jsp");
                } else if (Utilidades.naoNome(nome1)) {
                    session.setAttribute("NaoNome", "NaoNome");
                    Utilidades.populaAtributosForm(request, response);
                    response.sendRedirect("/yGallery/Registo.jsp");
                } else if (Utilidades.naoPostal(codPostal)) {
                    session.setAttribute("NaoPostal", "NaoPostal");
                    Utilidades.populaAtributosForm(request, response);
                    response.sendRedirect("/yGallery/Registo.jsp");
                } else {
                    Pessoa.insere(params);
                    session.setAttribute("erro", "Registo efectuado com sucesso");
                    response.sendRedirect("/yGallery/erro.jsp");
                }

            } else {
                session.setAttribute("emailExiste", "emailExiste");
                Utilidades.populaAtributosForm(request, response);
                response.sendRedirect("/yGallery/Registo.jsp");
            }

        }


    }

    private void apagaPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        HttpSession session = request.getSession();
        Hashtable params = new Hashtable();
        params.put("var_email", request.getParameter("var_email"));
        if (verificaSeMailEstaLivre(request, response)) {
            params.put("var_email", request.getParameter("var_email"));
        } else {
            session.setAttribute("erro", "e-mail não existe");
            response.sendRedirect("/yGallery/erro.jsp");
        }
    }

    public static boolean verificaSeMailEstaLivre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (Pessoa.devolvePessoaPorEmail(request, response) == null) {
            return true;
        }
        return false;

    }

    private void alteraDados(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        HttpSession session = request.getSession();
        Hashtable params = new Hashtable();
        session.removeAttribute("emailExiste");
        params.put("var_idPessoa", session.getAttribute("id"));
        params.put("var_email", request.getParameter("var_email"));
        params.put("var_nome", request.getParameter("var_nome"));
        params.put("var_datadenascimento", request.getParameter("var_datadenascimento"));
        params.put("var_morada", request.getParameter("var_morada"));
        params.put("var_codigopostal", request.getParameter("var_codigopostal"));

        if (verificaSeMailEstaLivre(request, response) || session.getAttribute("email").equals(request.getParameter("var_email"))) {
            Pessoa.altera(params);
            session.removeAttribute("emailExiste");
            response.sendRedirect("/yGallery/index.jsp");
        } else {
            session.setAttribute("emailExiste", "emailExiste");
            response.sendRedirect("/yGallery/Perfil.jsp");
        }
    }

    private void recuperarPassword(HttpServletRequest request, HttpServletResponse response) throws AddressException, MessagingException, Exception {
        HttpSession session1 = request.getSession();
        Properties props = System.getProperties();
        String mensagem = null;
        String password = Pessoa.devolvePasswordPorEmail((String) request.getParameter("para"));
        if (password != null) {

            mensagem = "A sua password do yourgallery é: " + password + "";
            /*props.put("mail.smtp.host", mailServer);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");*/
            //testes
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.debug", "true");
            props.put("mail.mime.charset", "ISO-8859-1");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getDefaultInstance(props);//recebe props

            InternetAddress destinatario = new InternetAddress((String) request.getParameter("para"));
            InternetAddress remetente = new InternetAddress("yourgallery@gmail.com");

            Message msg = new MimeMessage(session);
            msg.setSentDate(new Date());//novo
            msg.setFrom(remetente);
            msg.setRecipient(Message.RecipientType.TO, destinatario);
            msg.setSubject("Recuperar Password");
            msg.setContent(mensagem.toString(), "text/HTML");

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "your.gallery.lei@gmail.com", "qwerty54321");
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            response.sendRedirect("/yGallery/index.jsp");

        } else {
            session1.setAttribute("erro", "O endereço não está registado");
            response.sendRedirect("/yGallery/erro.jsp");
        }
    }
}
