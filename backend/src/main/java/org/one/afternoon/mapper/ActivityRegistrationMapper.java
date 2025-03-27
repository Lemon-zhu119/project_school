package org.one.afternoon.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.one.afternoon.domain.RegistrationRecord;
import org.one.afternoon.domain.ActivityRegistration;

import java.util.List;

@Mapper
public interface ActivityRegistrationMapper {
    Integer getLastActivityRecordId();
    public boolean insert(RegistrationRecord registrationRecord);

    List<ActivityRegistration> queryOne(ActivityRegistration activityRegistration);
    List<ActivityRegistration> findAll();

    boolean deleteById(Integer index);

    boolean update(ActivityRegistration activityRegistration);
}
