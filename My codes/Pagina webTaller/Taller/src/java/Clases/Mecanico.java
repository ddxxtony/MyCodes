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
public class Mecanico extends Trabajador {

    public String specialidad;

    public Mecanico() {
    }
    
    
public boolean Nuevo_mecanico(String usuario, String Psw, String nombre, 
            String appat, String apmat, String calle, String colonia, 
            int numero, String Ciudad, String Estado, int telefono,
            String especialidad, int salario, int cp ){
        oracleConection con = new oracleConection();
           String consulta="";
                    consulta ="call ALTA_MECANICO ('"+usuario+"','"+Psw+"','"+nombre+"',"
                            + "'"+telefono+"',now(),'"+salario+"','"+appat+"','"
                            + ""+apmat+"','"+calle+"','"+Ciudad+"',"+Estado+","+numero+","
                            + ""+cp+",'"+colonia+"','"+especialidad+"')";

           //ejectamos consulta
         System.out.println(consulta);
           //n indicara cuantos registros cumplen la condicion
           int n=  con.Update(consulta);
           if(con.rs!=null){



                 if(n!=0){//Es trabajador
                     return true;
                 }
                 
                 con.closeRsStmt();
             
           
           //desconectamos
           //si existe al menos un registro
          
          
        
    
    }
                           
        return false;
    }
    public Mecanico(String usr,int fg) {
    oracleConection con = new oracleConection();
        String consulta = "";
        consulta = "select * from Mecanico,Trabajador where Trabajador.Usuario='"+usr+"' and Trabajador_Usuario=Usuario";
        //ejectamos consulta
        con.Consult(consulta);
        //n indicara cuantos registros cumplen la condicion
        int n = 0;
        if (con.rs != null) {
            try {
                con.rs.last();
                                 
                //privilegios=objConn.rs.getString("priv");
                //id=objConn.rs.getString("id");
                n = con.rs.getRow();
                con.rs.first();
                int contador = 0;//se encarga de contar la fila en la que se van a guardar los datos
                if (n != 0) {//Es trabajador      
                       this.usuario = con.rs.getString("Usuario");
                        this.pass = con.rs.getString("psw");
                        this.nombre = con.rs.getString("nombre");
                       this.telefono = Integer.parseInt(con.rs.getString("telefono"));
                       this.antiguedad = con.rs.getString("antiguedad");
                        this.salario = Integer.parseInt(con.rs.getString("salario"));
                        this.appat = con.rs.getString("appat");
                        this.apmat = con.rs.getString("apmat");
                       this.Calle = con.rs.getString("calle");
                        this.ciudad = con.rs.getString("Ciudad");
                       this.estado = con.rs.getString("Estado");
                       this.numero = con.rs.getString("numero");
                      this.cp = Integer.parseInt(con.rs.getString("codigo_postal"));
                       this.colonia = con.rs.getString("Colonia");
                       this.foto = (Blob)con.rs.getBlob("foto");
                         this.specialidad = con.rs.getString("Especialidad");
                     


                }
            } catch (Exception e) {
                                   
            }
            con.closeRsStmt();
        }

        //desconectamos
        //si existe al menos un registro


    }

    public Mecanico(String specialidad) {
        this.specialidad = specialidad;
    }

    public Mecanico(String specialidad, String usuario, String nombre, String appat, String apmat, String Calle, String numero, String colonia, int telefono, String antiguedad, int salario, String pass, String ciudad, String estado, int cp) {
        super(usuario, nombre, appat, apmat, Calle, numero, colonia, telefono, antiguedad, salario, pass, ciudad, estado, cp);
        this.specialidad = specialidad;
    }

    public static Mecanico[] Consultar_Mecanicos() {
        oracleConection con = new oracleConection();
        Mecanico[] mecanicos = null;// =new Servicio[Nregistros] ;
        String consulta = "";
        consulta = "select * from Mecanico,Trabajador where Trabajador_Usuario=Usuario";

        //ejectamos consulta
        con.Consult(consulta);
        //n indicara cuantos registros cumplen la condicion
        int n = 0;
        if (con.rs != null) {
            try {
                con.rs.last();
                //privilegios=objConn.rs.getString("priv");
                //id=objConn.rs.getString("id");
                n = con.rs.getRow();
                con.rs.first();
                int contador = 0;//se encarga de contar la fila en la que se van a guardar los datos
                if (n != 0) {//Es trabajador
                    mecanicos = new Mecanico[n];
                       
                    do {
                        mecanicos[contador] = new Mecanico();
                        mecanicos[contador].usuario = con.rs.getString("Usuario");
                        mecanicos[contador].pass = con.rs.getString("psw");
                        mecanicos[contador].nombre = con.rs.getString("nombre");
                        mecanicos[contador].telefono = Integer.parseInt(con.rs.getString("telefono"));
                        mecanicos[contador].antiguedad = con.rs.getString("antiguedad");
                        mecanicos[contador].salario = Integer.parseInt(con.rs.getString("salario"));
                        mecanicos[contador].appat = con.rs.getString("appat");
                        mecanicos[contador].apmat = con.rs.getString("apmat");
                        mecanicos[contador].Calle = con.rs.getString("calle");
                        mecanicos[contador].ciudad = con.rs.getString("Ciudad");
                        mecanicos[contador].estado = con.rs.getString("Estado");
                        mecanicos[contador].numero = con.rs.getString("numero");
                        mecanicos[contador].cp = Integer.parseInt(con.rs.getString("codigo_postal"));
                        mecanicos[contador].colonia = con.rs.getString("Colonia");
                        mecanicos[contador].foto = (Blob)con.rs.getBlob("foto");
                         mecanicos[contador].colonia = con.rs.getString("Especialidad");
                        contador++;
                                
                    } while (con.rs.next());

                }
            } catch (Exception e) {
                                   
            }
            con.closeRsStmt();
        }

        //desconectamos
        //si existe al menos un registro
        return mecanicos;

    }

    @Override
    public int Consultar_trabajador(String usr, String psw, String Tuser) {
        int n = 0;

        if (super.Consultar_trabajador(usr, psw, Tuser) > 0) {

            oracleConection con = new oracleConection();
            String consulta = "";
            consulta = "select * from Mecanico where Trabajador_Usuario='" + usr + "'";
            //ejectamos consulta
            con.Consult(consulta);
            //n indicara cuantos registros cumplen la condicion

            if (con.rs != null) {
                try {
                    con.rs.last();
                    //privilegios=objConn.rs.getString("priv");
                    //id=objConn.rs.getString("id");
                    n = con.rs.getRow();
                    if (n != 0) {//Es trabajador
                        this.specialidad = con.rs.getString(1);
                    }
                } catch (Exception e) {
                }
                con.closeRsStmt();
            }

            //desconectamos
            //si existe al menos un registro
        }
        return n;
    }

    @Override
    public void alta_trabajador() {
        super.alta_trabajador(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void baja_trabajador() {
        super.baja_trabajador(); //To change body of generated methods, choose Tools | Templates.
    }

}
