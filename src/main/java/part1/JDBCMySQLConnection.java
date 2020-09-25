package part1;

import java.sql.*;

public class JDBCMySQLConnection {

    private static Connection connection = null;

    public static Connection getConnection() {

        final String url = "jdbc:mysql://localhost:3306/qlsv";
        final String username = "root";
        final String password ="123456789";

        try {
            // Step 1: Dang ky driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            */
            // Step 2: Tao doi tuong connection
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("---Connected to the MySQL server successfully.");
            } else {
                System.out.println("---Failed to make connection!");
            }

        } catch (SQLException e) {
            JDBCException jdbcException = new JDBCException();
            jdbcException.printfSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("---Close connection");
        } catch (SQLException ex) {
            JDBCException.printfSQLException(ex);
        }
    }
    public static void closeConnection(ResultSet rs, Statement stmt) {
        try {
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet closed!");
            }
        } catch (SQLException ex) {
            /* ignored */
        }
        try {
            stmt.close();
            System.out.println("Statement closed!");
        } catch (SQLException ex) {
            /* ignored */
        }
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException ex) {
            /* ignored */
        }
    }
    public static void closeConnection(ResultSet resultSet, PreparedStatement preparedStatement) {
        try {
            if (resultSet != null) {
                resultSet.close();
                System.out.println("ResultSet closed!");
            }
        } catch (SQLException ex) {
            /* ignored */ }
        try {
            preparedStatement.close();
            System.out.println("PreparedStatement closed!");
        } catch (SQLException ex) {
            /* ignored */ }
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException ex) {
            /* ignored */ }
    }
    public static void closeConnection(ResultSet resultSet, CallableStatement callableStatement) {
        try {
            if (resultSet != null) {
                resultSet.close();
                System.out.println("ResultSet closed!");
            }
        } catch (SQLException ex) {
            /* ignored */ }
        try {
            callableStatement.close();
            System.out.println("CallableStatement closed!");
        } catch (SQLException ex) {
            /* ignored */ }
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException ex) {
            /* ignored */ }
    }

    public static void main(String[] args) {
        //TestConnection
        JDBCMySQLConnection jdbcMySQLConnection = new JDBCMySQLConnection();
        jdbcMySQLConnection.getConnection();
    }
}

