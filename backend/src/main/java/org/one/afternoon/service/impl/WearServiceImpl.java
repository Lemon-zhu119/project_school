package org.one.afternoon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.one.afternoon.domain.Wear;
import org.one.afternoon.mapper.WearMapper;
import org.one.afternoon.service.WearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.saveBatch;

@Service
public class WearServiceImpl extends ServiceImpl<WearMapper, Wear> implements WearService  {
    @Autowired
    private WearMapper wearMapper;

    @Override
    public List<Wear> findAll() {
        return wearMapper.findAll();
    }


    @Override
    public int insert(Wear wear) {
        return wearMapper.insert(wear);
    }

    @Override
    public boolean deleteById(int id) {
        return wearMapper.deleteById(id);
    }

    @Override
    public boolean update(Wear wear) {
        return wearMapper.update(wear);
    }

    @Override
    public boolean batchInsert(List<Wear> wears) {
        return saveBatch(wears);
    }


}
