package org.one.afternoon.service.impl;
import org.one.afternoon.domain.ActivityRegistration;
import org.one.afternoon.domain.RegistrationRecord;
import org.one.afternoon.mapper.ActivityRegistrationMapper;
import org.one.afternoon.service.ActivityRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityRegistrationServiceImpl implements ActivityRegistrationService {

    @Autowired
    private ActivityRegistrationMapper activityRegistrationMapper;

    @Override
    public boolean insert(RegistrationRecord registrationRecord){
        return activityRegistrationMapper.insert(registrationRecord);
    }

    @Override
    public List<ActivityRegistration> findAll() {
        return activityRegistrationMapper.findAll();
    }

    @Override
    public List<ActivityRegistration> queryOne(ActivityRegistration activityRegistration){
        return activityRegistrationMapper.queryOne(activityRegistration);
    }
    public Integer getLastRegistrationId(){
        return activityRegistrationMapper.getLastActivityRecordId();
    }

    @Override
    public boolean deleteById(Integer index) {

        return  activityRegistrationMapper.deleteById(index);
    }

    @Override
    public boolean update(ActivityRegistration activityRegistration) {
        return activityRegistrationMapper.update(activityRegistration);
    }

    ;
}
