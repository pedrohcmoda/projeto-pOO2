package com.velas.velashome.db;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() throws ClassNotFoundException {
        Dotenv dotenv = Dotenv.configure().load();
        String dbUrl = dotenv.get("DB_URL");
        String pgPassword = dotenv.get("PG_PASSWORD");
        String pgUser = dotenv.get("PG_USER");

        if (conn == null) {
            try{
                Class.forName("org.postgresql.Driver");
                    do {
                       conn = DriverManager.getConnection(dbUrl, pgUser, pgPassword);
                    } while (conn == null);

                }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}