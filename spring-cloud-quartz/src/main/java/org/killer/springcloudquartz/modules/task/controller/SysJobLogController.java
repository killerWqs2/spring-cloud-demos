package org.killer.springcloudquartz.modules.task.controller;

import org.killer.springcloudquartz.common.R;
import org.killer.springcloudquartz.modules.task.entity.SysJobLog;
import org.killer.springcloudquartz.modules.task.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/job/log")
public class SysJobLogController {
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:list')")
    @GetMapping("/list")
    public R list(SysJobLog sysJobLog)
    {
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        return R.success(list);
    }

    /**
     * 导出定时任务调度日志列表
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJobLog sysJobLog) throws IOException
    {
//        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
//        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
//        util.exportExcel(response, list, "调度日志");
    }

    /**
     * 根据调度编号获取详细信息
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:query')")
    @GetMapping(value = "/{configId}")
    public R getInfo(@PathVariable Long jobLogId)
    {
        return R.success(jobLogService.selectJobLogById(jobLogId));
    }

    /**
     * 删除定时任务调度日志
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
    @DeleteMapping("/{jobLogIds}")
    public R remove(@PathVariable Long[] jobLogIds)
    {
        jobLogService.deleteJobLogByIds(jobLogIds);
        return R.success();
    }

    /**
     * 清空定时任务调度日志
     */
//    @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
    @DeleteMapping("/clean")
    public R clean()
    {
        jobLogService.cleanJobLog();
        return R.success();
    }
}
