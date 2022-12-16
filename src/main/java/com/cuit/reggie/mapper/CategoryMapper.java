package com.cuit.reggie.mapper;

import com.cuit.reggie.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liveb
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-12-10 21:55:25
* @Entity com.cuit.reggie.pojo.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




