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
  public class Temas {
    int id;
    String Fecha;
    int idUsuario;
    String Consulta;
    String Tema;
    int nvisitas;

    public Temas() {
        this.id = 0;
        this.Fecha = "";
        this.idUsuario = 0;
        this.Consulta = "";
        this.Tema = "";
        this.nvisitas = 0;
    }

 

    
    
    public String getConsulta() {
        return Consulta;
    }

    public String getFecha() {
        return Fecha;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getNvisitas() {
        return nvisitas;
    }

    public String getTema() {
        return Tema;
    }

    public void setConsulta(String Consulta) {
        this.Consulta = Consulta;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNvisitas(int nvisitas) {
        this.nvisitas = nvisitas;
    }

    public void setTema(String Tema) {
        this.Tema = Tema;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
           
    
}