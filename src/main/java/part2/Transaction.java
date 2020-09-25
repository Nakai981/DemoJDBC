package part2;

import part1.JDBCException;
import part1.JDBCMySQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCMySQLConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            connection.setAutoCommit(false);

            String sql1 = "INSERT INTO monhoc VALUES ('MH01','MAC',2)";
            String sql2 = "DELETE FROM monhoc WHERE monhoc.mamonhoc = 'MH02'";

            statement.executeUpdate(sql2);
            statement.executeUpdate(sql1);

            connection.commit();
        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        }finally {
            JDBCMySQLConnection.closeConnection();
        }
    }
}
