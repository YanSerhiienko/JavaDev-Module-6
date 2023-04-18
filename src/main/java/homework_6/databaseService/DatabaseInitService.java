package homework_6.databaseService;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public void initDb(String connectionUrl) {
        Flyway flyway = Flyway.configure().dataSource(connectionUrl, null, null).load();
        flyway.migrate();
    }
}

