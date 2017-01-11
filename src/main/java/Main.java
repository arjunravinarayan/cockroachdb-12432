import java.sql.*;

/**
 * Created by luyi on 2016/12/16.
   Updated by arjunravinarayan on 2017/01/11.


 To set up the environment, run:

./cockroach sql -e "create database db1"
./cockroach sql -d db1 -e "create table test1(pk string, value timestamp)"
./cockroach sql -d db1 -e "insert into test1 values('mykey', now())"
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        String url = new StringBuilder()
            .append("jdbc:postgresql://")
            .append("localhost")
            .append(':')
            .append(26257)
            .append('/')
            .append("db1")
            .append("?user=root")
            .append("&sslmode=disable")
            .append("&ssl=false")
            //            .append("&binaryTransferDisable=1114")
            // Uncommenting this line fixes the issue.
            .toString();

        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM test1 WHERE pk = ?");
        pstmt.setString(1, "mykey");
        for (int i = 0; i < 10; i++) {
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String key = rs.getString("pk");
            Timestamp ts = rs.getTimestamp("value");
            System.out.println(key + ", " + ts);
            rs.close();
        }
        pstmt.close();
        conn.close();
    }
}
