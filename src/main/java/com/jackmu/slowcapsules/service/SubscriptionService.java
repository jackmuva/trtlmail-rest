package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription saveSubscription(Subscription subscription);
    void deleteSubscription(String email, Long seriesId);
    List<Subscription> fetchSubscriptions();
}
