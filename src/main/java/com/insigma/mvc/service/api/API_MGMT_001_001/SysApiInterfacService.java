package com.insigma.mvc.service.api.API_MGMT_001_001;

import java.util.HashMap;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysApiInterface;


/**
 * 服务管理service
 * @author wengsh
 *
 */
public interface SysApiInterfacService {
	
	public HashMap<String,Object> getList( SysApiInterface sApiInterface );
	
	public AjaxReturnMsg<String>saveData(SysApiInterface sApiInterface);
	
	public AjaxReturnMsg<String>updatestatus(SysApiInterface sApiInterface);
	
	public SysApiInterface getSApiInterfaceById(String interfaceId);
	
	public SysApiInterface getSApiInterfaceDataById(String interfaceId);
	
	public SysApiInterface selectByUrl(String url,String appkey);
	
	
}
