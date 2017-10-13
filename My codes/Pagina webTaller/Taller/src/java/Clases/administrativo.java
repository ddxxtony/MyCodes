/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import OraclePackage.oracleConection;
import java.sql.Blob;

/**
 *
 * @author ellui
 */
public class administrativo extends Trabajador {
    
    public  String Tipo_administrativo;

    public administrativo() {
        super();
    }
    
    
    public boolean Alta_administrativo(String usuario, String Psw, String nombre, 
            String appat, String apmat, String calle, String colonia, 
            int numero, String Ciudad, String Estado, int telefono,
            int salario,String tipo,String antiguedad,int cp)
        {
        //usuario, nombre, appat, apmat, Calle, numero, colonia, telefono, antiguedad, salario, pass, ciudad, estado, cp;
         oracleConection con = new oracleConection();
           String consulta="";
                    consulta ="call ALTA_ADMINISTRATIVO ('"+usuario+"','"+Psw+"','"+nombre+"',"
                            + "'"+telefono+"','"+antiguedad+"','"+salario+"','"+appat+"','"
                            + ""+apmat+"','"+calle+"','"+Ciudad+"',"+Estado+","+numero+","
                            + ""+cp+"','"+colonia+"','"+tipo+"')";

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
    public administrativo(String usr,int x) {
        oracleConection con = new oracleConection();
        String consulta = "";
        consulta = "select * from Administrativo,Trabajador where Trabajador.Usuario='"+usr+"' and Trabajador_Usuario=Usuario";
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
                         this.Tipo_administrativo = con.rs.getString("tipo_admin");
                     


                }
            } catch (Exception e) {
                                   
            }
            con.closeRsStmt();
        }

        //desconectamos
        //si existe al menos un registro


    }
        
    

    public administrativo(String Tipo_administrativo) {
        this.Tipo_administrativo = Tipo_administrativo;
        
    }

    public administrativo(String Tipo_administrativo, String usuario, String nombre, String appat, String apmat, String Calle, String numero, String colonia, int telefono, String antiguedad, int salario, String pass, String ciudad, String estado, int cp) {
        super(usuario, nombre, appat, apmat, Calle, numero, colonia, telefono, antiguedad, salario, pass, ciudad, estado, cp);
        this.Tipo_administrativo = Tipo_administrativo;
    }
    
   public void Generar_admision(){
   
   }
   
   

   
   public static administrativo [] Consultar_administrativos() {
      oracleConection con = new oracleConection();
        administrativo [] admins = null;// =new Servicio[Nregistros] ;
        String consulta = "";
        consulta = "select * from Administrativo,Trabajador where Trabajador_Usuario=Usuario";

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
                    admins = new administrativo[n];
                                            
                    do {
                        admins[contador] = new administrativo();
                        admins[contador].usuario = con.rs.getString("Usuario");
                        admins[contador].pass = con.rs.getString("psw");
                        admins[contador].nombre = con.rs.getString("nombre");
                       admins[contador].telefono = Integer.parseInt(con.rs.getString("telefono"));
                        admins[contador].antiguedad = con.rs.getString("antiguedad");
                        admins[contador].salario = Integer.parseInt(con.rs.getString("salario"));
                        admins[contador].appat = con.rs.getString("appat");
                        admins[contador].apmat = con.rs.getString("apmat");
                       admins[contador].Calle = con.rs.getString("calle");
                        admins[contador].ciudad = con.rs.getString("Ciudad");
                       admins[contador].estado = con.rs.getString("Estado");
                       admins[contador].numero = con.rs.getString("numero");
                      admins[contador].cp = Integer.parseInt(con.rs.getString("codigo_postal"));
                        admins[contador].colonia = con.rs.getString("Colonia");
                       admins[contador].foto = (Blob)con.rs.getBlob("foto");
                         admins[contador].colonia = con.rs.getString("Especialidad");
                        contador++;
                                                 System.out.println(consulta+ " dentro del ciclo");
                    } while (con.rs.next());

                }
            } catch (Exception e) {
                                   
            }
            con.closeRsStmt();
        }

        //desconectamos
        //si existe al menos un registro
        return admins;

    }
    @Override
    public int Consultar_trabajador(String usr, String psw, String Tuser) {
         int n=0;
        if(super.Consultar_trabajador(usr, psw, Tuser)>0){ //To change body of generated methods, choose Tools | Templates.
        oracleConection con = new oracleConection();
         String consulta="";
                    consulta ="select * from Administrativo where Trabajador_Usuario='"+usr+"'";
           //ejectamos consulta
           con.Consult(consulta);
           //n indicara cuantos registros cumplen la condicion

           if(con.rs!=null){
                try{
               con.rs.last();
               //privilegios=objConn.rs.getString("priv");
               //id=objConn.rs.getString("id");
                n=con.rs.getRow();
                if(n!=0){//Es trabajador

                 this.Tipo_administrativo = con.rs.getString(1);

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
        }
        return n;
    }

   
   
    
    
    
   
}
