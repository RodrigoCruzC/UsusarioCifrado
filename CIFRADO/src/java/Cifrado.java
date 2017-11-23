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
@WebServlet(urlPatterns = {"/Cifrado"})
public class Cifrado extends HttpServlet {
    
    //Array para la L con la que se hara XoR
    public String [] Lx = new String[32];
    //Array para la R que se obtiene tras el proceso
    public String [] Rx = new String[32];
    //Array de 2 dimensiones que conforma la tabla de permutacion inicial
    public int [][] PermutacionInicial = {{58,50,42,34,26,18,10,2},{60,52,44,36,28,20,12,4},{62,54,46,38,30,22,14,6},{64,56,48,40,32,24,16,8},{57,49,41,33,25,17,9,1},{59,51,43,35,27,19,11,3},{61,53,45,37,29,21,13,5},{63,55,47,39,31,23,15,7}};
    //Array de 2 dimensiones que conforma la tabla de Expansion
    public int [][] Expansion = {{32,1,2,3,4,5,4,5},{6,7,8,9,8,9,10,11},{12,13,12,13,14,15,16,17},{16,17,18,19,20,21,20,21},{22,23,24,25,24,25,26,27},{28,29,28,29,30,31,32,1}};
    //Array de 2 dimensiones que conforma la tabla de Permutacion P
    public int [][] PermutacionP = {{16,7,20,21,29,12,28,17},{1,15,23,26,5,18,31,10},{2,8,24,14,32,27,3,9},{19,13,30,6,22,11,4,25}};
    //Array de 3 dimensiones que conforma la tabla de cajas S
    public int [][][] CajasS = {{{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},
                                {{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}},
                                {{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},
                                {{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},
                                {{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},
                                {{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},
                                {{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},
                                {{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}}};
    //Array de 2 dimensiones que conforma la tabla de permutacion final
    public int [][] PermutacionFinal = {{40,8,48,16,56,24,64,32},{39,7,47,15,55,23,63,31},{38,6,46,14,54,22,62,30},{37,5,45,13,53,21,61,29},{36,4,44,12,52,20,60,28},{35,3,43,11,51,19,59,27},{34,2,42,10,50,18,58,26},{33,1,41,9,49,17,57,25}};
    //constructor vacio
    public Cifrado(){
        
    }
    //metodo del procedimiento para sacar el siguiente L  y R, recibe una k y un L para los xors que se haran con R
    public String [] Procedimiento(String [] key, String [] Left, String [] Right){
        //contador para los espacios
        int contador = 0;
        //Array para la R a regresar
        String [] R = new String[32];
        this.Lx = Left;
        //invoca al metodo para la expancion de R
        Expansion(Right, key);
        //Hace la permutacion post-cajas s
        PermutacionP(this.Rx);
        //Se le asigna valor a la nueva Rx que es lo que valdra R
        this.Rx = XoR(this.Rx, this.Lx);
        //for para imprimir la R
        for (int i = 0; i < 32; i++) {
            System.out.print(Right[i]);
            contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
        }
        contador = 0;
        //for para darle valores a R y retornarla
        for (int i = 0; i < 32; i++) {
            System.out.print(this.Rx[i]);
            R[i] = this.Rx[i];
            contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
        }
        
        return R;
    }
    //metodo para la primera permutacion del cifrado
    public String [] PermutacionI(String [] numeros){
        //array a retornar
        String [] Permutado = new String[64];
        //contador para las posiciones
        int contador = 0;
        //for para la tabla de 2 dimensiones
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //se asignan valores al array a retornar
                Permutado[contador] = numeros[PermutacionInicial[i][j] - 1];
                contador++;
            }
        }
        return Permutado;
    }
    //Metodo para la expansion de R, recibe R y K ya que de aqui hara el Xor entre esos 2
    public void Expansion(String [] numeros, String [] llave){
        //array de la R inicial del metodo
        String [] R = new String [32];
        //array de la R Expandida a retornar
        String [] RExp = new String [48];
        int contador = 0;
        //se le asigna el valor a R
        R = numeros;
        //fors para la tabla de expansion
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                //Se le dan valores al arreglo a retornar
                RExp[contador] = R[Expansion[i][j] - 1];
                contador++;
            }
        }
        //se realiza el XoR entre K y R
        RExp = XoR(RExp, llave);
        //Manda la R expandida a las cajas s
        Sbox(RExp);
    }
    //Metodo para el Xor
    public String [] XoR(String [] R, String [] KL){
        //array del resultado del XoR
        String [] Resultado = new String[R.length];
        //for para hacer xor posicion por posicion
        for (int i = 0; i < R.length; i++) {
            if(R[i].equals(KL[i])){
                Resultado[i] = "0";
            }else{
                Resultado[i] = "1";
            }
        }
        //se regresa el arreglo
        return Resultado;
    }
    //Recibe una R expandida
    public void Sbox(String [] Resultado){
        //El resultado en numeros decimales, los cuales provienen de los numeros en las cajas S
        String [] resultadoD = new String[8];
        //Resultado ya en binario para permutaciones y XoR
        String [] resultadoB = new String[32];
        //Array para las Bs
        String [] Bx = new String[6];
        //array para M
        String [] M = new String[2];
        //array para N
        String [] N = new String[4];
        //contador para posiciones del array
        int contador = 0;
        //for donde se busca la posicion en las cajas s
        for (int i = 0; i < 8; i++) {
            //for para sacar una B
            for (int j = 0; j < 6; j++) {
                Bx[j] = Resultado[j + (6*i)];            
            }
            //se le dan posiciones de B para M y N
            M[0]=Bx[0];
            M[1]=Bx[5];
            N[0]=Bx[1];
            N[1]=Bx[2];
            N[2]=Bx[3];
            N[3]=Bx[4];
            //se le da un numero decimal proviniente de las cajas s
            resultadoD[i] = "" + (CajasS[i][Integer.parseInt(ConversorBD(M))][Integer.parseInt(ConversorBD(N))]);
        }                      
        //for para transformar el numero decimal de las cjas s en binario
        for (int i = 0; i < resultadoD.length; i++) {
            for (int j = 0; j < ConversorDB(resultadoD[i]).length; j++) {
                //ConversorDB(resultadoD[i]) es el array que proviene del conversor, es mejor que declarar array por numero de caja s
                resultadoB[contador] = ConversorDB(resultadoD[i])[j];
                contador++;        
            } 
        }
        //se regresa en binario el valor
        this.Rx = resultadoB;
    }
    //metodo para la permutacion P
    public void PermutacionP(String [] R){
        //array a regresar
        String [] Permutado = new String[32];
        //contador para posiciones del arreglo
        int contador = 0;
        //fors para recorrer la tabla de permutacion P
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                //se van asignando valores
                Permutado[contador] = R[PermutacionP[i][j] - 1];
                contador++;
            }
        }
        //Se le da valor a la variable global
        this.Rx = Permutado;
    }

    //Metodo para la permutacion final
    public String [] PermutacionF(String [] numeros){
        //array a devolver
        String [] Permutado = new String[64];
        //contador para posiciones
        int contador = 0;
        //fors para recorrer tabla
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //se asignan valores
                Permutado[contador] = numeros[PermutacionFinal[i][j] - 1];
                contador++;
            }
        }
        //se regresa array
        return Permutado;
    }
    //Conversor de binario a decimal
    public String ConversorBD(String [] Binario){
        //totral ira sumando el valor de cada digito elevado a cierta piotencia
        int total = 0;
        //cadena se usara para simplificar el elevar a potencias
        String [] cadena = new String[Binario.length];
        String resultado = "";
        for (int i = 0; i < cadena.length; i++) {
            cadena[i] = Binario[(Binario.length-1) - i];
        }
        //eleva a potencias
        for (int i = 0; i < cadena.length; i++) {
            int numero = 0;
            numero = Integer.parseInt(cadena[i]);
            total += numero * Math.pow(2, i);
        }
        resultado = total + "";
        //regresa resultado
        return resultado;
    }
   
    //Conversor de decimal a a binario
    //parecido al que esta en conversor pero sin la necesidad de considerar letras
    public String [] ConversorDB(String clave){
        String [] binarioA = new String[4];
        String binario = "";
        String secundario = "";
        String sust = "";
        int cont = 0;
        
        int pieza = Integer.parseInt(clave);
        if(pieza == 0){
            secundario = "0000";
            binario += secundario;
            sust = "";
            secundario = "";
        }else{
            while(pieza >= 1){
                secundario += "" + (pieza%2);
                pieza = pieza/2;   
            }
            sust = secundario;
            secundario = "";
            for (int j = 0; j < sust.length(); j++) {
                secundario += "" + sust.charAt(sust.length()-(j+1));
            }
            for (int j = 0; j < secundario.length(); j++) {
                cont++;
            }
            if(cont < 4){
                if (cont == 3) {
                    secundario = 0+ "" + secundario;
                }else if(cont == 2){
                    secundario = 0+""+0+ "" + secundario;
                }else if(cont == 1){
                    secundario = 0+""+0+ "" + 0 + "" +secundario;
                }
            }
            binario += secundario;
        }
        for (int i = 0; i < binario.length(); i++) {
            binarioA[i] = "" + binario.charAt(i);
        }
        return binarioA;
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
            out.println("<title>Servlet Cifrado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cifrado at " + request.getContextPath() + "</h1>");
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
