package com.example.demo.service;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

public class WebCrawlerService extends WebCrawler {

    @Override
    public void visit(Page page) {
        page.getWebURL().getURL();
    }
}
