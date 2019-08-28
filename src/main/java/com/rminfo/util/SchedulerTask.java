package com.rminfo.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/13
 * Time: 17:41
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@Component
public class SchedulerTask {
    private int count=0;

    /**
     * 每隔6秒打印一次
     */
    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }
}
