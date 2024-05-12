package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new")
    public ResponseEntity postSeries(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Series series){
        if(series.getEmail().equals(userDetails.getUsername())){
            seriesService.saveSeries(series);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update")
    public ResponseEntity<Series> putSeries(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Series series){
        if(series.getEmail().equals(userDetails.getUsername())){
            return new ResponseEntity(seriesService.saveSeries(series), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{seriesId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteSeries(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long seriesId){
        if(seriesService.fetchBySeriesId(seriesId).getEmail().equals(userDetails.getUsername())){
            seriesService.deleteSeries(seriesId);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getNewest")
    public List<Series> getNewestSeries(@RequestParam(defaultValue = "0") int page){
        try{
            Pageable paging = PageRequest.of(page, 10);
            Page<Series> pageSeries = seriesService.fetchNewest(paging);
            return pageSeries.getContent();
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/writer/{writer}")
    public List<Series> getPublishedSeriesByWriter(@RequestParam(defaultValue = "0") int page,
                                                   @PathVariable String writer){
        Pageable paging = PageRequest.of(page, 1);
        Page<Series> pageSeries = seriesService.fetchByWriter(paging, writer);
        return pageSeries.getContent();
    }

    @GetMapping("/tag/{tag}")
    public List<Series> getSeriesByTag(@PathVariable String tag){
        return seriesService.fetchByTag(tag);
    }

    @GetMapping("/search/{keyword}")
    public List<Series> searchPublishedSeries(@PathVariable String keyword, @RequestParam(defaultValue = "0") int page){
        try{
            Pageable paging = PageRequest.of(page, 10);
            Page<Series> pageSeries = seriesService.fetchByKeyword(paging, keyword, Boolean.TRUE);
            return pageSeries.getContent();
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/{id}")
    public Series getSeriesById(@PathVariable Long id){
        return seriesService.fetchBySeriesId(id);
    }

}
