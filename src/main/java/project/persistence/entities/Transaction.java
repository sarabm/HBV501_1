package project.persistence.entities;
import javax.persistence.*;
import java.util.Date;
/**
 * The class for the Transaction Note itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "transaction") // If you want to specify a table name, you can do so here
public class Transaction {
    // Declare that this attribute is the id and autogenerate
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transactionId", updatable = false, nullable = false)
    private Long id;

    /*@Id
    @Column(name = "splitId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    //Composite-id class must implement Serializable
    private Long splitId;

    private Boolean confirmed;
    private Boolean ignored;

    //JPA relationship to account
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private Double amount;
    private String descr;

    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    /**
     * Empty constructor
     */
    public Transaction() {
    }

    /**
     * default  constructor
     * @param account
     * @param amount
     */
    public Transaction(Account account, Double amount) {
        this.account = account;
        this.amount = amount;
    }

    /**
     * Getters n settesr
     * @return
     */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSplitId() {
        return splitId;
    }
    public void setSplitId(Long splitId) {
        this.splitId = splitId;
    }
    public Boolean getConfirmed() {
        return confirmed;
    }
    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
    public Boolean getIgnored() {
        return ignored;
    }
    public void setIgnored(Boolean ignored) {
        this.ignored = ignored;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Account getAccount() {
        return account;
    }
}