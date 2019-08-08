package mohannad.springframework.model.Repository;

import mohannad.springframework.model.books.Email;
import org.springframework.data.repository.CrudRepository;

/**
 * created by mohannad
 */
public interface EmailRepository extends CrudRepository<Email , Long> {
}
