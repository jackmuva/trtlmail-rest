package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO: Decide on whether/how to use join columns
@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Modifying
    @Transactional
    void deleteByEntryId(Long id);

    @Modifying
    @Transactional
    void deleteBySeriesId(Long id);
    Page<Entry> findAllBySeriesId(Pageable pageable, Long seriesId);
    List<Entry> findAllBySeriesId(Long seriesId);
    List<Entry> findByEntryId(Long id);

    Entry findBySeriesIdAndOrderNum(Long seriesId, Integer orderNum);
}
