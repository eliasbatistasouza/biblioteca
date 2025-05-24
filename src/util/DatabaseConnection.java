package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String host = System.getenv("MYSQL_HOST");
    private static final String port = System.getenv("MYSQL_PORT");
    private static final String database = System.getenv("MYSQL_DATABASE");
    private static final String user = System.getenv("MYSQL_USER");
    private static final String password = System.getenv("MYSQL_PASSWORD");

    private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}