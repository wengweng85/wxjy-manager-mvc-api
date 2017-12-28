package com.insigma.mvc.serviceimp.api.API_MGMT_001_002;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.UUIDGenerator;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.api.API_MGMT_001_002.SysApiChannelMapper;
import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiChannelInterface;
import com.insigma.mvc.model.SysApiInterface;
import com.insigma.mvc.service.api.API_MGMT_001_002.SysApiChannelService;

/**
 *
 * 渠道管理接口实现
 * @author wengsh
 *
 */
@Service
public class SysApiChannelServiceImpl  extends MvcHelper<SysApiChannel> implements SysApiChannelService {

	
	@Resource
	private SysApiChannelMapper  sApiChannelMapper;
	
	
	
	@Override
	public HashMap<String, Object> getList(SysApiChannel sApiChannel) {
		PageHelper.offsetPage(sApiChannel.getOffset(), sApiChannel.getLimit());
		List<SysApiChannel> list=sApiChannelMapper.getList(sApiChannel);
		PageInfo<SysApiChannel> pageinfo = new PageInfo<SysApiChannel>(list);
		return this.success_hashmap_response(pageinfo);
	}


	@Override
	@Transactional
	public AjaxReturnMsg<String> saveData(SysApiChannel sApiChannel) {
		//判断是否更新 新增
		if(StringUtils.isEmpty(sApiChannel.getChannelId())){
			sApiChannel.setChannelAppkey(UUIDGenerator.generate());
			sApiChannel.setChannelStatus("-1");//待审核
			sApiChannel.setAddtime(new Date());//创建时间
			int insertnum=sApiChannelMapper.insert(sApiChannel);
			if(insertnum==1){
				return this.success("新增成功");
			}else{
				return this.error("新增成功,请确认此服务是否已经被删除");
			}
		}else{
			int updatenum=sApiChannelMapper.updateByPrimaryKeySelective(sApiChannel);
			if(updatenum==1){
				return this.success("更新成功");
			}else{
				return this.error("更新失败,请确认此服务是否已经被删除");
			}
		}
	}


	@Override
	public SysApiChannel getSApiChannelById(String interfaceId) {
		return sApiChannelMapper.selectByPrimaryKey(interfaceId);
	}


	@Override
	public SysApiChannel getSApiChannelDataById(String interfaceId) {
		return sApiChannelMapper.selectDataByPrimaryKey(interfaceId);
	}


	@Override
	@Transactional
	public AjaxReturnMsg<String> updatestatus(SysApiChannel sApiChannel) {
		int updatenum=sApiChannelMapper.updateByPrimaryKeySelective(sApiChannel);
		if(updatenum==1){
			return this.success("更新成功");
		}else{
			return this.error("更新失败,请确认此服务是否已经被删除");
		}
	}

	@Override
	public List<SysApiInterface> getApiInterfaceTree(String channelid) {
		return sApiChannelMapper.getApiInterfaceTree(channelid);
	}

	@Override
	@Transactional
	public AjaxReturnMsg<String> saveChannelInterface(SysApiChannel sApiChannel) {
		sApiChannelMapper.deleteChannelInterfaceByChannelId(sApiChannel.getChannelId());
		String[] selectnodes= sApiChannel.getSelectnodes().split(",");
		List<SysApiChannelInterface> list=new ArrayList<SysApiChannelInterface>();
		for(int i=0;i<selectnodes.length;i++){
			SysApiChannelInterface temp=new SysApiChannelInterface();
			temp.setChannelId(sApiChannel.getChannelId());
			temp.setInterfaceId(selectnodes[i]);
			temp.setIsvalid("1");
			temp.setAddtime(new Date());
			list.add(temp);
		}
		if(list.size()>0){
			sApiChannelMapper.batIinsertChannelInterface(list);
		}	
	   return this.success("渠道服务更新成功");
	}


	@Override
	public SysApiChannel getApiChannelByAppkey(String appkey) {
		return sApiChannelMapper.getApiChannelByAppkey(appkey);
	}


}