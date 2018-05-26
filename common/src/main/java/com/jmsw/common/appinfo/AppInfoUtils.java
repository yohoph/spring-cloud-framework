package com.jmsw.common.appinfo;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2018/2/7
 * @description
 */
public class AppInfoUtils {

    public static AppInfo getAppInfo() {
        AppInfo appInfo = new AppInfo();
//        appInfo.setAppId(RpcContext.getContext().getAttachment(Constants.APP_ID));
        return appInfo;
    }

}
