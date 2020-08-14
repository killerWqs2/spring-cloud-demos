package org.killer.springcloudquartz.modules.task.controller;

import org.killer.springcloudquartz.common.R;
import org.killer.springcloudquartz.modules.task.service.ISysJobLogService;
import org.killer.springcloudquartz.modules.task.entity.SysJob;
import org.killer.springcloudquartz.modules.task.service.ISysJobService;
import org.killer.springcloudquartz.utils.CronUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 调度任务信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/job")
public class SysJobController {
    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:list')")
    @GetMapping("/list")
    public R list(SysJob sysJob)
    {
        List<SysJob> list = jobService.selectJobList(sysJob);
        return R.success(list);
    }

    /**
     * 导出定时任务列表
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJob sysJob) throws IOException
    {
//        List<SysJob> list = jobService.selectJobList(sysJob);
//        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
//        util.exportExcel(response, list, "定时任务");
    }

    /**
     * 获取定时任务详细信息
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:query')")
    @GetMapping(value = "/{jobId}")
    public R getInfo(@PathVariable("jobId") Long jobId)
    {
        return R.success(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:add')")
    @PostMapping
    public R add(@RequestBody SysJob sysJob) throws SchedulerException
    {
        if (!CronUtils.isValid(sysJob.getCronExpression()))
        {
            return R.failed("cron表达式不正确");
        }
//        sysJob.setCreateBy(SecurityUtil.getUsername());
        jobService.insertJob(sysJob);
        return R.success();
    }

    /**
     * 修改定时任务
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:edit')")
    @PutMapping
    public R edit(@RequestBody SysJob sysJob) throws SchedulerException
    {
        if (!CronUtils.isValid(sysJob.getCronExpression()))
        {
            return R.failed("cron表达式不正确");
        }
//        sysJob.setUpdateBy(SecurityUtil.getUsername());
        jobService.updateJob(sysJob);
        return R.success();
    }

    /**
     * 定时任务状态修改
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
    @PutMapping("/changeStatus")
    public R changeStatus(@RequestBody SysJob job) throws SchedulerException
    {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        jobService.changeStatus(newJob);
        return R.success();
    }

    /**
     * 定时任务立即执行一次
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
    @PutMapping("/run")
    public R run(@RequestBody SysJob job) throws SchedulerException
    {
        jobService.run(job);
        return R.success();
    }

    /**
     * 删除定时任务
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
    @DeleteMapping("/{jobIds}")
    public R remove(@PathVariable Long[] jobIds) throws SchedulerException
    {
        jobService.deleteJobByIds(jobIds);
        return R.success();
    }
}
