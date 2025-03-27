package org.one.afternoon.controller;

import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Course;
import org.one.afternoon.domain.Dish;
import org.one.afternoon.domain.Dish;
import org.one.afternoon.service.DishService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisService redisService;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Dish>> getAllDishes() {
        return Result.success(dishService.findAllDishes());
    }
    @CrossOrigin
    @PostMapping("/queryOne")
    public Result<List<Dish>> queryOne(@RequestBody Dish dish) {
            return Result.success(dishService.query(dish));
    }
    @CrossOrigin
    @PostMapping("/findOne")
    @ResponseBody
    public Result<Dish> findDishById(@RequestParam int index) {
        return Result.success(dishService.findDishById(index));
    }
    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody Dish dish) {
        dish.setId(dishService.getLastId()+1);
        boolean save= dishService.insert(dish);
        redisService.setValue("dishes",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> deleteOne(@RequestParam Integer index) {
        dishService.deleteById(index);
        return Result.success("删除成功");
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            dishService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }


    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody Dish dish) {
        boolean update = dishService.update(dish);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}