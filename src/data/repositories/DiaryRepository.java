package data.repositories;

import data.models.Diary;

public interface DiaryRepository {
    Diary save(Diary diary);
    Diary findById(int id);
    Iterable<Diary> findAll();
    void delete(Diary diary);
    long count();
    void clear();
}
