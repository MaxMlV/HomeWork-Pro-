import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Students");
        EntityManager manager = factory.createEntityManager();

        Group groupOne = new Group("Group-1");
        groupOne.addStudent(new Student("Max", 21, groupOne));
        groupOne.addStudent(new Student("Anton", 21, groupOne));
        Group groupTwo = new Group("Group-2");
        groupTwo.addStudent(new Student("Ivan", 25, groupTwo));
        groupTwo.addStudent(new Student("Alex", 30, groupTwo));
        groupTwo.addStudent(new Student("Mickael", 22, groupTwo));

        try {
            manager.getTransaction().begin();
            manager.persist(groupOne);
            manager.persist(groupTwo);
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.getTransaction().commit();
        }

        try {
            Group foundGroupOne = manager.find(Group.class, groupOne.getGroup_id());
            System.out.println(foundGroupOne.getName() + " -> " + foundGroupOne.size() + " students in this group.");
            Group foundGroupTwo = manager.find(Group.class, groupTwo.getGroup_id());
            System.out.println(foundGroupTwo.getName() + " -> " + foundGroupTwo.size() + " students in this group.");
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
            factory.close();
        }
    }
}
