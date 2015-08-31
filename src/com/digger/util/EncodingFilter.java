package com.digger.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @see:����ϵͳ�������ݵ��ַ�����
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
	 * �������봦����
	 * @param arg2:��������,�������������
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