package com.tiager.doublesource.controller;

import com.tiager.doublesource.mybatis.mapper.UserMapper;
import com.tiager.doublesource.mybatis.mapperTwo.ProvinceMapper;
import com.tiager.doublesource.mybatis.po.User;
import com.tiager.doublesource.mybatis.poTwo.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GetDataByIdFromDbImpl implements GetDataByIdFromDb{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public void getDataFromDb() {
        User user = userMapper.selectByPrimaryKey(1);
        Province province = provinceMapper.selectByPrimaryKey(3);
        System.out.println("名字:"+user.getNickname());
        System.out.println("省份名字:"+province.getPname());

        System.out.println("名字:");
        System.out.println("省份名字:");
    }
}
