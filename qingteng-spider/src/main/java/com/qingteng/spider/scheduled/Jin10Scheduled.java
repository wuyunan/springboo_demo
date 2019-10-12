package com.qingteng.spider.scheduled;


import com.qingteng.spider.pipeline.Jin10Pipeline;
import com.qingteng.spider.processor.Jin10Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;


/**
 * info:新闻定时任务
 * Created by shang on 16/8/22.
 */
@Component
public class Jin10Scheduled {

    @Autowired
    private Jin10Pipeline jin10Pipeline;

    /**
     * 简书
     */
    @Scheduled(cron = "0/10 * *  * * ? ")//从0点开始,每2个小时执行一次
    public void jianShuScheduled() {
        System.out.println("----开始执行金十数据定时任务");
        Spider spider = Spider.create(new Jin10Processor());
        spider.addUrl("https://cn.investing.com/rates-bonds/u.s.-10-year-bond-yield");
        spider.addPipeline(jin10Pipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }

}