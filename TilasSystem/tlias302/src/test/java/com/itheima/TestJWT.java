package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

@SpringBootTest
public class TestJWT {
    @Test
    public void testCreateToken() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 10086);
        map.put("username", "admin");
        //2.生成token:hs256(第一部分+第二部分+秘钥)
        val token = Jwts.builder()
                .setClaims(map)
                        .signWith(SignatureAlgorithm.HS256,"itheima".getBytes())
                                //有效期
                                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                                        .compact();
        System.out.println(token);
    }
    @Test
    //解析token
    public void testDecodeToken() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwODYsImV4cCI6MTcxOTM4OTQ1MCwidXNlcm5hbWUiOiJhZG1pbiJ9.IG85l14B0NhnxQVqJToFVtlfu0hrAUbRplrZ0pBDVVc";
        Claims claims = Jwts.parser()
                .setSigningKey("itheima".getBytes())//指定签名密钥（必须保证和生成令牌时使用相同的签名密钥）
                // hs256（头+体+itheima）  => 字符串
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
