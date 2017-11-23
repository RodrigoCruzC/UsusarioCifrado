

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
 * @author Alumno
 */
@WebServlet(urlPatterns = {"/peg"})
public class peg extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String resp1=request.getParameter("p1");
            int corre1 =0;
            if(resp1.equals("radio"))
            {
               corre1=1;
            }
            
            cDatos dat =new cDatos();
            
            dat.conectar();
            dat.actualizar("INSERT INTO preg1 VALUES('"+resp1+"',"+corre1+");");
            dat.cierraConexion();
            
              String resp2=request.getParameter("p2");
            int corre2=0;
            if(resp2.equals("radio5"))
            {
               corre2=1;
            }
            
            cDatos dat2 =new cDatos();
            
            dat2.conectar();
            dat2.actualizar("INSERT INTO preg2 VALUES('"+resp2+"',"+corre2+");");
            dat2.cierraConexion();
            
              String resp3=request.getParameter("p3");
            int corre3 =0;
            if(resp3.equals("radio9"))
            {
               corre3=1;
            }
            
            cDatos dat3 =new cDatos();
            
            dat3.conectar();
            dat3.actualizar("INSERT INTO preg3 VALUES('"+resp3+"',"+corre3+");");
            dat3.cierraConexion();
            
               String resp4=request.getParameter("p4");
            int corre4 =0;
            if(resp4.equals("radio11"))
            {
               corre4=1;
            }
            
            cDatos dat4 =new cDatos();
            
            dat4.conectar();
            dat4.actualizar("INSERT INTO preg4 VALUES('"+resp4+"',"+corre4+");");
            dat4.cierraConexion();
            
               String resp5=request.getParameter("p5");
            int corre5 =0;
            if(resp5.equals("radio16"))
            {
               corre5=1;
            }
            
            cDatos dat5 =new cDatos();
            
            dat5.conectar();
            dat5.actualizar("INSERT INTO preg5 VALUES('"+resp5+"',"+corre5+");");
            dat5.cierraConexion();
            
               String resp6=request.getParameter("p6");
            int corre6 =0;
            if(resp6.equals("radio18"))
            {
               corre6=1;
            }
            
            cDatos dat6 =new cDatos();
            
            dat6.conectar();
            dat6.actualizar("INSERT INTO preg6 VALUES('"+resp6+"',"+corre6+");");
            dat6.cierraConexion();
            
                     String resp7=request.getParameter("p7");
            int corre7 =0;
            if(resp7.equals("radio21"))
            {
               corre7=1;
            }
            
            cDatos dat7 =new cDatos();
            
            dat7.conectar();
            dat7.actualizar("INSERT INTO preg7 VALUES('"+resp7+"',"+corre7+");");
            dat7.cierraConexion();
            
            String resp8=request.getParameter("p8");
            int corre8 =0;
            if(resp8.equals("radio25"))
            {
               corre8=1;
            }
            
            cDatos dat8 =new cDatos();
            
            dat8.conectar();
            dat8.actualizar("INSERT INTO preg8 VALUES('"+resp8+"',"+corre8+");");
            dat8.cierraConexion();
            
              String resp9=request.getParameter("p9");
            int corre9 =0;
            if(resp9.equals("radio26"))
            {
               corre9=1;
            }
            
            cDatos dat9 =new cDatos();
            
            dat9.conectar();
            dat9.actualizar("INSERT INTO preg9 VALUES('"+resp9+"',"+corre9+");");
            dat9.cierraConexion();
            
            String resp10=request.getParameter("p10");
            int corre10 =0;
            if(resp10.equals("radio30"))
            {
               corre10=1;
            }
            
            cDatos dat10 =new cDatos();
            
            dat10.conectar();
            dat10.actualizar("INSERT INTO preg10 VALUES('"+resp10+"',"+corre10+");");
            dat10.cierraConexion();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet peg</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Cuestionario lleno </h1>");       
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
