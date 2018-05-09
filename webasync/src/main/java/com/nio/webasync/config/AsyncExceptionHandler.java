package com.nio.webasync.config;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.info("Async method: {} has uncaught exception,params:{}", method.getName());

        if (throwable instanceof AsyncException) {
            AsyncException asyncException = (AsyncException) throwable;
            log.info("asyncException:{}",asyncException.getErrorMessage());
        }

        log.info("Exception :");
        throwable.printStackTrace();
    }
}
