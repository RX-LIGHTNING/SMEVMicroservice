package com.example.smev.event;

import com.example.smev.worker.Worker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class WorkerStartEvent {

    private final Worker worker;

    @EventListener(ApplicationReadyEvent.class)
    public void startWorkerPool(){
        ScheduledThreadPoolExecutor workerPool = new ScheduledThreadPoolExecutor(1);
        workerPool.scheduleWithFixedDelay(worker,0,150, TimeUnit.MILLISECONDS);
        log.info("Worker started");
    }
}
