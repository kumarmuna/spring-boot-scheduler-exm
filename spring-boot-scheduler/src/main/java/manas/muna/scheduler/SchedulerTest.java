package manas.muna.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTest implements SchedulingConfigurer{
	
	@Scheduled(fixedRate=5000)
    public void printMessage() {
//        System.out.println("I am called by Spring scheduler"+TimeUnit.MINUTES+":"+TimeUnit.SECONDS);
		System.out.println("I am called by Spring scheduler ,"+Thread.currentThread().getName());
    }
	
	/*
	 * in case you have tasks which can take long time to complete, and are frequent, 
	 * you can optionally configure thread-pool with specified pool-size to handle each tasks in separate thread.
	 * Below 2 methods are implemented for threading
	 * */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }
 
    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
