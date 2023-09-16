import data.models.Diary;
import data.repositories.DiaryRepository;
import data.repositories.DiaryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryRepositoryTest {
    private DiaryRepository diaryRepository;
    @BeforeEach
    public void startWithThis(){
        diaryRepository = new DiaryRepositoryImpl();
    }
    @Test
    public void testThatOneDiaryCanBeSaved(){
        Diary diary = new Diary();
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testThatOneDiaryCanBeSaved_AndCanBeFound(){
        Diary diary = new Diary();
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
        assertEquals(diary, diaryRepository.findById(1));
    }

    @Test
    public void testThatOneDiaryCanBeSaved_AndCanBeUpdated(){
        Diary diary = new Diary();
        diary.setUsername("esther");
        diaryRepository.save(diary);

        Diary updatedDiary = new Diary();
        updatedDiary.setId(1);
        updatedDiary.setUsername("adunni");
        diaryRepository.save(updatedDiary);

        assertEquals("adunni", diaryRepository.findById(1).getUsername());
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testThatCanPrintAllTheDiaries_ThatWereSaved(){
        Diary diary = new Diary();
        diary.setUsername("esther");
        diaryRepository.save(diary);

        Diary diary1 = new Diary();
        diary1.setUsername("adunni");
        diaryRepository.save(diary1);

        Diary diary2 = new Diary();
        diary2.setUsername("deborah");
        diaryRepository.save(diary2);

        assertEquals("esther", diaryRepository.findById(1).getUsername());
        assertEquals("adunni", diaryRepository.findById(2).getUsername());
        assertEquals("deborah", diaryRepository.findById(3).getUsername());
        assertEquals(3, diaryRepository.count());

        Iterable<Diary> expectedDiaries = List.of(new Diary[]{diary, diary1, diary2});

        assertEquals(expectedDiaries, diaryRepository.findAll());
    }

    @Test
    public void testThatCanDeleteDiary_ThatWereSaved(){
        Diary diary = new Diary();
        diary.setUsername("esther");
        diaryRepository.save(diary);

        Diary diary1 = new Diary();
        diary1.setUsername("adunni");
        diaryRepository.save(diary1);

        Diary diary2 = new Diary();
        diary2.setUsername("deborah");
        diaryRepository.save(diary2);

        assertEquals("esther", diaryRepository.findById(1).getUsername());
        assertEquals("adunni", diaryRepository.findById(2).getUsername());
        assertEquals("deborah", diaryRepository.findById(3).getUsername());
        assertEquals(3, diaryRepository.count());

        diaryRepository.delete(diary2);
        assertNull(diaryRepository.findById(3));
    }

    @Test
    public void testThatCanTheListOfDiaries_ThatWereSaved_CanBeCleared(){
        Diary diary = new Diary();
        diary.setUsername("esther");
        diaryRepository.save(diary);

        Diary diary1 = new Diary();
        diary1.setUsername("adunni");
        diaryRepository.save(diary1);

        Diary diary2 = new Diary();
        diary2.setUsername("deborah");
        diaryRepository.save(diary2);

        assertEquals("esther", diaryRepository.findById(1).getUsername());
        assertEquals("adunni", diaryRepository.findById(2).getUsername());
        assertEquals("deborah", diaryRepository.findById(3).getUsername());
        assertEquals(3, diaryRepository.count());

        diaryRepository.clear();
        assertEquals(0, diaryRepository.count());
    }

}
