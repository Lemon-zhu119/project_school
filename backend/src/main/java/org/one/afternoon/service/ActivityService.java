package org.one.afternoon.service;

import org.one.afternoon.domain.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    List<Activity> findAll();
    Activity findById(int index);

    List<Activity> queryOne(Activity activity);

    Integer getLastActivityId();

    boolean insert(Activity activity);

    boolean deleteById(Integer index);

    boolean update(Activity activity);
}
