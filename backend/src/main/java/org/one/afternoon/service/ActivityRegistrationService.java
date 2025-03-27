package org.one.afternoon.service;


import org.one.afternoon.domain.ActivityRegistration;
import org.one.afternoon.domain.RegistrationRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityRegistrationService {
    List<ActivityRegistration> queryOne(ActivityRegistration activityRegistration);

    boolean insert(RegistrationRecord registrationRecord);
    List<ActivityRegistration> findAll();
    Integer getLastRegistrationId();

    boolean deleteById(Integer index);

    boolean update(ActivityRegistration activityRegistration);
}
