package data.repositories;

import data.models.Entry;

public interface EntryRepository {
    Entry findById(int id);
    Iterable<Entry> findAll();
    Entry save(Entry entry);
    void delete(Entry entry);
    void clear();
    long count();


}
