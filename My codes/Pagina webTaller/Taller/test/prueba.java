/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import OraclePackage.oracleConection;

import OraclePackage.oracleConection;
import java.sql.SQLException;

/**
 *
 * @author ellui
 */
public class prueba {
    
      /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        oracleConection x=new oracleConection();
        x.Connect();
        x.Consult("select * from Trabajador");
        int n=0;
           if(x.rs!=null){
                
               x.rs.last();
               //privilegios=objConn.rs.getString("priv");
               //id=objConn.rs.getString("id");
                n=x.rs.getRow();
                System.out.println("sddssdfdsf   "+n);
                
         }
    }
    
}
