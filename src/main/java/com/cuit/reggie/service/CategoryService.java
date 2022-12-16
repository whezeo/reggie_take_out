package com.cuit.reggie.service;

import com.cuit.reggie.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
* @author liveb
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-12-10 21:55:25
*/
@Service
public interface CategoryService extends IService<Category> {

    void remove(Long id);
}
