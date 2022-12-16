package com.cuit.reggie.service;

import com.cuit.reggie.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liveb
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2022-12-16 15:29:08
*/
public interface OrdersService extends IService<Orders> {

    void submit(Orders orders);
}
