package com.nio.webasync.task;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AsyncTask {
    @Async
    public void dealNoReturnTask(){
        log.info("Thread {} deal No Return Task start", Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            throw new NullPointerException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Thread {} deal No Return Task end at {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }

    @Async
    public Future<String> haveReusltReturnTask(){
        try {
//            Thread.sleep(3000);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("thread", Thread.currentThread().getName());
        jsonObject.put("time", System.currentTimeMillis());
        return new AsyncResult<String>(jsonObject.toJSONString());
    }
}
