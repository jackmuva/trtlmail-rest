package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SeriesService {
    Series saveSeries(Series series);

    void deleteSeries(Long id);

    Page<Series> fetchNewest(Pageable pageable);

    Page<Series> fetchByWriter(Pageable pageable, String writer);

    List<Series> fetchByTag(String tag);

    Page<Series> fetchByKeyword(Pageable pageable, String keyword, Boolean published);
    Series fetchBySeriesId(Long id);
}
