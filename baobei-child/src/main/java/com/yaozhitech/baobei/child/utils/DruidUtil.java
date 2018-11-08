package com.yaozhitech.baobei.child.utils;

import com.alibaba.druid.filter.config.ConfigTools;

public abstract class DruidUtil {

    /**
     * 明文密码 转 密文密码
     * @param pwd 明文密码 默认: 123456
     * @throws Exception
     */
    public static void passwordEncryption(String pwd) throws Exception {
        String password = pwd != null && "".equals(pwd) ? pwd : "123456";
        String encodePassword =  ConfigTools.encrypt(password);
        System.out.println("明文密码: " + encodePassword);
        System.out.println("密文密码: " + ConfigTools.decrypt(encodePassword));
    }
}
