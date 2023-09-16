package data.repositories;

import data.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository{
    private List<Entry> entries = new ArrayList<>();
    private int count = 0;
    @Override
    public Entry findById(int id) {
        for (Entry entry:entries) {
            if (entry.getId() == id) return entry;
        }
        return null;
    }

    @Override
    public Iterable<Entry> findAll() {
        return entries;
    }

    @Override
    public Entry save(Entry entry) {
        boolean entryDoesNotExist = entry.getId() == 0;
        if (entryDoesNotExist) saveNew(entry);
        else update(entry);
        return null;
    }

    private void update(Entry entry) {
        Entry newEntry = findById(entry.getId());
        newEntry.setEntry(entry);
    }

    private void saveNew(Entry entry) {
        entry.setId(generateId());
        entries.add(entry);
        count();
    }

    private int generateId() {
        return ++count;
    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
    }

    @Override
    public void clear() {
        count -= entries.size();
        entries.removeAll(entries);
    }

    @Override
    public long count() {
        return count;
    }
}
