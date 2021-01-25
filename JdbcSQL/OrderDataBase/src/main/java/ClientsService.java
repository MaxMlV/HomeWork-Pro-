import java.sql.*;

public class ClientsService {
    private final DbConnector connector;
    private final Connection connection;

    public ClientsService(DbConnector connector) throws SQLException {
        this.connector = connector;
        this.connection = this.connector.connect();
    }

    public DbConnector getConnector() {
        return connector;
    }

    public Connection getConnection() {
        return connection;
    }

    public String showAllClients() throws SQLException {
        StringBuilder builder = new StringBuilder();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM orderdb.clients")) {
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    builder.append("\t\t" + md.getColumnName(i));
                builder.append("\n");

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        builder.append("\t\t" + rs.getString(i));
                    }
                    builder.append("\n");
                }
                rs.close();
            }
            ps.close();
        }
        return builder.toString();
    }

    public void addClient(Client client) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO orderdb.clients (firstname, lastname, age)" +
                "VALUES (?, ?, ?)")) {
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getAge());
            ps.executeUpdate();
            ps.close();
        }
    }

    public void removeClient(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM orderdb.clients WHERE clients.clientid = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }
    }
}
