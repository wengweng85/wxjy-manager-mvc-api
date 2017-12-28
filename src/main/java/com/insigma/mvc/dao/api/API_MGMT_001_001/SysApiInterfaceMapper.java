package com.insigma.mvc.dao.api.API_MGMT_001_001;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.SysApiInterface;

public interface SysApiInterfaceMapper {
    
	List<SysApiInterface> getList(SysApiInterface sApiInterface );
	
	int deleteByPrimaryKey(String interfaceId);

    int insert(SysApiInterface record);

    int insertSelective(SysApiInterface record);

    SysApiInterface selectByPrimaryKey(String interfaceId);
    
    SysApiInterface selectNameByPrimaryKey(String interfaceId);

    int updateByPrimaryKeySelective(SysApiInterface record);

    int updateByPrimaryKey(SysApiInterface record);
    
    SysApiInterface selectByUrl(@Param("url") String url,@Param("appkey") String appkey);
   
}