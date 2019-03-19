package com.example.administrator.mytoolproject;


import android.text.TextUtils;

/**
     * 登录返回数据
     * 创建作者： 黄如辉
     * 创建时间： 2017/4/10 13:33
    **/

public class LoginResponse {
    private String token;
    private String memberId;
    private String nick;
    private String type;
    private String userLevel;
    private boolean success;
    private String errorMsg;
    private String mobile;

    public LoginResponse() {}

    public LoginResponse(String token, String memberId, String nick, String type, String userLevel) {
         this.token = token;
         this.memberId = memberId;
         this.nick = nick;
         this.type = type;
         this.userLevel = userLevel;
    }

    public int getUserLevel() {
          if (TextUtils.isEmpty(userLevel)){
              return 0;
          }else{
              if (userLevel.equals("0")){
                  return 0;
              }else if (userLevel.equals("1")){
                  return 1;
              }else{
                  return 0;
              }
          }
    }

    public String getToken() {
        return TextUtils.isEmpty(token)?"":token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMemberId() {
        return TextUtils.isEmpty(memberId)?"":memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNick() {
        return TextUtils.isEmpty(nick)?"":nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getType() {
        return TextUtils.isEmpty(type)?"":type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUserLevel(String userLevel) {
          this.userLevel = userLevel;
      }

    public void clearLogin(){
        this.token = "";
        this.memberId = "";
        this.nick = "";
        this.type = "";
        this.userLevel = "";
        this.success = false;
        this.errorMsg = "";
        this.mobile = "";
    }
}
