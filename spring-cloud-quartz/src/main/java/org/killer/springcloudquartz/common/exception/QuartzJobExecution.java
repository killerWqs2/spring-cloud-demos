package org.killer.springcloudquartz.common.exception;

import org.killer.springcloudquartz.modules.task.entity.SysJob;
import org.killer.springcloudquartz.utils.AbstractQuartzJob;
import org.killer.springcloudquartz.utils.JobInvokeUtil;
import org.quartz.JobExecutionContext;


/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
