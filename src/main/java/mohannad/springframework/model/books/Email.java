package mohannad.springframework.model.books;

import javax.persistence.*;
import java.util.Set;

/**
 * created by mohannad
 */
@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String domain;

    @ManyToOne(fetch = FetchType.LAZY)
    //JoinColumn(name = "emp_id" emp_id  is the name of joining column
    @JoinColumn(name = "emp_id") //@JoinColumn annotation defines that actual physical mapping on the owning side
    //It simply means that our Email entity will have a foreign key column named employee_id referring to the primary attribute id of our Employee entity.
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
