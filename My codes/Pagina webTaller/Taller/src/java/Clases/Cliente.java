/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import OraclePackage.oracleConection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author ellui
 */
public class Cliente  extends HttpServlet{
    public String usuario;
    public String Psw;
    public String nombre;
    public String appat;
    public String apmat;
   public String calle;
   public String colonia;
   public int numero;
   public String Ciudad;
   public String Estado;
   public int telefono;
   public String celular;
   public int ingresos;
   public String email;

    
   
    
    public  Cliente(){
        
    
    }
    public  Cliente(String user){
        oracleConection con = new oracleConection();
           String consulta="";
                    consulta ="select * from Cliente where Usuario='"+user+"'";

            //Cliente [] clientes=null;
           //ejectamos consulta
           con.Consult(consulta);
           //n indicara cuantos registros cumplen la condicion
           int n=0;
           if(con.rs!=null){


             try {
                 con.rs.last();
                 //privilegios=objConn.rs.getString("priv");
                 //id=objConn.rs.getString("id");
                 n=con.rs.getRow();
             //    clientes=new Cliente[n];
                 con.rs.first();
                 if(n!=0){//Es trabajador
                         //clientes[i]=new Cliente();
                         //System.out.println(consulta);
                         this.usuario =con.rs.getString("Usuario");
                         this.Psw =con.rs.getString("psw");
                         this.nombre =con.rs.getString("nombre");
                         this.appat =con.rs.getString("appat");
                         this.apmat =con.rs.getString("apmat");
                         this.calle = con.rs.getString("calle");
                         this.colonia = con.rs.getString("Colonia");
                         this.numero =Integer.parseInt(con.rs.getString("numero"));
                         this.Ciudad = con.rs.getString("Ciudad");
                         this.Estado = con.rs.getString("Estado");
                         this.telefono = Integer.parseInt(con.rs.getString("telefono"));
                         this.celular = con.rs.getString("celular");
                         this.ingresos = Integer.parseInt(con.rs.getString("ingresos"));
                         this.email =con.rs.getString("email");

                   
                 }
                 
                 con.closeRsStmt();
             } catch (SQLException ex) {
                 Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
             }
           }
           
           //desconectamos

        
    
    }

    public Cliente(String usuario, String Psw, String nombre, String appat, String apmat, String calle, String colonia, int numero, String Ciudad, String Estado, int telefono, String celular, int ingresos, String email) {
        this.usuario = usuario;
        this.Psw = Psw;
        this.nombre = nombre;
        this.appat = appat;
        this.apmat = apmat;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
        this.Ciudad = Ciudad;
        this.Estado = Estado;
        this.telefono = telefono;
        this.celular = celular;
        this.ingresos = ingresos;
        this.email = email;
    }


    
    
    public boolean nuevo_cliente(String usuario, String Psw, String nombre, 
            String appat, String apmat, String calle, String colonia, 
            int numero, String Ciudad, String Estado, int telefono,
            String celular, int ingresos, String email ){
        oracleConection con = new oracleConection();
           String consulta="";
                    consulta ="call ALTA_CLIENTE ('"+usuario+"','"+Psw+"','"+nombre+"',"
                            + "'"+appat+"','"+apmat+"','"+calle+"','"+colonia+"','"
                            + ""+numero+"','"+Ciudad+"','"+Estado+"',"+telefono+","+celular+","
                            + ""+ingresos+",'"+email+"')";

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

    
    public void eliminar_cliente(){
    
    }
    
    public void modificar_cliente(){
    
    }
    

    
    public  Cliente [] Consulta_usuarios(){
         oracleConection con = new oracleConection();
           String consulta="";
                    consulta ="select * from Cliente";

            Cliente [] clientes=null;
           //ejectamos consulta
           con.Consult(consulta);
           //n indicara cuantos registros cumplen la condicion
           int n=0;
           if(con.rs!=null){


             try {
                 con.rs.last();
                 //privilegios=objConn.rs.getString("priv");
                 //id=objConn.rs.getString("id");
                 n=con.rs.getRow();
                 clientes=new Cliente[n];
                 con.rs.first();
                 int i=0;
                 if(n!=0){//Es trabajador
                     do{
                         
                         clientes[i]=new Cliente();
                         System.out.println("      sdfdsf"+con.rs.getString("celular"));
                         clientes[i].usuario =con.rs.getString("Usuario");
                         clientes[i].Psw =con.rs.getString("psw");
                         clientes[i].nombre =con.rs.getString("nombre");
                         clientes[i].appat =con.rs.getString("appat");
                         clientes[i].apmat =con.rs.getString("apmat");
                         clientes[i].calle = con.rs.getString("calle");
                         clientes[i].colonia = con.rs.getString("Colonia");
                         clientes[i].numero =Integer.parseInt(con.rs.getString("numero"));
                         clientes[i].Ciudad = con.rs.getString("Ciudad");
                         clientes[i].Estado = con.rs.getString("Estado");
                         clientes[i].telefono = Integer.parseInt(con.rs.getString("telefono"));
                         clientes[i].celular = con.rs.getString("celular");
                         clientes[i].ingresos = Integer.parseInt(con.rs.getString("ingresos"));
                         clientes[i].email =con.rs.getString("email");
                         i++;
                     }while(con.rs.next());
                 }
                 
                 con.closeRsStmt();
             } catch (SQLException ex) {
                 Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
             }
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
                
        return clientes;
        
    
    }
    
    public int check_user(String usr,String psw,String Tuser){
        oracleConection con = new oracleConection();
    String consulta="";
                    consulta ="select * from Cliente where Usuario='"+usr+"' and psw='"
                   +psw+ "'";

           //ejectamos consulta
           con.Consult(consulta);
           //n indicara cuantos registros cumplen la condicion
           int n=0;
           if(con.rs!=null){
                try{
               con.rs.last();
               //privilegios=objConn.rs.getString("priv");
               //id=objConn.rs.getString("id");
                n=con.rs.getRow();
                System.out.println(consulta +"   "+n);
                if(n!=0){//Es trabajador

                this.usuario =con.rs.getString("Usuario");
                this.Psw =con.rs.getString("psw");
                this.nombre =con.rs.getString("nombre");
                this.appat =con.rs.getString("appat");
               this.apmat =con.rs.getString("apmat");
               this.calle = con.rs.getString("calle");
                this.colonia = con.rs.getString("Colonia");
               this.numero =Integer.parseInt(con.rs.getString("numero"));
               this.Ciudad = con.rs.getString("Ciudad");
               this.Estado = con.rs.getString("Estado");
                this.telefono = Integer.parseInt(con.rs.getString("telefono"));
                this.celular = con.rs.getString("celular");
                this.ingresos = Integer.parseInt(con.rs.getString("ingresos"));
                this.email =con.rs.getString("email");
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
