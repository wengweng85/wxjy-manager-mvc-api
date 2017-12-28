package com.insigma.mvc.dao.api.API_MGMT_001_002;

import java.util.List;

import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiInterface;
import com.insigma.mvc.model.SysApiChannelInterface;

public interface SysApiChannelMapper {
	
	List<SysApiChannel> getList(SysApiChannel sApiInterface );
	
    int deleteByPrimaryKey(String channelId);

    int insert(SysApiChannel record);

    int insertSelective(SysApiChannel record);

    SysApiChannel selectByPrimaryKey(String channelId);
    
    SysApiChannel selectDataByPrimaryKey(String channelId);

    int updateByPrimaryKeySelective(SysApiChannel record);

    int updateByPrimaryKey(SysApiChannel record);
    
    List<SysApiInterface> getApiInterfaceTree(String channelid);  
    
    int deleteChannelInterfaceByChannelId(String channelid);
    
    void batIinsertChannelInterface(List<SysApiChannelInterface> list);
    
    SysApiChannel  getApiChannelByAppkey(String appkey);
}