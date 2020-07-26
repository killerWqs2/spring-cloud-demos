package org.killer.t0datafetch.modules.quartzAdmin.service.impl;


import org.killer.t0datafetch.modules.quartzAdmin.dao.JobAndTriggerRepository;
import org.killer.t0datafetch.modules.quartzAdmin.entity.QrtzJobDetail;
import org.killer.t0datafetch.modules.quartzAdmin.service.IJobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Service
public class IJobAndTriggerServiceImpl implements IJobAndTriggerService {

    @Autowired
    private JobAndTriggerRepository jobAndTriggerMapper;

    @Override
    public Page<QrtzJobDetail> getJobAndTriggerDetails(Integer pageNum, Integer pageSize) {
        return jobAndTriggerMapper.getJobAndTriggerDetails(PageRequest.of(pageNum, pageSize));
    }

}
