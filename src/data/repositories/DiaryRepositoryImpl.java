package data.repositories;

import data.models.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository{
    private int count = 0;
    private List<Diary> diaries = new ArrayList<>();

    @Override
    public Diary save(Diary diary) {
        boolean diaryDoesNotExist = diary.getId() == 0;
        if (diaryDoesNotExist) saveNew(diary);
        else update(diary);
        return diary;
    }

    private void update(Diary diary) {
        Diary newdiary = findById(diary.getId());
        newdiary.setUsername(diary.getUsername());
    }

    private void saveNew(Diary diary) {
        diary.setId(generateId());
        diaries.add(diary);
        count();
    }

    private int generateId() {
        return ++count;
    }

    @Override
    public Diary findById(int id) {
        for (var diary : diaries){
            if (diary.getId() == id) return diary;
        }
        return null;
    }

    @Override
    public Iterable<Diary> findAll() {
        return diaries;
    }

    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void clear() {
        while (!diaries.isEmpty()) {
            count-= diaries.size();
            diaries.removeAll(diaries);
        }
    }
}
