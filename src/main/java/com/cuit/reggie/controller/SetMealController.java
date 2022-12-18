package com.cuit.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.reggie.pojo.Category;
import com.cuit.reggie.pojo.Setmeal;
import com.cuit.reggie.service.CategoryService;
import com.cuit.reggie.service.SetmealDishService;
import com.cuit.reggie.service.SetmealService;
import com.cuit.reggie.vo.R;
import com.cuit.reggie.vo.dto.SetmealDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetMealController {
    @Autowired
    SetmealService setmealService;
    @Autowired
    SetmealDishService setmealDishService;
    @Autowired
    CategoryService categoryService;

    @PostMapping
    @CacheEvict(value = "setmealList",allEntries = true)
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("新增套餐!");
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功 ");
    }

    @GetMapping("page")
    public R<Page> list(int page,int pageSize,String name){
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> setMealDtoPage = new Page<>();
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Setmeal::getName,name).orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo,wrapper);

        BeanUtils.copyProperties(pageInfo,setMealDtoPage,"records");
        List<Setmeal> records = pageInfo.getRecords();
        ArrayList<SetmealDto> setmealDtos = new ArrayList<>();
        records.forEach(setmeal -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(setmeal,setmealDto);
            Long categoryId = setmeal.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if(category!=null)
             setmealDto.setCategoryName(category.getName());
            setmealDtos.add(setmealDto);
                }
        );
        setMealDtoPage.setRecords(setmealDtos);
        return R.success(setMealDtoPage);
    }

    @DeleteMapping
    @CacheEvict(value = "setmealList",allEntries = true)
    public R<String> remove(@RequestParam List<Long> ids){
        log.info("删除套餐操作");
        setmealService.removeWithDishs(ids);
        return R.success("删除成功");
    }
    @GetMapping("/list")
    @Cacheable(value = "setmealList",key = "#setmeal.categoryId + '_'  + #setmeal.status")
    public R<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(setmeal.getCategoryId()!=null,Setmeal::getCategoryId,setmeal.getCategoryId());
        wrapper.eq(setmeal.getStatus()!=null,Setmeal::getStatus,setmeal.getStatus());
        wrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(wrapper);
        return R.success(list);
    }
}
