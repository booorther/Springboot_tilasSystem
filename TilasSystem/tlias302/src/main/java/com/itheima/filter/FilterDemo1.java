package com.itheima.filter;

import javax.servlet.*;
import java.io.IOException;
/*过滤器@WebFilter("/*") 拦截所有请求*/
//@WebFilter("/luhongling")
public class FilterDemo1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    /*拦截请求后自动执行dofilter方法
    * 默认策略都为拦截，响应*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
        System.out.println("Filter 拦截器执行前....");
        filterchain.doFilter(request, response);
        System.out.println("Filter 拦截器执行后....");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
