package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService{
    @Autowired
    private SeriesRepository seriesRepository;

    public Series saveSeries(Series series){
        return seriesRepository.save(series);
    }

    public void deleteSeries(Long id){
        seriesRepository.deleteById(id);
    }

    public Page<Series> fetchNewest(Pageable pageable){
        return seriesRepository.findByPublishedIsTrueOrderByDatetimeDesc(pageable);
    }

    public List<Series> fetchByWriter(String penName){
        return seriesRepository.findByPenNameIgnoreCase(penName);
    }

    public List<Series> fetchByTag(String tag){
        return seriesRepository.findAllByTagsIsContainingIgnoreCaseAndPublishedIsTrue(tag);
    }

    public Page<Series> fetchByKeyword(Pageable pageable, String keyword, Boolean published){
        return seriesRepository.findAllByKeyword(pageable, keyword, keyword, keyword, keyword, published);
    }

    public List<Series> fetchBySeriesId(Long id){
        return seriesRepository.findBySeriesId(id);
    }

}
