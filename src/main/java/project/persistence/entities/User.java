package project.persistence.entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for the User Note itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name="`User`")
public class User {
    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "userid", updatable = false, nullable = false)
    private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
    @ManyToMany
    protected List<User> friendlist = null;


    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="USER_FRIEND",
            joinColumns=@JoinColumn(name="USER_ID"),
            inverseJoinColumns=@JoinColumn(name="FRIEND_ID")
    )
    public List getFriendlist() {
        return friendlist;
    }
    /*
    @ManyToMany
    protected List<User> friendlist = null;*/



    // Notice the empty constructor, because we need to be able to create an empty User to add
    // to our model so we can use it with our form
    public User() {
    }

    public User(String username, String firstname, String lastname, String email, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public void setFriendlist(List<User> friendlist) {
        this.friendlist = friendlist;
    }

    public Long getId() {return id;}
}