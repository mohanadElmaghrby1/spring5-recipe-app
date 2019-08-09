package mohannad.springframework.converters;

import mohannad.springframework.commands.UnitOfMeasureCommand;
import mohannad.springframework.model.UnitOfMeasure;
import org.hibernate.annotations.Synchronize;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * created by mohannad
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {


    @Nullable
    @Override
    public synchronized UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if ( unitOfMeasureCommand == null )
            return null;

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(unitOfMeasureCommand.getId());
        uom.setDescription(unitOfMeasureCommand.getDescription());
        return uom;
    }

}
