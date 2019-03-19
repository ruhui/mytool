package com.example.administrator.mytoolproject;

 /**
    * 登录请求参数
    * 创建作者： 黄如辉
    * 创建时间： 2017/4/10 14:03
   **/

public class LoginRequest {

    public String  loginCode;
    public String password;
    public String system;
    public String mobileBrand;
    public String mobileModel;
    public String version;
    public String versionNum;
    public String ip;
    public String deviceId;
    public String sprChnl;
    public String imei;
    public String verifyCode;

     public LoginRequest(String loginCode, String password, String system, String mobileBrand, String mobileModel,
                         String version, String versionNum, String ip, String deviceId, String sprChnl, String imei) {
         this.loginCode = loginCode;
         this.password = password;
         this.system = system;
         this.mobileBrand = mobileBrand;
         this.mobileModel = mobileModel;
         this.version = version;
         this.versionNum = versionNum;
         this.ip = ip;
         this.deviceId = deviceId;
         this.sprChnl = sprChnl;
         this.imei = imei;
     }

    public LoginRequest() {

    }

//     loginCode	登陆账号（同时支持账号或手机号）	Yizhan001 或 15006039388
//     verifyCode	验证码	111111
//     system	操作系统	Android 或IOS
//     mobileBrand	手机品牌	如苹果、华为等
//     mobileModel	手机型号	如iphone6等
//     version	版本	1
//     versionNum	版本号	0.2.1
//     deviceId	设备ID	前方唯一标识
//     imei	设备序列号	12344854545
//     sprChnl	渠道类型
 }

