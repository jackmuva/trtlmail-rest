package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.config.StripeConfig;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Profile("!local-profile")
public class StripeServiceProd implements  StripeService{
    @Autowired
    StripeConfig stripeConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(StripeServiceProd.class);

    @Override
    public Boolean sessionPaid(String sessionId) throws StripeException {
        LOGGER.info(sessionId);
        LOGGER.info(stripeConfig.getStripeApiKey());
        Stripe.apiKey = stripeConfig.getStripeApiKey();
        Session session = Session.retrieve(sessionId);
        LOGGER.info(session.getClientReferenceId());
        LOGGER.info(session.getStatus());
        LOGGER.info(session.getCustomer());
        return Boolean.TRUE;
    }
}
