package com.alivc.base;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class AuthKeyUrlUtil {

    public static String getAuthUrl(String fileName) {

        String domainName = ConfigMapUtil.getValueByKey("live_domain");

        String authKey = ConfigMapUtil.getValueByKey("auth_key");
        Long timestamp = System.currentTimeMillis() / 1000;
        String rand = UUID.randomUUID().toString().replace("-", "");

        return getAuthUrl(domainName, fileName, authKey, timestamp, rand);

    }

    public static String getAuthUrl(String domainName, String fileName, String authKey, Long timestamp, String rand) {

        String param = timestamp + "-" + rand + "-0-";

        String md5Hash = DigestUtils.md5Hex(fileName + "-" + param + authKey);

        String url = domainName + fileName + "?auth_key=" + param + md5Hash;

        return url;

    }


}
