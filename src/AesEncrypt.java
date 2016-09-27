import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AesEncrypt {
	 // 加密    
    public static String encrypt(String sSrc, String sKey) throws Exception {    
        if (sKey == null) {    
            System.out.print("Key为空null");    
            return null;    
        }    
        // 判断Key是否为16位    
        if (sKey.length() != 16) {    
            System.out.print("Key长度不是16位");    
            return null;    
        }    
        byte[] raw = sKey.getBytes();    
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");    
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"    
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度    
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);    
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());    
    
        return Base64.encodeBase64String(encrypted);//此处使用BAES64做转码功能，同时能起到2次加密的作用。    
    }    
}
