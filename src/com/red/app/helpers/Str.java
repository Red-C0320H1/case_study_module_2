package com.red.app.helpers;

import java.util.HashMap;
import java.util.Map;

public class Str {
	public static String ascii(String title) {
		HashMap<String, String[]> map = charsArray();

		for(Map.Entry<String, String[]> entry : map.entrySet()) {
			String   key    = entry.getKey();
			String[] values = entry.getValue();
			for(String value: values) {
				title = title.replaceAll(value, key);
			}
		}
		return title.replaceAll("[^\\x20-\\x7E]", "");
	}

	public static String slug(String title, String separator) {
		title = ascii(title);
		title = title.replaceAll("\\_", separator);
		title = title.replaceAll("@", separator + "at" + separator);
		title = title.toLowerCase();
		title = title.replaceAll("[^\\-\\pL\\pN\\s]+", separator);
		title = title.replaceAll("[\\-\\s]+", separator);
		title = title.replaceAll("^-|-$", "");
		return title;
	}

	protected static HashMap<String, String[]> charsArray() {
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		map.put("0", new String[]{"°", "₀", "۰", "０"});
		map.put("1", new String[]{"¹", "₁", "۱", "１"});
		map.put("2", new String[]{"²", "₂", "۲", "２"});
		map.put("3", new String[]{"³", "₃", "۳", "３"});
		map.put("4", new String[]{"⁴", "₄", "۴", "٤", "４"});
		map.put("5", new String[]{"⁵", "₅", "۵", "٥", "５"});
		map.put("6", new String[]{"⁶", "₆", "۶", "٦", "６"});
		map.put("7", new String[]{"⁷", "₇", "۷", "７"});
		map.put("8", new String[]{"⁸", "₈", "۸", "８"});
		map.put("9", new String[]{"⁹", "₉", "۹", "９"});
		map.put("a", new String[]{"à", "á", "ả", "ã", "ạ", "ă", "ắ", "ằ", "ẳ", "ẵ", "ặ", "â", "ấ", "ầ", "ẩ", "ẫ", "ậ", "ā", "ą", "å", "α", "ά", "ἀ", "ἁ", "ἂ", "ἃ", "ἄ", "ἅ", "ἆ", "ἇ", "ᾀ", "ᾁ", "ᾂ", "ᾃ", "ᾄ", "ᾅ", "ᾆ", "ᾇ", "ὰ", "ά", "ᾰ", "ᾱ", "ᾲ", "ᾳ", "ᾴ", "ᾶ", "ᾷ", "а", "أ", "အ", "ာ", "ါ", "ǻ", "ǎ", "ª", "ა", "अ", "ا", "ａ", "ä"});
		map.put("b", new String[]{"б", "β", "ب", "ဗ", "ბ", "ｂ"});
		map.put("c", new String[]{"ç", "ć", "č", "ĉ", "ċ", "ｃ"});
		map.put("d", new String[]{"ď", "ð", "đ", "ƌ", "ȡ", "ɖ", "ɗ", "ᵭ", "ᶁ", "ᶑ", "д", "δ", "د", "ض", "ဍ", "ဒ", "დ", "ｄ"});
		map.put("e", new String[]{"é", "è", "ẻ", "ẽ", "ẹ", "ê", "ế", "ề", "ể", "ễ", "ệ", "ë", "ē", "ę", "ě", "ĕ", "ė", "ε", "έ", "ἐ", "ἑ", "ἒ", "ἓ", "ἔ", "ἕ", "ὲ", "έ", "е", "ё", "э", "є", "ə", "ဧ", "ေ", "ဲ", "ე", "ए", "إ", "ئ", "ｅ"});
		map.put("f", new String[]{"ф", "φ", "ف", "ƒ", "ფ", "ｆ"});
		map.put("g", new String[]{"ĝ", "ğ", "ġ", "ģ", "г", "ґ", "γ", "ဂ", "გ", "گ", "ｇ"});
		map.put("h", new String[]{"ĥ", "ħ", "η", "ή", "ح", "ه", "ဟ", "ှ", "ჰ", "ｈ"});
		map.put("i", new String[]{"í", "ì", "ỉ", "ĩ", "ị", "î", "ï", "ī", "ĭ", "į", "ı", "ι", "ί", "ϊ", "ΐ", "ἰ", "ἱ", "ἲ", "ἳ", "ἴ", "ἵ", "ἶ", "ἷ", "ὶ", "ί", "ῐ", "ῑ", "ῒ", "ΐ", "ῖ", "ῗ", "і", "ї", "и", "ဣ", "ိ", "ီ", "ည်", "ǐ", "ი", "इ", "ی", "ｉ"});
		map.put("j", new String[]{"ĵ", "ј", "Ј", "ჯ", "ج", "ｊ"});
		map.put("k", new String[]{"ķ", "ĸ", "к", "κ", "Ķ", "ق", "ك", "က", "კ", "ქ", "ک", "ｋ"});
		map.put("l", new String[]{"ł", "ľ", "ĺ", "ļ", "ŀ", "л", "λ", "ل", "လ", "ლ", "ｌ"});
		map.put("m", new String[]{"м", "μ", "م", "မ", "მ", "ｍ"});
		map.put("n", new String[]{"ñ", "ń", "ň", "ņ", "ŉ", "ŋ", "ν", "н", "ن", "န", "ნ", "ｎ"});
		map.put("o", new String[]{"ó", "ò", "ỏ", "õ", "ọ", "ô", "ố", "ồ", "ổ", "ỗ", "ộ", "ơ", "ớ", "ờ", "ở", "ỡ", "ợ", "ø", "ō", "ő", "ŏ", "ο", "ὀ", "ὁ", "ὂ", "ὃ", "ὄ", "ὅ", "ὸ", "ό", "о", "و", "ို", "ǒ", "ǿ", "º", "ო", "ओ", "ｏ", "ö"});
		map.put("p", new String[]{"п", "π", "ပ", "პ", "پ", "ｐ"});
		map.put("q", new String[]{"ყ", "ｑ"});
		map.put("r", new String[]{"ŕ", "ř", "ŗ", "р", "ρ", "ر", "რ", "ｒ"});
		map.put("s", new String[]{"ś", "š", "ş", "с", "σ", "ș", "ς", "س", "ص", "စ", "ſ", "ს", "ｓ"});
		map.put("t", new String[]{"ť", "ţ", "т", "τ", "ț", "ت", "ط", "ဋ", "တ", "ŧ", "თ", "ტ", "ｔ"});
		map.put("u", new String[]{"ú", "ù", "ủ", "ũ", "ụ", "ư", "ứ", "ừ", "ử", "ữ", "ự", "û", "ū", "ů", "ű", "ŭ", "ų", "µ", "у", "ဉ", "ု", "ူ", "ǔ", "ǖ", "ǘ", "ǚ", "ǜ", "უ", "उ", "ｕ", "ў", "ü"});
		map.put("v", new String[]{"в", "ვ", "ϐ", "ｖ"});
		map.put("w", new String[]{"ŵ", "ω", "ώ", "ဝ", "ွ", "ｗ"});
		map.put("x", new String[]{"χ", "ξ", "ｘ"});
		map.put("y", new String[]{"ý", "ỳ", "ỷ", "ỹ", "ỵ", "ÿ", "ŷ", "й", "ы", "υ", "ϋ", "ύ", "ΰ", "ي", "ယ", "ｙ"});
		map.put("z", new String[]{"ź", "ž", "ż", "з", "ζ", "ز", "ဇ", "ზ", "ｚ"});
		map.put("aa", new String[]{"ع", "आ", "آ"});
		map.put("ae", new String[]{"æ", "ǽ"});
		map.put("ai", new String[]{"ऐ"});
		map.put("ch", new String[]{"ч", "ჩ", "ჭ", "چ"});
		map.put("dj", new String[]{"ђ", "đ"});
		map.put("dz", new String[]{"џ", "ძ"});
		map.put("ei", new String[]{"ऍ"});
		map.put("gh", new String[]{"غ", "ღ"});
		map.put("ii", new String[]{"ई"});
		map.put("ij", new String[]{"ĳ"});
		map.put("kh", new String[]{"х", "خ", "ხ"});
		map.put("lj", new String[]{"љ"});
		map.put("nj", new String[]{"њ"});
		map.put("oe", new String[]{"ö", "œ", "ؤ"});
		map.put("oi", new String[]{"ऑ"});
		map.put("oii", new String[]{"ऒ"});
		map.put("ps", new String[]{"ψ"});
		map.put("sh", new String[]{"ш", "შ", "ش"});
		map.put("shch", new String[]{"щ"});
		map.put("ss", new String[]{"ß"});
		map.put("sx", new String[]{"ŝ"});
		map.put("th", new String[]{"þ", "ϑ", "θ", "ث", "ذ", "ظ"});
		map.put("ts", new String[]{"ц", "ც", "წ"});
		map.put("ue", new String[]{"ü"});
		map.put("uu", new String[]{"ऊ"});
		map.put("ya", new String[]{"я"});
		map.put("yu", new String[]{"ю"});
		map.put("zh", new String[]{"ж", "ჟ", "ژ"});
		map.put("(c)", new String[]{"©"});
		map.put("A", new String[]{"Á", "À", "Ả", "Ã", "Ạ", "Ă", "Ắ", "Ằ", "Ẳ", "Ẵ", "Ặ", "Â", "Ấ", "Ầ", "Ẩ", "Ẫ", "Ậ", "Å", "Ā", "Ą", "Α", "Ά", "Ἀ", "Ἁ", "Ἂ", "Ἃ", "Ἄ", "Ἅ", "Ἆ", "Ἇ", "ᾈ", "ᾉ", "ᾊ", "ᾋ", "ᾌ", "ᾍ", "ᾎ", "ᾏ", "Ᾰ", "Ᾱ", "Ὰ", "Ά", "ᾼ", "А", "Ǻ", "Ǎ", "Ａ", "Ä"});
		map.put("B", new String[]{"Б", "Β", "ब", "Ｂ"});
		map.put("C", new String[]{"Ç", "Ć", "Č", "Ĉ", "Ċ", "Ｃ"});
		map.put("D", new String[]{"Ď", "Ð", "Đ", "Ɖ", "Ɗ", "Ƌ", "ᴅ", "ᴆ", "Д", "Δ", "Ｄ"});
		map.put("E", new String[]{"É", "È", "Ẻ", "Ẽ", "Ẹ", "Ê", "Ế", "Ề", "Ể", "Ễ", "Ệ", "Ë", "Ē", "Ę", "Ě", "Ĕ", "Ė", "Ε", "Έ", "Ἐ", "Ἑ", "Ἒ", "Ἓ", "Ἔ", "Ἕ", "Έ", "Ὲ", "Е", "Ё", "Э", "Є", "Ə", "Ｅ"});
		map.put("F", new String[]{"Ф", "Φ", "Ｆ"});
		map.put("G", new String[]{"Ğ", "Ġ", "Ģ", "Г", "Ґ", "Γ", "Ｇ"});
		map.put("H", new String[]{"Η", "Ή", "Ħ", "Ｈ"});
		map.put("I", new String[]{"Í", "Ì", "Ỉ", "Ĩ", "Ị", "Î", "Ï", "Ī", "Ĭ", "Į", "İ", "Ι", "Ί", "Ϊ", "Ἰ", "Ἱ", "Ἳ", "Ἴ", "Ἵ", "Ἶ", "Ἷ", "Ῐ", "Ῑ", "Ὶ", "Ί", "И", "І", "Ї", "Ǐ", "ϒ", "Ｉ"});
		map.put("J", new String[]{"Ｊ"});
		map.put("K", new String[]{"К", "Κ", "Ｋ"});
		map.put("L", new String[]{"Ĺ", "Ł", "Л", "Λ", "Ļ", "Ľ", "Ŀ", "ल", "Ｌ"});
		map.put("M", new String[]{"М", "Μ", "Ｍ"});
		map.put("N", new String[]{"Ń", "Ñ", "Ň", "Ņ", "Ŋ", "Н", "Ν", "Ｎ"});
		map.put("O", new String[]{"Ó", "Ò", "Ỏ", "Õ", "Ọ", "Ô", "Ố", "Ồ", "Ổ", "Ỗ", "Ộ", "Ơ", "Ớ", "Ờ", "Ở", "Ỡ", "Ợ", "Ø", "Ō", "Ő", "Ŏ", "Ο", "Ό", "Ὀ", "Ὁ", "Ὂ", "Ὃ", "Ὄ", "Ὅ", "Ὸ", "Ό", "О", "Ө", "Ǒ", "Ǿ", "Ｏ", "Ö"});
		map.put("P", new String[]{"П", "Π", "Ｐ"});
		map.put("Q", new String[]{"Ｑ"});
		map.put("R", new String[]{"Ř", "Ŕ", "Р", "Ρ", "Ŗ", "Ｒ"});
		map.put("S", new String[]{"Ş", "Ŝ", "Ș", "Š", "Ś", "С", "Σ", "Ｓ"});
		map.put("T", new String[]{"Ť", "Ţ", "Ŧ", "Ț", "Т", "Τ", "Ｔ"});
		map.put("U", new String[]{"Ú", "Ù", "Ủ", "Ũ", "Ụ", "Ư", "Ứ", "Ừ", "Ử", "Ữ", "Ự", "Û", "Ū", "Ů", "Ű", "Ŭ", "Ų", "У", "Ǔ", "Ǖ", "Ǘ", "Ǚ", "Ǜ", "Ｕ", "Ў", "Ü"});
		map.put("V", new String[]{"В", "Ｖ"});
		map.put("W", new String[]{"Ω", "Ώ", "Ŵ", "Ｗ"});
		map.put("X", new String[]{"Χ", "Ξ", "Ｘ"});
		map.put("Y", new String[]{"Ý", "Ỳ", "Ỷ", "Ỹ", "Ỵ", "Ÿ", "Ῠ", "Ῡ", "Ὺ", "Ύ", "Ы", "Й", "Υ", "Ϋ", "Ŷ", "Ｙ"});
		map.put("Z", new String[]{"Ź", "Ž", "Ż", "З", "Ζ", "Ｚ"});
		map.put("AE", new String[]{"Æ", "Ǽ"});
		map.put("Ch", new String[]{"Ч"});
		map.put("Dj", new String[]{"Ђ"});
		map.put("Dz", new String[]{"Џ"});
		map.put("Gx", new String[]{"Ĝ"});
		map.put("Hx", new String[]{"Ĥ"});
		map.put("Ij", new String[]{"Ĳ"});
		map.put("Jx", new String[]{"Ĵ"});
		map.put("Kh", new String[]{"Х"});
		map.put("Lj", new String[]{"Љ"});
		map.put("Nj", new String[]{"Њ"});
		map.put("Oe", new String[]{"Œ"});
		map.put("Ps", new String[]{"Ψ"});
		map.put("Sh", new String[]{"Ш"});
		map.put("Shch", new String[]{"Щ"});
		map.put("Ss", new String[]{"ẞ"});
		map.put("Th", new String[]{"Þ", "Θ"});
		map.put("Ts", new String[]{"Ц"});
		map.put("Ya", new String[]{"Я"});
		map.put("Yu", new String[]{"Ю"});
		map.put("Zh", new String[]{"Ж"});
		map.put(" ", new String[]{"\\xC2\\xA0", "\\xE2\\x80\\x80", "\\xE2\\x80\\x81", "\\xE2\\x80\\x82", "\\xE2\\x80\\x83", "\\xE2\\x80\\x84", "\\xE2\\x80\\x85", "\\xE2\\x80\\x86", "\\xE2\\x80\\x87", "\\xE2\\x80\\x88", "\\xE2\\x80\\x89", "\\xE2\\x80\\x8A", "\\xE2\\x80\\xAF", "\\xE2\\x81\\x9F", "\\xE3\\x80\\x80", "\\xEF\\xBE\\xA0"});
		return map;
	}
}
