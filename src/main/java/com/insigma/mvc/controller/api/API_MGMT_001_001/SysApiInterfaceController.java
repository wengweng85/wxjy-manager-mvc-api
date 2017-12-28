package com.insigma.mvc.controller.api.API_MGMT_001_001;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.DemoAc01;
import com.insigma.mvc.model.SysApiInterface;
import com.insigma.mvc.service.api.API_MGMT_001_001.SysApiInterfacService;


/**
*  服务管理页面
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/api")
public class SysApiInterfaceController extends MvcHelper<DemoAc01> {
	
	@Resource
	private SysApiInterfacService sApiInterfacService;
	
	
	/**
	 * 跳转至服务主页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("api/API_MGMT_001_001/apiList");
        return modelAndView;
	}
	
	
	/**
	 * 获取服务列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public HashMap<String,Object> getList(HttpServletRequest request,Model model, SysApiInterface sApiInterface ) throws Exception {
		return  sApiInterfacService.getList(sApiInterface);
	}
	
	
	/**
	 * 跳转至服务新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("api/API_MGMT_001_001/apiAdd");
        return modelAndView;
	}
	
	/**
	 * 跳转至服务编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEdit/{interfaceId}")
	public ModelAndView toEdit(HttpServletRequest request,Model model,@PathVariable String interfaceId) throws Exception {
		ModelAndView modelAndView=new ModelAndView("api/API_MGMT_001_001/apiEdit");
		SysApiInterface sApiInterface=sApiInterfacService.getSApiInterfaceById(interfaceId);
		modelAndView.addObject("sApiInterface",sApiInterface);  
        return modelAndView;
	}
	
	
	/**
	 * 跳转至服务查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/toView/{interfaceId}")
	public ModelAndView toView(HttpServletRequest request,Model model,@PathVariable String interfaceId) throws Exception {
		ModelAndView modelAndView=new ModelAndView("api/API_MGMT_001_001/apiView");
		SysApiInterface sApiInterface=sApiInterfacService.getSApiInterfaceDataById(interfaceId);
		modelAndView.addObject("sApiInterface",sApiInterface);  
        return modelAndView;
	}
	/**
	 * 更新或保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/savedata")
	@ResponseBody
	public AjaxReturnMsg<String> savedata(HttpServletRequest request,Model model,@Valid SysApiInterface sApiInterface,BindingResult result) throws Exception {
		//检验输入
		if (result.hasErrors()){
			return validate(result);
		}
		return sApiInterfacService.saveData(sApiInterface);
	}
	
	/**
	 * 服务状态更新
	 * @param request
	 * @return
	 */
	@RequestMapping("/updatestatus")
	@ResponseBody
	public AjaxReturnMsg<String> updatestatus(HttpServletRequest request,Model model,SysApiInterface sApiInterface) throws Exception {
		return sApiInterfacService.updatestatus(sApiInterface);
	}

}