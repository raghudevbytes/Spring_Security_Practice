package com.jwt.jwtlearningauthserver.Hashing;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashingBasic {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try {
            String input="userId:torronto";

            hash(input);
            hashWithKey(input,"Raghu");

            SecretKey key= secretKey();
            String encText =   encryption(input,key);
            decrypt(encText, key);

            String sKey="james23123456879";
            String ivParamKey ="myIV123456789012";
            String advEncTExt=encryptAdvanced(input,sKey,ivParamKey);
            decryptAdvanced(advEncTExt,sKey,ivParamKey);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
    //simple hashing used Secured Hashing Algorithm 256
    public static void hash(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] digestBytes = digest.digest(text.getBytes(Charset.defaultCharset()));
        System.out.println(Base64.getEncoder().encodeToString(digestBytes));
    }

    //same hashing but used with key
    public static void hashWithKey(String input, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),"SHA256");
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(input.getBytes());
        System.out.println(Base64.getEncoder().encodeToString(hash));
    }
    //Encryption with AES
    public static String  encryption (String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher =  Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encryptedByte = cipher.doFinal(input.getBytes());
        String encText = Base64.getEncoder().encodeToString(encryptedByte);
        System.out.println(encText);
    return encText;
    }
    //Decryption with AES
    public static String decrypt(String encText, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decByte = cipher.doFinal(Base64.getDecoder().decode(encText));
        String orgText = new String(decByte);
        System.out.println(orgText);
        return orgText;
    }
    //Secret key generator
    private static SecretKey secretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
//Encryption with AES/CBC/PKCS5PADDING
    public static String encryptAdvanced(String inputText, String sKey, String ivParam){
        String encString = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec secretKeySpec = new SecretKeySpec(sKey.getBytes(),"AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParam.getBytes(Charset.defaultCharset()));
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);
            byte[] encBytes= cipher.doFinal(inputText.getBytes());
            encString = Base64.getEncoder().encodeToString(encBytes);
            System.out.println("ADVANCED: encString :"+encString);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return encString;
    }
    //Decryption with AES/CBC/PKCS5PADDING
    public static void decryptAdvanced(String encText, String sKey, String ivParam){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec secretKeySpec = new SecretKeySpec(sKey.getBytes(),"AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParam.getBytes(Charset.defaultCharset()));
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);
            byte[] decBytes = cipher.doFinal(Base64.getDecoder().decode(encText.getBytes()));
            System.out.println("DECRYPT TEXT:"+new String(decBytes));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }
}

