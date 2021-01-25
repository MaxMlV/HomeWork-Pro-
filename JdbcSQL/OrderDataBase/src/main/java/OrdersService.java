import java.sql.*;

public class OrdersService {
    private final DbConnector connector;
    private final Connection connection;

    public OrdersService(DbConnector connector) throws SQLException {
        this.connector = connector;
        this.connection = this.connector.connect();
    }

    public String showAllOrders() throws SQLException {
        StringBuilder builder = new StringBuilder();
        try (PreparedStatement ps = connection.prepareStatement("SELECT orderdb.clients.firstname, orderdb.clients.lastname, " +
                "orderdb.clients.age, orderdb.products.name, " +
                "orderdb.products.price FROM orderdb.orders INNER JOIN " +
                "orderdb.clients ON orderdb.orders.clientid = orderdb.clients.clientid " +
                "INNER JOIN orderdb.products ON orderdb.products.productid = orderdb.orders.productid;")) {
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    builder.append("\t\t" + md.getColumnName(i));
                builder.append("\n");

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        builder.append("\t\t" + rs.getString(i));
                    }
                    builder.append("$");
                    builder.append("\n");
                }
                rs.close();
            }
            ps.close();
        }
        return builder.toString();
    }

    public void createOrder(int clientId, int productId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO orderdb.orders (clientid, productid) " +
                "VALUES (?, ?)")) {
            ps.setInt(1, clientId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();
        }
    }

    public void removeOrder(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM orderdb.orders WHERE orders.orderid = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }
    }
}
