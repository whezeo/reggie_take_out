package com.cuit.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cuit.reggie.pojo.AddressBook;
import com.cuit.reggie.service.AddressBookService;
import com.cuit.reggie.vo.R;
import com.cuit.reggie.vo.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    AddressBookService addressBookService;
    /**
     * 新增地址
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){
        //设置用户id
        addressBook.setUserId(UserThreadLocal.get());
        log.info("添加地址 {}" , addressBook);
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }
    @PutMapping
    public R<String> update(@RequestBody AddressBook addressBook){
        addressBookService.updateById(addressBook);
        return R.success("修改成功");
    }
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        addressBookService.removeByIds(ids);
        return  R.success("删除成功");
    }


    @PutMapping("default")
    public R<AddressBook> changeDefault(@RequestBody AddressBook addressBook){
        log.info("修改默认地址");
        LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        /**
         * 设置该用户所有的id为0
         * 设置指定id为1
         */
        wrapper.eq(AddressBook::getUserId,UserThreadLocal.get());
        wrapper.set(AddressBook::getIsDefault,0);
        addressBookService.update(wrapper);
        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }
    @GetMapping("/{id}")
    public R get(@PathVariable Long id){
        AddressBook addressBook = addressBookService.getById(id);
        if(addressBook==null){
            return R.error("没有找到该对象");
        }
        return R.success(addressBook);
    }
    @GetMapping("default")
    public R<AddressBook> getDefault(){
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,UserThreadLocal.get());
        queryWrapper.eq(AddressBook::getIsDefault,1);
        AddressBook addressBook = addressBookService.getOne(queryWrapper);
        if(addressBook==null){
            return R.error("无默认地址");
        }

        return R.success(addressBook);
    }
    @GetMapping("list")
    public R<List<AddressBook>> list(AddressBook addressBook){
        log.info("显示地址列表{}",addressBook);
        addressBook.setUserId(UserThreadLocal.get());
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);
        List<AddressBook> list = addressBookService.list(queryWrapper);

        return R.success(list);
    }
}
