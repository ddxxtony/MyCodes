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
public class Cita {
   public int id_cita;
   public  String fecha;
    public String Cliente;
    public String auto;

    
    public Cita(int id_cita, String fecha, String Cliente, String auto) {
        this.id_cita = id_cita;
        this.fecha = fecha;
        this.Cliente = Cliente;
        this.auto = auto;
    }


    public Cita() {
       
    }
    
    public void alta_cita(String fecha,String hora,String user,String auto){
        oracleConection con = new oracleConection();
          String consulta="";
                    consulta ="insert into Cita values (incremento_citas.nextval,to_date('"+fecha+" "+hora+"','DD/MM/YYYY HH:MI'),'"+user+"',"+auto+")";
                               System.out.println(consulta);
                               
       con.Update(consulta);
    }
    
    public void baja_cita(){
    }
    public void consulta_cita(int IDCita){

        
        
    }
    
    //se encarga de verificar si una fecha ya existe o no
    //retorna tru si la fecha existe
    public static boolean cheeck(String fecha, String hora){
        oracleConection con = new oracleConection();
         //TO_DATE('01-03-2011 07:01','DD-MM-YYYY HH:MI')
    String consulta="";
                    consulta ="select * from cita where TO_DATE(Fecha,'DD/MM/YYYY HH:MI')="
                            +"TO_DATE('"+fecha+" "+hora+"','DD/MM/YYYY HH:MI')";

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
                if(n!=0){//Es trabajador
                     System.out.println(consulta);
                  return true;
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
    
    
    
    




