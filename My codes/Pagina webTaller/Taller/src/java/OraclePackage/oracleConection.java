/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OraclePackage;
//MySql
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;

public class oracleConection {
    public Statement stmt = null;
    public ResultSet rs = null;
    public Connection conn = null;
    public  CallableStatement cs = null;
    public oracleConection() {
        //Connect();
    }
    public void Connect() {
        //Conectar con mysql...
        String connectionUrl = "";
        try {
        Class.forName("oracle.jdbc.OracleDriver");
            connectionUrl ="jdbc:oracle:thin:@localhost:1521:XE";
            conn = DriverManager.getConnection(connectionUrl,"Tall","123456");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: "
                   + cE.toString());
        }
    }
    public void Consult(String query) {
        try {
            if (conn == null) {
                Connect();
            } else {
                if (conn.isClosed()) {
                    Connect();
                }
            }
        } catch (SQLException ex) {       
        }
        //consulta...
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (stmt.execute(query)) {
                rs = stmt.getResultSet();
                rs.first();
                
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("Error: " + ex.getErrorCode());
        }
    }

    public int Update(String query) {
        int rModif = 0;
        try {
            if (conn == null) {
                Connect();
            } else {
                if (conn.isClosed()) {
                    Connect();
                }
            }
        } catch (SQLException ex) {   
        }
        try {
           // stmt = conn.createStatement();
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rModif = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("Error: " + ex.getErrorCode());
        }
        return rModif;
    }

    public void closeRsStmt() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
            } // ignore
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
            } // ignore
            stmt = null;
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


/*
 *
 * String connectionUrl = "jdbc:mysql://localhost/consultorio?"
                    +"user=root&password=lircla";

 */