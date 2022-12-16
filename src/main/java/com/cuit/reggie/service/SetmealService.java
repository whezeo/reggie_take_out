package com.cuit.reggie.service;

import com.cuit.reggie.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.reggie.vo.dto.SetmealDto;

import java.util.List;

/**
* @author liveb
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2022-12-11 11:24:27
*/
public interface SetmealService extends IService<Setmeal> {

    void saveWithDish(SetmealDto setmealDto);

    void removeWithDishs(List<Long> ids);
}
