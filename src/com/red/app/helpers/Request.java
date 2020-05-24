package com.red.app.helpers;

import com.red.app.config.ConfigHTTP;
import com.red.curl.*;

import java.io.InputStream;
import java.util.HashMap;

public class Request {
	public void setHeadHTTP(CurlLib curl, Pointer ch) {
		HashMap<String, String> head = new HashMap<String, String>();
		head.put(ConfigHTTP.KEY_USER_AGEN,       ConfigHTTP.VALUE_USER_AGEN);
		head.put(ConfigHTTP.KEY_ACCEPT,          ConfigHTTP.VALUE_ACCEPT);
		head.put(ConfigHTTP.KEY_ENCODING,        ConfigHTTP.VALUE_ENCODING);
		head.put(ConfigHTTP.KEY_ACCEPT_LANGUAGE, ConfigHTTP.VALUE_ACCEPT_LANGUAGE);
		head.put(ConfigHTTP.KEY_ACCEPT_CHARSET,  ConfigHTTP.VALUE_ACCEPT_CHARSET);
		head.put(ConfigHTTP.KEY_CONNECTION,      ConfigHTTP.VALUE_CONNECTION);
		head.put(ConfigHTTP.KEY_KEEP_ALIVE,      ConfigHTTP.VALUE_KEEP_ALIVE);
		head.put(ConfigHTTP.KEY_EXPECT,          ConfigHTTP.VALUE_EXPECT);
		curl.curl_setopt(ch, 10023, head);
	}

	public String grab_content(String url) {
		CurlLib curl = CurlFactory.getInstance(ConfigHTTP.CURL_FACTORY);
		Pointer ch = curl.curl_init();

		this.setHeadHTTP(curl, ch);

		curl.curl_setopt(ch, CurlOption.CURLOPT_CONNECTTIMEOUT, ConfigHTTP.CONNECTTIMEOUT);
		curl.curl_setopt(ch, CurlOption.CURLOPT_TIMEOUT,        ConfigHTTP.CONNECTTIMEOUT);
		curl.curl_setopt(ch, CurlOption.CURLOPT_SSL_VERIFYPEER, false);
		curl.curl_setopt(ch, CurlOption.CURLOPT_SSL_VERIFYHOST, false);

		curl.curl_setopt(ch, CurlOption.CURLOPT_URL, url);
		Object html = curl.curl_exec(ch);
		Object httpCode = curl.curl_getinfo(ch, CurlInfo.CURLINFO_HTTP_CODE);

		if (httpCode != null && 200 == Integer.valueOf(httpCode.toString())) {
			return (String) html;
		}

		return  null;
	}

	public InputStream curl_getStream(String url) {
		CurlLib curl = CurlFactory.getInstance(ConfigHTTP.CURL_FACTORY);
		Pointer ch = curl.curl_init();

		this.setHeadHTTP(curl, ch);

		curl.curl_setopt(ch, CurlOption.CURLOPT_CONNECTTIMEOUT, ConfigHTTP.CONNECTTIMEOUT);
		curl.curl_setopt(ch, CurlOption.CURLOPT_TIMEOUT, ConfigHTTP.CONNECTTIMEOUT);
		curl.curl_setopt(ch, CurlOption.CURLOPT_SSL_VERIFYPEER, false);
		curl.curl_setopt(ch, CurlOption.CURLOPT_SSL_VERIFYHOST, false);

		curl.curl_setopt(ch, CurlOption.JAVA_RET_STREAM, true);

		curl.curl_setopt(ch, CurlOption.CURLOPT_URL, url);

		Object data = curl.curl_exec(ch);
		Object httpCode = curl.curl_getinfo(ch, CurlInfo.CURLINFO_HTTP_CODE);

		curl.curl_close(ch);

		if (httpCode != null && 200 == Integer.valueOf(httpCode.toString())) {
			return (InputStream) data;
		}

		return  null;
	}
}
