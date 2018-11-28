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

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Transaction> transactionList;
    //@JoinColumn(name = "transactionId")

    private String user1;
    private String user2;
    private Double netBalance;


    public Account() {
    }

    public Account(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public String getUser1() {
        return user1;
    }

    public void setUsers(String user1,  String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public String getUser2() {
        return user2;
    }



    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }


    public Double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(Double netBalance) {
        this.netBalance = netBalance;
    }
}