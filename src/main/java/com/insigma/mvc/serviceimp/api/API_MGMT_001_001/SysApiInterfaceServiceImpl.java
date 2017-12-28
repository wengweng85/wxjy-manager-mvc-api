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
 * �������ӿ�ʵ��
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
		//��������
		if(StringUtils.isNotEmpty(sApiInterface.getInterfaceType())){
			sApiInterface.setA_interfaceType(sApiInterface.getInterfaceType().split(","));
		}
		
		//��������
		if(StringUtils.isNotEmpty(sApiInterface.getInterfaceNetwork ())){
			sApiInterface.setA_interfaceNetwork(sApiInterface.getInterfaceNetwork().split(","));
		}
		
		//����Э��
		if(StringUtils.isNotEmpty(sApiInterface.getInterfacePotocol())){
			sApiInterface.setA_interfacePotocol(sApiInterface.getInterfacePotocol().split(","));
		}
		
		//����״̬
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
		//�ж��Ƿ���� ����
		if(StringUtils.isEmpty(sApiInterface.getInterfaceId())){
			int insertnum=sApiInterfaceMapper.insert(sApiInterface);
			if(insertnum==1){
				return this.success("�����ɹ�");
			}else{
				return this.error("�����ɹ�,��ȷ�ϴ˷����Ƿ��Ѿ���ɾ��");
			}
		}else{
			int updatenum=sApiInterfaceMapper.updateByPrimaryKeySelective(sApiInterface);
			if(updatenum==1){
				return this.success("���³ɹ�");
			}else{
				return this.error("����ʧ��,��ȷ�ϴ˷����Ƿ��Ѿ���ɾ��");
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
			return this.success("���³ɹ�");
		}else{
			return this.error("����ʧ��,��ȷ�ϴ˷����Ƿ��Ѿ���ɾ��");
		}
	}


	@Override
	public SysApiInterface selectByUrl(String url,String appkey) {
		return sApiInterfaceMapper.selectByUrl(url,appkey);
	}
	
}