package com.cuit.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.reggie.pojo.ShoppingCart;
import com.cuit.reggie.service.ShoppingCartService;
import com.cuit.reggie.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
* @author liveb
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-12-15 20:10:37
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

}




