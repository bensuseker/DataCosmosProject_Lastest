package com.datacosmos.datacosmosproject.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Crawler {
//    public String crawl(String searchTerm) throws IOException {
//        StringBuilder stringBuilder = new StringBuilder();
//        String url = "https://www.kaggle.com/datasets?search=" + searchTerm;
//        System.out.println("Url formed: " + url);
//        Document doc = Jsoup.connect(url).get();
//        System.out.println(doc);
//        Elements datasetList = doc.select("div.sc-kQZgv div.dbpaFS");
//        System.out.println("datasetList: " + datasetList);
//        for (Element list : datasetList) {
//            String title = list.attr("title");
//            String href = list.absUrl("href");
//            stringBuilder.append(title).append(": ").append(href).append("\n");
//            System.out.println(list.attr("title") + ": " + list.absUrl("href"));
//        }
//        String result = stringBuilder.toString();
//        return result;
//    }
//
//    @SneakyThrows
//    public String crawl2(String searchTerm) {
//        try (final WebClient webClient = new WebClient()) {
//            String url = "https://www.kaggle.com/datasets?search=" + searchTerm;
//            final HtmlPage page = webClient.getPage(url);
//            System.out.println(page);
//
//            //get list of all divs
////            final DomNodeList<DomNode> divs = page.querySelectorAll("div");
////            for (DomNode div : divs) {
////            ....
////            }
//
//            //get div which has the id 'breadcrumbs'
////            final DomNode div = page.querySelector("div#breadcrumbs");
//        }
//        return "lala";
//    }

    public List<Map<String, String>> crawlKaggle(String searchTerm) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\arkha\\WebDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        // This is the list, where we will store our results, and return in the end.
        List<Map<String, String>> datasetList = new ArrayList<>();

        String url = "https://www.kaggle.com/datasets?search=" + searchTerm;
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Loop for pages
        for (int i = 1; i <= 5; i++) {
            System.out.println("\nPage: " + i);

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

            // Go the next page
            // using css selector, I'm getting the div responsible for page navigation,
            // then I'm getting the last <i> tag within that div.
            // then, I'm simply clicking it using javascript.
            WebElement nextPage = driver.findElement(By.cssSelector("div[aria-label='pagination navigation'] > i:last-of-type"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextPage);
        }

        //        driver.quit();
        return datasetList;
    }

    public List<Map<String, String>> crawlUciMlRepository(String searchTerm) {
        /*
        Approach:
        1) We will simply load the entire UciMlRepository datasets page. It has 622 datasets.
        2) We use the keyword to filter through those datasets, using xpath.
        3) We return the matched results.
        TODO: Set a limit on how many you return

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

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\arkha\\WebDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        // This is the list, where we will store our results, and return in the end.
        List<Map<String, String>> datasetList = new ArrayList<>();

        String url = "https://archive.ics.uci.edu/ml/datasets.php";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        By selector = By.cssSelector("table:nth-of-type(2) td:nth-of-type(2) table:nth-of-type(2)");
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

        return datasetList;
    }

    public List<Map<String, String>> crawlCernOpenDataPortal(String searchTerm) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\arkha\\WebDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        // This is the list, where we will store our results, and return in the end.
        List<Map<String, String>> datasetList = new ArrayList<>();

        String url = "https://opendata.cern.ch/search?page=1&size=20&display=detailed&type=Dataset&q=" + searchTerm;
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
         */

        // First we should check if there are search results, if there are no results, then we don't need to wait.
        By selector = By.cssSelector("div.invenio-search-results ng-pluralize");
        WebElement searchResultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        if (searchResultElement.getText().equals("No results.")) {
            System.out.println("crawlCernOpenDataPortal: No results found for your search query.");
        }

        // Do this, when we have datasets for the searched keyword
        else {
            // Wait until the dataset divs show up
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
