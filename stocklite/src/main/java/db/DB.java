package db;

import java.sql.*;

public class DB {

    private static Connection conn = null;
    public static int id=0;
    
    public static Connection getConnection() throws ClassNotFoundException {
        String dbUrl = "postgres://postgres:fB6f6625CdCaa46DCefebg6Bc-bFaGG@viaduct.proxy.rlwy.net:44891/railway";
        String pgPassword = "postgres";
        String pgUser = "fB6f6625CdCaa46DCefebg6Bc-bFaGG";

        if (conn == null) {
        if(id != -1){
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
        }
        return conn;
    }

    public static int getId() {
        return id;
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