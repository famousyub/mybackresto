package com.omnia.app.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;

@Component
public class RssFeedView extends AbstractRssFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, 
      Channel feed, HttpServletRequest request) {
        feed.setTitle("Baeldung RSS Feed");
        feed.setDescription("yummy food multi restaurant food  order with qr table");
        feed.setLink("http://localhost:4201/");
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, 
      HttpServletRequest request, HttpServletResponse response) {
        Item entryOne = new Item();
        entryOne.setTitle("Yummy food");
        entryOne.setAuthor("ayoub.smayen@gmail.com");
        entryOne.setLink("http://localhost:4201/");
        entryOne.setPubDate(Date.from(Instant.parse("2017-12-19T00:00:00Z")));
        return Arrays.asList(entryOne);
    }
}