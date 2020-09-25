package part1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementQuery {
    private Connection connection = null;

    public StatementQuery() {
        // Step 1-2: Tao ket noi
        connection = JDBCMySQLConnection.getConnection();
    }

    public void selectStudent(){
        final String query = "SELECT * FROM sinhvien";
        try {
            // Step 3: Tao doi tuong statement
            Statement statement = connection.createStatement();
            // Step 4: Thuc thi cau lenh truy van SQL va lay danh sach ra
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("--> List Student");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                        +" | "+resultSet.getString(3));
            }
        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            // Step 5: Dong ket noi
            JDBCMySQLConnection.closeConnection();
        }
    }

    public static void main(String[] args) {
        //Test
        StatementQuery statementQuery = new StatementQuery();
        statementQuery.selectStudent();
    }
}
