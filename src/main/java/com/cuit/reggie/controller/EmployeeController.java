package com.cuit.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.reggie.pojo.Employee;
import com.cuit.reggie.service.EmployeeService;
import com.cuit.reggie.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //md5加密
        String password = employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        //查询用户
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername,employee.getUsername());
        Employee user = employeeService.getOne(wrapper);
        if(user==null){
            return R.error("登陆失败");
        }
        if(!password.equals(user.getPassword())){
            return R.error("登陆失败");
        }
        if(user.getStatus()==0){
            return R.error("账号已禁用");
        }
        //登录成功
        request.getSession().setAttribute("employee",user.getId());
        return R.success(user);
    }
    @PostMapping("/logout")
    public R<String > logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("save  employee " + employee.toString());
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
       // employee.setCreateTime(LocalDateTime.now());
      //employee.setUpdateTime(LocalDateTime.now());
       //Long  userId = (Long)request.getSession().getAttribute("employee");
      //employee.setCreateUser(userId);
        //employee.setUpdateUser(userId);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String name){
        Page<Employee> employeePage = new Page<>(page,pageSize);

        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name),Employee::getName,name);
        wrapper.orderByDesc(Employee::getCreateTime);
        employeeService.page(employeePage,wrapper);
        return R.success(employeePage);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info("修改员工大的信息");
       // Long empId = (Long)request.getSession().getAttribute("employee");
        // employee.setUpdateUser(empId);
        // employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }


    /**
     * 根据id查询employee
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }
}
