package com.itheima.controller;

import com.itheima.pojo.Result;

import com.itheima.utils.OSSUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private OSSUtils ossUtils;
    @PostMapping("/upload")
    public Result upload( MultipartFile image) throws IOException {
        /*
        * 文件格式化处理
        * */

        String fileName = image.getOriginalFilename();//CE-v.jpg或美女.jpg
        /*image.getOriginalFilename()文件原始名称
        *  image.getInputStream()文件系统输入流*/
        //截取文件后缀格式
        //获取文件名字符的最后一个.的索引位置   .的下标位置
        int index = fileName.lastIndexOf(".");
        //1.2从最后一个.索引开始截取
        String suffix = fileName.substring(index);//.jpg  .png .gif

        //2.UUID随机（由时间字符构成，不会重复）生成字符串为文件名
        String randomStr= UUID.randomUUID().toString().replace("-","");
        //新文件名=随机字符.文件后缀
//        originalFilename=randomStr+suffix;
        //3.每个图片加上文件夹
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        String timefomatFolder=sdf.format(date);// 2024/06/24
        fileName=timefomatFolder+"/"+randomStr+suffix;// 2024/06/24+/+ d812f6870fd542788ca7fcbc191ed93d+.jpg


        String url= ossUtils.upload(fileName, image.getInputStream());
        //图片名称相同，会被新图覆盖
        /*业务处理*/
        return Result.success(url);

    }


/*    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile image) throws IOException {
        log.info("接受文件对象：{}",image);
        log.info("请求参数属性名：{}",image.getName());

        log.info("接受文件名称：{}", image.getOriginalFilename());
        log.info("文件大小{}", image.getSize());
        //获取文件流io
        InputStream is = image.getInputStream();
        log.info("文件输入流{}", is);
        //关闭文件流
        is.close();
        //把接受文件对象image写道盘符中。文件上传
        image.transferTo(new File("D:/Exercise/PraticalTraining2/Projects/img","R-C.jpg"));
        return Result.success("https://tlias302.oss-cn-guangzhou.aliyuncs.com/R-C.jpg");
    }*/
}
