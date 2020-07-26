package org.killer.t0datafetch.modules.quartzAdmin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author killer
 * @date 2020/07/26 - 21:39
 */
@Data
@Embeddable
public class QrtzJobDetailKey implements Serializable {

    /** 以下三个为主键 */
    // @Column(name = "SCHED_NAME")
    private String schedName;

    // @Column(name = "JOB_NAME")
    private String jobName;

    // @Column(name = "JOB_GROUP")
    private String jobGroup;

}
