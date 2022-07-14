package com.lzy.controller;

import com.github.pagehelper.PageHelper;
import com.lzy.Dao.ShenHeShiPinDao;
import com.lzy.Dao.TouXiangDao;
import com.lzy.Model.*;
import com.lzy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
//@RequestMapping("/file")
public class MyfileCOntroller {

    @Autowired
    TouXiangDao touXiangDao;

    @Autowired
    ShenHeShiPinDao shenHeShiPinDao;

    @Autowired //注解 自动装配不能new shiPinService()
    private ShiPinService shiPinService;

    @Autowired
    private imageService imageService;

    private String  url;

    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    //@ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file,Model model) {

        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            model.addAttribute("sbmsg","上传失败!文件不能为空");
            return "/file";
        }
        /*参考步骤：
        1、获取文件名中最后一次出现"."号的位置
        2、根据"."号的位置，获取文件的后缀
        3、判断"."号位置及文件后缀名*/
        //获取文件名中最后一次出现"."号的位置
        String s = ".";
        int index = file.getOriginalFilename().lastIndexOf(s);
        // 获取文件的后缀
        String prefix =file.getOriginalFilename().substring(index+1);
        System.out.println(file.getOriginalFilename());
        System.out.println(prefix);
        // 判断必须包含"."号，且不能出现在首位，同时后缀名为"mp4"
        if ( index !=0 && !prefix.equals("mp4"))
        {
            model.addAttribute("sbmsg","上传失败!只能上传mp4文件");
            System.out.println("只能上传mp4文件！！");
            return "/file";
        }


        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.print("上传的文件名为: "+fileName+"\n");

        fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");


        //加个时间戳，尽量避免文件名称重复
        String path = "C:/Users/admin/Desktop/文件夹/大学课程/springboot/springboot-02-web/src/main/resources/static/video/" +fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            model.addAttribute("sbmsg","上传失败!文件已存在");
            System.out.println("文件已存在！！");
            return "/file";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            //url="http://你自己的域名/映射地址/"+fileName;//正式项目
            url="http://localhost:80/video/"+fileName;//url直接访问项目
            int jieguo= shiPinService.insertUrl(fileName,path,url);
            System.out.print("插入结果"+jieguo+"\n");
            System.out.print("保存的完整url===="+url+"\n");

        } catch (IOException e) {
            model.addAttribute("sbmsg", "上传失败!!请重新上传");
            return "/file";
        }
        model.addAttribute("cgmsg", "上传成功!!");
        return "/file";
    }

    @RequestMapping(value = "/uploadTouxiang",produces="application/json;charset=UTF-8")
    public String uploadTouxiang(HttpSession session, @RequestParam("fileName") MultipartFile file, Model model){
        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            model.addAttribute("sbmsg","上传失败!文件不能为空");
            return "/touxiang";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        System.out.print("上传的文件名为: "+fileName+"\n");
        fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");
        String path = "C:/Users/admin/Desktop/文件夹/大学课程/springboot/springboot-02-web/src/main/resources/static/image/touxiang/" +fileName;
        System.out.print("保存文件绝对路径"+path+"\n");
        touXiangDao.delTouxiang((String)session.getAttribute("userAccount"));
        String oldTouxiangName = (String)session.getAttribute("oldTouxiang");
        String delpath = "C:/Users/admin/Desktop/文件夹/大学课程/springboot/springboot-02-web/src/main/resources/static/image/touxiang/" + oldTouxiangName ;
        System.out.println("正在删除旧头像"+oldTouxiangName);
        File delimage = new File(delpath);
        if(delimage.exists()){

            if(delimage.delete()){
                System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        }else{
            System.out.println("文件不存在!");
        }
        //创建文件路径
        File dest = new File(path);
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            url="http://localhost:80/image/touxiang/"+fileName;
            String userAccount = (String) session.getAttribute("userAccount");
            System.out.println("上传者为:"+userAccount);
            int jieguo= imageService.insertImageUrl(userAccount,fileName,url);
            System.out.print("插入结果"+jieguo+"\n");
            System.out.print("保存的完整url===="+url+"\n");

        } catch (IOException e) {
            model.addAttribute("sbmsg", "上传失败!!请重新上传");
            return "/touxiang";
        }
        model.addAttribute("cgmsg", "上传成功!!点我返回主页");
        session.removeAttribute("userTouxiang");
        session.setAttribute("userTouxiang", fileName);
        return "/touxiang";

    }

    //查询
    @RequestMapping("/chaxun")
    public String huizhiDuanxin(Model model){
        System.out.print("查询视频"+"\n");
        List<Shipin> shipins=shiPinService.selectShipin();
        System.out.print("查询到的视频数量=="+shipins.size()+"\n");
        model.addAttribute("Shipins", shipins);

        return "/filelist";
    }

    @RequestMapping("/tougao")
    public String tougao(String videoTitle,String tag, HttpSession session){
        //得到ajax的数据，参数写text数据的key值，就能得到value值
        String fileName = (String) session.getAttribute("fileName");
        System.out.println("分类:"+tag+videoTitle+"它的文件名:"+fileName);
        System.out.println("作者"+(String) session.getAttribute("userName2"));
        String author = (String) session.getAttribute("userAccount");
        String lujing = "C:/Users/admin/Desktop/文件夹/大学课程/springboot/springboot-02-web/src/main/resources/static/shenHeVideo/"+fileName;
        shenHeShiPinDao.insertShenheShipin(fileName, videoTitle, tag, lujing, author);

        return "/file";
    }





}
