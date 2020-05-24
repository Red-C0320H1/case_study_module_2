package com.red.app.helpers;

import com.red.app.config.ConfigIO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SoundIO {
	public boolean curl_dowload(String url, File outputFile) {
		Request request  = new Request();
		InputStream data = request.curl_getStream(url);
		if (data != null) {
			BufferedInputStream  bIn  = null;
			BufferedOutputStream bOut = null;

			try {
				bIn  = new BufferedInputStream(data);
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
		BufferedInputStream  bIn = null;
		BufferedOutputStream bOut = null;

		try {
			URL url = new URL(urlPath);
			bIn  = new BufferedInputStream(url.openStream());
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

	public File loadTemp(String url) {
		File pathTemp   = new File(ConfigIO.TEMP_PATH);
		if (!pathTemp.exists()) {
			pathTemp.mkdir();
		}
		pathTemp = new File(ConfigIO.TEMP_PATH + ConfigIO.TEMP_FILE);

		SoundIO soundIO = new SoundIO();
		soundIO.grab_dowload(url, pathTemp);
		return pathTemp;
	}
}
