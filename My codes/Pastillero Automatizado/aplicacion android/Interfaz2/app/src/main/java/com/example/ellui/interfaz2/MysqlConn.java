package com.example.ellui.interfaz2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;





public class MysqlConn {
    public Statement stmt = null;
    public ResultSet rs = null;
    public Connection conn = null;

    public MysqlConn() {
        //Connect();
    }

    public void Connect() throws Exception {
        //Conectar con mysql...
        String connectionUrl = "";

        Class.forName("com.mysql.jdbc.Driver");
        //connectionUrl = "jdbc:mysql://10.0.0.18/android?user=android&password=123456";
        connectionUrl = "jdbc:mysql://192.168.43.181/android?user=root&password=ivan1234";
        conn = DriverManager.getConnection(connectionUrl);
    }

    public void Consult(String query) throws Exception {

        if (conn == null) {
            Connect();
        } else {
            if (conn.isClosed()) {
                Connect();
            }
        }

        //consulta...

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        if (stmt.execute(query)) {
            rs = stmt.getResultSet();
            rs.first();

        }

    }

    public int Update(String query) throws Exception {
        int rModif = 0;

        if (conn == null) {
            Connect();
        } else {
            if (conn.isClosed()) {
                Connect();
            }
        }

        stmt = conn.createStatement();
        rModif = stmt.executeUpdate(query);
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

    public void desConnect() throws Exception {
        closeRsStmt();
        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }
}
