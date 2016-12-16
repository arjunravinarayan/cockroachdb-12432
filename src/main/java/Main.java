import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * Created by luyi on 2016/12/16.
 */
public class Main {

    private static String url = "jdbc:postgresql://db1:26257,db2:26257,db3:26257/test12432?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory&loadBalanceHosts=true";

    private static String user = "user";

    private static String password = "password";

    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "false");
        config.setMaximumPoolSize(1);
        HikariDataSource ds = new HikariDataSource(config);

        for (int i = 0; i < 100; i++) {
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
