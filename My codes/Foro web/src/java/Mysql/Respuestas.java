/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql;

/**
 *
 * @author ellui
 */
public class Respuestas {
    int idTema;
    int idUsuario;
    String Respuesta;
    int id;

    public Respuestas() {
        this.idTema = 0;
        this.idUsuario = 0;
        this.Respuesta = "";
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public int getIdTema() {
        return idTema;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getRespuesta() {
        return Respuesta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public void setRespuesta(String Respuesta) {
        this.Respuesta = Respuesta;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
}
