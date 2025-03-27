package org.one.afternoon.service.impl;
import org.one.afternoon.domain.LeaveRequest;
import org.one.afternoon.domain.LeaveRequestRecord;
import org.one.afternoon.mapper.LeaveRequestMapper;
import org.one.afternoon.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestMapper leaveRequestMapper;
    @Override
    public List<LeaveRequest> findById(int sId) {
        return leaveRequestMapper.findById(sId);
    }
    @Override
    public boolean insert(LeaveRequestRecord leaveRequestRecord){
        return leaveRequestMapper.insert(leaveRequestRecord);
    }
    @Override
    public List<LeaveRequest> findAll() {
        return leaveRequestMapper.findAll();
    }
    @Override
    public boolean update(LeaveRequestRecord leaveRequestRecord){
        return leaveRequestMapper.update(leaveRequestRecord);
    }

    @Override
    public List<LeaveRequest> query(LeaveRequest leaveRequestRecord) {
        return leaveRequestMapper.query(leaveRequestRecord);
    }

    @Override
    public boolean deleteById(Integer index) {
        return leaveRequestMapper.deleteById(index);
    }
}
