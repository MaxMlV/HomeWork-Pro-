package controller;

import model.Dish;

import javax.persistence.*;
import java.util.List;

public class App {
    private EntityManagerFactory factory;
    private EntityManager manager;

    public App() {
        this.factory = Persistence.createEntityManagerFactory("Restaurant");
        this.manager = factory.createEntityManager();
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public void addDish(Dish dish) {
        try {
            manager.getTransaction().begin();
            manager.persist(dish);
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public String menuToPrint(List<Dish> dishes) {
        int count = 1;
        StringBuilder builder = new StringBuilder();
        builder.append("\n\t\t\tMENU\n\n");
        builder.append("\t\tName\t\tWeight\tPrice\tDiscount\n");
        for (Dish d : dishes) {
            builder.append("\t" + count + ". " + d.getName() +
                    "\t" + d.getWeight() + " g\t" +
                    d.getPrice() + "$\t" + d.getDiscount() + "%\n");
            count++;
        }
        return builder.toString();
    }

    public List<Dish> findAll() {
        TypedQuery<Dish> query = manager.createNamedQuery("menu.findAll", Dish.class);
        return query.getResultList();
    }

    public List<Dish> filterByPrice(double min, double max) {
        TypedQuery<Dish> query = manager.createNamedQuery("menu.price", Dish.class);
        query.setParameter("from", min);
        query.setParameter("to", max);
        return query.getResultList();
    }

    public List<Dish> filterByDiscount() {
        TypedQuery<Dish> query = manager.createNamedQuery("menu.discount", Dish.class);
        return query.getResultList();
    }
}
