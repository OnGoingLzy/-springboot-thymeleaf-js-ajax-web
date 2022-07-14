package com.lzy.Dao;
import com.lzy.Model.LeaveWord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LeaveWordDao {
    @Select({"select * from leaveword where acceptaccount = '${account}'"})
    public List<LeaveWord> selectLvMsg(@Param("account") String account);
}
