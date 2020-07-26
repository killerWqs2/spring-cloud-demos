package org.killer.t0datafetch.modules.quartzAdmin.service;


import org.killer.t0datafetch.modules.quartzAdmin.entity.QrtzJobDetail;
import org.springframework.data.domain.Page;

/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public interface IJobAndTriggerService {
    Page<QrtzJobDetail> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);
}
