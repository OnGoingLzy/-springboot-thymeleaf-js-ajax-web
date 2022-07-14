package com.lzy.service;

import com.lzy.Dao.ShiPinDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("imageService")
public class imageServiceImpl implements imageService{
    @Autowired
    ShiPinDao shiPinDao;

    @Override
    public int insertImageUrl(String owner,String name, String url) {
        System.out.print("开始插入=name=="+name+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        int jieguo=shiPinDao.insertImageUrl(owner,name,url);
        System.out.print("插入结果==="+jieguo+"\n");
        return jieguo;
    }
}
