package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.repository.EntryRepository;
import com.jackmu.slowcapsules.repository.ImageLookupRepository;
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
    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private ImageService imageService;

    public Series saveSeries(Series series){
        return seriesRepository.save(series);
    }

    public void deleteSeries(Long id){
        for(Entry entry : entryRepository.findAllBySeriesId(id)){
            imageService.deleteImagesInEntry(entry.getEntryId());
        }
        entryRepository.deleteBySeriesId(id);
        seriesRepository.deleteById(id);
    }

    @Override
    public void incrementReadersForSeries(Long id) {
        seriesRepository.incrementReaders(id);
    }

    public Page<Series> fetchNewest(Pageable pageable){
        return seriesRepository.findByPublishedIsTrueOrderByDatetimeDesc(pageable);
    }

    public Page<Series> fetchByWriter(Pageable pageable, String penName){
        return seriesRepository.findByPenNameIgnoreCaseOrderByDatetimeDesc(pageable, penName);
    }

    public Page<Series> fetchPublishedByWriter(Pageable pageable, String penName){
        return seriesRepository.findByPenNameIgnoreCaseAndPublishedIsTrueOrderByDatetimeDesc(pageable, penName);
    }

    public List<Series> fetchByTag(String tag){
        return seriesRepository.findAllByTagsIsContainingIgnoreCaseAndPublishedIsTrue(tag);
    }

    public Page<Series> fetchByKeyword(Pageable pageable, String keyword, Boolean published){
        return seriesRepository.findAllByKeyword(pageable, keyword, keyword, keyword, keyword, published);
    }

    public Series fetchBySeriesId(Long id){
        return seriesRepository.findBySeriesId(id);
    }

}
