package org.one.afternoon.service.impl;

import org.one.afternoon.domain.Dish;
import org.one.afternoon.mapper.DishMapper;
import org.one.afternoon.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    public List<Dish> findAllDishes() {
        return dishMapper.findAll();
    }



    @Override
    public Dish findDishById(int index) {
        return dishMapper.findById(index);
    }

    @Override
    public Long getLastId() {
        return dishMapper.getLastId();
    }

    @Override
    public boolean insert(Dish dish) {
        return dishMapper.insert(dish);
    }

    @Override
    public boolean deleteById(Integer index) {
        return dishMapper.deleteById(index);
    }

    @Override
    public boolean update(Dish dish) {
        return dishMapper.update(dish);
    }

    @Override
    public List<Dish> query(Dish dish) {
        return dishMapper.queryOne(dish);
    }
}
