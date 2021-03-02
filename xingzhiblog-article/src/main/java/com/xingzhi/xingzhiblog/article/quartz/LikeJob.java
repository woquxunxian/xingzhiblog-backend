package com.xingzhi.xingzhiblog.article.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @program: xingzhiblog
 * @description: 点赞状态任务
 * @author: 行之
 * @create: 2021-03-02 20:14
 **/
public class LikeJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("666");
    }
}
