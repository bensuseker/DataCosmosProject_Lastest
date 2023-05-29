package com.datacosmos.datacosmosproject.response;

import java.util.List;
import java.util.Map;

public class CrawlerResponse {
    private List<Map<String, String>> data;
    private Map<String, Integer> stats;

    public CrawlerResponse(Map<String, Integer> stats, List<Map<String, String>> data) {
        this.stats = stats;
        this.data = data;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
}
