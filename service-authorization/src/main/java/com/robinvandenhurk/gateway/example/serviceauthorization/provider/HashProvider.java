package com.robinvandenhurk.gateway.example.serviceauthorization.provider;

import org.apache.tomcat.util.buf.HexUtils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: HashProvider
 */

public class HashProvider {

    public String pbkdf2(String str) {
        try {
            byte[] salt = getSalt();
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(str.toCharArray(), salt, 10000, 512);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();

            return HexUtils.toHexString(res);
        } catch (Exception e) {
//            TODO: Proper logging
            System.out.println("Hashing failed!");
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getSalt() {
//        TODO: Do something prettier with this
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//
//        return salt;

        return "I'm salty".getBytes();
    }

}
