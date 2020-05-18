package com.red.app.helpers;

import com.red.curl.*;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class Helpers {

	// CURL GET Content
	public String grab_content(String url){
		CurlLib curl = CurlFactory.getInstance("default");
		Pointer ch   = curl.curl_init();

		// Head
		HashMap head = new HashMap();
		head.put("User-Agen", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		head.put("Accept-Encoding", "gzip,deflate,sdch");
		head.put("Accept-Language", "vi-VN,vi;q=0.8,fr-FR;q=0.6,fr;q=0.4,en-US;q=0.2,en;q=0.2");
		head.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		head.put("Connection", "keep-alive");
		head.put("Keep-Alive", "300");
		head.put("Expect", "");

		curl.curl_setopt(ch, CurlOption.CURLOPT_HTTPHEADER,      head);

		curl.curl_setopt(ch, CurlOption.CURLOPT_CONNECTTIMEOUT, 6000);
		curl.curl_setopt(ch, CurlOption.CURLOPT_TIMEOUT,        6000);
		curl.curl_setopt(ch, CurlOption.CURLOPT_SSL_VERIFYPEER, false);
		curl.curl_setopt(ch, CurlOption.CURLOPT_SSL_VERIFYHOST, false);

		curl.curl_setopt(ch, CurlOption.CURLOPT_URL, url);
		Object html = curl.curl_exec(ch);
		Object httpCode = curl.curl_getinfo(ch, CurlInfo.CURLINFO_HTTP_CODE);

		if (httpCode != null && 200 == Integer.valueOf(httpCode.toString())) {
			return (String) html;
		}
		return null;
	}

	// CURL Download
	public File grab_dowload(String urlPath, File outputFile) throws IOException {
		InputStream is = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		try {
			URL url = new URL(urlPath);
			is   = url.openStream();
			bin  = new BufferedInputStream(is);
			bout = new BufferedOutputStream(new FileOutputStream(outputFile));
			while (true) {
				int datum = bin.read();
				if (datum == -1) {
					break;
				}
				bout.write(datum);
			}
			bout.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				is.close();
				bin.close();
				bout.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		return outputFile;
	}
}
