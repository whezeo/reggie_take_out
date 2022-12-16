package com.cuit.reggie.service;

import com.cuit.reggie.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
* @author liveb
* @description 针对表【employee(员工信息)】的数据库操作Service
* @createDate 2022-12-07 16:31:14
*/
@Service
public interface EmployeeService extends IService<Employee> {

}
