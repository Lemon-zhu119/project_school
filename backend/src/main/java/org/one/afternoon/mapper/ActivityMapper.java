package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.one.afternoon.domain.Activity;

import java.util.List;

@Mapper
public interface ActivityMapper {

    List<Activity> findAll();

    List<Activity> queryOne(Activity activity);

    boolean insert(Activity activity);

    boolean deleteById(int id);

    boolean update(Activity activity);

    //查找是否存在用户
    @Select("select * from activity where id=#{id}")
    Activity findActivityById(int id);
    Integer getLastId();


}
