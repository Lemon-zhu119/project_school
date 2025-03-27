package org.one.afternoon.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.one.afternoon.domain.LeaveRequest;
import org.one.afternoon.domain.LeaveRequestRecord;

import java.util.List;
@Mapper
public interface LeaveRequestMapper {
    List<LeaveRequest> findById(int id);
    Integer getLastLeaveRequestId();
    public boolean insert(LeaveRequestRecord leaveRequestRecord);

    List<LeaveRequest> findAll();

    boolean update(LeaveRequestRecord leaveRequestRecord);

    List<LeaveRequest> query(LeaveRequest leaveRequest);

    boolean deleteById(Integer index);
}
