package org.killer.t0datafetch.config;

import org.killer.t0datafetch.schedules.HistoryDataFetchJob;
import org.killer.t0datafetch.schedules.ListedCompanyFetchJob;
import org.killer.t0datafetch.schedules.RealTimeDataFetchJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author killer
 * @date 2020/05/19 - 15:31
 */
@Configuration
public class QuartzConfiguration {

    @Bean("history-data-fetch")
    public JobDetail jobDetail1() {
        // identity 用来鉴别唯一的jobdetail
        return JobBuilder.newJob(HistoryDataFetchJob.class).withIdentity("history-data-fetch", "share-datas")
                .storeDurably().build();
    }

    @Bean("listed-company-data-fetch")
    public JobDetail jobDetail2() {
        return JobBuilder.newJob(ListedCompanyFetchJob.class).withIdentity("listed-company-data-fetch", "share-datas")
                .storeDurably().build();
    }

    @Bean("real-time-data-fetch")
    public JobDetail jobDetail3() {
        return JobBuilder.newJob(RealTimeDataFetchJob.class).withIdentity("real-time-data-fetch", "share-datas")
                .storeDurably().build();
    }

    @Bean("history-data-fetch-trigger")
    public Trigger trigger1() {
        // 历史数据每一秒执行一次
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail1()).withIdentity("history-data-fetch", "share-datas").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
                .build();
    }

    @Bean("listed-company-data-fetch-trigger")
    public Trigger trigger2() {
        // 每天定时获取
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail2()).withIdentity("listed-company-data-fetch", "share-datas").startNow().withSchedule(CronScheduleBuilder.cronSchedule("0 40 11 * * ? "))
                .build();
    }

    @Bean("real-time-data-fetch-trigger")
    public Trigger trigger3() {
        // 历史数据执行一次就好
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail3()).withIdentity("real-time-data-fetchhistory-data-fetch", "share-datas").startNow().withSchedule(CronScheduleBuilder.cronSchedule("0/1 0 0/1 * * ? "))
                .build();
    }

}
