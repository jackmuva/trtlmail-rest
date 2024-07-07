package com.jackmu.slowcapsules.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;

@Configuration
//@Profile("!local-profile")
public class StripeConfig {
    final Map<String, String> env = System.getenv();
    private String stripeApiKey;
    private String stripeAccountId;

    @Bean
    public StripeConfig getStripeConfig(){
        setStripeApiKey();
        setStripeAccountId();
        return this;
    }

    public void setStripeApiKey(){
        stripeApiKey = env.get("stripe_api_key");
    }
    public void setStripeAccountId(){
        stripeAccountId = env.get("stripe_acct_id");
    }

    public String getStripeApiKey(){
        return stripeApiKey;
    }
    public String getStripeAccountId(){ return stripeAccountId; }
}
