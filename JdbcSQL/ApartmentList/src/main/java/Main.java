import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ApartmentSearcher searcher = new ApartmentSearcher();

        while (true) {
            System.out.println("\n\t1. Show all apartments." +
                    "\n\t2. Search by district." +
                    "\n\t3. Search by rooms count." +
                    "\n\t4. Search by apartment area." +
                    "\n\t5. Search by price." +
                    "\n\t6. Search by address." +
                    "\n\n\t0. Exit.");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searcher.showAll();
                    break;
                case 2:
                    searcher.byDistrict();
                    break;
                case 3:
                    searcher.byRooms();
                    break;
                case 4:
                    searcher.byArea();
                    break;
                case 5:
                    searcher.byPrice();
                    break;
                case 6:
                    searcher.byAddress();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
