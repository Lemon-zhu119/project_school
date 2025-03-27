package org.one.afternoon.service;


import org.one.afternoon.domain.LeaveRequest;
import org.one.afternoon.domain.LeaveRequestRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveRequestService {
    List<LeaveRequest> findById(int sId);

    boolean insert(LeaveRequestRecord leaveRequestRecord);

    List<LeaveRequest> findAll();

    boolean update(LeaveRequestRecord leaveRequestRecord);

    List<LeaveRequest> query(LeaveRequest leaveRequestRecord);

    boolean deleteById(Integer index);
}
