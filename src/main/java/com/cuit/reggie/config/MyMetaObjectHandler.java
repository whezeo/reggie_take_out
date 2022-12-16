package com.cuit.reggie.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cuit.reggie.vo.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据
 */

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("自动填充");
        //metaObject.setValue("");
        metaObject.setValue("updateUser", UserThreadLocal.get());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("createUser", UserThreadLocal.get());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("自动填充");
        metaObject.setValue("updateUser", UserThreadLocal.get());
        metaObject.setValue("updateTime", LocalDateTime.now());
    }
}
