package com.lzy.Dao;

import com.lzy.Model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Mapper
@Component
public interface ShiPinDao {

    //插入视频
    @Insert({"insert into shipins (name,lujing,url) values (#{name},#{lujing},#{url})"})
    public int insertUrl(@Param("name")String name,@Param("lujing")String lujing,@Param("url")String url);

    //插入图片
    @Insert({"insert into touxiangs (owner,touxiangName,url) values (#{owner},#{name},#{url})"})
    public int insertImageUrl(@Param("owner") String owner,@Param("name")String name,@Param("url")String url);

    //查询
    @Select("select * from shipins")
    public List<Shipin> selectShipin();

    //查询从第几条开始后面size条数据
    @Select({"select * from shipins LIMIT #{pageNow},#{pageSize}"})
    public List<Shipin> VideoListPage(@Param("pageNow") int pageNow,@Param("pageSize") int pageSize);

    //获取视频条数返回int型
    @Select("select count(id) pageCount from shipins")
    public int pageCount();

    //获取查找到的视频条数返回int型        !!!注意，此处必须用 '%${变量名}'，用#无效
    @Select({"select count(id) pageCount from shipins where shipins.name like '%${selectName}%' and shipins.tag='${selectTag}'"})
    public int selectPageCount(@Param("selectName") String selectName,@Param("selectTag") String selectTag);

    @Select({"select * from shipins where shipins.name like '%${selectName}%' and shipins.tag='${selectTag}' LIMIT #{pageNow},#{pageSize}"})
    public List<Shipin> selectVideo(@Param("selectName") String selectName,@Param("selectTag") String selectTag,@Param("pageNow") int pageNow,@Param("pageSize") int pageSize);

    @Select({"select * from shipins where shipins.author = '${author}'"})
    public List<Shipin> selectMyVideo(@Param("author") String author);

    //转移视频
    @Insert({"insert into shipins (title,author,tag,name,lujing) values (#{title},#{author},#{tag},#{name},#{lujing})"})
    public int transferVideo(@Param("title")String title,
                             @Param("author")String author,
                             @Param("tag")String tag,
                             @Param("name") String name,
                             @Param("lujing") String lujing);


    @Select("select * from tag")
    public List<String> selectAllTag();

}
