package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.config.StripeConfig;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static spark.Spark.port;
import static spark.Spark.post;

@Service
//@Profile("!local-profile")
public class StripeServiceProd implements  StripeService{
    @Autowired
    StripeConfig stripeConfig;
//    private static final String SITE_URL = "https://trtlpost.com/payment";
    private static final String SITE_URL = "http://localhost:3000/payment";

    public void processPayment(){
        port(4242);

        // This is your test secret API key.
        Stripe.apiKey = stripeConfig.getStripeApiKey();

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
