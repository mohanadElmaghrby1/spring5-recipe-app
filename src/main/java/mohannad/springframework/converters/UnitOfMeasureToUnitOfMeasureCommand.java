package mohannad.springframework.converters;

import mohannad.springframework.commands.UnitOfMeasureCommand;
import mohannad.springframework.model.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * created by mohannad
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure , UnitOfMeasureCommand> {

    @Override
    public synchronized UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if ( unitOfMeasure == null )
            return null;

        final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(unitOfMeasure.getId());
        uomCommand.setDescription(unitOfMeasure.getDescription());
        return uomCommand;
    }
}
