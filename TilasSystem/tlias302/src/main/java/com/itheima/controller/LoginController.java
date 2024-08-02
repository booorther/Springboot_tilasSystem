package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录: {}", emp);
        //1.查找员工用户名和密码
        //浏览器响应controller请求在emp中存放用户名和密码
        Emp empForSQL = null;
//        try{
            empForSQL=empService.findByUsernamePasswordService(emp);
//        }catch (Exception e){
//            return Result.error(e.getMessage());
//        }
        //数据库密码解密
        if (empForSQL != null) {
            //2.生成token
            //创建哈希映射表
            HashMap<String, Object> map = new HashMap<>();
            //在表中存放用户ID和用户名（username）
            map.put("id", empForSQL.getId());
            map.put("username", empForSQL.getUsername());
            //生成JWT令牌
            String token= Jwts.builder()
                    .setClaims(map)
                    .signWith(SignatureAlgorithm.HS256,"itheima".getBytes())//选择算法和添加密令
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*2))//设置令牌异常时间
                    .compact();
            System.out.println(empForSQL.getUsername()+"登录成功，（controller层）生成令牌: " + token);
            return Result.success(token);
        } else {
            return Result.error("用户名或密码错误");
        }

    }
}
