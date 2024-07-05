package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.config.StripeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static spark.Spark.post;

@Service
public class StripeServiceProd implements  StripeService{
    @Autowired
    StripeConfig stripeConfig;
    private static final String PROCESS_PAYMENT_URL = "https://trtlpost.com/api/payments/";

    public void processPayment(){

    }
}
