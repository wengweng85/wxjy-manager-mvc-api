package com.insigma.mvc.serviceimp.api.API_MGMT_001_001;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.api.API_MGMT_001_001.SysApiInterfaceMapper;
import com.insigma.mvc.model.SysApiInterface;
import com.insigma.mvc.service.api.API_MGMT_001_001.SysApiInterfacService;

/**
 *
 * 服务管理接口实现
 * @author wengsh
 *
 */
@Service
public class SysApiInterfaceServiceImpl  extends MvcHelper<SysApiInterface> implements SysApiInterfacService {

	@Resource
	private SysApiInterfaceMapper  sApiInterfaceMapper;
	
	
	@Override
	public HashMap<String, Object> getList(SysApiInterface sApiInterface) {
		PageHelper.offsetPage(sApiInterface.getOffset(), sApiInterface.getLimit());
		//服务类型
		if(StringUtils.isNotEmpty(sApiInterface.getInterfaceType())){
			sApiInterface.setA_interfaceType(sApiInterface.getInterfaceType().split(","));
		}
		
		//服务网络
		if(StringUtils.isNotEmpty(sApiInterface.getInterfaceNetwork ())){
			sApiInterface.setA_interfaceNetwork(sApiInterface.getInterfaceNetwork().split(","));
		}
		
		//服务协议
		if(StringUtils.isNotEmpty(sApiInterface.getInterfacePotocol())){
			sApiInterface.setA_interfacePotocol(sApiInterface.getInterfacePotocol().split(","));
		}
		
		//服务状态
		if(StringUtils.isNotEmpty(sApiInterface.getInterfaceStatus())){
			sApiInterface.setA_interfaceStatus(sApiInterface.getInterfaceStatus().split(","));
		}
		List<SysApiInterface> list=sApiInterfaceMapper.getList(sApiInterface);
		PageInfo<SysApiInterface> pageinfo = new PageInfo<SysApiInterface>(list);
		return this.success_hashmap_response(pageinfo);
		
	}


	@Override
	@Transactional
	public AjaxReturnMsg<String> saveData(SysApiInterface sApiInterface) {
		//判断是否更新 新增
		if(StringUtils.isEmpty(sApiInterface.getInterfaceId())){
			int insertnum=sApiInterfaceMapper.insert(sApiInterface);
			if(insertnum==1){
				return this.success("新增成功");
			}else{
				return this.error("新增成功,请确认此服务是否已经被删除");
			}
		}else{
			int updatenum=sApiInterfaceMapper.updateByPrimaryKeySelective(sApiInterface);
			if(updatenum==1){
				return this.success("更新成功");
			}else{
				return this.error("更新失败,请确认此服务是否已经被删除");
			}
		}
	}


	@Override
	public SysApiInterface getSApiInterfaceById(String interfaceId) {
		return sApiInterfaceMapper.selectByPrimaryKey(interfaceId);
	}


	@Override
	public SysApiInterface getSApiInterfaceDataById(String interfaceId) {
		return sApiInterfaceMapper.selectNameByPrimaryKey(interfaceId);
	}


	@Override
	@Transactional
	public AjaxReturnMsg<String> updatestatus(SysApiInterface sApiInterface) {
		int updatenum=sApiInterfaceMapper.updateByPrimaryKeySelective(sApiInterface);
		if(updatenum==1){
			return this.success("更新成功");
		}else{
			return this.error("更新失败,请确认此服务是否已经被删除");
		}
	}


	@Override
	public SysApiInterface selectByUrl(String url,String appkey) {
		return sApiInterfaceMapper.selectByUrl(url,appkey);
	}
	
}