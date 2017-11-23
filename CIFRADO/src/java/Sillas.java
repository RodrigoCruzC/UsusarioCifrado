/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodri
 */
@WebServlet(urlPatterns = {"/Sillas"})
public class Sillas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Des des = new Des();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Sillas</title>");
            String usu= request.getParameter("usur");
            String contra = request.getParameter("con");
            String cifrado = des.cifrar(usu, "AAABBFFCC5598742");
            String contraCifrada = des.cifrar(contra, "AAABBFFCC5598742");
                       
            out.println("El usuario es: "+usu);
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Ususario Cifrado " + cifrado+"</h1>");
            out.println("<h1>Contraseña Cifrada "+contraCifrada + "</h1>");
            
            cDatos dat =new cDatos();            
            dat.conectar();
            dat.actualizar("INSERT INTO usuario VALUES(sha('"+cifrado+"'),MD5('"+contraCifrada+"'),'"+usu+"','"+contra+"');");
            dat.cierraConexion();
            
             out.println("<h1> Registro lleno </h1>");  
            
            out.println("</body>");
            
            out.println("</html>");
            
          
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
