import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AesEncrypt {
	 // ����    
    public static String encrypt(String sSrc, String sKey) throws Exception {    
        if (sKey == null) {    
            System.out.print("KeyΪ��null");    
            return null;    
        }    
        // �ж�Key�Ƿ�Ϊ16λ    
        if (sKey.length() != 16) {    
            System.out.print("Key���Ȳ���16λ");    
            return null;    
        }    
        byte[] raw = sKey.getBytes();    
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");    
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"�㷨/ģʽ/���뷽ʽ"    
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��    
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);    
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());    
    
        return Base64.encodeBase64String(encrypted);//�˴�ʹ��BAES64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�    
    }    
}
