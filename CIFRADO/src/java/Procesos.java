/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodri
 */
@WebServlet(urlPatterns = {"/Procesos"})
public class Procesos extends HttpServlet {

    //scanner para leer los textos introducidos por el usuario
    Scanner sc = new Scanner(System.in);
    //objeto para convertir
    Conversor conversor = new Conversor();
    //objeto donde estan los metodos de cifrado
    Cifrado Cifrar = new Cifrado();
    //arreglo para el mensaje a cifrar
    String [] mensajeC = new String[64];
    //tabla de permutacion
    int [][] Permutacion1 = {{57, 49, 41, 33, 25, 17, 9, 1},{58, 50, 42, 34, 26, 18, 10, 2},{59, 51, 43, 35, 27, 19, 11, 3,},
                                {60, 52, 44, 36, 63, 55, 47, 39},{31, 23, 15, 7, 62, 54, 46, 38},{30, 22, 14, 6, 61, 53, 45, 37}
                                ,{29, 21, 13, 5, 28, 20, 12, 4}};
    //tabla de permutacion de las llaves
    int [][] llaves = {{14,17,11,24,1,5,3,28},{15,6,21,10,23,19,12,4},{26,8,16,7,27,20,13,2},{41,52,31,37,47,55,30,40},
                       {51, 45, 33, 48, 44, 49, 39, 56}, {34, 53, 46, 42, 50, 36, 29, 32}};
    //Right y Left que se usaran en el mensaje cifrado
    String [] Rx = new String [32];
    String [] Lx = new String [32];
    //Bloque CD inicial
    String [] C0D0 = new String[56];
    //Llaves
    String [] k1 = new String [48];
    String [] k2 = new String [48];
    String [] k3 = new String [48];
    String [] k4 = new String [48];
    String [] k5 = new String [48];
    String [] k6 = new String [48];
    String [] k7 = new String [48];
    String [] k8 = new String [48];
    String [] k9 = new String [48];
    String [] k10 = new String [48];
    String [] k11 = new String [48];
    String [] k12 = new String [48];
    String [] k13 = new String [48];
    String [] k14 = new String [48];
    String [] k15 = new String [48];
    String [] k16 = new String [48];
    //constructor vacio
    public Procesos(){
         
    }
    
     //Metodo que sacara las los bloques C y D
     public String Bloques(String [] clave, String mensaje){
         //Arreglo donde ira cada bloque
        String [] CD1 = new String [56];
        String [] CD2 = new String [56];
        String [] CD3 = new String [56];
        String [] CD4 = new String [56];
        String [] CD5 = new String [56];
        String [] CD6 = new String [56];
        String [] CD7 = new String [56];
        String [] CD8 = new String [56];
        String [] CD9 = new String [56];
        String [] CD10 = new String [56];
        String [] CD11 = new String [56];
        String [] CD12 = new String [56];
        String [] CD13 = new String [56];
        String [] CD14 = new String [56];
        String [] CD15 = new String [56];
        String [] CD16 = new String [56];
        //contador para dar espaciado
        int contador = 0;
        //imprimira C0 y D0 y asi las siguientes
        System.out.print("C0 ");
        //for donde imprime el valor de C0 y D0
         for (int i = 0; i < clave.length; i++) {
             System.out.print(clave[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D1");
        //metodo que recorre una posicion para el siguiente bloque, tambien le asigna al arreglo su valor correspondiente
        CD1 = mover1pos(clave);
        System.out.print("C1 ");
         for (int i = 0; i < CD1.length; i++) {
             //imprime como en el anterior y en los siguientes
             System.out.print(CD1[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D1");
        
        CD2 = mover1pos(CD1);
        System.out.print("C2 ");
         for (int i = 0; i < CD2.length; i++) {
             System.out.print(CD2[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D2");
        //metodo que recorre dos posiciones para el siguiente bloque
        CD3 = mover2pos(CD2);
        System.out.print("C3 ");
         for (int i = 0; i < CD3.length; i++) {
             System.out.print(CD3[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D3");
        
        CD4 = mover2pos(CD3);
        System.out.print("C4 ");
         for (int i = 0; i < CD4.length; i++) {
             System.out.print(CD4[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D4");
        
        CD5 = mover2pos(CD4);
        System.out.print("C5 ");
         for (int i = 0; i < CD5.length; i++) {
             System.out.print(CD5[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D5");
        
        
        CD6 = mover2pos(CD5);
        System.out.print("C6 ");
         for (int i = 0; i < CD6.length; i++) {
             System.out.print(CD6[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D6");
        
        CD7 = mover2pos(CD6);
        System.out.print("C7 ");
         for (int i = 0; i < CD7.length; i++) {
             System.out.print(CD7[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D7");
        
        CD8 = mover2pos(CD7);
        System.out.print("C8 ");
         for (int i = 0; i < CD8.length; i++) {
             System.out.print(CD8[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D8");
        
        CD9 = mover1pos(CD8);
        System.out.print("C9 ");
         for (int i = 0; i < CD9.length; i++) {
             System.out.print(CD9[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D9");
        CD10 = mover2pos(CD9);
        System.out.print("C10 ");
         for (int i = 0; i < CD10.length; i++) {
             System.out.print(CD10[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D10");
        
        CD11 = mover2pos(CD10);
        System.out.print("C11 ");
         for (int i = 0; i < CD11.length; i++) {
             System.out.print(CD11[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D11");
        
        CD12 = mover2pos(CD11);
        System.out.print("C12 ");
         for (int i = 0; i < CD12.length; i++) {
             System.out.print(CD12[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D12");
        
        CD13 = mover2pos(CD12);
        System.out.print("C13 ");
         for (int i = 0; i < CD13.length; i++) {
             System.out.print(CD13[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D13");
        
        CD14 = mover2pos(CD13);
        System.out.print("C14 ");
         for (int i = 0; i < CD14.length; i++) {
             System.out.print(CD14[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D14");
        
        CD15 = mover2pos(CD14);
        System.out.print("C14 ");
         for (int i = 0; i < CD14.length; i++) {
             System.out.print(CD14[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D14");
        
        CD16 = mover1pos(CD15);
        System.out.print("C16 ");
         for (int i = 0; i < CD16.length; i++) {
             System.out.print(CD16[i]);
             contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
         }   
        System.out.println(" D16");
        //Se terminan de imprimir los bloques, cada arreglo del bloque tiene ya un valor
        System.out.println("");
        //Se empiezan a imprimir las llaves
        System.out.println("Llaves :");
        System.out.println("");
        System.out.print("k1 ");
        //se le asigna al arreglo global su valor correspondiente permutando en el metodo keys su bloque
        this.k1 = keys(CD1);
        System.out.println("");
        System.out.print("k2 ");
        this.k2 = keys(CD2);
        System.out.println("");
        System.out.print("k3 ");
        this.k3 = keys(CD3);
        System.out.println("");
        System.out.print("k4 ");
        this.k4 = keys(CD4);
        System.out.println("");
        System.out.print("k5 ");
        this.k5 = keys(CD5);
        System.out.println("");
        System.out.print("k6 ");
        this.k6 = keys(CD6);
        System.out.println("");
        System.out.print("k7 ");
        this.k7 = keys(CD7);
        System.out.println("");
        System.out.print("k8 ");
        this.k8 = keys(CD8);
        System.out.println("");
        System.out.print("k9 ");
        this.k9 = keys(CD9);
        System.out.println("");
        System.out.print("k10 ");
        this.k10 = keys(CD10);
        System.out.println("");
        System.out.print("k11 ");
        this.k11 = keys(CD11);
        System.out.println("");
        System.out.print("k12 ");
        this.k12 = keys(CD12);
        System.out.println("");
        System.out.print("k13 ");
        this.k13 = keys(CD13);
        System.out.println("");
        System.out.print("k14 ");
        this.k14 = keys(CD14);
        System.out.println("");
        System.out.print("k15 ");
        this.k15 = keys(CD15);
        System.out.println("");
        System.out.print("k16 ");
        this.k16 = keys(CD16);
        System.out.println("");
        System.out.println("");
        //se pide el mensaje a cifrar
        System.out.println("Introduzca el mensaje que quiere cifrar: ");
        //se lee
        //se convierte a binario
        System.out.print("Conversion del mensaje a binario: ");
        mensajeC = conversor.conversorHexaBin(mensaje);
        //iniocia el proceso de cifrado
        return ProcedimientoCifrado();
     }
      //metodo para la permutacion 1
     public String Permutacion1(String [] clave, String mensaje){
         //contador para la posicion que se guardara
         int cont = 0;
         //posiciones de la caja de permutacion se recorren mediante 2 fors, ya que es un arreglo de 2 dimensiones
         for (int i = 0; i < 7; i++) {
             for (int j = 0; j < 8; j++) {
                 //se le asigna valor al array del bloque CD0
                 this.C0D0[cont] = "" + (clave[Permutacion1[i][j] - 1]);
                 cont++;
             }
         }
         //Inicia el metodo que sacar las llaves
         return Bloques(this.C0D0, mensaje);
     }
     
     //metodo para sacar las llaves
     public String[] keys(String [] cd){
         //contador para la posicion que se guardara
        int cont = 0;
        //contador para el espaciado
        int contador = 0;
        //arreglo que regresara, sera el valor del arreglo k que lo pida
        String [] k = new String[48];
        //doble for para recorrer la tabla de permutaciones para k
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                //se le asigna valor a la k, su posicion es el contador
                k[cont] = cd[llaves[i][j] - 1];
                //se imprime la posicion de k
                System.out.print(k[cont]);
                cont++;
                contador++;
                //deja espacio si ya van 4 posiciones
                if(contador == 4){
                    System.out.print(" ");
                    contador = 0;
                }
            }             
        }
        //regresa la k
        return k;
    }
     //metodo paa recorrer una posicion
     public String [] mover1pos(String []clave){
        //Se divide en 2 partes ell arreglo, ya que se recorre a la mitad
        String[] parte1 = new String[28];
        String[] parte2 = new String[28];
        //Arreglo donde se guardara la cadena en conjunto
        String[] ArregloRecorrido = new String[56];
        //se divide en 2
        for(int i = 0; i <= 27; i++) {
            parte1[i] = "" + clave[i];
        }   
        for(int i = 0; i <= 27; i++) {
            parte2[i] = clave[28 + i];
        }
        //Este string guarda el primer digito que se recorrera hasta el final
        String Mitad1 = parte1[0];
        String Mitad2 = parte2[0];
        //Se recorre un digito
        for(int i = 0; i < 27; i++) {
            parte1[i] = parte1[i+1];
            parte2[i] = parte2[i+1];
        }
        //se asigna el digito final
        parte1[27] = Mitad1;
        parte2[27] = Mitad2;
        //se juntan los arreglos
        for (int i = 0; i <= 27; i++) {
            ArregloRecorrido[i] = parte1[i];
            ArregloRecorrido[i + 28] = parte2[i]; 
        }
        return ArregloRecorrido;
    }
    //metodo paa recorrer dos posiciones           
    public String [] mover2pos(String []clave){
        //Basicamente lo mismo que con recorrer una posicion, excepto que se crean 2 strings mas porque ahora hay que guardar 2 numeros
        String[] parte1 = new String[28];
        String[] parte2 = new String[28];
        String[] ArregloRecorrido = new String[56];
        for(int i = 0; i <= 27; i++) {
            parte1[i] = "" + clave[i];
        }
        for(int i = 0; i <= 27; i++) {
            parte2[i] = clave[28 + i];
        }
        String Mitad11 = parte1[0];
        String Mitad12 = parte1[1];
        String Mitad21 = parte2[0];
        String Mitad22 = parte2[1];       
        for(int i = 0; i < 26; i++) {
            parte1[i] = parte1[i+2];
            parte2[i] = parte2[i+2];   
        }
        parte1[26] = Mitad11;
        parte1[27] = Mitad12;
        parte2[26] = Mitad21;
        parte2[27] = Mitad22;
        for (int i = 0; i <= 27; i++) {
            ArregloRecorrido[i] = parte1[i];
            ArregloRecorrido[i + 28] = parte2[i];  
        }
        return ArregloRecorrido;
    }
    //Procedimiento para el cifrado
    public String ProcedimientoCifrado(){
        int contador = 0;
        //arreglo sustituto de L para guardar el valor anterior de R y asignarselo a la nueva L
        String [] Lsust = new String [32];
        //Permutacion inicial del mensaje
        this.mensajeC = Cifrar.PermutacionI(mensajeC);
        //Se le asignan los valores iniciales a R y L
        for (int i = 0; i < 32; i++) {
            Rx[i] = mensajeC[32+i];
            Lx[i] = mensajeC[i];
        }
        //Se imprimen R0 y L0
        System.out.print("L0  ");
        for (int i = 0; i < 64; i++) {
            System.out.print(mensajeC[i]);
            contador++;
            if(contador == 4){
                System.out.print(" ");
                contador = 0;
            }
        }
        System.out.print("R0");
        System.out.println("");
        //Se guarda el valor de la R anterior ya que este sera el valor de la nueva L
        Lsust = Rx;
        System.out.print("L1  ");
        //Se saca la nueva R
        Rx = Cifrar.Procedimiento(k1, Lx, Rx);
        //L adquiere el valor de la vieja R
        Lx = Lsust;
        System.out.print("R1");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L2  ");
        Rx = Cifrar.Procedimiento(k2, Lx, Rx);
        Lx = Lsust;
        System.out.print("R2");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L3  ");
        Rx = Cifrar.Procedimiento(k3, Lx, Rx);
        Lx = Lsust;
        System.out.print("R3");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L4  ");
        Rx = Cifrar.Procedimiento(k4, Lx, Rx);
        Lx = Lsust;
        System.out.print("R4");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L5  ");
        Rx = Cifrar.Procedimiento(k5, Lx, Rx);
        Lx = Lsust;
        System.out.print("R5");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L6  ");
        Rx = Cifrar.Procedimiento(k6, Lx, Rx);
        Lx = Lsust;
        System.out.print("R6");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L7  ");
        Rx = Cifrar.Procedimiento(k7, Lx, Rx);
        Lx = Lsust;
        System.out.print("R7");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L8  ");
        Rx = Cifrar.Procedimiento(k8, Lx, Rx);
        Lx = Lsust;
        System.out.print("R8");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L9  ");
        Rx = Cifrar.Procedimiento(k9, Lx, Rx);
        Lx = Lsust;
        System.out.print("R9");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L10  ");
        Rx = Cifrar.Procedimiento(k10, Lx, Rx);
        Lx = Lsust;
        System.out.print("R10");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L11  ");
        Rx = Cifrar.Procedimiento(k11, Lx, Rx);
        Lx = Lsust;
        System.out.print("R11");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L12  ");
        Rx = Cifrar.Procedimiento(k12, Lx, Rx);
        Lx = Lsust;
        System.out.print("R12");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L13  ");
        Rx = Cifrar.Procedimiento(k13, Lx, Rx);
        Lx = Lsust;
        System.out.print("R13");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L14  ");
        Rx = Cifrar.Procedimiento(k14, Lx, Rx);
        Lx = Lsust;
        System.out.print("R14");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L15  ");
        Rx = Cifrar.Procedimiento(k15, Lx, Rx);
        Lx = Lsust;
        System.out.print("R15");
        System.out.println("");
        
        Lsust = Rx;
        System.out.print("L16  ");
        Rx = Cifrar.Procedimiento(k16, Lx, Rx);
        Lx = Lsust;
        System.out.print("R16");
        System.out.println("");
        //Se invierten L y R para la permutacion final
        for (int i = 0; i < 32; i++) {
            mensajeC[32+i] = Lx[i];
            mensajeC[i] = Rx[i];
        }
        //Se realiza la permutacion final
        mensajeC = Cifrar.PermutacionF(mensajeC);
        System.out.println("");
        System.out.println("Mensaje Cifrado: ");
        System.out.println("");
        //Convierte el mensaje de binario a hexadecimal
        return ConCifrado(mensajeC);
    }
    //Conversor de Binario a Hexadecimal
    public String ConCifrado(String[] cadena){
        String tmp ="";
        //array para los numeros decimales que se retornaran
        String [] numerosDecimales = new String[16];
        //Se realiza de 4 en 4, para esto ese este arreglo
        String [] numeros = new String[4];
        //array con las letras validas
        char [] letra = {'A', 'B', 'C', 'D', 'E', 'F'};
        //for que se encargara de sacar los 16 digitos del mensaje ya cifrado
        for (int i = 0; i < 16; i++) {
            //se le asigna valor a numeros 
            for (int j = 0; j < 4; j++) {
                numeros[j] = "" + cadena[j + (4*i)];
            }
            //cad se usara para invertir posiciones y simplifique el proceso de elevar exponencialmente
            String [] cad = new String[numeros.length];
            int total = 0;
            for (int j = 0; j < cad.length; j++) {
                cad[j] = numeros[3-j];
            }
            //se saca el valor del numero
            for (int j = 0; j < cad.length; j++) {
                int num = 0;
                //mediante potencias se saca el numero
                num = Integer.parseInt(cad[j]);
                total += num * Math.pow(2, j);
            }
            //se le da un valor al arreglo que llevara el numero ya convertido
            numerosDecimales[i] = total + "";
        }
        //for para juntar los numeros y convertirlos si son letras
        for (int i = 0; i < numerosDecimales.length; i++) {
            if (Integer.parseInt(numerosDecimales[i]) >= 10) {
                switch (Integer.parseInt(numerosDecimales[i])){
                    case 10:
                        numerosDecimales[i] = "" + letra[0];
                        break;
                    case 11:
                        numerosDecimales[i] = "" + letra[1];
                        break; 
                    case 12:
                        numerosDecimales[i] = "" + letra[2];
                        break;  
                    case 13:
                        numerosDecimales[i] = "" + letra[3];
                        break;    
                    case 14:
                        numerosDecimales[i] = "" + letra[4];
                        break; 
                    case 15:
                        numerosDecimales[i] = "" + letra[5];
                        break; 
                    default:
                        break;
                } 
            }
            //se imprimen los digitos del cifrado
            System.out.print(numerosDecimales[i]);
            tmp+=numerosDecimales[i];
        }
        System.out.println("");
        return tmp;
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
            out.println("<title>Servlet Procesos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Procesos at " + request.getContextPath() + "</h1>");
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
