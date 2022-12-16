package com.cuit.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.reggie.pojo.AddressBook;
import com.cuit.reggie.service.AddressBookService;
import com.cuit.reggie.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
* @author liveb
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2022-12-16 15:29:16
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

}




