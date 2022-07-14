package com.lzy.controller;

import com.lzy.Dao.ShiPinDao;
import com.lzy.Model.Shipin;
import com.lzy.service.ShiPinService;
import com.lzy.service.ShiPinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class videoController {

    public videoController(ShiPinServiceImpl shiPinService) {
        this.shiPinService =  shiPinService;
    }

    @Autowired
    ShiPinDao shiPinDao;

    //点击播放按钮，开始播放视频
    @GetMapping("/video")
    public String videoPlay() {

        return "video";
    }
    //@Autowired
    private ShiPinServiceImpl shiPinService;
    //查询
    @RequestMapping("/videoList")
    public String vediosDaquan(Model model,Model model2){
        System.out.print("查询视频"+"\n");
        List<Shipin> shipins=shiPinService.selectShipin();
        System.out.print("查询到的视频数量=="+shipins.size()+"\n");
        model.addAttribute("Shipins", shipins);
        //model2.addAttribute("ShipinsPath", url);
        model2.addAttribute("path1", "video/");
        return "/video";
    }

    //分页查询视频
    @RequestMapping("/videoListPage")
    public String videosPageList(@RequestParam("pageNow") int pageNow, Model model1, Model model2,Model model3,Model model4){
        System.out.println("分页查询开始："+pageNow);
        List<Shipin> list = shiPinService.VideoListPage(pageNow);
        System.out.println("第一个视频是"+list.get(0).getName());
        int page = shiPinService.getPage();
        List<String> tagall = shiPinDao.selectAllTag();
        model1.addAttribute("tagall",tagall);
        model1.addAttribute("page",page);
        model2.addAttribute("pageNow",pageNow);
        model3.addAttribute("list",list);
        model4.addAttribute("path1", "video/");

        return "videoListPage";
    }

    //查找标签为xx，名字含xx的视频
    @RequestMapping("/selectVideo")
    public String selectVideo(@RequestParam("selectName") String videoName,@RequestParam("selectTag") String videoTag,@RequestParam("pageNow") int pageNow, Model model1, Model model2,Model model3,Model model4,Model model5,Model model6,Model model7) throws SQLException {
        System.out.println("查找视频开始");
        String sql = "SELECT *" +
                " FROM shipins" +
                " where shipins.name like \"%"+videoName+"%\" and shipins.tag=\""+videoTag+"\" ";
        List<Shipin> list = shiPinService.selectVideo(videoName,videoTag,pageNow);
//        System.out.println("查询名字有:"+videoName+"标签为:"+videoTag+" 的视频"+pageNow);
        System.out.println("查询搭配的第一个视频是"+list.get(0).getName());
        int page = shiPinService.getSelectPage(videoName,videoTag);
        model1.addAttribute("page",page);
        model2.addAttribute("pageNow",pageNow);
        model3.addAttribute("list",list);
        model4.addAttribute("path1", "video/");
        model5.addAttribute("pd",1);
        model6.addAttribute("videoName",videoName);
        model7.addAttribute("videoTag",videoTag);
        return "videoListPage";
    }

}

