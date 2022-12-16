package com.cuit.reggie.vo;


import com.cuit.reggie.vo.error.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@ResponseBody
@ControllerAdvice(annotations = {RestController.class,Controller.class})
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     */
    @ExceptionHandler(SQLException.class)
    public R<String> exceptionHandler(SQLException ex){
    log.error(ex.getMessage());
        return R.error("数据库错误! ");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex){
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }
}
