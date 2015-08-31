package com.digger.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.model.Pay;
import com.digger.model.User;
import com.digger.service.IPayService;
import com.digger.service.IUserService;
import com.digger.service.impl.PayService;
import com.digger.service.impl.UserService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PayAction extends ActionSupport {

	private Pay pay;
	private IPayService payService;
	private IUserService userService;
	private String payword;
	private String words;

	public void setPay(Pay pay) {
		this.pay = pay;
	}
	public Pay getPay() {
		return pay;
	}
	public PayAction(){
		payService=new PayService();
		userService=new UserService();
	}
	public void setPayword(String payword) {
		this.payword = payword;
	}
	public String getPayword() {
		return payword;
	}
	public String savePay()throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		User u=(User) session.getAttribute("user");
		if(pay.getPayword().equals("")||getPayword().equals("")){
			addFieldError("*", "支付密码和确认密码不能为空！");
			return ERROR;
		}else{
			if(!pay.getPayword().equals(getPayword())){
				addFieldError("*", "支付密码和确认密码不一致！");
				return ERROR;
			}else{
				u.setRights(3);
				pay.setUser_id(u.getUser_id());
				payService.savePay(pay);
				userService.updateUser(u);
				session.removeAttribute("user");
				session.removeAttribute("mescount");
				session.removeAttribute("treeInfo");
				session.removeAttribute("user_info");
				session.removeAttribute("pay");
				return SUCCESS;
			}
		}
	}
	public String updatePayword()throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		Pay p=(Pay) session.getAttribute("pay");
		if(pay.getPayword().equals("")||getPayword().equals("")){
			addFieldError("*", "支付密码和确认密码不能为空！");
			return ERROR;
		}else{
			if(!pay.getPayword().equals(getPayword())){
				addFieldError("*", "支付密码和确认密码不一致！");
				return ERROR;
			}else{
				Pay pa=payService.selectPay(p.getUser_id());
				if(!pa.getPayword().equals(getWords())){
					addFieldError("*", "原密码错误！");
					return ERROR;
				}else{
					p.setPayword(pay.getPayword());
					payService.updatePay(p);
					addFieldError("提示信息", "密码更改成功");
					return SUCCESS;
				}
			}
		}
		
	}
	public String pay()throws Exception{
		
		return SUCCESS;
	}
	public String payinfo()throws Exception{
		
		return SUCCESS;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public String getWords() {
		return words;
	}
}
