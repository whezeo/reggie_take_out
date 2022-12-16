package com.cuit.reggie.mapper;

import com.cuit.reggie.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liveb
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2022-12-07 16:31:14
* @Entity com.cuit.reggie.pojo.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}




