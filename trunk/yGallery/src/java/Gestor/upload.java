/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestor;

import Sistema.Artigo;
import Sistema.TipoArtigo;
import Sistema.Galeria;
import Sistema.Url;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

/**
 *
 * @author Joao
 */
public class upload extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
//queremos projecto\web\pics
        String path = this.getServletContext().getRealPath("/"); //isto vai parar ao projecto\build\src
        path = path.substring(0, path.lastIndexOf("\\build\\"));  //substring volta para projecto\
        path += "\\web\\upload\\"; //finalmente fica com projecto\web\pics
        Hashtable params = new Hashtable();
        String nome_ficheiro = null;
        try {
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    // System.out.println(name+"<-----------------");
                    String value = item.getString();
                    //   System.out.println(value+"<-----------------");
                    //fazer algo com isto
                    params.put(name, value);

                } else {

                    nome_ficheiro = ((String) session.getAttribute("id") + System.currentTimeMillis() + ".jpg");
                    item.write(new File(path + nome_ficheiro));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            trataHash(params, request);
            trataHash2(params, nome_ficheiro);
            
        } catch (Exception ex) {
            Logger.getLogger(upload.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("/yGallery/MinhaGaleria.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void trataHash(final Hashtable params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        params.put("var_idTipoArtigo", TipoArtigo.devolveId((String) params.get("tipo_item")));
        params.remove("tipo_item");
        params.put("var_idGaleria", (String) Galeria.devolveIdGaleriaPorIdArtista(Sistema.Artista.devolveIdArtistaPorIdPessoa((String) session.getAttribute("id"))));
        Artigo.insere(params);

    }

    private void trataHash2(final Hashtable params, String nome_ficheiro) {
        Hashtable params2 = new Hashtable();
        params2.put("var_url", ("upload/" + nome_ficheiro));
        try {
            params2.put("var_idArtigo", (String) Artigo.devolveIdArtigoPorNome((String) params.get("var_nome")));
        } catch (Exception ex) {
            Logger.getLogger(upload.class.getName()).log(Level.SEVERE, null, ex);
        }
        Url.insere(params2);
    }
}
