package com.example.administrator.mytoolproject;

import com.google.gson.GsonBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

/**
    * 提交接口统一参数设置
    * 创建作者： 黄如辉
    * 创建时间： 2017/4/10 13:37
   **/
public class ResquestParent<T> {

     /** token登陆成功后服务端返回的随机串 **/
    private String token = "";
     /** reqData请求数据 **/
    private T reqData;
     /** timeStamp当前时间戳（精确到秒） **/
    private int timeStamp;
     /** nonceStr  16 位随机字符串 **/
    private String nonceStr;
    /** version 版本号**/
    private String version = "10";
    /** system系统号**/
    private String system = "2";
    /**获取当前系统的android版本号**/
    private String systemVersion = android.os.Build.VERSION.RELEASE;
     /** signature签名 **/
    private String signature;


     public ResquestParent(T reqData) {
         this.reqData = reqData;
         token = "";
         timeStamp = 55;
         nonceStr = "dsjgkfldsjlk";
         signature = getSignature(token, timeStamp, nonceStr, reqData);
     }

     public ResquestParent(T reqData, String token) {
         this.token = token;
         this.reqData = reqData;
         timeStamp = 45343654;
         nonceStr = "dsfkjdshlkjfdslkj";
         signature = getSignature(token, timeStamp, nonceStr, reqData);
     }

     public ResquestParent(String token, T reqData, int timeStamp, String nonceStr) {
         this.token = token;
         this.reqData = reqData;
         this.timeStamp = timeStamp;
         this.nonceStr = nonceStr;
         signature = getSignature(token, timeStamp, nonceStr, reqData);
     }

     public String getToken() {
         return token;
     }


     /**
      * 签名算法
      */
     private String getSignature(String token, int timeStamp, String nonceStr, T reqData){
         ArrayList<String> list = new ArrayList<>();
         String copysignature = "";
         list.add(token);list.add(String.valueOf(timeStamp)); list.add(nonceStr); list.add("3BDB71CA-11A3-49");
         Collections.sort(list);
         for (String str: list) {
             copysignature = copysignature + str;
         }
         String reqdatatostring = getGson(reqData);
         copysignature = copysignature + reqdatatostring;
         return getMd5(copysignature);
     }

     String getGson(Object t){
        GsonBuilder gb =new GsonBuilder();
        gb.disableHtmlEscaping();
        return gb.create().toJson(t);
     }

    //md5加密
    String getMd5(String plainText) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            md.update(plainText.getBytes());
            // 获得密文
            byte b[] = md.digest();
            // 把密文转换成十六进制的字符串形式
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
