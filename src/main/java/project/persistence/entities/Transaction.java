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
    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long splitId;
    private Boolean confirmed;
    private Boolean ignored;
    // private Account account;
    private Double amount;
    private String descr;
    private Date date; //autogenerata

    public Transaction() {
    }
    public Transaction(Long splitId, Boolean confirmed, Boolean ignored, Double amount, String descr) {
        this.splitId = splitId;
        this.confirmed = confirmed;
        this.ignored = ignored;
        this.amount = amount;
        this.descr = descr;
    }
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}