package com.lzy.service;


import com.github.pagehelper.Page;
import com.lzy.Dao.ShiPinDao;
import com.lzy.Model.Shipin;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.sql.SQLException;
import java.util.List;
@Service
public interface ShiPinService {

    //@Autowired
    //ShiPinDao shiPinDao;
    int PAGESIZE = 4;   //每页显示视频数

    List<Shipin> VideoListPage(int pageNow);

    int getPage() throws SQLException;

    //插入
    int insertUrl(String name,String lujing,String url);

    //查询
    List<Shipin> selectShipin();

    //搜索视频
    List<Shipin> selectVideo(String selectName,String selectTag,int pageNow);

    int getSelectPage(String selectName,String selectTag) throws SQLException;

}
