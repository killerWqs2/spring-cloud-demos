package org.killer.t0datafetch.modules.quartzAdmin.entity;

import javax.persistence.*;

/**
 * @author killer
 * @date 2020/07/26 - 21:52
 */
@Entity
@Table(name = "QRTZ_CRON_TRIGGERS")
public class QrtzCronTrigger {

    @EmbeddedId
    private QrtzTriggerKey id;

    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;

    @Column(name = "TIME_ZONE_ID")
    private String timeZoneId;

}
