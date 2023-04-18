package homework_6;

import homework_6.client.ClientService;
import homework_6.databaseService.Database;

import java.sql.SQLException;
import java.util.List;

public class ApplicationTest {
    public static void main(String[] args) throws SQLException {

        ClientService cs = new ClientService(Database.getInstance().getConnection());

        long id = cs.create("Jimmy McGill");
        System.out.println("created = " + cs.getById(id));

        cs.setName(id, "Saul Goodman");
        System.out.println("set name for created = " + cs.getById(id));

        printList(cs.listAll());

        cs.deleteById(id);
        System.out.println("after delete = " + cs.getById(id));
    }

    private static void printList(List list) {
        list.forEach(System.out::println);
    }
}
