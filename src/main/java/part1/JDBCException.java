package part1;

import java.sql.SQLException;

public class JDBCException {
    public static void printfSQLException(SQLException ex) {
        for (Throwable tb : ex) {
            if (tb instanceof SQLException) {
                tb.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) tb).getSQLState());
                System.err.println("Error Code: " + ((SQLException) tb).getErrorCode());
                System.err.println("Message: " + tb.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }

            }
        }


    }
}
