import data.models.Entry;
import data.repositories.EntryRepository;
import data.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntryRepositoryTest {
    private EntryRepository entryRepository;
    @BeforeEach
    public void startWithThis(){
        entryRepository = new EntryRepositoryImpl();
    }
    @Test
    public void testThatOneEntryCanBeSaved(){
        Entry entry = new Entry();
        entryRepository.save(entry);

        assertEquals(1, entryRepository.count());
    }
    @Test
    public void testThatOneEntryCanBeSaved_AndCanBeFound(){
        Entry entry = new Entry();
        entry.setTitle("title");
        entry.setBody("body");
        entryRepository.save(entry);


        assertEquals(1, entryRepository.count());
        assertEquals("title", entryRepository.findById(1).getTitle());
        assertEquals("body", entryRepository.findById(1).getBody());
    }
    @Test
    public void testThatMoreThanOneEntryCanBeSaved_AndUpdated(){
        Entry entry = new Entry();
        entry.setTitle("title");
        entry.setBody("body");
        entryRepository.save(entry);

        Entry entry1 = new Entry();
        entry1.setTitle("Title");
        entry1.setBody("Body");
        entryRepository.save(entry1);

        Entry entry2 = new Entry();
        entry2.setTitle("TITLE");
        entry2.setBody("BODY");
        entryRepository.save(entry2);


        assertEquals(3, entryRepository.count());
        assertEquals("title", entryRepository.findById(1).getTitle());
        assertEquals("body", entryRepository.findById(1).getBody());

        assertEquals(3, entryRepository.count());
        assertEquals("Title", entryRepository.findById(2).getTitle());
        assertEquals("Body", entryRepository.findById(2).getBody());

        assertEquals(3, entryRepository.count());
        assertEquals("TITLE", entryRepository.findById(3).getTitle());
        assertEquals("BODY", entryRepository.findById(3).getBody());

        Entry newlyUpdated = entryRepository.findById(3);
        newlyUpdated.setId(3);
        newlyUpdated.setTitle("new title");
        newlyUpdated.setBody("new body");
        entryRepository.save(newlyUpdated);

        assertEquals(3, entryRepository.count());
        assertEquals("new title", entryRepository.findById(3).getTitle());
        assertEquals("new body", entryRepository.findById(3).getBody());
    }
    @Test
    public void testThatEntriesCanBeSaved_AndAllBePrinted(){
        Entry entry = new Entry();
        entry.setTitle("title");
        entry.setBody("body");
        entryRepository.save(entry);

        Entry entry1 = new Entry();
        entry1.setTitle("Title");
        entry1.setBody("Body");
        entryRepository.save(entry1);

        Entry entry2 = new Entry();
        entry2.setTitle("TITLE");
        entry2.setBody("BODY");
        entryRepository.save(entry2);


        assertEquals(3, entryRepository.count());
        assertEquals("title", entryRepository.findById(1).getTitle());
        assertEquals("body", entryRepository.findById(1).getBody());

        assertEquals(3, entryRepository.count());
        assertEquals("Title", entryRepository.findById(2).getTitle());
        assertEquals("Body", entryRepository.findById(2).getBody());

        assertEquals(3, entryRepository.count());
        assertEquals("TITLE", entryRepository.findById(3).getTitle());
        assertEquals("BODY", entryRepository.findById(3).getBody());

        Iterable<Entry> expectedEntries = List.of(new Entry[]{entry, entry1, entry2});
        assertEquals(expectedEntries, entryRepository.findAll());
    }
    @Test
    public void testThatEntryThatWasSaved_CanBeDeleted(){
        Entry entry = new Entry();
        entry.setTitle("title");
        entry.setBody("body");
        entryRepository.save(entry);

        Entry entry1 = new Entry();
        entry1.setTitle("Title");
        entry1.setBody("Body");
        entryRepository.save(entry1);

        Entry entry2 = new Entry();
        entry2.setTitle("TITLE");
        entry2.setBody("BODY");
        entryRepository.save(entry2);


        assertEquals(3, entryRepository.count());
        assertEquals("title", entryRepository.findById(1).getTitle());
        assertEquals("body", entryRepository.findById(1).getBody());

        assertEquals(3, entryRepository.count());
        assertEquals("Title", entryRepository.findById(2).getTitle());
        assertEquals("Body", entryRepository.findById(2).getBody());

        assertEquals(3, entryRepository.count());
        assertEquals("TITLE", entryRepository.findById(3).getTitle());
        assertEquals("BODY", entryRepository.findById(3).getBody());

        entryRepository.delete(entry1);
        assertNull(entryRepository.findById(2));
    }
    @Test
    public void testThatListOfEntryThatWasSaved_CanBeDeleted(){
        Entry entry = new Entry();
        entry.setTitle("title");
        entry.setBody("body");
        entryRepository.save(entry);

        Entry entry1 = new Entry();
        entry1.setTitle("Title");
        entry1.setBody("Body");
        entryRepository.save(entry1);

        Entry entry2 = new Entry();
        entry2.setTitle("TITLE");
        entry2.setBody("BODY");
        entryRepository.save(entry2);


        assertEquals(3, entryRepository.count());
        assertEquals("title", entryRepository.findById(1).getTitle());
        assertEquals("body", entryRepository.findById(1).getBody());

        assertEquals(3, entryRepository.count());
        assertEquals("Title", entryRepository.findById(2).getTitle());
        assertEquals("Body", entryRepository.findById(2).getBody());

        assertEquals(3, entryRepository.count());
        assertEquals("TITLE", entryRepository.findById(3).getTitle());
        assertEquals("BODY", entryRepository.findById(3).getBody());

        entryRepository.clear();
        assertEquals(0, entryRepository.count());
    }
}
