package com.lzy.service;

import com.lzy.Dao.ShiPinDao;
import com.lzy.Model.Shipin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service(value = "ShiPinService")
public class ShiPinServiceImpl implements ShiPinService{

    @Autowired
    ShiPinDao shiPinDao;

    @Override
    public List<Shipin> VideoListPage(int pageNow) {
        pageNow = (pageNow - 1 ) * ShiPinService.PAGESIZE;
        int pageSize = ShiPinService.PAGESIZE;
        return shiPinDao.VideoListPage(pageNow,pageSize);
    }
    //搜索视频
    @Override
    public List<Shipin> selectVideo(String selectName,String selectTag,int pageNow) {
        pageNow = (pageNow - 1 ) * ShiPinService.PAGESIZE;
        int pageSize = ShiPinService.PAGESIZE;
        System.out.println("查询名字有:"+selectName+"标签为:"+selectTag+" 的视频"+pageNow+" "+pageSize);
        return shiPinDao.selectVideo(selectName,selectTag,pageNow,pageSize);
    }
    @Override
    public int getPage() {
        /**
         * 将条数转换为页数，获得当前条数属于第几页
         * 总条数/每页显示行数
         * */
        int page = 0;   //最大页数
        int pageCount = shiPinDao.pageCount();    //获取数据库总条数
        if (pageCount % ShiPinService.PAGESIZE == 0){
            page = pageCount / ShiPinService.PAGESIZE;
        }else {
            page = pageCount / ShiPinService.PAGESIZE + 1;
        }
        return page;
    }

    //插入
    @Override
    public int insertUrl(String name,String lujing,String url){
        System.out.print("开始插入=name=="+name+"\n");
        System.out.print("开始插入=lujing=="+lujing+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        int jieguo=shiPinDao.insertUrl(name,lujing,url);
        System.out.print("插入结果==="+jieguo+"\n");
        return jieguo;
    }
    //查询所有视频
    @Override
    public List<Shipin> selectShipin(){
        List<Shipin> shipins=shiPinDao.selectShipin();
        return  shipins;
    }



    @Override
    public int getSelectPage(String selectName,String selectTag) throws SQLException {
        /**
         * 将条数转换为页数，获得当前条数属于第几页
         * 总条数/每页显示行数
         * */
        int page = 0;   //最大页数
        int pageCount = shiPinDao.selectPageCount(selectName,selectTag);    //获取条数
        if (pageCount % ShiPinService.PAGESIZE == 0){
            page = pageCount / ShiPinService.PAGESIZE;
        }else {
            page = pageCount / ShiPinService.PAGESIZE + 1;
        }
        return page;
    }
}
