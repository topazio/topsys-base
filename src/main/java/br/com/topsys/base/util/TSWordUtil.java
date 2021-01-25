package br.com.topsys.base.util;


public final class TSWordUtil {

	
	private TSWordUtil() {
	}

	public static String wrap(String str, int wrapLength) {
		return wrap(str, wrapLength, null, false);
	}

	
	public static String wrap(String str, int wrapLength, String newLineStr,
			boolean wrapLongWords) {
		if (str == null) {
			return null;
		}
		if (newLineStr == null) {
			newLineStr = TSUtil.LINE_SEPARATOR;
		}
		if (wrapLength < 1) {
			wrapLength = 1;
		}
		int inputLineLength = str.length();
		int offset = 0;
		StringBuffer wrappedLine = new StringBuffer(inputLineLength + 32);

		while ((inputLineLength - offset) > wrapLength) {
			if (str.charAt(offset) == ' ') {
				offset++;
				continue;
			}
			int spaceToWrapAt = str.lastIndexOf(' ', wrapLength + offset);

			if (spaceToWrapAt >= offset) {
				// normal case
				wrappedLine.append(str.substring(offset, spaceToWrapAt));
				wrappedLine.append(newLineStr);
				offset = spaceToWrapAt + 1;

			} else {
				// really long word or URL
				if (wrapLongWords) {
					// wrap really long word one line at a time
					wrappedLine.append(str.substring(offset, wrapLength
							+ offset));
					wrappedLine.append(newLineStr);
					offset += wrapLength;
				} else {
					// do not wrap really long word, just extend beyond limit
					spaceToWrapAt = str.indexOf(' ', wrapLength + offset);
					if (spaceToWrapAt >= 0) {
						wrappedLine
								.append(str.substring(offset, spaceToWrapAt));
						wrappedLine.append(newLineStr);
						offset = spaceToWrapAt + 1;
					} else {
						wrappedLine.append(str.substring(offset));
						offset = inputLineLength;
					}
				}
			}
		}

		// Whatever is left in line is short enough to just pass through
		wrappedLine.append(str.substring(offset));

		return wrappedLine.toString();
	}


	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		StringBuffer buffer = new StringBuffer(strLen);
		boolean whitespace = true;
		for (int i = 0; i < strLen; i++) {
			char ch = str.charAt(i);
			if (Character.isWhitespace(ch)) {
				buffer.append(ch);
				whitespace = true;
			} else if (whitespace) {
				buffer.append(Character.toTitleCase(ch));
				whitespace = false;
			} else {
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	
	public static String capitalizeFully(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		StringBuffer buffer = new StringBuffer(strLen);
		boolean whitespace = true;
		for (int i = 0; i < strLen; i++) {
			char ch = str.charAt(i);
			if (Character.isWhitespace(ch)) {
				buffer.append(ch);
				whitespace = true;
			} else if (whitespace) {
				buffer.append(Character.toTitleCase(ch));
				whitespace = false;
			} else {
				buffer.append(Character.toLowerCase(ch));
			}
		}
		return buffer.toString();
	}

	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		StringBuffer buffer = new StringBuffer(strLen);
		boolean whitespace = true;
		for (int i = 0; i < strLen; i++) {
			char ch = str.charAt(i);
			if (Character.isWhitespace(ch)) {
				buffer.append(ch);
				whitespace = true;
			} else if (whitespace) {
				buffer.append(Character.toLowerCase(ch));
				whitespace = false;
			} else {
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	
	public static String swapCase(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		StringBuffer buffer = new StringBuffer(strLen);

		boolean whitespace = true;
		char ch = 0;
		char tmp = 0;

		for (int i = 0; i < strLen; i++) {
			ch = str.charAt(i);
			if (Character.isUpperCase(ch)) {
				tmp = Character.toLowerCase(ch);
			} else if (Character.isTitleCase(ch)) {
				tmp = Character.toLowerCase(ch);
			} else if (Character.isLowerCase(ch)) {
				if (whitespace) {
					tmp = Character.toTitleCase(ch);
				} else {
					tmp = Character.toUpperCase(ch);
				}
			} else {
				tmp = ch;
			}
			buffer.append(tmp);
			whitespace = Character.isWhitespace(ch);
		}
		return buffer.toString();
	}

}