package com.cuit.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.reggie.pojo.DishFlavor;
import com.cuit.reggie.service.DishFlavorService;
import com.cuit.reggie.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author liveb
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2022-12-12 16:57:05
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

}




