package homework_6.databaseService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Database INSTANCE = new Database();
    private final String connectionUrl = "jdbc:h2:./m6db";

    private Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }
}
