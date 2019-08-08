package mohannad.springframework.model.Repository;

import mohannad.springframework.model.books.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * created by mohannad
 */
public interface EmployeeRepository extends CrudRepository<Employee , Long> {
}
