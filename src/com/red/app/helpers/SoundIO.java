package com.red.app.helpers;

import com.red.app.config.ConfigIO;
import com.red.app.media.Sound;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SoundIO {
	private static volatile SoundIO instance;

	private SoundIO() {
	}

	;

	public static SoundIO getInstance() {
		if (instance == null) {
			synchronized (SoundIO.class) {
				instance = new SoundIO();
			}
		}
		return instance;
	}

	public boolean curl_dowload(String url, File outputFile) {
		Request request = new Request();
		InputStream data = request.curl_getStream(url);
		if (data != null) {
			BufferedInputStream bIn = null;
			BufferedOutputStream bOut = null;

			try {
				bIn = new BufferedInputStream(data);
				bOut = new BufferedOutputStream(new FileOutputStream(outputFile));

				while (true) {
					int datum = bIn.read();
					if (datum == -1) {
						break;
					}
					bOut.write(datum);
				}
				bOut.flush();

				bIn.close();
				bOut.close();
				return true;

			} catch (IOException var8) {
			}
		}

		return false;
	}

	public boolean grab_dowload(String urlPath, File outputFile) {
		BufferedInputStream bIn = null;
		BufferedOutputStream bOut = null;

		try {
			URL url = new URL(urlPath);
			bIn = new BufferedInputStream(url.openStream());
			bOut = new BufferedOutputStream(new FileOutputStream(outputFile));

			while (true) {
				int datum = bIn.read();
				if (datum == -1) {
					break;
				}
				bOut.write(datum);
			}
			bOut.flush();

			bIn.close();
			bOut.close();
			return true;

		} catch (IOException var7) {
			var7.printStackTrace();
		}
		return false;
	}

	public String download(String url, String path, String name) {
		File pathTemp = new File(path);
		if (!pathTemp.exists()) {
			pathTemp.mkdir();
		}

		pathTemp = new File(path + name);

		if (!pathTemp.exists()) {
			SoundIO soundIO = new SoundIO();
			soundIO.grab_dowload(url, pathTemp);
		}

		return pathTemp.toURI().toString();
	}
}
