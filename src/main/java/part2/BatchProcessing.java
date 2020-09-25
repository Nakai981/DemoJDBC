package part2;

import part1.JDBCException;
import part1.JDBCMySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class BatchProcessing {
    private Connection connection = null;

    public BatchProcessing() {
        this.connection = JDBCMySQLConnection.getConnection();
    }

    public void insertSubjectByPrepared(ArrayList<Subject> listSubject){
        try {
            //Step 3 - 4: Tao doi tuong PrepareStatement va thuc thi cau lenh
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO monhoc values (?,?,?)");
            for (Subject i: listSubject){
                preparedStatement.setString(1,i.getId());
                preparedStatement.setString(2,i.getName());
                preparedStatement.setInt(3,i.getNum());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            JDBCMySQLConnection.closeConnection();
        }
    }
    public void insertSubjectByStatement(ArrayList<Subject> listSubject){
        try {
            // Step 3: Tao doi tuong statement
            Statement statement = connection.createStatement();
            // Step 4: Thuc thi cau lenh truy van SQL va lay danh sach ra
            for (Subject i: listSubject){
                String query = "INSERT INTO monhoc values ('"+i.getId()+"','"+i.getName()+"',"+i.getNum()+")" ;
                statement.addBatch(query);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            JDBCException.printfSQLException(e);
        } finally {
            // Step 5: Dong ket noi
            JDBCMySQLConnection.closeConnection();
        }
    }

    public static void main(String[] args) {
         Subject sb1 = new Subject();
         sb1.setId("MH06");
         sb1.setName("TOANRR");
         sb1.setNum(3);
         Subject sb2 = new Subject();
         sb2.setId("MH07");
         sb2.setName("TOANTT");
         sb2.setNum(2);
         ArrayList<Subject> listSubject = new ArrayList();
         listSubject.add(sb1);
         listSubject.add(sb2);

         BatchProcessing batchProcessing = new BatchProcessing();
        // batchProcessing.insertSubjectByPrepared(listSubject);
         batchProcessing.insertSubjectByStatement(listSubject);

    }
}
