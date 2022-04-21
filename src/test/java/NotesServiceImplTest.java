import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class NotesServiceImplTest {
    /** Utworzenie nowego obiektu implementacji usługi w celu wykluczenia wpływu * poprzednich testów na nowo realizowane. */
    @Before
    public void before() {
        notesService = NotesServiceImpl.createWith(new NotesStorageMock());
    }
    /** Próba dodania pustej referencji do oceny musi skończyć się zgłoszeniem * przez usługę sytuacji wyjątkowej {@link IllegalArgumentException}. */ @Test(expected = IllegalArgumentException.class)
    public void add_nullNote() {
        notesService.add(null);
    }
    /** Dodanie poprawnej oceny musi skończyć się pomyślnie. */
    @Test
    public void add() {
        notesService.add(Note.of("Jan Kowalski", 5.0f));
    }
    /** Próba wyznaczenia średniej arytmetycznej ucznia o nieokreślonym ({@code null}) * nazwisku musi skończyć się zgłoszeniem przez usługę sytuacji wyjątkowej * {@link IllegalArgumentException}. */
    @Test(expected = IllegalArgumentException.class)
    public void averageOf_null() {
        notesService.averageOf(null);
    }
    /** Próba wyznaczenia średniej arytmetycznej nieistniejącego ucznia (bez dodanych * wcześniej ocen) musi zakończyć się zwróceniem wartości zerowej.<br> * Ponieważ ta sama ścieżka będzie realizowana dla ucznia o pustym nazwisku, nie ma * potrzeby tworzenia oddzielnego testu sprawdzającego zachowanie w przypadku, * gdy zostanie podane puste nazwisko ucznia. */
    @Test
    public void averageOf_nonexistent() {
        assertEquals(0.0f, notesService.averageOf("Maria Curie"), EQUALITY_DELTA); }
    /** Próba wyznaczenia średniej dla dwóch uczniów. Specjalnie podajemy dwóch uczniów, * by wykryć błędy wyznaczania średniej wynikające z brania pod uwagę ocen uczniów innych * niż wskazany. */
    @Test
    public void averageOf_existing() {
        notesService.add(Note.of("Jan Kowalski", 4.0f));
        notesService.add(Note.of("Jan Kowalski", 2.0f));
        notesService.add(Note.of("Maria Curie", 5.0f));
        notesService.add(Note.of("Maria Curie", 4.5f));
        notesService.add(Note.of("Maria Curie", 4.0f));
        assertEquals(4.5f, notesService.averageOf("Maria Curie"), EQUALITY_DELTA); assertEquals(3.0f, notesService.averageOf("Jan Kowalski"), EQUALITY_DELTA); }
    /** Próba usuwania wszystkich ocen ucznia. Uzupełniamy bazę, sprawdzamy średnią, * po czym czyścimy bazę i weryfikujemy, czy teraz średnia jest równa zero. */ @Test
    public void clear() {
        notesService.add(Note.of("Jan Kowalski", 4.0f));
        notesService.add(Note.of("Jan Kowalski", 2.0f));
        assertEquals(3.0f, notesService.averageOf("Jan Kowalski"), EQUALITY_DELTA); notesService.clear();
        assertEquals(0.0f, notesService.averageOf("Jan Kowalski"), EQUALITY_DELTA); }
    /** Dokładność porównywania liczb zmiennoprzecinkowych. Ponieważ oceny zawsze * będą liczone z dokładnością do jednej setnej, zakładamy tutaj właśnie jedną * setną. */
    private static final float EQUALITY_DELTA = 0.01f;
    /** Testowana implementacja usługi. */
    private NotesService notesService;
}
