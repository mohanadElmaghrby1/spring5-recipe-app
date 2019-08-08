package mohannad.springframework.model.books;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * created by mohannad
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee") //We can easily use the mappedBy attribute of @OneToMany annotation to do so
    //Here, the value of mappedBy is the name of the association-mapping attribute on the owning side. With this,
    // we have now established a bidirectional association between our Employee and Email entities.
    private List<Email> emails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
