import java.sql.*;

public class ProductsService {
    private final DbConnector connector;
    private final Connection connection;

    public ProductsService(DbConnector connector) throws SQLException {
        this.connector = connector;
        this.connection = this.connector.connect();
    }

    public DbConnector getConnector() {
        return connector;
    }

    public Connection getConnection() {
        return connection;
    }

    public String showAllProducts() throws SQLException {
        StringBuilder builder = new StringBuilder();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM orderdb.products")) {
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

    public void addProduct(Product product) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO orderdb.products (name, price) " +
                "VALUES (?, ?)")) {
            ps.setString(1, product.getProductName());
            ps.setFloat(2, product.getPrice());
            ps.executeUpdate();
            ps.close();
        }
    }

    public void removeProduct(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM orederdn.products WHERE products.productid = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }
    }
}
