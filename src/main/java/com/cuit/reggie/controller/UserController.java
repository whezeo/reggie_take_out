package com.cuit.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cuit.reggie.pojo.User;
import com.cuit.reggie.service.UserService;
import com.cuit.reggie.vo.R;
import com.cuit.reggie.vo.utils.SMSUtils;
import com.cuit.reggie.vo.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(HttpSession session, @RequestBody User user){
        /**
         * 获取手机号
         * 生成验证码
         * 验证码保存session
         */
        String phone = user.getPhone();
        if(!StringUtils.isBlank(phone)){
            String  code = ValidateCodeUtils.generateValidateCode(4).toString();

            log.info("code={}",code);
            //SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            session.setAttribute(phone,code);
            return R.success("验证码发送成功");
        }
        return null;

    }

    @PostMapping("/login")
    public R<String> login(@RequestBody Map user,HttpSession session){
        log.info(user.toString());
        /**
         * 获取手机号和验证码
         * 获取session保存的验证码
         * 进行比较
         * 是否为新用户如果为新用户 自动注册
         */
        String phone = (String) user.get("phone");
        String code = (String) user.get("code");

        String readCode = (String) session.getAttribute(phone);
        if(!readCode.equals(code)){
            return R.error("验证码不正确");
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone,phone);
        User user1  = userService.getOne(wrapper);
        if(user1==null){
            //新用户
            user1 = new User();
            user1.setPhone(phone);
            user1.setStatus(1);
            userService.save(user1);
        }
        session.setAttribute("user",user1.getId());
        return R.success("登录成功");
    }
}
