package org.one.afternoon.service.impl;

import org.one.afternoon.domain.Activity;
import org.one.afternoon.mapper.ActivityMapper;
import org.one.afternoon.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public List<Activity> findAll() {
        return activityMapper.findAll();
    }

    public Activity findById(int index) {
        return activityMapper.findActivityById(index);
    }
    public List<Activity> queryOne(Activity activity){
        return activityMapper.queryOne(activity);
    }

    @Override
    public Integer getLastActivityId() {
        return activityMapper.getLastId();
    }

    @Override
    public boolean insert(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public boolean deleteById(Integer index) {
        return activityMapper.deleteById(index);
    }

    @Override
    public boolean update(Activity activity) {
        return activityMapper.update(activity);
    }
}
