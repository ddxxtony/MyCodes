/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import OraclePackage.oracleConection;

/**
 *
 * @author ellui
 */
public class Servicio {
    public int id_servicio;
    public String nombre;
    public String descripcion;
    public String Area;
    public String precio;

    public Servicio(int id_servicio, String nombre, String descripcion, String Area) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.Area = Area;
    }

    
    
    public Servicio() {
    }
    
     public void  alta_servicio(){
     
     }
     
     public void Baja_servicio(){
     
     }
     
     public void Modificar_servicio(){
     
     }
     
     public String Consulta_precio(String id_auto,String id_serv){
        oracleConection con = new oracleConection();
        String consulta="";
                    consulta ="SELECT precio from es_para where Auto_id_auto="+id_auto+" and Servicio_id_ser="+id_serv;

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
                con.rs.first();
                if(n!=0){//Es trabajador
                     return con.rs.getString("precio");
                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return null;
     
     }
     
     
     //Metodo que nos devuelve unicamente un servicio que coincida con el id
        public  Servicio[] Consultar_servicio_id(String id){
       oracleConection con = new oracleConection();
       Servicio [] servicios=null;// =new Servicio[Nregistros] ;
        String consulta="";
                    consulta ="SELECT * FROM Servicio where id_ser="+id;

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
                con.rs.first();
               int contador=0;//se encarga de contar la fila en la que se van a guardar los datos
                if(n!=0){//Es trabajador
                    servicios=new Servicio[n];
                   do{
                       servicios[contador]=new Servicio();
                       servicios[contador].id_servicio = Integer.parseInt(con.rs.getString("id_ser"));
                        servicios[contador].nombre = con.rs.getString("nombre");
                       servicios[contador].descripcion = con.rs.getString("Descripcion");
                        servicios[contador].Area =con.rs.getString("Area");
                       contador++;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return servicios;
     
     }
        
     //metodo sobrecargado donde nos regresa todos los servicios que corresponden a una cierta area
     public Servicio[] Consultar_servicios(String Area){
       oracleConection con = new oracleConection();
       Servicio [] servicios=null;// =new Servicio[Nregistros] ;
        String consulta="";
                    consulta ="SELECT * FROM Servicio where Area='"+Area+"'";

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
                con.rs.first();
               int contador=0;//se encarga de contar la fila en la que se van a guardar los datos
                if(n!=0){//Es trabajador
                    servicios=new Servicio[n];
                   do{
                       servicios[contador]=new Servicio();
                       servicios[contador].id_servicio = Integer.parseInt(con.rs.getString("id_ser"));
                        servicios[contador].nombre = con.rs.getString("nombre");
                       servicios[contador].descripcion = con.rs.getString("Descripcion");
                        servicios[contador].Area =con.rs.getString("Area");
                       contador++;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return servicios;
     
     }
          public Servicio[] Consultar_servicios(String Id_auto,int X){
       oracleConection con = new oracleConection();
       Servicio [] servicios=null;// =new Servicio[Nregistros] ;
         String consulta="";
        consulta="";
                    consulta ="SELECT * FROM es_para,Servicio where Auto_id_auto="+Id_auto+" and Servicio_id_ser=Servicio.id_ser";
            
              System.out.println(consulta);
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
                con.rs.first();
               int contador=0;//se encarga de contar la fila en la que se van a guardar los datos
                if(n!=0){//Es trabajador
                    servicios=new Servicio[n];
                   do{
                       servicios[contador]=new Servicio();
                       servicios[contador].id_servicio = Integer.parseInt(con.rs.getString("id_ser"));
                        servicios[contador].nombre = con.rs.getString("nombre");
                       servicios[contador].descripcion = con.rs.getString("Descripcion");
                        servicios[contador].Area =con.rs.getString("Area");
                        servicios[contador].precio =con.rs.getString("precio");
                       contador++;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return servicios;
     
     }
     //metodo sobrecargadop que recibe el numero de registros que se quiere retornar de la tabla servicios
     public Servicio[] Consultar_servicios(int Nregistros){
       oracleConection con = new oracleConection();
       Servicio [] servicios=null;// =new Servicio[Nregistros] ;
         String consulta="";
       if(Nregistros>0){
        consulta="";
                    consulta ="SELECT * FROM (select * from Servicio"
                            + " ORDER BY nombre) Servicios WHERE rownum <="+Nregistros+ " ORDER BY rownum";
            }else{
                 consulta ="SELECT * FROM Servicio" ;
       
            }

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
                con.rs.first();
               int contador=0;//se encarga de contar la fila en la que se van a guardar los datos
                if(n!=0){//Es trabajador
                    servicios=new Servicio[n];
                   do{
                       servicios[contador]=new Servicio();
                       servicios[contador].id_servicio = Integer.parseInt(con.rs.getString("id_ser"));
                        servicios[contador].nombre = con.rs.getString("nombre");
                       servicios[contador].descripcion = con.rs.getString("Descripcion");
                        servicios[contador].Area =con.rs.getString("Area");
                       contador++;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return servicios;
     
     }
}
