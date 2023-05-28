package com.datacosmos.datacosmosproject.controller;

import com.datacosmos.datacosmosproject.crawler.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crawl")
public class CrawlerController {
    private final Crawler crawler;

    @Autowired
    public CrawlerController(Crawler crawler) {
        this.crawler = crawler;
    }

    @GetMapping("/{searchTerm}")
    public List<Map<String, String>> crawlWebsites(@PathVariable("searchTerm") String searchTerm) throws IOException {
        // start the crawler
        System.out.println("Calling crawler!!!");
        List<Map<String, String>> crawlerOutput = crawler.crawlKaggle(searchTerm);
//        List<Map<String, String>> crawlerOutput = crawler.crawlUciMlRepository(searchTerm);
//        List<Map<String, String>> crawlerOutput = crawler.crawlCernOpenDataPortal(searchTerm);
        System.out.println("crawlerOutput: " + crawlerOutput);
        return crawlerOutput;
    }
}
