package org.killer.t0datafetch.modules.quartzAdmin.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * @author killer
 * @date 2020/07/26 - 21:46
 */
@Data
@Embeddable
public class QrtzTriggerKey implements Serializable {

    /** 以下三个为主键 */
    // @Column(name = "SCHED_NAME")
    private String schedName;

    // @Column(name = "JOB_NAME")
    private String triggerName;

    // @Column(name = "JOB_GROUP")
    private String triggerGroup;

}
