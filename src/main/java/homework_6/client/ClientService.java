package homework_6.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement selectMaxIdSt;
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement setNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement listAllSt;
    private PreparedStatement clearSt;

    public ClientService(Connection connection)  {
        try {
            selectMaxIdSt = connection.prepareStatement("SELECT max(id) AS maxId FROM client");
            createSt = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
            getByIdSt = connection.prepareStatement("SELECT name FROM client WHERE id = ?");
            setNameSt = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
            deleteByIdSt = connection.prepareStatement("DELETE FROM client WHERE id = ?");
            listAllSt = connection.prepareStatement("SELECT id, name FROM client");
            clearSt = connection.prepareStatement("DELETE FROM client");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) {
        try {
            createSt.setString(1, name);
            createSt.executeUpdate();
            long id;
            try (ResultSet rs = selectMaxIdSt.executeQuery()) {
                rs.next();
                id = rs.getLong("maxId");
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getById(long id) {
        try {
            getByIdSt.setLong(1, id);
            String name;
            try (ResultSet rs = getByIdSt.executeQuery()) {
                rs.next();
                name = rs.getString("name");
            }
            return name;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setName(long id, String name) {
        try {
            setNameSt.setString(1, name);
            setNameSt.setLong(2, id);
            setNameSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try {
            deleteByIdSt.setLong(1, id);
            deleteByIdSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() {
        List<Client> result = new ArrayList<>();
        try (ResultSet rs = listAllSt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Client client = new Client();
                client.setId(id);
                client.setName(name);
                result.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void clear() {
        try {
            clearSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
