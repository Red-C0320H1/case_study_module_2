package com.red.app.config;

public interface ConfigHTTP {
	String KEY_USER_AGEN   = "User-Agen";
	String VALUE_USER_AGEN = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";
	String KEY_ACCEPT = "Accept";
	String VALUE_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
	String KEY_ENCODING = "Accept-Encoding";
	String VALUE_ENCODING = "gzip,deflate,sdch";
	String KEY_ACCEPT_LANGUAGE = "Accept-Language";
	String VALUE_ACCEPT_LANGUAGE = "vi-VN,vi;q=0.8,fr-FR;q=0.6,fr;q=0.4,en-US;q=0.2,en;q=0.2";
	String KEY_ACCEPT_CHARSET = "Accept-Charset";
	String VALUE_ACCEPT_CHARSET = "ISO-8859-1,utf-8;q=0.7,*;q=0.7";
	String KEY_CONNECTION = "Connection";
	String VALUE_CONNECTION = "keep-alive";
	String KEY_KEEP_ALIVE = "Keep-Alive";
	String VALUE_KEEP_ALIVE = "300";
	String KEY_EXPECT = "Expect";
	String VALUE_EXPECT = "";
	String CURL_FACTORY = "default";
	int CONNECTTIMEOUT = 60000;
}
