package com.jackmu.slowcapsules.service;

import com.google.gson.Gson;
import com.jackmu.slowcapsules.config.StripeConfig;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.post;
import static spark.Spark.get;

@Service
public class StripeServiceProd implements  StripeService{
    @Autowired
    StripeConfig stripeConfig;
//    private static final String SITE_URL = "https://trtlpost.com/payment/confirmation";
    private static final String SITE_URL = "http://localhost:3000/payment/confirmation";

    public void processPayment(){
        post("/create-checkout-session", (request, response) -> {
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl(SITE_URL + "?success=true")
                            .setCancelUrl(SITE_URL + "?canceled=true")
                            .setAutomaticTax(
                                    SessionCreateParams.AutomaticTax.builder()
                                            .setEnabled(true)
                                            .build())
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                            .setPrice("price_1PZPhRRpkmzU17K3kSMzxazv")
                                            .build())
                            .build();
            Session session = Session.create(params);

            response.redirect(session.getUrl(), 303);
            return "";
        });
    }
}
