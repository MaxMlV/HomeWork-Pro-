import javax.persistence.*;
import java.util.Currency;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accid;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
}
