package com.shuai.test;
import com.shuai.unit.OssFileUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

public class ossTest {
    public static void main(String[] args) throws FileNotFoundException{
     /*   String ext = StringUtils.substringAfterLast("OssFile01",".");
        System.out.println("文件"+ext);
        String a="9";
        File file = new File("d://dd");
        MultipartFile multipartFile = new MultipartFile("d://");
       // System.out.println("文件名"+multipartFile.getOriginalFilename());
        String s = OssFileUnit.uploadFile(ext+".jpg",multipartFile);
       System.out.println("s="+s);*/
        ossTest ossTest =new ossTest();
        ossTest.split();

    }

    public void split() {
        String aa= "a,b,c,d";
        System.out.println(aa.split(",")[1]);
    }
}
