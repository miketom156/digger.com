package com.digger.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.model.Pay;
import com.digger.model.Recharge;
import com.digger.service.IPayService;
import com.digger.service.IRechargeService;
import com.digger.service.impl.PayService;
import com.digger.service.impl.RechargeService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RechargeAction extends ActionSupport {
	
	private Recharge recharge;
	private IRechargeService rechargeService;
	private IPayService payService;
	public void setRecharge(Recharge recharge) {
		this.recharge = recharge;
	}
	public Recharge getRecharge() {
		return recharge;
	}
	public RechargeAction(){
		rechargeService=new RechargeService();
		payService=new PayService();
	}
	public String saveRecharge()throws Exception{
		if(recharge.getRecharge().equals("")){
			addFieldError("*", "充值卡号不能为空！");
			return ERROR;
		}else{
			String sql=" where recharge='"+recharge.getRecharge()+"'";
			Recharge r=rechargeService.selectRecharge(sql);
			if(r!=null){
				addFieldError("*", "充值卡号已经存在！");
				return ERROR;
			}else{
				rechargeService.saveRecharge(recharge);
				addFieldError("*", "充值卡信息添加成功！");
				return SUCCESS;
			}
		}
	}
	public String selectRecharge()throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		Pay p=(Pay) session.getAttribute("pay");
		if(recharge.getRecharge().equals("")){
			addFieldError("*", "充值卡号不能为空！");
			return ERROR;
		}else{
			String sql=" where recharge='"+recharge.getRecharge()+"'";
			Recharge r=rechargeService.selectRecharge(sql);
			if(r==null){
				addFieldError("*", "充值卡号不存在，请重新输入！");
				return ERROR;
			}else{
				Double money;
				Double a,b;
				a=r.getMoney();
				b=p.getMoney();
				System.out.println(a);
				System.out.println(b);
				money=a+b;
				System.out.println(money);
				p.setMoney(money);
				payService.updatePay(p);
				rechargeService.deleteRecharge(r);
				Pay pa=payService.selectPay(p.getUser_id());
				session.removeAttribute("pay");
				session.setAttribute("pay", pa);
				addFieldError("提示信息", "充值成功");
				return SUCCESS;
				}
			}
		}

}
