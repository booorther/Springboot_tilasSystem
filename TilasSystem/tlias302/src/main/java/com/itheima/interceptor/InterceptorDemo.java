package com.itheima.interceptor;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/*拦截器interceptor
* 实现校验*/
@Component
public class InterceptorDemo implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        System.out.println("处理前preHandle路径....."+request.getRequestURI());
        //不在/login路径中进行拦截
        //登录成功后获取token并解密token
        String token = request.getHeader("Token");//获取浏览器中从请求头获取token
        System.out.println("拦截器interceptor获取浏览器中的Token:"+token);
        /* HashMap<String, Object> map = new HashMap<>();
            //在表中存放用户ID和用户名（username）
            map.put("id", empForSQL.getId());
            //put(键，值)
            map.put("username", empForSQL.getUsername());*/
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
            return false;//拦截
        }
        //get（键）的值
        System.out.println("恭喜"+body.get("uersname")+"登录成功！");
        return true;//通过
    }
}
