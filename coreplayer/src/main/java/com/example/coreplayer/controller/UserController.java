package com.example.coreplayer.controller;

import com.example.coreplayer.domain.User;
import com.example.coreplayer.service.UserService;
import com.example.coreplayer.util.Json;
import com.example.coreplayer.util.Utils;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

//    七牛云配置
    final String accessKey="Ff7teascb-5o79ihQAlXp2620YNEIna8rNuXFpSC";
    final String secreKey="ENjXgQiA-zMR4A5hEF-26roqjH8TPlAjq6L3YYbZ";
    final String bucket="wdehbcj-img";
    final String headlink="http://img.wdehbcj.top";
    @Autowired
    UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;
//    图片外链路径
    private String headimgaddress;

    /**
     * 登录接口
     * @param token  不是必要的
     * @param username
     * @param password
     * @return
     */
    @GetMapping("login")
    public Map<String,Object> userLogin(@RequestParam(required = false) String token,
                                        @RequestParam String username,
                                        @RequestParam String password){



        if(username=="admin"&&password=="admin"){
            return Json.success(new User().setUsername("amdin"));
        }
        System.out.println(token);
        User user=userService.login(username, password);
        System.out.println(user);

        if(token!=null&&!token.isEmpty()){
//            token不为空，表明可能近期登录过，redis里面查token，存在则放行，不存在则创建token存入
           if(redisTemplate.opsForValue().get(token)!=null){
               if(user!=null){
                   user.setPassword(null);
                   System.out.println("token不为空");
                   return Json.success(user,"登陆成功");
               }
               return Json.fail("账号或密码有误");


           }else{//token 已过期，需要重新验证用户名密码并生成token
               if(user!=null){
                   user.setPassword(null);
                   return Json.success(user,"登录成功");
               }
           }

        }else{
//            为空表示最近没有登录，查询账号密码是否匹配，匹配则创建token存入redis，放行
            System.out.println("token 为空");
          if(user!=null){
              user.setPassword(null);
              System.out.println(user+"登录成功");
              return Json.success(user,"登录成功");
          }
//          return Json.fail("登录失败");
        }
        return Json.fail("登录失败");
    }

    /**
     * 邮件接口，通过邮件发送验证码
     * @param request 借request获取IP地址
     * @param email 邮件地址
     * @return
     */
    @GetMapping("sendmail")
    public Map<String,Object> sendTokenToFront(HttpServletRequest request,String email){

        String token= Utils.tokenCreate(Utils.getIpAddress(request));
        String identifyingCode=Utils.identifyingCodeCreate();
        System.out.println(email);

        System.out.println(identifyingCode);
        Utils.sendEmailWithValueDataCodeToOthers(email,identifyingCode);
        redisTemplate.opsForValue().set(token,identifyingCode,30, TimeUnit.MINUTES);
        return Json.success(token);

    }

    /**
     * 注册接口，user在json里面，valuedatacode和token在地址栏传输
     * @param user 用户名，密码，邮箱地址
     * @param identityingCode 验证码
     * @param token
     * @return
     */
    @PostMapping("register")
    public Map<String,Object> register(@RequestBody User user,
                                       @RequestParam("valuedatacode") String identityingCode,
                                       @RequestParam("token") String token
                                       ){


        System.out.println(user);
        System.out.println(identityingCode);
        System.out.println(token);

        if(redisTemplate.opsForValue().get(token).equals(identityingCode)){
//            token和验证码匹配     数据可以存入数据库，而后可以正常登陆
            user.setHeadimgaddress(this.headimgaddress);
            userService.save(user);
            return Json.success("注册成功");
        }

        return Json.fail("注册失败");

    }
    @PostMapping("headimg")
    public Map<String,Object> headImgUploadToQiniu(MultipartFile file) throws Exception{
//将前端传来的图片文件保存到七牛云服务器上，并获取外链地址，与对应的id存入数据库中
        FileInputStream in=(FileInputStream) file.getInputStream();
        String path=Utils.fileUpLoadToQiniuyun(accessKey,secreKey,headlink,bucket,in);
        if(path==null){
            return Json.fail("头像上传失败！！！");
        }
        this.headimgaddress=null;
        this.headimgaddress=path;
        return Json.success("图片上传成功");
    }
}
