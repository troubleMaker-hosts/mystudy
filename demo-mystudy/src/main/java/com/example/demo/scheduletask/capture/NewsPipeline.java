package com.example.demo.scheduletask.capture;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: NewsPipeline
 * @Description:    news 爬取结果(ResultItems) 处理
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/01/27 16:35
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
public class NewsPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Map<String, String>> allNews = resultItems.get("allNews");
        allNews.forEach(newsMap -> {
            for (Map.Entry<String, String> entry : newsMap.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            System.out.println("----------------------------------------");
        });
    }
}
