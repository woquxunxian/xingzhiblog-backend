/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xingzhi.xingzhiblog.admin.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingzhi.xingzhiblog.admin.common.utils.PageUtils;
import com.xingzhi.xingzhiblog.admin.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
