package part2;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class RowSet {
    public void selectStudent() throws SQLException, ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/qlsv";
        String username = "root";
        String password ="123456789";

        // Step 1: Dang ky driver
       // Class.forName("com.mysql.cj.jdbc.Driver");

        // Step 2: Tao ket noi
        JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
        jdbcRowSet.setUrl(url);
        jdbcRowSet.setUsername(username);
        jdbcRowSet.setPassword(password);

        // Step 3: Tao cau lenh truy van
        jdbcRowSet.setCommand("select * from sinhvien");

        // Step 4: Thuc thi cau lenh
        jdbcRowSet.execute();
        System.out.println("--> List Student");
        while (jdbcRowSet.next()){
            System.out.println(jdbcRowSet.getString(1)+" | "+jdbcRowSet.getString(2)
                    +" | "+jdbcRowSet.getString(3));
        }

        // Step 5: Dong connection
        jdbcRowSet.close();

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        RowSet rowSet = new RowSet();
        rowSet.selectStudent();
    }
}
