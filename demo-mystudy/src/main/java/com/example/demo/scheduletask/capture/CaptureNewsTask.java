package com.example.demo.scheduletask.capture;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CaptureNewsTask
 * @Description:    使用爬虫(webMagic) 获取 新闻
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/01/27 14:01
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
@Component
public class CaptureNewsTask implements PageProcessor {

    /**
     * webmagic基本设置
     */
    private Site site = Site.me()
            .setCharset("UTF-8")
            .setTimeOut(10 * 1000)
            //设置下载失败时的重试睡眠时间
            .setRetrySleepTime(2 * 1000)
            //重试次数
            .setRetryTimes(3);

    /**
     * 页面处理
     * @param page  页面信息
     */
    @Override
    public void process(Page page) {
        List<Map<String, String>> allNews = new ArrayList<>();
        Elements allNewsElements = page.getHtml().getDocument().getElementById("content_left").getElementsByClass("result-op c-container xpath-log new-pmd");
        for (int i = 0; i < allNewsElements.size(); i ++) {
            Element element = allNewsElements.get(i);
            Map<String, String> news = new HashMap();
            news.put("title", element.selectFirst("a").text());
            news.put("url", element.selectFirst("a").attr("href"));
            news.put("source", element.getElementsByClass("c-color-gray c-font-normal c-gap-right").text());
            news.put("time", element.getElementsByClass("c-color-gray2 c-font-normal").text());
            news.put("abstract", element.getElementsByClass("c-font-normal c-color-text").text());
            allNews.add(news);
        }
        page.putField("allNews", allNews);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void start(String url) {
        System.out.println(url);
         Spider.create(new CaptureNewsTask())
                .addUrl(url)
                //.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                .addPipeline(new NewsPipeline())
                .run();
    }
}
