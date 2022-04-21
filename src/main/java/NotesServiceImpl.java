import java.util.Collection;

public class NotesServiceImpl implements NotesService {
    public static NotesService createWith(final NotesStorage storageService) {
        return new NotesServiceImpl(storageService);
    }

    @Override
    public void add(final Note note) {
        storageService.add(note);
    }

    @Override
    public float averageOf(final String name) {
        float sum = 0.0f;
        final Collection<Note> notes = storageService.getAllNotesOf(name);
        if(notes.isEmpty()) {
            return 0;
        }
        for (final Note note : notes) {
            sum += note.getNote();
        }
        return sum / notes.size();
    }

    @Override
    public void clear() {
        storageService.clear();
    }

    private NotesServiceImpl(final NotesStorage storageService) {
        this.storageService = storageService;
    }

    private final NotesStorage storageService;
}
