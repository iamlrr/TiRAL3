import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;

public final class NotesStorageMock implements NotesStorage { @Override
public void add(final Note note) {
    notes.put(note.getName(), note);
}
    @Override
    public List<Note> getAllNotesOf(final String name) {
        return (List<Note>) notes.get(name);
    }
    @Override
    public void clear() {
        notes.clear();
    }
    private final Multimap<String, Note> notes = ArrayListMultimap.create(); }
