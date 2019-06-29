package com.zzm.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTask {

    private final static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 定时任务 ,每5秒打印一次当期时间
     */
    @Scheduled(fixedRate = 50000)
    public void showTest() {
        System.out.println("当期时间 ==> "+dataFormat.format(new Date()));
    }

    /**
     * 每天下午5点输出语句
     */
    @Scheduled(cron = "0 0 17 * * ?")
    public void showTest1() {
        System.out.println("当前时间 ==> 这是一个定时输出语句");
    }

}
