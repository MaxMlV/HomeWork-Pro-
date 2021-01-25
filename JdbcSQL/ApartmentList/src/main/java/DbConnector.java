import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private final String DB_CONNECTION = "jdbc:mysql://localhost:3306/appartmentdb?";
    private final String DB_TIMEZONE = "useTimezone=true&serverTimezone=UTC";
    private final String DB_USER = "&user=root&";
    private final String DB_PASSWORD = "password=maxim68010-1343";
    private final String DB_USESSL = "&useSSL=false";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION + DB_TIMEZONE + DB_USER + DB_PASSWORD + DB_USESSL);
    }
}
