package com.project.server.springboot.project.account.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringUtil {
    private static MessageDigest md;
    private static Random rnd;
    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    private static final int SALT_LENGTH = 7;
    private static final String ALGORITH = "SHA-256";

    public static String generateSalt() {
        if (rnd == null) {
            rnd = new Random();
        }
        StringBuilder salt = new StringBuilder();
        while (salt.length() < SALT_LENGTH) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public static String hashPassword(String password, String salt) {
        try {
            if (md == null) {
                md = MessageDigest.getInstance(ALGORITH);
            }
            md.update(password.getBytes());
            md.update(salt.getBytes());
            final byte[] hash = md.digest();
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; ++i) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ignored) {
            return "";
        }
    }

    public static boolean comparePasswordWithSalt(String inputPassword, String salt, String hashPassword) {
        return hashPassword(inputPassword, salt).equals(hashPassword);
    }
}
