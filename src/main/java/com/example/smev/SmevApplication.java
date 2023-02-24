package com.example.smev;

import com.example.smev.worker.Worker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class SmevApplication {

    private final Worker worker;
    public static void main(String[] args) {
        SpringApplication.run(SmevApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void startWorkerPool(){
        ScheduledThreadPoolExecutor workerPool = new ScheduledThreadPoolExecutor(1);
        workerPool.scheduleWithFixedDelay(worker,0,250, TimeUnit.MILLISECONDS);
        log.info("Worker started");
    }
}
