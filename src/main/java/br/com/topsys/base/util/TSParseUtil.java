package br.com.topsys.base.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.topsys.base.exception.TSSystemException;

/*
 * Created on 25/08/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author Andr� Top�zio
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class TSParseUtil {
	private static NumberFormat numFormat = NumberFormat.getNumberInstance();
	
	private TSParseUtil() {
		 
	}

	public static String numberToString(Number valor) {
		String number = null;
		if (valor != null) {
			NumberFormat numFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
			number = numFormat.format(valor);
		} else {
			number = "";
		}
		return number;
	}

	public static Boolean stringToBoolean(String valor) {
		Boolean bool = null;

		if (valor == null || valor.trim().length() == 0) {
			bool = false;
		} else {
			bool = valor.trim().equals("0") ? false : true;
		}
		return bool;
	}

	public static String booleanToString(Boolean valor) {
		if (valor == null) {
			return "0";
		} else {
			return valor.booleanValue() ? "1" : "0";
		}
	}

	public static Integer stringToInteger(String valor) {
		Integer it = null;
		try {
			if (valor != null) {
				it = numFormat.parse(valor).intValue();
			}
		} catch (Exception e) {
		}
		return it;
	}

	public static Long stringToLong(String valor) {
		Long it = null;
		try {
			if (valor != null) {
				it = numFormat.parse(valor).longValue();
			}
		} catch (Exception e) {
		}

		return it;
	}

	public static Float stringToFloat(String valor) {
		Float it = null;
		try {
			if (valor != null) {
				it = numFormat.parse(valor).floatValue();
			}
		} catch (Exception e) {
		}
		return it;
	}

	public static Date stringToDate(String data) {
		Date date = null;
		if (data != null && data.trim().length() > 0) {
			try {
				SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
				date = dtFormat.parse(data);
			} catch (Exception e) {
			}
		}
		return date;
	}

	public static Date stringToDate(String data, String padrao) {
		Date date = null;
		if (data != null && data.trim().length() > 0) {
			try {
				SimpleDateFormat dtFormat = new SimpleDateFormat(padrao);
				date = dtFormat.parse(data);
			} catch (Exception e) {
			}
		}
		return date;
	}

	public static Timestamp stringToTimestamp(String data) {
		Timestamp ts = null;
		if ((data == null) || data.equals("")) {

			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {

			ParsePosition pos = new ParsePosition(0);
			java.util.Date dtu = dateFormat.parse(data, pos);

			ts = new Timestamp(dtu.getTime());

		} catch (Exception e) {
		}

		return ts;
	}

	public static Timestamp stringToTimestamp(String data, String pattern) {
		Timestamp ts = null;
		if ((data == null) || data.equals("")) {

			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		try {

			ParsePosition pos = new ParsePosition(0);
			java.util.Date dtu = dateFormat.parse(data, pos);

			ts = new Timestamp(dtu.getTime());

		} catch (Exception e) {
		}

		return ts;
	}

	public static String timestampToString(Timestamp data) {
		if (data == null) {
			return null;
		}
		java.util.Date date = (java.util.Date) data;
		return dateToString(date, "dd/MM/yyyy");

	}

	public static Timestamp stringDateHourToTimestamp(String data) {
		Timestamp ts = null;
		if ((data == null) || data.equals("")) {

			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {

			ParsePosition pos = new ParsePosition(0);
			java.util.Date date = dateFormat.parse(data, pos);

			ts = new Timestamp(date.getTime());

		} catch (Exception e) {
		}

		return ts;
	}

	public static String timestampToStringHora(Timestamp data) {
		if (data == null) {
			return null;
		}
		java.util.Date date = (java.util.Date) data;

		return dateHourToString(date);

	}

	public static String dateHourToString(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm");
		return sdf.format(data);
	}

	public static Date stringToDateHour(String data) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm");
			date = sdf.parse(data);
		} catch (Exception e) {

			throw new TSSystemException(e);
		}
		return date;
	}

	public static String dateToString(Date data) {
		String dataStr = null;
		if (data != null) {
			SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
			dataStr = dtFormat.format(data);
		} else {
			dataStr = "";
		}
		return dataStr;
	}

	public static String dateToString(Date data, String padrao) {
		String dataStr = null;
		if (data != null) {
			SimpleDateFormat dtFormat = new SimpleDateFormat(padrao);
			dataStr = dtFormat.format(data);
		} else {
			dataStr = "";
		}
		return dataStr;
	}

	private static DecimalFormat monetario() {

		Locale loc = new Locale("br", "PT");
		NumberFormat nf = NumberFormat.getNumberInstance(loc);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("###,##0.00");

		return df;

	}

	private static DecimalFormat monetario1() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));
		DecimalFormat df = (DecimalFormat) nf;
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		df.setDecimalSeparatorAlwaysShown(true);
		String patterns = "###,###.00";
		df.applyPattern(patterns);
		return df;
	}

	public static String stringToMonetario(String value) {
		String retorno = null;
		if (value == null) {
			return null;
		}
		try {
			retorno = monetario().parse(value).toString();
		} catch (RuntimeException e) {

			throw new TSSystemException(e);
		} catch (Exception e) {

			throw new TSSystemException(e);
		}
		return retorno;

	}

	@SuppressWarnings("deprecation")
	public static Double stringToDouble(String value) {
		Double retorno = null;
		if (value == null) {
			return null;
		}
		try {
			char charNumero[] = value.toCharArray();
			String numero = "";
			for (int i = 0; i < charNumero.length; i++) {
				if (charNumero[i] != '.') {
					numero += charNumero[i];
				}

			}

			retorno = new Double(numero.replace(',', '.'));

		} catch (RuntimeException e) {

			throw new TSSystemException(e);
		} catch (Exception e) {

			throw new TSSystemException(e);
		}
		return retorno;
	}

	public static String doubleToString(Double value) {
		String retorno = null;
		if (value == null) {
			return null;
		}
		try {

			retorno = monetario().format(value.doubleValue());
		} catch (RuntimeException e) {

			throw new TSSystemException(e);
		} catch (Exception e) {

			throw new TSSystemException(e);
		}

		return retorno;

	}

	public static Timestamp dateToTimeStamp(Date data) {

		if (!TSUtil.isEmpty(data)) {

			return TSParseUtil.stringToTimestamp(TSParseUtil.dateToString(data, TSDateUtil.DD_MM_YYYY_HH_MM_SS),
					TSDateUtil.DD_MM_YYYY_HH_MM_SS);

		} else {

			return null;

		}

	}

	public static Timestamp dateToTimeStampFinal(Date data) {

		if (!TSUtil.isEmpty(data)) {

			Calendar cal = Calendar.getInstance();

			cal.setTime(data);

			cal.add(Calendar.HOUR, 23);

			cal.add(Calendar.MINUTE, 59);

			cal.add(Calendar.SECOND, 59);

			return TSParseUtil.stringToTimestamp(
					TSParseUtil.dateToString(cal.getTime(), TSDateUtil.DD_MM_YYYY_HH_MM_SS),
					TSDateUtil.DD_MM_YYYY_HH_MM_SS);

		} else {

			return null;

		}

	}
}