package homework_6.client;

import homework_6.databaseService.Database;
import homework_6.databaseService.DatabaseInitService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private Connection connection;
    private ClientService clientService;

    @BeforeEach
    public void beforeEach() throws SQLException {
        String connectionUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        new DatabaseInitService().initDb(connectionUrl);
        connection = DriverManager.getConnection(connectionUrl);
        clientService = new ClientService(connection);
        clientService.clear();
    }

    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }


    @Test
    public void testThatClientCreatedCorrectly() {
        long id = clientService.create("Test Client");

        String actualName = clientService.getById(id);

        Assertions.assertEquals("Test Client", actualName);
    }

    @Test
    public void setName() {
        long id = clientService.create("Test Client");
        clientService.setName(id, "Changed Name");
        String actual = clientService.getById(id);
        Assertions.assertEquals("Changed Name", actual);
    }

    @Test
    public void deleteById() {
        long id = clientService.create("Test Client");
        clientService.deleteById(id);
        String expected = clientService.getById(id);
        Assertions.assertNull(expected);
    }

    @Test
    public void listAll() {
        Client newClient = new Client();
        newClient.setName("Test Client");

        long id = clientService.create(newClient.getName());
        newClient.setId(id);

        List<Client> expectedClients = Collections.singletonList(newClient);
        List<Client> actualClients = clientService.listAll();

        Assertions.assertEquals(expectedClients, actualClients);
    }


}