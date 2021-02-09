package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.scheduletask.capture.CaptureNewsTask;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName: WebCaptureTest
 * @Description:    爬虫 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/01/27 17:27
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class WebCaptureTest {
    @Autowired
    private CaptureNewsTask captureNewsTask;

    @Test
    public void webMagicBaiduTest() {
        String url = "http://www.baidu.com/s?ie=utf-8&medium=0&rtt=1&bsst=1&rsv_dl=news_t_sk&cl=2&wd=特斯拉";
        captureNewsTask.start(url);

    }

    @Test
    public void jsoupBaiduTest() throws Exception {
        String url = "https://www.baidu.com/s?ie=utf-8&medium=0&rtt=1&bsst=1&rsv_dl=news_t_sk&cl=2&wd=特斯拉";
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();//模拟火狐浏览器
        System.out.println(doc.body());
    }

    @Test
    public void jsoupSogouWeixinTest() throws Exception {
        String baseUrl = "http://weixin.sogou.com";
        String url = "https://weixin.sogou.com/weixin?type=2&s_from=input&ie=utf8&page=1&query=特斯拉";
        Document document = Jsoup.connect(url).get();//模拟火狐浏览器
        Elements allNewsLi = document.getElementById("main").getElementsByClass("news-list").select("li");
        System.out.println(allNewsLi.size());
        for (int i = 0; i < allNewsLi.size(); i ++) {
            Element element = allNewsLi.get(i);
            System.out.println(element.selectFirst("h3").selectFirst("a").text());
            System.out.println(element.selectFirst("h3").selectFirst("a").attr("href"));
            System.out.println(element.selectFirst("p").text());
            System.out.println(element.getElementsByClass("account").text());
            String timeScript = element.getElementsByClass("s-p").select("span").select("script").get(0).childNode(0).toString();
            System.out.println(timeScript);
            long timeMillis = Long.parseLong(timeScript.substring(timeScript.indexOf("'") + 1, timeScript.lastIndexOf("'"))) * 1000;
            System.out.println(timeMillis);
            System.out.println(DateFormatUtils.format(timeMillis, "yyyy-MM-dd"));
            System.out.println("------------------------------");
        }
    }
}
