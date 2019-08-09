package mohannad.springframework.converters;

import mohannad.springframework.commands.UnitOfMeasureCommand;
import mohannad.springframework.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Id;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public final String DESCRIPTION="cup";
    public final Long ID=1L;

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setDescription(DESCRIPTION);
        uomCommand.setId(ID);

        UnitOfMeasure unitOfMeasure = converter.convert(uomCommand);

        //test
        assertNotNull(unitOfMeasure);
        assertEquals(DESCRIPTION , unitOfMeasure.getDescription());
        assertEquals(ID , unitOfMeasure.getId());
    }
}