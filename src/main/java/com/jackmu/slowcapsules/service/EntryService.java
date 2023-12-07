package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Entry;

import java.util.List;

public interface EntryService {
    Entry saveEntry(Entry entry);
    Entry updateEntry(Entry entry);
    void deleteEntry(Long id);
    List<Entry> fetchEntriesBySeriesId(Long id);
    List<Entry> fetchEntriesByEntryId(Long id);

    Entry fetchFirstEntryBySeriesId(Long id);
}
