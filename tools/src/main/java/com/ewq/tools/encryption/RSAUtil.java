package com.ewq.tools.encryption;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.Key;

/**
 * Description: RSA 工具类
 */
@SuppressWarnings("unused")
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class RSAUtil {
    ///////////////////////////////////////////////////////////////////////////
    // RSA + Base64 加解密
    ///////////////////////////////////////////////////////////////////////////
    public static String Base64Encrypt(Key encryptKey, String data) {
        try {
            return Base64.encode(RSA.encrypt(encryptKey, data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    public static String Base64Decrypt(Key decryptKey, String data) {
        try {
            byte[] base64Decrypt = RSA.decrypt(decryptKey, Base64.decode(data));
            if (null == base64Decrypt) {
                return data;
            } else {
                return new String(base64Decrypt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // RSA + BCD 加解密
    ///////////////////////////////////////////////////////////////////////////
    public static String BCDEncrypt(Key encryptKey, String data) {
        try {
            return BCD.encode(RSA.encrypt(encryptKey, data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    public static String BCDDecrypt(Key decryptKey, String data) {
        try {
            byte[] bcdDecrypt = RSA.decrypt(decryptKey, BCD.decode(data));
            if (null == bcdDecrypt) {
                return data;
            } else {
                return new String(bcdDecrypt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }
}
