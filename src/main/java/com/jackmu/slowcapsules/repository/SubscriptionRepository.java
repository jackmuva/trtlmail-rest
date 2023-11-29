package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Modifying
    @Transactional
    void deleteBySubscriberEmailAndSeriesId(String email, Long seriesId);
}