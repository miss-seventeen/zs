package com.shuai.unit;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class OssFileUnit {
    public static String uploadFile(String objectKey, MultipartFile multipartFile)throws OSSException, ClientException, FileNotFoundException{
        //创建OSSClient的实例
        OSSClient ossClient=new OSSClient("endpoint","accessKey","accessKeySecret");
        StringBuffer sb =new StringBuffer();
        //上传的文件不是空，并且文件的名字不是空字符串
        String bucketName = null;
        if(multipartFile.getSize()!=0 && !"".equals(multipartFile.getName())){
            ObjectMetadata om =new ObjectMetadata();
            om.setContentLength(multipartFile.getSize());
            //设置文件上传到服务器的名称
            om.addUserMetadata("filename",objectKey);

            try{ossClient.putObject(bucketName,objectKey,new ByteArrayInputStream(multipartFile.getBytes()),om);
            }catch (IOException e){

            }
        }
        //设置这个文件地址的有效时间
        //Date expiration=new Date(new Date().getTime()+36001*1000*24);
        Date expiration=new Date(System.currentTimeMillis()+36001*1000*24);
        String url =ossClient.generatePresignedUrl(bucketName,objectKey,expiration).toString();
        return url;
    }
}
