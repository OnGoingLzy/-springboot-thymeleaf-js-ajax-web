package com.lzy.Dao;
import com.lzy.Model.Shipin;
import com.lzy.Model.collect;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CollectDao {
    @Select({"select * from collectfolder where author='${author}'"})
    public List<collect> selectFolder(@Param("author") String author);

    @Select({"select * from shipins where shipins.id in (select videoId from allcollect where inFolderId = '${folderId}')"})
    public List<Shipin> selectCollectVideo(@Param("folderId") String folderId);

    @Insert({"insert into collectfolder (collectFolderName,author) VALUES('${folderName}','${author}');"})
    public void newFolder(@Param("folderName") String folderName,@Param("author") String author);
}
