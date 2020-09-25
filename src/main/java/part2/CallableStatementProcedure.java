package part2;

import part1.JDBCException;
import part1.JDBCMySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class CallableStatementProcedure {
    private Connection connection = null;

    public CallableStatementProcedure() {
        // Step 1-2: Tao ket noi
        connection = JDBCMySQLConnection.getConnection();
    }

    public void callProceduceSearchStudentByAddress(String address){
        try{
            //Step 3: Tao doi tuong CallableStatement
            CallableStatement callableStatement = connection.prepareCall("call timKiemSV(?)");
            callableStatement.setString(1,address);
            //Step 4: Thuc thi cau lenh
            ResultSet resultSet = callableStatement.executeQuery();

            System.out.println("--> List Student");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)
                        +" | "+resultSet.getString(3));
            }
        }catch (SQLException e){
            JDBCException.printfSQLException(e);
        }finally {
            JDBCMySQLConnection.closeConnection();
        }
    }
    public static void main(String[] args) {
        CallableStatementProcedure callableStatementProcedure = new CallableStatementProcedure();
        callableStatementProcedure.callProceduceSearchStudentByAddress("Ninh Bình");
    }
}
