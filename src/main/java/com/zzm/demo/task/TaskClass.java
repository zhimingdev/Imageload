package com.zzm.demo.task;

import org.LatencyUtils.TimeServices;

import javax.xml.ws.Service;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskClass {


    public static void ma
    in(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(()-> System.out.println("时间 ==> "+new Date()),0,3, TimeUnit.SECONDS);
    }
}
