package com.gbm.edu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;


public class AES256Util {

	public static Key aesGetKey(String key) throws UnsupportedEncodingException {
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

		return keySpec;
	}

	// 암호화
	public static String aesEncode(String key, String str)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		Key keySpec = aesGetKey(key);

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));

		return enStr;
	}

	// 암호화
	public static String aesEncode(String key, byte[] userBytes)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		Key keySpec = aesGetKey(key);

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

		byte[] encrypted = c.doFinal(userBytes);
		String enStr = new String(Base64.encodeBase64(encrypted));

		return enStr;
	}

	// 암호화
	public static byte[] aesEncode2(String key, String str)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		Key keySpec = aesGetKey(key);

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

		byte[] encrypted = Base64.encodeBase64(c.doFinal(str.getBytes("UTF-8")));

		return encrypted;
	}
	
	public static void FileToFileEnc(String srcPath, String srcFile, String targetPath, String targetFile, String key)
			throws IOException, InvalidKeyException, SecurityException, IllegalBlockSizeException, BadPaddingException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
		File f1 = new File(srcPath + File.separator + srcFile);
		byte[] fileByteData = null;
		if ((f1.exists()) && (f1.length() > 0L)) {
			FileInputStream fin = new FileInputStream(srcPath + File.separator + srcFile);
			fileByteData = new byte[(int) f1.length()];
			fin.read(fileByteData);
			fin.close();
			memToFileEnc(fileByteData, targetPath, targetFile, key);
		}
	}

	public static void memToFileEnc(byte[] sourceString, String targetPath, String targetFile, String key)
			throws InvalidKeyException, SecurityException, IOException, IllegalBlockSizeException, BadPaddingException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
		String enStr = aesEncode(key, sourceString);

		File f = new File(targetPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		FileOutputStream fout = new FileOutputStream(targetPath + File.separator + targetFile);
		fout.write(enStr.getBytes());
		fout.close();
	}

	// 복호화
	public static String aesDecode(String key, String str)
			throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		Key keySpec = aesGetKey(key);

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));

		byte[] byteStr = Base64.decodeBase64(str.getBytes());

		return new String(c.doFinal(byteStr), "UTF-8");
	}

	// 복호화
	public static byte[] aesDecode2(String key, String str)
			throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		Key keySpec = aesGetKey(key);

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));

		byte[] byteStr = Base64.decodeBase64(str.getBytes());

		return c.doFinal(byteStr);
	}
	
	public static void FileToFileDec(String srcPath, String srcFile, String targetPath, String targetFile, String key)
			throws InvalidKeyException, IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] desc = FileToMemDec(srcPath, srcFile, key);
		FileOutputStream fout = new FileOutputStream(targetPath + File.separator + targetFile);
		fout.write(desc);
		fout.close();
	}

	public static byte[] FileToMemDec(String targetPath, String targetFile, String key)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		File fi = new File(targetPath + File.separator + targetFile);
		byte[] data = null;
		if ((fi.isFile()) && (fi.exists())) {
			FileInputStream fin = new FileInputStream(targetPath + File.separator + targetFile);
			byte[] rtn = new byte[(int) fi.length()];
			if (fi.length() > 0L) {
				fin.read(rtn);
			}
			data = aesDecode2(key, new String(rtn));
			fin.close();
		}
		return data;
	}

	public static String ResourcePathFileToMemDec(String targetPath, String targetFile, String key)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IllegalArgumentException {

			ClassPathResource classPathResource = new ClassPathResource("/"+targetFile);
			if(classPathResource.exists() == false){
			    throw new IllegalArgumentException();
			}

			StringWriter writer = new StringWriter();
			IOUtils.copy(classPathResource.getInputStream(), writer, "UTF-8");
			String str = writer.toString();

			writer.close();

		return new String(AES256Util.aesDecode2(key,str));
	}

}
