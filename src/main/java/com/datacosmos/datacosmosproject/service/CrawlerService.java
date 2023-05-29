package com.datacosmos.datacosmosproject.service;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawlerService {
    private final ResourceLoader resourceLoader;
    private final String driverPath;
    private ChromeDriver driver;

    public CrawlerService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.driverPath = getChromeDriverPath();
    }

    public void setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless=new");
        this.driver = new ChromeDriver(options);
    }

    public void quitWebDriver() {
        driver.quit();
    }

    private String getChromeDriverPath() {
        try {
            Resource resource = resourceLoader.getResource("classpath:webdriver/chromedriver.exe");
            String chromedriverPath = resource.getFile().getAbsolutePath();
//            System.out.println("chrome driver path: " + chromedriverPath);
            return chromedriverPath;
        } catch (IOException e) {
            // Handle the exception
            return "getChromeDriverPath: Could not get chrome driver path!";
        }
    }

    public List<Map<String, String>> crawlKaggle(String searchTerm) {
        // This is the list, where we will store our results, and return in the end.
        List<Map<String, String>> datasetList = new ArrayList<>();

        // This variable tells the crawler how many pages we want to try
        int pageCount = 5;

        String url = "https://www.kaggle.com/datasets?search=" + searchTerm;
        driver.get(url);

//        System.out.println(driver.getPageSource());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Loop for pages
        for (int i = 1; i <= pageCount; i++) {
            System.out.println("\nPage: " + i);

            // Check if there are no results
            // This div will help us figure out if datasets for our keyword exist
            WebElement h2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#site-content > div.sc-dQelHR.iMCzUG > div > div > div > div.sc-esSOno.cxfNYz > h2")));
            if (h2.getText().equals("Datasets")) {
                System.out.println("crawlKaggle: No results found for your search query.");
                return datasetList;
            }

            // Do this if we have results for our keyword
            else {
                // Wait until the div we want is populated, and the dataset's links are present
                By selector = By.cssSelector("ul.km-list--three-line > li[role='listitem'] > div > a:first-child");
                wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

                List<WebElement> datasets = driver.findElements(By.cssSelector("ul.km-list--three-line li[role='listitem']"));
                for (WebElement dataset : datasets) {
                    String datasetName = dataset.getAttribute("aria-label");
                    String datasetLink = dataset.findElement(By.tagName("a")).getAttribute("href");

                    System.out.println(datasetName + ": " + datasetLink);

                    // This is a hashmap to map each dataset's name with its key
                    Map<String, String> datasetMap = new HashMap<>();
                    datasetMap.put("name", datasetName);
                    datasetMap.put("url", datasetLink);

                    // Add it into our final list
                    datasetList.add(datasetMap);

                }

                // Check for last iteration
                if (i < pageCount) {
                    // Go the next page
                    // using css selector, I'm getting the div responsible for page navigation,
                    // then I'm getting the last <i> tag within that div.
                    // then, I'm simply clicking it using javascript.
                    WebElement nextPage = driver.findElement(By.cssSelector("div[aria-label='pagination navigation'] > i:last-of-type"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextPage);
                }
            }
        }

        return datasetList;
    }

    public List<Map<String, String>> crawlUciMlRepository(String searchTerm) {
        /*
        Approach:
        1) We will simply load the entire UciMlRepository datasets page. It has 622 datasets.
        2) We use the keyword to filter through those datasets, using xpath.
        3) We return the matched results.

        The following CSS Selector and XPath expression help us narrow down the HTML element that contains the data
        we want.
        CSS Selector: "table:nth-of-type(2) td:nth-of-type(2) table:nth-of-type(2)"
        Xpath       : "//table[2]//td[2]//table[2]"

        Both of the above expressions grab the second table on the page, then within that table,
        grab the second td element and within that td element,
        grab the second table.

        More CSS Selector/xpath notes, it is safe to ignore these comments.

        Performing keyword search on the browser the same way we do with CTRL+F, but using xpath:

        XPath expressions:
        Exact match:
        //table[2]//td[2]//table[2]//tr//td[1]//p[@class='normal'][1]//a[text()='searchTerm']

        Case-sensitive search (will only match Cancer with the capital C)
        //table[2]//td[2]//table[2]//tr//td[1]//p[@class='normal'][1]//a[contains(.,'Cancer')]

        Case-insensitive search (will match any of cancer/Cancer/CANCER, etc)
        //table[2]//td[2]//table[2]//tr//td[1]//p[@class='normal'][1]//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'cancer')]
         */

        // This is the list, where we will store our results, and return in the end.
        List<Map<String, String>> datasetList = new ArrayList<>();

        String url = "https://archive.ics.uci.edu/ml/datasets.php";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        By selector = By.cssSelector("table:nth-of-type(2) td:nth-of-type(2) table:nth-of-type(2) tr td:nth-of-type(1) p.normal:nth-of-type(1) a");
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        // Note: I wanted to use a CSS Selector for this,
        // but the CSS Selector ':contains' function doesn't seem to be working in the chrome driver for me.
        // So, I used xpath.
        List<WebElement> linkTags = table.findElements(By.xpath("//table[2]//td[2]//table[2]//tr//td[1]//p[@class='normal'][1]//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + searchTerm.toLowerCase() + "')]"));

        // Loop over the matched datasets and extract the information we need
        for (WebElement linkTag : linkTags) {
            String datasetName = linkTag.getText();
            String datasetLink = linkTag.getAttribute("href");
            System.out.println(datasetName + ": " + datasetLink);

            // This is a hashmap to map each dataset's name with its key
            Map<String, String> datasetMap = new HashMap<>();
            datasetMap.put("name", datasetName);
            datasetMap.put("url", datasetLink);

            // Add it into our final list
            datasetList.add(datasetMap);
        }

        if (datasetList.size() == 0) {
            System.out.println("crawlUciMlRepository: No results found for your search query.");
        }

        return datasetList;
    }

    public List<Map<String, String>> crawlCernOpenDataPortal(String searchTerm) {
        // This is the list, where we will store our results, and return in the end.
        List<Map<String, String>> datasetList = new ArrayList<>();

        // Note: if you want to modify how many datasets are returned, you can change the 'size=20' GET query parameter
        int urlSizeParameter = 100;
        String url = "https://opendata.cern.ch/search?page=1&size=" + urlSizeParameter +
                "&display=detailed&type=Dataset&q=" + searchTerm;
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        /*
        CSS Selector magic:
        div.result.ng-scope div.card div.card-body:not([class*='card-body-condensed'])
        This selects the div which contains our datasets.

        To get the dataset name, we can append:
        p.title.ng-binding

        To get the <a> tag with the dataset link within this div, we can append:
        a.make-parent-div-clickable

        To check if a tags are present:
        div.result.ng-scope div.card div.card-body:not([class*='card-body-condensed']) a.make-parent-div-clickable
         */

        // First we should check if there are search results, if there are no results, then we don't need to wait.
        By selector = By.cssSelector("div.invenio-search-results ng-pluralize");
        WebElement searchResultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        if (searchResultElement.getText().equals("No results.")) {
            System.out.println("crawlCernOpenDataPortal: No results found for your search query.");
        }

        // Do this, when we have datasets for the searched keyword
        else {
            // Wait until the dataset divs show up (with the <a> tags!)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.result.ng-scope div.card div.card-body:not([class*='card-body-condensed'])")));

            // Put them in a list
            List<WebElement> datasetDivs = driver.findElements(By.cssSelector("div.result.ng-scope div.card div.card-body:not([class*='card-body-condensed'])"));

            // Loop over datasetDivs and extract the information we need
            for (WebElement datasetDiv : datasetDivs) {
                String datasetName = datasetDiv.findElement(By.cssSelector("p.title.ng-binding")).getText();
                String datasetLink = datasetDiv.findElement(By.cssSelector("a.make-parent-div-clickable")).getAttribute("href");
                System.out.println(datasetName + ": " + datasetLink);

                // This is a hashmap to map each dataset's name with its key
                Map<String, String> datasetMap = new HashMap<>();
                datasetMap.put("name", datasetName);
                datasetMap.put("url", datasetLink);

                // Add it into our final list
                datasetList.add(datasetMap);
            }
        }

        return datasetList;
    }
}
