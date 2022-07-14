package com.lzy.Dao;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountMsgDao {
    @Select({"select mysign from accountmessage where account='${account}'"})
    public String selectSign(@Param("account") String account);

    @Update({"update accountmessage set mySign = '${newsign}' where account='${account}'"})
    public void updateSign(@Param("newsign") String newsign,@Param("account") String account);

    @Select({"select name from accounttable where account = '${account}'"})
    public String selectUserName(@Param("account") String account);

    @Insert({"insert into leaveword (sendAccount,acceptAccount,content) values('${senderAccount}','${acceptAccount}','${msg}')"})
    public boolean insertLeaveWord(String senderAccount, String acceptAccount, String msg);
}
