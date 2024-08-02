package com.itheima.utils;

import java.util.UUID;

public class DemoUUID {
    public static void main(String[] args) {
        //UUID随机生成字符串
/*UUID由32个十六进制数字组成，分为5个部分，用连字符分隔，形式为8-4-4-4-12的36个字符。具体来说，UUID的组成如下：
时间戳部分（Time）：表示UUID创建的时间，通常是100纳秒级别的时间戳。
时钟序列部分（Clock Sequence）：用于在同一台机器上生成多个UUID时保证它们的唯一性。
节点部分（Node）：表示UUID生成的机器的MAC地址或随机数。
变体部分（Variants）：用于区分UUID的不同版本。*/
        String si= UUID.randomUUID().toString();
        String s1= UUID.randomUUID().toString();

        System.out.println(si);
        System.out.println(s1);
        System.out.println( s1.replaceAll("-", ""));
    }
}
