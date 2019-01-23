package com.hmwl.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Shiro_Utils {
    public static void main(String[] args) {
        Md5Hash hash = new Md5Hash("123","ls",3);
        String s = hash.toString();
        System.out.println(s);
    }
    public static String M5dEncoding(String username,String salt,Integer num){
        Md5Hash hash = new Md5Hash(username,salt,num);
        String s = hash.toString();
        return s;
    }
}
