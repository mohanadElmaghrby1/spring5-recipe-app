package mohannad.springframework.services;

import mohannad.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * created by mohannad
 */
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUom();
}
