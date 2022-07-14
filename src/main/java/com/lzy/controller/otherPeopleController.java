package com.lzy.controller;

import com.lzy.Dao.*;
import com.lzy.Model.LeaveWord;
import com.lzy.Model.LeaveWordAndTouxiang;
import com.lzy.Model.Shipin;
import com.lzy.Model.collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "test", urlPatterns = "/ShowAllPersonServlet")
@Controller
public class otherPeopleController extends HttpServlet {
    @Autowired
    ShiPinDao shiPinDao;
    @Autowired
    CollectDao collectDao;
    @Autowired
    AccountMsgDao accountMsgDao;
    @Autowired
    ShenHeShiPinDao shenHeShiPinDao;
    @Autowired
    LeaveWordDao leaveWordDao;
    @Autowired
    TouXiangDao touXiangDao;
    @RequestMapping("/otherPeople")
    public String otherTheme(HttpSession session, Model model, @Param("otherAccount") String otherAccount){
        String otherName = accountMsgDao.selectUserName(otherAccount);
        model.addAttribute("otherAccount", otherAccount);
        System.out.println("他的账户是"+otherAccount);
        model.addAttribute("otherName",otherName);
        model.addAttribute("otherTouxiang",touXiangDao.selectTouxiang(otherAccount));
        System.out.println("他的头像是"+touXiangDao.selectTouxiang(otherAccount));
        String otherSign = accountMsgDao.selectSign(otherAccount);
        model.addAttribute("othersign", otherSign);
        List<Shipin> myVideoList = shiPinDao.selectMyVideo(otherAccount);
        model.addAttribute("shipinlist",myVideoList);
        List<LeaveWordAndTouxiang> myLeaveWordAndTouxiang = new ArrayList();
        List<LeaveWord> myLeaveWord = leaveWordDao.selectLvMsg(otherAccount);
        List<collect> folderlist = collectDao.selectFolder(otherAccount);
        model.addAttribute("collectfolder", folderlist);
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
        return "/otherPeople";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

            PrintWriter out = response.getWriter();
            out.println("7777");
    }

    //打开收藏
    @RequestMapping("/getOtherCollect")
    public String getCollect(Model model, String folderId, HttpSession session,String otherAccount){
        List<Shipin> collectList = collectDao.selectCollectVideo(folderId);
        System.out.println(collectList);
        model.addAttribute("collectList",collectList);
        //model.addAttribute("pd", true);   //用于判断
        session.setAttribute("pd",false);
        model.addAttribute("otherAccount",otherAccount);
        return "otherPeople::myCollectRefresh";
    }
    //退出收藏夹
    @RequestMapping("/exitOtherFolder")
    public String exitOtherFolder(Model model,HttpSession session,String otherAccount){
        List<collect> folderlist = collectDao.selectFolder(otherAccount);
        model.addAttribute("collectfolder", folderlist);
        model.addAttribute("collectList",null);
        //model.addAttribute("pd", null);
        session.setAttribute("pd",true);
        model.addAttribute("otherAccount",otherAccount);
        return "otherPeople::myCollectRefresh";
    }

}
