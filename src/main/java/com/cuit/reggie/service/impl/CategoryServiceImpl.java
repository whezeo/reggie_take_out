package com.cuit.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.reggie.pojo.Category;
import com.cuit.reggie.pojo.Dish;
import com.cuit.reggie.pojo.Setmeal;
import com.cuit.reggie.service.CategoryService;
import com.cuit.reggie.mapper.CategoryMapper;
import com.cuit.reggie.service.DishService;
import com.cuit.reggie.service.SetmealService;
import com.cuit.reggie.vo.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author liveb
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-12-10 21:55:25
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int dishCount = dishService.count(dishLambdaQueryWrapper);
        if(dishCount!=0){
            throw new CustomException("分类关联了菜品,无法删除!");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int setmealCount = dishService.count(dishLambdaQueryWrapper);
        if(setmealCount!=0){
            throw new CustomException("分类关联了套餐,无法删除!");
        }
        super.removeById(id);
    }
}




