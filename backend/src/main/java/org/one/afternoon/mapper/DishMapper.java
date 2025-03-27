package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.one.afternoon.domain.Dish;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("SELECT * FROM dishes")
    List<Dish> findAll();

    @Select("SELECT * FROM dishes WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Dish> searchByName(String name);
    @Select("SELECT * FROM dishes where id=#{index}")
    Dish findById(int index);
    List<Dish> queryOne(Dish dish);
    Long getLastId();

    boolean insert(Dish dish);

    boolean deleteById(Integer index);

    boolean update(Dish dish);
}
