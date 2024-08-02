package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;

import java.io.FileInputStream;

public class Demo {

    public static void main(String[] args) throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        //图片服务存放节点（广州）地址
        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        //配置系统环境变量，扣款“账户OSS_ACCESS_KEY_ID”和“密码OSS_ACCESS_KEY_SECRET”
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Bucket名称，例如examplebucket。
        //存储空间名称tlias302
        String bucketName = "tlias302";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
//exampledir/exampleobject.txt===》文件jia/文件名
        String objectName = "img/美女.jpg";

        // 创建OSSClient实例。使用密钥对区节点服务登录，assClient对象实现上传
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            //正文
//            String content = "Hello OSS";
            //图片输入流
            FileInputStream fileInputStream = new FileInputStream("D:\\Exercise\\PraticalTraining2\\Projects\\img\\R-C.jpg");
            //使用0ss完成上传：空间名，上传成功后的文件名，文件内容为输入流
//            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
            ossClient.putObject(bucketName, objectName, fileInputStream);
        } catch (Exception e) {
            System.out.println("上传失败。。。。。。。:" + e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}