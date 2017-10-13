/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import OraclePackage.oracleConection;
import java.sql.Blob;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author ellui
 */
public class Trabajador extends HttpServlet{
   public String usuario;
    public  String nombre;
    public  String appat;
   public   String apmat;
   public   String Calle;
   public   String numero;
   public   String colonia;
     public int telefono;
    public  String antiguedad;
    public  int salario;
    public  String pass;
    public  String ciudad;
    public  String estado;
     public int cp;
     Blob foto;

    

    public Trabajador() {
        
    }

    public Trabajador(String usuario, String nombre, String appat, String apmat, String Calle, String numero, String colonia, int telefono, String antiguedad, int salario, String pass, String ciudad, String estado, int cp) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.appat = appat;
        this.apmat = apmat;
        this.Calle = Calle;
        this.numero = numero;
        this.colonia = colonia;
        this.telefono = telefono;
        this.antiguedad = antiguedad;
        this.salario = salario;
        this.pass = pass;
        this.ciudad = ciudad;
        this.estado = estado;
        this.cp = cp;
        
    }

    
    public void alta_trabajador(){
    
    }
    
    public void baja_trabajador(){
    
    }
    
    public void modificar_usuario(){
    
    }
    
   public int Consultar_trabajador(String usr){
    oracleConection con = new oracleConection();
    String consulta="";
                    consulta ="select * from Trabajador where Usuario='"+usr+"'";

           //ejectamos consulta
           con.Consult(consulta);
           System.out.println(consulta);
           //n indicara cuantos registros cumplen la condicion
           int n=0;
           if(con.rs!=null){
                try{
               con.rs.last();
               //privilegios=objConn.rs.getString("priv");
               //id=objConn.rs.getString("id");
                n=con.rs.getRow();
                if(n!=0){//Es trabajador
                 this.usuario = con.rs.getString("Usuario");
                  this.pass = con.rs.getString("psw");
                  this.nombre =con.rs.getString(3);
                 this.telefono = Integer.parseInt(con.rs.getString(4));
              this.antiguedad = con.rs.getString(5);
                            this.salario =Integer.parseInt(con.rs.getString(6));
                             this.appat = con.rs.getString(7);
                                        this.apmat = con.rs.getString(8); 
                                    this.Calle = con.rs.getString(9);
                                      this.ciudad = con.rs.getString(10);
                                          this.estado = con.rs.getString(11);
       
                     this.numero = con.rs.getString(12);
                               this.cp =Integer.parseInt(con.rs.getString(13));
                  this.colonia = con.rs.getString(14);
         
             
                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return n;
    }
    
    
    public int Consultar_trabajador(String usr,String psw,String Tuser){
    oracleConection con = new oracleConection();
    String consulta="";
                    consulta ="select * from Trabajador where Usuario='"+usr+"' and psw='"
                   +psw+ "'";

           //ejectamos consulta
           con.Consult(consulta);
           System.out.println(consulta);
           //n indicara cuantos registros cumplen la condicion
           int n=0;
           if(con.rs!=null){
                try{
               con.rs.last();
               //privilegios=objConn.rs.getString("priv");
               //id=objConn.rs.getString("id");
                n=con.rs.getRow();
                if(n!=0){//Es trabajador
                 this.usuario = con.rs.getString(1);
                  this.pass = con.rs.getString(2);
                  this.nombre =con.rs.getString(3);
                 this.telefono = Integer.parseInt(con.rs.getString(4));
              this.antiguedad = con.rs.getString(5);
                            this.salario =Integer.parseInt(con.rs.getString(6));
                             this.appat = con.rs.getString(7);
                                        this.apmat = con.rs.getString(8); 
                                    this.Calle = con.rs.getString(9);
                                      this.ciudad = con.rs.getString(10);
                                          this.estado = con.rs.getString(11);
       
                     this.numero = con.rs.getString(12);
                               this.cp =Integer.parseInt(con.rs.getString(13));
                  this.colonia = con.rs.getString(14);
         
             
           


  
               
              
            

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return n;
    }
    
    
    
    
    
    
}
