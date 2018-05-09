package com.nio.webasync;

import com.nio.webasync.task.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = WebasyncApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class AsyncTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void test(){
        asyncTask.dealNoReturnTask();
        try {
            log.info("begin to deal other Task!");
            TimeUnit.SECONDS.sleep(10);
//            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        Future<String> future = asyncTask.haveReusltReturnTask();
        log.info("begin to deal other Task!");
        while (true) {
            if(future.isCancelled()){
                log.info("deal async task is Cancelled");
                break;
            }
            if (future.isDone() ) {
                log.info("deal async task is Done");
                log.info("return result is " + future.get());
                break;
            }
            log.info("wait async task to end ...");
            Thread.sleep(1000);
        }
    }
}
