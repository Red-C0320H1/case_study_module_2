package com.red.app.helpers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
	private static Logger logger = Logger.getLogger("MyLog");

	static {
		FileHandler fh;

		try {
			fh = new FileHandler("LogFile.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void info(String msg){
		logger.info(msg);
	}

	public static void warning(String msg){
		logger.warning(msg);
	}
}
