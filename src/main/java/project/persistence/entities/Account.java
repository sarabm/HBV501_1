package project.persistence.entities;
import javax.persistence.*;
import java.util.List;
/**
 * The class for the Account itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "account") // If you want to specify a table name, you can do so here
public class Account  {
    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "accountId", updatable = false, nullable = false)
    private Long id;

    @OneToMany
    private List<Transaction> transactionList;
    //@JoinColumn(name = "transactionId")


    private User[] users;
    private Double netBalance;


    public Account() {
    }

    public Account(User[] users) {
        this.users = users;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(Double netBalance) {
        this.netBalance = netBalance;
    }
}