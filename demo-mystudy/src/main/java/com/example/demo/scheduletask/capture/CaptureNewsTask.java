package com.example.demo.scheduletask.capture;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.swing.text.html.CSS;
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
        //webMagic里主要使用了三种抽取技术：XPath、正则表达式和CSS选择器。另外，对于JSON格式的内容，可使用JsonPath进行解析。
        //CSS 选择器
        //List<Map<String, String>> allNews = getAllNewsByCss(page);

        //xpath
        List<Map<String, String>> allNews = getAllNewsByPath(page);
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

    /**
     * 使用 css选择器 获取 新闻
     * @param page  页面信息
     * @return  allNews
     */
    public List<Map<String, String>> getAllNewsByCss(Page page) {
        List<Map<String, String>> allNews = new ArrayList<>();
        Elements allNewsElements = page.getHtml().getDocument().getElementById("content_left").getElementsByClass("result-op c-container xpath-log new-pmd");
        for (int i = 0; i < allNewsElements.size(); i ++) {
            Element element = allNewsElements.get(i);
            Map<String, String> news = new HashMap();
            //css 选择器
            //news.put("title", element.selectFirst("a").text());
            //news.put("url", element.selectFirst("a").attr("href"));
            news.put("title", element.getElementsByClass("news-title_1YtI1").select("a").text());
            news.put("url", element.getElementsByClass("news-title_1YtI1").select("a").attr("href"));
            news.put("source", element.getElementsByClass("c-color-gray c-font-normal c-gap-right").text());
            news.put("time", element.getElementsByClass("c-color-gray2 c-font-normal").text());
            news.put("abstract", element.getElementsByClass("c-font-normal c-color-text").text());
            allNews.add(news);
        }
        return allNews;
    }

    /**
     * 使用 xpath 获取 新闻
     * @param page  页面信息
     * @return  allNews
     */
    private List<Map<String, String>> getAllNewsByPath(Page page) {
        List<Map<String, String>> allNews = new ArrayList<>();
        List<Selectable> allNewsSelectable = page.getHtml().xpath("//div[@class='result-op c-container xpath-log new-pmd']").nodes();
        for (Selectable selectable : allNewsSelectable) {
            Map<String, String> news = new HashMap();
            //xpath
            news.put("title", selectable.xpath("h3[@class='news-title_1YtI1']/a/text()").get());
            news.put("url", selectable.xpath("h3[@class='news-title_1YtI1']/a/@href").get());
            news.put("source", selectable.xpath("div[@class='c-span-last c-span9']/div/span[1]/text()").get());
            news.put("time", selectable.xpath("div[@class='c-span-last c-span9']/div/span[2]/text()").get());
            news.put("abstract", selectable.xpath("div[@class='c-span-last c-span9']/span/text()").get());
            allNews.add(news);
        }
        System.out.println(allNews.size());
        return allNews;
    }
}
