package com.cayanay.fileservice.service;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {
    public static String md5Hash(String input) {
        return DigestUtils.md5Hex(input).toUpperCase();
    }
}