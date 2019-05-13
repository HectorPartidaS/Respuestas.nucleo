/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.respuestas.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.itson.respuestas.persistencia.Conexion;

/**
 *  Representa el usuario que realiza las preguntas.
 * @author Ivan Lopez
 */
public class Usuario {
    private int id;
    private String nombre;
    private String cuenta;
    private String contraseña;
    private Date fechaNacimiento;

    /**
     * obtiene el valor de id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * le otorga valor a id
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public List<Usuario> obtenerTodos(){
        List<Usuario> usuarios = new ArrayList<>();
        try{
            Connection conx = Conexion.getConnection();
                    Statement st = conx.createStatement();
                    ResultSet rs = st.executeQuery("select id,nombre,cuenta,fechanacimiento from usuario");
                    while(rs.next()){
                        Usuario u = new Usuario();
                        u.setId(rs.getInt(1));
                        u.setNombre(rs.getString(2));
                        u.setCuenta(rs.getString(3));
                        Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(4));
                        u.setFechaNacimiento(fecha);
                        usuarios.add(u);
                    }
                    conx.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return usuarios;
                
    }
  public boolean guardar(String nombre,String cuenta, String contrasena, Date fechaNacimiento){
  boolean exito = false;
  try{
    Connection conx = Conexion.getConnection();
    String query = "INSERT INTO usuario(nombre,cuenta,contrasena,fechaNacimiento) VALUES (?,?,?,?)";
    PreparedStatement st = conx.prepareStatement(query);
    st.setString(1,nombre);
    st.setString(2,cuenta);
    st.setString(3,contrasena);
    st.setDate(4,new java.sql.Date(fechaNacimiento.getTime()));
    st.execute();
    conx.close();
    exito=true;
  }catch(Exception ex){
      System.out.println(ex);
  }
  return exito;
}

    @Override
    public String toString() {
        return "Usuario{\n" + "id=" + id + ",\n nombre=" + nombre + ",\n cuenta=" + cuenta +  ",\n fechaNacimiento=" + fechaNacimiento + '}';
    }
    
}
