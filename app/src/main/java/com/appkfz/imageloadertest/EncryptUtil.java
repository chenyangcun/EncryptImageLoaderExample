package com.appkfz.imageloadertest;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by chenyc on 17/8/31.
 */

public class EncryptUtil {

    private final static String key = "1234567890ABCDEF";

    public static byte[] encrypt(byte[] data) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] encrypted = cipher.doFinal(data);
        return encrypted;
    }
    public static byte[] decrypt(byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }


    public static void encryptImageFile(String inputFile, String outputFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            File file = new File(inputFile);
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[(int)file.length()];
            IOUtils.readFully(fileInputStream,buffer);
            byte[] outData = encrypt(buffer);
            IOUtils.write(outData,fileOutputStream);
        }
        catch (Exception e){
                e.printStackTrace();
        } finally {
            if(fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileOutputStream !=null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
