package com.red.app.zingmp3;

import com.red.app.helpers.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ZingAPI {
	// Sort by Key HashMap
	public static Map<String, String> sortByKey(Map<String, String> map) {
		TreeMap<String, String> sorted = new TreeMap<>(map);
		return sorted;
	}

	// SHA 256
	public static String toHexStringSHA256(byte[] hash) {
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}
	public static String getSHA256(String input) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance(ZingInfo.SHA256);
		return toHexStringSHA256(md.digest(input.getBytes(StandardCharsets.UTF_8)));
	}

	// MAC SHA512
	private static String toHexStringMAC512(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

	public static String calculateHMAC(String data, String key) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ZingInfo.HMAC_SHA512);
		Mac mac = Mac.getInstance(ZingInfo.HMAC_SHA512);
		mac.init(secretKeySpec);
		return toHexStringMAC512(mac.doFinal(data.getBytes()));
	}

	public static String getCurrentTime(){
		Date date = new Date();
		return String.valueOf(date.getTime()/1000);
	}

	// BUILD URL
	public static String buildAPIURL(String api, Map<String, String> param) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
		param.put("ctime", getCurrentTime());
		param.put("sig", buildSig(api, param));
		return ZingInfo.API_URL + api + "?" + buildQuery(param, "&") + "api_key=" + ZingInfo.API_KEY;
	}

	public static String buildSig(String api, Map<String, String> param) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
		String n = buildMessage(param);
		return calculateHMAC(api + getSHA256(n), ZingInfo.PRIVATE_KEY);
	}

	// buildQuery
	public static String buildQuery(Map<String, String> param, String sq){
		String cer = "";
		try {
			for (String key : param.keySet()) {
				cer += key + "=" + URLEncoder.encode(param.get(key), "UTF-8") + sq;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return cer;
	}

	public static String buildMessage(Map<String, String> param){
		param = sortByKey(param);

		Map<String, String> newCer = new HashMap<String, String>();
		for (String key : param.keySet()) {
			if ((key.equals("ctime") || key.equals("id")) && null != param.get(key)) {
				newCer.put(key, param.get(key));
			}
		}
		return buildQuery(newCer, "");
	}
}
