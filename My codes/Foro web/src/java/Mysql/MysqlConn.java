/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ellui
 */
package Mysql;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author TONNY
 */
public class MysqlConn {
    
    Statement stmt = null;
   public ResultSet rs = null;
    Connection conn = null;
    
    
    
    
    
    public MysqlConn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost/foro?" + "user=root&password=123456";
            conn = DriverManager.getConnection(connectionUrl);
        }catch (SQLException e){
            System.out.println("SQL Exception: "+ e.toString());
        }catch(ClassNotFoundException cE){
            System.out.println("Class Not Found Exception: "+ cE.toString());
        }        
    }
    
    
    
    
    
    
    public void Consult(String query){
        //consulta...
      try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if(stmt.execute(query)){
                rs= stmt.getResultSet();
                rs.first();
               
            }
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " +ex.getSQLState());
            System.out.println("Error: " + ex.getErrorCode());
        }
    }
    
    
    
    
    public int Update(String query) {
        System.out.println(query);
        int rModif = 0;
        try{
            stmt = conn.createStatement();
            rModif = stmt.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " +ex.getSQLState());
            System.out.println("Error: " + ex.getErrorCode());
        }
        return rModif;
    }
    public void closeRsStmt(){
        if( rs != null){
            try{
                rs.close();
            }catch (SQLException sqlEx){}
            rs = null;
        }
        if( stmt != null){
            try{
                stmt.close();
            }catch (SQLException sqlEx){}
            stmt = null;
        }
        if( conn != null){
            try{
                conn.close();
            }catch (SQLException sqlEx){}
            conn = null;
        }
        
    }
    
     public void desConnect() {
        closeRsStmt();
        try {
            conn.close();
        } catch (SQLException ex) {
           
        }
    }
}

    

