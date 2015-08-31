package com.digger.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.util.Config;
import com.digger.util.ConvertCharacter;

import com.digger.model.Menu;
import com.digger.model.Pay;
import com.digger.model.User;
import com.digger.model.User_info;
import com.digger.service.IMenuService;
import com.digger.service.IMessageService;
import com.digger.service.IPayService;
import com.digger.service.IUserService;
import com.digger.service.IUser_infoService;
import com.digger.service.impl.MenuService;
import com.digger.service.impl.MessageService;
import com.digger.service.impl.PayService;
import com.digger.service.impl.UserService;
import com.digger.service.impl.User_infoService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class User_infoAction extends ActionSupport {
	/**
	 * 以模块化的方式操作Users类
	 */
	private User user;
	private User_info user_info;
	/**
	 * 传入IUser_infoService方法，调用login方法
	 */

	private IUserService userService;
	// private IUser_infoSevice user_infoService;
	private IUser_infoService user_infoService;
	private IMenuService menuService;
	private IPayService payService;
	private IMessageService messageService;

	public User_infoAction() {
		userService = new UserService();
		user_infoService = new User_infoService();
		menuService = new MenuService();
		payService = new PayService();
		messageService = new MessageService();

	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser_infoService(IUser_infoService user_infoService) {
		this.user_infoService = user_infoService;
	}

	public IUser_infoService getUser_infoService() {
		return user_infoService;
	}

	private String password;
	private String new_password;
	private String user_name;
	private String sex;
	private String year;
	private String month;
	private String day;
	private String s1;
	private String s2;
	private String user_password;
	private String user_password2;
	private String date;
	private String natives;
	private int age;
	private String brithday;
	private String[] hoody;
	private String hoodys = "";
	private String e_mail;
	private int tel;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setUser_name(String user_name) {
		// user_name=new ConvertCharacter().Convert(user_name);
		this.user_name = user_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setSex(String sex) {
		if (sex == "1") {
			sex = "男";
		} else
			sex = "女";
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYear() {
		return year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDay() {
		return day;
	}

	public void setS1(String s1) {
		// s1=new ConvertCharacter().Convert(s1);
		this.s1 = s1;
	}

	public String getS1() {
		return s1;
	}

	public void setS2(String s2) {
		// s2=new ConvertCharacter().Convert(s2);
		this.s2 = s2;
	}

	public String getS2() {
		return s2;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password2(String user_password2) {
		this.user_password2 = user_password2;
	}

	public String getUser_password2() {
		return user_password2;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public String getBrithday() {
		return brithday;
	}

	public void setHoody(String[] hoody) {
		this.hoody = hoody;
	}

	public String[] getHoody() {
		return hoody;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getTel() {
		return tel;
	}

	// 用户登录
	public String login() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();// 获得session
		String valid = validUser();// 验证用户
		if (valid != SUCCESS) {
			return valid;
		}
		String name = new ConvertCharacter().Convert(user.getUser_name());
		String mysql = " where user_name='" + name + "';";
		User u = userService.loginUser(mysql);// 获得用户对象
		if (u != null && user.getUser_password().equals(u.getUser_password())) {// 判断是否用户密码相同
			ArrayList<Menu> rights = menuService.queryAllRightByUID(u.getUser_id());
			User_info userinfo = new User_info();
			userinfo.setUser_id(u.getUser_id());
			userinfo = user_infoService.select(userinfo.getUser_id());
			Pay p = payService.selectPay(u.getUser_id());
			session.setAttribute("user", u);
			String treeCode = generateTree(rights);
			String sql = " where isn_read=0 and users=" + u.getUser_id();
			int count = messageService.messageCount(sql);
			session.setAttribute("mescount", count);
			session.setAttribute("treeInfo", treeCode);
			session.setAttribute("user_info", userinfo);
			session.setAttribute("pay", p);
			return SUCCESS;
		} else {
			addFieldError("**", "您输入的用户名或密码错误！");
			return LOGIN;
		}
	}

	public String selectuserinfo() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User u = (User) session.getAttribute("user");
		User_info userinfo = new User_info();
		userinfo.setUser_id(u.getUser_id());
		userinfo = user_infoService.select(userinfo.getUser_id());
		session.setAttribute("user_info", userinfo);
		return SUCCESS;
	}

	// 用户注册
	public String Regist() throws Exception {
		User u = new User();
		User_info u_info = new User_info();
		u.setUser_name(user_name);
		System.out.println(u.getUser_name());
		System.out.println(user_password2);
		u.setUser_password(user_password);
		if (u.getUser_name().equals(null) && u.getUser_password().equals(null)) {
			addFieldError("**", "用户名和密码不能为空");
		} else if (!u.getUser_password().equals(user_password2)) {
			addFieldError("@", "两密码不一致，不予注册");
			return INPUT;
		}
		String mysql = " where user_name='" + u.getUser_name() + "';";
		User use = userService.select(mysql);
		if (use != null) {
			addFieldError("**", "用户名存在，请重新注册！");
			return INPUT;
		}
		u.setRights(Config.defaultRidhts);
		userService.registUser(u);
		date = year + "-" + month + "-" + day;
		natives = s1 + s2;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date mydate = bartDateFormat.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(mydate.getTime());
		int i = checkAge();
		use = userService.select(mysql);
		u_info.setUser_id(use.getUser_id());
		u_info.setUser_name(u.getUser_name());
		u_info.setSex(sex);
		u_info.setBrithday(sqlDate);
		u_info.setAge(i);
		u_info.setNatives(natives);
		u_info.setProvince(s1);
		u_info.setCity(s2);
		user_infoService.regist(u_info);
		return SUCCESS;
	}

	// 年龄
	@SuppressWarnings("deprecation")
	public int checkAge() {
		date = year + "-" + month + "-" + day;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		java.util.Date nowDate = null;
		java.util.Date mydate = null;
		try {
			nowDate = formatter.parse(str);
			mydate = formatter.parse(date);
		} catch (Exception e) {
		}
		age = nowDate.getYear() - mydate.getYear();
		return age;
	}

	// 修改密码
	public String updatePassword() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();// 获得session对象
		User u = (User) session.getAttribute("user");// 从session中取出user对象
		if (user.getUser_password().equals("")) {
			addFieldError("*", "原密码不能为空");
			return INPUT;
		} else if (getPassword().equals("")) {
			addFieldError("@", "新密码不能为空");
			return INPUT;
		} else if (!user.getUser_password().equals(u.getUser_password())) {
			addFieldError("*", "原密码错误");
			return INPUT;
		} else {
			if (!getPassword().equals(getNew_password())) {
				addFieldError("*", "新密码和确认密码不一致");
				return INPUT;
			} else {
				u.setUser_password(getPassword());
				this.userService.updateUser(user);
				session.removeAttribute("user");
				addFieldError("@", "密码更改成功，请重新登录！");
				return SUCCESS;
			}
		}
	}

	// 退出登录
	public String logout() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();// 获得session对象
		session.removeAttribute("user");// 从session中移除user信息
		session.removeAttribute("mescount");
		session.removeAttribute("treeInfo");
		session.removeAttribute("user_info");
		session.removeAttribute("pay");
		return SUCCESS;
	}

	// 验证用户
	public String validUser() {
		if (user.getUser_name().equals("")
				&& user.getUser_password().equals("")) {
			addFieldError("**", "用户名和密码不能为空！");
			return INPUT;
		} else if (user.getUser_name().equals("")) {
			addFieldError("**", "用户名不能为空！");
			return INPUT;
		} else if (user.getUser_password().equals("")) {
			addFieldError("**", "用户密码不能为空！");
			return INPUT;
		}
		return SUCCESS;
	}

	// 修改用户信息
	@SuppressWarnings("deprecation")
	public String updateUser_info() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		user_info = (User_info) session.getAttribute("user_info");
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());
		java.util.Date mydate = bartDateFormat.parse(brithday);
		String str = bartDateFormat.format(curDate);
		java.util.Date nowDate = null;
		try {
			nowDate = bartDateFormat.parse(str);
		} catch (Exception e) {
		}
		if (nowDate.getDate() < mydate.getDate()) {
			addFieldError("*", "此生日不存在");
			return ERROR;
		} else {
			if (!e_mail.equals("")) {
				boolean flag = emailFormat(e_mail);
				if (flag == false) {
					addFieldError("*", "email格式不对");
					return ERROR;
				}
			}
			for (int i = 0; i < getHoody().length; i++) {

				hoodys += getHoody()[i];
				System.out.println(hoodys);
			}
			user_info.setSex(sex);
			user_info.setHoody(hoodys);
			System.out.println(user_info.getHoody());
			age = nowDate.getYear() - mydate.getYear();
			java.sql.Date sqlDate = new java.sql.Date(mydate.getTime());
			user_info.setBrithday(sqlDate);
			user_info.setE_mail(e_mail);
			user_info.setTel(tel);
			natives = s1 + s2;
			user_info.setNatives(natives);
			user_info.setProvince(s1);
			user_info.setCity(s2);
			user_info.setAge(age);
			user_infoService.update(user_info);
			addFieldError("提示信息", "有户资料更改成功");
			return SUCCESS;
		}
	}

	/**
	 * 生成页面构建菜单的代码
	 */
	private String generateTree(ArrayList<Menu> rights) {
		StringBuffer result = new StringBuffer();
		result.append("d = new dTree('d');");
		for (int i = 0; i < rights.size(); i++) {
			Menu menu = (Menu) rights.get(i);
			// 判断当前节点类型
			String menutype = menu.getMenu_type();
			if ("root".equals(menutype)) {
				result.append("d.add(" + menu.getMenu_id() + ","
						+ menu.getParent() + ",'<font color=\"blue\"><b>"
						+ menu.getMenu_name() + "</b></font>');");
			} else if ("foler".equals(menutype)) {
				result.append("d.add(" + menu.getMenu_id() + ","
						+ menu.getParent() + ",'" + menu.getMenu_name()
						+ "','','folderOpen.gif');");
			} else {
				result.append("d.add(" + menu.getMenu_id() + ","
						+ menu.getParent() + ",'" + menu.getMenu_name() + "','"
						+ menu.getUrl() + "','','mainFrame');");
			}
		}
		result.append("document.write(d);");
		result.append("d.openAll();");

		return result.toString();
	}

	public static boolean emailFormat(String email) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

}
