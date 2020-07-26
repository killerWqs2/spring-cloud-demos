package org.killer.t0datafetch.modules.quartzAdmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by haoxy on 2018/9/28.
 * 这是一个关联查询的结果 一对一
 */
@Data
@Entity
@Table(name = "QRTZ_JOB_DETAILS")
public class QrtzJobDetail implements Serializable {

    @EmbeddedId
    private QrtzJobDetailKey key;

    @Column(name = "JOB_CLASS_NAME")
    private String jobClassName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_DURABLE")
    private String durable;

    @Column(name = "IS_NONCONCURRENT")
    private String nonconcurrent;

    @Column(name = "IS_UPDATE_DATA")
    private String updateData;

    @Column(name = "REQUESTS_RECOVERY")
    private String requestsRecovery;

    @Column(name = "JOB_DATA")
    private String jobData;

    @OneToOne
    private QrtzTrigger trigger;

}
