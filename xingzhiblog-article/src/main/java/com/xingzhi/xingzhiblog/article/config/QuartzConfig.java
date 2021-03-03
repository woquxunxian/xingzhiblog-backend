package com.xingzhi.xingzhiblog.article.config;

import com.xingzhi.xingzhiblog.article.quartz.LikeJob;
import com.xingzhi.xingzhiblog.article.quartz.ViewJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: xingzhiblog
 * @description: 定时任务配置
 * @author: 行之
 * @create: 2021-03-02 20:13
 **/
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail likeJobDetail(){
        JobDetail jobDetail = JobBuilder.newJob(LikeJob.class)
                .withIdentity("likeJob","likeJobGroup")
                .storeDurably()
                .build();
        return jobDetail;
    }
    @Bean
    public Trigger likeTrigger(){
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(likeJobDetail())
                .withIdentity("likeTrigger()","likeTriggerGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/15 * * * ? *"))
                .build();
        return trigger;
    }

    @Bean
    public JobDetail viewJobDetail(){
        JobDetail jobDetail = JobBuilder.newJob(ViewJob.class)
                .withIdentity("viewJob","viewJobGroup")
                .storeDurably()
                .build();
        return jobDetail;
    }
    @Bean
    public Trigger viewTrigger(){
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(viewJobDetail())
                .withIdentity("viewTrigger()","viewTriggerGroup")
                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ? *"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * * * ? *"))
                .build();
        return trigger;
    }
}