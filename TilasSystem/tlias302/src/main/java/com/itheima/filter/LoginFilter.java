package com.itheima.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/*
    Java中的过滤器：
    实现校验
    */
//@WebFilter("/*")
public class LoginFilter implements Filter {
/*    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }*/
    //校验浏览器的token
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*1.从请求对象req中获取token
        * 2.JWT解析
        *      报错：token过期或为null，伪造等。请求不会到达controller
        *      不报错：token合法，进一步登录，把请求传到controller中
        * 3.登录请求不进行校验，登录为了 生成token，直接放行*/
        HttpServletRequest req = (HttpServletRequest) request;//向浏览器请求
        //1.类型强行转换，使得请求对象功能丰富，子类型才可以获取请求路径方法
        //2.获取请求路径
        String requestURI = req.getRequestURI();//端口号后的路径
        if(requestURI.contains("login")){
            //requestURI包含了login路径
            chain.doFilter(request, response);//放行
            return;//结束当前方法
        }
        //若不是登录路径
        //3.获取浏览器中的Token
        String token = req.getHeader("Token");//获取浏览器中的请求头
        System.out.println("过滤器Filter获取浏览器中的Token:"+token);
        Map body=null;
        //校验token。解密token
        try{
             body= Jwts.parser().
                    setSigningKey("itheima".getBytes()).
                    parseClaimsJws(token).
                    getBody();
        }catch(Exception e){
            //报错说明token有问题，不能放行，响应一个固定值给前端，使用户重新登录
            response.getWriter().write("{\n" +
                    "\t\"code\": 0,\n" +
                    "\t\"msg\": \"NOT_LOGIN\",\n" +
                    "\t\"data\": null\n" +
                    "}");
            return;//结束
        }
        //token校验成功，放行
        System.out.println("欢迎"+body.get("username")+"回来,过滤器（Filter）身份校验成功...");
        chain.doFilter(request, response);

    }

}
