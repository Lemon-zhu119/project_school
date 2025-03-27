package org.one.afternoon.service;

import org.one.afternoon.domain.Dish;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DishService {
    List<Dish> findAllDishes();

    Dish findDishById(int index);

    Long getLastId();

    boolean insert(Dish dish);

    boolean deleteById(Integer index);

    boolean update(Dish dish);

    List<Dish> query(Dish dish);
}
