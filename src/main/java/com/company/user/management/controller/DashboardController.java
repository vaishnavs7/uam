package com.company.user.management.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DashboardController {

    @GetMapping("/dashboard")
    public String getDashboard(@RequestHeader Authentication authentication) {
        return "This is a secure dashboard";
    }
}
