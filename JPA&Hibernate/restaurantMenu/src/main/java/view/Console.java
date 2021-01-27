package view;

import controller.App;
import model.Customer;
import model.Dish;

import java.util.List;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private App app;
    private Customer customer;

    public Console() {
        this.scanner = new Scanner(System.in);
        this.app = new App();
        this.customer = new Customer();
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\t\t\t\tWELCOME TO THE RESTAURANT!\n\n" +
                    "\t1. Show all menu.\n" +
                    "\t2. Filter positions by price.\n" +
                    "\t3. DISCOUNTS!!!\n" +
                    "\t4. Add new position.\n" +
                    "\t5. Show customer order.\n\n" +
                    "\t0. Exit.");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    orderFromAll();
                    break;
                case 2:
                    orderFromPriceFilter();
                    break;
                case 3:
                    orderFromDiscountsFilter();
                    break;
                case 4:
                    createNewDish();
                    break;
                case 5:
                    showCustomerOrder();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void positionToAdd(List<Dish> dishes) {
        System.out.println("\n\t1. Add position to the order.\n" +
                "\t2. Previous page.");
        int choiceOne = Integer.parseInt(scanner.nextLine());
        if (choiceOne == 2) {
            return;
        }
        System.out.println("\tWhich position you want to add to order?\t\tAlready in order -- " + customer.getAmountOfFood() +
                " g --");
        int choiceTwo = Integer.parseInt(scanner.nextLine()) - 1;
        if (!customer.isToMuch(dishes.get(choiceTwo).getWeight())) {
            customer.addDish(dishes.get(choiceTwo));
            System.out.println("\n\tPosition has been added to order.");
        } else {
            System.out.println("\n\tWow! This is to mush food for one person!");
        }
    }

    public void orderFromAll() {
        List<Dish> dishes = app.findAll();
        System.out.println(app.menuToPrint(dishes));
        positionToAdd(dishes);
    }

    public void orderFromPriceFilter() {
        System.out.println("\tEnter min value.");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.println("\tEnter max value.");
        double max = Double.parseDouble(scanner.nextLine());
        List<Dish> dishes = app.filterByPrice(min, max);
        System.out.println(app.menuToPrint(dishes));
        positionToAdd(dishes);
    }

    public void orderFromDiscountsFilter() {
        List<Dish> dishes = app.filterByDiscount();
        System.out.println(app.menuToPrint(dishes));
        positionToAdd(dishes);
    }

    public void createNewDish() {
        System.out.println("\tEnter name.");
        String name = scanner.nextLine();
        System.out.println("\tEnter weight.");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.println("\tEnter price.");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("\tEnter discount value in percentages.");
        double discount = Double.parseDouble(scanner.nextLine());
        Dish dish = new Dish(name, weight, price, discount);
        app.addDish(dish);
        System.out.println("\tNew position has been added!");
    }

    public void showCustomerOrder() {
        System.out.println(customer.showOrder());
    }
}
