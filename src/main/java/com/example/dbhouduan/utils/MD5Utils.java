package com.example.dbhouduan.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5Utils {
    public static String md5Hex(String src) {

        return  DigestUtils.md5Hex((src).getBytes());
    }

    public static void main(String[] args) {
        System.out.println(md5Hex("admin"));
    }
}