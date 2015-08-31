package com.digger.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @see:处理系统请求数据的字符编码
 * @author Administrator
 *
 */

public class EncodingFilter implements Filter {
	
	public EncodingFilter()
	{
	}

	public void destroy() {
		

	}
	
	/**
	 * 程序乱码处理工作
	 * @param arg2:过滤器链,多个过滤器构成
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	 }
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}


}