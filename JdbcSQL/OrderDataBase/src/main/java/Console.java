import java.sql.SQLException;
import java.util.Scanner;

public class Console {
    private final Scanner scanner;
    private final ClientsService clientsService;
    private final ProductsService productsService;
    private final OrdersService ordersService;

    public Console() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.clientsService = new ClientsService(new DbConnector());
        this.productsService = new ProductsService(new DbConnector());
        this.ordersService = new OrdersService(new DbConnector());
    }

    public void mainMenu() throws SQLException {
        while (true) {
            System.out.println("\n\t\t\tMAIN MENU" +
                    "\n\n\t1. Clients menu." +
                    "\n\t2. Products menu." +
                    "\n\t3. Orders menu." +
                    "\n\n\t0. Exit.");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    clientsMenu();
                    break;
                case 2:
                    productsMenu();
                    break;
                case 3:
                    ordersMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void clientsMenu() throws SQLException {
        System.out.println("\n\t\t\tCLIENTS MENU" +
                "\n\n\t1. Show all clients." +
                "\n\t2. Add new client." +
                "\n\t3. Remove client." +
                "\n\n\t0. Previous page.");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.println(clientsService.showAllClients());
                break;
            case 2:
                createClient();
                break;
            case 3:
                clientToRemove();
                break;
            case 0:
                return;
            default:
                throw new AssertionError();
        }
    }

    public void createClient() throws SQLException {
        System.out.println("\n\t\tEnter client first name.");
        String firstName = scanner.nextLine();
        System.out.println("\n\t\tEnter client last name.");
        String lastName = scanner.nextLine();
        System.out.println("\n\t\tEnter client age.");
        int age = Integer.parseInt(scanner.nextLine());
        Client client = new Client(firstName, lastName, age);
        clientsService.addClient(client);
        System.out.println("\n\t\tNew client has been added!");
    }

    public void clientToRemove() throws SQLException {
        System.out.println("\n\t\tWhich client you want to remove?\n");
        System.out.println(clientsService.showAllClients());
        int choice = Integer.parseInt(scanner.nextLine());
        clientsService.removeClient(choice);
        System.out.println("\n\t\tClient has been removed!");
    }

    public void productsMenu() throws SQLException {
        System.out.println("\n\t\t\tPRODUCTS MENU" +
                "\n\n\t1. Show all products." +
                "\n\t2. Add new product." +
                "\n\t3. Remove product." +
                "\n\n\t0. Previous page.");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.println(productsService.showAllProducts());
                break;
            case 2:
                createProduct();
                break;
            case 3:
                productToRemove();
                break;
            case 0:
                return;
            default:
                throw new AssertionError();
        }
    }

    public void createProduct() throws SQLException {
        System.out.println("\n\t\tEnter name of product.");
        String productName = scanner.nextLine();
        System.out.println("\n\t\tEnter product price.");
        float price = Float.parseFloat(scanner.nextLine());
        Product product = new Product(productName, price);
        productsService.addProduct(product);
        System.out.println("\n\t\tNew product has been added!");
    }

    public void  productToRemove() throws SQLException {
        System.out.println("\n\t\tWhich product you want to remove?\n");
        System.out.println(productsService.showAllProducts());
        int choice = Integer.parseInt(scanner.nextLine());
        productsService.removeProduct(choice);
        System.out.println("\n\t\tProduct has been removed!");
    }

    public void ordersMenu() throws SQLException {
        System.out.println("\n\t\t\tORDERS MENU" +
                "\n\n\t1. Show all orders." +
                "\n\t2. Add new order." +
                "\n\t3. Remove order." +
                "\n\n\t0. Previous page.");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.println(ordersService.showAllOrders());
                break;
            case 2:
                createOrder();
                break;
            case 3:
                orderToRemove();
            case 0:
                return;
            default:
                throw new AssertionError();
        }
    }

    public void createOrder() throws SQLException {
        System.out.println("\n\t\tChoose client who ordering and which product.\n");
        System.out.println(clientsService.showAllClients());
        int clientId = Integer.parseInt(scanner.nextLine());
        System.out.println();
        System.out.println(productsService.showAllProducts());
        int productId = Integer.parseInt(scanner.nextLine());
        ordersService.createOrder(clientId, productId);
        System.out.println("\n\t\tOrder has been created!");

    }

    public void orderToRemove() throws SQLException {
        System.out.println("\n\t\tWhich product you want to remove?\n");
        System.out.println(ordersService.showAllOrders());
        int choice = Integer.parseInt(scanner.nextLine());
        ordersService.removeOrder(choice);
        System.out.println("\n\t\tProduct has been removed!");
    }
}
