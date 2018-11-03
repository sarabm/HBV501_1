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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private List<Transaction> transactionList;
    //private User[] users;
    private Double netBalance;



}