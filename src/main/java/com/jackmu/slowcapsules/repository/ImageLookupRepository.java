package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.editorjs.ImageLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageLookupRepository extends JpaRepository<ImageLookup, Long> {
    @Modifying
    @Transactional
    void deleteByEntryId(Long entryId);

    @Modifying
    @Transactional
    void deleteByImageFilename(String filename);
    List<ImageLookup> findAllByEntryId(Long entryId);
}
