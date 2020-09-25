package part2;

import part1.JDBCException;
import part1.JDBCMySQLConnection;

import java.sql.*;

public class StatementAndPreparedStatement {
    public Connection connection = null;

    public StatementAndPreparedStatement() {
        //Step 1-2
        this.connection = JDBCMySQLConnection.getConnection();
    }

    public void signInByStatement(String user, String password){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from sinhvien where username= '"+user+"' and password = '"+password+"'");
            if(resultSet!=null){
                System.out.println("-> Dang nhap thanh cong");
            }else{
                System.out.println("-> Dang nhap that bai");
            }
        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            JDBCMySQLConnection.closeConnection();
        }
    }
    public void signInByPrepared(String user, String password){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sinhvien where username= ? and password = ?");
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count =0;
            while(resultSet.next()){
                count++;
            }
            if(count!=0){
                System.out.println("-> Dang nhap thanh cong");
            }else{
                System.out.println("-> Dang nhap that bai");
            }
        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            JDBCMySQLConnection.closeConnection();
        }
    }

    public static void main(String[] args) {
        StatementAndPreparedStatement statementAndPreparedStatement = new StatementAndPreparedStatement();
//        statementAndPreparedStatement.signInByStatement("xuyen","xuyen1234");
//        statementAndPreparedStatement.signInByStatement("hack'#",null);
//        statementAndPreparedStatement.signInByPrepared("xuyen","xuyen1234");
       statementAndPreparedStatement.signInByPrepared("hack'#",null);

    }
}
