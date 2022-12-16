package com.cuit.reggie.mapper;

import com.cuit.reggie.pojo.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liveb
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2022-12-11 11:24:18
* @Entity com.cuit.reggie.pojo.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




