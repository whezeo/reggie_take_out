package com.cuit.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.reggie.mapper.EmployeeMapper;
import com.cuit.reggie.pojo.Employee;
import com.cuit.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl  extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {

}
