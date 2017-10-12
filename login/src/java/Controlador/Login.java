/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BaseDatos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrés
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    BaseDatos BD = new BaseDatos();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("usuario");
            String contra = request.getParameter("contra");
            Boolean resultado=false;
            
        try {
            BD.Conectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            resultado = BD.ValidarUsuario(nombre, contra);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println("<html>");
        out.println("<head><title>Respuesta al Formulario</title></head>");
        out.println("<body><CENTER>");    
        
       if(resultado)
            {
                out.println("<H1>Contraseña y Usuario correctos</H1>");
            }
            else
            {
                out.println("<h1>Datos incorrectos</h1>");
                out.println("<script>"
                        + "function back(){"
                        + "window.location = 'http://localhost:8080/login/Login.html'"
                        + "}"
                        + "</script>");
                out.println("<button onclick='back()'>Back</button>");
            }
        out.println("</CENTER></body></html>");
        out.close();
    }

}
