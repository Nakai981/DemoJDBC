package part2;

import part1.JDBCException;
import part1.JDBCMySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetSQL {
    private Connection connection = null;

    public ResultSetSQL() {
        // Step 1-2: Tao ket noi
        connection = JDBCMySQLConnection.getConnection();
    }
    public void selectStudent(){
        final String query = "SELECT * FROM sinhvien";
        try {
            // Step 3: Tao doi tuong statement/ su khac bien
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // Step 4: Thuc thi cau lenh truy van SQL va lay danh sach ra
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("--> List Student next");
            resultSet.next();
            System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                        +" | "+resultSet.getString(3));



            System.out.println("-->  Student first");
            resultSet.first();
            System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                    +" | "+resultSet.getString(3));

            System.out.println("-->  Student last");
            resultSet.last();
            System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                    +" | "+resultSet.getString(3));

            System.out.println("-->  Student previous");
            resultSet.previous();
            System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                    +" | "+resultSet.getString(3));

            System.out.println("-->  Student absolute");
            resultSet.absolute(3);
            System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                    +" | "+resultSet.getString(3));

            System.out.println("-->  Student relative");
            resultSet.relative(3);
            System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                    +" | "+resultSet.getString(3));

        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            // Step 5: Dong ket noi
            JDBCMySQLConnection.closeConnection();
        }
    }

    public void updateStudent(){
        final String query = "SELECT * FROM sinhvien";
        try {
            // Step 3: Tao doi tuong statement/ su khac bien
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            // Step 4: Thuc thi cau lenh truy van SQL va lay danh sach ra
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            System.out.println("-->  Student change");
            System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                    +" | "+resultSet.getString(3));
            resultSet.updateString(2,"Tran Thi Xuyen");
            resultSet.updateRow();
            System.out.println("-->  Student changed");
            System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                    +" | "+resultSet.getString(3));

        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            // Step 5: Dong ket noi
            JDBCMySQLConnection.closeConnection();
        }
    }

    public static void main(String[] args) {
        ResultSetSQL resultSetSQL = new ResultSetSQL();
        resultSetSQL.updateStudent();

    }
}
