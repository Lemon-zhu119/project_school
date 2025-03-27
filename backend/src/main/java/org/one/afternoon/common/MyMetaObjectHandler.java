package org.one.afternoon.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//MetaObjectHandler接口是mybatisPlus为我们提供的的一个扩展接口，我们可以利用这个接口在我们插入或者更新数据的时候，为一些字段指定默认值。
// 实现这个需求的方法不止一种，在sql层面也可以做到，在建表的时候也可以指定默认值。
//eg.自动生成时间等
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start info fill......");
        //自动生成createAt值
        this.setFieldValByName("createAt",new Date(),metaObject);
        //自动生成updateAt值
        this.setFieldValByName("updateAt",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //update操作时自动执行该更新操作
        log.info("start info fill......");
        this.setFieldValByName("updateAt",new Date(),metaObject);
    }
}
