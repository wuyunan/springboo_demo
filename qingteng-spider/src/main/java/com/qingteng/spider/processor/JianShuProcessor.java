package com.qingteng.spider.processor;

import com.qingteng.spider.entity.News;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * info:简书首页爬虫
 * Created by shang on 16/9/9.
 */
public class JianShuProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("jianshu.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    ;

    public static final String list = "http://www.jianshu.com";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(list).match()) {
            System.out.println("ssssssss");
            List<Selectable> list=page.getHtml().xpath("//ul[@class='note-list']/li").nodes();
            for (Selectable s : list) {
                String title=s.xpath("//div/a[@class='title']/text()").toString();
                String link=s.xpath("//div/a[@class='title']").links().toString();
                News news=new News();
                news.setTitle(title);
                news.setInfo(title);
                news.setLink(link);
                //news.setSources(new Sources(5));
                System.out.println(news.toString());
                page.putField("news"+title, news);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


}