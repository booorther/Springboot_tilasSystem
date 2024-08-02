package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
@Component//非三层架构类对象注入
public class OSSUtils {

    /*
    * 文件上传阿里SSO功能
    * */
    //@Value传值
//    @Autowired传对象
    @Value("${aliyun.tliassystem.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.tliassystem.oss.bucketName}")
    private String bucketName;

    public  String upload(String filename, InputStream inputStream)  {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        //图片服务存放节点（广州）地址
//        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        //配置系统环境变量，扣款“账户OSS_ACCESS_KEY_ID”和“密码OSS_ACCESS_KEY_SECRET”
        EnvironmentVariableCredentialsProvider credentialsProvider = null;
        try {
            credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        // 填写Bucket名称，例如examplebucket。
        //存储空间名称tlias302
//        String bucketName = "tlias302";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
//exampledir/exampleobject.txt===》文件jia/文件名
//        String objectName = "img/美女.jpg";

        // 创建OSSClient实例。使用密钥对区节点服务登录，assClient对象实现上传
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            //正文
//            String content = "Hello OSS";
            //图片输入流
//            FileInputStream fileInputStream = new FileInputStream("D:\\Exercise\\PraticalTraining2\\Projects\\img\\R-C.jpg");
            //使用0ss完成上传：空间名，上传成功后的文件名，文件内容为输入流
//            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
            ossClient.putObject(bucketName, filename, inputStream);
        } catch (Exception e) {
            System.out.println("上传失败。。。。。。。:" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                ossClient.shutdown();
            }
        }
        String url = "https://" + bucketName + "." + endpoint+"/"+filename;

       //当前上传成功图片的网络路径
        return url;
    }
}