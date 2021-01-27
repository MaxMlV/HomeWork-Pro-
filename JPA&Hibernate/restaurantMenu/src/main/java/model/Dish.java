package model;

import javax.persistence.*;

@Entity
@Table(name = "restaurant.menu")
@NamedQueries({
        @NamedQuery(name = "menu.findAll", query = "SELECT d FROM Dish d"),
        @NamedQuery(name = "menu.discount", query = "SELECT d FROM Dish d WHERE d.discount > 0"),
        @NamedQuery(name = "menu.price", query = "SELECT d FROM Dish d WHERE d.price BETWEEN :from AND :to"),
})
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "discount", nullable = false)
    private double discount;

    public Dish() {
    }

    public Dish(String name, double weight, double price, double discount) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
