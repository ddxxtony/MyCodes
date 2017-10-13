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
public class Auto {
  public   int id_auto;
   public  String nombre;
   public  String modelo;
   public  String marca;
   public  String tipo;
   public  int cilindros;

    public Auto() {
    }

    public Auto(int id_auto, String nombre, String modelo, String marca, String tipo, int cilindros) {
        this.id_auto = id_auto;
        this.nombre = nombre;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.cilindros = cilindros;
    }
    
      public Auto( String nombre, String modelo, String marca, String tipo) {
          
        this.id_auto = id_auto;
        this.nombre = nombre;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.cilindros = cilindros;
    }
    
      
      public String Consulta_auto(String nombre, String modelo, String marca, String tipo,String cilindros){
      
       oracleConection con = new oracleConection();
       String respuesta=null;
        String consulta="";
        consulta="select *  from Auto "
                + "where nombre='"+nombre+"' "
                + "and marca='"+marca+"'"
                + " and modelo="+modelo+" "
                + "and tipo='"+tipo+"'"
                + " and cilindros="+cilindros;
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
                con.rs.first();
               int contador=0;//se encarga de contar la fila en la que se van a guardar los datos
                if(n!=0){//Es trabajador
                       this.id_auto = Integer.parseInt(con.rs.getString("id_auto"));
                        this.nombre = con.rs.getString("nombre");
                       this.modelo = con.rs.getString("modelo");
                       this.marca =con.rs.getString("marca");
                       this.tipo =con.rs.getString("tipo");
                       this.cilindros =Integer.parseInt(con.rs.getString("cilindros"));
                       respuesta=String.valueOf(this.id_auto);

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return respuesta;
      
      }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Auto [] Consulta_Modelo(){
         oracleConection con = new oracleConection();
       Auto [] autos=null;// =new Servicio[Nregistros] ;
        String consulta="";
        consulta="select distinct modelo from Auto";
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
                    autos=new Auto[n];
                   do{
                       autos[contador]=new Auto();
                       //autos[contador].id_auto = Integer.parseInt(con.rs.getString("id_auto"));
                        //autos[contador].nombre = con.rs.getString("nombre");
                       autos[contador].modelo = con.rs.getString("modelo");
                       //autos[contador].marca =con.rs.getString("marca");
                       //autos[contador].tipo =con.rs.getString("tipo");
                       //autos[contador].cilindros =Integer.parseInt(con.rs.getString("cilindros"));
                       contador++;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return autos;
     
     }
    public static Auto [] Consulta_tipo(){
         oracleConection con = new oracleConection();
       Auto [] autos=null;// =new Servicio[Nregistros] ;
        String consulta="";
        consulta="select distinct tipo from Auto";
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
                    autos=new Auto[n];
                   do{
                       autos[contador]=new Auto();
                       //autos[contador].id_auto = Integer.parseInt(con.rs.getString("id_auto"));
                        //autos[contador].nombre = con.rs.getString("nombre");
                       //autos[contador].modelo = con.rs.getString("modelo");
                       //autos[contador].marca =con.rs.getString("marca");
                       autos[contador].tipo =con.rs.getString("tipo");
                       //autos[contador].cilindros =Integer.parseInt(con.rs.getString("cilindros"));
                       contador++;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return autos;
     
     }
    
 
         public static Auto [] Consulta_Nombre(){
         oracleConection con = new oracleConection();
       Auto [] autos=null;// =new Servicio[Nregistros] ;
        String consulta="";
        consulta="select distinct nombre from Auto";
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
                    autos=new Auto[n];
                   do{
                       autos[contador]=new Auto();
                       //autos[contador].id_auto = Integer.parseInt(con.rs.getString("id_auto"));
                        autos[contador].nombre = con.rs.getString("nombre");
                       //autos[contador].modelo = con.rs.getString("modelo");
                       //autos[contador].marca =con.rs.getString("marca");
                       //autos[contador].tipo =con.rs.getString("tipo");
                       //autos[contador].cilindros =Integer.parseInt(con.rs.getString("cilindros"));
                       contador++;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return autos;
     
     }
           
     public static Auto [] Consulta_Marca(){
         oracleConection con = new oracleConection();
       Auto [] autos=null;// =new Servicio[Nregistros] ;
        String consulta="";
        consulta="select distinct marca from Auto";
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
                    autos=new Auto[n];
                   do{
                       autos[contador]=new Auto();
                       //autos[contador].id_auto = Integer.parseInt(con.rs.getString("id_auto"));
                        //autos[contador].nombre = con.rs.getString("nombre");
                       //autos[contador].modelo = con.rs.getString("modelo");
                       autos[contador].marca =con.rs.getString("marca");
                       //autos[contador].tipo =con.rs.getString("tipo");
                       //autos[contador].cilindros =Integer.parseInt(con.rs.getString("cilindros"));
                       contador++;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return autos;
     
     }
     
     
     //se encarga deverificae que exissta un auto que satisfaga ciertas caracteristicas
           public static boolean Check_auto(String nombre, String modelo,String tipo,String marca){
         oracleConection con = new oracleConection();
        String consulta="";
        consulta="select  * from Auto where nombre="+nombre+" and modelo="+modelo+" and tipo="+tipo+ " and marca="+marca;
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
                   do{
                     
                       contador++;
                         return true;
                   }while(con.rs.next());
                    

                }
                }catch(Exception e){
                    }
               con.closeRsStmt();
           }
           
           //desconectamos
           //si existe al menos un registro
          
          
        
        return false;
     
     }
        
    }
     
   
    

