/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.respuestas.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author uriel
 */
public class Conexion {
    private static Connection conexion;
    private static String stringConexion = "jdbc:mysql://localhost:3306/respuestasdb?useLegacyDatetimeCode=false&serverTimezone=UTC";    
    private static String driver = "com.mysql.cj.jdbc.Driver";   
    private static String usuario = "root";   
    private static String password = "chempo12";

    /**
     * Obtiene una conexiÃ³n activa a la base de datos en MySQL con los parÃ¡metros seÃ±alados.
     * @return ConexiÃ³n activa con los atributos necesarios para realizar queries. Si no es posible conectar, retorna null.
     */
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            try {
                conexion = DriverManager.getConnection(stringConexion, usuario, password);
            } catch (SQLException ex) {
                System.out.println("OcurriÃ³ un error al conectar: " + ex.getMessage()); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("OcurriÃ³ un error: " + ex.getMessage()); 
        }
        return conexion;
    }
}
