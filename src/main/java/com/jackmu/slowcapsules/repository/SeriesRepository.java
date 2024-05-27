package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Page<Series> findByPublishedIsTrueOrderByDatetimeDesc(Pageable pageable);
    Page<Series> findByPenNameIgnoreCaseAndPublishedIsTrue(Pageable pageable, String penName);
    Page<Series> findByPenNameIgnoreCase(Pageable pageable, String penName);
    List<Series> findAllByTagsIsContainingIgnoreCaseAndPublishedIsTrue(String tag);
    @Query(value = "SELECT * FROM Series WHERE (POSITION(LOWER(:penName) in LOWER(pen_name)) > 0 " +
            "OR POSITION(LOWER(:tag) in LOWER(tags)) > 0 OR POSITION(LOWER(:summary) in LOWER(summary)) > 0 " +
            "OR POSITION(LOWER(:title) in LOWER(title)) > 0) AND published = :published",
    nativeQuery = true)
    Page<Series> findAllByKeyword(
            Pageable pageable,
            @Param("penName") String penName,
            @Param("tag") String tag,
            @Param("summary") String summary,
            @Param("title") String title,
            @Param("published") Boolean published
    );
    Series findBySeriesId(Long id);
}
