package mohannad.springframework.repositories;

import mohannad.springframework.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // for intergration testing , will bring up the spring context
@DataJpaTest // bring up embaded data base and jpa config
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() throws Exception {
        //querying data base and get Teaspoon UnitOfMeasure
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        //make sure that the query result is equal Teaspoon
        assertEquals("Teaspoon" , uomOptional.get().getDescription());
    }

    /**
     * this method will take less time than the above method
     * because spring context called in the above and still available
     * @throws Exception
     */
    @Test
    public void findByDescriptionCup() throws Exception {
        //querying data base and get Teaspoon UnitOfMeasure
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

        //make sure that the query result is equal Teaspoon
        assertEquals("Cup" , uomOptional.get().getDescription());
    }


}