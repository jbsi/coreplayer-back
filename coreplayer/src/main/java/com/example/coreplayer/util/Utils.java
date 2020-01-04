package com.example.coreplayer.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.mail.MailUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.Random;

public class Utils {

//6位验证码生成
    public static String identifyingCodeCreate(){
        return (int)(Math.random()*1000000)+"";
    }
//    token生成
    public static String tokenCreate(String ipAddress){
        final String header=154762159+"";

        return SecureUtil.md5(header+ipAddress);
    }
//给某个邮箱发送验证码
    public static void sendEmailWithValueDataCodeToOthers(String emailAdderss,String valueDataCode){
        MailUtil.send(emailAdderss,"欢迎注册该视频网站","<h1>我也不知道这是啥</h1><br><p>"+valueDataCode+"</p>",true);
    }
//    文件上传到七牛云服务器返回文件外链路径
    public static String  fileUpLoadToQiniuyun(String AK, String CK,String headlink ,String bucket, FileInputStream fileInputStream){
        Configuration cfg=new Configuration(Zone.zone1());
        UploadManager uploadManager=new UploadManager(cfg);

        Auth auth=Auth.create(AK, CK);
        String upToken = auth.uploadToken(bucket);
        Response response = null;
        try {
            response = uploadManager.put(fileInputStream, null, upToken, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            String returnPath = headlink+ "/" + putRet.key;
            return returnPath;

        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }
//获取客户端IP地址
    public static String getIpAddress(HttpServletRequest request){
        String ipAddress="";
        if(request!=null){
            ipAddress=request.getHeader("X-Forwarded-For");
            if(ipAddress==null||"".equals(ipAddress)){
                ipAddress=request.getRemoteAddr();
            }
        }
        return ipAddress;
    }
}
