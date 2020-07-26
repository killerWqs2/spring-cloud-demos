package org.killer.t0datafetch.modules.quartzAdmin.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author killer
 * @date 2020/07/26 - 21:45
 */
@Data
@Entity
@Table(name = "QRTZ_TRIGGERS")
public class QrtzTrigger implements Serializable {

    @Id
    private QrtzTriggerKey id;

    @OneToOne
    private QrtzCronTrigger cronTrigger;

}
