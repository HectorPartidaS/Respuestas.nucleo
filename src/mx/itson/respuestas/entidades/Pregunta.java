/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.respuestas.entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import mx.itson.respuestas.enumeradores.Categoria;
import mx.itson.respuestas.persistencia.Conexion;

/**
 *Representa las
 * @author lgym5
 */
public class Pregunta {
    
    private int id;
    private String titulo;
    private String descripcion;
    private Usuario usuario;
    private Date fecha;
    private Categoria categoria;
    private List<Respuesta> respuesta;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the respuesta
     */
    public List<Respuesta> getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(List<Respuesta> respuesta) {
        this.respuesta = respuesta;
    }
    public List<Pregunta> obtenerTodos(){
        List<Pregunta> preguntas = new ArrayList<>();
        try{
            Connection conx = Conexion.getConnection();
                    Statement st = conx.createStatement();
                    ResultSet rs = st.executeQuery("select id,titulo from pregunta");
                    while(rs.next()){
                        Pregunta p = new Pregunta();
                        p.setId(rs.getInt(1));
                        p.setTitulo(rs.getString(2));
                        
                        preguntas.add(p);
                    }
                    conx.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return preguntas;
                
    }
    public void LlenarTabla(String[] dato){
        Connection conx = Conexion.getConnection();
            String sql = "select id,titulo from pregunta";
            Statement st;     
        try {
            st=conx.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while(result.next()){
                dato[0] = result.getString(1);
                dato[1] = result.getString(2);
                
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo hermano");
        }
    }
}
