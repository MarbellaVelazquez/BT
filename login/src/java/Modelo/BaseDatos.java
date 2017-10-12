package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.*;
/**
 *
 * @author Andrés
 */
public class BaseDatos {
    Connection con = null;
    Statement sta = null;
    ResultSet r = null;
    
    public String Conectar() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        String mensaje;
        try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/usuarios", "root", "n0m3l0");
                sta = con.createStatement();
                mensaje = "Conexion exitosa";
            }catch (SQLException error){
                mensaje = error.toString();
            }
        return mensaje;
    }
    
    public Boolean ValidarUsuario(String Nombre, String contra) throws SQLException
    {
        Boolean VerExist = false;
        r = sta.executeQuery("SELECT * FROM usuario;");
        
        while(r.next())
        {
            if(Nombre.equals(r.getString("usuario")) && contra.equals(r.getString("password")))
            {
                VerExist = true;
            }
        }
        
        return VerExist;
    }
}
