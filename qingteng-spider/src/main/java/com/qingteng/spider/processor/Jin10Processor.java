package com.qingteng.spider.processor;

import com.qingteng.spider.entity.YieldCurve;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;

/**
 * info:jin10首页爬虫
 * Created by wu on 19/10/12.
 */
public class Jin10Processor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("cn.investing.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    ;

    public static final String list = "https://cn.investing.com/rates-bonds/u.s.-10-year-bond-yield";

    @Override
    public void process(Page page) {

        if (page.getUrl().regex(list).match()) {

            String  list=page.getHtml().xpath("//span[@id='last_last']/text()").toString();

            //for (Selectable s : list) {
                //System.out.println(s.toString());

                //String title=s.xpath("//div/h3/text()").toString();
               // String value=s.xpath("//div/div/text()]").toString();
                YieldCurve yieldCurve=new YieldCurve();
                Date createData = new Date();
                yieldCurve.setCreateDate(createData);
                yieldCurve.setName("美国十年期国债收益率\n");
                yieldCurve.setTerm(10);
                yieldCurve.setValue(Double.valueOf(list));
                //news.setSources(new Sources(5));
                System.out.println(yieldCurve.toString());
                page.putField("yieldCurve"+createData.toString(), yieldCurve);
            //}
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


}