package org.killer.t0datafetch.modules.quartzAdmin.dao;

import org.killer.t0datafetch.modules.quartzAdmin.entity.QrtzJobDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Repository
public interface JobAndTriggerRepository extends JpaRepository<QrtzJobDetail, Integer> {

    @Query(value = "SELECT DISTINCT " +
            "QRTZ_JOB_DETAILS.JOB_NAME ," +
            "QRTZ_JOB_DETAILS.JOB_GROUP ," +
            "QRTZ_JOB_DETAILS.JOB_CLASS_NAME ," +
            "QRTZ_TRIGGERS.TRIGGER_NAME ," +
            "QRTZ_TRIGGERS.TRIGGER_GROUP ," +
            "QRTZ_CRON_TRIGGERS.CRON_EXPRESSION ," +
            "QRTZ_CRON_TRIGGERS.TIME_ZONE_ID " +
            "FROM QRTZ_JOB_DETAILS " +
            "LEFT JOIN QRTZ_TRIGGERS ON QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_JOB_DETAILS.JOB_GROUP " +
            "LEFT JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME " +
            "AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME " +
            "AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP", nativeQuery = true)
    Page<QrtzJobDetail> getJobAndTriggerDetails(Pageable pageable);

}
