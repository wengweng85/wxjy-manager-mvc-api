package com.insigma.mvc.service.api.API_MGMT_001_002;

import java.util.HashMap;
import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiInterface;


/**
 * ÇþµÀ¹ÜÀíservice
 * @author wengsh
 *
 */
public interface SysApiChannelService {
	
	public HashMap<String,Object> getList( SysApiChannel sApiChannel );
	
	public AjaxReturnMsg<String>saveData(SysApiChannel sApiChannel);
	
	public AjaxReturnMsg<String>updatestatus(SysApiChannel sApiChannel);
	
	public SysApiChannel getSApiChannelById(String interfaceId);
	
	public SysApiChannel getSApiChannelDataById(String interfaceId);
	
	public List<SysApiInterface> getApiInterfaceTree(String channelid);
	
	public AjaxReturnMsg<String> saveChannelInterface(SysApiChannel sApiChannel);
	
	public SysApiChannel  getApiChannelByAppkey(String appkey);
	
}
