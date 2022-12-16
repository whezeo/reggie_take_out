package com.cuit.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.reggie.pojo.OrderDetail;
import com.cuit.reggie.service.OrderDetailService;
import com.cuit.reggie.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author liveb
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-12-16 15:29:03
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




