package com.jasperTest.demo.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CustomHttpServletRequestWrapperFilter implements Filter
{
	public void init ( FilterConfig fc ) throws ServletException
	{
	}

	public void doFilter (
			ServletRequest request,
			ServletResponse response,
			FilterChain chain ) throws IOException,	ServletException
	{
		chain.doFilter(new CustomHttpServletRequestWrapper((HttpServletRequest) request), response);
	}

	public void destroy ()
	{
	}
}