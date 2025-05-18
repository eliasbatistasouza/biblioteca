package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String ENV_FILE = ".env";
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(ENV_FILE));

            String host = props.getProperty("DB_HOST");
            String port = props.getProperty("DB_PORT");
            String dbname = props.getProperty("DB_NAME");
            user = props.getProperty("DB_USER");
            password = props.getProperty("DB_PASSWORD");

            url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo .env: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
