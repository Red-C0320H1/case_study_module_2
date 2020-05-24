package com.red.app.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class Helpers {
	public static String secondstoMinutes(double number) {
		double durationTime = number / 60.0D;
		String[] durations = String.valueOf(durationTime).split("\\.");
		if (durations[1].length() > 2) {
			durations[1] = durations[1].substring(0, 2);
		}

		double second = (double)Integer.valueOf(durations[1]);
		second = 60.0D * (second / 100.0D);
		int seconds = (int)second;
		return numberPad(durations[0], 2) + ":" + numberPad(String.valueOf(seconds), 2);
	}

	public static String numberPad(String number, int length) {
		while(number.length() < length) {
			number = "0" + number;
		}

		return number;
	}

	public static String patternURL(String url) {
		Pattern pattern = Pattern.compile("(https?:)?\\/\\/(.*)");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			url = matcher.group(2);
			if (!url.isEmpty()) {
				url = "https://" + url;
			}
		}

		return url;
	}

	public void setAnchorNodeFull(Node node, double top, double left, double bottom, double right) {
		AnchorPane.setTopAnchor(node, top);
		AnchorPane.setLeftAnchor(node, left);
		AnchorPane.setBottomAnchor(node, bottom);
		AnchorPane.setRightAnchor(node, right);
	}

	public void setAnchorNodeH(Node node, double left, double right) {
		AnchorPane.setLeftAnchor(node, left);
		AnchorPane.setRightAnchor(node, right);
	}

	public void setAnchorNodeV(Node node, double top, double bottom) {
		AnchorPane.setTopAnchor(node, top);
		AnchorPane.setBottomAnchor(node, bottom);
	}
}
