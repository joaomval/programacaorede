/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Joao
 */
public class Artista extends HttpServlet {

    public static final String INSERE_ARTIGO = "insere_artigo";

    public void UploadFicheiro(HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Entrou.%%%%%%%%%%");
        //queremos projecto\web\pics
        String path = this.getServletContext().getRealPath("/"); //isto vai parar ao projecto\build\src
        path = path.substring(0, path.lastIndexOf("\\build\\"));  //substring volta para projecto\
        path += "\\imagens\\"; //finalmente fica com projecto\web\pics
        try {
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println("name##### " + name + "\n value##### " + value);
                } else {
                    item.write(new File(path + "nomedapic.jpg"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        if (accao.equals(INSERE_ARTIGO)) {
            UploadFicheiro(request);
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
}
