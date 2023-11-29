package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Subscription;
import com.jackmu.slowcapsules.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<Subscription> getSubscriptions(){
        return subscriptionService.fetchSubscriptions();
    }

    //TODO: Add endpoint to check if a user is already subscribed to a series

    @PostMapping(value = "/new")
    public Subscription putSubscription(@RequestBody Subscription subscription){
        return subscriptionService.saveSubscription(subscription);
    }

    @DeleteMapping("/cancelSubscription/{email}/{seriesId}")
    public void deleteSubscription(@PathVariable String email, @PathVariable Long seriesId){
        subscriptionService.deleteSubscription(email, seriesId);
    }
}
