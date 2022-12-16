package com.cuit.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.reggie.pojo.Category;
import com.cuit.reggie.pojo.Dish;
import com.cuit.reggie.pojo.DishFlavor;
import com.cuit.reggie.service.CategoryService;
import com.cuit.reggie.service.DishFlavorService;
import com.cuit.reggie.service.DishService;
import com.cuit.reggie.vo.R;
import com.cuit.reggie.vo.dto.DishDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liveb
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    DishService dishService;
    @Autowired
    DishFlavorService dishFlavorService;
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info("保存菜品数据 ");
        dishService.saveWithFlavor(dishDto);
        return R.success("添加成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Dish> pageInfo = new Page<Dish>(page,pageSize);
        Page<DishDto> dishDtoPage = new Page<>();
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Dish::getName,name);
        dishService.page(pageInfo,wrapper);
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> dishDtos = new ArrayList<>();
        for (Dish record : records) {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(record,dishDto);
            Long categoryId = record.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category!=null)
            dishDto.setCategoryName(category.getName());

            dishDtos.add(dishDto);
        }
        dishDtoPage.setRecords(dishDtos);
        return R.success(dishDtoPage);
    }

    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        log.info("更新菜品数据 ");
        dishService.updateWithFlavor(dishDto);
        return R.success("添加成功");
    }

//    /**
//     * 根据条件查询菜品数据
//     * @param dish
//     * @return
//     */
//    @GetMapping("/list")
//    public R<List<Dish>> list(Dish dish){
//        Long categoryId = dish.getCategoryId();
//        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
//        //添加查询条件
//        wrapper.eq(categoryId!=null,Dish::getCategoryId,categoryId).eq(Dish::getStatus,1);
//        wrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
//        List<Dish> list = dishService.list(wrapper);
//        return R.success(list);
//    }

    /**
     * 根据条件查询菜品数据
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        Long categoryId = dish.getCategoryId();
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        wrapper.eq(categoryId!=null,Dish::getCategoryId,categoryId).eq(Dish::getStatus,1);
        wrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(wrapper);
        List<DishDto> dishDtos = new ArrayList<>();
        list.forEach(dish1 -> {

            LambdaQueryWrapper<DishFlavor> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(DishFlavor::getDishId,dish1.getId());
            List<DishFlavor> dishFlavors = dishFlavorService.list(wrapper1);
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(dish1,dishDto);
            dishDto.setFlavors(dishFlavors);
            dishDtos.add(dishDto);

        });

        return R.success(dishDtos);
    }
}
