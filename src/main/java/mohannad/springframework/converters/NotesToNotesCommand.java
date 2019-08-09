package mohannad.springframework.converters;


import mohannad.springframework.commands.NotesCommand;
import mohannad.springframework.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * created by mohannad
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{

    @Nullable
    @Override
    public  synchronized NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
    }
}