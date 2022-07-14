package com.lzy.controller;

import com.lzy.Dao.*;
import com.lzy.Model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class individualController {
    @Autowired
    CollectDao collectDao;
    @Autowired
    ShiPinDao shiPinDao;
    @Autowired
    AccountMsgDao accountMsgDao;
    @Autowired
    ShenHeShiPinDao shenHeShiPinDao;
    @Autowired
    LeaveWordDao leaveWordDao;
    @Autowired
    TouXiangDao touXiangDao;
    @RequestMapping("/mySelf")
    public String mySelf(HttpSession session, Model model){
        String author = (String) session.getAttribute("userAccount");
        System.out.println("作者是:"+author);
        List<Shipin> myVideoList = shiPinDao.selectMyVideo(author);
        List<ShenheShipin> myShenheVideoList = shenHeShiPinDao.selectMyShenheVideo(author);
        String mysign = accountMsgDao.selectSign(author);
        System.out.println("当前签名:"+mysign);
        List<collect> folderlist = collectDao.selectFolder(author);
        model.addAttribute("collectfolder", folderlist);
        model.addAttribute("shipinlist",myVideoList);
        model.addAttribute("mysign",mysign);
        model.addAttribute("path1","video/");
        model.addAttribute("shenheList",myShenheVideoList);
        if(author.equals("admin")){
            List<ShenheShipin> allList = shenHeShiPinDao.allShenhe();
            System.out.println("当前可审核视频==>"+allList);
            model.addAttribute("allshenheList",allList);
        }
        List<LeaveWordAndTouxiang> myLeaveWordAndTouxiang = new ArrayList();
        List<LeaveWord> myLeaveWord = leaveWordDao.selectLvMsg(author);
        for(LeaveWord MLW : myLeaveWord){
            System.out.println("当前留言");
            String touxaingName = touXiangDao.selectTouxiang(MLW.getSendaccount());
            String sender = MLW.getSendaccount();
            String accepter = MLW.getAcceptaccount();
            String content = MLW.getContent();
            String senderName = accountMsgDao.selectUserName(MLW.getSendaccount());
            LeaveWordAndTouxiang LWAT = new LeaveWordAndTouxiang(sender,accepter,content,touxaingName,senderName);
            myLeaveWordAndTouxiang.add(LWAT);
        }
        model.addAttribute("leaveword",myLeaveWordAndTouxiang);
        session.setAttribute("pd",true);

        return "mySelf";
    }
    @RequestMapping("/refreshSign")
    public String refreshSign(String newsign,HttpSession session){
        String account = (String) session.getAttribute("userAccount");
        System.out.println(account+"的新签名是:"+newsign);
        accountMsgDao.updateSign(newsign,account);

        return "mySelf";
    }
    @RequestMapping("/passAudit")
    public String passAudit(String targetVideoId){
        System.out.println("审核通过的视id频是:" + targetVideoId);
        //将数据库审核表的视频移动到视频表中
        ShenheShipin video = shenHeShiPinDao.selectVideoId(targetVideoId);
        System.out.println("审核通过的视频:"+video);
        String fileName = video.getVname();
        shiPinDao.transferVideo(video.getTitle(),video.getAuthor(),video.getTag(),video.getVname(),video.getLujing());
        if(shenHeShiPinDao.delShenheVideoId(targetVideoId)){
            System.out.println("已从审核队列删除该视频信息");
            String delPath = "C:/Users/admin/Desktop/文件夹/大学课程/springboot/springboot-02-web/src/main/resources/static/shenHeVideo/" + fileName;
            String newPath = "C:/Users/admin/Desktop/文件夹/大学课程/springboot/springboot-02-web/src/main/resources/static/Video/" + fileName;

            File file = new File(delPath);
            if(file.isFile() && file.exists()){
                file.renameTo(new File(newPath));
                System.out.println("文件: "+fileName+" 文件移动成功");
            }else {
                System.out.println("文件: "+fileName+" 文件移动删除失败");
            }
        };

        return "/mySelf";
    }

    @RequestMapping("/submitLeaveWord")
    public void submitLeaveWord(@RequestParam("leavewordmsg") String msg, @RequestParam("senderAccount") String senderAccount, @RequestParam("acceptAccount") String acceptAccount, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(senderAccount+"给"+acceptAccount+"的新留言:"+msg);
        accountMsgDao.insertLeaveWord(senderAccount,acceptAccount,msg);
        String s = "/otherPeople?otherAccount=" + acceptAccount;
        PrintWriter out = response.getWriter();
        if(acceptAccount.equals(senderAccount)){
            out.print("<script>"
                    +"function leaveWord(){\n" +
                    "    window.location.href='mySelf';\n" +
                    "}" +
                    "var t1 = window.setTimeout(leaveWord,1000);"
                    +"</script>"
            );
            return;
        }
        out.print("<script>"
                +"function leaveWord(){\n" +
                "    window.location.href='otherPeople?otherAccount="+acceptAccount+"';\n" +
                "}" +
                "var t1 = window.setTimeout(leaveWord,1000);"
                +"</script>"
        );
    }
//打开收藏
    @RequestMapping("/getCollect")
    public String getCollect(Model model, String folderId, HttpSession session){
        List<Shipin> collectList = collectDao.selectCollectVideo(folderId);
        System.out.println(collectList);
        model.addAttribute("collectList",collectList);
        //model.addAttribute("pd", true);   //用于判断
        session.setAttribute("pd",false);
        return "mySelf::myCollectRefresh";
    }
    //退出收藏夹
    @RequestMapping("/exitFolder")
    public String exitFolder(Model model,HttpSession session){
        String author = String.valueOf(session.getAttribute("userAccount"));
        List<collect> folderlist = collectDao.selectFolder(author);
        model.addAttribute("collectfolder", folderlist);
        model.addAttribute("collectList",null);
        //model.addAttribute("pd", null);
        session.setAttribute("pd",true);
        return "mySelf::myCollectRefresh";
    }

    @RequestMapping("/newFolder")
    public String newFolder(String account,String folderName,Model model,HttpSession session){
        collectDao.newFolder(folderName,account);
        List<collect> folderlist = collectDao.selectFolder(account);
        model.addAttribute("collectfolder", folderlist);
        model.addAttribute("collectList",null);
        session.setAttribute("pd",true);
        return "mySelf::myCollectRefresh";
    }
}
