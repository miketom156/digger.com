package com.digger.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.model.Pageinfo;
import com.digger.model.Pay;
import com.digger.model.Trading_info;
import com.digger.model.User;
import com.digger.service.IPayService;
import com.digger.service.ITrading_infoService;
import com.digger.service.impl.PayService;
import com.digger.service.impl.Trading_infoService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Trading_infoAction extends ActionSupport {
	
	private Trading_info trading;
	private ITrading_infoService tradingService;
	private IPayService payService;
	private String isnotPay;
	private String passwords;
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	public String getPasswords() {
		return passwords;
	}

	public void setTrading(Trading_info trading) {
		this.trading = trading;
	}

	public Trading_info getTrading() {
		return trading;
	}
	public Trading_infoAction(){
		tradingService=new Trading_infoService();
		payService= new PayService();
	}
	public String selectTradingAll()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("user");
		String ispay=(String) request.getSession().getAttribute("ispay");
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		Pageinfo pageinfo=new Pageinfo();
		int v_currPage = 0;
		int v_pageSize = 0;
		String sql;
		if (currentPage == null) {
			v_currPage = 1;
		} else {
			v_currPage = Integer.parseInt(currentPage);
		}

		if (pageSize == null) {
			v_pageSize = 3;
		} else {
			v_pageSize = Integer.parseInt(pageSize);
		}
		if(ispay==null){
			sql=" where payer="+user.getUser_id();
			pageinfo=tradingService.queryAlltrading(v_currPage, v_pageSize, sql);
		}else{
			if(ispay.equals("是")){
				sql=" where IsN_pay=1 and payer="+user.getUser_id();
				pageinfo=tradingService.queryAlltrading(v_currPage, v_pageSize, sql);
			}else{
				sql=" where IsN_pay=0 and payer="+user.getUser_id();
				pageinfo=tradingService.queryAlltrading(v_currPage, v_pageSize, sql);
			}
		}
		request.setAttribute("tradinginfo", pageinfo);
		return SUCCESS;
	}
	public String selecttrading()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("user");
		String sql;
		if(getIsnotPay().equals("是")){
			sql=" where IsN_pay=1 and payer="+user.getUser_id();	
		}else{
		sql=" where IsN_pay=0 and payer="+user.getUser_id();}
		Pageinfo pageinfo=tradingService.queryAlltrading(1, 3, sql);
		request.setAttribute("tradinginfo", pageinfo);
		request.getSession().setAttribute("ispay", getIsnotPay());
		return SUCCESS;
		
	}
	public String trading()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		Trading_info t=tradingService.selectTradingId(trading.getPay_id());
		session.setAttribute("trading", t);
		return SUCCESS;
	}
	public String payTrading()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		Trading_info t=(Trading_info) session.getAttribute("trading");
		Pay p1=payService.selectPay(t.getPayer());
		Pay p2=payService.selectPay(t.getSellers());
		if(!p1.getPayword().equals(getPasswords())){
			addFieldError("*", "支付密码不正确!");
			return ERROR;
		}else{
		if(t.getMoney()>p1.getMoney()){
			addFieldError("*", "金额不足!");
			return ERROR;
		}else{
			Double a,b;
			a=p1.getMoney()-t.getMoney();
			b=p2.getMoney()+t.getMoney();
			p1.setMoney(a);
			p2.setMoney(b);
			t.setIsN_pay(1);
			payService.updatePay(p1);
			payService.updatePay(p2);
			tradingService.updateTrading(t);
			addFieldError("提示信息", "支付成功!");
			return SUCCESS;
		}
		}
	}

	public void setIsnotPay(String isnotPay) {
		this.isnotPay = isnotPay;
	}

	public String getIsnotPay() {
		return isnotPay;
	}

}
