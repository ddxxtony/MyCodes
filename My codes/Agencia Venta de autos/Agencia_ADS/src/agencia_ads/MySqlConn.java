/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agencia_ads;
//MySql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MySqlConn {
    public Statement stmt = null;
    public ResultSet rs = null;
    public Connection conn = null;
    public MySqlConn() {
        //Connect();
    }
    public void Connect() {
        //Conectar con mysql...
        String connectionUrl = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectionUrl =
                     "jdbc:mysql://localhost/agencia?"
                    +"user=root&password=123456";
            
            conn = DriverManager.getConnection(connectionUrl);
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
            stmt = conn.createStatement();
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
            stmt = conn.createStatement();
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