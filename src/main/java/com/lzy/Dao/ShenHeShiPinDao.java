package com.lzy.Dao;

import com.lzy.Model.ShenheShipin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShenHeShiPinDao {
    //插入审核视频
    @Insert({"insert into shenheshipins (vname,title,tag,author,lujing) values (#{name},#{title},#{tag},#{author},#{lujing})"})
    public void insertShenheShipin(@Param("name")String name,
                                   @Param("title") String title,
                                   @Param("tag")String tag,
                                   @Param("lujing")String lujing,
                                   @Param("author")String author);

    @Select({"select id,vname,title,tag,author from shenheshipins where author = '${author}'"})
    public List<ShenheShipin> selectMyShenheVideo(@Param("author") String author);

    @Select("select id,vname,title,tag,author from shenheshipins")
    public List<ShenheShipin> allShenhe();

    @Select({"select * from shenheshipins where id = '${id}'"})
    public ShenheShipin selectVideoId(@Param("id") String id);

    @Delete({"delete from shenheshipins where id = '${id}'"})
    public boolean delShenheVideoId(@Param("id") String id);

}
