package com.cuit.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.reggie.pojo.Setmeal;
import com.cuit.reggie.pojo.SetmealDish;
import com.cuit.reggie.service.SetmealDishService;
import com.cuit.reggie.service.SetmealService;
import com.cuit.reggie.mapper.SetmealMapper;
import com.cuit.reggie.vo.dto.SetmealDto;
import com.cuit.reggie.vo.error.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author liveb
* @description 针对表【setmeal(套餐)】的数据库操作Service实现
* @createDate 2022-12-11 11:24:27
*/
@Service
@Slf4j
@Transactional
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements SetmealService{
    @Autowired
    SetmealDishService setmealDishService;
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.forEach((setmealDish -> {
            setmealDish.setSetmealId(setmealDto.getId());
        }));
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public void removeWithDishs(List<Long> ids) {
        //判断是否可以删除
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Setmeal::getId,ids).eq(Setmeal::getStatus,1);
        int count = super.count(wrapper);
        if(count!=0){
            throw new CustomException("套餐正在售卖中,无法删除！");
        }
        //删除setmeal
        super.removeByIds(ids);
        //setmealdish
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getDishId,ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);
    }
}




