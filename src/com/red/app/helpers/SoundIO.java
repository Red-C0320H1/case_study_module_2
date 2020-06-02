package com.red.app.helpers;

import com.red.app.config.ConfigIO;
import com.red.app.media.Sound;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class SoundIO {
	private static volatile SoundIO instance;

	private SoundIO() {
	}

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

	public void createFolder(File file){
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public String download(String url, String path, String name, String ext) {
		File pathTemp      = new File(ConfigIO.PROFILE_PATH);
		createFolder(pathTemp);

		pathTemp           = new File(ConfigIO.TEMP_PATH);
		createFolder(pathTemp);

		pathTemp           = new File(ConfigIO.TEMP_PATH + name + ConfigIO.TEMP_EXT);
		if (pathTemp.exists()) {
			return pathTemp.toURI().toString();
		}

		pathTemp           = new File(ConfigIO.PROFILE_PATH + name + ConfigIO.MP3_EXT);
		if (pathTemp.exists()) {
			return pathTemp.toURI().toString();
		}

		pathTemp = new File(path + name + ext);

		if (!pathTemp.exists()) {
			SoundIO soundIO = new SoundIO();
			soundIO.grab_dowload(url, pathTemp);
		}

		return pathTemp.toURI().toString();
	}

	public void download(Sound sound) {
		String url  = sound.getURL();
		String path = ConfigIO.PROFILE_PATH + sound.getSEO() + ConfigIO.MP3_EXT;

		File file = new File(path);

		URI uri = null;

		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File fileTEMP = new File(uri);

		if (!file.exists() && fileTEMP.exists() && !url.equals("-1")){
			try {
				BufferedInputStream bIn   = new BufferedInputStream(new FileInputStream(fileTEMP));
				BufferedOutputStream bOut = new BufferedOutputStream(new FileOutputStream(path));
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

				fileTEMP.delete();
			} catch (IOException var7) {
				var7.printStackTrace();
			}
		}
	}
}
