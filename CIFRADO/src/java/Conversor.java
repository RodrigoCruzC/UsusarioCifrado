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
@WebServlet(urlPatterns = {"/Conversor"})
public class Conversor extends HttpServlet {

    String _resultado;
    String _binario;
    //Constructor con valores default para variables globales
    public Conversor(){
        this._resultado = "";
        this._binario = "";
    }
    public String[] conversorHexaBin(String clave){
        //variable que se usara para valores de pisiciones de un array
        int numero = 0;
        //array para reemplazar letras por numeros (se puede expandir a mas letras)
        char [] Letras = {'A','B','C','D','E','F'};
        // ,'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
        //array con lo que vale cada letra en hexadecimal
        String [] valorLetras = {"10","11","12","13","14","15"};
        // "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36"
        //array que se devolvera
        String [] claveRetorno = new String[64];
        //array de valores
        String [] claveBinario = new String[16];
        //Se le asignan valores al array que se devolvera
        for (int i = 0; i < clave.length(); i++) {
            claveBinario[i] = "" + clave.charAt(i);
            for (int j = 0; j < Letras.length; j++) {
                if(clave.charAt(i) == Letras[j]){
                    claveBinario[i] = valorLetras[j];
                }
            }
        }
        for (int i = 0; i < claveBinario.length; i++) {
            this._resultado = "";
            numero = Integer.parseInt(claveBinario[i]);
            while((numero/2)>0){
                //Se le añade al string previamente declarado el residuo de una division
                //(El % te proporciona el residuo de una division)
                this._resultado += "" + (numero%2);
                //Guarda el resultado de la division, para que sea el siguiente
                numero = numero/2;
            }
            //añade los digitos, los residuos, mas el residuo final
            this._resultado += "" + numero;
            if (this._resultado.length() == 1) {
                this._resultado += "" + 0 + "" + 0 + "" +0;
            }else if(this._resultado.length() == 2){
                this._resultado += "" + 0 + "" + 0;
            }else if(this._resultado.length() == 3){
                this._resultado += "" + 0;
            }
            String sust = this._resultado;
            this._resultado = "";

            for (int j = 0; j < sust.length(); j++) {
                this._resultado += "" + sust.charAt(sust.length()-(j+1));
            }
            this._binario += this._resultado;
        }
        for (int i = 0; i < this._binario.length(); i++) {
           claveRetorno[i] = "" + this._binario.charAt(i);
           System.out.print(claveRetorno[i]);
        }
        System.out.println("");
        return claveRetorno; 
    }    
  
    
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Conversor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Conversor at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
