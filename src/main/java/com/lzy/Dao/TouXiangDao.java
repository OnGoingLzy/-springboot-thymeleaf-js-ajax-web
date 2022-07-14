package com.lzy.Dao;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TouXiangDao {

    //查找头像
    @Select({"select touxiangName from touxiangs where owner='${account}'"})
    public String selectTouxiang(@Param("account") String account);
    //删除头像
    @Delete({"delete from touxiangs where owner='${account}'"})
    public void delTouxiang(@Param("account") String account);


}
