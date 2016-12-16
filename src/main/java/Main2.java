import java.sql.*;

/**
 * Created by luyi on 2016/12/16.
 */
public class Main2 {

    private static String url = "jdbc:postgresql://db1:26257,db2:26257,db3:26257/test12432?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory&loadBalanceHosts=true&user=user&password=password";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(url);

        for (int i = 0; i < 10; i++) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM test1 WHERE pk = ?");
            pstmt.setString(1, "mykey");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Timestamp ts = rs.getTimestamp("value");
            System.out.println(ts);
            rs.close();
            pstmt.close();
        }

        conn.close();
    }

}
