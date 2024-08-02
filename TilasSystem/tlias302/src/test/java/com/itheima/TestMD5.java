package com.itheima;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.util.UUID;

@SpringBootTest
public class TestMD5 {
    @Test
    public void testMD5() {
        String ps="123456";
        String ps1MD5= DigestUtils.md5DigestAsHex(ps.getBytes());
        System.out.println(ps1MD5);
    }
    @Test
    public void testMD52() {
        String ps="123456";
        //MD5数据摘要算法：32位
        /*MD5信息摘要算法是一种广泛使用的哈希函数，它将任意长度的输入数据通过一系列复杂的变换，最终生成一个128位的哈希值。这个过程是不可逆的，即不能从哈希值恢复出原始输入。下面是MD5算法的原理及其组成步骤：
填充：MD5算法首先对输入数据进行填充，使其长度达到一个特定的长度，这是为了使原始数据的长度可以被512整除。填充的方法是在原始数据后面添加一个“1”，然后添加足够数量的“0”，最后添加一个64位的整数表示原始数据的长度。
初始化缓冲区：MD5算法使用了一个64位的缓冲区，分为四个16位部分，用来存储中间结果和最终结果。这四个部分被初始化为特定的常数。
处理分组：填充后的数据被划分为长度为512位的分组，每个分组又划分为16个32位的子分组。然后，通过一系列的位操作和模加运算，每个分组都被处理并更新缓冲区的内容。这个过程涉及四个主要的轮函数和一系列的非线性函数。
输出：处理完所有分组后，缓冲区中的内容就是最终的哈希值。这个哈希值是一个128位的数，通常表示为32个十六进制数。*/
        String ps2MD5= DigestUtils.md5DigestAsHex(ps.getBytes());
        //UUID随机函数。32位
        /*UUID由32个十六进制数字组成，分为5个部分，用连字符分隔，形式为8-4-4-4-12的36个字符。具体来说，UUID的组成如下：
时间戳部分（Time）：表示UUID创建的时间，通常是100纳秒级别的时间戳。
时钟序列部分（Clock Sequence）：用于在同一台机器上生成多个UUID时保证它们的唯一性。
节点部分（Node）：表示UUID生成的机器的MAC地址或随机数。
变体部分（Variants）：用于区分UUID的不同版本。*/
        String salt= UUID.randomUUID().toString().replace("-","");
        //随机盐算法，防止彩虹表反向查询，同一个密码有不同的密文
        ///MD5(   MD5（“123456”）  +salt)  +salt  =  密文
        String saltMD5= DigestUtils.md5DigestAsHex((ps2MD5+salt).getBytes()) +salt;
        System.out.println("密文反向查询："+saltMD5);
        //64位
        //密文反向查询：md5:56daf5ef767790f5ee24133c2da7d6e3(32位) +salt:b48d198c303d4527940a62629811e42b（32位）
        //密文反向查询：188f994cd08b7732af93af8e533e2091d35eafd1526f4a10835c3830565908d1
        //密文反向查询：f18c418b611b5dbb844690717aa4dbde88c5d564d5aa47a984e6ac9c28997c6c
    }
    @Test
    public void testMD53() {
        String ps="123456";
        String passwordSalt="56daf5ef767790f5ee24133c2da7d6e3b48d198c303d4527940a62629811e42b";
        //1.截取出盐，随机变固定。即固定随机盐
        String salt=passwordSalt.substring(32);
        //2.盐和明文代入算法计算密文
        //MD5数据摘要算法；相同明文产生相同密文，ps="123456"
        String passwordMD5= DigestUtils.md5DigestAsHex(ps.getBytes());
        //3.MD5数据摘要算法；相同明文产生相同密文，ps="123456"+passwordSalt="56daf5ef767790f5ee24133c2da7d6e3b48d198c303d4527940a62629811e42b"
        //MD5（“123456”）  +salt)
        //MD5(   MD5（“123456”）  +salt)  +salt
        String passwordSaltMD5= DigestUtils.md5DigestAsHex((passwordMD5+salt).getBytes()) +salt;
        if(passwordSaltMD5.equals(passwordSalt)){
            System.out.println("登录成功！");
        }else {
            System.out.println("登录失败！");
        }




    }


}
