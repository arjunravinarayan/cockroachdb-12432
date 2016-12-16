import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

/**
 * Created by luyi on 2016/12/16.
 */
public class Main3 {

    private static String url = "jdbc:postgresql://db1:26257,db2:26257,db3:26257/test12432?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory&loadBalanceHosts=true";

    private static String user = "user";

    private static String password = "password";

    public static void main(String[] args) throws SQLException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setJdbcUrl(url);
        ds.setUser(user);
        ds.setPassword(password);
        ds.setMaxPoolSize(1);

        for (int i = 0; i < 10; i++) {
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM test1 WHERE pk = ?");
            pstmt.setString(1, "mykey");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Timestamp ts = rs.getTimestamp("value");
            System.out.println(ts);
            rs.close();
            pstmt.close();
            conn.close();
        }
    }

}
