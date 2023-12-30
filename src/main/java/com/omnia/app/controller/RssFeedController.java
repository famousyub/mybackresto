package com.omnia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.View;

import com.omnia.app.config.RssFeedView;

@RestController
@RequestMapping("/api/feed")
public class RssFeedController {

    @Autowired
    private RssFeedView view;
    
    @GetMapping("/rss")
    public View getFeed() {
        return view;
    }
}