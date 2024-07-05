package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.util.GenericHttpResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payments")
public class StripeController {
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/increaseReaderCount")
    public GenericHttpResponse increaseReaderCount(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long seriesId){
        return null;
    }
}
