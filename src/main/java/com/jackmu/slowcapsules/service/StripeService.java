package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Series;
import com.stripe.exception.StripeException;

public interface StripeService {
    Series sessionPaid(String sessionId) throws StripeException;
}
