package com.cuit.reggie.service;

import com.cuit.reggie.pojo.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.reggie.vo.dto.DishDto;

/**
* @author liveb
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-12-11 11:24:18
*/
public interface DishService extends IService<Dish> {

    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}
