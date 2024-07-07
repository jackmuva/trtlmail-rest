package com.jackmu.slowcapsules.service;

import com.stripe.exception.StripeException;

public interface StripeService {
    Boolean sessionPaid(String sessionId) throws StripeException;
}
