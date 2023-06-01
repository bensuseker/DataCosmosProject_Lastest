package com.datacosmos.datacosmosproject.controller;

import com.datacosmos.datacosmosproject.response.CrawlerResponse;
import com.datacosmos.datacosmosproject.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crawl")
public class CrawlerController {
    private final CrawlerService crawlerService;

    @Autowired
    public CrawlerController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @GetMapping("/{searchTerm}")
    public CrawlerResponse crawlWebsites(@PathVariable("searchTerm") String searchTerm) {

        System.out.println("Calling crawlers!!!");

        // start web driver
        crawlerService.setupWebDriver();

        // Crawling process
        List<Map<String, String>> kaggleResults = crawlerService.crawlKaggle(searchTerm);
        List<Map<String, String>> uciResults = crawlerService.crawlUciMlRepository(searchTerm);
        List<Map<String, String>> cernResults = crawlerService.crawlCernOpenDataPortal(searchTerm);

        List<Map<String, String>> crawlerOutput = new ArrayList<>();

        // Prepare datasets list for json response
        crawlerOutput.addAll(kaggleResults);
        crawlerOutput.addAll(uciResults);
        crawlerOutput.addAll(cernResults);

        // Prepare stats for json response
        Map<String, Integer> stats = new HashMap<>();
        stats.put("kaggle", kaggleResults.size());
        stats.put("uci", uciResults.size());
        stats.put("cern", cernResults.size());
        stats.put("total", crawlerOutput.size());
        //        System.out.println("crawlerOutput: " + crawlerOutput);

        // close web driver
        crawlerService.quitWebDriver();

        return new CrawlerResponse(stats, crawlerOutput);
    }


}
