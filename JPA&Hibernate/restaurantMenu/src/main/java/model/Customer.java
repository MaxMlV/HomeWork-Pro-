package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Dish> dishes;
    private double maxAmountOfFood;

    public Customer() {
        this.dishes = new ArrayList<>();
        this.maxAmountOfFood = 1000.0;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public double getMaxAmountOfFood() {
        return maxAmountOfFood;
    }

    public void setMaxAmountOfFood(Double maxAmountOfFood) {
        this.maxAmountOfFood = maxAmountOfFood;
    }

    public double getTotalPrice() {
        double price = 0;
        for (Dish d : dishes) {
            if (d.getDiscount() > 0) {
                price = d.getPrice() - ((d.getDiscount()/100) * d.getPrice());
            } else {
                price = price + d.getPrice();
            }
        }
        return price;
    }

    public double getAmountOfFood() {
        double amountOfFood = 0;
        for (Dish d : dishes) {
            amountOfFood = amountOfFood + d.getWeight();
        }
        return amountOfFood;
    }

    public boolean isToMuch(double weight) {
        if ((getAmountOfFood() + weight) > maxAmountOfFood) {
            return true;
        }
        return false;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public String showOrder() {
        int count = 1;
        StringBuilder builder = new StringBuilder();
        builder.append("\n\t\t\t\tCUSTOMER ORDER\n\n");
        builder.append("\t\tName\t\tWeight\tPrice\tDiscount\n");
        for (Dish d : dishes) {
            builder.append("\t" + count + ". " + d.getName() +
                    "\t" + d.getWeight() + " g\t" +
                    d.getPrice() + "$\t" + d.getDiscount() + "%\n");
            count++;
        }
        builder.append("\n\t\t\t\ttotal price: " + getTotalPrice() + "$" +
                "\t\ttotal weight: " + getAmountOfFood() + " g\n\n");
        return builder.toString();
    }
}
