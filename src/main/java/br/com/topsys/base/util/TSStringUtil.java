/*
 * Created on 24/08/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.com.topsys.base.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author andre
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public final class TSStringUtil {

	public static final String EMPTY = "";

	private static final String[] PADDING = new String[Character.MAX_VALUE];
	
	private TSStringUtil() {
		
	}

	static {
		// space padding is most common, start with 64 chars
		PADDING[32] = "                                                                ";
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return false;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return true;
			}
		}
		return false;
	}

	public static String trim(String str) {
		return (str == null ? null : str.trim());
	}

	public static String trimToNull(String str) {
		String ts = trim(str);
		return (ts == null || ts.length() == 0 ? null : ts);
	}

	public static String trimToEmpty(String str) {
		return (str == null ? EMPTY : str.trim());
	}

	public static String strip(String str) {
		return strip(str, null);
	}

	public static String stripToNull(String str) {
		if (str == null) {
			return null;
		}
		str = strip(str, null);
		return (str.length() == 0 ? null : str);
	}

	public static String stripToEmpty(String str) {
		return (str == null ? EMPTY : strip(str, null));
	}

	public static String strip(String str, String stripChars) {
		if (str == null || str.length() == 0) {
			return str;
		}
		str = stripStart(str, stripChars);
		return stripEnd(str, stripChars);
	}

	public static String stripStart(String str, String stripChars) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		int start = 0;
		if (stripChars == null) {
			while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
				start++;
			}
		} else if (stripChars.length() == 0) {
			return str;
		} else {
			while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
				start++;
			}
		}
		return str.substring(start);
	}

	public static String stripEnd(String str, String stripChars) {
		int end;
		if (str == null || (end = str.length()) == 0) {
			return str;
		}

		if (stripChars == null) {
			while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
				end--;
			}
		} else if (stripChars.length() == 0) {
			return str;
		} else {
			while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
				end--;
			}
		}
		return str.substring(0, end);
	}

	public static String[] stripAll(String[] strs) {
		return stripAll(strs, null);
	}

	public static String[] stripAll(String[] strs, String stripChars) {
		int strsLen;
		if (strs == null || (strsLen = strs.length) == 0) {
			return strs;
		}
		String[] newArr = new String[strsLen];
		for (int i = 0; i < strsLen; i++) {
			newArr[i] = strip(strs[i], stripChars);
		}
		return newArr;
	}

	public static boolean equals(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equals(str2));
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equalsIgnoreCase(str2));
	}

	public static int indexOf(String str, char searchChar) {
		if (str == null || str.length() == 0) {
			return -1;
		}
		return str.indexOf(searchChar);
	}

	public static int indexOf(String str, char searchChar, int startPos) {
		if (str == null || str.length() == 0) {
			return -1;
		}
		return str.indexOf(searchChar, startPos);
	}

	public static int indexOf(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return -1;
		}
		return str.indexOf(searchStr);
	}

	public static int indexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return -1;
		}
		// JDK1.2/JDK1.3 have a bug, when startPos > str.length for "", hence
		if (searchStr.length() == 0 && startPos >= str.length()) {
			return str.length();
		}
		return str.indexOf(searchStr, startPos);
	}

	public static int lastIndexOf(String str, char searchChar) {
		if (str == null || str.length() == 0) {
			return -1;
		}
		return str.lastIndexOf(searchChar);
	}

	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if (str == null || str.length() == 0) {
			return -1;
		}
		return str.lastIndexOf(searchChar, startPos);
	}

	public static int lastIndexOf(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return -1;
		}
		return str.lastIndexOf(searchStr);
	}

	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return -1;
		}
		return str.lastIndexOf(searchStr, startPos);
	}

	public static boolean contains(String str, char searchChar) {
		if (str == null || str.length() == 0) {
			return false;
		}
		return (str.indexOf(searchChar) >= 0);
	}

	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		return (str.indexOf(searchStr) >= 0);
	}

	public static int indexOfAny(String str, char[] searchChars) {
		if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int indexOfAny(String str, String searchChars) {
		if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
			return -1;
		}
		return indexOfAny(str, searchChars.toCharArray());
	}

	public static int indexOfAnyBut(String str, char[] searchChars) {
		if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
			return -1;
		}
		outer: for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					continue outer;
				}
			}
			return i;
		}
		return -1;
	}

	public static int indexOfAnyBut(String str, String searchChars) {
		if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			if (searchChars.indexOf(str.charAt(i)) < 0) {
				return i;
			}
		}
		return -1;
	}

	public static boolean containsOnly(String str, char[] valid) {
		// All these pre-checks are to maintain API with an older version
		if ((valid == null) || (str == null)) {
			return false;
		}
		if (str.length() == 0) {
			return true;
		}
		if (valid.length == 0) {
			return false;
		}
		return indexOfAnyBut(str, valid) == -1;
	}

	public static boolean containsOnly(String str, String validChars) {
		if (str == null || validChars == null) {
			return false;
		}
		return containsOnly(str, validChars.toCharArray());
	}

	public static boolean containsNone(String str, char[] invalidChars) {
		if (str == null || invalidChars == null) {
			return true;
		}
		int strSize = str.length();
		int validSize = invalidChars.length;
		for (int i = 0; i < strSize; i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < validSize; j++) {
				if (invalidChars[j] == ch) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean containsNone(String str, String invalidChars) {
		if (str == null || invalidChars == null) {
			return true;
		}
		return containsNone(str, invalidChars.toCharArray());
	}

	public static int indexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}
		int sz = searchStrs.length;

		// String's can't have a MAX_VALUEth index.
		int ret = Integer.MAX_VALUE;

		int tmp = 0;
		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];
			if (search == null) {
				continue;
			}
			tmp = str.indexOf(search);
			if (tmp == -1) {
				continue;
			}

			if (tmp < ret) {
				ret = tmp;
			}
		}

		return (ret == Integer.MAX_VALUE) ? -1 : ret;
	}

	public static int lastIndexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}
		int sz = searchStrs.length;
		int ret = -1;
		int tmp = 0;
		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];
			if (search == null) {
				continue;
			}
			tmp = str.lastIndexOf(search);
			if (tmp > ret) {
				ret = tmp;
			}
		}
		return ret;
	}

	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		// handle negatives, which means last n characters
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return EMPTY;
		}

		return str.substring(start);
	}

	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		// handle negatives
		if (end < 0) {
			end = str.length() + end; // remember end is negative
		}
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		// check length next
		if (end > str.length()) {
			end = str.length();
		}

		// if start is greater than end, return ""
		if (start > end) {
			return EMPTY;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	// Left/Right/Mid
	// -----------------------------------------------------------------------

	public static String left(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		} else {
			return str.substring(0, len);
		}
	}

	public static String right(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		} else {
			return str.substring(str.length() - len);
		}
	}

	public static String mid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0 || pos > str.length()) {
			return EMPTY;
		}
		if (pos < 0) {
			pos = 0;
		}
		if (str.length() <= (pos + len)) {
			return str.substring(pos);
		} else {
			return str.substring(pos, pos + len);
		}
	}

	public static String substringBefore(String str, String separator) {
		if (str == null || separator == null || str.length() == 0) {
			return str;
		}
		if (separator.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String substringAfter(String str, String separator) {
		if (str == null || str.length() == 0) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	public static String substringBeforeLast(String str, String separator) {
		if (str == null || separator == null || str.length() == 0 || separator.length() == 0) {
			return str;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String substringAfterLast(String str, String separator) {
		if (str == null || str.length() == 0) {
			return str;
		}
		if (separator == null || separator.length() == 0) {
			return EMPTY;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1 || pos == (str.length() - separator.length())) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	public static String substringBetween(String str, String tag) {
		return substringBetween(str, tag, tag);
	}

	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	

	public static String join(Object[] array) {
		return join(array, null);
	}

	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}
		int arraySize = array.length;
		int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].toString().length()) + 1) * arraySize);
		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = EMPTY;
		}
		int arraySize = array.length;

		// ArraySize == 0: Len = 0
		// ArraySize > 0: Len = NofStrings *(len(firstString) + len(separator))
		// (Assuming that all Strings are roughly equally long)
		int bufSize = ((arraySize == 0) ? 0
				: arraySize * ((array[0] == null ? 16 : array[0].toString().length())
						+ ((separator != null) ? separator.length() : 0)));

		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if ((separator != null) && (i > 0)) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	public static String join(Iterator iterator, char separator) {
		if (iterator == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(256); // Java default is 16,
													// probably too small
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
			if (iterator.hasNext()) {
				buf.append(separator);
			}
		}
		return buf.toString();
	}

	public static String join(Iterator iterator, String separator) {
		if (iterator == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(256); // Java default is 16,
													// probably too small
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
			if ((separator != null) && iterator.hasNext()) {
				buf.append(separator);
			}
		}
		return buf.toString();
	}

	public static String deleteWhitespace(String str) {
		if (str == null) {
			return null;
		}
		int sz = str.length();
		StringBuffer buffer = new StringBuffer(sz);
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				buffer.append(str.charAt(i));
			}
		}
		return buffer.toString();
	}

	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	public static String replace(String text, String repl, String with, int max) {
		if (text == null || repl == null || with == null || repl.length() == 0 || max == 0) {
			return text;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0, end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	public static String replaceChars(String str, char searchChar, char replaceChar) {
		if (str == null) {
			return null;
		}
		return str.replace(searchChar, replaceChar);
	}

	public static String replaceChars(String str, String searchChars, String replaceChars) {
		if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
			return str;
		}
		char[] chars = str.toCharArray();
		int len = chars.length;
		boolean modified = false;
		for (int i = 0, isize = searchChars.length(); i < isize; i++) {
			char searchChar = searchChars.charAt(i);
			if (replaceChars == null || i >= replaceChars.length()) {
				// delete
				int pos = 0;
				for (int j = 0; j < len; j++) {
					if (chars[j] != searchChar) {
						chars[pos++] = chars[j];
					} else {
						modified = true;
					}
				}
				len = pos;
			} else {
				// replace
				for (int j = 0; j < len; j++) {
					if (chars[j] == searchChar) {
						chars[j] = replaceChars.charAt(i);
						modified = true;
					}
				}
			}
		}
		if (modified == false) {
			return str;
		}
		return new String(chars, 0, len);
	}

	public static String overlay(String str, String overlay, int start, int end) {
		if (str == null) {
			return null;
		}
		if (overlay == null) {
			overlay = EMPTY;
		}
		int len = str.length();
		if (start < 0) {
			start = 0;
		}
		if (start > len) {
			start = len;
		}
		if (end < 0) {
			end = 0;
		}
		if (end > len) {
			end = len;
		}
		if (start > end) {
			int temp = start;
			start = end;
			end = temp;
		}
		return new StringBuffer(len + start - end + overlay.length() + 1).append(str.substring(0, start))
				.append(overlay).append(str.substring(end)).toString();
	}

	public static String upperCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

	public static String lowerCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	/**
	 * <p>
	 * Capitalizes a String changing the first letter to title case as per
	 * {@link Character#toTitleCase(char)}. No other letters are changed.
	 * </p>
	 * 
	 * <p>
	 * For a word based alorithm, see {@link WordUtils#capitalize(String)}. A
	 * <code>null</code> input String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * 
	 *  StringUtils.capitalize(null)  = null
	 *  StringUtils.capitalize(&quot;&quot;)    = &quot;&quot;
	 *  StringUtils.capitalize(&quot;cat&quot;) = &quot;Cat&quot;
	 *  StringUtils.capitalize(&quot;cAt&quot;) = &quot;CAt&quot;
	 * 
	 * </pre>
	 * 
	 * @param str the String to capitalize, may be null
	 * @return the capitalized String, <code>null</code> if null String input
	 * @see WordUtils#capitalize(String)
	 * @see #uncapitalize(String)
	 * @since 2.0
	 */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}

	/**
	 * <p>
	 * Capitalizes a String changing the first letter to title case as per
	 * {@link Character#toTitleCase(char)}. No other letters are changed.
	 * </p>
	 * 
	 * @param str the String to capitalize, may be null
	 * @return the capitalized String, <code>null</code> if null String input
	 * @deprecated Use the standardly named {@link #capitalize(String)}. Method will
	 *             be removed in Commons Lang 3.0.
	 */
	public static String capitalise(String str) {
		return capitalize(str);
	}

	/**
	 * <p>
	 * Uncapitalizes a String changing the first letter to title case as per
	 * {@link Character#toLowerCase(char)}. No other letters are changed.
	 * </p>
	 * 
	 * <p>
	 * For a word based alorithm, see {@link WordUtils#uncapitalize(String)}. A
	 * <code>null</code> input String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * 
	 *  StringUtils.uncapitalize(null)  = null
	 *  StringUtils.uncapitalize(&quot;&quot;)    = &quot;&quot;
	 *  StringUtils.uncapitalize(&quot;Cat&quot;) = &quot;cat&quot;
	 *  StringUtils.uncapitalize(&quot;CAT&quot;) = &quot;cAT&quot;
	 * 
	 * </pre>
	 * 
	 * @param str the String to uncapitalize, may be null
	 * @return the uncapitalized String, <code>null</code> if null String input
	 * @see WordUtils#uncapitalize(String)
	 * @see #capitalize(String)
	 * @since 2.0
	 */
	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}

	/**
	 * <p>
	 * Uncapitalizes a String changing the first letter to title case as per
	 * {@link Character#toLowerCase(char)}. No other letters are changed.
	 * </p>
	 * 
	 * @param str the String to uncapitalize, may be null
	 * @return the uncapitalized String, <code>null</code> if null String input
	 * @deprecated Use the standardly named {@link #uncapitalize(String)}. Method
	 *             will be removed in Commons Lang 3.0.
	 */
	public static String uncapitalise(String str) {
		return uncapitalize(str);
	}

	/**
	 * <p>
	 * Swaps the case of a String changing upper and title case to lower case, and
	 * lower case to upper case.
	 * </p>
	 * 
	 * <ul>
	 * <li>Upper case character converts to Lower case</li>
	 * <li>Title case character converts to Lower case</li>
	 * <li>Lower case character converts to Upper case</li>
	 * </ul>
	 * 
	 * <p>
	 * For a word based alorithm, see {@link WordUtils#swapCase(String)}. A
	 * <code>null</code> input String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * 
	 *  StringUtils.swapCase(null)                 = null
	 *  StringUtils.swapCase(&quot;&quot;)                   = &quot;&quot;
	 *  StringUtils.swapCase(&quot;The dog has a BONE&quot;) = &quot;tHE DOG HAS A bone&quot;
	 * 
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer performs a word
	 * based alorithm. If you only use ASCII, you will notice no change. That
	 * functionality is available in WordUtils.
	 * </p>
	 * 
	 * @param str the String to swap case, may be null
	 * @return the changed String, <code>null</code> if null String input
	 */
	public static String swapCase(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		StringBuffer buffer = new StringBuffer(strLen);

		char ch = 0;
		for (int i = 0; i < strLen; i++) {
			ch = str.charAt(i);
			if (Character.isUpperCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if (Character.isTitleCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if (Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}
			buffer.append(ch);
		}
		return buffer.toString();
	}

	/**
	 * <p>
	 * Capitalizes all the whitespace separated words in a String. Only the first
	 * letter of each word is changed.
	 * </p>
	 * 
	 * <p>
	 * Whitespace is defined by {@link Character#isWhitespace(char)}. A
	 * <code>null</code> input String returns <code>null</code>.
	 * </p>
	 * 
	 * @param str the String to capitalize, may be null
	 * @return capitalized String, <code>null</code> if null String input
	 * @deprecated Use the relocated {@link WordUtils#capitalize(String)}. Method
	 *             will be removed in Commons Lang 3.0.
	 */
	public static String capitaliseAllWords(String str) {
		return TSWordUtil.capitalize(str);
	}

	// Count matches
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Counts how many times the substring appears in the larger String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String input returns <code>0</code>.
	 * </p>
	 * 
	 * <pre>
	 * 
	 *  StringUtils.countMatches(null, *)       = 0
	 *  StringUtils.countMatches(&quot;&quot;, *)         = 0
	 *  StringUtils.countMatches(&quot;abba&quot;, null)  = 0
	 *  StringUtils.countMatches(&quot;abba&quot;, &quot;&quot;)    = 0
	 *  StringUtils.countMatches(&quot;abba&quot;, &quot;a&quot;)   = 2
	 *  StringUtils.countMatches(&quot;abba&quot;, &quot;ab&quot;)  = 1
	 *  StringUtils.countMatches(&quot;abba&quot;, &quot;xxx&quot;) = 0
	 * 
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param sub the substring to count, may be null
	 * @return the number of occurances, 0 if either String is <code>null</code>
	 */
	public static int countMatches(String str, String sub) {
		if (str == null || str.length() == 0 || sub == null || sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			count++;
			idx += sub.length();
		}
		return count;
	}
	
	public static boolean isNumeric(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	// Character Tests
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if the String contains only unicode letters.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("") will
	 * return <code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * 
	 *  StringUtils.isAlpha(null)   = false
	 *  StringUtils.isAlpha(&quot;&quot;)     = true
	 *  StringUtils.isAlpha(&quot;  &quot;)   = false
	 *  StringUtils.isAlpha(&quot;abc&quot;)  = true
	 *  StringUtils.isAlpha(&quot;ab2c&quot;) = false
	 *  StringUtils.isAlpha(&quot;ab-c&quot;) = false
	 * 
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if only contains letters, and is non-null
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetter(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAlphaSpace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isLetter(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	public static String escapeSql(String str) {
		if (str == null) {
			return null;
		}
		return replace(str, "'", "''");
	}

	public static String removerAcentos(String palavra) {

		if (TSUtil.isEmpty(palavra)) {

			return null;

		} else {

			return Normalizer.normalize(new StringBuilder(palavra), Normalizer.Form.NFKD)
					.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

		}

	}

	public static String removerNaoAlfaNumerico(String palavra) {

		if (TSUtil.isEmpty(palavra)) {

			return null;

		} else {

			return palavra.replaceAll("\\W", "");

		}

	}

	public static String formatarNomeProprio(String nome) {

		if (!TSUtil.isEmpty(nome)) {

			List<String> preposicoes = new ArrayList<String>();

			preposicoes.add("da");

			preposicoes.add("das");

			preposicoes.add("de");

			preposicoes.add("do");

			preposicoes.add("dos");

			preposicoes.add("e");

			nome = nome.replaceAll("  ", " ").trim().toLowerCase();

			String nomes[] = nome.split(" ");

			String nomeFormatado = "";

			for (String parte : nomes) {

				if (!preposicoes.contains(parte)) {

					nomeFormatado += " " + parte.substring(0, 1).toUpperCase() + parte.substring(1, parte.length());

				} else {

					nomeFormatado += " " + parte;

				}

			}

			return nomeFormatado.trim();

		} else {

			return null;

		}

	}

	public static String removerFormatacaoIdentificador(String documento) {

		if (!TSUtil.isEmpty(documento)) {

			documento = documento.replaceAll("-", "");

			documento = documento.replaceAll("/", "");

			String numero = "";

			for (int i = 0; i < documento.length(); i++) {

				if (documento.charAt(i) != '.') {

					numero += documento.charAt(i);

				}

			}

			documento = numero;

		}

		return documento;

	}

}