package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.model.Subscription;
import com.jackmu.slowcapsules.service.SeriesService;
import com.jackmu.slowcapsules.service.SubscriptionService;
import com.jackmu.slowcapsules.util.GenericHttpResponse;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private SeriesService seriesService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<Subscription> getSubscriptions(){
        return subscriptionService.fetchSubscriptions();
    }

    //TODO: Add endpoint to check if a user is already subscribed to a series

    @PostMapping(value = "/new")
    public GenericHttpResponse postSubscription(@RequestBody Subscription subscription){
        Series series = seriesService.fetchBySeriesId(subscription.getSeriesId());
        if(series.getMaxCurrentReaders() <= series.getNumCurrentReaders()){
            return new GenericHttpResponse(HttpStatus.SC_LOCKED, "This series can only have " +
                    series.getMaxCurrentReaders() + " readers at a time. Check at a later time or ask author to " +
                    "increase allowable reader count");
        } else {
            seriesService.incrementReadersForSeries(subscription.getSeriesId());
            subscriptionService.saveSubscription(subscription);
            return new GenericHttpResponse(HttpStatus.SC_OK, "Subscribed! You will receive the first email entry tomorrow");
        }
    }

    @DeleteMapping("/cancelSubscription/{email}/{seriesId}")
    public void deleteSubscription(@PathVariable String email, @PathVariable Long seriesId){
        subscriptionService.deleteSubscription(email, seriesId);
    }
}
