package part2;

import part1.JDBCException;
import part1.JDBCMySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PreparedStatementQuery {
    private Connection connection = null;

    public PreparedStatementQuery() {
        // Step 1-2: Tao ket noi
        connection = JDBCMySQLConnection.getConnection();
    }

    public void insertStudent(String id, String name, Date date, boolean gender, String address){
        try {
            //Step 3 - 4: Tao doi tuong PrepareStatement va thuc thi cau lenh
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sinhvien values (?,?,?,?,?);");
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setDate(3, (java.sql.Date)date);
            preparedStatement.setBoolean(4,gender);
            preparedStatement.setString(5,address);
            int count = preparedStatement.executeUpdate();
            System.out.println("-> Count: "+count);
        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            JDBCMySQLConnection.closeConnection();
        }
    }
    public void selectStudent (){
        try {
            //Step 3 - 4: Tao doi tuong PrepareStatement va thuc thi cau lenh
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sinhvien");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("--> List Student");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                        +" | "+resultSet.getString(3));
            }
        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            JDBCMySQLConnection.closeConnection();
        }
    }

    public static void main(String[] args)  {
        //Test
        PreparedStatementQuery preparedStatementQuery = new PreparedStatementQuery();
        //prepareStatementQuery.insertStudent("MS08","Phan Anh",null,true,"Nghe An");
        preparedStatementQuery.selectStudent();
    }
}
