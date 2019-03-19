package com.example.administrator.mytoolproject;

import android.text.TextUtils;

/**
    * 提交接口时统一的请求参数
    * 创建作者： 黄如辉
    * 创建时间： 2017/4/10 13:35
   **/

public class ResponseParent<T> {
     /*returnCode为0000为成功。
     1002需要重新登录
     4006表示需要弹窗显示限购限制评价同时succes要为false*/
     private String returnCode;
     private String returnMsg;
     private String returnSize;
     private T returnData;
    private String success;
    private String errorMsg;
    private String mobile;
     public ResponseParent(String returnCode, String returnSize, String returnMsg, T returnData) {
        this.returnCode = returnCode;
        this.returnSize = returnSize;
        this.returnMsg = returnMsg;
        this.returnData = returnData;
     }

     public String getReturnCode() {
         return returnCode;
     }

     public String getReturnMsg() {
         return returnMsg;
     }

     public void setReturnMsg(String returnMsg) {
         this.returnMsg = returnMsg;
     }

     public String getReturnSize() {
         if (TextUtils.isEmpty(returnSize)){
             returnSize = "10";
         }
         return returnSize;
     }

     public T getReturnData() {
         return returnData;
     }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
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
}
