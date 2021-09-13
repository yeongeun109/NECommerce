package com.ecommerce.blockchain.domain;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class CryptoUtil
{
	private static final String MODE = "AES/CBC/PKCS5Padding";
	private String encode = "UTF-8";

	private IvParameterSpec ivSpec;
	private SecretKey key;

	public static CryptoUtil of(String stringForKey){
		return new CryptoUtil().generateKey(stringForKey);
	}

	public CryptoUtil generateKey(String stringForKey)
	{
		byte[] keydata = stringForKey.substring(0,16).getBytes();
		key = new SecretKeySpec(keydata, "AES");
		ivSpec = new IvParameterSpec(keydata);
		return this;
	}

	public synchronized String encryptBase64(String str)
			throws Exception
	{
		byte[] raw = encrypt(str.getBytes());
		try {
			return new String(Base64.encodeBase64(raw), encode);
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e);
		}
	}

	private byte[] encrypt(byte[] org)
	{
		try {
			Cipher cipher = Cipher.getInstance(MODE);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			return cipher.doFinal(org);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized String decryptBase64(String base64)
	{
		byte[] raw;
		try {
			raw = Base64.decodeBase64(base64.getBytes(encode));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return decrypt(raw);
	}

	private String decrypt(byte[] data)
	{
		try {
			Cipher cipher = Cipher.getInstance(MODE);
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			byte[] decryptedText = cipher.doFinal(data);
			return new String(decryptedText, encode);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
