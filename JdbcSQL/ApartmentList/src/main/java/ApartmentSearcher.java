import java.sql.*;
import java.util.Scanner;

public class ApartmentSearcher {
    private final Scanner scanner;
    private final DbConnector connector;
    private final Connection connection;

    public ApartmentSearcher() throws SQLException {
        this.connector = new DbConnector();
        this.connection = connector.connect();
        this.scanner = new Scanner(System.in);
    }

    public void printPreparedStatement(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();

            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnName(i) + "\t\t");
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
        }
    }

    public void showAll() throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appartments")) {
            printPreparedStatement(ps);
        }
    }

    public void byDistrict() throws SQLException {
        System.out.println("\n\tChoose what district do you want?" +
                "\n\n\t\t1. Left Coast." +
                "\n\t\t2. Right Coast.");
        int districtChoice = scanner.nextInt();
        String district;
        switch (districtChoice) {
            case 1:
                district = "Left Coast";
                break;
            case 2:
                district = "Right Coast";
                break;
            default:
                throw new AssertionError();
        }
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appartmentdb.appartments WHERE appartments.district = ?")) {
            ps.setString(1, district);
            printPreparedStatement(ps);
        }
    }

    public void byRooms() throws SQLException {
        System.out.println("\n\tHow many rooms do you want?");
        int roomsCount = scanner.nextInt();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appartmentdb.appartments WHERE appartments.rooms = ?")) {
            ps.setInt(1, roomsCount);
            printPreparedStatement(ps);
        }
    }

    public void byArea() throws SQLException {
        System.out.println("\n\tWhat area do you prefer? (Will be show you preferred area or smaller)");
        double areaChoice = scanner.nextDouble();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appartmentdb.appartments WHERE appartments.area <= ?")) {
            ps.setDouble(1, areaChoice);
            printPreparedStatement(ps);
        }
    }

    public void byPrice() throws SQLException {
        System.out.println("\n\tHow much you may spend for the apartment?");
        double priceChoice = scanner.nextDouble();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appartmentdb.appartments WHERE appartments.price <= ?")) {
            ps.setDouble(1, priceChoice);
            printPreparedStatement(ps);
        }
    }

    public void byAddress() throws SQLException {
        System.out.println("\n\tChoose address of your apartment." +
                "\n\n\t\t1. Someaddress st. 1" +
                "\n\t\t2. Someaddress st. 2");
        int addressChoice = scanner.nextInt();
        String address;
        switch (addressChoice) {
            case 1:
                address = "Someaddress st. 1";
                break;
            case 2:
                address = "Someaddress st. 2";
                break;
            default:
                throw new AssertionError();
        }
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appartmentdb.appartments WHERE appartments.address = ?")) {
            ps.setString(1, address);
            printPreparedStatement(ps);
        }
    }
}
